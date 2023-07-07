<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-6 mt-5">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title text-center">Quen mat khau</h3>
                    <form action="/forgot-password" method="POST">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Gửi yêu cầu</button>
                    </form>
                    <%--        kiem tra nếu thất bai--%>
                    <% if (request.getAttribute("FailMessage") != null) { %>
                    <div class="alert alert-danger mt-3">
                        <p><%= request.getAttribute("FailMessage") %>
                        </p>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
