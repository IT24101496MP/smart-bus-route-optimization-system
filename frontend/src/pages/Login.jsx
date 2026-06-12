import { useState } from 'react';
import './Login.css';

export default function Login({ onNavigate, onAdminLogin }) {
  const [form, setForm] = useState({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const [message, setMessage] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (field) => (event) => {
    setForm((prev) => ({ ...prev, [field]: event.target.value }));
    setMessage(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage(null);
    setIsSubmitting(true);

    try {
      const response = await fetch('http://localhost:8080/api/users/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      });

      const data = await response.json();

      if (response.ok && data.success) {
        setMessage({ type: 'success', text: data.message || 'Login successful' });

        if (data.role === 'ADMIN') {
          setTimeout(() => {
            onAdminLogin?.();
          }, 800);
        }
      } else {
        setMessage({ type: 'error', text: data.message || 'Login failed' });
      }
    } catch (error) {
      setMessage({
        type: 'error',
        text: 'Unable to reach the backend. Make sure it is running on port 8080.',
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <main className="page-center">
      <section className="brand-panel">
        <div className="brand-header">
          <div className="brand-logo">🚌</div>
          <h1>
            <span className="brand-purple">Transit</span>
            <span className="brand-blue">Nova</span>
          </h1>
        </div>
        <p className="account-title">Welcome Back</p>
        <p className="brand-copy">
          Sign in to view bus routes, check schedules, and find the fastest way to your destination.
        </p>
      </section>

      <section className="register-card">
        <form className="register-form" onSubmit={handleSubmit}>
          <label className="full-width-label">
            Email
            <input
              type="email"
              value={form.email}
              onChange={handleChange('email')}
              placeholder="e.g. john.doe@example.com"
              required
            />
          </label>

          <label className="full-width-label">
            Password
            <div className="password-input-wrapper">
              <input
                type={showPassword ? 'text' : 'password'}
                value={form.password}
                onChange={handleChange('password')}
                placeholder="Enter your password"
                required
              />
              <button
                type="button"
                className="password-toggle-btn"
                onClick={() => setShowPassword(!showPassword)}
                aria-label={showPassword ? 'Hide password' : 'Show password'}
              >
                {showPassword ? (
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
                    <path d="M17.94 17.94A10.94 10.94 0 0 1 12 20.5C7.05 20.5 2.73 17.28 1 12.5a10.94 10.94 0 0 1 1.85-2.87" />
                    <path d="M1 1l22 22" />
                    <path d="M9.53 9.53a3.5 3.5 0 0 0 4.95 4.95" />
                    <path d="M14.12 14.12A3.5 3.5 0 0 1 9.88 9.88" />
                  </svg>
                ) : (
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
                    <path d="M1 12.5C2.73 17.28 7.05 20.5 12 20.5s9.27-3.22 11-8c-1.73-4.78-6.05-8-11-8S2.73 7.72 1 12.5z" />
                    <circle cx="12" cy="12.5" r="3.5" />
                  </svg>
                )}
              </button>
            </div>
          </label>

          {message && (
            <div className={`message ${message.type === 'success' ? 'message-success' : 'message-error'}`}>
              {message.text}
            </div>
          )}

          <button className="primary-button" type="submit" disabled={isSubmitting}>
            {isSubmitting ? 'Signing in...' : 'Sign In'}
          </button>

          <p className="page-footer">
            Don&apos;t have an account?{' '}
            <button type="button" className="page-link" onClick={onNavigate}>
              Sign Up
            </button>
          </p>
        </form>
      </section>
    </main>
  );
}
