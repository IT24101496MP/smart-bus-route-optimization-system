import './AdminDashboard.css';

export default function AdminDashboard({ onLogout }) {
  const stats = {
    users: 0,
    buses: 0,
    routes: 0,
    delayed: 0,
    overcrowded: 0,
  };

  const activeBuses = [];
  const delayedBuses = [];
  const overcrowded = [];
  const activities = [];

  const sidebarItems = [
    { icon: '🏠', label: 'Dashboard' },
    { icon: '🚌', label: 'Buses' },
    { icon: '🛣', label: 'Routes' },
    { icon: '📍', label: 'Bus Stops' },
    { icon: '📅', label: 'Schedules' },
    { icon: '👥', label: 'Passenger Counts' },
    { icon: '⚠', label: 'Delays' },
    { icon: '👤', label: 'Users' },
    { icon: '📊', label: 'Reports' },
    { icon: '⚙', label: 'Settings' },
  ];

  return (
    <div className="admin-shell page-center">
      <header className="top-nav">
        <div className="nav-left">
          <div className="brand-logo">🚌</div>
          <h1 className="nav-title">
            <span className="brand-purple">Transit</span>
            <span className="brand-blue">Nova</span>
          </h1>
        </div>

        <div className="nav-right">
          <div className="search-wrapper">
            <span className="search-icon">🔍</span>
            <input
              className="search-input"
              type="search"
              placeholder="Search buses, routes, passengers..."
              aria-label="Search"
            />
          </div>
          <button
            className="icon-button"
            type="button"
            onClick={onLogout}
            aria-label="Log out"
          >
            <svg width="26" height="26" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
              <path d="M14 6H8C6.89543 6 6 6.89543 6 8V10" stroke="#111827" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
              <path d="M6 14V16C6 17.1046 6.89543 18 8 18H14" stroke="#111827" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
              <path d="M10 12H20" stroke="#111827" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
              <path d="M17 9L20 12L17 15" stroke="#111827" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
            </svg>
          </button>
        </div>
      </header>

      <section className="welcome-panel">
        <div>
          <h2>Welcome Back, Admin 👋</h2>
          <p>Manage buses, routes, schedules, delays, and passenger information from one central dashboard.</p>
        </div>
      </section>

      <div className="admin-body">
        <aside className="sidebar" aria-label="Admin navigation">
          <div className="sidebar-brand">
            <div className="sidebar-brand-logo">🚌</div>
            <div>
              <div className="sidebar-brand-name">TransitNova</div>
              <div className="sidebar-brand-desc">Smart Bus System</div>
            </div>
          </div>
          
          <div className="sidebar-heading">Menu</div>
          <ul>
            {sidebarItems.map((it) => (
              <li key={it.label} className={`sidebar-item ${it.label === 'Dashboard' ? 'active' : ''}`}>
                <span className="sidebar-icon">{it.icon}</span>
                <span className="sidebar-label">{it.label}</span>
              </li>
            ))}
          </ul>

          <div className="sidebar-footer">
            <div className="sidebar-user">
              <div className="user-avatar">A</div>
              <div className="user-info">
                <div className="user-name">Admin</div>
                <div className="user-email">admin@transitnova.com</div>
              </div>
            </div>
          </div>
        </aside>

        <main className="admin-content">
          {/* Stats Grid */}
          <section className="stats-grid">
            <div className="stat-card">
              <div className="stat-header">
                <p className="stat-title">Total Users</p>
                <span className="stat-icon">👥</span>
              </div>
              <p className="stat-value">{stats.users}</p>
              <p className="stat-change positive">↑ 12% from last month</p>
            </div>
            <div className="stat-card">
              <div className="stat-header">
                <p className="stat-title">Total Buses</p>
                <span className="stat-icon">🚌</span>
              </div>
              <p className="stat-value">{stats.buses}</p>
              <p className="stat-change positive">↑ 5% from last month</p>
            </div>
            <div className="stat-card">
              <div className="stat-header">
                <p className="stat-title">Total Routes</p>
                <span className="stat-icon">🛣</span>
              </div>
              <p className="stat-value">{stats.routes}</p>
              <p className="stat-change positive">↑ 2% from last month</p>
            </div>
            <div className="stat-card">
              <div className="stat-header">
                <p className="stat-title">Delayed Buses</p>
                <span className="stat-icon">⏱</span>
              </div>
              <p className="stat-value">{stats.delayed}</p>
              <p className="stat-change negative">↓ 25% from yesterday</p>
            </div>
            <div className="stat-card">
              <div className="stat-header">
                <p className="stat-title">Overcrowded Buses</p>
                <span className="stat-icon">👫</span>
              </div>
              <p className="stat-value">{stats.overcrowded}</p>
              <p className="stat-change negative">↓ 16% from yesterday</p>
            </div>
          </section>

          {/* Three Panel Section */}
          <section className="three-panels">
            <div className="panel">
              <div className="panel-header">
                <h3>Active Buses (Live Status)</h3>
                <a href="#" className="view-all-link">View All</a>
              </div>
              <div className="panel-list">
                {activeBuses.length === 0 ? (
                  <div className="empty-state">No active buses added yet.</div>
                ) : (
                  activeBuses.map((b) => (
                    <div key={b.id} className="panel-item">
                      <div className="item-left">
                        <div className="bus-icon">🚌</div>
                        <div>
                          <div className="bus-id">{b.id}</div>
                          <div className="bus-route">{b.route || 'Route info'}</div>
                        </div>
                      </div>
                      <div className="item-right">
                        <div className="bus-status on-time">On Time</div>
                        <div className="bus-pass">{b.passengers} / {b.capacity}</div>
                      </div>
                    </div>
                  ))
                )}
              </div>
            </div>

            <div className="panel">
              <div className="panel-header">
                <h3>Delayed Buses</h3>
                <a href="#" className="view-all-link">View All</a>
              </div>
              <div className="panel-list">
                {delayedBuses.length === 0 ? (
                  <div className="empty-state">No delayed buses yet.</div>
                ) : (
                  delayedBuses.map((d) => (
                    <div key={d.id} className="panel-item">
                      <div className="item-left">
                        <div className="delay-icon">⏱</div>
                        <div>
                          <div className="bus-id">{d.id}</div>
                          <div className="bus-route">{d.route || 'Route info'}</div>
                        </div>
                      </div>
                      <div className="item-right">
                        <div className="delay-badge">{d.delay || 0} min</div>
                      </div>
                    </div>
                  ))
                )}
              </div>
            </div>

            <div className="panel">
              <div className="panel-header">
                <h3>Overcrowded Buses</h3>
                <a href="#" className="view-all-link">View All</a>
              </div>
              <div className="panel-list">
                {overcrowded.length === 0 ? (
                  <div className="empty-state">No overcrowded buses yet.</div>
                ) : (
                  overcrowded.map((o) => {
                    const level = Math.round((o.passengers / o.capacity) * 100);
                    return (
                      <div key={o.id} className="panel-item overcrowded">
                        <div className="item-left">
                          <div className="crowd-icon">👫</div>
                          <div>
                            <div className="bus-id">{o.id}</div>
                            <div className="bus-route">Passengers: {o.passengers}/{o.capacity}</div>
                          </div>
                        </div>
                        <div className="item-right">
                          <div className="crowd-level">{level}%</div>
                        </div>
                      </div>
                    );
                  })
                )}
              </div>
            </div>
          </section>

          {/* Bottom Sections */}
          <section className="bottom-sections">
            <div className="left-col">
              <div className="panel">
                <h3>Today's Passenger Overview</h3>
                <div className="chart-placeholder">
                  <p>📊 Chart will be displayed here</p>
                  <small>No data to display yet</small>
                </div>
              </div>
            </div>

            <div className="right-col">
              <div className="panel">
                <div className="panel-header">
                  <h3>Recent Activities</h3>
                  <a href="#" className="view-all-link">View All</a>
                </div>
                <ul className="activity-list">
                  {activities.length === 0 ? (
                    <li className="empty-state">No activity yet.</li>
                  ) : (
                    activities.map((a, i) => (
                      <li key={i} className="activity-item">
                        <span className="act-icon">📌</span>
                        <span className="act-text">{a.text}</span>
                        <span className="act-time">{a.time}</span>
                      </li>
                    ))
                  )}
                </ul>
              </div>

              <div className="panel">
                <h3>Quick Actions</h3>
                <div className="quick-actions">
                  <button className="quick-action-btn primary">🚌 Add New Bus</button>
                  <button className="quick-action-btn primary">🛣 Add New Route</button>
                  <button className="quick-action-btn secondary">⏱ Record Delay</button>
                  <button className="quick-action-btn secondary">📅 View Schedules</button>
                </div>
              </div>
            </div>
          </section>
        </main>
      </div>
    </div>
  );
}
