package com.example.demo.Assignment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Mu")
public class Mu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma;

    @NotBlank(message = "khong duoc de trong ten")
    @Column(name = "ten")
    private String ten;

    @Column(name = "so_luong")
    @NotNull(message = "khong duoc de trong so luong")
    private Integer soLuong;

    @NotNull(message = "khong duoc de trong don gia")
    @Positive(message = "Don gia phải lớn hơn 0")
    @Column(name = "dong_ia")
    private BigDecimal donGia;

    @Column(name = "modifydate")
    private Date modifyDate;
    @NotBlank
    @Column(name = "xuat_su")
    private String xuatsu;

    @Column(name = "images")
    private String images;
    @NotBlank
    @Column(name = "chat_lieu")
    private String chatLieu;

    public String getXuatsu() {
        return xuatsu;
    }

    public void setXuatsu(String xuatsu) {
        this.xuatsu = xuatsu;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }
}
