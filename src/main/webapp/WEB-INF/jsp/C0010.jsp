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
					<c:if test="${error != null}">
						<div class="modal fade" id="Modal" tabindex="-1" style="display:block;" aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel">ログインエラー</h1>
										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">${error}</div>
									
								</div>
							</div>
						</div>
						<script>
  						document.addEventListener('DOMContentLoaded', function() {
    					var myModal = new bootstrap.Modal(document.getElementById('Modal'));
   						 myModal.show();
 							 });
					</script>
					</c:if>					
				</div>
			</div>
		</form>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
				crossorigin="anonymous"></script>
</body>
</html>
