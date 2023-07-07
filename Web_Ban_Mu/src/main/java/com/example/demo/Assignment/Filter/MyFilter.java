package com.example.demo.Assignment.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = {"/*"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI();

        // Lấy thông tin người dùng đã đăng nhập (ví dụ: thông qua session)
        if (httpRequest.getSession().getAttribute("loggedInUser") == null) {
            // Người dùng chưa đăng nhập
            if (url.contains("/add-san-pham-gh") || url.contains("/xem-hoa-don") || url.contains("/thanh-toan")
                    || url.contains("/gio-hang") || url.contains("/dangxuat") || url.contains("/test")
                    || url.contains("/index")) {
                httpResponse.sendRedirect("/login");
                return;
            }
        } else {
            // Người dùng đã đăng nhập
            Integer role = (Integer) httpRequest.getSession().getAttribute("role");
            if (role != null && role.equals(1) && url.contains("/admin/index")) {
                // Người dùng có quyền user k duoc vao
                httpResponse.sendRedirect("/erorr");
                return;
            } else if (role != null && role.equals(1) && url.equals("/huydqph27425/Ass/admin/test")) {
                // Người dùng không có quyền admin không được truy cập vào trang khuyến mãi
                httpResponse.sendRedirect("/erorr"); // Chuyển hướng đến trang thông báo lỗi
                return;
            }
        }
        chain.doFilter(request, response);
        // Nếu không nằm trong các điều kiện trên, cho phép truy cập bình thường

    }


}
