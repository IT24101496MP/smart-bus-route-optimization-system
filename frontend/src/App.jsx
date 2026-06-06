import { useState } from 'react';
import Register from './pages/Register';
import Login from './pages/Login';

export default function App() {
  const [page, setPage] = useState('register');

  return (
    <div className="app-shell">
      {page === 'register' ? (
        <Register onNavigate={() => setPage('login')} />
      ) : (
        <Login onNavigate={() => setPage('register')} />
      )}
    </div>
  );
}
