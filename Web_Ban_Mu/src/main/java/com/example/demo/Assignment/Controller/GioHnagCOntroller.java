package com.example.demo.Assignment.Controller;

import com.example.demo.Assignment.CustomModel.GioHangChiTietCustom;
import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Model.GioHang;
import com.example.demo.Assignment.Model.GioHangChiTIet;
import com.example.demo.Assignment.Model.Mu;
import com.example.demo.Assignment.Service.IGioHangServIce;
import com.example.demo.Assignment.Service.ILogin;
import com.example.demo.Assignment.Service.IMuService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/huydqph27425/Ass")
public class GioHnagCOntroller {
    @Autowired
    private IGioHangServIce servIce;
    @Autowired
    private ILogin login;
    @Autowired
    private IMuService Muservice;

    @GetMapping("/gio-hang")
    public String indexGH(Model model, HttpSession session) {
        String user = (String) session.getAttribute("loggedInUser");

        Account account = login.getOne(user);
        Integer idUSer = account.getId();
        List<GioHangChiTietCustom> listGH = servIce.xemGioHang(idUSer);
        BigDecimal tontien = servIce.tinhTongTien(idUSer);

        model.addAttribute("listGH", listGH);
        model.addAttribute("tontien", tontien);

        return "/Assignment/GioHang";
    }

    @PostMapping("/add-san-pham-gh")
    public String addSPGH(HttpSession session, @RequestParam("ma") Integer ma,
                          @RequestParam("soLuong") Integer soLuong,
                          @RequestParam(value = "giaGiam", defaultValue = "0")
                              BigDecimal giaMoi, RedirectAttributes redirectAttributes) {

        String user = (String) session.getAttribute("loggedInUser");

        Account account = login.getOne(user);
        Integer idAccount = account.getId();

        Mu mu = Muservice.getMu(ma);

        GioHang gioHang = servIce.CheckGioHang(idAccount);

        if (mu.getSoLuong() < soLuong) {
            redirectAttributes.addFlashAttribute("message", "so luong qua yeu cau");
            return "redirect:/huydqph27425/Ass/details/" + ma;

        }

        if (gioHang == null) {
            gioHang = servIce.createGH(user, account); // Tạo mới giỏ hàng và gán lại giá trị cho gioHang


            servIce.themSanPham(ma, soLuong, gioHang, mu, giaMoi); // Thêm sản phẩm vào giỏ hàng

            List<Object[]> list = servIce.soluongSpGh(account.getUserName());
            Integer gioHangCount = ((Number) list.get(0)[0]).intValue();
            session.setAttribute("gioHangCount", gioHangCount);
            return "redirect:/huydqph27425/Ass/gio-hang";
        } else {

            servIce.themSanPham(ma, soLuong, gioHang, mu, giaMoi); // Thêm sản phẩm vào giỏ hàng

            List<Object[]> list = servIce.soluongSpGh(account.getUserName());
            Integer gioHangCount = ((Number) list.get(0)[0]).intValue();
            session.setAttribute("gioHangCount", gioHangCount);
            return "redirect:/huydqph27425/Ass/gio-hang";
        }


    }

    @GetMapping("/xoa-san-pham-gh/{ma}")
    public String xoa(@PathVariable("ma") Integer ma, HttpSession session) {
        String user = (String) session.getAttribute("loggedInUser");
        Account account = login.getOne(user);
        Integer idAccount = account.getId();


        GioHang gioHang = servIce.CheckGioHang(idAccount);
        //xoa san pham
        servIce.xoaSpGh(ma, gioHang);
        // cap nhat lai bo dem số lượng
        List<Object[]> list = servIce.soluongSpGh(account.getUserName());
        Integer gioHangCount = ((Number) list.get(0)[0]).intValue();
        session.setAttribute("gioHangCount", gioHangCount);

        return "redirect:/huydqph27425/Ass/gio-hang";
    }

    @GetMapping("/bot-san-pham-gh/{ma}/{soluong}")
    public String bot(Model model, @PathVariable("ma") Integer ma,
                      @PathVariable("soluong") Integer soLuong, HttpSession session) {
        String user = (String) session.getAttribute("loggedInUser");
        Account account = login.getOne(user);
        Integer idAccount = account.getId();


        GioHang gioHang = servIce.CheckGioHang(idAccount);
        servIce.botSpGh(ma, soLuong, gioHang);
        return "redirect:/huydqph27425/Ass/gio-hang";
    }


}
