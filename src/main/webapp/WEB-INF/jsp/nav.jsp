<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
	<div class="container-fluid">
		<span class="navbar-brand">物品売上管理システム</span>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="C0020.html">ダッシュボード</a></li>

				<c:if test="${user.authority == 1 || user.authority == 3}">
					<li class="nav-item"><a class="nav-link" href="S0010.html">売上登録</a></li>
				</c:if>
					<li class="nav-item"><a class="nav-link" href="S0020.html">売上実績</a></li>

				<c:if test="${user.authority == 2 || user.authority == 3}">
					<li class="nav-item"><a class="nav-link" href="S0030.html">アカウント登録</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="S0040.html">アカウント検索</a></li>
			</ul>

			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item d-flex align-items-center px-2 text-nowrap">
					<span class="navbar-text"><h4>
							<c:out value="${user.name}" />
						</h4></span>
				</li>
				<li class="nav-item">
					<h4>
						<a class="nav-link" href="Logout.html">ログアウト</a>
					</h4>
				</li>
			</ul>
		</div>
	</div>
</nav>
