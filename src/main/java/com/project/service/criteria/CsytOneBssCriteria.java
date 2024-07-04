package com.project.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.project.domain.CsytOneBss} entity. This class is used
 * in {@link com.project.web.rest.CsytOneBssResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /csyt-one-bsses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CsytOneBssCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maCsyt;

    private StringFilter maThuebao;

    private StringFilter maDuan;

    private IntegerFilter trangthai;

    private LocalDateFilter thoigianCapnhat;

    private StringFilter maTinh;

    private StringFilter maSpdv;

    private StringFilter maHopdong;

    private LocalDateFilter ngaykyHopdong;

    private LocalDateFilter hieulucTu;

    private LocalDateFilter hieulucDen;

    private StringFilter tenKhachhang;

    private StringFilter diachiKhachhang;

    private StringFilter mota;

    private StringFilter lastUser;

    private ZonedDateTimeFilter lastModified;

    private Boolean distinct;

    public CsytOneBssCriteria() {}

    public CsytOneBssCriteria(CsytOneBssCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.maCsyt = other.optionalMaCsyt().map(StringFilter::copy).orElse(null);
        this.maThuebao = other.optionalMaThuebao().map(StringFilter::copy).orElse(null);
        this.maDuan = other.optionalMaDuan().map(StringFilter::copy).orElse(null);
        this.trangthai = other.optionalTrangthai().map(IntegerFilter::copy).orElse(null);
        this.thoigianCapnhat = other.optionalThoigianCapnhat().map(LocalDateFilter::copy).orElse(null);
        this.maTinh = other.optionalMaTinh().map(StringFilter::copy).orElse(null);
        this.maSpdv = other.optionalMaSpdv().map(StringFilter::copy).orElse(null);
        this.maHopdong = other.optionalMaHopdong().map(StringFilter::copy).orElse(null);
        this.ngaykyHopdong = other.optionalNgaykyHopdong().map(LocalDateFilter::copy).orElse(null);
        this.hieulucTu = other.optionalHieulucTu().map(LocalDateFilter::copy).orElse(null);
        this.hieulucDen = other.optionalHieulucDen().map(LocalDateFilter::copy).orElse(null);
        this.tenKhachhang = other.optionalTenKhachhang().map(StringFilter::copy).orElse(null);
        this.diachiKhachhang = other.optionalDiachiKhachhang().map(StringFilter::copy).orElse(null);
        this.mota = other.optionalMota().map(StringFilter::copy).orElse(null);
        this.lastUser = other.optionalLastUser().map(StringFilter::copy).orElse(null);
        this.lastModified = other.optionalLastModified().map(ZonedDateTimeFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CsytOneBssCriteria copy() {
        return new CsytOneBssCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaCsyt() {
        return maCsyt;
    }

    public Optional<StringFilter> optionalMaCsyt() {
        return Optional.ofNullable(maCsyt);
    }

    public StringFilter maCsyt() {
        if (maCsyt == null) {
            setMaCsyt(new StringFilter());
        }
        return maCsyt;
    }

    public void setMaCsyt(StringFilter maCsyt) {
        this.maCsyt = maCsyt;
    }

    public StringFilter getMaThuebao() {
        return maThuebao;
    }

    public Optional<StringFilter> optionalMaThuebao() {
        return Optional.ofNullable(maThuebao);
    }

    public StringFilter maThuebao() {
        if (maThuebao == null) {
            setMaThuebao(new StringFilter());
        }
        return maThuebao;
    }

    public void setMaThuebao(StringFilter maThuebao) {
        this.maThuebao = maThuebao;
    }

    public StringFilter getMaDuan() {
        return maDuan;
    }

    public Optional<StringFilter> optionalMaDuan() {
        return Optional.ofNullable(maDuan);
    }

    public StringFilter maDuan() {
        if (maDuan == null) {
            setMaDuan(new StringFilter());
        }
        return maDuan;
    }

    public void setMaDuan(StringFilter maDuan) {
        this.maDuan = maDuan;
    }

    public IntegerFilter getTrangthai() {
        return trangthai;
    }

    public Optional<IntegerFilter> optionalTrangthai() {
        return Optional.ofNullable(trangthai);
    }

    public IntegerFilter trangthai() {
        if (trangthai == null) {
            setTrangthai(new IntegerFilter());
        }
        return trangthai;
    }

    public void setTrangthai(IntegerFilter trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDateFilter getThoigianCapnhat() {
        return thoigianCapnhat;
    }

    public Optional<LocalDateFilter> optionalThoigianCapnhat() {
        return Optional.ofNullable(thoigianCapnhat);
    }

    public LocalDateFilter thoigianCapnhat() {
        if (thoigianCapnhat == null) {
            setThoigianCapnhat(new LocalDateFilter());
        }
        return thoigianCapnhat;
    }

    public void setThoigianCapnhat(LocalDateFilter thoigianCapnhat) {
        this.thoigianCapnhat = thoigianCapnhat;
    }

    public StringFilter getMaTinh() {
        return maTinh;
    }

    public Optional<StringFilter> optionalMaTinh() {
        return Optional.ofNullable(maTinh);
    }

    public StringFilter maTinh() {
        if (maTinh == null) {
            setMaTinh(new StringFilter());
        }
        return maTinh;
    }

    public void setMaTinh(StringFilter maTinh) {
        this.maTinh = maTinh;
    }

    public StringFilter getMaSpdv() {
        return maSpdv;
    }

    public Optional<StringFilter> optionalMaSpdv() {
        return Optional.ofNullable(maSpdv);
    }

    public StringFilter maSpdv() {
        if (maSpdv == null) {
            setMaSpdv(new StringFilter());
        }
        return maSpdv;
    }

    public void setMaSpdv(StringFilter maSpdv) {
        this.maSpdv = maSpdv;
    }

    public StringFilter getMaHopdong() {
        return maHopdong;
    }

    public Optional<StringFilter> optionalMaHopdong() {
        return Optional.ofNullable(maHopdong);
    }

    public StringFilter maHopdong() {
        if (maHopdong == null) {
            setMaHopdong(new StringFilter());
        }
        return maHopdong;
    }

    public void setMaHopdong(StringFilter maHopdong) {
        this.maHopdong = maHopdong;
    }

    public LocalDateFilter getNgaykyHopdong() {
        return ngaykyHopdong;
    }

    public Optional<LocalDateFilter> optionalNgaykyHopdong() {
        return Optional.ofNullable(ngaykyHopdong);
    }

    public LocalDateFilter ngaykyHopdong() {
        if (ngaykyHopdong == null) {
            setNgaykyHopdong(new LocalDateFilter());
        }
        return ngaykyHopdong;
    }

    public void setNgaykyHopdong(LocalDateFilter ngaykyHopdong) {
        this.ngaykyHopdong = ngaykyHopdong;
    }

    public LocalDateFilter getHieulucTu() {
        return hieulucTu;
    }

    public Optional<LocalDateFilter> optionalHieulucTu() {
        return Optional.ofNullable(hieulucTu);
    }

    public LocalDateFilter hieulucTu() {
        if (hieulucTu == null) {
            setHieulucTu(new LocalDateFilter());
        }
        return hieulucTu;
    }

    public void setHieulucTu(LocalDateFilter hieulucTu) {
        this.hieulucTu = hieulucTu;
    }

    public LocalDateFilter getHieulucDen() {
        return hieulucDen;
    }

    public Optional<LocalDateFilter> optionalHieulucDen() {
        return Optional.ofNullable(hieulucDen);
    }

    public LocalDateFilter hieulucDen() {
        if (hieulucDen == null) {
            setHieulucDen(new LocalDateFilter());
        }
        return hieulucDen;
    }

    public void setHieulucDen(LocalDateFilter hieulucDen) {
        this.hieulucDen = hieulucDen;
    }

    public StringFilter getTenKhachhang() {
        return tenKhachhang;
    }

    public Optional<StringFilter> optionalTenKhachhang() {
        return Optional.ofNullable(tenKhachhang);
    }

    public StringFilter tenKhachhang() {
        if (tenKhachhang == null) {
            setTenKhachhang(new StringFilter());
        }
        return tenKhachhang;
    }

    public void setTenKhachhang(StringFilter tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public StringFilter getDiachiKhachhang() {
        return diachiKhachhang;
    }

    public Optional<StringFilter> optionalDiachiKhachhang() {
        return Optional.ofNullable(diachiKhachhang);
    }

    public StringFilter diachiKhachhang() {
        if (diachiKhachhang == null) {
            setDiachiKhachhang(new StringFilter());
        }
        return diachiKhachhang;
    }

    public void setDiachiKhachhang(StringFilter diachiKhachhang) {
        this.diachiKhachhang = diachiKhachhang;
    }

    public StringFilter getMota() {
        return mota;
    }

    public Optional<StringFilter> optionalMota() {
        return Optional.ofNullable(mota);
    }

    public StringFilter mota() {
        if (mota == null) {
            setMota(new StringFilter());
        }
        return mota;
    }

    public void setMota(StringFilter mota) {
        this.mota = mota;
    }

    public StringFilter getLastUser() {
        return lastUser;
    }

    public Optional<StringFilter> optionalLastUser() {
        return Optional.ofNullable(lastUser);
    }

    public StringFilter lastUser() {
        if (lastUser == null) {
            setLastUser(new StringFilter());
        }
        return lastUser;
    }

    public void setLastUser(StringFilter lastUser) {
        this.lastUser = lastUser;
    }

    public ZonedDateTimeFilter getLastModified() {
        return lastModified;
    }

    public Optional<ZonedDateTimeFilter> optionalLastModified() {
        return Optional.ofNullable(lastModified);
    }

    public ZonedDateTimeFilter lastModified() {
        if (lastModified == null) {
            setLastModified(new ZonedDateTimeFilter());
        }
        return lastModified;
    }

    public void setLastModified(ZonedDateTimeFilter lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CsytOneBssCriteria that = (CsytOneBssCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maCsyt, that.maCsyt) &&
            Objects.equals(maThuebao, that.maThuebao) &&
            Objects.equals(maDuan, that.maDuan) &&
            Objects.equals(trangthai, that.trangthai) &&
            Objects.equals(thoigianCapnhat, that.thoigianCapnhat) &&
            Objects.equals(maTinh, that.maTinh) &&
            Objects.equals(maSpdv, that.maSpdv) &&
            Objects.equals(maHopdong, that.maHopdong) &&
            Objects.equals(ngaykyHopdong, that.ngaykyHopdong) &&
            Objects.equals(hieulucTu, that.hieulucTu) &&
            Objects.equals(hieulucDen, that.hieulucDen) &&
            Objects.equals(tenKhachhang, that.tenKhachhang) &&
            Objects.equals(diachiKhachhang, that.diachiKhachhang) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(lastUser, that.lastUser) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            maCsyt,
            maThuebao,
            maDuan,
            trangthai,
            thoigianCapnhat,
            maTinh,
            maSpdv,
            maHopdong,
            ngaykyHopdong,
            hieulucTu,
            hieulucDen,
            tenKhachhang,
            diachiKhachhang,
            mota,
            lastUser,
            lastModified,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsytOneBssCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalMaCsyt().map(f -> "maCsyt=" + f + ", ").orElse("") +
            optionalMaThuebao().map(f -> "maThuebao=" + f + ", ").orElse("") +
            optionalMaDuan().map(f -> "maDuan=" + f + ", ").orElse("") +
            optionalTrangthai().map(f -> "trangthai=" + f + ", ").orElse("") +
            optionalThoigianCapnhat().map(f -> "thoigianCapnhat=" + f + ", ").orElse("") +
            optionalMaTinh().map(f -> "maTinh=" + f + ", ").orElse("") +
            optionalMaSpdv().map(f -> "maSpdv=" + f + ", ").orElse("") +
            optionalMaHopdong().map(f -> "maHopdong=" + f + ", ").orElse("") +
            optionalNgaykyHopdong().map(f -> "ngaykyHopdong=" + f + ", ").orElse("") +
            optionalHieulucTu().map(f -> "hieulucTu=" + f + ", ").orElse("") +
            optionalHieulucDen().map(f -> "hieulucDen=" + f + ", ").orElse("") +
            optionalTenKhachhang().map(f -> "tenKhachhang=" + f + ", ").orElse("") +
            optionalDiachiKhachhang().map(f -> "diachiKhachhang=" + f + ", ").orElse("") +
            optionalMota().map(f -> "mota=" + f + ", ").orElse("") +
            optionalLastUser().map(f -> "lastUser=" + f + ", ").orElse("") +
            optionalLastModified().map(f -> "lastModified=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
