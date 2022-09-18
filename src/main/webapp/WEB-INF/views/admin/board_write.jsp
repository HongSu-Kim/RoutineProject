<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
			
				<!-- header -->
				<div class="card-header">
					<h2>Board Write</h2>
				</div>

				<!-- input -->
				<div class="card-body">
					<form:form action="" method="post" modelAttribute="boardDTO">
						<div class="row">
							<!-- nickname -->
							<div class="col-lg-6">
								<div class="form-group">
									<label for="memberNickname">Nickname</label>
									<input type="text" class="form-control" id="memberNickname" name="memberId" value="${boardDTO.memberNickname}">
								</div>
							</div>
							<!-- category -->
							<div class="col-lg-6">
								<div class="form-group">
									<label for="category">Category</label>
									<c:if test="${mode == 'write'}" >
										<select class="form-control" id="category" name="category">
											<option value="">Choose...</option>
											<option value="notice">Notice</option>
											<option value="FAQ">FAQ</option>
										</select>
									</c:if>
									<c:if test="${mode == 'edit'}" >
											<input type="text" class="form-control" id="memberId" name="memberId" value="${boardDTO.category}" readonly>
									</c:if>
									<form:errors path="category"/>
								</div>
							</div>
						</div>
						<!-- title -->
						<div class="form-group mb-4">
							<label for="boardTitle">Title</label>
							<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${boardDTO.boardTitle}">
							<form:errors path="boardTitle"/>
						</div>
						<!-- content -->
						<div class="form-group">
							<label for="boardContent">Content</label>
							<textarea class="form-control min-px-250" id="boardContent" name="boardContent">${boardDTO.boardContent}</textarea>
							<form:errors path="boardContent"/>
						</div>
						<!-- button -->
						<div class="d-inline-flex float-right mt-4">
							<button class="btn btn-outline-primary mb-2" type="submit">register</button>
						</div>
					</form:form>
					<div class="d-inline-flex mt-4">
						<c:if test="${mode == 'write'}" >
							<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/board-list?boardCategory=${boardCategory }';" >list</button>
						</c:if>
						<c:if test="${mode == 'edit'}" >
							<button class="btn btn-outline-primary mb-2" onclick="history.back()" >cancle</button>
						</c:if>
					</div>
				</div>

			</div>
		</div>
	</div>
