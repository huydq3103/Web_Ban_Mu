<!DOCTYPE html>
<html>
<head>
    <title>Dang Ki</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%@include file="../Layout/header.jsp" %>
<div class="container">
    <h1 class="mt-5">Đăng ký</h1>
    <form action="/dang-ki" method="post">
        <div class="form-group">
            <label for="password">Mail:</label>
            <input type="text" class="form-control" id="mail" name="mail" required>
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <button type="submit" class="btn btn-primary">Đăng ký</button>
    </form>

    <%-- Kiểm tra và hiển thị thông điệp thành công --%>
   <c:if test="${fail !=null}">
       <div class="alert alert-danger mt-3">
           <p>${fail}
           </p>
       </div>
   </c:if>



</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
