<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<title>売上詳細表示</title>
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="col-3 text-end mt-5">
		<h2>売上詳細表示</h2>
	</div>
	<div class="container mt-4">
		<!-- 販売日 -->
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">販売日</div>
			<div class="col-4">
				<c:out value="${picksale.sale_date}" />
			</div>
		</div>

		<!-- 担当 -->
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">担当</div>
			<div class="col-4">${aName}</div>
		</div>

		<!-- 商品カテゴリー -->
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">商品カテゴリー</div>
			<div class="col-4">${cName}</div>
		</div>

		<!-- 商品名 -->
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">商品名</div>
			<div class="col-4">
				<c:out value="${picksale.trade_name}" />
			</div>
		</div>

		<!-- 単価 -->
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">単価</div>
			<div class="col-2">
				<c:out value="${picksale.unit_price}" />
			</div>
		</div>

		<!-- 個数 -->
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">個数</div>
			<div class="col-2">
				<c:out value="${picksale.sale_number}" />
			</div>
		</div>

		<!-- 備考 -->
		<div class="row mb-4 align-items-start">
			<div class="col-2 text-end fw-bold">備考</div>
			<div class="col-6">
				<c:out value="${picksale.note}" />
			</div>
		</div>

		<!-- ボタン -->
		<form method="post" action="S0022.html">
			<div class="row justify-content-center">
				<div class="col-auto">
					<div class="d-flex gap-3">
						<button type="submit" name="action" value="2"
							class="btn btn-primary">編集</button>
						<button type="submit" name="action" value="1"
							class="btn btn-danger">削除</button>
						<button type="submit" name="action" value="0"
							class="btn btn-light">キャンセル</button>
					</div>
				</div>
			</div>
		</form>
	</div>



	<script src="js/bootstrap.bundle.min.js" defer></script>
</body>
</html>