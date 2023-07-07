package com.example.demo.Assignment.Controller;

import com.example.demo.Assignment.Model.Mu;

import com.example.demo.Assignment.Service.IHoaDonSerVice;
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
import java.util.*;

@Controller
@RequestMapping("/huydqph27425/Ass")
@SessionAttributes("gioHangCount")
public class Mucontroller {
    @Autowired
    private IMuService service;
    @Autowired
    private IHoaDonSerVice hoaDonSerVice;


    @GetMapping("/details/{ma}")
    public String a(Model model, @PathVariable("ma") Integer ma) {
        Mu Mu = service.getMu(ma);
        model.addAttribute("mu", Mu);

        return "/Assignment/detailGH";
    }
    @GetMapping("/details/{ma}/{giaMoi}")
    public String detialGHKhicoGiamGia(Model model, @PathVariable("ma") Integer ma
            ,@PathVariable("giaMoi") BigDecimal giaMoi ) {
        Mu Mu = service.getMu(ma);
        model.addAttribute("mu", Mu);
        model.addAttribute("giaMoi",giaMoi);

        return "/Assignment/detailGH";
    }

    @GetMapping("/admin/index")
    public String page(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        model.addAttribute("Mu", new Mu());

        Page<Mu> listMu = service.pagination(Optional.of(pageNo));

        int totalPages = listMu.getTotalPages();

        model.addAttribute("toTal", totalPages - 1);
        List<Mu> list = listMu.getContent();
        model.addAttribute("muList", list);

        model.addAttribute("listMu", listMu);
        return "/Assignment/index";
    }

    @GetMapping()
    public String index(Model model, HttpSession session) {
        session.setAttribute("gioHangCount", 0);
        Page<Mu> listMu = service.pagination(Optional.of(0));

        int totalPages = listMu.getTotalPages();

        model.addAttribute("toTal", totalPages - 1);
        List<Mu> list = listMu.getContent();


        model.addAttribute("muList", list);

        model.addAttribute("listMu", listMu);
        return "/Assignment/trang-chu";
    }

    @GetMapping("/page")
    public String Page(Model model,HttpSession session
            ,@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        session.setAttribute("gioHangCount", 0);
        Page<Mu> listMu = service.pagination(Optional.of(pageNo));

        int totalPages = listMu.getTotalPages();

        model.addAttribute("toTal", totalPages - 1);
        List<Mu> list = listMu.getContent();


        model.addAttribute("muList", list);

        model.addAttribute("listMu", listMu);
        return "/Assignment/trang-chu";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "Xuatsu", required = false) String xuatsu,
                         @RequestParam(value = "ChatLieu", required = false) String chatlieu,
                         @RequestParam(value = "min", required = false) BigDecimal min,
                         @RequestParam(value = "max", required = false) BigDecimal max,
                         @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                         Model model) {
        model.addAttribute("Mu", new Mu());
        Page<Mu> listMu = service.findByAll(name, xuatsu, chatlieu, min, max, Optional.ofNullable(pageNo));
        int totalPages = listMu.getTotalPages();

        model.addAttribute("toTal", totalPages - 1);
        List<Mu> list = listMu.getContent();
        model.addAttribute("muList", list);
        model.addAttribute("selectedValue", xuatsu);

        return "/Assignment/index";
    }


    @GetMapping("/xoa/{ma}")
    public String Xoa(@PathVariable("ma") Integer id, RedirectAttributes redirectAttributes) {
        if (service.delete(id)) {
            redirectAttributes.addFlashAttribute("Success", "xoa thanh cong");

        } else {
            redirectAttributes.addFlashAttribute("Fail", "xoa that bai");
        }
        return "redirect:/huydqph27425/Ass/admin/index";
    }

    @GetMapping("/edit/{ma}")
    public String Tim(@PathVariable("ma") Integer ma, Model model) {
        Mu Mu = service.getMu(ma);
        String cleanedTen = Mu.getChatLieu().trim(); // Loại bỏ khoảng trắng ở đầu và cuối chuỗi
        String cleanedXuatsu = Mu.getXuatsu().trim(); // Loại bỏ khoảng trắng ở đầu và cuối chuỗi

        Mu.setChatLieu(cleanedTen);
        Mu.setXuatsu(cleanedXuatsu);
        String anh = Mu.getImages();
        System.out.println(anh);
        model.addAttribute("anh", anh);
        model.addAttribute("Mu", Mu);

        return "/Assignment/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("Mu") Mu mu, @RequestParam("anh") String anh, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Xử lý khi có lỗi trong form
            model.addAttribute("Mu", mu); // Gán lại đối tượng Mu vào model
            Page<Mu> muPage = service.pagination(Optional.of(0));
            List<Mu> muList = muPage.getContent();
            model.addAttribute("toTal", muPage.getTotalPages());
            model.addAttribute("muList", muList);

            return "/Assignment/index";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date presentDay = Calendar.getInstance().getTime();
            String formattedDate = dateFormat.format(presentDay);
            try {
                Date format = dateFormat.parse((formattedDate));
                mu.setImages(anh);
                mu.setModifyDate(format);
                if (service.save(mu)) {
                    redirectAttributes.addFlashAttribute("Success", "Thêm thành công");
                } else {
                    redirectAttributes.addFlashAttribute("Fail", "Thêm thất bại");
                }
                return "redirect:/huydqph27425/Ass/admin/index";
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        }
    }

    @PostMapping("/update")
    public String Update(@Valid @ModelAttribute("Mu") Mu mu, @RequestParam("anh") String anh, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Xử lý khi có lỗi trong form
            return "/Assignment/add";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date presentDay = Calendar.getInstance().getTime();
            String formattedDate = dateFormat.format(presentDay);
            try {
                Date format = dateFormat.parse((formattedDate));
                mu.setImages(anh);
                mu.setModifyDate(format);
                if (service.update(mu.getMa(), mu)) {
                    redirectAttributes.addFlashAttribute("Success", "Update thành công");
                } else {
                    redirectAttributes.addFlashAttribute("Fail", "Update thất bại");
                }
                return "redirect:/huydqph27425/Ass/admin/index";
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }



}
