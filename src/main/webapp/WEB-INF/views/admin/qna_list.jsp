<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
				<div class="card-body">
          <table class="table">
            <thead class="thead-light">
              <tr>
                <th scope="col">No.</th>
                <th scope="col">Email</th>
                <th scope="col">Title</th>
                <th scope="col">Created</th>
                <th scope="col">Reply</th>
              </tr>
            </thead>
            <tbody>
              <c:if test="${empty lists}">
                <tr>
                  <td colspan="5" align="center">글이 존재하지 않습니다.</td>
                </tr>
              </c:if>
              <c:forEach var="dto" items="${lists}">
                <tr>
                  <td scope="row">${dto.boardId }</td>
                  <td>
                    <a href="/admin/board-list?boardCategory=QnA&membarId=${dto.memberId}">${dto.memberEmail }</a>
                  </td>
                  <td>
                    <a class="btn btn-link px-0 py-0" href="/admin/board-detail?boardId=${dto.boardId }">${dto.boardTitle }</a>
                  </td>
                  <fmt:parseDate value="${dto.boardCreate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
                  <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parsedDateTime }" /></td>
                  <c:if test="${empty dto.reply}" >
                    <td>
                      <a href="/admin/reply-wrtie?boardId=${dto.boardId}">답변 대기</a>
                    </td>
                  </c:if>
                  <c:if test="${!empty dto.reply}" >
                    <td>답변 완료</td>
                  </c:if>
                </tr>
              </c:forEach>
            </tbody>
          </table>
				</div>
			</div>
		</div>
	</div>