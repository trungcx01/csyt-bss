package com.project.service;

import com.project.domain.CsytOneBss;
import com.project.repository.CsytOneBssRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.project.domain.CsytOneBss}.
 */
@Service
@Transactional
public class CsytOneBssService {

    private static final Logger log = LoggerFactory.getLogger(CsytOneBssService.class);

    private final CsytOneBssRepository csytOneBssRepository;

    public CsytOneBssService(CsytOneBssRepository csytOneBssRepository) {
        this.csytOneBssRepository = csytOneBssRepository;
    }

    /**
     * Save a csytOneBss.
     *
     * @param csytOneBss the entity to save.
     * @return the persisted entity.
     */
    public CsytOneBss save(CsytOneBss csytOneBss) {
        log.debug("Request to save CsytOneBss : {}", csytOneBss);
        return csytOneBssRepository.save(csytOneBss);
    }

    /**
     * Update a csytOneBss.
     *
     * @param csytOneBss the entity to save.
     * @return the persisted entity.
     */
    public CsytOneBss update(CsytOneBss csytOneBss) {
        log.debug("Request to update CsytOneBss : {}", csytOneBss);
        return csytOneBssRepository.save(csytOneBss);
    }

    /**
     * Partially update a csytOneBss.
     *
     * @param csytOneBss the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CsytOneBss> partialUpdate(CsytOneBss csytOneBss) {
        log.debug("Request to partially update CsytOneBss : {}", csytOneBss);

        return csytOneBssRepository
            .findById(csytOneBss.getId())
            .map(existingCsytOneBss -> {
                if (csytOneBss.getMaCsyt() != null) {
                    existingCsytOneBss.setMaCsyt(csytOneBss.getMaCsyt());
                }
                if (csytOneBss.getMaThuebao() != null) {
                    existingCsytOneBss.setMaThuebao(csytOneBss.getMaThuebao());
                }
                if (csytOneBss.getMaDuan() != null) {
                    existingCsytOneBss.setMaDuan(csytOneBss.getMaDuan());
                }
                if (csytOneBss.getTrangthai() != null) {
                    existingCsytOneBss.setTrangthai(csytOneBss.getTrangthai());
                }
                if (csytOneBss.getThoigianCapnhat() != null) {
                    existingCsytOneBss.setThoigianCapnhat(csytOneBss.getThoigianCapnhat());
                }
                if (csytOneBss.getMaTinh() != null) {
                    existingCsytOneBss.setMaTinh(csytOneBss.getMaTinh());
                }
                if (csytOneBss.getMaSpdv() != null) {
                    existingCsytOneBss.setMaSpdv(csytOneBss.getMaSpdv());
                }
                if (csytOneBss.getMaHopdong() != null) {
                    existingCsytOneBss.setMaHopdong(csytOneBss.getMaHopdong());
                }
                if (csytOneBss.getNgaykyHopdong() != null) {
                    existingCsytOneBss.setNgaykyHopdong(csytOneBss.getNgaykyHopdong());
                }
                if (csytOneBss.getHieulucTu() != null) {
                    existingCsytOneBss.setHieulucTu(csytOneBss.getHieulucTu());
                }
                if (csytOneBss.getHieulucDen() != null) {
                    existingCsytOneBss.setHieulucDen(csytOneBss.getHieulucDen());
                }
                if (csytOneBss.getTenKhachhang() != null) {
                    existingCsytOneBss.setTenKhachhang(csytOneBss.getTenKhachhang());
                }
                if (csytOneBss.getDiachiKhachhang() != null) {
                    existingCsytOneBss.setDiachiKhachhang(csytOneBss.getDiachiKhachhang());
                }
                if (csytOneBss.getMota() != null) {
                    existingCsytOneBss.setMota(csytOneBss.getMota());
                }
                if (csytOneBss.getLastUser() != null) {
                    existingCsytOneBss.setLastUser(csytOneBss.getLastUser());
                }
                if (csytOneBss.getLastModified() != null) {
                    existingCsytOneBss.setLastModified(csytOneBss.getLastModified());
                }

                return existingCsytOneBss;
            })
            .map(csytOneBssRepository::save);
    }

    /**
     * Get one csytOneBss by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CsytOneBss> findOne(Long id) {
        log.debug("Request to get CsytOneBss : {}", id);
        return csytOneBssRepository.findById(id);
    }

    /**
     * Delete the csytOneBss by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CsytOneBss : {}", id);
        csytOneBssRepository.deleteById(id);
    }
}
