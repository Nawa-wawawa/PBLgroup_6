<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <form action="S0040.html" method="POST" name="search">

      <!-- 氏名 -->
      <div class="mb-3 row">
        <label for="name" class="col-sm-2 col-form-label text-end">
          氏名 <span class="badge rounded-pill text-bg-secondary">部分一致</span>
        </label>
        <div class="col-sm-10">
          <input type="text" class="form-control 
          <c:if test='${not empty errors.error1}'>is-invalid</c:if> m-1"
           id="name" name="name" placeholder="氏名">
           <c:if test="${not empty errors.error1}">
           <div class="invalid-feedback">${errors.error1}</div>
           </c:if>
      </div>

      <!-- メールアドレス -->
      <div class="mb-3 row">
        <label for="email" class="col-sm-2 col-form-label text-end">
        メールアドレス
        </label>
        <div class="col-sm-10">
          <input type="email" class="form-control
          <c:if test='${not empty errors.error2}'>is-invalid</c:if> m-1"
           id="email" name="mail" placeholder="メールアドレス">
           <c:if test="${not empty errors.error2}">
           <div class="invalid-feedback">${errors.error2}</div>
           </c:if>
        </div>
      </div>

      <!-- 権限 -->
      <div class="mb-3 row">
        <label class="col-sm-2 col-form-label text-end">
          権限 
        </label>
        <div class="col-sm-10 mt-2">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="All" value="0" checked>
            <label class="form-check-label" for="All">すべて</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleNone" value="1">
            <label class="form-check-label" for="roleNone">権限なし</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleRead" value="2">
            <label class="form-check-label" for="roleRead">売上登録</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleUpdate" value="3">
            <label class="form-check-label" for="roleUpdate">アカウント登録</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="roleAll" value="4">
            <label class="form-check-label" for="roleAll">売上／アカウント登録</label>
          </div>
        </div>
      </div>

      <!-- 検索＆クリアボタン -->
      <div class="row">
  		<div class="offset-sm-2 col-sm-10">
    		<button type="submit" class="btn btn-primary">🔍検索</button>
  	  		<button type="reset" class="btn btn-light ms-2">クリア</button> 
  		</div>
	</div>
    </form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
>>>>>>> account
</body>
</html>