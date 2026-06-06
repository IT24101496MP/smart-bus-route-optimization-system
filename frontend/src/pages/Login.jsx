export default function Login({ onNavigate }) {
  return (
    <main className="page-center">
      <section className="brand-panel">
        <div className="brand-logo">
          <span>🚌</span>
        </div>
        <div>
          <h1>TransitNova</h1>
          <p>Sign in to continue monitoring routes and receiving recommendations.</p>
        </div>
      </section>

      <section className="register-card">
        <div className="card-header">
          <div>
            <p className="eyebrow">Welcome back</p>
            <h2>Login page coming soon</h2>
          </div>
        </div>

        <div className="register-form login-placeholder">
          <p>We are preparing the login experience. Use the button below to return to registration.</p>
          <button className="secondary-button" type="button" onClick={onNavigate}>
            Create account
          </button>
        </div>
      </section>
    </main>
  );
}
