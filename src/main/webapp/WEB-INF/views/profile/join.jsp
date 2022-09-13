<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<head>
    <title>Join</title>
</head>
<body>

<div><</div>
<div>로그인</div>

<div class="title">회원가입</div>

<form:form action="/profile/join" method="post" modelAttribute="memberJoinDTO">

    <div class="contents">
        <input class="element" type="text" name="email" id="email" placeholder="이메일 주소">
        <form:errors path="email"/>
    </div>
    <div class="contents">
        <input class="element" type="password" name="pwd" id="pwd" placeholder="비밀번호">
        <form:errors path="pwd"/>
    </div>
    <div class="contents">
        <input class="element" type="password" name="pwd2" id="pwd2" placeholder="비밀번호 확인">
        <form:errors path="pwd2"/>
        <spring:hasBindErrors name="memberJoinDTO">
            <c:if test="${errors.hasFieldErrors('pwd2')}">
                ${errors.getFieldError('pwd2').defaultMessage}
            </c:if>
        </spring:hasBindErrors>
    </div>
    <div class="contents">
        <input class="element" type="text" name="nickname" id="nickname" placeholder="닉네임">
        <form:errors path="nickname"/>
    </div>
    <div class="contents">
        <input class="element" type="text" name="gender" id="gender" placeholder="성별">
        <form:errors path="gender"/>
    </div>
    <div class="contents">
        <input class="element" type="text" name="birth" id="birth" placeholder="생년월일">
        <form:errors path="birth"/>
    </div>
    <button class="button" type="submit">가입하기</button>
</form:form>

</body>
</html>
