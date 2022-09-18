<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<title>Routine Admin</title>

<!-- <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/dashboard/"> -->

<!-- GOOGLE FONTS -->
<link href="https://fonts.googleapis.com/css?family=Karla:400,700|Roboto" rel="stylesheet"/>
<link href="/plugins/material/css/materialdesignicons.min.css" rel="stylesheet"/>
<link href="/plugins/simplebar/simplebar.css" rel="stylesheet"/>

<!-- PLUGINS CSS STYLE -->
<link href="/plugins/nprogress/nprogress.css" rel="stylesheet"/>
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

<!-- MONO CSS -->
<link rel="stylesheet" href="/css/style.css"/>

<!-- LAYOUT ADMIN CSS -->
<link rel="stylesheet" href="/css/layout_admin.css"/>

<!-- FAVICON -->
<link href="/images/favicon.png" rel="shortcut icon"/>

<!-- nprogress script -->
<script src="/plugins/nprogress/nprogress.js"></script>

</head>
<body class="navbar-fixed sidebar-fixed" id="body">
<script>
  NProgress.configure({
    showSpinner : false
  });
  NProgress.start();
</script>

<!-- WRAPPER -->
<div class="wrapper">

  <!-- SIDEBAR -->
  <tiles:insertAttribute name="sidebar"/>
  <!-- SIDEBAR END -->

  <!-- PAGE WRAPPER -->
  <div class="page-wrapper min-vh-100">

    <!-- HEADER -->
    <tiles:insertAttribute name="header"/>
    <!-- HEADER END -->

    <!-- CONTENT -->
    <tiles:insertAttribute name="body"/>
    <!-- CONTENT END -->

    <!-- FOOTER -->
    <tiles:insertAttribute name="footer"/>
    <!-- FOOTER END -->

  </div>
  <!-- PAGE WRAPPER END-->

</div>
<!-- WRAPPER END -->

<!--script -->
<script src="/plugins/jquery/jquery.min.js"></script>
<script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/plugins/simplebar/simplebar.min.js"></script>
<script src="https://unpkg.com/hotkeys-js/dist/hotkeys.min.js"></script>

<!-- write -->
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="/plugins/dropzone/dropzone.js"></script>

<script src="/js/mono.js"></script>
<script src="/js/chart.js"></script>
<script src="/js/map.js"></script>
<script src="/js/custom.js"></script>

</body>
</html>
