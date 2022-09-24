<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
				<div class="card-body">
					<form:form action="" method="post" modelAttribute="missionAddRecommendDTO">
						<div class="row">
							<!-- name -->
							<div class="col-lg-6">
								<div class="form-group mb-4">
									<label for="missionName">미션명</label>
										<div class="error-message">
											&nbsp;&nbsp;<form:errors path="missionName"/>
										</div>
									<input type="text" class="form-control" id="missionName" name="missionName" value="${missionDTO.missionName}">
								</div>
							</div>
							<!-- icon -->
							<div class="col-lg-6">
								<div class="form-group mb-4">
									<label for="iconId">아이콘 </label>
									<button type="button" class="btn btn-sm btn-outline-primary px-1 py-1" id="iconList"> 아이콘 선택 </button>
									<div class="error-message">
										&nbsp;&nbsp;<form:errors path="iconId"/>
									</div>
									<%-- <input type="hidden" class="form-control" id="iconId" name="iconId" value="${missionDTO.iconId}"> --%>
									<input type="text" class="form-control" id="iconId" name="iconId" value="${missionDTO.iconId}">
								</div>
							</div>
						</div>
						<!-- runTime -->
						<div class="form-group mb-4">
							<label for="runTime">소요시간</label>
								<div class="error-message">
									&nbsp;&nbsp;<form:errors path="runTime"/>
								</div>
							<input type="text" class="form-control" id="runTime" name="runTime" value="${missionDTO.runTime}">
						</div>
						<!-- content -->
						<div class="form-group mb-4">
							<label for="missionContent">내용</label>
							<textarea class="form-control min-px-250" id="missionContent" name="missionContent">${missionDTO.missionContent}</textarea>
						</div>
						<!-- button -->
						<div class="d-inline-flex float-right mt-4">
							<button class="btn btn-outline-primary mb-2" type="submit">추가</button>
						</div>
					</form:form>
					<div class="d-inline-flex mt-4">
						<c:if test="${mode == 'add'}" >
							<button class="btn btn-outline-primary mb-2" onclick="location.href='/admin/mission-list';" >목록</button>
						</c:if>
						<c:if test="${mode == 'update'}" >
							<button class="btn btn-outline-primary mb-2" type="button" onclick="history.back()" >취소</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- script -->
<script type ="text/javascript">
$(function() {
	$('#iconList').bind('click', function() {
		$('#iconId').val(1);
	})
});
</script>