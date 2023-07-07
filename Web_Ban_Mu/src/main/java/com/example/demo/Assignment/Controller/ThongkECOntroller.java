package com.example.demo.Assignment.Controller;

import com.example.demo.Assignment.Service.IHoaDonSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThongkECOntroller {

    @Autowired
    private IHoaDonSerVice hoaDonSerVice;

    @GetMapping("/thong-ke")
    public String showBieuDo() {
        return "/Assignment/BieuDo";
    }

    @GetMapping("/bieu-do/chart-data")
    @ResponseBody
    public Map<String, Object> insertData(@RequestParam("date") String date,
                                          @RequestParam("week") String week,
                                          @RequestParam("month") String month,
                                          @RequestParam("inventory") String inventory) {

        Map<String, Object> data = new HashMap<>();

        if (inventory.equals("inStock")) {
            if (date != null && !date.isEmpty()) {
                Date ngay = parseDate(date);
                List<Object[]> listNgay = hoaDonSerVice.hangBanChayNgay(ngay);
                data.put("listNgay", listNgay);
            }
            if (week != null && !week.isEmpty()) {
                Date tuan = parseWeek(week);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date2 = format.format(tuan);

                List<Object[]> listTuan = hoaDonSerVice.hangBanChayTuan(date2);
                data.put("listTuan", listTuan);
            }
            if (month != null && !month.isEmpty()) {
                Date thang = parseMonth(month);
                List<Object[]> listThang = hoaDonSerVice.hangBanChayThang(thang);
                data.put("listThang", listThang);
            }
        } else {
            if (date != null && !date.isEmpty()) {
                Date ngay = parseDate(date);
                List<Object[]> listNgay = hoaDonSerVice.hangTon();
                data.put("listNgay", listNgay);
            }
            if (week != null && !week.isEmpty()) {
                Date tuan = parseWeek(week);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date2 = format.format(tuan);
                List<Object[]> listTuan = hoaDonSerVice.hangTon();
                data.put("listTuan", listTuan);
            }
            if (month != null && !month.isEmpty()) {
                Date thang = parseMonth(month);
                List<Object[]> listThang = hoaDonSerVice.hangTon();
                data.put("listThang", listThang);
            }
        }

        return data;
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(dateString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ngày không hợp lệ");
        }
    }


    private Date parseWeek(String weekString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-'W'ww");
            return format.parse(weekString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Tuần không hợp lệ");
        }
    }


    private Date parseMonth(String monthString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            return format.parse(monthString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tháng không hợp lệ");
        }
    }

}
