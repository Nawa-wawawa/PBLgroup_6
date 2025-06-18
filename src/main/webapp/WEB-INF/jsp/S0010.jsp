<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<meta charset="UTF-8">
<title>売上登録</title>
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="col-2 text-end mt-5">
		<h2>売上登録</h2>
	</div>

	<div class="container">
		<form method="post" action="S0010.html">
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>販売日</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-2">
					<input type="date" name="sale_date" class="form-control"
						value="${today}" required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>担当</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-4">
					<select name="staff" class="form-control" required>
						<option value="">担当者を選択してください</option>
						<c:forEach var="account" items="${accountslist}">
							<option value="${account.account_id}">${account.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>商品カテゴリー</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-4">
					<select name="category" class="form-control" required>
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
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-4">
					<input type="text" name="product_name" class="form-control"
						placeholder="商品名" required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>単価</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-2">
					<input type="number" name="unit_price" class="form-control"
						placeholder="単価" min="1" max="2147483647"step="1" required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>個数</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-2">
					<input type="number" name="quantity" class="form-control"
						placeholder="個数" min="1" max="2147483647"step="1" required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">備考</div>
				<div class="col-4">
					<textarea name="remarks" class="form-control" rows="3"
						placeholder="特記事項などがあれば入力"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="offset-2 col-2 text-end">
					<button type="submit" class="btn btn-primary">登録</button>
				</div>
			</div>
		</form>
	</div>

	<c:if test="${not empty errors}">
		<div class="modal fade" id="Modal" tabindex="-1"data-bs-backdrop="static" data-bs-keyboard="false" 
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">入力エラー</h1>
					</div>
					<div class="modal-body">
						<c:forEach var="entry" items="${errors}">
							<div>${entry.value}</div>
							<!-- ← ここを修正 -->
						</c:forEach>
					</div>
					<div class="modal-footer">
						<a class="btn btn-primary" href="S0010.html">分かりました。</a>

					</div>
				</div>
			</div>
		</div>

		<script>
			document.addEventListener('DOMContentLoaded', function() {
				var myModal = new bootstrap.Modal(document
						.getElementById('Modal'));
				myModal.show();
			});
		</script>
	</c:if>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"
		defer></script>
</body>
</html>