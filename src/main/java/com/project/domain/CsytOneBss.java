package com.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A CsytOneBss.
 */
@Entity
@Table(name = "csyt_one_bss")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CsytOneBss implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "ma_csyt", length = 50, nullable = false)
    private String maCsyt;

    @NotNull
    @Size(max = 50)
    @Column(name = "ma_thuebao", length = 50, nullable = false)
    private String maThuebao;

    @NotNull
    @Size(max = 50)
    @Column(name = "ma_duan", length = 50, nullable = false)
    private String maDuan;

    @NotNull
    @Column(name = "trangthai", nullable = false)
    private Integer trangthai;

    @NotNull
    @Column(name = "thoigian_capnhat", nullable = false)
    private LocalDate thoigianCapnhat;

    @Size(max = 50)
    @Column(name = "ma_tinh", length = 50)
    private String maTinh;

    @Size(max = 50)
    @Column(name = "ma_spdv", length = 50)
    private String maSpdv;

    @Size(max = 50)
    @Column(name = "ma_hopdong", length = 50)
    private String maHopdong;

    @Column(name = "ngayky_hopdong")
    private LocalDate ngaykyHopdong;

    @Column(name = "hieuluc_tu")
    private LocalDate hieulucTu;

    @Column(name = "hieuluc_den")
    private LocalDate hieulucDen;

    @Size(max = 255)
    @Column(name = "ten_khachhang", length = 255)
    private String tenKhachhang;

    @Size(max = 255)
    @Column(name = "diachi_khachhang", length = 255)
    private String diachiKhachhang;

    @Size(max = 4000)
    @Column(name = "mota", length = 4000)
    private String mota;

    @Size(max = 255)
    @Column(name = "last_user", length = 255)
    private String lastUser;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CsytOneBss id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaCsyt() {
        return this.maCsyt;
    }

    public CsytOneBss maCsyt(String maCsyt) {
        this.setMaCsyt(maCsyt);
        return this;
    }

    public void setMaCsyt(String maCsyt) {
        this.maCsyt = maCsyt;
    }

    public String getMaThuebao() {
        return this.maThuebao;
    }

    public CsytOneBss maThuebao(String maThuebao) {
        this.setMaThuebao(maThuebao);
        return this;
    }

    public void setMaThuebao(String maThuebao) {
        this.maThuebao = maThuebao;
    }

    public String getMaDuan() {
        return this.maDuan;
    }

    public CsytOneBss maDuan(String maDuan) {
        this.setMaDuan(maDuan);
        return this;
    }

    public void setMaDuan(String maDuan) {
        this.maDuan = maDuan;
    }

    public Integer getTrangthai() {
        return this.trangthai;
    }

    public CsytOneBss trangthai(Integer trangthai) {
        this.setTrangthai(trangthai);
        return this;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDate getThoigianCapnhat() {
        return this.thoigianCapnhat;
    }

    public CsytOneBss thoigianCapnhat(LocalDate thoigianCapnhat) {
        this.setThoigianCapnhat(thoigianCapnhat);
        return this;
    }

    public void setThoigianCapnhat(LocalDate thoigianCapnhat) {
        this.thoigianCapnhat = thoigianCapnhat;
    }

    public String getMaTinh() {
        return this.maTinh;
    }

    public CsytOneBss maTinh(String maTinh) {
        this.setMaTinh(maTinh);
        return this;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getMaSpdv() {
        return this.maSpdv;
    }

    public CsytOneBss maSpdv(String maSpdv) {
        this.setMaSpdv(maSpdv);
        return this;
    }

    public void setMaSpdv(String maSpdv) {
        this.maSpdv = maSpdv;
    }

    public String getMaHopdong() {
        return this.maHopdong;
    }

    public CsytOneBss maHopdong(String maHopdong) {
        this.setMaHopdong(maHopdong);
        return this;
    }

    public void setMaHopdong(String maHopdong) {
        this.maHopdong = maHopdong;
    }

    public LocalDate getNgaykyHopdong() {
        return this.ngaykyHopdong;
    }

    public CsytOneBss ngaykyHopdong(LocalDate ngaykyHopdong) {
        this.setNgaykyHopdong(ngaykyHopdong);
        return this;
    }

    public void setNgaykyHopdong(LocalDate ngaykyHopdong) {
        this.ngaykyHopdong = ngaykyHopdong;
    }

    public LocalDate getHieulucTu() {
        return this.hieulucTu;
    }

    public CsytOneBss hieulucTu(LocalDate hieulucTu) {
        this.setHieulucTu(hieulucTu);
        return this;
    }

    public void setHieulucTu(LocalDate hieulucTu) {
        this.hieulucTu = hieulucTu;
    }

    public LocalDate getHieulucDen() {
        return this.hieulucDen;
    }

    public CsytOneBss hieulucDen(LocalDate hieulucDen) {
        this.setHieulucDen(hieulucDen);
        return this;
    }

    public void setHieulucDen(LocalDate hieulucDen) {
        this.hieulucDen = hieulucDen;
    }

    public String getTenKhachhang() {
        return this.tenKhachhang;
    }

    public CsytOneBss tenKhachhang(String tenKhachhang) {
        this.setTenKhachhang(tenKhachhang);
        return this;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public String getDiachiKhachhang() {
        return this.diachiKhachhang;
    }

    public CsytOneBss diachiKhachhang(String diachiKhachhang) {
        this.setDiachiKhachhang(diachiKhachhang);
        return this;
    }

    public void setDiachiKhachhang(String diachiKhachhang) {
        this.diachiKhachhang = diachiKhachhang;
    }

    public String getMota() {
        return this.mota;
    }

    public CsytOneBss mota(String mota) {
        this.setMota(mota);
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLastUser() {
        return this.lastUser;
    }

    public CsytOneBss lastUser(String lastUser) {
        this.setLastUser(lastUser);
        return this;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public ZonedDateTime getLastModified() {
        return this.lastModified;
    }

    public CsytOneBss lastModified(ZonedDateTime lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CsytOneBss)) {
            return false;
        }
        return getId() != null && getId().equals(((CsytOneBss) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsytOneBss{" +
            "id=" + getId() +
            ", maCsyt='" + getMaCsyt() + "'" +
            ", maThuebao='" + getMaThuebao() + "'" +
            ", maDuan='" + getMaDuan() + "'" +
            ", trangthai=" + getTrangthai() +
            ", thoigianCapnhat='" + getThoigianCapnhat() + "'" +
            ", maTinh='" + getMaTinh() + "'" +
            ", maSpdv='" + getMaSpdv() + "'" +
            ", maHopdong='" + getMaHopdong() + "'" +
            ", ngaykyHopdong='" + getNgaykyHopdong() + "'" +
            ", hieulucTu='" + getHieulucTu() + "'" +
            ", hieulucDen='" + getHieulucDen() + "'" +
            ", tenKhachhang='" + getTenKhachhang() + "'" +
            ", diachiKhachhang='" + getDiachiKhachhang() + "'" +
            ", mota='" + getMota() + "'" +
            ", lastUser='" + getLastUser() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
