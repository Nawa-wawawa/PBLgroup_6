<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<meta charset="UTF-8">
<title>売上詳細編集確認</title>
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="col-3 text-end mt-5">
		<h2>売上詳細編集確認</h2>
	</div>
	<div class="container">
		<form method="post" action="S0024.html">
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>販売日</span>

					</span>
				</div>
				<div class="col-2">
					<input type="date" name="sale_date" class="form-control"
						value="${sessionScope.picksale.sale_date}" disabled>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>担当</span>

					</span>
				</div>
				<div class="col-4">
					<select name="staff" class="form-control" disabled>
						<option>${accountName}</option>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>商品カテゴリー</span>
					</span>
				</div>
				<div class="col-4">

					<select name="category" class="form-control" disabled>
						<option>${categoryName}</option>
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
						placeholder="${sessionScope.picksale.trade_name}" disabled>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>単価</span>
				</div>
				<div class="col-2">
					<input type="number" name="unit_price" class="form-control"
						placeholder="${sessionScope.picksale.unit_price}" disabled>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>個数</span>

					</span>
				</div>
				<div class="col-2">
					<input type="number" name="quantity" class="form-control"
						placeholder="${sessionScope.picksale.sale_number}" disabled>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">備考</div>
				<div class="col-4">
					<textarea name="remarks" class="form-control" rows="3"
						placeholder="${sessionScope.picksale.note}" disabled></textarea>
				</div>
			</div>
			<div class="row">
				<div class="offset-2 col-2 text-end">
					<button type="submit" class="btn btn-primary" name="action"
						value="1">OK</button>
					<a href="S0023.html" class="btn btn-light" role="button">キャンセル</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>