package com.smartbus.algorithm;

import java.util.*;

public class DijkstraAlgorithm {

    public Map<String, Integer> shortestDistance(
            Map<String, List<Node>> graph,
            String source) {

        Map<String, Integer> distanceMap =
                new HashMap<>();

        PriorityQueue<Node> queue =
                new PriorityQueue<>(
                        Comparator.comparingInt(Node::getDistance)
                );

        queue.add(new Node(source, 0));

        distanceMap.put(source, 0);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            String currentCity =
                    current.getCity();

            int currentDistance =
                    current.getDistance();

            List<Node> neighbours =
                    graph.getOrDefault(
                            currentCity,
                            new ArrayList<>());

            for (Node neighbour : neighbours) {

                int newDistance =
                        currentDistance
                                + neighbour.getDistance();

                if (!distanceMap.containsKey(
                        neighbour.getCity())
                        ||
                        newDistance <
                                distanceMap.get(
                                        neighbour.getCity())) {

                    distanceMap.put(
                            neighbour.getCity(),
                            newDistance);

                    queue.add(
                            new Node(
                                    neighbour.getCity(),
                                    newDistance));
                }
            }
        }

        return distanceMap;
    }
}