//package com.example.demo.Assignment.Congifg;
//
//import com.example.demo.Assignment.Service.ILogin;
//import com.example.demo.Assignment.Service.Iplm.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/huydqph27425/Ass").authenticated() // Yêu cầu xác thực để truy cập vào "/huydqph27425/Ass"
//                .anyRequest().permitAll() // Cho phép truy cập tự do vào các trang khác
//                .and()
//                .formLogin()
//                .loginPage("/login") // Đường dẫn tới trang đăng nhập
//                .defaultSuccessUrl("/huydqph27425/Ass") // Đường dẫn sau khi đăng nhập thành công
//                .failureUrl("/login?error") // Đường dẫn sau khi đăng nhập thất bại
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//}
//
