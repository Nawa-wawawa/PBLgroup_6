<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索結果</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand">物品売上管理システム</span>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ダッシュボード</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上実績</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント検索</a></li>
				</ul>
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="ms-3 mt-5">
		<h2>売上検索結果表示</h2>
	</div>
	<div class="container text-left">

		<!-- 見出し -->
		<div class="row fw-bold border-bottom py-2">
			<div class="col">操作</div>
			<div class="col">No</div>
			<div class="col">販売日</div>
			<div class="col">担当</div>
			<div class="col-2">商品カテゴリー</div>
			<div class="col-3">商品名</div>
			<div class="col">単価</div>
			<div class="col">個数</div>
			<div class="col">小計</div>
		</div>

		<!-- データ行 -->
		<c:forEach var="sales" items="${sessionScope.saleslist}">
			<form method="post" action="S0021.html">
				<div class="row border-bottom py-2 align-items-center">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">&check; 詳細</button>
					</div>
					<div class="col-1">
						<input type="hidden" name="id" value="${sales.sale_id}">${sales.sale_id}
					</div>
					<div class="col-1">${sales.sale_date}</div>
					<div class="col-1">
						<c:set var="accountName" value="" />
						<c:forEach var="ac" items="${sessionScope.accountslist}">
							<c:if test="${ac.account_id == sales.account_id}">
								<c:set var="accountName" value="${ac.name}" />
								${ac.name}
							</c:if>
						</c:forEach>
						<input type="hidden" name="accountName" value="${accountName}" />
					</div>
					<div class="col-2">
						<c:set var="categoryName" value="" />
						<c:forEach var="ct" items="${sessionScope.categorylist}">
							<c:if test="${ct.category_id == sales.category_id}">
								<c:set var="categoryName" value="${ct.category_name}" />
								${ct.category_name}
							</c:if>
						</c:forEach>
						<input type="hidden" name="categoryName" value="${categoryName}" />
					</div>
					<div class="col-3">${sales.trade_name}</div>
					<div class="col">${sales.unit_price}</div>
					<div class="col">${sales.sale_number}</div>
					<div class="col">${sales.unit_price * sales.sale_number}</div>
				</div>
			</form>
		</c:forEach>
	</div>
	<!--		<div class="d-block d-md-none">-->
	<!--			<c:forEach var="sales" items="${sessionScope.saleslist}">-->
	<!--				<form method="post" action="S0021.html">-->
	<!--					 個別レコードブロック -->
	<!--					<div class="border rounded mb-3 p-2 bg-light">-->
	<!--						 1段目 -->
	<!--						<div class="row">-->
	<!--							<div class="col-3 fw-bold">-->
	<!--								<button type="submit"-->
	<!--									class="btn btn-primary d-flex align-items-center">-->
	<!--									<span class="text-white" style="font-weight: bold;">&check;</span>-->
	<!--									詳細-->
	<!--								</button>-->
	<!--							</div>-->
	<!--							<div class="col-3">-->
	<!--								<input type="hidden" name="id" value="${sales.sale_id}">No:-->
	<!--								${sales.sale_id}-->
	<!--							</div>-->
	<!--							<div class="col-6">販売日: ${sales.sale_date}</div>-->
	<!--						</div>-->
	<!--						<div class="row mt-1">-->
	<!--							<div class="col-6">担当: ${sales.account_id}</div>-->
	<!--							<div class="col-6">カテゴリ: ${sales.category_id}</div>-->
	<!--						</div>-->

	<!--						 2段目 -->
	<!--						<div class="row mt-2 border-top pt-2">-->
	<!--							<div class="col-12 fw-bold">商品情報</div>-->
	<!--						</div>-->
	<!--						<div class="row">-->
	<!--							<div class="col-12">商品名: ${sales.trade_name}</div>-->
	<!--						</div>-->
	<!--						<div class="row">-->
	<!--							<div class="col-4">単価: ${sales.unit_price}</div>-->
	<!--							<div class="col-4">個数: ${sales.sale_number}</div>-->
	<!--							<div class="col-4">小計: ${sales.unit_price * sales.sale_number}</div>-->
	<!--						</div>-->
	<!--					</div>-->
	<!--				</form>-->
	<!--			</c:forEach>-->
	<!--		</div>-->

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"
		defer></script>
</body>
</html>