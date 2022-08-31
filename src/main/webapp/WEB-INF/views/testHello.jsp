<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!doctype html>
<html>
<head>
    <title>Hello Spring Web MVC</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>

    <style>
        div[class^="col"] { padding: 20px; border: 1px solid #dadada; background-color: #eeeeee; }
    </style>

</head>
<body>
    <div class="container">

        <div class="row">
            <div class="col align-self-start">
                One of three columns
            </div>
        </div>
<!--
        <div class="row justify-content-start">
            <div class="col-4">
                One of two columns1
            </div>
            <div class="col-4">
                One of two columns1
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-4">
                One of two columns2
            </div>
            <div class="col-4">
                One of two columns2
            </div>
        </div>

        <div class="row justify-content-end">
            <div class="col-4">
                One of two columns3
            </div>
            <div class="col-4">
                One of two columns3
            </div>
        </div>

        <div class="row justify-content-around">
            <div class="col-4">
                One of two columns4
            </div>
            <div class="col-4">
                One of two columns4
            </div>
        </div>

        <div class="row justify-content-between">
            <div class="col-4">
                One of two columns5
            </div>
            <div class="col-4">
                One of two columns5
            </div>
        </div>
-->
      </div>
</body>
</html>