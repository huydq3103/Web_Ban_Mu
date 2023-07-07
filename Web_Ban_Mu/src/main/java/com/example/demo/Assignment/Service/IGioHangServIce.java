package com.example.demo.Assignment.Service;

import com.example.demo.Assignment.CustomModel.GioHangChiTietCustom;
import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Model.GioHang;
import com.example.demo.Assignment.Model.GioHangChiTIet;
import com.example.demo.Assignment.Model.Mu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IGioHangServIce {

    BigDecimal tinhTongTien(Integer idUser);


//    void themGia(Integer id, BigDecimal gia);

  void  themSanPham(Integer id, Integer soLuong, GioHang gioHang, Mu mu,BigDecimal donGiaGiam);

    GioHang CheckGioHang(Integer id);

    List<GioHangChiTietCustom> xemGioHang(Integer idUser);

    List<GioHangChiTietCustom> getGioHang(Integer idGh);


    GioHang createGH(String ma, Account Acoount);

    void xoaSpGh(Integer ma,GioHang gioHang);

    void botSpGh(Integer ma,Integer soLuong,GioHang gioHang);

    void xoaGhKhiThanhToan(Integer idGh);

    List<Object[]> soluongSpGh(String userName);

}
