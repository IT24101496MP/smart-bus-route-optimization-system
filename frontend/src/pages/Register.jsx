import { useMemo, useState } from 'react';

const initialState = {
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  confirmPassword: '',
};

export default function Register({ onNavigate }) {
  const [form, setForm] = useState(initialState);
  const [message, setMessage] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const requirements = useMemo(
    () => [
      { label: 'At least 8 characters', valid: form.password.length >= 8 },
      { label: 'One uppercase letter', valid: /[A-Z]/.test(form.password) },
      { label: 'One lowercase letter', valid: /[a-z]/.test(form.password) },
      { label: 'One number', valid: /[0-9]/.test(form.password) },
      { label: 'One symbol', valid: /[^A-Za-z0-9]/.test(form.password) },
    ],
    [form.password]
  );

  const allRequirementsMet = requirements.every((item) => item.valid);
  const passwordsMatch = form.password === form.confirmPassword && form.confirmPassword.length > 0;

  const handleChange = (field) => (event) => {
    setForm((prev) => ({ ...prev, [field]: event.target.value }));
    setMessage(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage(null);

    if (!allRequirementsMet) {
      setMessage({ type: 'error', text: 'Please meet all password rules before continuing.' });
      return;
    }

    if (!passwordsMatch) {
      setMessage({ type: 'error', text: 'Passwords must match.' });
      return;
    }

    setIsSubmitting(true);

    try {
      const response = await fetch('http://localhost:8080/api/users/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      });

      const data = await response.json();
      if (response.ok) {
        setMessage({ type: 'success', text: data.message || 'Account created successfully.' });
        setForm(initialState);
      } else {
        setMessage({ type: 'error', text: data.message || 'Unable to create account.' });
      }
    } catch (err) {
      setMessage({ type: 'error', text: 'Unable to reach the backend. Make sure it is running on port 8080.' });
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
        <p className="account-title">Create Your Account</p>
        <p className="brand-copy">Sign up to access bus routes, track buses in real time, and receive route recommendations.</p>
      </section>

      <section className="register-card">
        <form className="register-form" onSubmit={handleSubmit}>
          <div className="form-row">
            <label>
              First name
              <input
                type="text"
                value={form.firstName}
                onChange={handleChange('firstName')}
                placeholder="e.g. John"
                required
              />
            </label>

            <label>
              Last name
              <input
                type="text"
                value={form.lastName}
                onChange={handleChange('lastName')}
                placeholder="e.g. Doe"
                required
              />
            </label>
          </div>

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
                className={`password-toggle-btn ${showPassword ? 'visible' : 'hidden'}`}
                onClick={() => setShowPassword(!showPassword)}
                aria-label="Toggle password visibility"
              >
                <span className="eye-icon" aria-hidden="true" />
              </button>
            </div>
          </label>

          <div className="requirements-panel">
            <p className="requirements-title">Password must contain:</p>
            <ul>
              {requirements.map((item) => (
                <li key={item.label} className={item.valid ? 'valid' : ''}>
                  <span>{item.valid ? '✓' : '○'}</span>
                  {item.label}
                </li>
              ))}
            </ul>
          </div>

          <label className="full-width-label">
            Confirm password
            <div className="password-input-wrapper">
              <input
                type={showConfirmPassword ? 'text' : 'password'}
                value={form.confirmPassword}
                onChange={handleChange('confirmPassword')}
                placeholder="e.g. John"
                required
              />
              <button
                type="button"
                className={`password-toggle-btn ${showConfirmPassword ? 'visible' : 'hidden'}`}
                onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                aria-label="Toggle confirm password visibility"
              >
                <span className="eye-icon" aria-hidden="true" />
              </button>
            </div>
          </label>

          {message && (
            <div className={`message ${message.type === 'success' ? 'message-success' : 'message-error'}`}>
              {message.text}
            </div>
          )}

          <button className="primary-button" type="submit" disabled={isSubmitting}>
            {isSubmitting ? 'Creating account...' : 'Create account'}
          </button>

          <p className="page-footer">
            Already have an account?{' '}
            <button type="button" className="page-link" onClick={onNavigate}>
              Sign in
            </button>
          </p>
        </form>
      </section>
    </main>
  );
}
