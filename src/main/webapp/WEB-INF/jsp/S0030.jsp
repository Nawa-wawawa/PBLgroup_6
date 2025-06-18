<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>アカウント登録</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="container mt-5">
		<h1 class="mb-4">アカウント登録</h1>
		<form action="S0031.html" method="POST">

			<!-- 氏名 -->
			<div class="mb-3 row">
				<label for="name" class="col-sm-2 col-form-label text-end">
					氏名 <span class="badge bg-secondary">必須</span>
				</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name"
						value="${fn:escapeXml(name)}" placeholder="氏名を入力">
					<div class="form-error-space">
						<c:if test="${isSubmitted and not empty fieldErrors['name']}">
							<span class="error-message">${fieldErrors['name']}</span>
						</c:if>
					</div>
				</div>
			</div>

			<!-- メールアドレス -->
			<div class="mb-3 row">
				<label for="mail" class="col-sm-2 col-form-label text-end">
					メールアドレス <span class="badge bg-secondary">必須</span>
				</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="mail" name="mail"
						value="${fn:escapeXml(mail)}" placeholder="メールアドレスを入力">
					<div class="form-error-space">
						<c:if test="${isSubmitted and not empty fieldErrors['mail']}">
							<span class="error-message">${fieldErrors['mail']}</span>
						</c:if>
					</div>
				</div>
			</div>

			<!-- パスワード -->
			<div class="mb-3 row">
				<label for="password" class="col-sm-2 col-form-label text-end">
					パスワード <span class="badge bg-secondary">必須</span>
				</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="パスワードを入力">
					<div class="form-error-space">
						<c:if test="${isSubmitted and not empty fieldErrors['password']}">
							<span class="error-message">${fieldErrors['password']}</span>
						</c:if>
					</div>
				</div>
			</div>

			<!-- パスワード確認 -->
			<div class="mb-3 row">
				<label for="confirmPassword"
					class="col-sm-2 col-form-label text-end"> パスワード（確認） <span
					class="badge bg-secondary">必須</span>
				</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="confirmPassword"
						name="confirmPassword" placeholder="パスワード（確認）">
					<div class="form-error-space">
						<c:if
							test="${isSubmitted and not empty fieldErrors['confirmPassword']}">
							<span class="error-message">${fieldErrors['confirmPassword']}</span>
						</c:if>
					</div>
				</div>
			</div>

			<!-- 権限 -->
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end"> 権限 <span
					class="badge bg-secondary">必須</span>
				</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="role"
							value="0" id="roleRead"
							<c:if test="${roles != null && fn:contains(roles, '0')}">checked</c:if>>
						<label class="form-check-label" for="roleRead">売上登録</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="role"
							value="update" id="roleUpdate"
							<c:if test="${roles != null && fn:contains(roles, 'update')}">checked</c:if>>
						<label class="form-check-label" for="roleUpdate">アカウント登録</label>
					</div>
				</div>
			</div>

			<!-- 登録ボタン -->
			<div class="row">
				<div class="offset-sm-2 col-sm-10">
					<button type="submit" class="btn btn-primary">登録</button>
				</div>
			</div>

		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>