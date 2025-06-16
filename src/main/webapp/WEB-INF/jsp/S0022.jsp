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
				<!-- 左側のメニュー -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ダッシュボード</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上実績</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント検索</a></li>
				</ul>

				<!-- ログアウトだけ右端に -->
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="col-3 text-end mt-5">
		<h2>売上詳細表示</h2>
	</div>
	<div class="container">
		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">販売日</div>
			<div class="col-2">
				<c:out value="${picksale.sale_id}" />
			</div>
		</div>

		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">担当</div>
			<div class="col-4">
				<c:out value="${picksale.account_id}" />
			</div>
		</div>

		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">商品カテゴリー</div>
			<div class="col-4">
				<c:out value="${picksale.category_id}" />
			</div>
		</div>

		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">商品名</div>
			<div class="col-4">
				<c:out value="${picksale.trade_name}" />
			</div>
		</div>

		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">単価</div>
			<div class="col-2">
				<c:out value="${picksale.unit_price}" />
			</div>
		</div>

		<div class="row mb-3 align-items-center">
			<div class="col-2 text-end fw-bold">個数</div>
			<div class="col-2">
				<c:out value="${picksale.sale_number}" />
			</div>
		</div>

		<div class="row mb-3 align-items-start">
			<div class="col-2 text-end fw-bold">備考</div>
			<div class="col-4">
				<c:out value="${picksale.note}" />
			</div>
		</div>

		<form method="post" action="S0022.html">
			<div class="row">
				<div class="offset-4 col-5">
					<div class="d-flex gap-2">
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