<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<%-- <div class="col-md-8 order-md-1">
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
	</div> --%>
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">

				<!-- header -->
				<div class="card-header">
					<h2>Board Detail</h2>
				</div>

				<!-- body -->
				<div class="card-body">
					<div class="row">
						<!-- nickname -->
						<div class="col-lg-6">
							<div clas="card-title">
								<label>Nickname</label>
							</div>
							<div class="card mb-3">
								<div class="card-body py-2">
									<p class="card-text">${boardDTO.memberNickname}</p>
								</div>
							</div>
						</div>
						<!-- category -->
						<div class="col-lg-6">
							<div clas="card-title">
								<label>Category</label>
							</div>
							<div class="card mb-3">
								<div class="card-body py-2">
									<p class="card-text">${boardDTO.category}</p>
								</div>
							</div>
						</div>
					</div>
					<!-- title -->
					<label for="boardTitle">Title</label>
					<div class="card mb-3">
						<div class="card-body py-2">
							<p class="card-text">${boardDTO.boardTitle}</p>
						</div>
					</div>
					<!-- content -->
					<label for="boardContent">Content</label>
					<div class="card mb-3">
						<div class="card-body min-px-250 py-2">
							<p class="card-text">${boardDTO.boardTitle}</p>
						</div>
					</div>
					<!-- button -->
					<div class="d-flex justify-content-between mt-6">
						<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/board-list?boardCategory=${boardCategory }';" >list</button>
						<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/board-edit?boardId=${boardDTO.boardId }';">edit</button>
					</div>
				</div>

			<div>
		</div>
	</div>
</div>
