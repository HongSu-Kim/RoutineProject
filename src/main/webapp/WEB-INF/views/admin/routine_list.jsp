<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%-- <html>
<head>
<title>Routine List</title>
</head>
<body>
<h2>Routine List</h2>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Total Time</th>
                <th>Active</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty lists}">
                <tr>
                    <td colspan="4" align="center">추천 루틴이 존재하지 않습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="dto" items="${lists}">
                <tr>
                    <td>${dto.routineId }</td>
                    <td>${dto.routineName }</td>
                    <td>${dto.totalTime }</td>
                    <td>${dto.active }</td>
                </tr>
                <tr class="collapse bg-dark" id="navbarHeader">
                  <div class="container">
                    <div class="row">
                      <div class="col-sm-8 col-md-7 py-4">
                        <h4 class="text-white">About</h4>
                        <p class="text-muted">Add some information about the album below, the author, or any other background context. Make it a few sentences long so folks can pick up some informative tidbits. Then, link them off to some social networking sites or contact information.</p>
                      </div>
                      <div class="col-sm-4 offset-md-1 py-4">
                        <h4 class="text-white">Contact</h4>
                        <ul class="list-unstyled">
                          <li><a href="#" class="text-white">Follow on Twitter</a></li>
                          <li><a href="#" class="text-white">Like on Facebook</a></li>
                          <li><a href="#" class="text-white">Email me</a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary" onclick="location.href='/admin/routine-add';">write</button>
</div>
</body>
</html> --%>
	<div class="content-wrapper">
		<div class="content">
			<div class="card card-default">
				<!-- header -->
				<div class="card-header">
					<h2>Routine List</h2>
				</div>
				<!-- input -->
				<div class="card-body">
          <table class="table">
            <thead class="thead-light">
              <tr>
                <th scope="col">No.</th>
                <th scope="col">Name</th>
                <th scope="col">Total Time</th>
                <th scope="col">Active</th>
              </tr>
            </thead>
            <tbody>
              <c:if test="${empty lists}">
                <tr>
                  <td colspan="4" align="center">추천 루틴이 존재하지 않습니다.</td>
                </tr>
              </c:if>
              <c:forEach var="routineDTO" items="${lists}">
                <tr>
                  <td scope="row">${routineDTO.routineId }</td>
                  <td>
                    <h2 class="mb-0">
                      <button class="btn btn-link collapsed p-0" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                        ${routineDTO.routineName }
                      </button>
                    </h2>
                  </td>
                  <fmt:parseDate value="${routineDTO.totalTime }" pattern="HH:mm:ss" var="parsedTime" type="both"/>
                  <td><fmt:formatDate pattern="HH:mm:ss" value="${parsedTime }" /></td>
                  <td>${routineDTO.active }</td>
                </tr>
                <tr>
                  <td colspan="4">
                    <div class="accordion" id="accordionTwo">
                      <div class="card border-0">
                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionOne" style="">
                          <%-- <div class="card-body"> --%>
                            <table class="table">
                              <thead class="thead">
                                <tr>
                                  <th scope="col">No.</th>
                                  <th scope="col">Icon</th>
                                  <th scope="col">Name</th>
                                  <th scope="col">Run Time</th>
                                  <th scope="col">Content</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:if test="${empty routineDTO.missionList}">
                                  <tr>
                                    <td class="border-0" colspan="4" align="center">추천 미션이 존재하지 않습니다.</td>
                                  </tr>
                                </c:if>
                                <c:forEach var="missionDTO" items="${routineDTO.missionList}"> 
                                  <tr>
                                    <td scope="row">${missionDTO.missionOrder}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                  </tr>
                                </c:forEach>
                              </tbody>
                            </table>
                          <%-- </div> --%>
                        </div>
                      </div>
                    </div>
                  </td>
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

  <%-- <div class="card border-0">
    <div class="card-header" id="headingOne">
      <h2 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          Collapsible Group Item #1
        </button>
      </h2>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionOne" style="">
      <div class="card-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon
        officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3
        wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.
        Nihil
        anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan
        excepteur
        butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you
        probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div> --%>