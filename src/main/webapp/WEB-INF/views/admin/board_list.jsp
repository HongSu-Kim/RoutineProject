<html>
<head>
<body>
<h2>Inquire List</h2>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
            <tr>
                <th>No.</th>
                <th>Title</th>
                <th>Created</th>
                <th>Hits</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="dto" item="${lists}">
                <tr>
                    <td>${dto.boardNum}</td>
                    <td>${dto.boardTitle}</td>
                    <td>${dto.boardCraeted}</td>
                    <td>${dto.boardHits}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</head>
