package com.example.demo.Assignment.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

      @GetMapping("/erorr")
      public String toString() {

          return "/Assignment/ErrorPage";
      }
}
