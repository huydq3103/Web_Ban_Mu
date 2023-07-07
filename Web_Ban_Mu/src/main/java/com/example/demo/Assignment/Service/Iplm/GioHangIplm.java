package com.example.demo.Assignment.Service.Iplm;

import com.example.demo.Assignment.CustomModel.GioHangChiTietCustom;
import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Model.GioHang;
import com.example.demo.Assignment.Model.GioHangChiTIet;
import com.example.demo.Assignment.Model.Mu;
import com.example.demo.Assignment.Responsitory.IGioHangREspon;
import com.example.demo.Assignment.Responsitory.IGioHangChiTietRespon;
import com.example.demo.Assignment.Responsitory.IMuResponsitory;
import com.example.demo.Assignment.Service.IGioHangServIce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class GioHangIplm implements IGioHangServIce {


    @Autowired
    private IGioHangChiTietRespon responsitory;
    @Autowired
    private IGioHangREspon rEspon2;

    @Autowired
    private IMuResponsitory Muresponsitory;

    @Override
    public BigDecimal tinhTongTien(Integer idUser) {
        List<GioHangChiTietCustom> gioHangChiTietList = xemGioHang(idUser);
        BigDecimal tongTien = gioHangChiTietList.stream()
                .map(ghct -> ghct.getDonGia().multiply(BigDecimal.valueOf(ghct.getSoLuong())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return tongTien;
    }


    @Override
    public void themSanPham(Integer id, Integer soLuong, GioHang gioHang, Mu mu,BigDecimal donGiaGiam) {

        GioHangChiTIet gioHangCT = responsitory.findByMu_MaAndGioHang_Id(id,gioHang.getId());
        if (gioHangCT != null) {
            // Tìm thấy phần tử có mu.ma tương ứng
            Integer slHienTai = gioHangCT.getSoLuong();
            Integer slMoi = slHienTai + soLuong;
            gioHangCT.setSoLuong(slMoi);
            responsitory.save(gioHangCT);
            // update lai so luong
            updateSL(id, soLuong);
        } else {

            if (donGiaGiam.compareTo(BigDecimal.ZERO) == 0) {
                GioHangChiTIet newGioHangCT = new GioHangChiTIet();
                // Cài đặt các thông tin khác cho newGioHang
                newGioHangCT.setSoLuong(soLuong);
                newGioHangCT.setGioHang(gioHang);
                newGioHangCT.setMu(mu);
                newGioHangCT.setDonGia(mu.getDonGia());
                responsitory.save(newGioHangCT);
                // update lai so luong
                updateSL(id, soLuong);
            }else{
                // Không tìm thấy phần tử, tạo mới và lưu vào cơ sở dữ liệu
                GioHangChiTIet newGioHangCT = new GioHangChiTIet();
                // Cài đặt các thông tin khác cho newGioHang
                newGioHangCT.setSoLuong(soLuong);
                newGioHangCT.setGioHang(gioHang);
                newGioHangCT.setMu(mu);
                newGioHangCT.setDonGia(donGiaGiam);
                responsitory.save(newGioHangCT);
                // update lai so luong
                updateSL(id, soLuong);
            }

        }

    }

    @Override
    public GioHang CheckGioHang(Integer id) {
        return rEspon2.findAllByAccount_id(id);
    }

    @Override
    public List<GioHangChiTietCustom> xemGioHang(Integer idUser) {
        return responsitory.findAllByUserId(idUser);
    }

    @Override
    public List<GioHangChiTietCustom> getGioHang(Integer idGh) {
        return responsitory.findByGioHang_Id(idGh);
    }


    public GioHang createGH(String ma, Account Acoount) {
        String magh = "gio_hang" + ma;
        Date ngaytao = new Date();
        GioHang gh = new GioHang(magh, ngaytao, Acoount);
        return rEspon2.save(gh);
    }

    @Override
    @Transactional
    public void xoaSpGh(Integer ma,GioHang gioHang) {
        GioHangChiTIet ghtct = responsitory.findByMu_MaAndGioHang_Id(ma,gioHang.getId());
        responsitory.deleteByMu_Ma(ma);
        Integer soLuong = ghtct.getSoLuong();
        // update lai so luong san pham
        updateSLKhiXoaHayBo(ma, soLuong);
    }

    @Override
    public void botSpGh(Integer ma, Integer soLuong,GioHang gioHang) {
        GioHangChiTIet ghtct = responsitory.findByMu_MaAndGioHang_Id(ma,gioHang.getId());
        if (ghtct != null) {
            Integer soLuongHienTai = ghtct.getSoLuong(); // Lấy số lượng hiện tại
            if (soLuongHienTai > 0) {
                ghtct.setSoLuong(soLuongHienTai - soLuong); // Giảm số lượng theo yêu cầu
                responsitory.save(ghtct);

                if (ghtct.getSoLuong() == 0) {
                    // Xóa sản phẩm khỏi giỏ hàng
                    responsitory.delete(ghtct);

                }

                // Cập nhật lại số lượng sản phẩm
                updateSLKhiXoaHayBo(ma, soLuong);
            }
        }
    }

    @Override
    @Transactional
    public void xoaGhKhiThanhToan(Integer idGh) {
        responsitory.deleteByGioHang_Id(idGh);
    }

    @Override
    public  List<Object[]> soluongSpGh(String userName) {
        return responsitory.soLuongSpGh(userName);
    }


    public void updateSL(Integer id, Integer soLuong) {
        Mu updateSL = Muresponsitory.findByMa(id);
        Integer slBandau = updateSL.getSoLuong();
        if (slBandau - soLuong == 0) {
            updateSL.setSoLuong(0);
            Muresponsitory.save(updateSL);
        } else {
            updateSL.setSoLuong(slBandau - soLuong);
            Muresponsitory.save(updateSL);
        }

    }

    public void updateSLKhiXoaHayBo(Integer id, Integer soLuong) {
        Mu updateSL = Muresponsitory.findByMa(id);
        Integer slBandau = updateSL.getSoLuong();
        updateSL.setSoLuong(slBandau + soLuong);
        Muresponsitory.save(updateSL);
    }

}
