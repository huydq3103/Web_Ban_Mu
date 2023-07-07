<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>Bootstrap Detail Page</title>
    <style>
        .input-group-sm .form-control {
            max-width: 38px;
        }
        /* Thay đổi font chữ cho menu */
        .navbar-nav .nav-link {
            font-family: "Your-Font-Family", sans-serif; /* Thay thế "Your-Font-Family" bằng tên font chữ mong muốn */
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/bc8223d7dd.js" crossorigin="anonymous"></script>
</head>
<body>

<%@include file="../Layout/header.jsp" %>
<div class="container">
    <h1>CHi tiet san pham</h1>
    <div class="row">
        <div class="col-md-6">
            <img src="/image/${mu.images}" alt="Hình ảnh sản phẩm" class="img-fluid">
        </div>

        <div class="col-md-6">
            <h3>Thong tin san pham</h3>
            <p>Mo ta san pham</p>
           <P>San pham khong chua thuoc tru sau an toan voi nguoi dung </P>

            <form action="/huydqph27425/Ass/add-san-pham-gh" method="post">
                <div class="form-group">
                    <label for="quantity">so luong:</label>
                    <div class="input-group input-group-sm">
                        <div class="input-group-prepend">
                            <button type="button" class="btn btn-outline-secondary" id="decrease">-</button>
                        </div>
                        <input type="text" id="quantity" class="form-control" name="soLuong"
                               oninput="checkQuantity()"
                               placeholder="Nhập số lượng" value="1">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-outline-secondary" id="increase">+</button>
                        </div>
                    </div>
                    <span>So luong san co : ${mu.soLuong}</span>
                </div>
                <input type="hidden" name="ma" value="${mu.ma}">
                <input type="hidden" name="giaGiam" value="${giaMoi}">
                <button id="addToCartBtn" type="submit" class="btn btn-primary" onclick="addToCart()">Thêm vào giỏ
                    hàng
                </button>
            </form>

            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                        ${message}
                </div>
            </c:if>

        </div>


    </div>
</div>


<script>
    $(document).ready(function () {
        $("#increase").click(function () {
            var quantity = parseInt($("#quantity").val());
            var sl = ${mu.soLuong};
            if (quantity < sl)
                $("#quantity").val(quantity + 1);
        });

        $("#decrease").click(function () {
            var quantity = parseInt($("#quantity").val());
            if (quantity > 1) {
                $("#quantity").val(quantity - 1);
            }
        });
    });

    function addToCart() {
        // Kiểm tra điều kiện nếu người dùng đã mua hết hàng

        var sl = ${mu.soLuong};
        if (sl === 0) { // Thay 'hasNoStock' bằng điều kiện kiểm tra tương ứng của bạn
            // Vô hiệu hóa nút "Thêm vào giỏ hàng"
            document.getElementById("addToCartBtn").disabled = true;
        } else {
            document.getElementById("addToCartBtn").disabled = false;

        }
    }

    function checkQuantity() {
        var quantityInput = document.getElementById("quantity");
        var quantityValue = Number(quantityInput.value);
        var submitButton = document.getElementById("addToCartBtn");
        var sl = ${mu.soLuong};
        var slInt = parseInt(sl.value);

        // Kiểm tra điều kiện số lượng
        if (quantityValue < 1 || quantityValue > slInt || isNaN(quantityValue) ) { // Thay 'maxQuantity' bằng số lượng cho phép tối đa
            // Vô hiệu hóa nút gửi
            submitButton.disabled = true;
        } else {
            // Kích hoạt nút gửi
            submitButton.disabled = false;
        }
    }

</script>
<script>
    window.addEventListener('pageshow', function (event) {
        // Kiểm tra nếu trang được load lại từ cache
        if (event.persisted) {
            // Tải lại trang
            window.location.reload();
        }
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
