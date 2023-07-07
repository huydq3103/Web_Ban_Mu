package com.example.demo.Assignment.Service;

import com.example.demo.Assignment.Model.Mu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IMuService {

    List<Mu> getAll();

    boolean save(Mu mu);

    boolean delete(Integer ma);

    boolean update(Integer ma, Mu NewMu);

    Page<Mu> pagination(Optional<Integer> pageNo);

    Mu getMu(Integer ma);

//    Page<Mu> Find(String ten, Optional<Integer> pageNo);
//
//    Page<Mu> FinByPrice(BigDecimal min, BigDecimal max, Optional<Integer> pageNo);
//
//    Page<Mu> FinByXuatSu(String xuatSu, Optional<Integer> pageNo);
//
//    Page<Mu> FinByChatLieu(String chatLieu, Optional<Integer> pageNo);


    Page<Mu> findByAll(String name, String xuatSu, String chatLieu, BigDecimal min, BigDecimal max, Optional<Integer> pageNo);
}
