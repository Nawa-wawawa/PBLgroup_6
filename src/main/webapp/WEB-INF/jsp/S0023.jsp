<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
    <meta charset="UTF-8">
    <title>売上詳細編集</title>
    <style>
        .text-danger {
            min-height: 1.8em;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <p class="navbar-brand">物品売上管理システム</p>
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

    <div class="col-2 text-end mt-5">
        <h2>売上詳細編集</h2>
    </div>

    <div class="container">
        <form method="post" action="S0023.html">

            <!-- 販売日 -->
            <div class="row mb-3">
                <div class="col-2 text-end">販売日</div>
                <div class="col-2">
                    <input type="date" name="sale_date" class="form-control"
                           value="${sale_date != null ? sale_date : picksale.sale_date}">
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_sale_date_required']}" />
                            <c:out value="${errors['error_sale_date_format']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 担当 -->
            <div class="row mb-3">
                <div class="col-2 text-end">担当</div>
                <div class="col-4">
                    <select name="staff" class="form-control">
                        <c:forEach var="account" items="${accountslist}">
                            <option value="${account.account_id}"
                                <c:if test="${account.account_id == (staff != null ? staff : picksale.account_id)}">selected</c:if>>
                                ${account.name}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_staff_required']}" />
                            <c:out value="${errors['error_staff_not_found']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 商品カテゴリ -->
            <div class="row mb-3">
                <div class="col-2 text-end">商品カテゴリー</div>
                <div class="col-4">
                    <select name="category" class="form-control">
                        <c:forEach var="cat" items="${categorylist}">
                            <option value="${cat.category_id}"
                                <c:choose>
                                    <c:when test="${not empty category}">
                                        <c:if test="${cat.category_id == category}">selected</c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${cat.category_id == picksale.category_id}">selected</c:if>
                                    </c:otherwise>
                                </c:choose>>
                                ${cat.category_name}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_category_required']}" />
                            <c:out value="${errors['error_category_not_found']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 商品名 -->
            <div class="row mb-3">
                <div class="col-2 text-end">商品名</div>
                <div class="col-4">
                    <input type="text" name="product_name" class="form-control"
                           value="${product_name != null ? product_name : picksale.trade_name}" autocomplete="off">
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_product_name_required']}" />
                            <c:out value="${errors['error_name']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 単価 -->
            <div class="row mb-3">
                <div class="col-2 text-end">単価</div>
                <div class="col-2">
                    <input type="number" name="unit_price" class="form-control"
                           value="${unit_price != null ? unit_price : picksale.unit_price}"
                           min="1" max="2147483647" step="1">
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_unit_price_format']}" />
                            <c:out value="${errors['error_price']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 個数 -->
            <div class="row mb-3">
                <div class="col-2 text-end">個数</div>
                <div class="col-2">
                    <input type="number" name="quantity" class="form-control"
                           value="${quantity != null ? quantity : picksale.sale_number}"
                           min="1" max="2147483647" step="1">
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_quantity_format']}" />
                            <c:out value="${errors['error_quantity']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- 備考 -->
            <div class="row mb-3">
                <div class="col-2 text-end">備考</div>
                <div class="col-4">
                    <textarea name="remarks" class="form-control" rows="3">${remarks != null ? remarks : picksale.note}</textarea>
                    <div class="text-danger">
                        <c:if test="${errors != null}">
                            <c:out value="${errors['error_remarks']}" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- ボタン -->
            <div class="row">
                <div class="offset-2 col-2 text-end">
                    <button type="submit" class="btn btn-primary" name="action" value="1">&check; 更新</button>
                    <button type="submit" class="btn btn-light" name="action" value="0">キャンセル</button>
                </div>
            </div>
        </form>
    </div>

    <script src="js/bootstrap.bundle.min.js" defer></script>
</body>
</html>
