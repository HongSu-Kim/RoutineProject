<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<title>Board List</title>
</head>
<body>
<h2>List</h2>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
            <tr>
                <th>No.</th>
                <th>Title</th>
                <th>Created</th>
                <th>Hits</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty lists}">
                <tr>
                    <td colspan="4" align="center">글이 존재하지 않습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="dto" items="${lists}">
                <tr>
                    <td>${dto.boardId }</td>
                    <td>
                        <a href="/admin/board-detail?boardId=${dto.boardId }">${dto.boardTitle }</a>
                    </td>
                    <fmt:parseDate value="${dto.boardCreate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDateTime }" /></td>
                    <td>${dto.boardHits }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="hidden" name="category" value="${category }"/>
    <button class="btn btn-primary" onclick="location.href='/admin/board-write?category=${category }';">write</button>
</div>
</body>
</html>
