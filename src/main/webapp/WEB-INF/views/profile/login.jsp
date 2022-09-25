<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>

<!-- header -->
<div class="card-header pb-0 card-header-routine">
    <a href="javascript:history.back();">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left bi-routine" viewBox="0 0 16 16">
            <path fill-rule="evenodd"d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
        </svg>
    </a>
</div>

<!-- body -->
<div class="card-body px-5 pb-5 pt-0 card-body-routine">
    <h4 class="text-dark text-center mb-5">로그인</h4>
    <form:form action="/profile/join" method="post" modelAttribute="memberJoinDTO">
        <div class="row row-routine">
            <!-- 입력 -->
            <div class="form-group col-md-12 mb-4 mb_routine">
                <div>
                    <input type="email" class="form-control input-lg input_routine" name="email" id="email" value="${memberJoinDTO.email}" aria-describedby="nameHelp" placeholder="이메일 주소">
                </div>
                <div class="error_routine">
                    <form:errors path="email"/>
                </div>
            </div>
            <div class="form-group col-md-12 mb-4 mb_routine">
                <div>
                <input type="password" class="form-control input-lg input_routine" name="pwd" id="pwd" value="${memberJoinDTO.pwd}" aria-describedby="emailHelp" placeholder="비밀번호">
                 </div>
                <div class="error_routine">
                <form:errors path="pwd"/>
                </div>
            </div>

            <!-- 버튼 -->
            <div class="col-md-12 col-routine-2">
                <button type="submit" class="btn btn-primary btn-pill mb-4 btn-routine">로그인</button>
            </div>

            <!-- 하단 링크 -->
            <div>
                <div class="bottom-routine">
                    <a href="/profile/find" class="a-routine a-bottom-routine">비밀번호 찾기</a>
                </div>
                <div class="middlebar-routine"></div>
                <div class="bottom-routine">
                    <a href="/profile/join" class="a-routine a-bottom-routine">회원가입</a>
                </div>
            </div>
        </div>
    </form:form>
</div>