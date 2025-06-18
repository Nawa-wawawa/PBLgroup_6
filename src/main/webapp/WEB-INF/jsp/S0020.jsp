<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<title>売上検索条件入力</title>
</head>
<body>
	<jsp:include page="nav.jsp" />


	<div class="col-3 text-end mt-5">
		<h2>売上検索条件入力</h2>
	</div>
	<div class="container">
		<form method="post" action="S0020.html">
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1">販売日 </span>
				</div>
				<div class="col-4">
					<div class="d-flex align-items-center">
						<input type="date" name="start_date" class="form-control me-2">
						<span class="me-2">～</span> <input type="date" name="end_date"
							class="form-control">
					</div>
				</div>

			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1">担当</span>

				</div>
				<div class="col-4">
					<select name="staff" class="form-control">
						<option  value="">担当者を選択してください</option>
						<c:forEach var="account" items="${accountslist}">
							<option value="${account.account_id}">${account.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1">商品カテゴリー</span>

				</div>
				<div class="col-4">
					<select name="category" class="form-control">
						<option value="">選択してください</option>
						<c:forEach var="category" items="${categorylist}">
							<option value="${category.category_id}">${category.category_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>商品名</span>
						<span class="badge rounded-pill text-bg-secondary">部分一致</span>
					</span>
				</div>
				<div class="col-4">
					<input type="text" name="product_name" class="form-control"
						placeholder="商品名を入力">
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>備考</span>
						<span class="badge rounded-pill text-bg-secondary">部分一致</span>
					</span>
				</div>
				<div class="col-4">
					<textarea name="remarks" class="form-control" rows="3"
						placeholder="特記事項などがあれば入力"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="offset-2 col-2 text-end">
					<button type="submit" class="btn btn-primary">検索</button>
					<button type="button" class="btn btn-light">クリア</button>
				</div>
			</div>
		</form>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"
		defer></script>
</body>
</html>