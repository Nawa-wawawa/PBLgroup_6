<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索結果</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
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

	<div class="ms-3 mt-5">
		<h2>売上検索結果表示</h2>
	</div>
	<div class="container text-left">

		<div class="d-none d-md-block">
			<div class="row">
				<div class="col">操作</div>
				<div class="col">No</div>
				<div class="col">販売日</div>
				<div class="col">担当</div>
				<div class="col-2">商品カテゴリー</div>
				<div class="col-4">商品名</div>
				<div class="col">単価</div>
				<div class="col">個数</div>
				<div class="col">小計</div>
			</div>

			<div>
				<c:forEach var="sales" items="${sessionScope.saleslist}">
					<div class="col">☑詳細</div>
					<div class="col">${sales.sale_id}</div>
					<div class="col">${sales.sale_date}</div>
					<div class="col">${sales.account_id}</div>
					<div class="col-2">${sales.category_id}</div>
					<div class="col-4">${sales.trade_name}</div>
					<div class="col">${sales.unit_price}</div>
					<div class="col">${sales.sale_number}</div>
					<div class="col">${sales.unit_price * sales.sale_number}</div>
				</c:forEach>
			</div>
		</div>



<!--		 スマホ用2段表示 -->
<!--		<div class="d-block d-md-none border-bottom py-2">-->
<!--			 1段目：前半項目 -->
<!--			<div class="row">-->
<!--				<div class="col">操作</div>-->
<!--				<div class="col">No</div>-->
<!--				<div class="col">販売日</div>-->
<!--				<div class="col">担当</div>-->
<!--				<div class="col-5">商品カテゴリー</div>-->
<!--			</div>-->
<!--			<c:forEach var="sales" items="${sessionScope.saleslist}">-->
<!--				<div class="row">-->
<!--					<div class="col">☑詳細</div>-->
<!--					<div class="col">${sales.sale_id}</div>-->
<!--					<div class="col">${sales.sale_date}</div>-->
<!--					<div class="col">${sales.account_id}</div>-->
<!--					<div class="col-2">${sales.category_id}</div>-->

<!--				</div>-->
<!--				 2段目：商品名・単価・個数・小計 -->
<!--				<div class="row mt-1">-->
<!--					<div class="col-6">商品名</div>-->
<!--					<div class="col">単価</div>-->
<!--					<div class="col">個数</div>-->
<!--					<div class="col">小計</div>-->
<!--				</div>-->
<!--				<div class="row">-->

<!--					<div class="col-4">${sales.trade_name}</div>-->
<!--					<div class="col">${sales.unit_price}</div>-->
<!--					<div class="col">${sales.sale_number}</div>-->
<!--					<div class="col">${sales.unit_price * sales.sale_number}</div>-->
<!--				</div>-->
<!--			</c:forEach>-->
<!--		</div>-->

	</div>
</body>
</html>