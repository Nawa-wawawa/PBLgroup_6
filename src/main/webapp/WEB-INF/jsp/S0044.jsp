<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="hasSalesAuthority" value="${requestScope.hasSalesAuthority}" />
<c:set var="hasAccountAuthority"
	value="${requestScope.hasAccountAuthority}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント詳細削除確認画面</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="container mt-5">

		<jsp:include page="error.jsp" />

		<h1 class="mb-4">アカウント詳細削除確認</h1>

		<form action="S0044.html" method="POST">

			<!-- 表示項目（すべてdisabled） -->
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">氏名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${account.name}"
						disabled />
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">メールアドレス</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${account.mail}"
						disabled />
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">パスワード</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						value="${account.password}" disabled />
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">パスワード（確認）</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						value="${account.password}" disabled />
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">権限</label>
				<div class="col-sm-10">
					<jsp:include page="authority.jsp">
						<jsp:param name="disabled" value="true" />
					</jsp:include>

				</div>
			</div>

			<!-- ボタン -->
			<div class="row">
				<div class="offset-sm-2 col-sm-10">
					<button type="submit" class="btn btn-danger">×OK</button>
					<a href="${pageContext.request.contextPath}/S0041.html"
						class="btn btn-secondary ms-2">キャンセル</a>
				</div>
			</div>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/js/error.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"
		defer></script>
</body>
</html>