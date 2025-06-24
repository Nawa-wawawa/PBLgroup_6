<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- エラー表示エリア（ここに常にスペースを確保） -->
<div class="mb-3" style="height: 3em;">
	<c:if test="${error != null}">
		<div id="errorMessage"
			class="alert alert-danger text-dark p-2 m-0 animate__animated"
			role="alert">${error}</div>
	</c:if>
</div>