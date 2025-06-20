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
	<jsp:include page="nav.jsp" />

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
		<h2>
			<c:out value="${errors['error_select_not_found']}" default="" />
		</h2>
		<c:forEach var="sales" items="${sessionScope.saleslist}">
			<form method="post" action="S0021.html">
				<div class="row border-bottom py-2 align-items-center">
					<div class="col-auto">
						<c:if test="${user.authority == 1 || user.authority == 3}">
							<button type="submit" class="btn btn-primary">&check; 詳細</button>
						</c:if>
						<c:if test="${!(user.authority == 1 || user.authority == 3)}">
							<span class="text-muted">閲覧専用</span>
						</c:if>
					</div>
					<div class="col-1">
						<input type="hidden" name="id" value="${sales.sale_id}">${sales.sale_id}
					</div>
					<div class="col-1">${sales.sale_date}</div>
					<div class="col-1">
						<c:set var="accountName" value="" />

						<c:forEach var="ac" items="${accountslist}">

							<c:if test="${ac.account_id == sales.account_id}">
								<c:set var="accountName" value="${ac.name}" />
								${ac.name}
								</c:if>
						</c:forEach>
						<input type="hidden" name="accountName" value="${accountName}" />
					</div>
					<div class="col-2">
						<c:set var="categoryName" value="" />
						<c:forEach var="ct" items="${categorylist}">
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