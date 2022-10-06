/* 회원가입 & 회원정보 수정 */
/* form 경로 설정 */
let join = /*[[ @{join} ]]*/;
let edit = /*[[ @{profile-edit} ]]*/;

$("#join").on("click", function(){
    $("#form").attr("action", join),
    $("#form").attr("method", post);
});

$("#edit").on("click", function(){
    $("#form").attr("action", edit),
    $("#form").attr("method", put);
});

/* 회원정보 수정 */
$("#edit").on("click", () => {
    this.edit();
});

edit: function(){
    let data = {
        email: $("#email").val(),
        pwd: $("#newPwd").val(),
        nickname: $("#nickname").val()
    }

    $.ajax({
        type: "PUT",
        url: "profile-edit",
        data: JSON.stringify(data), // http body 데이터
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).done(function(resp){
        swal("회원정보가 수정되었습니다.");
        location.href="mypage";
    }).fail(function(error){
        swal(JSON.stringify(error));
    })
}


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
                swal("발송 완료!", "입력하신 이메일로 인증번호가 발송되었습니다.", "success").then((OK) = > {
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
})