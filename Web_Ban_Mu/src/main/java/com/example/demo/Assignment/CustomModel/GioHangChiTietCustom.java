package com.example.demo.Assignment.CustomModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTietCustom {
    private String ten;
    private String chatLieu;
    private String images;
    private String xuatsu;
    private Integer soLuong;

    private BigDecimal donGia;

    private Integer ma;

    private Integer idGh;

    public GioHangChiTietCustom(String ten, String chatLieu, String xuatsu, Integer soLuong, BigDecimal donGia, Integer ma) {
        this.ten = ten;
        this.chatLieu = chatLieu;
        this.xuatsu = xuatsu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ma = ma;
    }
}
