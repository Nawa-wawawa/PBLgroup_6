<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" />
<meta charset="UTF-8">
<title>売上登録確認</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<p class="navbar-brand">物品売上管理システム</p>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ダッシュボード</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上実績</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント検索</a></li>
				</ul>


				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ログアウト</a></li>
				</ul>

			</div>
		</div>
	</nav>

	<div class="col-3 text-end mt-5">
		<h2>登録確認確認</h2>
	</div>

	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/S0011Servlet" >
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>販売日</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-2">
					<input type="date" name="sale_date" class="form-control"
						value="${sessionScope.sale_date}"disabled>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1"> <span>担当</span>
						<span class="badge rounded-pill text-bg-secondary">必須</span>
					</span>
				</div>
				<div class="col-4">
					<select name="staff" class="form-control" disabled>
							<option value="${staff}">${accountname}</option>
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
					<select name="category" class="form-control" disabled>
							<option value="${category}">${categoryname}</option>	
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
						placeholder="商品名を入力" value="${product_name}" disabled>
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
						placeholder="円単位で入力" value="${unit_price}"disabled>
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
						placeholder="個数を入力" value="${quantity}"disabled>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-2 text-end">備考</div>
				<div class="col-4">
					<textarea name="remarks" class="form-control" rows="3"
						placeholder="特記事項などがあれば入力" value="${remarks}"disabled>${remarks}</textarea>
				</div>
			</div>
			<div class="row">
				<div class="offset-2 col-2 text-end">
					<button type="submit" class="btn btn-primary">登録</button>
					<a href="${pageContext.request.contextPath}/S0010Servlet" class="btn btn-light" role="button">キャンセル</a>

				</div>
			</div>
		</form>
	</div>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"
		defer></script>
</body>
</html>