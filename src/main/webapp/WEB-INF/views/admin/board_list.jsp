<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
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
                    <td>${dto.boardNum }</td>
                    <td>
                        <a href="/admin/board-detail?boardNum=${dto.boardNum }">${dto.boardTitle }</a>
                    </td>
                    <td>${dto.boardCreate }</td>
                    <td>${dto.boardHits }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary" onclick="location.href='/admin/board-write?categoryName=${categoryName }';">write</button>
</div>
</body>
</head>
