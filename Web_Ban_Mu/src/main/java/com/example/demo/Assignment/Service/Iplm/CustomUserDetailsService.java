//package com.example.demo.Assignment.Service.Iplm;
//
//import com.example.demo.Assignment.Model.Account;
//import com.example.demo.Assignment.Service.ILogin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private ILogin loginService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Lấy thông tin người dùng từ nguồn dữ liệu (ví dụ: cơ sở dữ liệu)
//        Account account = loginService.getOne(username);
//        if (account == null) {
//            throw new UsernameNotFoundException("Người dùng không tồn tại");
//        }
//
//        // Lấy mật khẩu từ người dùng đã được truyền vào từ phương thức đăng nhập
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String passwordFromUserInput = (String) authentication.getCredentials();
//
//        // Kiểm tra mật khẩu
//        // Kiểm tra mật khẩu
//        String passwordFromDatabase = account.getPassword();
//        if (!passwordFromUserInput.equals(passwordFromDatabase)) {
//            throw new BadCredentialsException("Mật khẩu không chính xác");
//        }
//
//
//        // Tạo đối tượng UserDetails từ thông tin người dùng
//        String role = String.valueOf(account.getRole());
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role));
//
//        return new User(account.getUserName(), account.getPassword(), authorities);
//    }
//
//
//}
