document.addEventListener('DOMContentLoaded', function() {
	const error = document.getElementById('errorMessage');
	if (error) {
		setTimeout(() => {
			error.classList.add('animate__fadeOut');
		}, 5000);
	}
});
