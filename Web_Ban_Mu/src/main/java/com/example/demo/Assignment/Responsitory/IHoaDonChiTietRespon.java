package com.example.demo.Assignment.Responsitory;

import com.example.demo.Assignment.Model.HoaDon;
import com.example.demo.Assignment.Model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public interface IHoaDonChiTietRespon extends JpaRepository<HoaDonChiTiet, Integer> {

    @Query("""
            SELECT hdct
            FROM HoaDonChiTiet hdct
            JOIN hdct.hoadon hd
            WHERE hdct.hoadon.maHD = :maHD
            GROUP BY hdct
            """)
    List<HoaDonChiTiet> findByAccount_Id(@Param("maHD") String maHD);


    @Query(value = "SELECT TOP 10 hdct.id_san_pham,m.ten,SUM(hdct.so_luong) AS SoLuongBan\n" +
            "FROM hoa_don_chi_tiet hdct\n" +
            "JOIN Mu m ON m.ma = hdct.id_san_pham\n" +
            "JOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "WHERE CONVERT(DATE, hd.ngay_thanh_toan) = :ngay\n" +
            "GROUP BY hdct.id_san_pham,m.ten\n" +
            "ORDER BY SoLuongBan DESC;", nativeQuery = true)
    List<Object[]> listHangBanChayTheoNgay(@Param("ngay") Date ngay);

    @Query(value = "SELECT TOP 10 hdct.id_san_pham,m.ten,SUM(hdct.so_luong) AS SoLuongBan\n" +
            "FROM hoa_don_chi_tiet hdct\n" +
            "JOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "JOIN Mu m ON m.ma = hdct.id_san_pham\n" +
            "WHERE DATEPART(WEEK, hd.ngay_thanh_toan) = DATEPART(WEEK, :tuan)\n" +
            "    AND DATEPART(YEAR, hd.ngay_thanh_toan) = DATEPART(YEAR, :tuan)\n" +
            "GROUP BY hdct.id_san_pham,m.ten\n" +
            "ORDER BY SoLuongBan DESC;", nativeQuery = true)
    List<Object[]> listHangBanChayTheoTuan(@Param("tuan") String tuan);

    @Query(value = "SELECT TOP 10 hdct.id_san_pham,m.ten ,SUM(hdct.so_luong) AS SoLuongBan\n" +
            "FROM hoa_don_chi_tiet hdct\n" +
            "JOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "JOIN Mu m ON m.ma = hdct.id_san_pham\n" +
            "WHERE DATEPART(MONTH, hd.ngay_thanh_toan) = DATEPART(MONTH, :thang)\n" +
            "    AND DATEPART(YEAR, hd.ngay_thanh_toan) = DATEPART(YEAR, :thang)\n" +
            "GROUP BY hdct.id_san_pham,m.ten\n" +
            "ORDER BY SoLuongBan DESC;", nativeQuery = true)
    List<Object[]> listHangBanChayTheoThang(@Param("thang") Date thang);

    // hang ton
    @Query(value = "SELECT TOP 10 mh.ten\n" +
            "FROM Mu mh\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM hoa_don_chi_tiet hdct\n" +
            "\tJOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "    WHERE mh.ma = hdct.id_san_pham\n" +
            "        AND CONVERT(DATE, hd.ngay_thanh_toan) = CONVERT(DATE, :ngay)\n" +
            ")\n" +
            "ORDER BY mh.modifydate DESC;", nativeQuery = true)
    List<Object[]> listHangTonTheoNgay(@Param("ngay") Date ngay);


    @Query(value = "SELECT TOP 10 mh.ten\n" +
            "FROM Mu mh\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM hoa_don_chi_tiet hdct\n" +
            "\tJOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "    WHERE mh.ma = hdct.id_san_pham\n" +
            "        AND DATEPART(WEEK, hd.ngay_thanh_toan) = DATEPART(WEEK, :tuan)\n" +
            "        AND DATEPART(YEAR, hd.ngay_thanh_toan) = DATEPART(YEAR, :tuan)\n" +
            ")\n" +
            "ORDER BY mh.modifydate DESC;", nativeQuery = true)
    List<Object[]> listHangTonTheoTuan(@Param("tuan") String tuan);


    @Query(value = "SELECT TOP 10 mh.ten\n" +
            "FROM Mu mh\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM hoa_don_chi_tiet hdct\n" +
            "\tJOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "    WHERE mh.ma = hdct.id_san_pham\n" +
            "        AND DATEPART(MONTH, hd.ngay_thanh_toan) = DATEPART(MONTH, :thang)\n" +
            "        AND DATEPART(YEAR, hd.ngay_thanh_toan) = DATEPART(YEAR, :thang)\n" +
            ")\n" +
            "ORDER BY mh.modifydate DESC;", nativeQuery = true)
    List<Object[]> listHangTonTheoThang(@Param("thang") Date thang);



    @Query(value ="SELECT TOP 10 mh.ten,mh.ma,mh.so_luong\n" +
            "            FROM Mu mh\n" +
            "            WHERE NOT EXISTS (\n" +
            "               SELECT 1\n" +
            "               FROM hoa_don_chi_tiet hdct\n" +
            "               JOIN hoa_don hd ON hdct.id_hoa_don = hd.id\n" +
            "               WHERE mh.ma = hdct.id_san_pham)" ,nativeQuery = true)
     List<Object[]> HangTon();
}
