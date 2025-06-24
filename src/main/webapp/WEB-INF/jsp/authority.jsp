<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-check form-check-inline">
	<input class="form-check-input" type="checkbox" name="role"
		id="roleRead" value="salesregister"
		<c:if test="${account.salesRole}">checked</c:if>> <label
		class="form-check-label" for="roleRead">売上登録</label>
</div>
<div class="form-check form-check-inline">
	<input class="form-check-input" type="checkbox" name="role"
		id="roleUpdate" value="accountregister"
		<c:if test="${account.accountRole}">checked</c:if>> <label
		class="form-check-label" for="roleUpdate">アカウント登録</label>
</div>
