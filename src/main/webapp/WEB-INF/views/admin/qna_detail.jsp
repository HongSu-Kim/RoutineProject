<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
				<div class="card-header align-items-baseline px-7">
					<h3>${boardDTO.boardTitle}</h3>
					<fmt:parseDate value="${boardDTO.boardCreate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
					<h6 class="pr-4">${boardDTO.category} / <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parsedDateTime }" /> / ${boardDTO.boardHits}</h6>
				</div>
				<div class="card-body">
					<p class="px-6 pb-3">${boardDTO.memberNickname} / ${boardDTO.memberEmail}</p>
					<div class="card mb-3">
						<div class="card-body min-px-250 py-5">
							<p class="card-text">${boardDTO.boardContent}</p>
						</div>
					</div>
					<!-- reply -->
					<c:if test="${empty boardDTO.reply}" >
						<form:form action="/admin/reply-write" method="post" modelAttribute="replyWriteDTO">
							<!-- session에서 받아온 작성자 id -->
							<%-- <input type="hidden" name="memberId" value="${memberId}" /> --%>
							<input type="hidden" name="memberId" value="1" />
							<input type="hidden" name="boardId" value="${boardDTO.boardId}" />
							<div class="form-group">
								<label for="replyContent">답글 작성</label>
									<div class="error-message">
										<form:errors path="replyContent"/>
									</div>
								<textarea class="form-control min-px-150" id="replyContent" name="replyContent" placeholder="답글을 작성해주세요..."></textarea>
							</div>
							<div class="d-inline-flex float-right mt-4">
								<button class="btn btn-outline-primary mb-2" type="submit">작성</button>
							</div>
						</form:form>
					</c:if>
					<c:if test="${!empty boardDTO.reply}" >
						<div class="card-header align-items-baseline px-3">
							<fmt:parseDate value="${boardDTO.reply.replyCreated }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime2" type="both"/>
							<h6>답변 완료 / <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parsedDateTime2 }" /></h6>
							<h6 class="text-transform-none">${boardDTO.reply.member.nickname} / ${boardDTO.reply.member.email}</h6>
						</div>
						<div class="card mb-3">
							<div class="card-body min-px-250 py-5">
								<p class="card-text">${boardDTO.reply.replyContent}</p>
							</div>
						</div>
					</c:if>
					<div class="d-inline-flex mt-4">
						<button class="btn btn-outline-primary mb-2" type="button" onclick="location.href='/admin/board-list?boardCategory=${boardCategory }';" >목록</button>
						</div>
				</div>
			</div>
		</div>
	</div>
