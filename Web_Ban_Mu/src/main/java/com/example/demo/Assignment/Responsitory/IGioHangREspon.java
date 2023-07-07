package com.example.demo.Assignment.Responsitory;

import com.example.demo.Assignment.Model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IGioHangREspon extends JpaRepository<GioHang,Integer> {
    GioHang findAllByAccount_id(@Param("id") Integer id);
}
