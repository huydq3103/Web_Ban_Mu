<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <title>Login Form</title>
</head>
<body>


<div class="container">
    <h1>Dang nhap</h1>
    <form id="login-form" action="/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="role">Role:</label>--%>
<%--            <div class="form-check">--%>
<%--                <input type="radio" class="form-check-input" id="role-user" name="role" value="1" checked>--%>
<%--                <label class="form-check-label" for="role-user">User</label>--%>
<%--            </div>--%>
<%--            <div class="form-check">--%>
<%--                <input type="radio" class="form-check-input" id="role-admin" name="role" value="0">--%>
<%--                <label class="form-check-label" for="role-admin">Admin</label>--%>
<%--            </div>--%>
<%--        </div>--%>
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
        <a href="/form-dang-ki">Ban chua co tai khoan an vao day ?</a>
        <a href="/quen-mat-khau">Quen mat khau ?</a>
        <!-- Hiển thị thông báo lỗi -->
        <c:if test="${not empty error}">
            <p style="color: red">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>
        <%-- Kiểm tra và hiển thị thông điệp thành công --%>
        <% if (request.getAttribute("successMessage") != null) { %>
        <div class="alert alert-success mt-3">
            <p><%= request.getAttribute("successMessage") %>
            </p>
        </div>
        <% } %>
        <%-- Kiểm tra xem có thông báo lỗi không --%>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
        <!-- Ví dụ kiểm tra xem session đã xóa hay chưa -->

    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


<script>
    $(document).ready(function () {
        $("#login-form").submit(function (event) {

            // Lấy giá trị từ trường username, password và role
            var username = $("#username").val();
            var password = $("#password").val();

            // Kiểm tra nếu trường username hoặc password rỗng
            if (username === "") {
                alert("Vui lòng nhập username.");
                event.preventDefault(); // Ngăn chặn gửi biểu mẫu mặc định
            }

            if (password === "") {
                alert("Vui lòng nhập password.");
                event.preventDefault(); // Ngăn chặn gửi biểu mẫu mặc định
            }

            // Tiến hành xử lý tiếp theo nếu không có lỗi
            // Gửi dữ liệu đăng nhập (username, password, role) đến máy chủ hoặc xử lý tại đây

            // ...
        });
    });
</script>

</body>
</html>
