package com.example.demo.Assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "gio_hang")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_gh")
    private String maGH;

    @Column(name = "ngay_tao")
    private Date ngayTao;
    @OneToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id")
    private Account account;


    @OneToMany(mappedBy = "gioHang")
    private List<GioHangChiTIet> gioHangChiTIets = new ArrayList<>();

    public GioHang(String maGH, Date ngayTao, Account account) {
        this.maGH = maGH;
        this.ngayTao = ngayTao;
        this.account = account;
    }
}
