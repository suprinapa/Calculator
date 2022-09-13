<%--
  Created by IntelliJ IDEA.
  User: supri
  Date: 9/5/2022
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>History</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <style>
        <%@include file="/WEB-INF/css/pagi.css" %>
    </style>
</head>
<body>
<div class="container">
    <br>
    <div>
        <a type="button" href="/calculator"><i class="fa fa-arrow-left" aria-hidden="true"> BACK </i></a>
        <form:form class = "deleteAll" method="post" action="/history/deleteAll">
        <button id="show-popup-btn" class="btn btn-danger" type="submit"><i class="fas fa-trash">DELETE ALL</i>
        </button>
        </form:form>
    </div>
    <%--    <div class="panel panel-primary">--%>
    <%--        <div class="panel-body">--%>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>SN</th>
            <th>Input Expression</th>
            <th>Output Expression</th>
            <th>Created Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allHistoryList}" var="his"  varStatus="myIndex">
            <tr>
                <td>${myIndex.index + 1 + ((currentPage - 1) * pageSize)}</td>
                <td>${his.inputExpression}</td>
                <td>${his.outputExpression}</td>
                <td><fmt:formatDate value="${his.createdDate}"
                                    pattern="dd/MM/yyyy"/></td>

                <td>
                    <form:form method="post" action="/history/${his.id}/${currentPage}">
                    <button id="show-popup-btn" class="btn btn-danger" type="submit" ><i class="fas fa-trash"></i>
                    </button>
                    <div id="popup-container">
                            <%--                                      <h4>Are you sure? </h4>--%>
                        </form:form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:if test="${currentPage > 1}">
            <li class="page-item"><a class="page-link" href="/history/${currentPage - 1}">Previous</a></li>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><span class="page-link">${i}<span class="sr-only">(current)</span></span></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/history/${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt totalPages}">
            <li class="page-item"><a class="page-link" href="/history/${currentPage + 1}">Next</a></li>
        </c:if>
    </ul>
</nav>
<script>
    $(function () {
        $("#show-popup-btn").click(function () {
            $("#popup-container").show();
        })
        $("#popup-close").click(function () {
            $("#popup-container").hide();
        })
    })
</script>
</body>
</html>
