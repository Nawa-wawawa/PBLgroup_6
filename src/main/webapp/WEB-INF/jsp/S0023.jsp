<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<meta charset="UTF-8">
<title>売上詳細編集</title>
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="col-2 text-end mt-5">
		<h2>売上詳細編集</h2>
	</div>

	<div class="container">
		<form method="post" action="S0023.html">
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>販売日</span>

					</span>
				</div>
				<div class="col-2">
					<input type="date" name="sale_date" class="form-control"
						value="${picksale.sale_date}" required>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>担当</span>
						<!--セレクトは変更必須-->
					</span>
				</div>
				<div class="col-4">
					<select name="staff" class="form-control" required>
						<c:forEach var="account" items="${accountslist}">
							<option value="${account.account_id}"
								${account.account_id == picksale.account_id ? "selected" : ""}>
								${account.name}</option>
						</c:forEach>
					</select>


				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>商品カテゴリー</span>
					</span>
				</div>
				<div class="col-4">
					<select name="category" class="form-control" required>
						<c:forEach var="category" items="${categorylist}">
							<option value="${category.category_id}"
							${category.category_id == picksale.category_id ? "selected" : ""}>
							${category.category_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>商品名</span>
					</span>
				</div>
				<div class="col-4">
					<input type="text" name="product_name" class="form-control"
						value="${picksale.trade_name}" required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>単価</span>
				</div>
				<div class="col-2">
					<input type="number" name="unit_price" class="form-control"
						value="${picksale.unit_price}" min="1" max="2147483647" step="1"
						required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>個数</span>

					</span>
				</div>
				<div class="col-2">
					<input type="number" name="quantity" class="form-control"
						value="${picksale.sale_number}" min="1" max="2147483647" step="1"
						required>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">備考</div>
				<div class="col-4">
					<textarea name="remarks" class="form-control" rows="3"
						value="${picksale.note}">${picksale.note}</textarea>
				</div>
			</div>
			<div class="row">
				<div class="offset-2 col-2 text-end">
					<button type="submit" class="btn btn-primary" name="action"
						value="1">&check;"更新</button>
					<button type="submit" class="btn btn-light" name="action" value="0">キャンセル</button>
				</div>
			</div>
		</form>
	</div>

	<script src="js/bootstrap.bundle.min.js" defer></script>
</body>
</html>