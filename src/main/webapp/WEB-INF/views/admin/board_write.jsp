<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
<body>
<div class="col-md-8 order-md-1">
    <h4 class="mb-3">Board Write</h4>
    <form:form class="needs-validation" action="" method="post">
        <div class="row">
            <!-- idx -->
            <div class="col-md-6 mb-3">
                <label for="memberId">memberId</label>
                <input type="text" class="form-control" id="memberId" name="memberId" placeholder="memberId" value="${boardDTO.memberId}">
            </div>
            <!-- categoryName -->
            <div class="col-md-6 mb-3">
                <label for="category">Category</label>
                <c:if test="${mode == 'write'}" >
                    <select class="custom-select d-block w-100" id="category" name="category">
                        <option value="">Choose...</option>
                        <option value="notice">Notice</option>
                        <option value="FAQ">FAQ</option>
                    </select>
                    <form:errors path="category"/>
                </c:if>
                <c:if test="${mode == 'edit'}" >
                    <input type="text" class="form-control" id="memberId" name="memberId" value="${boardDTO.category}" readonly>
                </c:if>
            </div>
        </div>
        <!-- boardTitle -->
        <div class="mb-3">
            <label for="boardTitle">Title</label>
            <input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="Title" value="${boardDTO.boardTitle}"/>
            <form:errors path="boardTitle"/>
        </div>
        <!-- boardContent -->
        <div class="mb-3">
            <label for="boardContent">Content</label>
            <textarea class="form-control" id="boardContent" name="boardContent" rows="5" style="resize: none;" placeholder="Content">${boardDTO.boardContent}</textarea>
            <form:errors path="boardContent"/>
        </div>
        <button class="btn btn-primary" type="button" onclick="location.href='/admin/board-list?category=${category}';" >list</button>
        <button class="btn btn-primary" type="submit">register</button>
    </form:form>
</div>
</body>
</head>
