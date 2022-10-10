$(function() {

    /* 활성화 */
    $('#routine-activate').click(function() {

    });

    /* 비활성화 */
    $('#routine-deactivate').click(function() {

    });

    /* 삭제 */
    $('.dropdown-delete').click(function() {
        let routineId = $(this).siblings('input').val()
        $('#deleteId').val(routineId)
    });

})