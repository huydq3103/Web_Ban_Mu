package com.example.demo.Assignment.Service;

import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Model.HoaDon;
import com.example.demo.Assignment.Model.HoaDonChiTiet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IHoaDonSerVice {

    List<HoaDonChiTiet> xemHoaDon(String maHD);

    HoaDon createHoaDon(Account account, String tenNguoiNhan, String diaChi, String sdt);

    List<HoaDon> getOneHD(Integer idUSer);

    HoaDonChiTiet createHoaDonCT(HoaDonChiTiet hoaDonChiTiet);

    BigDecimal tongTienHD(List<HoaDonChiTiet> hoaDonChiTiets);

    List<Object[]> hangBanChayNgay(Date ngay);

    List<Object[]> hangBanChayTuan(String tuan);

    List<Object[]> hangBanChayThang(Date thang);

    // hang ton
    List<Object[]> hangTonNgay(Date ngay);
    List<Object[]> hangTonTuan(String tuan);
    List<Object[]> hangTonThang(Date thang);

    List<Object[]> hangTon();

}
