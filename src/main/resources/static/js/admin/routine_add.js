$(function() {

	$('#switch-active').click(function() {
		if ($('#routineActive').val() == 'true') {
			$('#routineActive').val('false')
		} else {
			$('#routineActive').val('true')
		}
	});

	$('#addButton').click(function() {

		let index = $('#missionList tr').length

		$('#missionList').append(
			'<tr>' +
			'	<td>' +
			'		<div class="form-group icon-group mb-0" data-toggle="modal" data-target="#missionListModal">' +
			'			<input type="hidden" id="missionIconId" name="missionIconId"/>' +
			'			<img src="https://item.kakaocdn.net/do/d535331cab7ad6095881f561e84c1886c37d537a8f2c6f426591be6b8dc7b36a" id="iconSrc">' +
			'		</div>' +
			'	</td>' +
			'	<td><input type="text" class="form-control" id="missionName" name="missionName"></td>' +
			'	<td><input type="text" class="form-control" id="runTime" name="runTime" onclick=saveTime(' + index + ') onblur="checkTime(' + index + ')" ></td>' +
			'	<td><input type="text" class="form-control" id="missionContent" name="missionContent" value=" "></td>' +
			'	<td><div class="btn-group" role="group" aria-label="Basic example">' +
			'		<button type="button" class="btn btn-sm btn-outline-primary px-2">미션</button>' +
			'		<button type="button" class="btn btn-sm btn-outline-primary px-2 removeButton">삭제</button>' +
			'	</div></td>' +
			'</tr>'
		);

		$('.removeButton').click(function() {
			$(this).parents("tr").remove (); // remove the button
		});

		$('.icon-group').click(function() {
			selectMission = $(this)
		})

		// $('.runTime').on('blur', function() {
		// 	alert('asdas')
		// 	let runTime = document.getElementsByName('runTime')
		// 	let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/
		// 	if (!reg.test(this.value)) {
		// 		alert('asdas')
		// 	}
		// });
	});

	// $('#removeButton').on('click', function() {
	// 	$(this).parents("tr").remove (); // remove the button
	// });

	let selectMission

	$('.category').click(function() {
		let category = $(this).siblings('input').val()
		$('.icon-row').css('display', 'none')
		$('#' + category).css('display', 'flex')
	})

	$('.icon-img').click(function() {
		let missionIconId = $(this).siblings('input[name="modalIconId"]').val()
		let iconSrc = $(this).siblings('input[name="modalIconSrc"]').val()

		selectMission.children('input[id="missionIconId"]').val(missionIconId)
		selectMission.children('img[id="iconSrc"]').attr('src', iconSrc)

		$('.icon-group').click()
	})

	$('#submitButton').click(function() {
		// 유효성 검사
		// routineName
		if($('#routineName').val().trim() == ''){
			$('#routineName-error').css('display', 'inline-block')
			$('#routineName').val('')
			$('#routineName').focus()
			return;
		} else {
			$('#routineName-error').hide()
		}

		let missionIconId = document.getElementsByName('missionIconId')
		let missionName = document.getElementsByName('missionName')
		let runTime = document.getElementsByName('runTime')
		let missionContent = document.getElementsByName('missionContent')

		for (let i = 0; i < missionIconId.length; i++) {
			// 아이콘
			if (!missionIconId[i].value) {
				alert('아이콘 필수');
				return;
			}
			// 미션명
			if (missionName[i].value.trim() == '') {
				alert('미션명 필수');
				missionName[i].value = '';
				missionName[i].focus();
				return;
			}
			// 소요시간
			if (runTime[i].value.trim() == '') {
				alert('소요시간 필수');
                runTime[i].value == '';
				return;
			} else {
				let runTime = document.getElementsByName("runTime")[i].value;
				let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$/
				if (!reg.test(runTime)) {
					checkTime(i)
                    runTime[i].focus();
					return;
				}
			}
		}

		document.routineForm.submit();
	});

});

function addTime(data1) {
	let time1 = new Date('1994-07-02 ' + totalTime)
	let time2 = new Date('1994-07-02  ' + data1)
	time1.setHours(time1.getHours() + time2.getHours())
	time1.setMinutes(time1.getMinutes() + time2.getMinutes())
	totalTime = '' + (time1.getHours() < 10 ? '0' + time1.getHours() : time1.getHours())
			+ ':' + (time1.getMinutes() < 10 ? '0' + time1.getMinutes() : time1.getMinutes())
}

function subTime(data1) {
	let time1 = new Date('1994-07-02 ' + totalTime)
	let time2 = new Date('1994-07-02 ' + data1)
	time1.setHours(time1.getHours() - time2.getHours())
	time1.setMinutes(time1.getMinutes() - time2.getMinutes())
	totalTime = '' + (time1.getHours() < 10 ? '0' + time1.getHours() : time1.getHours())
			+ ':' + (time1.getMinutes() < 10 ? '0' + time1.getMinutes() : time1.getMinutes())
}

let totalTime = '00:00:00'
let currentTime = '00:00:00'
function saveTime(num) {
	currentTime = '00:00:00'
	let runTime = document.getElementsByName("runTime")[num].value;
	let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$/
	if (reg.test(runTime)) {
		currentTime = runTime
	}
}

function checkTime(num) {
	let runTime = document.getElementsByName("runTime")[num].value;
	let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$/
	if (!reg.test(runTime)) {
		subTime(currentTime)
		console.log(totalTime)
		currentTime = '00:00:00'
		alert('시간형식에 맞게 입력해주세요(hh:mm)')
	} else {
		subTime(currentTime)
		addTime(runTime)
		$('#totalTime').val(totalTime)
	}
}
