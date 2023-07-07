package com.example.demo.Assignment.Responsitory;

import com.example.demo.Assignment.Model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHoaDonResponsitory extends JpaRepository<HoaDon,Integer> {



    List<HoaDon> findByAccount_Id(Integer idUSer);
}
