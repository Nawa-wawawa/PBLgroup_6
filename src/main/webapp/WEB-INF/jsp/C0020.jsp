<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>ダッシュボード</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dash.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="container my-4">
		<h2 class="mb-4 text-center">ダッシュボード</h2>

		<!-- ✅ 積み上げ棒グラフ -->
		<div class="card shadow-sm mb-5">
			<div class="card-body">
				<h5 class="card-title text-center mb-4">月別・カテゴリ別売上（積み上げ棒グラフ）</h5>
				<canvas id="monthlyBarChart" width="100" height="50"></canvas>
			</div>
		</div>

		<!-- 全体売上カード（年間 + 月間） -->
		<div class="row g-4 mb-4">
			<div class="col-12 mx-auto">
				<div class="card shadow border-0 rounded-3 py-4 px-5">
					<div class="card-body text-center">
						<h4 class="card-title mb-4 fw-bold text-dark">
							<i class="bi bi-bar-chart-fill me-2 fs-4"></i> 全体売上
						</h4>
						<div class="d-flex justify-content-around fs-5">
							<div>
								<div class="text-muted">年間</div>
								<div class="fw-bold text-dark">¥${yearlyTotal}</div>
							</div>
							<div>
								<div class="text-muted">月間</div>
								<div class="fw-bold text-primary">¥${monthlyTotal}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- あなたの売上カード（年間 + 月間） -->
		<div class="row g-4">
			<div class="col-12 mx-auto">
				<div class="card shadow border-0 rounded-3 py-4 px-5">
					<div class="card-body text-center">
						<h4 class="card-title mb-4 fw-bold text-success">
							<i class="bi bi-person-fill me-2 fs-4"></i> あなたの売上
						</h4>
						<div class="d-flex justify-content-around fs-5">
							<div>
								<div class="text-muted">年間</div>
								<div class="fw-bold text-success">¥${yearlyUser}</div>
							</div>
							<div>
								<div class="text-muted">月間</div>
								<div class="fw-bold text-success">¥${monthlyUser}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- Chart用データ -->
		<script>
    const monthlyCategoryData = {
      <c:forEach var="entry" items="${monthlyCategoryData}" varStatus="loop">
        "${entry.key}": [<c:forEach var="val" items="${entry.value}" varStatus="status">${val}<c:if test="${!status.last}">,</c:if></c:forEach>]<c:if test="${!loop.last}">,</c:if>
      </c:forEach>
    };
  </script>

		<!-- Chart.jsライブラリ -->
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<!-- 積み上げ棒グラフ用JS -->
		<script src="${pageContext.request.contextPath}/js/chart-config.js"></script>
		<!-- Bootstrap -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
