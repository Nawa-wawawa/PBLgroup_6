<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<meta charset="UTF-8">
<title>売上登録</title>
<style>
/* エラーメッセージ用スペースの高さを確保 */
.error-message {
	color: red;
	font-size: 0.875em;
	min-height: 1.2em;
	margin-top: 0.25rem;
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="col-2 text-end mt-5">
		<h2>売上登録</h2>
	</div>

	<div class="container">
		<form method="post" action="S0010.html" novalidate>
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>販売日</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-2">
					<input type="date" name="sale_date" class="form-control"
						value="${not empty sessionScope.salesData.sale_date 
						? sessionScope.salesData.sale_date 
             			: (not empty param['sale_date'] 
             			? param['sale_date'] 
             			: today)}"
						required>

					<div class="error-message">
						<c:out value="${errors['error_sale_date_required']}" default="" />
						<c:out value="${errors['error_sale_date_format']}" default="" />
					</div>
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
							<option value="${account.account_id}"
								<c:choose>
              <c:when test="${not empty sessionScope.salesData.account_id and sessionScope.salesData.account_id == account.account_id}">
                selected
              </c:when>
              <c:when test="${not empty param['staff'] and param['staff'] == account.account_id}">
                selected
              </c:when>
            </c:choose>>
								${fn:escapeXml(account.name)}</option>
						</c:forEach>
					</select>
					<div class="error-message">
						<c:out value="${errors['error_staff_required']}" default="" />
						<c:out value="${errors['error_staff_not_found']}" default="" />
					</div>
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
							<option value="${category.category_id}"
								<c:choose>
              <c:when test="${not empty sessionScope.salesData.category_id and sessionScope.salesData.category_id == category.category_id}">
                selected
              </c:when>
              <c:when test="${not empty param['category'] and param['category'] == category.category_id}">
                selected
              </c:when>
            </c:choose>>
								${fn:escapeXml(category.category_name)}</option>
						</c:forEach>
					</select>
					<div class="error-message">
						<c:out value="${errors['error_category_required']}" default="" />
						<c:out value="${errors['error_category_not_found']}" default="" />
					</div>
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
						placeholder="商品名" required
						value="${fn:escapeXml(not empty sessionScope.salesData.trade_name ? sessionScope.salesData.trade_name : (not empty param['product_name'] ? param['product_name'] : ''))}">
					<div class="error-message">
						<c:out value="${errors['error_product_name_required']}" default="" />
						<c:out value="${errors['error_name']}" default="" />
					</div>
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
						placeholder="単価" min="1" max="2147483647" step="1" required
						value="${fn:escapeXml(not empty sessionScope.salesData.unit_price ? sessionScope.salesData.unit_price : (not empty param['unit_price'] ? param['unit_price'] : ''))}">
					<div class="error-message">
						<c:out value="${errors['error_unit_price_required']}" default="" />
						<c:out value="${errors['error_price']}" default="" />
						<c:out value="${errors['error_unit_price_format']}" default="" />
					</div>
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
						placeholder="個数" min="1" max="2147483647" step="1" required
						value="${fn:escapeXml(not empty sessionScope.salesData.sale_number ? sessionScope.salesData.sale_number : (not empty param['quantity'] ? param['quantity'] : ''))}">
					<div class="error-message">
						<c:out value="${errors['error_quantity_required']}" default="" />
						<c:out value="${errors['error_quantity']}" default="" />
						<c:out value="${errors['error_quantity_format']}" default="" />
					</div>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">備考</div>
				<div class="col-4">
					<textarea name="remarks" class="form-control" rows="3"
						placeholder="特記事項などがあれば入力">${fn:escapeXml(not empty sessionScope.salesData.note ? sessionScope.salesData.note : (not empty param['remarks'] ? param['remarks'] : ''))}</textarea>
					<div class="error-message">
						<c:out value="${errors['error_remarks']}" default="" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="offset-2 col-2 text-end">
					<button type="submit" class="btn btn-primary">登録</button>
				</div>
			</div>
		</form>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"
		defer></script>
</body>
</html>
