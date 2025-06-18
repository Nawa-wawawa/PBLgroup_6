<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>ダッシュボード</title>

<!-- Bootstrap CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<!-- カスタム CSS -->
<link href="${pageContext.request.contextPath}/css/dash.css"
	rel="stylesheet" />
</head>

<body>
	<jsp:include page="nav.jsp" />

	<div class="container my-4">
		<h2 class="mb-4 text-center">ダッシュボード</h2>
		<!-- 売上金額カード群 -->
		<div class="row g-4 mb-5">
			<div class="col-md-6">
				<div class="card shadow border-0 rounded-3 py-4 px-5">
					<div class="card-body text-center p-0">
						<h4 class="card-title mb-4 fw-bold text-dark">
							<i class="bi bi-bar-chart-line-fill me-3 fs-3 align-middle"></i>年間売上
						</h4>
						<div class="d-flex justify-content-center gap-5 fs-4">
							<div>
								<div class="text-muted fs-5">全体</div>
								<div class="fw-bold">¥${yearlyTotal}</div>
							</div>
							<div>
								<div class="text-muted fs-5">あなた</div>
								<div class="fw-bold text-success">¥${yearlyUser}</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card shadow border-0 rounded-3 py-4 px-5">
					<div class="card-body text-center p-0">
						<h4 class="card-title mb-4 fw-bold text-primary">
							<i class="bi bi-calendar me-3 fs-3 align-middle"></i>月間売上
						</h4>

						<div class="d-flex justify-content-center gap-5 fs-4">
							<div>
								<div class="text-muted fs-5">全体</div>
								<div class="fw-bold">¥${monthlyTotal}</div>
							</div>
							<div>
								<div class="text-muted fs-5">あなた</div>
								<div class="fw-bold text-success">¥${monthlyUser}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- 比較カード群 -->
		<div class="row g-3 mb-5">
			<div class="col-md-3">
				<div class="card border-success shadow-sm">
					<div class="card-body text-center">
						<h6 class="card-subtitle mb-2 text-muted">前年同期比（全体）</h6>
						<p
							class="${prevYearCompareTotal >= 0 ? 'positive' : 'negative'} fs-5">
							<i
								class="bi ${prevYearCompareTotal >= 0 ? 'bi-arrow-up-right' : 'bi-arrow-down-right'}"></i>
							${prevYearCompareTotal}%
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card border-success shadow-sm">
					<div class="card-body text-center">
						<h6 class="card-subtitle mb-2 text-muted">前年同期比（あなた）</h6>
						<p
							class="${prevYearCompareUser >= 0 ? 'positive' : 'negative'} fs-5">
							<i
								class="bi ${prevYearCompareUser >= 0 ? 'bi-arrow-up-right' : 'bi-arrow-down-right'}"></i>
							${prevYearCompareUser}%
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card border-primary shadow-sm">
					<div class="card-body text-center">
						<h6 class="card-subtitle mb-2 text-muted">前月比（全体）</h6>
						<p
							class="${prevMonthCompareTotal >= 0 ? 'positive' : 'negative'} fs-5">
							<i
								class="bi ${prevMonthCompareTotal >= 0 ? 'bi-arrow-up-right' : 'bi-arrow-down-right'}"></i>
							${prevMonthCompareTotal}%
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card border-primary shadow-sm">
					<div class="card-body text-center">
						<h6 class="card-subtitle mb-2 text-muted">前月比（あなた）</h6>
						<p
							class="${prevMonthCompareUser >= 0 ? 'positive' : 'negative'} fs-5">
							<i
								class="bi ${prevMonthCompareUser >= 0 ? 'bi-arrow-up-right' : 'bi-arrow-down-right'}"></i>
							${prevMonthCompareUser}%
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- グラフ -->
		<div class="row g-4">
			<div class="col-md-6">
				<div class="card shadow-sm">
					<div class="card-body">
						<h5 class="card-title text-center mb-4">カテゴリー別売上割合（円グラフ）</h5>
						<canvas id="categoryPieChart" width="400" height="400"></canvas>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card shadow-sm">
					<div class="card-body">
						<h5 class="card-title text-center mb-4">カテゴリー別売上（月別棒グラフ）</h5>
						<canvas id="monthlyBarChart" width="400" height="400"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Chart用データ出力 -->
	<script>
    const categoryLabels = [
      <c:forEach var="label" items="${categoryLabels}" varStatus="status">
        "${label}"<c:if test="${!status.last}">,</c:if>
      </c:forEach>
    ];
    const categoryData = [
      <c:forEach var="value" items="${categoryData}" varStatus="status">
        ${value}<c:if test="${!status.last}">,</c:if>
      </c:forEach>
    ];
  </script>

	<!-- JSライブラリ -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<!-- Chart描画JS -->
	<script src="${pageContext.request.contextPath}/js/chart-config.js"></script>
	<!-- Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>

