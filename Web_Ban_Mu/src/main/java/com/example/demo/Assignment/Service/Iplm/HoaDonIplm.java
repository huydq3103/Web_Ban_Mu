package com.example.demo.Assignment.Service.Iplm;

import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Model.HoaDon;
import com.example.demo.Assignment.Model.HoaDonChiTiet;
import com.example.demo.Assignment.Responsitory.IHoaDonChiTietRespon;
import com.example.demo.Assignment.Responsitory.IHoaDonResponsitory;
import com.example.demo.Assignment.Service.IHoaDonSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class HoaDonIplm implements IHoaDonSerVice {
    @Autowired
    private IHoaDonResponsitory hoaDonResponsitory;

    @Autowired
    private IHoaDonChiTietRespon hoaDonChiTietRespon;

    @Override
    public List<HoaDonChiTiet> xemHoaDon(String maHD) {
        return hoaDonChiTietRespon.findByAccount_Id(maHD);
    }

    @Override
    public HoaDon createHoaDon(Account account, String tenNguoiNhan, String diaChi, String sdt) {
        UUID uuid = UUID.randomUUID();
        String maHD = uuid.toString();

        Date ngayTao = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayTao);

        calendar.add(Calendar.DAY_OF_MONTH, 1); // Thêm 1 ngày vào ngày tạo
        Date ngayShip = calendar.getTime();
        calendar.setTime(ngayShip);

        calendar.add(Calendar.DAY_OF_MONTH, 2); // Thêm 1 ngày vào ngày tạo
        Date ngayNhan = calendar.getTime();
        Date ngayThanhToan = new Date();

        Integer tinhTrang = 1;
        String tenNguoiNHan = tenNguoiNhan;
        String diachi = diaChi;
        String sddt = sdt;

        HoaDon hd = new HoaDon(maHD, ngayTao, ngayShip,
                ngayNhan, ngayThanhToan, tinhTrang,
                tenNguoiNHan, diachi, sddt, account);

        return hoaDonResponsitory.save(hd);

    }

    @Override
    public List<HoaDon> getOneHD(Integer idUser) {
        return hoaDonResponsitory.findByAccount_Id(idUser);
    }

    @Override
    public HoaDonChiTiet createHoaDonCT(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRespon.save(hoaDonChiTiet);
    }

    @Override
    public BigDecimal tongTienHD(List<HoaDonChiTiet> hoaDonChiTiets) {
        BigDecimal tongTien = BigDecimal.ZERO;
        for (HoaDonChiTiet hdct : hoaDonChiTiets) {
            BigDecimal thanhTien = hdct.getDonGia().multiply(BigDecimal.valueOf(hdct.getSoLuong()));
            tongTien = tongTien.add(thanhTien);
        }
        return tongTien;
    }

    @Override
    public List<Object[]> hangBanChayNgay(Date ngay) {
        return hoaDonChiTietRespon.listHangBanChayTheoNgay(ngay);
    }

    @Override
    public List<Object[]> hangBanChayTuan(String tuan) {
        return hoaDonChiTietRespon.listHangBanChayTheoTuan(tuan);
    }


    @Override
    public List<Object[]> hangBanChayThang(Date thang) {
        return hoaDonChiTietRespon.listHangBanChayTheoThang(thang);
    }

    @Override
    public List<Object[]> hangTonNgay(Date ngay) {
        return hoaDonChiTietRespon.listHangTonTheoNgay(ngay);
    }

    @Override
    public List<Object[]> hangTonTuan(String tuan) {
        return hoaDonChiTietRespon.listHangTonTheoTuan(tuan);
    }

    @Override
    public List<Object[]> hangTonThang(Date thang) {
        return hoaDonChiTietRespon.listHangTonTheoThang(thang);
    }

    @Override
    public List<Object[]> hangTon() {
        return hoaDonChiTietRespon.HangTon();
    }


}
