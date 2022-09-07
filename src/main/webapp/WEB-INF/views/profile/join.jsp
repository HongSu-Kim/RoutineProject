<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<head>
    <title>Join</title>
</head>
<body>
<form action="/join" method="post">
    <div class="title">회원가입</div>
    <div class="container">
        <div class="contents">
            <input class="element" type="text" name="email" id="email" placeholder="이메일 주소">
        </div>
        <div class="contents">
            <input class="element" type="password" name="pwd" id="pwd" placeholder="비밀번호">
        </div>
        <div class="contents">
            <input class="element" type="password" name="pwd2" id="pwd2" placeholder="비밀번호 확인">
        </div>
        <div class="contents">
            <input class="element" type="text" name="nickname" id="nickname" placeholder="닉네임">
        </div>
        <div class="contents">
            <input class="element" type="text" name="gender" id="gender" placeholder="성별">
        </div>
        <div class="contents">
            <input class="element" type="text" name="birth" id="birth" placeholder="생년월일">
        </div>
        
        <button class="button" type="submit">가입하기</button>
    </div>
</form>
</body>
</html>
