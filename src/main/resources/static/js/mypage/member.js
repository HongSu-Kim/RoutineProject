/* 회원정보 수정 */
//$("#edit").on("click", () => {
$("#edit").on("click", function () {
    let data = {
            email: $("#email").val(),
            pwd: $("#newPwd").val(),
            nickname: $("#nickname").val()
        }

        $.ajax({
            type: "PUT",
            url: "mypage-edit",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data), // http body 데이터
            dataType: "json"
        }).success(function(){
            alert("회원정보가 수정되었습니다.");
            location.href="mypage";
        }).error(function(error){
            alert(JSON.stringify(error));
        })
});


/* 비밀번호 찾기 */
/* 이메일 전송 */
$("#pwdFind").click(function () {
    let email = $("#email").val();
    let name = $("#name").val();

    $.ajax({
        type: "GET",
        url: "pwd-find",
        data: {
            "email": email,
            "name": name
        },
        success: function (res) {
            if (res['check']) {
                alert("발송 완료!", "입력하신 이메일로 인증번호가 발송되었습니다.", "success").then((OK) => {
                    if(OK) {
                        $.ajax({
                            type: "POST",
                            url: "pwd-find",
                            data: {
                                "email": email,
                                "name": name
                            }
                        })
                        window.location = "login";
                    }
                }
            )
                $('#checkMsg').html('<p style="color:black"></p>');
            } else {
                $('#checkMsg').html('<p style="color:red">일치하는 정보가 없습니다.</p>');
            }
        }
    })
});