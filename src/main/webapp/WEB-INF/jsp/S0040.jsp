<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索条件入力</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="nav.jsp" />

	<div class="container mt-5">
		<h1 class="mb-4">アカウント検索条件入力</h1>
		<form>

			<!-- 氏名 -->
			<div class="mb-3 row">
				<label for="name" class="col-sm-2 col-form-label text-end">
					氏名 <span class="badge rounded-pill text-bg-secondary">部分一致</span>
				</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" placeholder="氏名">
				</div>
			</div>

			<!-- メールアドレス -->
			<div class="mb-3 row">
				<label for="email" class="col-sm-2 col-form-label text-end">
					メールアドレス </label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email"
						placeholder="メールアドレス">
				</div>
			</div>

			<!-- 権限 -->
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label text-end"> 権限 </label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="role"
							id="roleNone" value="none" checked> <label
							class="form-check-label" for="roleNone">権限なし</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="role"
							id="roleRead" value="read"> <label
							class="form-check-label" for="roleRead">売上登録</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="role"
							id="roleUpdate" value="update"> <label
							class="form-check-label" for="roleUpdate">アカウント登録</label>
					</div>
				</div>
			</div>

			<!-- 検索＆クリアボタン -->
			<div class="row">
				<div class="offset-sm-2 col-sm-10">
					<button type="submit" class="btn btn-primary">🔍検索</button>
					<button type="button" class="btn btn-light ms-2"
						onclick="history.back()">クリア</button>
				</div>
			</div>

		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>