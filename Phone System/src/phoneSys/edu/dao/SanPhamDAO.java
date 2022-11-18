/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phoneSys.edu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import phoneSys.edu.entity.SanPham;
import phoneSys.edu.ultil.jdbcHelper;

/**
 *
 * @author NP
 */
// hhhhhhhh
public class SanPhamDAO extends PhoneSysDAO<SanPham, String> {

    String INSERT_SQL = "INSERT INTO SanPham(MaSanPham, TenSanPham, HangSanXuat, XuatXu, MauSac, SoLuong, DonGia, HinhAnh, TrangThai, GhiChu) values(?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SanPham set TenSanPham = ?, HangSanXuat = ?, XuatXu = ?, MauSac = ?, SoLuong = ?, DonGia = ?, HinhAnh = ?, TrangThai = 'True', GhiChu = ? WHERE MaSanPham = ?";
    String DELETE_SQL = "UPDATE SanPham set TenSanPham = ?, HangSanXuat = ?, XuatXu = ?, MauSac = ?, SoLuong = ?, DonGia = ?, HinhAnh = ?, TrangThai = 'False', GhiChu = ? WHERE MaSanPham = ?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham = ?";
    String SELECT_BY_HANG_SQL = "SELECT DISTINCT HangSanXuat FROM SanPham";
    String SELECT_BY_MAUSAC_SQL = "SELECT DISTINCT MauSac FROM SanPham";
    String SELECT_BY_XUATXU_SQL = "SELECT DISTINCT XuatXu FROM SanPham";
    String SELECT_LOC_HANG_SQL = "SELECT * FROM SanPham WHERE HangSanXuat = ?";
    String SELECT_LOC_MAUSAC_SQL = "SELECT * FROM SanPham WHERE MauSac = ?";
    String SELECT_LOC_XUATXU_SQL = "SELECT * FROM SanPham WHERE XuatXu = ?";
 

    @Override
    public void insert(SanPham entity) {
        try {
            jdbcHelper.update(INSERT_SQL,
                    entity.getMaSanPham(), entity.getTenSanPham(), entity.getHangSanXuat(), entity.getXuatXu(), entity.getMauSac(),
                    entity.getSoLuong(), entity.getDonGia(), entity.getHinhAnh(), true, entity.getGhiChu()
            );
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(SanPham entity) {
        try {
            jdbcHelper.update(UPDATE_SQL,
                    entity.getTenSanPham(), entity.getHangSanXuat(), entity.getXuatXu(), entity.getMauSac(),
                    entity.getSoLuong(), entity.getDonGia(), entity.getHinhAnh(), entity.getGhiChu(), entity.getMaSanPham()
            );
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String key) {
        try {
            jdbcHelper.update(DELETE_SQL, key);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete_SanPham(SanPham entity) {
        try {
            jdbcHelper.update(DELETE_SQL,
                    entity.getTenSanPham(), entity.getHangSanXuat(), entity.getXuatXu(), entity.getMauSac(),
                    entity.getSoLuong(), entity.getDonGia(), entity.getHinhAnh(), entity.getGhiChu(), entity.getMaSanPham()
            );
        } catch (Exception e) {
        }
    }

    @Override
    public SanPham selectByid(String key) {
        List<SanPham> list = (List<SanPham>) selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        return (List<SanPham>) this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSanPham(rs.getString("MaSanPham"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setHangSanXuat(rs.getString("HangSanXuat"));
                entity.setXuatXu(rs.getString("XuatXu"));
                entity.setMauSac(rs.getString("MauSac"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setDonGia(rs.getFloat("DonGia"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setGhiChu(rs.getString("GhiChu"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object> selectByHang() {
       List<Object> list_Hang = new ArrayList<Object>();
        try {
            ResultSet rs = jdbcHelper.query(SELECT_BY_HANG_SQL);
            while (rs.next()) {
                list_Hang.add(rs.getObject(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_Hang;
    }
    
    public List<Object> selectByMauSac() {
      List<Object> list_Mau = new ArrayList<Object>();
        try {
            ResultSet rs = jdbcHelper.query(SELECT_BY_MAUSAC_SQL);
            while (rs.next()) {
                list_Mau.add(rs.getObject(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_Mau;
    }
    
    public List<Object> selectByXuatXu() {
       List<Object> list_XuatXu = new ArrayList<Object>();
        try {
            ResultSet rs = jdbcHelper.query(SELECT_BY_XUATXU_SQL);
            while (rs.next()) {
                list_XuatXu.add(rs.getObject(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_XuatXu;
    }
    
    public List<SanPham> selectAll_TheoHangSanXuat(String hang) {
        return (List<SanPham>) this.selectBySql(SELECT_LOC_HANG_SQL, hang);
    }
    
    public List<SanPham> selectAll_TheoMauSac(String mau) {
        return (List<SanPham>) this.selectBySql(SELECT_LOC_MAUSAC_SQL, mau);
    }
    
    public List<SanPham> selectAll_TheoXuatXu(String xuatxu) {
        return (List<SanPham>) this.selectBySql(SELECT_LOC_XUATXU_SQL, xuatxu);
    }
}
