$("#pwdFind").click(function () {
    let email = $("#email").val();
    let name = $("#name").val();

    $.ajax({
        type: "GET",
        url: "pwd-auth",
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
                            url: "pwd-auth",
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