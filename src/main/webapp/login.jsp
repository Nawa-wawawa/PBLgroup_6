<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン｜物品売上管理システム</title>
</head>
<body class="d-flex justify-content-center min-vh-100">
	<div class="container p-5 text-center">
		<h1>物品売上管理システム</h1>
		<form action="LoginServlet" mathod="POST">
			<div class="row mb-3">
				<div class="row mb-3 justify-content-center">
					<div class="col-md-5">
						<!-- 形式未指定 -->
						メールアドレス <span class="badge bg-secondary mt-3">必須</span> <input
							type="email" class="form-control mt-3 mb-3" name="mail"
							placeholder="メールアドレス" >
						パスワード <span class="badge bg-secondary mt-3">必須</span> <input
							type="password" class="form-control mt-3 mb-3" name="password"
							placeholder="パスワード">
						<div class="d-grid col-12 mx-auto">
							<input type="submit" class="btn btn-primary m-2" value="ログイン">
						</div>
					</div>
				</div>
			</div>
		</form>
</body>
</html>
