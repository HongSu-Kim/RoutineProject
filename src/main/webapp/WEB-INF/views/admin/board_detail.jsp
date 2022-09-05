<html>
<body>
<div class="col-md-8 order-md-1">
    <h4 class="mb-3">${boardDTO.boardTitle }</h4>
    <input type="hidden" name="boardNum" value="${boardDTO.boardNum }"/>
    <div class="row">
        <!-- idx -->
        <div class="col-md-6 mb-3">
            <label for="idx">Idx</label>
            ${boardDTO.idx }
            <!-- <input type="text" class="form-control" id="idx" name="idx" value="${dto.idx}" readonly> -->
        </div>
        <!-- categoryName -->
        <div class="col-md-6 mb-3">
            <label for="categoryName">Category</label>
            ${boardDTO.categoryName }
        </div>
    </div>
    <!-- boardContent -->
    <div class="mb-3">
        <label for="boardContent">Content</label>
        <textarea class="form-control" id="boardContent" name="boardContent" rows="5" style="resize: none;" readonly>${boardDTO.boardContent}</textarea>
    </div>
    <button class="btn btn-primary" onclick="location.href='/admin/board-list';">list</button>
    <button class="btn btn-primary" onclick="location.href='/admin/board-write';">edit</button>
    <hr class="mb-4">
    <form class="needs-validation" action="" method="post" novalidate="">
        <!-- replyContent -->
        <div class="mb-3">
            <label for="replyContent">Reply</label>
            <textarea class="form-control" id="replyContent" name="replyContent" rows="5" style="resize: none;" placeholder="Content"></textarea>
            <div class="invalid-feedback">
                Please enter Content.
            </div>
        </div>
        <button class="btn btn-primary" type="submit">register</button>
    </form>
</div>
</body>
</head>
