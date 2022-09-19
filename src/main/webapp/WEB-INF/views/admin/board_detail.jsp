<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
				<div class="card-header align-items-baseline px-7">
					<h3>${boardDTO.boardTitle}</h3>
					<fmt:parseDate value="${boardDTO.boardCreate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
					<h6>${boardDTO.category} / <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parsedDateTime }" /> / ${boardDTO.boardHits}</h6>
				</div>
				<div class="card-body">
					<div class="card mb-3">
						<div class="card-body min-px-250 py-5">
							<p class="card-text">${boardDTO.boardContent}</p>
						</div>
					</div>
					<!-- button -->
					<div class="d-flex justify-content-between mt-6">
						<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/board-list?boardCategory=${boardCategory }';" >목록</button>
						<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/board-edit?boardId=${boardDTO.boardId }';">수정</button>
					</div>
				</div>
			</div>
		</div>
	</div>
