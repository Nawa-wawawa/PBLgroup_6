<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント検索結果</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container-sm mt-5">
		<h1 class="mb-4">アカウント検索結果表示</h1>
		<c:choose>
			<c:when test="${not empty accountList}">
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
						<c:forEach var="item" items="${accountList}">
							<tr>
								<form action="S0041.html" method="POST">
									<td>
										<div class="d-flex">
											<c:if test="${user.authority == 2 || user.authority == 3}">
												<input type="hidden" name="accountId"
													value="${item.account_id}">
												<button type="submit" class="btn btn-primary btn-sm me-2"
													name="action" value="edit">✓編集</button>

												<input type="hidden" name="accountId"
													value="${item.account_id}">
												<button type="submit" class="btn btn-danger btn-sm"
													name="action" value="delete">×削除</button>
											</c:if>
											<c:if test="${!(user.authority == 2 || user.authority == 3)}">
												<span class="text-muted">閲覧専用</span>
											</c:if>
										</div>
									</td>
									<td><input type="hidden" name="id"
										value="${item.account_id}">${item.account_id}</td>
									<td><input type="hidden" name="name" value="${item.name}">
										${item.name}</td>
									<td><input type="hidden" name="mail" value="${item.mail}">${item.mail}</td>
									<td><input type="hidden" name="authority"
										value="${item.authority}"> <c:choose>
											<c:when test="${item.authority == 0}">権限なし</c:when>
											<c:when test="${item.authority == 1}">売上登録</c:when>
											<c:when test="${item.authority == 2}">アカウント登録</c:when>
											<c:when test="${item.authority == 3}">売上／アカウント登録</c:when>
										</c:choose></td>
								</form>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:when test="${error3 != null}">
				<center>
					<p class="lead">${error3}</p>
				</center>
			</c:when>
		</c:choose>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
