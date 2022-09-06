<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Member List</title>
</head>
<body>
    <h2>Member List</h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>id</th>
                    <th>nickname</th>
                    <th>joinDate</th>
                    <th>grade</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty lists}">
                    <tr>
                        <td colspan="5" align="center">해당하는 회원이 존재하지 않습니다.</td>
                    </tr>
                </c:if>
                <c:forEach var="dto" items="${lists}">
                    <tr>
                        <td>${dto.memberNum }</td>
                        <td>
                            <a href="/admin/board-list?categoryName=QnA">${dto.id }</a>
                        </td>
                        <td>${dto.nickname }</td>
                        <td>${dto.joinDate }</td>
                        <td>${dto.grade }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
