<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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
</html>
