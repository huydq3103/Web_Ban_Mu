select * from Gio_hang
select * from gio_hang_chi_tiet
select * from account 
select * from hoa_don
select * from hoa_don_chi_tiet
select * from mu

update Account set user_name= 'huydq' , password = '1',role=1 where id=9
delete Account where id=10
SELECT TOP 10 hdct.id_san_pham, m.ten, SUM(hdct.so_luong) AS SoLuongBan
FROM hoa_don_chi_tiet hdct
JOIN hoa_don hd ON hdct.id_hoa_don = hd.id
JOIN Mu m ON m.ma = hdct.id_san_pham
WHERE DATEPART(WEEK, hd.ngay_thanh_toan) = DATEPART(WEEK, GETDATE())
    AND DATEPART(YEAR, hd.ngay_thanh_toan) = DATEPART(YEAR, GETDATE())
    AND hd.ngay_thanh_toan >= DATEADD(WEEK, DATEDIFF(WEEK, 0, GETDATE()), 0)
GROUP BY hdct.id_san_pham, m.ten
ORDER BY SoLuongBan DESC;

delete from gio_hang

insert into Account(user_name,password,role) values('hihi',1,1)
delete from gio_hang_chi_tiet where id_gio_hang = 0

ALTER TABLE Gio_hang ADD ma_gh char(100) unique;



insert into gio_hang_chi_tiet(id_gio_hang,id_san_pham,so_luong,don_gia)
values(8,80,2,2)

select count(ghct.id_san_pham)  from  gio_hang_chi_tiet ghct
join  Gio_hang gh on ghct.id_gio_hang = gh.id
join Account acc on acc.id = gh.id_account
where acc.user_name = 'hihi'




 SELECT hdct.hoadon.maHD, hdct.hoadon.ngayTao, hdct.hoadon.ngayThanhToan,
        hdct.hoadon.tenNguoiNhan, hdct.hoadon.tinhTrang
        FROM HoaDonChiTiet hdct
        JOIN hdct.hoadon hd
		join Account acc on acc.id = id_account
        WHERE hd.account.id = :idUser
        GROUP BY hdct.hoadon.maHD, hdct.hoadon.ngayTao, hdct.hoadon.ngayThanhToan,
        hdct.hoadon.tenNguoiNhan, hdct.hoadon.tinhTrang, hdct.hoadon.id



SELECT TOP 10 mh.ten,mh.ma
FROM Mu mh
WHERE NOT EXISTS (
    SELECT 1
    FROM hoa_don_chi_tiet hdct
    JOIN hoa_don hd ON hdct.id_hoa_don = hd.id
    WHERE mh.ma = hdct.id_san_pham  
)
