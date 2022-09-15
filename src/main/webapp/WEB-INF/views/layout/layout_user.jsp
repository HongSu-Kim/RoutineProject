<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.101.0">
  
  <title>Layout User</title>

  <!-- <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/> -->
  <link rel="stylesheet" href="/css/style.css" id="main-css-href"/>
  <link rel="stylesheet" type="text/css" href="/css/layout_user.css"/>

</head>
<body id="body">

  <div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh">
    <div class="d-flex flex-column justify-content-between">
      <div class="justify-content-center">
        <div class="col-xl-5">
          <div class="card card-default mb-0">
            <!-- <tiles:insertAttribute name="body"/>  -->
            <tiles:insertAttribute name="navbar"/>
          </div>
        </div>
      </div>
    </div>
  </div>

  
  
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>

</body>
</html>