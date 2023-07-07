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
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body>
<p style="margin-top: 50px;text-align: center;font-size: 20px"> QUẢN LÝ MŨ</p>
<div class="container" id="form">
    <div class="row">
        <div class="col-sm-8">
            <form:form id="my-form" method="post" action="/huydqph27425/Ass/add" modelAttribute="Mu">
                <div class="form-group row">
                    <label for="ma" class="col-sm-3 col-form-label">Ma :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="ma" name="ma"/>
                        <form:errors path="ma" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="ten" class="col-sm-3 col-form-label">Ten :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="ten" name="ten"/>
                        <form:errors path="ten" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="soLuong" class="col-sm-3 col-form-label">So luong :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="soLuong" name="soLuong"/>
                        <form:errors path="soLuong" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="donGia" class="col-sm-3 col-form-label">Gia :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="donGia" name="donGia"/>
                        <form:errors path="donGia" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="xuatsu" class="col-sm-3 col-form-label">Xuat su:</label>
                    <div class="col-sm-9">
                        <select class="form-control" id="xuatsu" name="xuatsu">
                            <option value="Viet Nam">Viet Nam</option>
                            <option value="Mi">Mi</option>
                            <option value="Duc">Duc</option>
                            <option value="Trung Quoc">Trung Quoc</option>
                        </select>
                        <form:errors path="xuatsu" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Chat lieu :</label>
                    <div class="col-sm-9">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="chatLieu" id="cotton" value="Cotton"/>
                            <label class="form-check-label" for="cotton">Cotton</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="chatLieu" id="cat" value="Cat"/>
                            <label class="form-check-label" for="cat">Cat</label>
                        </div>
                        <form:errors path="chatLieu" cssClass="error-message"/>
                    </div>
                </div>
                <div class="sm-9">
                    <label class="col-sm-3 col-form-label">Anh</label>
                    <input class="form-control" type="file" id="formFileMultiple" name="anh" multiple>
                </div>

                <div class="form-group row">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="button" onclick="ConfirmAdd()" class="btn btn-primary" id="btn-add">Them</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>


<div class="container">
    <form class="row gx-3 gy-2 align-items-center" id="myForm" method="get">
        <div class="col-sm-3">
            <label class="visually-hidden" for="specificSizeInputName">Name</label>
            <input type="text" class="search-input" id="specificSizeInputName" name="name" value="${param.name}">
        </div>

        <select class="form-select" id="Selected" name="Xuatsu">
            <option value="">Choose...</option>
            <option selected value="Viet Nam" <c:if test="${selectedValue == 'Viet Nam'}">selected</c:if>>Viet Nam
            </option>
            <option value="Trung Quoc" <c:if test="${selectedValue == 'Trung Quoc'}">selected</c:if>>Trung Quoc</option>
        </select>


        <div class="col-sm-3">
            <label class="visually-hidden">Gia min</label>
            <input type="text" class="search-input" id="Min-Price" name="min" value="${param.min}">
        </div>
        <div class="col-sm-3">
            <label class="visually-hidden">Gia max</label>
            <input type="text" class="search-input" id="Max-price" name="max" value="${param.max}">
        </div>

        <div class="col-auto">
            <button type="submit" id="submitButton" class="btn btn-primary">Tim kiem
            </button>
        </div>
    </form>
</div>


<c:if test="${not empty muList}">
    <table class="table table-Success table-hover">
        <thead>
        <th>Ma</th>
        <th>Ten</th>
        <th>So luong</th>
        <th>Gia</th>
        <th>Xuat su</th>
        <th>Chat lieu</th>
        <th>Anh</th>
        <th>Action</th>
        </thead>
        <tbody id="muListTableBody">
        <c:forEach items="${muList}" var="gv">
            <tr>
                <td class="table-light">${gv.ma}</td>
                <td class="table-light">${gv.ten}</td>
                <td class="table-light">${gv.soLuong}</td>
                <td class="table-light">${gv.donGia}</td>
                <td class="table-light">${gv.xuatsu}</td>
                <td class="table-light">${gv.chatLieu}</td>
                <td class="table-light"><img src="/image/${gv.images}"></td>
                <td class="table-light">
                    <a href="#" onclick="event.preventDefault(); ConfirmXoa('${gv.ma}')">
                        <button class="btn btn-danger">Xoa</button>
                    </a>

                    <button class="btn btn-success" onclick="window.location.href='/huydqph27425/Ass/edit/${gv.ma}'">
                        Xem
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty muList}">
    <p>Khong co du lieu </p>
</c:if>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${toTal > 0}">
            <li class="page-item active">
                <a class="page-link" href="/huydqph27425/Ass/search?pageNo=0">First</a>
            </li>
            <c:forEach begin="1" end="${toTal}" var="l">
                <li class="page-item">
                    <a class="page-link" href="/huydqph27425/Ass/search?pageNo=${l}">${l}</a>
                </li>
            </c:forEach>
            <li class="page-item">

                <a class="page-link" href="#" id="nextButton">Next</a>

            </li>
        </c:if>
        <c:if test="${toTal <= 0}">
            <li class="page-item">
                <a class="page-link" href="/huydqph27425/Ass/search?pageNo=0">Quay Về</a>
            </li>
        </c:if>
    </ul>
</nav>


<script>


    $(document).ready(function () {
        var successMessage = "${Success}";
        var failMessage = "${Fail}";

        var showMessage = function (message) {
            if (message !== "") {
                alert(message);
                return true;
            }
            return false;
        };

        if (successMessage) {
            showMessage(successMessage);
            successMessage = ""; // Set giá trị thành rỗng
        } else if (failMessage) {
            showMessage(failMessage);
        }

    });


    function ConfirmAdd() {
        if (confirm("Ban co chac chan muon them san pham nay khong ?")) {
            document.getElementById("my-form").submit();
        } else {
            alert("cam on ban da huy")
            return;
        }
    }

    function ConfirmXoa(id) {
        if (confirm("Ban co chac chan muon xoa san pham nay khong ?")) {
            window.location.href = "/huydqph27425/Ass/xoa/" + id;
        } else {
            alert("cam on ban da huy")
            return;
        }
    }


</script>

<script>
    var total = ${toTal};
</script>
<script>


    var urlParams = new URLSearchParams(window.location.search);
    var pageNo = parseInt(urlParams.get("pageNo")) || 0;

    var total2 = parseInt(total);
    var nextButton = document.getElementById("nextButton");


    nextButton.addEventListener("click", function (e) {
        e.preventDefault();

        pageNo += 1;

        var newUrl = "/huydqph27425/Ass/search?pageNo=" + pageNo;
        if (pageNo > total2) {
            nextButton.setAttribute("disabled", "disabled");

        } else {
            nextButton.removeAttribute("disabled");
            window.location.href = newUrl;
        }

    });


</script>

<script>
    // Lấy phần tử select bằng id
    var selectElement = document.getElementById('Selected'); // value trung quoc
    var nameInput = document.getElementById('specificSizeInputName');


    // Bắt sự kiện thay đổi giá trị
    selectElement.addEventListener('change', function () {
        // Lấy giá trị đã chọn
        var selectedValue = selectElement.value; // selectedValue = trung quoc

        // Gán giá trị của ô input vào hidden input

        // Thực hiện chuyển hướng trang
        if (selectedValue === "") {
            window.location.href = "/huydqph27425/Ass/search";
        } else {
            window.location.href = "/huydqph27425/Ass/search?Xuatsu=" + selectedValue;
        }
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


</body>
</html>
