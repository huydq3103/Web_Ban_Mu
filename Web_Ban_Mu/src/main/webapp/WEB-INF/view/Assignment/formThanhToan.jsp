
<html lang="en">
<head>
    <title>Trang thanh toán</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/bc8223d7dd.js" crossorigin="anonymous"></script>
    <style>  /* Thay đổi font chữ cho menu */
    .navbar-nav .nav-link {
        font-family: "Your-Font-Family", sans-serif; /* Thay thế "Your-Font-Family" bằng tên font chữ mong muốn */
    }</style>
</head>
<body>
<%@include file="../Layout/header.jsp" %>

<div class="container">
    <h1 class="mt-5">Trang thanh toán</h1>
    <form action="/thanh-toan" method="POST">
        <div class="form-group">
            <label for="diaChi">Địa chỉ:</label>
            <input type="text" class="form-control" id="diaChi" name="diaChi" required>
        </div>

        <div class="form-group">
            <label for="tenNguoiNhan">Tên người nhận:</label>
            <input type="text" class="form-control" id="tenNguoiNhan" name="tenNguoiNhan" required>
        </div>

        <div class="form-group">
            <label for="sdt">Số điện thoại:</label>
            <input type="tel" class="form-control" id="sdt" name="sdt" pattern="[0-9]{10}" required>
        </div>

        <div class="form-group">
            <label for="soTien">Số tiền cần thanh toán:</label>
            <input type="number" class="form-control" id="soTien" name="soTien" value="${tongtien}" required disabled>
        </div>
        <div class="form-group">
            <label for="soTien">Số tiền thanh toán:</label>
            <input type="number" class="form-control" id="soTienCanThanhToan" name="soTienCanThanhToan"
                   value="${tongtien}" required>
            <span id="errorContainer" class="text-danger"></span>
        </div>
           <input type="hidden" name="idgh" value="${idGh}" >

        <button type="submit" id="paymentButton" class="btn btn-primary">Thanh Toán</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    $(document).ready(function () {
        // Lắng nghe sự kiện khi người dùng nhập giá trị vào ô số tiền cần thanh toán
        $("#soTienCanThanhToan").on("input", function () {
            var soTienCanThanhToan = $(this).val();
            var soTienThanhToan = $("#soTien").val();
            var errorContainer = $("#errorContainer");
            var paymentButton = $("#paymentButton");

            // Kiểm tra các điều kiện và thực hiện các hành động tương ứng
            if (soTienCanThanhToan < 0 || soTienCanThanhToan > soTienThanhToan || soTienCanThanhToan < soTienThanhToan) {
                errorContainer.text("Số tiền không hợp lệ");
                paymentButton.prop("disabled", true);
            } else {
                errorContainer.text("");
                paymentButton.prop("disabled", false);
            }
        });
    });
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<!-- Footer -->
<%@include file="../Layout/footer.jsp" %>
<!-- Footer -->
</body>
</html>
