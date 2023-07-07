package com.example.demo.Assignment.Responsitory;

import com.example.demo.Assignment.CustomModel.GioHangChiTietCustom;
import com.example.demo.Assignment.Model.GioHangChiTIet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGioHangChiTietRespon extends JpaRepository<GioHangChiTIet, Integer> {
    @Query("""
            SELECT new com.example.demo.Assignment.CustomModel.GioHangChiTietCustom(
                ghct.mu.ten, ghct.mu.chatLieu, ghct.mu.images, ghct.mu.xuatsu,
                 ghct.soLuong,ghct.donGia,ghct.mu.ma,ghct.gioHang.id
            )
            FROM GioHangChiTIet ghct
            WHERE ghct.gioHang.account.id = :userId
            """)
    List<GioHangChiTietCustom> findAllByUserId(@Param("userId") Integer userId);

    @Query("""
       SELECT new com.example.demo.Assignment.CustomModel.GioHangChiTietCustom
             (ghct.mu.ten, ghct.mu.chatLieu,
             ghct.mu.xuatsu, ghct.soLuong, ghct.donGia,ghct.mu.ma)
              FROM GioHangChiTIet ghct WHERE ghct.gioHang.id = :ghId
""")
    List<GioHangChiTietCustom> findByGioHang_Id(@Param("ghId") Integer gioHangId);


    GioHangChiTIet findByMu_MaAndGioHang_Id(Integer ma,Integer idGh);

    void deleteByMu_Ma(Integer maSP);

    void deleteByGioHang_Id(Integer idGh);

    @Query(value = "select count(ghct.id_san_pham) from gio_hang_chi_tiet ghct\n" +
            "join Gio_hang gh on ghct.id_gio_hang = gh.id\n" +
            "join Account acc on acc.id = gh.id_account\n" +
            "where acc.user_name = :userName", nativeQuery = true)

       public List<Object[]> soLuongSpGh(@Param("userName") String userName);

}


