<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.101.0">

<title>Layout Admin</title>

<!-- <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/dashboard/"> -->

<!--css -->
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="/css/dashboard.css"/>
<link rel="stylesheet" type="text/css" href="/css/layout_admin.css"/>

</head>
<body>
  <tiles:insertAttribute name="navbar"/>
  <div class="container-fluid">
    <div class="row">
      <tiles:insertAttribute name="sidebarMenu"/>
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
        <tiles:insertAttribute name="body"/>
      </main>
    </div>
  </div>

  <!--script -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
  <script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
  </body>
</html>
