package com.example.demo.Assignment.Responsitory;

import com.example.demo.Assignment.Model.GioHang;
import com.example.demo.Assignment.Model.Mu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IMuResponsitory extends JpaRepository<Mu,Integer> {

     Mu findByMa(Integer ma);

     Page<Mu> findByTenContains(String ten, Pageable pageable);

     Page<Mu> findByChatLieu(String chatLieu, Pageable pageable);

     Page<Mu> findByXuatsu(String xuatSu, Pageable pageable);

     Page<Mu> findByDonGiaBetween(BigDecimal min,BigDecimal max, Pageable pageable);


     Page<Mu> findByTenContainsAndXuatsuAndChatLieuAndDonGiaBetween(String name, String xuatSu, String chatLieu, BigDecimal min, BigDecimal max, Pageable pageable);

     Page<Mu> findByTenContainsAndXuatsuAndChatLieu(String name, String xuatSu, String chatLieu, Pageable pageable);

     Page<Mu> findByTenContainsAndXuatsuAndDonGiaBetween(String name, String xuatSu, BigDecimal min, BigDecimal max, Pageable pageable);

     Page<Mu> findByXuatsuAndChatLieuAndDonGiaBetween(String xuatSu, String chatLieu, BigDecimal min, BigDecimal max, Pageable pageable);

     Page<Mu> findByTenContainsAndChatLieuAndDonGiaBetween(String name, String chatLieu, BigDecimal min, BigDecimal max, Pageable pageable);

     Page<Mu> findByTenContainsAndXuatsu(String name, String xuatSu, Pageable pageable);

     Page<Mu> findByTenContainsAndChatLieu(String name, String chatLieu, Pageable pageable);

     Page<Mu> findByTenContainsAndDonGiaBetween(String name, BigDecimal min, BigDecimal max, Pageable pageable);

     Page<Mu> findByXuatsuAndChatLieu(String xuatSu, String chatLieu, Pageable pageable);

     Page<Mu> findByXuatsuAndDonGiaBetween(String xuatSu, BigDecimal min, BigDecimal max, Pageable pageable);

     Page<Mu> findByChatLieuAndDonGiaBetween(String chatLieu, BigDecimal min, BigDecimal max, Pageable pageable);
}
