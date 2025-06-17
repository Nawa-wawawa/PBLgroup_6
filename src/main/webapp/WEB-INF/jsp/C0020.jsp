<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ｜物品売上管理システム</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand">物品売上管理システム</span>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="C0020Servlet">ダッシュボード</a>
					</li>

					<c:if test="${canSales}">
						<li class="nav-item"><a class="nav-link" href="S0010Servlet">売上登録</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="S0020Servlet">売上実績</a>
						</li>
					</c:if>

					<c:if test="${canAccount}">
						<li class="nav-item"><a class="nav-link" href="S0030Servlet">アカウント登録</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="S0040Servlet">アカウント検索</a>
						</li>
					</c:if>
				</ul>

				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="LogoutServlet">ログアウト</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-4">
		<h2>ようこそ、${sessionScope.user.mail}さん！</h2>
		<p>以下の機能が利用可能です：</p>
		<ul>
			<c:if test="${canSales}">
				<li>売上登録・売上実績</li>
			</c:if>
			<c:if test="${canAccount}">
				<li>アカウント登録・アカウント検索</li>
			</c:if>
		</ul>
	</div>
</body>
</html>
