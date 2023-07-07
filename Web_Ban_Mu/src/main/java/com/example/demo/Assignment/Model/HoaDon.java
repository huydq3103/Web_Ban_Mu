package com.example.demo.Assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "hoa_don")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_hd")
    private String maHD;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_ship")
    private Date ngayShip;
    @Column(name = "ngay_nhan")
    private Date ngayNhan;
    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;
    @Column(name = "tinh_trang")
    private Integer tinhTrang;
    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "sdt")
    private String Sdt;

    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id")
    private Account account;

    public HoaDon(String maHD, Date ngayTao,
                  Date ngayShip, Date ngayNhan,
                  Date ngayThanhToan,
                  Integer tinhTrang,
                  String tenNguoiNhan,
                  String diaChi, String sdt,
                  Account account) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        Sdt = sdt;
        this.account = account;
    }
}
