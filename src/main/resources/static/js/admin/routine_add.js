$(function() {

	$('#switch-active').click(function() {
		if ($('#routineActive').val() == 'true') $('#routineActive').val('false')
		else $('#routineActive').val('true')
	});

	$('#addButton').click(function() {

		let index = $('#missionList tr').length

		$('#missionList').append(
			'<tr>' +
			'	<td><input type="text" class="form-control" id="iconId" name="iconId"></td>' +
			'	<td><input type="text" class="form-control" id="missionName" name="missionName"></td>' +
			'	<td><input type="text" class="form-control" id="runtime" name="runtime" onclick=saveTime(' + index + ') onblur="checkTime(' + index + ')" ></td>' +
			'	<td><input type="text" class="form-control" id="missionContent" name="missionContent"></td>' +
			'	<td><div class="btn-group" role="group" aria-label="Basic example">' +
			'		<button type="button" class="btn btn-sm btn-outline-primary px-2">미션</button>' +
			'		<button type="button" class="btn btn-sm btn-outline-primary px-2 removeButton">삭제</button>' +
			'	</div></td>' +
			'</tr>'
		);

		$('.removeButton').on('click', function() {
			$(this).parents("tr").remove (); // remove the button
		});

		// $('.runtime').on('blur', function() {
		// 	alert('asdas')
		// 	let runtime = document.getElementsByName('runtime')
		// 	let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/
		// 	if (!reg.test(this.value)) {
		// 		alert('asdas')
		// 	}
		// });
	});

	// $('#removeButton').on('click', function() {
	// 	$(this).parents("tr").remove (); // remove the button
	// });

	$('#submitButton').on('click', function() {
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

		let iconId = document.getElementsByName('iconId')
		let missionName = document.getElementsByName('missionName')
		let runtime = document.getElementsByName('runtime')
		let missionContent = document.getElementsByName('missionContent')

		for (let i = 0; i < iconId.length; i++) {
			// 아이콘
			if (!iconId[i].value) {
				alert('아이콘 필수');
				iconId[i].focus();
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
			if (runtime[i].value.trim() == '') {
				alert('소요시간 필수');
				runtime[i].value == '';
				runtime[i].focus();
				return;
			} else {
				let runtime = document.getElementsByName("runtime")[i].value;
				let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/
				if (!reg.test(runtime)) {
					checkTime(i)
					runtime[i].focus();
					return;
				}
			}
		}

		document.routineForm.submit();
	});

});

function addTime(data1) {
	let time1 = new Date('1970-01-01 ' + totalTime)
	let time2 = new Date('1970-01-01 ' + data1)
	time1.setHours(time1.getHours() + time2.getHours())
	time1.setMinutes(time1.getMinutes() + time2.getMinutes())
	time1.setSeconds(time1.getSeconds() + time2.getSeconds())
	totalTime = '' + (time1.getHours() < 10 ? '0' + time1.getHours() : time1.getHours())
			+ ':' + (time1.getMinutes() < 10 ? '0' + time1.getMinutes() : time1.getMinutes())
			+ ':' + (time1.getSeconds() < 10 ? '0' + time1.getSeconds() : time1.getSeconds())
}

function subTime(data1) {
	let time1 = new Date('1970-01-01 ' + totalTime)
	let time2 = new Date('1970-01-01 ' + data1)
	time1.setHours(time1.getHours() - time2.getHours())
	time1.setMinutes(time1.getMinutes() - time2.getMinutes())
	time1.setSeconds(time1.getSeconds() - time2.getSeconds())
	totalTime = '' + (time1.getHours() < 10 ? '0' + time1.getHours() : time1.getHours())
			+ ':' + (time1.getMinutes() < 10 ? '0' + time1.getMinutes() : time1.getMinutes())
			+ ':' + (time1.getSeconds() < 10 ? '0' + time1.getSeconds() : time1.getSeconds())
}

let totalTime = '00:00:00'
let currentTime = '00:00:00'
function saveTime(num) {
	currentTime = '00:00:00'
	let runtime = document.getElementsByName("runtime")[num].value;
	let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/
	if (reg.test(runtime)) {
		currentTime = runtime
	}
}

function checkTime(num) {
	let runtime = document.getElementsByName("runtime")[num].value;
	let reg = /^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/
	if (!reg.test(runtime)) {
		subTime(currentTime)
		console.log(totalTime)
		currentTime = '00:00:00'
		alert('시간형식에 맞게 입력해주세요(hh:mm:ss)')
	} else {
		subTime(currentTime)
		addTime(runtime)
		$('#totalTime').val(totalTime)
	}
}
