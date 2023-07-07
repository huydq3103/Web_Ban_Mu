package com.example.demo.Assignment.Controller;

import com.example.demo.Assignment.CustomModel.GioHangChiTietCustom;
import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Model.HoaDon;
import com.example.demo.Assignment.Model.HoaDonChiTiet;
import com.example.demo.Assignment.Model.Mu;
import com.example.demo.Assignment.Service.IGioHangServIce;
import com.example.demo.Assignment.Service.IHoaDonSerVice;
import com.example.demo.Assignment.Service.ILogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class HoaDonCOntroller {

    @Autowired
    private IHoaDonSerVice hoaDonSerVice;

    @Autowired
    private ILogin login;

    @Autowired
    private IGioHangServIce GHservIce;

    @GetMapping("/form-thanh-toan/{tongtien}/{idGh}")
    public String thanhToan(Model model, @PathVariable("tongtien") BigDecimal tongtien
            , @PathVariable("idGh") Integer idGh) {
        model.addAttribute("tongtien", tongtien);
        model.addAttribute("idGh", idGh);
        return "/Assignment/formThanhToan";
    }


    @GetMapping("/xem-hoa-don")
    public String xemHoaDOn(Model model, HttpSession session) {
        String user = (String) session.getAttribute("loggedInUser");
        Account account = login.getOne(user);
        List<HoaDon> hd = hoaDonSerVice.getOneHD(account.getId());
        model.addAttribute("hd", hd);
        return "/Assignment/HoaDon";
    }

    @GetMapping("/xem-chi-tiet-hoa-don/{maHD}")
    public String xemHoaDOnCT(Model model,@PathVariable("maHD") String maHD) {

        List<HoaDonChiTiet> hd = hoaDonSerVice.xemHoaDon(maHD);
        BigDecimal tongtien = hoaDonSerVice.tongTienHD(hd);
        model.addAttribute("hd", hd);
        model.addAttribute("tongtien",tongtien);
        return "/Assignment/HoaDonChietiet";
    }


    @PostMapping("/thanh-toan")
    public String thanhToan(@RequestParam("diaChi") String diaChi,
                            @RequestParam("tenNguoiNhan") String tenNguoiNhan,
                            @RequestParam("sdt") String sdt,
                            @RequestParam("idgh") Integer idgh,
                            @RequestParam("soTienCanThanhToan") BigDecimal soTienCanThanhToan,
                            HttpSession session, RedirectAttributes redirectAttributes) {
        String user = (String) session.getAttribute("loggedInUser");

        Account account = login.getOne(user);

        HoaDon CReateoaDon = hoaDonSerVice.createHoaDon(account, tenNguoiNhan, diaChi, sdt);

//         String maHd = CReateoaDon.getMaHD();
//         HoaDon hoaDon1 = hoaDonSerVice.getOneHD(maHd);

        // them vao hoa don chi tiet

        List<GioHangChiTietCustom> gioHangChiTietCustom = GHservIce.getGioHang(idgh);
        gioHangChiTietCustom.stream().map(ghct -> {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            Mu mu = new Mu();
            mu.setSoLuong(ghct.getSoLuong());
            mu.setDonGia(ghct.getDonGia());
            mu.setChatLieu(ghct.getChatLieu());
            mu.setXuatsu(ghct.getXuatsu());
            mu.setTen(ghct.getTen());
            mu.setMa(ghct.getMa());
            hoaDonChiTiet.setMu(mu);
            hoaDonChiTiet.setDonGia(ghct.getDonGia());
            hoaDonChiTiet.setSoLuong(ghct.getSoLuong());
            hoaDonChiTiet.setHoadon(CReateoaDon);
            return hoaDonSerVice.createHoaDonCT(hoaDonChiTiet);
        }).collect(Collectors.toList());

        // xoa san pham trong gio hang khi thanh toan xong
        GHservIce.xoaGhKhiThanhToan(idgh);
        // xoa bo dem san pham trong gio hang
        List<Object[]> list = GHservIce.soluongSpGh(user);
        Integer gioHangCount = ((Number) list.get(0)[0]).intValue();
        session.setAttribute("gioHangCount", gioHangCount);
       // messa thong bao thanh toan thanh cong
        redirectAttributes.addFlashAttribute("success","Thanh toan thanh cong");
        return "redirect:/huydqph27425/Ass/gio-hang";
    }
}
