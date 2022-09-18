<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>

<div class="col-lg-6 col-xl-5 col-md-10 ">
    <div class="card card-default mb-0">

        <!-- header -->
        <div class="card-header pb-0">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
               <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
            </svg>
            <button type="button" class="mb-1 btn btn-outline-primary btn-pill">로그인</button>
        </div>

        <!-- body -->
        <div class="card-body px-5 pb-5 pt-0">
            <h4 class="text-dark text-center mb-5">회원가입</h4>
            <form:form action="/profile/join" method="post" modelAttribute="memberJoinDTO">
                <div class="row">
                    <div class="form-group col-md-12 mb-4">
                        <input type="email" class="form-control input-lg" name="email" id="email" value="${memberJoinDTO.email}" aria-describedby="nameHelp" placeholder="이메일 주소">
                        <form:errors path="email"/>
                    </div>
                    <div class="form-group col-md-12 mb-4">
                        <input type="password" class="form-control input-lg" name="pwd" id="pwd" value="${memberJoinDTO.pwd}" aria-describedby="emailHelp" placeholder="비밀번호">
                        <form:errors path="pwd"/>
                    </div>
                    <div class="form-group col-md-12 ">
                        <input type="password" class="form-control input-lg" name="pwd2" id="pwd2" value="${memberJoinDTO.pwd2}" placeholder="비밀번호 확인">
                        <form:errors path="pwd2"/>
                    </div>
                    <div class="form-group col-md-12 ">
                        <input type="text" class="form-control input-lg" name="nickname" id="nickname" value="${memberJoinDTO.nickname}" placeholder="닉네임">
                        <form:errors path="nickname"/>
                    </div>
                    <div class="form-group col-md-12 mb-4">
                        <input type="text" class="form-control input-lg" name="gender" id="gender" value="${memberJoinDTO.gender}"  placeholder="성별">
                        <form:errors path="gender"/>
                    </div>
                    <div class="form-group col-md-12 mb-4">
                        <input type="text" class="form-control input-lg" name="birth" id="birth" value="${memberJoinDTO.birth}" placeholder="생년월일">
                        <form:errors path="birth"/>
                    </div>


                    <div class="col-md-12">
                        <div class="d-flex justify-content-between mb-3">
                            <div class="custom-control custom-checkbox mr-3 mb-3">
                                <input type="checkbox" class="custom-control-input" id="customCheck2">
                                <label class="custom-control-label" for="customCheck2">I Agree the terms and conditions.</label>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary btn-pill mb-4">가입하기</button>

                        <p>Already have an account?
                            <a class="text-blue" href="sign-in.html">Sign in</a>
                        </p>
                    </div>
                </div>
            </form:form>
      </div>
    </div>
</div>