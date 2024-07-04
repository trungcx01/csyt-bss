package com.project.repository;

import com.project.domain.CsytOneBss;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CsytOneBss entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsytOneBssRepository extends JpaRepository<CsytOneBss, Long>, JpaSpecificationExecutor<CsytOneBss> {}
