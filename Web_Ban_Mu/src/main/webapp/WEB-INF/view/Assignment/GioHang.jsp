<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 05/10/2023
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        /* Thay đổi font chữ cho menu */
        .navbar-nav .nav-link {
            font-family: "Your-Font-Family", sans-serif; /* Thay thế "Your-Font-Family" bằng tên font chữ mong muốn */
        }
    </style>
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


<table class="table caption-top">
    <thead>
    <tr>
        <th>TÊN SẢN PHẨM</th>
        <th>XUẤT SỨ</th>
        <th>CHẤT LIỆU</th>
        <TH>SỐ LƯỢNG</TH>
        <TH>HÌNH ẢNH</TH>
        <TH>ACtion</TH>
    </tr>

    </thead>
    <tbody>
    <c:set var="idGh" value=""/>

    <c:forEach items="${listGH}" var="gh">
        <tr>
            <!-- Gán giá trị cho idGh -->
            <c:set var="idGh" value="${gh.idGh}"/>
            <td>${gh.ten}</td>
            <td>${gh.xuatsu}</td>
            <td>${gh.chatLieu}</td>
            <td>${gh.soLuong}</td>
            <td><img src="/image/${gh.images}"></td>
            <td>
                <a href="/huydqph27425/Ass/xoa-san-pham-gh/${gh.ma}" class="btn btn-danger"
                   onclick="return confirmDelete()">Xóa</a>
                <a href="#" class="btn btn-warning" onclick="confirmReduceQuantity(${gh.ma},${gh.soLuong})">Bớt 1
                    phần</a>

            </td>
        </tr>
        <tr>
            <TH></TH>
            <TH></TH>
            <TH></TH>
            <TH></TH>
            <TH></TH>
            <TH>TỔNG TIỀN</TH>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>${tontien}</td>
    </tr>

    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <c:if test="${tontien==0}">
            <td><a href="/huydqph27425/Ass" class="btn btn-success">Them san pham</a></td>
        </c:if>
        <c:if test="${tontien>0}">
            <td><a href="/form-thanh-toan/${tontien}/${idGh}" class="btn btn-success"
            >Thanh Toan</a></td>
        </c:if>

    </tr>
    </tbody>

</table>
<c:if test="${not empty success}">
    <script>
        alert('${success}');
    </script>
</c:if>

<script>

    function confirmDelete() {
        return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');
    }
</script>
<script>
    function confirmReduceQuantity(ma, soLuong) {
        var confirmation = confirm("Bạn có chắc chắn muốn bớt 1 phần sản phẩm này?");

        if (confirmation) {
            var quantity = prompt("Nhập số lượng cần bớt:");

            if (quantity !== null && quantity !== "") {
                var reducedQuantity = Number(quantity); // Sử dụng Number() để chuyển đổi giá trị thành số
                var currentQuantity = parseInt(soLuong);

                if (!isNaN(reducedQuantity) && reducedQuantity <= currentQuantity && reducedQuantity > 0) {
                    // Thực hiện các xử lý bớt số lượng sản phẩm tại đây
                    // Gọi hàm hoặc thực hiện các yêu cầu AJAX tương ứng
                    // Ví dụ: redirect hoặc gửi yêu cầu POST
                    window.location.href = "/huydqph27425/Ass/bot-san-pham-gh/" + ma + "/" + reducedQuantity;
                } else {
                    alert("Số lượng không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                alert("Bạn chưa nhập số lượng. Vui lòng thử lại.");
            }
        }
    }


    // Kiểm tra khi người dùng nhấn Enter trong prompt
    function handlePromptInput(event, ma, soLuong) {
        if (event.keyCode === 13) {
            confirmReduceQuantity(ma, soLuong);
        }
    }
</script>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
