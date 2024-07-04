package com.project.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CsytOneBssCriteriaTest {

    @Test
    void newCsytOneBssCriteriaHasAllFiltersNullTest() {
        var csytOneBssCriteria = new CsytOneBssCriteria();
        assertThat(csytOneBssCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void csytOneBssCriteriaFluentMethodsCreatesFiltersTest() {
        var csytOneBssCriteria = new CsytOneBssCriteria();

        setAllFilters(csytOneBssCriteria);

        assertThat(csytOneBssCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void csytOneBssCriteriaCopyCreatesNullFilterTest() {
        var csytOneBssCriteria = new CsytOneBssCriteria();
        var copy = csytOneBssCriteria.copy();

        assertThat(csytOneBssCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(csytOneBssCriteria)
        );
    }

    @Test
    void csytOneBssCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var csytOneBssCriteria = new CsytOneBssCriteria();
        setAllFilters(csytOneBssCriteria);

        var copy = csytOneBssCriteria.copy();

        assertThat(csytOneBssCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(csytOneBssCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var csytOneBssCriteria = new CsytOneBssCriteria();

        assertThat(csytOneBssCriteria).hasToString("CsytOneBssCriteria{}");
    }

    private static void setAllFilters(CsytOneBssCriteria csytOneBssCriteria) {
        csytOneBssCriteria.id();
        csytOneBssCriteria.maCsyt();
        csytOneBssCriteria.maThuebao();
        csytOneBssCriteria.maDuan();
        csytOneBssCriteria.trangthai();
        csytOneBssCriteria.thoigianCapnhat();
        csytOneBssCriteria.maTinh();
        csytOneBssCriteria.maSpdv();
        csytOneBssCriteria.maHopdong();
        csytOneBssCriteria.ngaykyHopdong();
        csytOneBssCriteria.hieulucTu();
        csytOneBssCriteria.hieulucDen();
        csytOneBssCriteria.tenKhachhang();
        csytOneBssCriteria.diachiKhachhang();
        csytOneBssCriteria.mota();
        csytOneBssCriteria.lastUser();
        csytOneBssCriteria.lastModified();
        csytOneBssCriteria.distinct();
    }

    private static Condition<CsytOneBssCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getMaCsyt()) &&
                condition.apply(criteria.getMaThuebao()) &&
                condition.apply(criteria.getMaDuan()) &&
                condition.apply(criteria.getTrangthai()) &&
                condition.apply(criteria.getThoigianCapnhat()) &&
                condition.apply(criteria.getMaTinh()) &&
                condition.apply(criteria.getMaSpdv()) &&
                condition.apply(criteria.getMaHopdong()) &&
                condition.apply(criteria.getNgaykyHopdong()) &&
                condition.apply(criteria.getHieulucTu()) &&
                condition.apply(criteria.getHieulucDen()) &&
                condition.apply(criteria.getTenKhachhang()) &&
                condition.apply(criteria.getDiachiKhachhang()) &&
                condition.apply(criteria.getMota()) &&
                condition.apply(criteria.getLastUser()) &&
                condition.apply(criteria.getLastModified()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CsytOneBssCriteria> copyFiltersAre(CsytOneBssCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getMaCsyt(), copy.getMaCsyt()) &&
                condition.apply(criteria.getMaThuebao(), copy.getMaThuebao()) &&
                condition.apply(criteria.getMaDuan(), copy.getMaDuan()) &&
                condition.apply(criteria.getTrangthai(), copy.getTrangthai()) &&
                condition.apply(criteria.getThoigianCapnhat(), copy.getThoigianCapnhat()) &&
                condition.apply(criteria.getMaTinh(), copy.getMaTinh()) &&
                condition.apply(criteria.getMaSpdv(), copy.getMaSpdv()) &&
                condition.apply(criteria.getMaHopdong(), copy.getMaHopdong()) &&
                condition.apply(criteria.getNgaykyHopdong(), copy.getNgaykyHopdong()) &&
                condition.apply(criteria.getHieulucTu(), copy.getHieulucTu()) &&
                condition.apply(criteria.getHieulucDen(), copy.getHieulucDen()) &&
                condition.apply(criteria.getTenKhachhang(), copy.getTenKhachhang()) &&
                condition.apply(criteria.getDiachiKhachhang(), copy.getDiachiKhachhang()) &&
                condition.apply(criteria.getMota(), copy.getMota()) &&
                condition.apply(criteria.getLastUser(), copy.getLastUser()) &&
                condition.apply(criteria.getLastModified(), copy.getLastModified()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
