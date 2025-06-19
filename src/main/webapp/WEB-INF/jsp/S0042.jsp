<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="account" value="${requestScope.account}" />

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アカウント詳細編集</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error-message {
            color: red;
            font-size: 0.9em;
        }
        .form-error-space {
            min-height: 1.5em;
        }
    </style>
</head>
<body>
    <jsp:include page="nav.jsp" />

    <div class="container mt-5">
        <h1 class="mb-4">アカウント詳細編集</h1>
        <form action="S0043.html" method="POST">
            <!-- 修正済み: id → account_id -->
            <input type="hidden" name="id" value="${account.account_id}" />

            <!-- 氏名 -->
            <div class="mb-3 row">
                <label for="name" class="col-sm-2 col-form-label text-end">氏名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name"
                           value="${account.name}" placeholder="氏名を入力">
                    <div class="form-error-space">
                        <c:if test="${isSubmitted and not empty fieldErrors['name']}">
                            <span class="error-message">${fieldErrors['name']}</span>
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- メールアドレス -->
            <div class="mb-3 row">
                <label for="mail" class="col-sm-2 col-form-label text-end">メールアドレス</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="mail" name="mail"
                           value="${account.mail}" placeholder="メールアドレス">
                    <div class="form-error-space">
                        <c:if test="${isSubmitted and not empty fieldErrors['mail']}">
                            <span class="error-message">${fieldErrors['mail']}</span>
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- パスワード -->
            <div class="mb-3 row">
                <label for="password" class="col-sm-2 col-form-label text-end">パスワード</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password"
                           name="password" value="${account.password}" placeholder="パスワードを入力">
                    <div class="form-error-space">
                        <c:if test="${isSubmitted and not empty fieldErrors['password']}">
                            <span class="error-message">${fieldErrors['password']}</span>
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- パスワード確認 -->
            <div class="mb-3 row">
                <label class="col-sm-2 col-form-label text-end">パスワード（確認）</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="confirmPassword"
                           name="confirmPassword" value="${account.password}" placeholder="パスワード（確認）">
                    <div class="form-error-space">
                        <c:if test="${isSubmitted and not empty fieldErrors['confirmPassword']}">
                            <span class="error-message">${fieldErrors['confirmPassword']}</span>
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 権限 -->
            <div class="mb-3 row">
                <label class="col-sm-2 col-form-label text-end">権限</label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="role"
                               id="roleRead" value="0"
                               <c:if test="${hasSalesRole}">checked</c:if>>
                        <label class="form-check-label" for="roleRead">売上登録</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="role"
                               id="roleUpdate" value="update"
                               <c:if test="${hasAccountRole}">checked</c:if>>
                        <label class="form-check-label" for="roleUpdate">アカウント登録</label>
                    </div>
                </div>
            </div>

            <!-- ボタン -->
            <div class="row">
                <div class="offset-sm-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">✓ 更新</button>
                    <button type="submit" class="btn btn-secondary ms-2" onclick="history.back()">キャンセル</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
