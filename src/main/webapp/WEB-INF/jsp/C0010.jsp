<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form action="C0010Servlet" method="POST">
			<div class="row mb-3 justify-content-center">
				<div class="col-md-5 mb-2 text-start">
					<label for="mail">メールアドレス <span class="badge bg-secondary m-2">必須</span></label> 
					<input type="mail" class="form-control mb-4" name="mail" placeholder="xxx@xxx.com" required> 
					<label for="password">パスワード <span class="badge bg-secondary m-2">必須</span></label> 
					<input type="password" class="form-control mb-4" name="password" placeholder="8文字以上の半角英数字" required>
					<div class="d-grid col-12 mx-auto">
						<input type="submit" class="btn btn-primary m-1" value="ログイン">
					</div>				
				</div>
			</div>
		</form>
</body>
</html>
