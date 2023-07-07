package com.example.demo.Assignment.Controller;

import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Service.EmailSenderService;
import com.example.demo.Assignment.Service.IGioHangServIce;
import com.example.demo.Assignment.Service.ILogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
public class UserController {

    @Autowired
    private ILogin service;

    @Autowired
    private EmailSenderService Mailservice;

    @Autowired
    private IGioHangServIce GHservIce;



//    @Autowired
//    private AuthenticationManager authenticationManager;
    @GetMapping("/login")
    public String login() {
        return "/Assignment/Login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password

            , Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        // Xử lý logic đăng nhập
        Account account = service.checkAccount(username, password);

        if (account != null) {
            List<Object[]> list = GHservIce.soluongSpGh(username);
            Integer gioHangCount = ((Number) list.get(0)[0]).intValue();
            session.setAttribute("role",account.getRole());
            session.setAttribute("gioHangCount", gioHangCount);
            model.addAttribute("loggedInUser", username);


            return "redirect:/huydqph27425/Ass";
        } else {
            redirectAttributes.addFlashAttribute("error", "Thông tin đăng nhập không chính xác");
            return "redirect:/login";
        }
        // Lưu thông tin người dùng đã đăng nhập vào session


    }


    @GetMapping("/dangxuat")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Hủy bỏ session hiện tại
        session.removeAttribute("loggedInUser");
        session.removeAttribute("role");
        String message = "Đăng xuất thành công";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/login";
    }

    @GetMapping("/form-dang-ki")
    public String formdangki() {

        return "Assignment/DangKi";
    }

    @GetMapping("/quen-mat-khau")
    public String formquenMK() {
        return "Assignment/quen-mat-khau";
    }

    @PostMapping("/dang-ki")
    public String dangki(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("mail") String mail,
                         RedirectAttributes model) {
        Account account = service.finbymail(mail);
        if (account != null) {
            model.addFlashAttribute("fail", "Mail da ton tai ");
            return "redirect:/form-dang-ki";
        } else {
            Integer role = 1;
            Account NewAccount = new Account(username, password, role, mail);
            service.createAccount(NewAccount);

            // Thêm thông điệp thành công vào Model
            model.addFlashAttribute("successMessage", "Đăng ký thành công!");

            return "redirect:/login";
        }
    }

    // Hàm gửi email


    @PostMapping("/forgot-password")
    public String quenMatKhau(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {

        Account account = service.finbymail(email);
        if (account != null) {
            String username = account.getUserName();
            String pass = account.getPassword();
            // Gửi email chứa thông tin đặt lại mật khẩu
            String subject = "Yêu cầu đặt lại mật khẩu";
            String text = "Xin chào,\n\nBạn đã yêu cầu đặt lại mật khẩu." +
                    " Vui lòng sử dụng thông tin sau để đặt lại mật khẩu của bạn:" +
                    "\n\nTên đăng nhập: " + username + "\nMật khẩu mới: " + pass + "" +
                    "\n\nTrân trọng,\nNhóm hỗ trợ";

            Mailservice.sendEmail(email, subject, text);
            String mess = "Yêu cầu lấy lại mật khẩu thành công ? Vui lòng kiểm tra mail";
            redirectAttributes.addFlashAttribute("successMessage", mess);
            return "redirect:/login";
        } else {
            String mess = "Thong tin ban nhap khong ton tai ? Vui long thu lai sau";
            redirectAttributes.addFlashAttribute("FailMessage", mess);
            return "redirect:/quen-mat-khau";
        }


    }


    @GetMapping("/profile")
    public String proFile(Model model, HttpSession session) {
        String user = (String) session.getAttribute("loggedInUser");
        Account account = service.getOne(user);
        model.addAttribute("account", account);
        return "Assignment/Profile";
    }

    @PostMapping("/update-profile")
    public String updateTaiKHoan(@RequestParam("mail") String mail,
                                 @RequestParam("password") String password, HttpSession session
            , RedirectAttributes redirectAttributes) {
        String user = (String) session.getAttribute("loggedInUser");
        Account getaccount = service.getOne(user);

        Account account = new Account(mail, password, getaccount.getId(), 1, getaccount.getUserName());
        service.createAccount(account);

        redirectAttributes.addFlashAttribute("success", "Udapte thanh cong");

        return "redirect:/profile";
    }
}
