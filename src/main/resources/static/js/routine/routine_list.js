$(function() {

    /* 활성화 */
    $('.routine-activate').click(function() {
        let routineId = $(this).siblings('input').val()
        $('#activateId').val(routineId)
    });

    /* 비활성화 */
    $('.routine-deactivate').click(function() {
        let routineId = $(this).siblings('input').val()
        $('#deactivateId').val(routineId)
    });

    /* 삭제 */
    $('.dropdown-delete').click(function() {
        let routineId = $(this).siblings('input').val()
        $('#deleteId').val(routineId)
    });

})