package com.project.web.rest;

import com.project.domain.CsytOneBss;
import com.project.repository.CsytOneBssRepository;
import com.project.service.CsytOneBssQueryService;
import com.project.service.CsytOneBssService;
import com.project.service.criteria.CsytOneBssCriteria;
import com.project.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.project.domain.CsytOneBss}.
 */
@RestController
@RequestMapping("/api/csyt-one-bsses")
public class CsytOneBssResource {

    private static final Logger log = LoggerFactory.getLogger(CsytOneBssResource.class);

    private static final String ENTITY_NAME = "csytOneBss";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CsytOneBssService csytOneBssService;

    private final CsytOneBssRepository csytOneBssRepository;

    private final CsytOneBssQueryService csytOneBssQueryService;

    public CsytOneBssResource(
        CsytOneBssService csytOneBssService,
        CsytOneBssRepository csytOneBssRepository,
        CsytOneBssQueryService csytOneBssQueryService
    ) {
        this.csytOneBssService = csytOneBssService;
        this.csytOneBssRepository = csytOneBssRepository;
        this.csytOneBssQueryService = csytOneBssQueryService;
    }

    /**
     * {@code POST  /csyt-one-bsses} : Create a new csytOneBss.
     *
     * @param csytOneBss the csytOneBss to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new csytOneBss, or with status {@code 400 (Bad Request)} if the csytOneBss has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CsytOneBss> createCsytOneBss(@Valid @RequestBody CsytOneBss csytOneBss) throws URISyntaxException {
        log.debug("REST request to save CsytOneBss : {}", csytOneBss);
        if (csytOneBss.getId() != null) {
            throw new BadRequestAlertException("A new csytOneBss cannot already have an ID", ENTITY_NAME, "idexists");
        }
        csytOneBss = csytOneBssService.save(csytOneBss);
        return ResponseEntity.created(new URI("/api/csyt-one-bsses/" + csytOneBss.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, csytOneBss.getId().toString()))
            .body(csytOneBss);
    }

    /**
     * {@code PUT  /csyt-one-bsses/:id} : Updates an existing csytOneBss.
     *
     * @param id the id of the csytOneBss to save.
     * @param csytOneBss the csytOneBss to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csytOneBss,
     * or with status {@code 400 (Bad Request)} if the csytOneBss is not valid,
     * or with status {@code 500 (Internal Server Error)} if the csytOneBss couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CsytOneBss> updateCsytOneBss(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CsytOneBss csytOneBss
    ) throws URISyntaxException {
        log.debug("REST request to update CsytOneBss : {}, {}", id, csytOneBss);
        if (csytOneBss.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, csytOneBss.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!csytOneBssRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        csytOneBss = csytOneBssService.update(csytOneBss);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csytOneBss.getId().toString()))
            .body(csytOneBss);
    }

    /**
     * {@code PATCH  /csyt-one-bsses/:id} : Partial updates given fields of an existing csytOneBss, field will ignore if it is null
     *
     * @param id the id of the csytOneBss to save.
     * @param csytOneBss the csytOneBss to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csytOneBss,
     * or with status {@code 400 (Bad Request)} if the csytOneBss is not valid,
     * or with status {@code 404 (Not Found)} if the csytOneBss is not found,
     * or with status {@code 500 (Internal Server Error)} if the csytOneBss couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CsytOneBss> partialUpdateCsytOneBss(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CsytOneBss csytOneBss
    ) throws URISyntaxException {
        log.debug("REST request to partial update CsytOneBss partially : {}, {}", id, csytOneBss);
        if (csytOneBss.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, csytOneBss.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!csytOneBssRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CsytOneBss> result = csytOneBssService.partialUpdate(csytOneBss);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csytOneBss.getId().toString())
        );
    }

    /**
     * {@code GET  /csyt-one-bsses} : get all the csytOneBsses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of csytOneBsses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CsytOneBss>> getAllCsytOneBsses(CsytOneBssCriteria criteria) {
        log.debug("REST request to get CsytOneBsses by criteria: {}", criteria);

        List<CsytOneBss> entityList = csytOneBssQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /csyt-one-bsses/count} : count all the csytOneBsses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCsytOneBsses(CsytOneBssCriteria criteria) {
        log.debug("REST request to count CsytOneBsses by criteria: {}", criteria);
        return ResponseEntity.ok().body(csytOneBssQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /csyt-one-bsses/:id} : get the "id" csytOneBss.
     *
     * @param id the id of the csytOneBss to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the csytOneBss, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CsytOneBss> getCsytOneBss(@PathVariable("id") Long id) {
        log.debug("REST request to get CsytOneBss : {}", id);
        Optional<CsytOneBss> csytOneBss = csytOneBssService.findOne(id);
        return ResponseUtil.wrapOrNotFound(csytOneBss);
    }

    /**
     * {@code DELETE  /csyt-one-bsses/:id} : delete the "id" csytOneBss.
     *
     * @param id the id of the csytOneBss to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCsytOneBss(@PathVariable("id") Long id) {
        log.debug("REST request to delete CsytOneBss : {}", id);
        csytOneBssService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
