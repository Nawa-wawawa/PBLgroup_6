<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規アカウント登録確認画面</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="container mt-5">
		<!-- エラー表示エリア（ここに常にスペースを確保） -->
		<jsp:include page="error.jsp" />

		<h1 class="mb-4">アカウントを登録してもよろしいですか？</h1>

		<form action="S0031.html" method="POST">
			<!-- 隠し入力（登録用） -->

			<input type="hidden" name="name" value="${account.name}"> <input
				type="hidden" name="mail" value="${account.mail}"> <input
				type="hidden" name="password" value="${account.password}"> <input
				type="hidden" name="confirmPassword" value="${account.password}">

			<c:if test="${canRegisterSales}">
				<input type="hidden" name="role" value="salesregister">
			</c:if>
			<c:if test="${canRegisterAccounts}">
				<input type="hidden" name="role" value="accountregister">
			</c:if>

			<!-- 表示項目（すべてdisabled） -->
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">氏名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${account.name}"
						disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">メールアドレス</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${account.mail}"
						disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">パスワード</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						value="${account.password}" disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">パスワード（確認）</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						value="${account.password}" disabled>
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
					<button type="submit" class="btn btn-primary">OK</button>
					<button type="button" class="btn btn-light ms-2"
						onclick="history.back()">キャンセル</button>
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