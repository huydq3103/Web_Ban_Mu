<!DOCTYPE html>
<html>
<head>
    <title>Trang tạo mã khuyến mãi và nút bật/tắt</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        .active {
            background-color: green;
            color: white;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1>Chương trình khuyến mãi</h1>

    <div class="mb-3">
        <label for="promoCode">Mã khuyến mãi:</label>
        <span id="promoCode"></span>
    </div>

    <div class="mb-3">
        <button id="generateButton" class="btn btn-primary">Tạo mã khuyến mãi</button>
    </div>

    <div class="mb-3">
        <label for="discountPercentage">Phần trăm giảm giá:</label>
        <select id="discountPercentage" class="form-control">
            <option value="10">10%</option>
            <option value="20">20%</option>
            <option value="30">30%</option>
        </select>
    </div>

    <div class="mb-3">
        <button id="toggleButton" class="btn btn-danger">On/off</button>
    </div>
</div>
<script>
    $(document).ready(function () {
        var toggleState = sessionStorage.getItem("toggleState");
        if (toggleState === "on") {
            $("#toggleButton").addClass("active");
        }

        $("#toggleButton").click(function () {
            $(this).toggleClass("active");
            if ($(this).hasClass("active")) {
                sessionStorage.setItem("toggleState", "on");
                sendData();
            } else {
                sessionStorage.setItem("toggleState", "");
                sendData();
            }
        });

        $("#generateButton").click(function () {
            var promoCode = generatePromoCode();
            $("#promoCode").text(promoCode);
        });

        function generatePromoCode() {
            var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            var length = 10;
            var promoCode = '';

            for (var i = 0; i < length; i++) {
                var randomIndex = Math.floor(Math.random() * characters.length);
                promoCode += characters.charAt(randomIndex);
            }

            return promoCode;
        }


        function sendData() {
            var promoCode = $("#promoCode").text();
            var discountPercentage = $("#discountPercentage").val();

            // Kiểm tra trạng thái nút bật/tắt
            var toggleState = $("#toggleButton").hasClass("active") ? "on" : "";

            // Gửi dữ liệu đi, bao gồm trạng thái nút bật/tắt
            $.ajax({
                type: "POST",
                url: "/huydqph27425/Ass/admin/active-khuyen-mai",
                data: {
                    promoCode: promoCode,
                    discountPercentage: discountPercentage,
                    toggleState: toggleState
                },
                success: function (response) {
                    console.log("Dữ liệu đã được gửi đi thành công.");
                    console.log(discountPercentage);
                    console.log(promoCode);
                    console.log(toggleState);
                },
                error: function (xhr, status, error) {
                    console.log("Có lỗi xảy ra khi gửi dữ liệu.");
                }
            });
        }
    });


</script>
<script>
    var button = document.getElementById("toggleButton");
button.addEventListener("click", function () {
    // Khi nút được nhấn, kiểm tra lớp CSS hiện tại
    if (button.classList.contains("btn-danger")) {
        // Nếu nút đang có lớp CSS "btn-success", thì chuyển sang lớp CSS "btn-danger"
        button.classList.remove("btn-danger");
        button.classList.add("btn-success");
    } else {
        // Ngược lại, nếu nút không có lớp CSS "btn-success", thì chuyển về lớp CSS "btn-success"
        button.classList.remove("btn-success");
        button.classList.add("btn-danger");
    }
});</script>
</body>
</html>
