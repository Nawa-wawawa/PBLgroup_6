<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索結果</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <span class="navbar-brand">物品売上管理システム</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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

<div class="container-sm mt-5">
    <h1 class="mb-4">アカウント検索結果表示</h1>
    <form>
            <c:choose>
                <c:when test="${not empty todo}">
                    <table class="table only-horizontal-lines">
                        <thead>
                            <tr>
                                <th>操作</th>
                                <th>No</th>
                                <th>氏名</th>
                                <th>メールアドレス</th>
                                <th>権限</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${todo}">
                                <tr style="cursor:pointer;" onclick="location.href='DetailtaskServlet?id=${item.id}'">
                                    <td>
                                        <div class="d-flex">
                                            <button type="submit" class="btn btn-primary btn-sm me-2">✓編集</button>
                                           <button type="submit" class="btn btn-danger btn-sm">×削除</button>
                                        </div>
                                    </td>
                                    <td>1</td>
                                    <td>イチロー</td>
                                    <td>ichiro@sak.com</td>
                                    <td>アカウント登録</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
            </c:choose>
    </form>
</div>
</body>
</html>
