<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<title>売上検索条件入力</title>
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
		<h2>売上検索条件入力</h2>
	</div>
	<div class="container">
		<form method="post" action="">
			<div class="row mb-3">
				<div class="col-2 text-end">
					<span class="d-inline-flex align-items-center gap-1">販売日 </span>
				</div>
				<div class="col-4">
					<div class="d-flex align-items-center">
						<input type="text" name="start_date" class="form-control me-2">
						<span class="me-2">～</span> <input type="text" name="end_date"
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
						<option value="">担当者を選択してください</option>
						<option value="">名和</option>
						<option value="">相馬</option>
						<option value="">鈴木</option>
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
						<option value="食品">食品</option>
						<option value="日用品">日用品</option>
						<option value="家電">家電</option>
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

	<script src="js/bootstrap.bundle.min.js" defer></script>
</body>
</html>