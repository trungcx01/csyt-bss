package com.project.service;

import com.project.domain.*; // for static metamodels
import com.project.domain.CsytOneBss;
import com.project.repository.CsytOneBssRepository;
import com.project.service.criteria.CsytOneBssCriteria;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link CsytOneBss} entities in the database.
 * The main input is a {@link CsytOneBssCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CsytOneBss} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CsytOneBssQueryService extends QueryService<CsytOneBss> {

    private static final Logger log = LoggerFactory.getLogger(CsytOneBssQueryService.class);

    private final CsytOneBssRepository csytOneBssRepository;

    public CsytOneBssQueryService(CsytOneBssRepository csytOneBssRepository) {
        this.csytOneBssRepository = csytOneBssRepository;
    }

    /**
     * Return a {@link List} of {@link CsytOneBss} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CsytOneBss> findByCriteria(CsytOneBssCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CsytOneBss> specification = createSpecification(criteria);
        return csytOneBssRepository.findAll(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CsytOneBssCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CsytOneBss> specification = createSpecification(criteria);
        return csytOneBssRepository.count(specification);
    }

    /**
     * Function to convert {@link CsytOneBssCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CsytOneBss> createSpecification(CsytOneBssCriteria criteria) {
        Specification<CsytOneBss> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CsytOneBss_.id));
            }
            if (criteria.getMaCsyt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaCsyt(), CsytOneBss_.maCsyt));
            }
            if (criteria.getMaThuebao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaThuebao(), CsytOneBss_.maThuebao));
            }
            if (criteria.getMaDuan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaDuan(), CsytOneBss_.maDuan));
            }
            if (criteria.getTrangthai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthai(), CsytOneBss_.trangthai));
            }
            if (criteria.getThoigianCapnhat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThoigianCapnhat(), CsytOneBss_.thoigianCapnhat));
            }
            if (criteria.getMaTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTinh(), CsytOneBss_.maTinh));
            }
            if (criteria.getMaSpdv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaSpdv(), CsytOneBss_.maSpdv));
            }
            if (criteria.getMaHopdong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaHopdong(), CsytOneBss_.maHopdong));
            }
            if (criteria.getNgaykyHopdong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaykyHopdong(), CsytOneBss_.ngaykyHopdong));
            }
            if (criteria.getHieulucTu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHieulucTu(), CsytOneBss_.hieulucTu));
            }
            if (criteria.getHieulucDen() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHieulucDen(), CsytOneBss_.hieulucDen));
            }
            if (criteria.getTenKhachhang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenKhachhang(), CsytOneBss_.tenKhachhang));
            }
            if (criteria.getDiachiKhachhang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiachiKhachhang(), CsytOneBss_.diachiKhachhang));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), CsytOneBss_.mota));
            }
            if (criteria.getLastUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastUser(), CsytOneBss_.lastUser));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModified(), CsytOneBss_.lastModified));
            }
        }
        return specification;
    }
}
