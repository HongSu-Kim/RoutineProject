<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<div class="col-md-8 order-md-1">
    <h4 class="mb-3">${boardDTO.boardTitle }</h4>
    <input type="hidden" name="boardId" value="${boardDTO.boardId }"/>
    <div class="row">
        <!-- memberNum -> nickName -->
        <div class="col-md-6 mb-3">
            <label for="memberNickname">Member Nickname</label>
            <input type="text" class="form-control" id="memberNickname" name="memberNickname"value="${boardDTO.memberNickname }" readonly>
        </div>
        <!-- categoryName -->
        <div class="col-md-6 mb-3">
            <label for="category">Category</label>
            <input type="text" class="form-control" id="category" name="category" value="${boardDTO.category }" readonly>
        </div>
    </div>
    <!-- boardContent -->
    <div class="mb-3">
        <label for="boardContent">Content</label>
        <textarea class="form-control" id="boardContent" name="boardContent" rows="5" style="resize: none;" readonly>${boardDTO.boardContent}</textarea>
    </div>
    <button class="btn btn-primary" type="button" onclick="location.href='/admin/board-list?category=${boardDTO.category }';">list</button>
    <button class="btn btn-primary" type="button" onclick="location.href='/admin/board-edit?boardId=${boardDTO.boardId }';">edit</button>
    <hr class="mb-4">
    
    <!-- replyContent -->
    <c:if test="${boardDTO.category == 'QnA'}">
        <form class="needs-validation" action="/admin/reply-write" method="post" novalidate="">
            <div class="mb-3">
                <label for="replyContent">Reply</label>
                <textarea class="form-control" id="replyContent" name="replyContent" rows="5" style="resize: none;" placeholder="Content"></textarea>
                <div class="invalid-feedback">
                    Please enter Content.
                </div>
            </div>
            <button class="btn btn-primary" type="submit">register</button>
        </form>
        <hr class="mb-4">
    </c:if>
</div>
</body>
</head>
