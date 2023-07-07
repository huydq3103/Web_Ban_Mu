package com.example.demo.Assignment.Service.Iplm;

import com.example.demo.Assignment.Model.Mu;
import com.example.demo.Assignment.Responsitory.IMuResponsitory;
import com.example.demo.Assignment.Service.IMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MuServiceImplment implements IMuService {

    @Autowired
    private IMuResponsitory responsitory;

    List<Mu> list;

    @Override
    public List<Mu> getAll() {
        list = responsitory.findAll();
        return list;
    }

    @Override
    public boolean save(Mu mu) {

        try {
            responsitory.save(mu);
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }


    }

    @Override
    public boolean delete(Integer ma) {

        try {
            responsitory.deleteById(ma);
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }

    }

    @Override
    public boolean update(Integer ma, Mu NewMu) {
        try {
            responsitory.findById(ma)
                    .map(mu -> {
                        mu.setTen(NewMu.getTen());
                        mu.setChatLieu(NewMu.getChatLieu());
                        mu.setDonGia(NewMu.getDonGia());
                        mu.setSoLuong(NewMu.getSoLuong());
                        mu.setXuatsu(NewMu.getXuatsu());
                        mu.setImages(NewMu.getImages());
                        mu.setModifyDate(NewMu.getModifyDate());
                        return responsitory.save(mu);
                    }).orElseGet(() -> responsitory.save(NewMu));
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }

    }

    @Override
    public Page<Mu> pagination(Optional<Integer> pageNo) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), 8);
        return responsitory.findAll(pageable);
    }

    @Override
    public Mu getMu(Integer ma) {
        return responsitory.findByMa(ma);
    }

//    @Override
//    public Page<Mu> Find(String ten, Optional<Integer> pageNo) {
//        Pageable pageable = PageRequest.of(pageNo.orElse(0), 10);
//        return responsitory.findByTenContains(ten, pageable);
//    }
//
//    @Override
//    public Page<Mu> FinByPrice(BigDecimal min, BigDecimal max, Optional<Integer> pageNo) {
//        Pageable pageable = PageRequest.of(pageNo.orElse(0), 10);
//        return responsitory.findByDonGiaBetween(min, max, pageable);
//    }


    @Override
    public Page<Mu> findByAll(String name, String xuatSu, String chatLieu, BigDecimal min, BigDecimal max, Optional<Integer> pageNo) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), 10);

        if (name != null && xuatSu != null && chatLieu != null && min != null && max != null) {
            return responsitory.findByTenContainsAndXuatsuAndChatLieuAndDonGiaBetween(name, xuatSu, chatLieu, min, max, pageable);
        } else if (name != null && xuatSu != null && chatLieu != null) {
            return responsitory.findByTenContainsAndXuatsuAndChatLieu(name, xuatSu, chatLieu, pageable);
        } else if (name != null && xuatSu != null && min != null && max != null) {
            return responsitory.findByTenContainsAndXuatsuAndDonGiaBetween(name, xuatSu, min, max, pageable);
        } else if (name != null && chatLieu != null && min != null && max != null) {
            return responsitory.findByTenContainsAndChatLieuAndDonGiaBetween(name, chatLieu, min, max, pageable);
        } else if (xuatSu != null && chatLieu != null && min != null && max != null) {
            return responsitory.findByXuatsuAndChatLieuAndDonGiaBetween(xuatSu, chatLieu, min, max, pageable);
        } else if (name != null && xuatSu != null) {
            return responsitory.findByTenContainsAndXuatsu(name, xuatSu, pageable);
        } else if (name != null && chatLieu != null) {
            return responsitory.findByTenContainsAndChatLieu(name, chatLieu, pageable);
        } else if (name != null && min != null && max != null) {
            return responsitory.findByTenContainsAndDonGiaBetween(name, min, max, pageable);
        } else if (xuatSu != null && chatLieu != null) {
            return responsitory.findByXuatsuAndChatLieu(xuatSu, chatLieu, pageable);
        } else if (xuatSu != null && min != null && max != null) {
            return responsitory.findByXuatsuAndDonGiaBetween(xuatSu, min, max, pageable);
        } else if (chatLieu != null && min != null && max != null) {
            return responsitory.findByChatLieuAndDonGiaBetween(chatLieu, min, max, pageable);
        } else if (name != null) {
            return responsitory.findByTenContains(name, pageable);
        } else if (xuatSu != null) {
            return responsitory.findByXuatsu(xuatSu, pageable);
        } else if (chatLieu != null) {
            return responsitory.findByChatLieu(chatLieu, pageable);
        } else if (min != null && max != null) {
            return responsitory.findByDonGiaBetween(min, max, pageable);
        } else {
            return responsitory.findAll(pageable);
        }
    }


}
