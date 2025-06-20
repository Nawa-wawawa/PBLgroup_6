window.addEventListener('DOMContentLoaded', () => {
  const ctx = document.getElementById('monthlyBarChart').getContext('2d');

  const monthLabels = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];

  // monthlyCategoryData は JSP で以下の形式で出力されている前提
  // const monthlyCategoryData = {
  //   "食品": [1000, 2000, ..., 12個],
  //   "衣類": [...],
  //   ...
  // };

  const colors = [
    '#007bff', '#28a745', '#ffc107', '#dc3545', '#6f42c1',
    '#17a2b8', '#fd7e14', '#20c997', '#6610f2', '#e83e8c'
  ];

  const datasets = Object.keys(monthlyCategoryData).map((category, index) => ({
    label: category,
    data: monthlyCategoryData[category],
    backgroundColor: colors[index % colors.length]
  }));

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: monthLabels,
      datasets: datasets
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'top'
        },
        title: {
          display: true,
          text: '月別・カテゴリ別売上（積み上げ棒グラフ）'
        }
      },
      scales: {
        x: {
          stacked: true,
          title: {
            display: true,
            text: '月'
          }
        },
        y: {
          stacked: true,
          beginAtZero: true,
          title: {
            display: true,
            text: '売上（円）'
          }
        }
      }
    }
  });
});
