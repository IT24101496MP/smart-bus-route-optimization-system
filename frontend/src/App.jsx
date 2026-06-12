import { useState } from 'react';
import Register from './pages/Register';
import Login from './pages/Login';
import AdminDashboard from './pages/AdminDashboard';

export default function App() {
  const [page, setPage] = useState('register');

  return (
    <div className="app-shell">
      {page === 'register' && <Register onNavigate={() => setPage('login')} />}
      {page === 'login' && (
        <Login
          onNavigate={() => setPage('register')}
          onAdminLogin={() => setPage('admin')}
        />
      )}
      {page === 'admin' && <AdminDashboard onLogout={() => setPage('login')} />}
    </div>
  );
}
