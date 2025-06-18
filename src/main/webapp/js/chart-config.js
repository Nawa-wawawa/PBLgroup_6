window.addEventListener('DOMContentLoaded', () => {
	const labels = [...categoryLabels];
	const data = [...categoryData];

	const grayColors = [
		'rgba(50, 50, 50, 0.9)',  // 濃いグレー
		'rgba(80, 80, 80, 0.7)',
		'rgba(120, 120, 120, 0.7)',
		'rgba(160, 160, 160, 0.7)',
		'rgba(200, 200, 200, 0.7)'
	];

	const ctxPie = document.getElementById('categoryPieChart').getContext('2d');
	new Chart(ctxPie, {
		type: 'pie',
		data: {
			labels: labels,
			datasets: [{
				label: '売上割合',
				data: data,
				backgroundColor: grayColors
			}]
		},
		options: {
			responsive: true,
			maintainAspectRatio: true,
			plugins: {
				legend: {
					position: 'bottom',
					labels: {
						color: '#333',
						font: { size: 14 }
					}
				}
			}
		}
	});

	const ctxBar = document.getElementById('monthlyBarChart').getContext('2d');
	new Chart(ctxBar, {
		type: 'bar',
		data: {
			labels: labels,
			datasets: [{
				label: '月別売上',
				data: data,
				backgroundColor: 'rgba(80, 80, 80, 0.7)'  // 単色濃いグレー
			}]
		},
		options: {
			responsive: true,
			maintainAspectRatio: true,
			plugins: {
				legend: { display: false },
				tooltip: { enabled: true }
			},
			scales: {
				y: { beginAtZero: true }
			}
		}
	});
});

