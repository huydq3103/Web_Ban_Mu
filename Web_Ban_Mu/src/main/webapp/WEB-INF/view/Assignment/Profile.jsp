<!DOCTYPE html>
<html>
<head>
    <title>Dang Ki</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/bc8223d7dd.js" crossorigin="anonymous"></script>
</head>
<body>

<%@include file="../Layout/header.jsp" %>
<div class="container">
    <h1 class="mt-5">Đăng ký</h1>
    <form action="/update-profile" method="post">
        <div class="form-group">
            <label for="password">Mail:</label>
            <input type="email" class="form-control" id="mail" name="mail" value="${account.mail}" required>
        </div>
        <div class="form-group">
            <label>Username:</label>
            <input type="text" class="form-control" name="userName" value="${account.userName}" disabled>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="text" class="form-control" id="password" value="${account.password}" name="password"
                   required>
        </div>

        <button type="submit" class="btn btn-primary" onclick="return confirmUpdate();">Cập nhật</button>
    </form>

    <%-- Kiểm tra và hiển thị thông điệp thành công --%>


    <c:if test="${success !=null}">
        <div class="alert alert-success mt-3">
            <p>${success}
            </p>
        </div>
    </c:if>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    function confirmUpdate() {
        if (confirm('Bạn có chắc chắn với sự thay đổi này?')) {
            // Nếu người dùng chọn "OK", cho phép form được gửi đi
            return true;
        } else {
            // Nếu người dùng chọn "Cancel", ngăn chặn việc gửi form
            return false;
        }
    }

</script>
</body>
</html>
