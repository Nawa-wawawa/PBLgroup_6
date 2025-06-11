<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Account" %>
<%
    // 確認画面に渡されたAccountオブジェクトを取得
    Account account = (Account)request.getAttribute("account");
    if(account == null){
        response.sendRedirect("S0030.jsp"); // 入力画面に戻す
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規アカウント登録確認画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
	
  <div class="container mt-5">
    <h1 class="mb-4">アカウントを登録してもよろしいですか？</h1>
    <form action="S0031_2Servlet" method="POST">
      <!-- 氏名 -->
      <div class="mb-3 row">
        <label for="name" class="col-sm-2 col-form-label text-end">
          氏名 
        </label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="name" name="name" value="<%=account.getName()%>" disabled>
        </div>
      </div>

      <!-- メールアドレス -->
      <div class="mb-3 row">
        <label for="email" class="col-sm-2 col-form-label text-end">
          メールアドレス 
        </label>
        <div class="col-sm-10">
          <input type="email" class="form-control" id="email" name="mail" value="<%=account.getMail()%>" disabled>
        </div>
      </div>

      <!-- パスワード -->
      <div class="mb-3 row">
        <label for="password" class="col-sm-2 col-form-label text-end">
          パスワード 
        </label>
        <div class="col-sm-10">
          <input type="password" class="form-control" id="password" name="password" value="<%=account.getPassword()%>" disabled>
        </div>
      </div>

      <!-- パスワード確認 -->
      <div class="mb-3 row">
        <label for="confirmPassword" class="col-sm-2 col-form-label text-end">
          パスワード（確認） 
        </label>
        <div class="col-sm-10">
          <input type="password" class="form-control" id="confirmPassword" value="<%=account.getPassword()%>" disabled>
        </div>
      </div>

      <!-- 権限 -->
      <div class="mb-3 row">
        <label class="col-sm-2 col-form-label text-end">
          権限 
        </label>
        <div class="col-sm-10">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="authority" id="roleNone" value="0" <%= (account.getAuthority() == 0) ? "checked" : "" %> disabled>
            <label class="form-check-label" for="roleNone">権限なし</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="authority" id="roleRead" value="1" <%= ((account.getAuthority() & 1) == 1) ? "checked" : "" %> disabled>
            <label class="form-check-label" for="roleRead">参照</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="authority" id="roleUpdate" value="2" <%= ((account.getAuthority() & 2) == 2) ? "checked" : "" %> disabled>
            <label class="form-check-label" for="roleUpdate">更新</label>
          </div>
        </div>
      </div>

      <!-- ボタン配置（OK & キャンセル） -->
	  <div class="row">
  		<div class="offset-sm-2 col-sm-10">
    		<button type="submit" class="btn btn-primary">OK</button>
  	  		<button type="button" class="btn btn-light ms-2" onclick="history.back()">キャンセル</button>
  		</div>
	  </div>
    </form>
 	</div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
