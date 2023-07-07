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
    <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>Title</title>
    <style>
        .error-message {
            color: red;
        }

        #form {
            margin-left: 300px;
            margin-top: 50px;
        }

        #btn-add {
            width: 150px;
            margin-left: 200px;
        }

        #submit {
            width: 150px;
            margin-left: 500px;
            margin-top: 50px;
            margin-bottom: 50px;
        }

        /* Thay đổi font chữ cho menu */
        .navbar-nav .nav-link {
            font-family: Muli, sans-serif;
            color: #000000;
            font-size: large;
            font-style: normal; /* Thay thế "Your-Font-Family" bằng tên font chữ mong muốn */
        }

        .pagination .page-link.active {
            background-color: #FF66CC;

        }

        .pagination .page-link {
            color: #000000;
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

<div class="container">
    <div class="row">
        <c:forEach items="${muList}" var="sp">
            <div class="col-lg-3" style="margin-top: 40px">
                <a href="/huydqph27425/Ass/details/${sp.ma}">
                    <div class="card" style="width: 15rem;">
                        <img src="/image/${sp.images}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <c:set var="found" value="false"/>
                            <c:forEach items="${sessionScope.hangTonMoi}" var="sp2">
                                <c:if test="${sp.ma eq sp2[0]}">
                                <a href="/huydqph27425/Ass/details/${sp.ma}/${sp2[1]}">
                                    <p class="card-text" style="color: red">
                                        <s>${sp.donGia}</s> <span style="color: black">đ</span>-giam gia:${sp2[2]}%<br>
                                            ${sp2[1]} <span style="color: red">đ</span>
                                    </p>
                                </a>
                                    <c:set var="found" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${not empty sessionScope.hangTonMoi && not found or sessionScope.hangTonMoi == null }">
                                <p class="card-text" style="color: red">
                                        ${sp.donGia} <span style="color: red">đ</span>
                                </p>
                            </c:if>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>


    </div>
    <nav aria-label="Page navigation example" style="margin-top: 30px">
        <ul class="pagination justify-content-center" style="color: black;">
            <li class="page-item">
                <a class="page-link" href="#" id="Previous" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${toTal}" var="l">
                <li class="page-item">
                    <a  class="page-link" href="/huydqph27425/Ass/page?pageNo=${l}">${l}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="#" id="nextButton" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>


<!-- Footer -->
<%@include file="../Layout/footer.jsp" %>
<!-- Footer -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    window.addEventListener('pageshow', function (event) {
        // Kiểm tra nếu trang được load lại từ cache
        if (event.persisted) {
            // Tải lại trang
            window.location.reload();
        }
    });
</script>

<script>
    var total = ${toTal};
</script>
<script>


    var urlParams = new URLSearchParams(window.location.search);
    var pageNo = parseInt(urlParams.get("pageNo")) || 0;

    var total2 = parseInt(total);
    var nextButton = document.getElementById("nextButton");
    var Previous = document.getElementById("Previous");
    var pageLinks = document.querySelectorAll('.pagination .page-link');

    nextButton.addEventListener("click", function (e) {
        e.preventDefault();
        pageNo += 1;
        var newUrl = "/huydqph27425/Ass/page?pageNo=" + pageNo;
        if (pageNo > total2) {
            nextButton.setAttribute("disabled", "disabled");
        } else {
            nextButton.removeAttribute("disabled");
            window.location.href = newUrl;
        }
    });

    Previous.addEventListener("click", function (x) {
        x.preventDefault();
        if (pageNo > 0) {
            pageNo = pageNo - 1;
            var newUrl = "/huydqph27425/Ass/page?pageNo=" + pageNo;
            Previous.removeAttribute("disabled");
            window.location.href = newUrl;
        } else {
            Previous.setAttribute("disabled", "disabled");
        }
    });

    pageLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            pageLinks.forEach(function (link) {
                link.classList.remove('active');
            });
            this.classList.add('active');
            var newUrl = this.getAttribute('href');
            window.location.href = newUrl;
        });

        link.addEventListener('mouseover', function () {
            this.style.color = 'blue'; // Đổi màu sắc khi di chuột qua
        });

        link.addEventListener('mouseout', function () {
            // Kiểm tra và lưu trạng thái hover
            if (this.classList.contains('active')) {
                localStorage.setItem('activePage', this.getAttribute('href'));
            }
            this.style.color = 'black'; // Đổi lại màu sắc khi di chuột ra
        });
    });

    // Khôi phục trạng thái hover sau khi tải trang
    var activePage = localStorage.getItem('activePage');
    if (activePage) {
        var activeLink = document.querySelector('.pagination .page-link[href="' + activePage + '"]');
        if (activeLink) {
            activeLink.classList.add('active');
            activeLink.style.color = 'blue';
        }
    }



</script>

</body>
</html>
