$(function() {

	let weekActive = document.getElementsByName('weekActive')

	for (let i = 0; i < 7; i++) {
		if (weekActive[i].value == 'false') {
			document.getElementById('week' + i).style.color = '#a38479'
			document.getElementById('week' + i).style.backgroundColor = '#ffffff'
		}
	}

	$('.week').click(function() {
		if ($(this).parent('span').children('input').val() == 'true') {
			$(this).css('color','#a38479').css('background-color','#ffffff')
			$(this).parent('span').children('input').val('false')
		} else {
			$(this).css('color','#ffffff').css('background-color','#a38479')
			$(this).parent('span').children('input').val('true')
		}
	})

})
