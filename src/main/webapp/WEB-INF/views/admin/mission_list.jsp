<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<div class="content-wrapper">
		<div class="content">
			<!-- mission -->
			<div class="card card-default">
				<div class="card-body">
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th scope="col">No.</th>
								<th scope="col">Icon</th>
								<th scope="col">Name</th>
								<th scope="col">Run Time</th>
								<th scope="col">Content</th>
							</tr>
						</thead>
						<tbody>
							<!-- mission list -->
							<c:if test="${empty lists}">
								<tr>
									<td colspan="5" align="center">추천 미션이 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="missionDTO" items="${lists}">
								<tr>
									<td scope="row">${missionDTO.missionId }</td>
									<td>${missionDTO.iconFileName }</td>
									<td>${missionDTO.missionName }</td>
									<fmt:parseDate value="${missionDTO.runTime }" pattern="HH:mm:ss" var="parsedTime" type="time" />
									<td><fmt:formatDate pattern="HH:mm:ss" value="${parsedTime }" type="time" /></td>
									<td>${missionDTO.missionContent }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- button -->
					<div class="d-flex justify-content-end mt-6">
						<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/mission-add';">미션 추가</button>
					</div>
				</div>
			</div>
		</div>
	</div>
