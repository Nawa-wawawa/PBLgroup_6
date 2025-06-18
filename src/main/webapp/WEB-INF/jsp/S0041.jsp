<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
								<tr style="cursor: pointer;"
									onclick="location.href='DetailtaskServlet?id=${item.id}'">
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
