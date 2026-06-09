package com.smartbus.service;

import com.smartbus.dto.RouteOptionDTO;
import com.smartbus.dto.RouteSearchDTO;
import com.smartbus.entity.Bus;
import com.smartbus.entity.BusDelay;
import com.smartbus.entity.BusRouteStop;
import com.smartbus.entity.BusSchedule;
import com.smartbus.entity.City;
import com.smartbus.repository.BusDelayRepository;
import com.smartbus.repository.BusRepository;
import com.smartbus.repository.BusRouteStopRepository;
import com.smartbus.repository.BusScheduleRepository;
import com.smartbus.repository.CityRepository;
import com.smartbus.util.TimeCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final CityRepository cityRepository;
    private final BusRepository busRepository;
    private final BusRouteStopRepository busRouteStopRepository;
    private final BusScheduleRepository busScheduleRepository;
    private final BusDelayRepository busDelayRepository;

    public List<RouteOptionDTO> findBestRoutes(RouteSearchDTO dto) {
        String sourceCityName = dto.getSourceCity().trim();
        String destinationCityName = dto.getDestinationCity().trim();

        Optional<City> sourceCityOptional = cityRepository.findAll().stream()
                .filter(city -> city.getCityName().equalsIgnoreCase(sourceCityName))
                .findFirst();

        Optional<City> destinationCityOptional = cityRepository.findAll().stream()
                .filter(city -> city.getCityName().equalsIgnoreCase(destinationCityName))
                .findFirst();

        if (sourceCityOptional.isEmpty() || destinationCityOptional.isEmpty()) {
            return List.of();
        }

        City sourceCity = sourceCityOptional.get();
        City destinationCity = destinationCityOptional.get();

        List<BusRouteStop> routeStops = busRouteStopRepository.findAll();

        List<RouteOptionDTO> routeOptions = new ArrayList<>();

        for (Bus bus : busRepository.findAll()) {
            List<BusRouteStop> stops = routeStops.stream()
                    .filter(stop -> stop.getBus().getId().equals(bus.getId()))
                    .sorted(Comparator.comparingInt(BusRouteStop::getStopOrder))
                    .collect(Collectors.toList());

            int sourceIndex = findStopIndex(stops, sourceCity);
            int destinationIndex = findStopIndex(stops, destinationCity);

            if (sourceIndex < 0 || destinationIndex < 0 || sourceIndex >= destinationIndex) {
                continue;
            }

            BusRouteStop sourceStop = stops.get(sourceIndex);
            BusRouteStop destinationStop = stops.get(destinationIndex);
            int journeyTimeMinutes = destinationStop.getArrivalOffsetMinutes() - sourceStop.getArrivalOffsetMinutes();

            Optional<BusSchedule> scheduleOptional = busScheduleRepository.findByBus(bus);
            int delayMinutes = calculateTotalDelay(bus);
            String arrivalTime = "N/A";
            String dropOffTime = "N/A";

            if (scheduleOptional.isPresent()) {
                LocalTime arrival = TimeCalculator.addMinutes(
                        scheduleOptional.get().getStartTime(),
                        destinationStop.getArrivalOffsetMinutes() + delayMinutes);
                arrivalTime = TimeCalculator.formatTime(arrival);
                dropOffTime = arrivalTime;
            }

            String routeName = stops.subList(sourceIndex, destinationIndex + 1).stream()
                    .map(stop -> stop.getCity().getCityName())
                    .collect(Collectors.joining(" -> "));

            int distanceKm = Math.max(1, (destinationIndex - sourceIndex) * 10);
            int capacity = bus.getCapacity() == null ? 0 : bus.getCapacity();
            int currentPassengers = bus.getCurrentPassengerCount() == null ? 0 : bus.getCurrentPassengerCount();
            double crowdPercentage = capacity > 0
                    ? (currentPassengers * 100.0) / capacity
                    : 0.0;

            routeOptions.add(RouteOptionDTO.builder()
                    .busCode(bus.getBusCode())
                    .route(routeName)
                    .distanceKm(distanceKm)
                    .arrivalTime(arrivalTime)
                    .journeyTimeMinutes(journeyTimeMinutes)
                    .dropOffTime(dropOffTime)
                    .currentPassengers(currentPassengers)
                    .capacity(capacity)
                    .crowdPercentage(Math.round(crowdPercentage * 100.0) / 100.0)
                    .delayMinutes(delayMinutes)
                    .build());
        }

        return routeOptions.stream()
                .sorted(Comparator.comparingInt(RouteOptionDTO::getJourneyTimeMinutes))
                .collect(Collectors.toList());
    }

    private int findStopIndex(List<BusRouteStop> stops, City city) {
        for (int i = 0; i < stops.size(); i++) {
            if (stops.get(i).getCity().getId().equals(city.getId())) {
                return i;
            }
        }
        return -1;
    }

    private int calculateTotalDelay(Bus bus) {
        return busDelayRepository.findByBus(bus).stream()
                .mapToInt(BusDelay::getDelayMinutes)
                .sum();
    }
}

