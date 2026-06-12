import { useEffect, useState } from 'react';
import './AdminDashboard.css';

export default function AdminDashboard({ onLogout }) {
  const [now, setNow] = useState(new Date());

  useEffect(() => {
    const t = setInterval(() => setNow(new Date()), 1000);
    return () => clearInterval(t);
  }, []);

  const stats = {
    users: 1243,
    buses: 86,
    routes: 48,
    delayed: 5,
    overcrowded: 3,
  };

  const activeBuses = [
    { id: 'Bus01/A', status: 'Running', passengers: 35, capacity: 60 },
    { id: 'Bus09/A', status: 'Running', passengers: 48, capacity: 50 },
  ];

  const delayedBuses = [
    { id: 'Bus09/A', delay: 10 },
    { id: 'Bus04/B', delay: 5 },
  ];

  const overcrowded = [
    { id: 'Bus09/A', passengers: 48, capacity: 50 },
  ];

  const activities = [
    { time: '09:15', text: 'Bus01/A Delay Added' },
    { time: '09:10', text: 'New Route Created' },
    { time: '08:55', text: 'Bus05/B Added' },
    { time: '08:30', text: 'Passenger Count Updated' },
  ];

  const sidebarItems = [
    { icon: '🏠', label: 'Dashboard' },
    { icon: '🚌', label: 'Buses' },
    { icon: '🛣', label: 'Routes' },
    { icon: '📍', label: 'Stops' },
    { icon: '📅', label: 'Schedules' },
    { icon: '👥', label: 'Passengers' },
    { icon: '⚠', label: 'Delays' },
    { icon: '👤', label: 'Users' },
    { icon: '🚪', label: 'Logout' },
  ];

  return (
    <div className="admin-shell page-center">
      <header className="top-nav">
        <div className="nav-left">
          <div className="brand-logo">🚌</div>
          <h1 className="nav-title">TransitNova</h1>
        </div>

        <div className="nav-right">
          <div className="nav-datetime">{now.toLocaleString()}</div>
          <div className="nav-profile">Admin</div>
          <button className="secondary-button small" type="button" onClick={onLogout}>
            Logout
          </button>
        </div>
      </header>

      <div className="admin-body">
        <aside className="sidebar">
          <ul>
            {sidebarItems.map((it) => (
              <li key={it.label} className="sidebar-item">
                <span className="sidebar-icon">{it.icon}</span>
                <span className="sidebar-label">{it.label}</span>
              </li>
            ))}
          </ul>
        </aside>

        <main className="admin-content">
          <section className="stats-grid">
            <div className="stat-card">
              <p className="stat-title">Total Users</p>
              <p className="stat-value">{stats.users}</p>
            </div>
            <div className="stat-card">
              <p className="stat-title">Total Buses</p>
              <p className="stat-value">{stats.buses}</p>
            </div>
            <div className="stat-card">
              <p className="stat-title">Total Routes</p>
              <p className="stat-value">{stats.routes}</p>
            </div>
            <div className="stat-card">
              <p className="stat-title">Delayed Buses</p>
              <p className="stat-value">{stats.delayed}</p>
            </div>
            <div className="stat-card">
              <p className="stat-title">Overcrowded</p>
              <p className="stat-value">{stats.overcrowded}</p>
            </div>
          </section>

          <section className="panels">
            <div className="panel">
              <h3>Active Bus Status</h3>
              <div className="panel-list">
                {activeBuses.map((b) => (
                  <div key={b.id} className="panel-item">
                    <div className="bus-id">{b.id}</div>
                    <div className="bus-status">{b.status}</div>
                    <div className="bus-pass">Current Passengers: {b.passengers} / {b.capacity}</div>
                  </div>
                ))}
              </div>
            </div>

            <div className="panel">
              <h3>Delayed Buses</h3>
              <div className="panel-list">
                {delayedBuses.map((d) => (
                  <div key={d.id} className="panel-item">
                    <div className="bus-id">{d.id}</div>
                    <div className="bus-status">Delay: {d.delay} min</div>
                  </div>
                ))}
              </div>
            </div>

            <div className="panel">
              <h3>Overcrowded Buses</h3>
              <div className="panel-list">
                {overcrowded.map((o) => {
                  const level = Math.round((o.passengers / o.capacity) * 100);
                  return (
                    <div key={o.id} className="panel-item overcrowded">
                      <div className="bus-id">{o.id}</div>
                      <div className="bus-pass">Passengers: {o.passengers}/{o.capacity}</div>
                      <div className="crowd-level">
                        Crowd Level: <span className="badge danger">{level}%</span>
                      </div>
                    </div>
                  );
                })}
              </div>
            </div>
          </section>

          <section className="activities-actions">
            <div className="activities panel">
              <h3>Recent Activities</h3>
              <ul className="activity-list">
                {activities.map((a, i) => (
                  <li key={i}><span className="act-time">{a.time}</span> - {a.text}</li>
                ))}
              </ul>
            </div>

            <div className="actions panel">
              <h3>Quick Actions</h3>
              <div className="action-buttons">
                <button className="primary-button">+ Add Bus</button>
                <button className="primary-button">+ Add Route</button>
                <button className="secondary-button">+ Add Delay</button>
                <button className="secondary-button">+ Update Passenger Count</button>
              </div>
            </div>
          </section>
        </main>
      </div>
    </div>
  );
}
