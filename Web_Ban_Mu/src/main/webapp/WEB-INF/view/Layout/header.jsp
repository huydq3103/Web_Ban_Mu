<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary justify-content-sapce-evently">
    <div class="container-fluid" style="margin-top: 20px">
        <a class="navbar-brand" href="#">MU STORE</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-fill w-50">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/huydqph27425/Ass">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/thong-ke">Thong ke</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/thong-ke">Gioi thieu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/thong-ke">Lien he</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/huydqph27425/Ass/admin/test">khuyen mai</a>
                </li>
                <c:if test="${loggedInUser != null}">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="true">
                            <i class="fa-solid fa-user fa-bounce fa-lg">${loggedInUser}</i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/dangxuat">Dang xuat</a></li>
                            <li><a class="dropdown-item" href="/xem-hoa-don">Xem hao don</a></li>
                            <li><a class="dropdown-item" href="/profile">Profile</a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${loggedInUser == null}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/login"><i
                                class="fa-solid fa-user fa-bounce fa-lg"></i></a>
                    </li>

                </c:if>


            </ul>
            <form class="d-flex" role="search" style="float: right">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
                       style="width: 200px">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <ul class="navbar-nav nav-fill">
                <c:if test="${sessionScope.gioHangCount != 0}">
                    <li class="nav-item">
                        <a class="nav-link" href="/huydqph27425/Ass/gio-hang" style="margin-right: -300px">
                            <i class="fa-solid fa-cart-shopping fa-spin fa-spin-reverse fa-xl"></i>
                            <span class="badge badge-danger">${sessionScope.gioHangCount}</span>
                            <!-- Số lượng sản phẩm trong giỏ hàng -->
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.gioHangCount == 0}">
                    <li class="nav-item">
                        <a class="nav-link" href="/huydqph27425/Ass/gio-hang" style="margin-right: -300px">
                            <i class="fa-solid fa-cart-shopping fa-spin fa-spin-reverse fa-xl"></i>
                            <span class="badge badge-danger">0</span>
                            <!-- Số lượng sản phẩm trong giỏ hàng -->
                        </a>
                    </li>
                </c:if>

            </ul>

        </div>
    </div>
</nav>
