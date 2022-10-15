
let time
let timeStr

$(function() {
    setTime()

    $('#btn').click(function() {

        if ($('#btn').html() == "시작") {
            $('#btn').html("완료")
            //setInterval(함수, 시간) : 주기적인 실행
            stop = setInterval(timer, 1000)
            return;
        }

        clearInterval(stop)

        $.ajax({
            url: "/mission-next",
            method: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                routineId: $('#routineId').val(),
                nextMissionId: $('#nextMissionId').val()
            }),
            success: function (missionStartDTO) {
                $('#nextMissionId').val(missionStartDTO.nextMissionId);
                $('#missionName').text(missionStartDTO.missionName)
                $('#img').attr("src", "/img/icon/" + missionStartDTO.iconPath + "/" + missionStartDTO.iconFileName)
                let runTime = missionStartDTO.runTime.toString()
                $('#timer').text(runTime.substring(0,2) == ('00') ? runTime.substring(3): runTime)
                let runTimeStr = (runTime.substring(0,2) != ('00') ? runTime.substring(0,2) + "시간" : "") + runTime.substring(3,5) + "분"
                $('#runTimeStr').text(runTimeStr)
            },
            error: function (error) {
                location.href = '/routine-finish?routineId=' + $('#routineId').val()
            }

        }).done(function () {
            setTime()
            $('#timer').css('color', 'black')
            stop = setInterval(timer, 1000)
        })
    })
})

let setTime = function() {
    timeStr = $('#runTime').val()
    time = parseInt(timeStr.substring(0,2))*60*60 + parseInt(timeStr.substring(3,5))*60
}

let timer = function() {
    let curTime = (time > 0 ? time : time * -1) - 1

    //parseInt() : 정수를 반환
    let hou = parseInt(curTime/60/60)
    let min = parseInt(curTime/60%60) //몫을 계산
    let sec = curTime%60 //나머지를 계산

    timeStr = (time < 0 ? "-" : "")
            + (hou == 0 ? "" : (hou >= 10 ? hou + ":" : "0" + hou + ":"))
            + (min == 0 ? "00:" : ((min >= 10 ? min + ":" : "0" + min + ":")))
            + (sec > 10 ? sec : "0" + sec)


    $('#timer').html(timeStr)
    // document.getElementById("timer").innerHTML = timeStr;
    time--;

    //타임아웃 시
    if (time < 0) {
        // clearInterval(timer); //setInterval() 실행을 끝냄
        document.getElementById("timer").style.color = 'red'
        $('#timer').css('color', 'red')
    }
}