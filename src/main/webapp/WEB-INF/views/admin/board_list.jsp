<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
				<!-- header -->
				<div class="card-header">
					<h2>Board List</h2>
				</div>
				<!-- input -->
				<div class="card-body">
          <table class="table">
            <thead class="thead-light">
              <tr>
                <th scope="col">No.</th>
                <th scope="col">Title</th>
                <th scope="col">Created</th>
                <th scope="col">Hits</th>
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
                  <td scope="row">${dto.boardId }</td>
                  <td>
                    <a class="btn btn-link px-0 py-0" href="/admin/board-detail?boardId=${dto.boardId }">${dto.boardTitle }</a>
                  </td>
                  <fmt:parseDate value="${dto.boardCreate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDateTime }" /></td>
                  <td>${dto.boardHits }</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <!-- button -->
          <div class="d-flex justify-content-end mt-6">
            <button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/board-write?boardCategory=${boardCategory }';">write</button>
          </div>
				</div>
			</div>
		</div>
	</div>