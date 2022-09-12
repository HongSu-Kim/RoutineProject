<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<body>
<div class="col-md-8 order-md-1">
    <h4 class="mb-3">Board Write</h4>
    <form class="needs-validation" action="" method="post">
        <div class="row">
            <!-- idx -->
            <div class="col-md-6 mb-3">
                <label for="memberId">memberId</label>
                <input type="text" class="form-control" id="memberId" name="memberId" placeholder="memberId" required>
                <!-- <input type="text" class="form-control" id="memberId" name="memberId" value="${dto.memberId}" readonly> -->
            </div>
            <!-- categoryName -->
            <div class="col-md-6 mb-3">
                <label for="category">Category</label>
                <select class="custom-select d-block w-100" id="category" name="category" required>
                    <option value="">Choose...</option>
                    <option value="notice">Notice</option>
                    <option value="FAQ">FAQ</option>
                </select>
                <div class="invalid-tooltip">
                    Please select a valid category.
                </div>
            </div>
        </div>
        <!-- boardTitle -->
        <div class="mb-3">
            <label for="boardTitle">Title</label>
            <input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="Title" required/>
            <div class="invalid-tooltip">
                Please enter Title.
            </div>
        </div>
        <!-- boardContent -->
        <div class="mb-3">
            <label for="boardContent">Content</label>
            <textarea class="form-control" id="boardContent" name="boardContent" rows="5" style="resize: none;" placeholder="Content" required></textarea>
            <div class="invalid-tooltip">
                Please enter Content.
            </div>
        </div>
        <button class="btn btn-primary" onclick="location.href='/admin/board-list?category=${category}';">list</button>
        <button class="btn btn-primary" type="submit">register</button>
    </form>
</div>
</body>
</head>
