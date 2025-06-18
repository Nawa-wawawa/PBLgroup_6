<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.accounts"%>
<%
accounts account = (accounts) request.getAttribute("account");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント詳細削除確認画面</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="container mt-5">
		<h1 class="mb-4">アカウント詳細削除確認</h1>

		<form action="S0044.html" method="POST">
			<input type="hidden" name="action" value="delete"> <input
				type="hidden" name="id" value="<%=account.getId()%>"> <input
				type="hidden" name="name" value="<%=account.getName()%>">
			<input type="hidden" name="mail" value="<%=account.getMail()%>">
			<input type="hidden" name="password"
				value="<%=account.getPassword()%>"> <input type="hidden"
				name="confirmPassword" value="<%=account.getPassword()%>">
			<%
			if ((account.getAuthority() & 1) != 0) {
			%>
			<input type="hidden" name="role" value="0">
			<%
			}
			%>
			<%
			if ((account.getAuthority() & 2) != 0) {
			%>
			<input type="hidden" name="role" value="update">
			<%
			}
			%>

			<!-- 表示項目（すべてdisabled） -->
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">氏名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control"
						value="<%=account.getName()%>" disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">メールアドレス</label>
				<div class="col-sm-10">
					<input type="text" class="form-control"
						value="<%=account.getMail()%>" disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">パスワード</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						value="<%=account.getPassword()%>" disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">パスワード（確認）</label>
				<div class="col-sm-10">
					<input type="password" class="form-control"
						value="<%=account.getPassword()%>" disabled>
				</div>
			</div>

			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end">権限</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" disabled
							<%=((account.getAuthority() & 1) != 0) ? "checked" : ""%>>
						<label class="form-check-label">売上登録</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" disabled
							<%=((account.getAuthority() & 2) != 0) ? "checked" : ""%>>
						<label class="form-check-label">アカウント登録</label>
					</div>
				</div>
			</div>

			<!-- ボタン -->
			<div class="row">
				<div class="offset-sm-2 col-sm-10">
					<button type="submit" class="btn btn-danger">×OK</button>
					<button type="button" class="btn btn-light ms-2"
						onclick="history.back()">キャンセル</button>
				</div>
			</div>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
