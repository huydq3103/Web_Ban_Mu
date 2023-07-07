package com.example.demo.Assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "gio_hang_chi_tiet")
@IdClass(GioHangChiTietId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTIet {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_gio_hang", referencedColumnName = "id")
    private GioHang gioHang;

    @Id
    @ManyToOne()
    @JoinColumn(name = "id_san_pham", referencedColumnName = "ma")
    private Mu mu;
    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;
}
