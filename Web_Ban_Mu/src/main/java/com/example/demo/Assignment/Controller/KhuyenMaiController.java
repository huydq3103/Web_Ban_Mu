package com.example.demo.Assignment.Controller;

import com.example.demo.Assignment.Model.Mu;
import com.example.demo.Assignment.Service.IHoaDonSerVice;
import com.example.demo.Assignment.Service.IMuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/huydqph27425/Ass/admin/")
public class KhuyenMaiController {
    @Autowired
    private IMuService service;
    @Autowired
    private IHoaDonSerVice hoaDonSerVice;
    @Autowired
    private IMuService Muservice;
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/test")
    public String test() {
        return "Assignment/Form-Khuyen_mai";
    }

    @PostMapping("/active-khuyen-mai")
    public String test2(@RequestParam(value = "promoCode", defaultValue = "") String maKM,
                        @RequestParam(value = "discountPercentage", defaultValue = "") Integer giamGia,
                        @RequestParam("toggleState") String status, HttpServletRequest request, Model model) {
        List<Object[]> hangTon = hoaDonSerVice.hangTon();
        List<Mu> listSp = Muservice.getAll();
        HttpSession session = request.getSession();
        List<Object[]> hangTonMoi = (List<Object[]>) session.getAttribute("hangTonMoi");

        if (hangTon != null) {
            if (maKM != null && giamGia != null && status.equals("on")) {
                hangTonMoi = new ArrayList<>();
                for (Object[] row : hangTon) {
                    Integer maSp = (Integer) row[1];
                    for (Mu m : listSp) {
                        if (m.getMa().equals(maSp)) {
                            BigDecimal giacu = m.getDonGia();
                            BigDecimal giamoi = giacu.subtract(giacu.multiply(BigDecimal.valueOf(giamGia)).divide(BigDecimal.valueOf(100)));

                            Object[] newRow = new Object[3];
                            newRow[0] = row[1];
                            newRow[1] = giamoi;
                            newRow[2] = giamGia;
                            hangTonMoi.add(newRow);
                        }
                    }
                }
                session.setAttribute("hangTonMoi", hangTonMoi);
                model.addAttribute("hangTon", hangTonMoi); // Thêm hangTonMoi vào model
                return "redirect:/huydqph27425/Ass";
            }
            if (maKM != null && giamGia != null && status.equals("")) {
                session.setAttribute("hangTonMoi", null);
                model.addAttribute("hangTon", null); // Thêm giá trị null vào model
            }
        }

        return "redirect:/huydqph27425/Ass";
    }


}
