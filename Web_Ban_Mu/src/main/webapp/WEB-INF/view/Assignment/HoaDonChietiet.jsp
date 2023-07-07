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
        <th>Don gia</th>

    </tr>

    </thead>
    <tbody>


    <c:forEach items="${hd}" var="hd">
        <tr>
            <td>${hd.mu.ten}</td>
            <td>${hd.mu.xuatsu}</td>
            <td>${hd.mu.chatLieu}</td>
            <td>${hd.soLuong}</td>
            <td>${hd.donGia}</td>
        </tr>

    </c:forEach>
    <tr>
        <TH></TH>
        <TH></TH>
        <TH></TH>
        <TH></TH>
        <TH>TỔNG TIỀN HÓA ĐƠN</TH>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>${tongtien}</td>
    </tr>

    </tbody>

</table>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
