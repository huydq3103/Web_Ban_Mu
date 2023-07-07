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
        .error-message {
            color: red;
        }
        #form{
            margin-left: 300px;
            margin-top: 100px;
        }
        #btn-add{
            width: 150px;
            margin-left: 200px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<div class="container" id="form">
    <div class="row">
        <div class="col-sm-8">
            <form:form id="my-form" method="post" action="/huydqph27425/Ass/update" modelAttribute="Mu">
                <div class="form-group row">
                    <label for="ma" class="col-sm-3 col-form-label">Ma :</label>
                    <div class="col-sm-9">
                        <form:input type="text" class="form-control" id="ma" name="ma" path="ma"/>
                        <form:errors path="ma" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="ten" class="col-sm-3 col-form-label">Ten :</label>
                    <div class="col-sm-9">
                        <form:input type="text" class="form-control" id="ten" name="ten" path="ten"/>
                        <form:errors path="ten" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="soLuong" class="col-sm-3 col-form-label">So luong :</label>
                    <div class="col-sm-9">
                        <form:input type="text" class="form-control" id="soLuong" name="soLuong" path="soLuong"/>
                        <form:errors path="soLuong" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="donGia" class="col-sm-3 col-form-label">Gia :</label>
                    <div class="col-sm-9">
                        <form:input type="text" class="form-control" id="donGia" name="donGia" path="donGia"/>
                        <form:errors path="donGia" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="xuatsu" class="col-sm-3 col-form-label">Xuat su:</label>
                    <div class="col-sm-9">
                        <form:select class="form-control" id="xuatsu" name="xuatsu" path="xuatsu">
                            <form:option value="Viet Nam">Viet Nam</form:option>
                            <form:option value="Mi">Mi</form:option>
                            <form:option value="Duc">Duc</form:option>
                            <form:option value="Trung Quoc">Trung Quoc</form:option>
                        </form:select>
                        <form:errors path="xuatsu" cssClass="error-message"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Chat lieu :</label>
                    <div class="col-sm-9">
                        <div class="form-check form-check-inline">
                            <form:radiobutton class="form-check-input"  name="chatLieu" id="cotton" value="Cotton"
                                        path="chatLieu"/>
                            <label class="form-check-label" for="cotton">Cotton</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:radiobutton class="form-check-input"  name="chatLieu" id="cat" value="Cat"
                                        path="chatLieu"/>
                            <label class="form-check-label" for="cat">Cat</label>
                        </div>
                        <form:errors path="chatLieu" cssClass="error-message"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="anh">Anh:</label>
                    <input class="form-control" type="file" id="anh" name="anh" multiple>
                    <span>Anh hien tai :${anh}</span>
<%--                    <input type="hidden" name="anh" value="${anh}">--%>
                    <div id="selected-file"></div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="button" onclick="ConfirmAdd()" class="btn btn-primary" id="btn-add">Update
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

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

</script>

<script>
    var input = document.getElementById("anh");
    var output = document.getElementById("selected-file");
    input.addEventListener("change", function(e) {
        var files = e.target.files;
        var fileNames = "";
        for (var i = 0; i < files.length; i++) {
            fileNames += files[i].name + ", ";
        }
        output.innerText = fileNames;
    });
</script>
</body>
</html>
