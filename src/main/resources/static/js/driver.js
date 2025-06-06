const form = document.getElementById('driverRegistrationForm');

form.addEventListener('submit', function(e) {
  e.preventDefault();

  const fullName = form.fullName.value.trim();
  const email = form.email.value.trim();
  const phone = form.phone.value.trim();
  const license = form.licenseNumber.value.trim();

  if (fullName.length < 3) {
    alert('Full name must be at least 3 characters long.');
    return;
  }

  if (!email.includes('@') || email.length < 5) {
    alert('Please enter a valid email address.');
    return;
  }

  const phoneRegex = /^[\d\s+\-]{6,15}$/;
  if (!phoneRegex.test(phone)) {
    alert('Please enter a valid phone number.');
    return;
  }

  if (license.length < 5) {
    alert("Driver's license number must be at least 5 characters.");
    return;
  }

  alert('Driver successfully registered!');
  form.reset();
});
