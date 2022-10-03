$(function() {

	for (let i = 0; i < 7; i++) {
		let weekBtn = $('button[id="week' + i + '"]')
		if (weekBtn.siblings('input').val() == 'false') {
			weekBtn.css('color', '#a38479')
			weekBtn.css('backgroundColor', '#ffffff')
		}
	}

	$('.week').click(function() {
		if ($(this).siblings('input').val() == 'true') {
			$(this).css('color','#a38479').css('background-color','#ffffff')
			$(this).siblings('input').val('false')
		} else {
			$(this).css('color','#ffffff').css('background-color','#a38479')
			$(this).siblings('input').val('true')
		}
	})

})
