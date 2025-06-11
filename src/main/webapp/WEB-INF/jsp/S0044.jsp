<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>アカウント詳細削除確認</title>
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
				<!-- 左側のメニュー -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ダッシュボード</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">売上実績</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント登録</a></li>
					<li class="nav-item"><a class="nav-link" href="#">アカウント検索</a></li>
				</ul>
 
				<!-- ログアウトだけ右端に -->
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="#">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
  <div class="container mt-5">
    <h1 class="mb-4">アカウント詳細削除確認</h1>
    <form>

      <!-- 氏名 -->
      <div class="mb-3 row">
        <label for="name" class="col-sm-2 col-form-label text-end">
          氏名
        </label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="name" placeholder="氏名を入力" disabled>
        </div>
      </div>

      <!-- メールアドレス -->
      <div class="mb-3 row">
        <label for="email" class="col-sm-2 col-form-label text-end">
          メールアドレス
        </label>
        <div class="col-sm-10">
          <input type="email" class="form-control" id="email" placeholder="メールアドレス" disabled>
        </div>
      </div>

      <!-- パスワード -->
      <div class="mb-3 row">
        <label for="password" class="col-sm-2 col-form-label text-end">
          パスワード
        </label>
        <div class="col-sm-10">
          <input type="password" class="form-control" id="password" placeholder="パスワードを入力" disabled>
        </div>
      </div>

      <!-- パスワード確認 -->
      <div class="mb-3 row">
        <label for="confirmPassword" class="col-sm-2 col-form-label text-end">
          パスワード（確認）
        </label>
        <div class="col-sm-10">
          <input type="password" class="form-control" id="confirmPassword" placeholder="パスワード（確認）" disabled>
        </div>
      </div>

      <!-- 権限 -->
      <div class="mb-3 row">
        <label class="col-sm-2 col-form-label text-end">
          権限
        </label>
        <div class="col-sm-10">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleNone" value="none" checked disabled>
            <label class="form-check-label" for="roleNone">権限なし</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleRead" value="read" disabled>
            <label class="form-check-label" for="roleRead">売上登録</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleUpdate" value="update" disabled>
            <label class="form-check-label" for="roleUpdate">アカウント登録</label>
          </div>
        </div>
      </div>

      <!-- 登録ボタン -->
      <div class="row">
  		<div class="offset-sm-2 col-sm-10">
    		<button type="submit" class="btn btn-danger">×OK</button>
  	  		<button type="button" class="btn btn-light ms-2" onclick="history.back()">キャンセル</button>
  		</div>
	</div>

    </form>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

