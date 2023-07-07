package com.example.demo;

//import com.example.demo.Assignment.Congifg.SecurityConfig;
import com.example.demo.Assignment.Congifg.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfig.class}) // duong dan anh
@ServletComponentScan
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}
