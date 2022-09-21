<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
									<td>
										<h2 class="mb-0">
											<button class="btn btn-link collapsed p-0" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
												${missionDTO.missionName }
											</button>
										</h2>
									</td>
									<fmt:parseDate value="${missionDTO.runTime }" pattern="HH:mm:ss" var="parsedTime" type="both"/>
									<td><fmt:formatDate pattern="HH:mm:ss" value="${parsedTime }" /></td>
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
