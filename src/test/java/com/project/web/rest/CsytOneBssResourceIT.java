package com.project.web.rest;

import static com.project.domain.CsytOneBssAsserts.*;
import static com.project.web.rest.TestUtil.createUpdateProxyForBean;
import static com.project.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.IntegrationTest;
import com.project.domain.CsytOneBss;
import com.project.repository.CsytOneBssRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CsytOneBssResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CsytOneBssResourceIT {

    private static final String DEFAULT_MA_CSYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_CSYT = "BBBBBBBBBB";

    private static final String DEFAULT_MA_THUEBAO = "AAAAAAAAAA";
    private static final String UPDATED_MA_THUEBAO = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DUAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_DUAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANGTHAI = 1;
    private static final Integer UPDATED_TRANGTHAI = 2;
    private static final Integer SMALLER_TRANGTHAI = 1 - 1;

    private static final LocalDate DEFAULT_THOIGIAN_CAPNHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_THOIGIAN_CAPNHAT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_THOIGIAN_CAPNHAT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_MA_TINH = "AAAAAAAAAA";
    private static final String UPDATED_MA_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_MA_SPDV = "AAAAAAAAAA";
    private static final String UPDATED_MA_SPDV = "BBBBBBBBBB";

    private static final String DEFAULT_MA_HOPDONG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HOPDONG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAYKY_HOPDONG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYKY_HOPDONG = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYKY_HOPDONG = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_HIEULUC_TU = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIEULUC_TU = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_HIEULUC_TU = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_HIEULUC_DEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIEULUC_DEN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_HIEULUC_DEN = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_TEN_KHACHHANG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KHACHHANG = "BBBBBBBBBB";

    private static final String DEFAULT_DIACHI_KHACHHANG = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI_KHACHHANG = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_USER = "AAAAAAAAAA";
    private static final String UPDATED_LAST_USER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFIED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_LAST_MODIFIED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String ENTITY_API_URL = "/api/csyt-one-bsses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CsytOneBssRepository csytOneBssRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCsytOneBssMockMvc;

    private CsytOneBss csytOneBss;

    private CsytOneBss insertedCsytOneBss;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsytOneBss createEntity(EntityManager em) {
        CsytOneBss csytOneBss = new CsytOneBss()
            .maCsyt(DEFAULT_MA_CSYT)
            .maThuebao(DEFAULT_MA_THUEBAO)
            .maDuan(DEFAULT_MA_DUAN)
            .trangthai(DEFAULT_TRANGTHAI)
            .thoigianCapnhat(DEFAULT_THOIGIAN_CAPNHAT)
            .maTinh(DEFAULT_MA_TINH)
            .maSpdv(DEFAULT_MA_SPDV)
            .maHopdong(DEFAULT_MA_HOPDONG)
            .ngaykyHopdong(DEFAULT_NGAYKY_HOPDONG)
            .hieulucTu(DEFAULT_HIEULUC_TU)
            .hieulucDen(DEFAULT_HIEULUC_DEN)
            .tenKhachhang(DEFAULT_TEN_KHACHHANG)
            .diachiKhachhang(DEFAULT_DIACHI_KHACHHANG)
            .mota(DEFAULT_MOTA)
            .lastUser(DEFAULT_LAST_USER)
            .lastModified(DEFAULT_LAST_MODIFIED);
        return csytOneBss;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsytOneBss createUpdatedEntity(EntityManager em) {
        CsytOneBss csytOneBss = new CsytOneBss()
            .maCsyt(UPDATED_MA_CSYT)
            .maThuebao(UPDATED_MA_THUEBAO)
            .maDuan(UPDATED_MA_DUAN)
            .trangthai(UPDATED_TRANGTHAI)
            .thoigianCapnhat(UPDATED_THOIGIAN_CAPNHAT)
            .maTinh(UPDATED_MA_TINH)
            .maSpdv(UPDATED_MA_SPDV)
            .maHopdong(UPDATED_MA_HOPDONG)
            .ngaykyHopdong(UPDATED_NGAYKY_HOPDONG)
            .hieulucTu(UPDATED_HIEULUC_TU)
            .hieulucDen(UPDATED_HIEULUC_DEN)
            .tenKhachhang(UPDATED_TEN_KHACHHANG)
            .diachiKhachhang(UPDATED_DIACHI_KHACHHANG)
            .mota(UPDATED_MOTA)
            .lastUser(UPDATED_LAST_USER)
            .lastModified(UPDATED_LAST_MODIFIED);
        return csytOneBss;
    }

    @BeforeEach
    public void initTest() {
        csytOneBss = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCsytOneBss != null) {
            csytOneBssRepository.delete(insertedCsytOneBss);
            insertedCsytOneBss = null;
        }
    }

    @Test
    @Transactional
    void createCsytOneBss() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CsytOneBss
        var returnedCsytOneBss = om.readValue(
            restCsytOneBssMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CsytOneBss.class
        );

        // Validate the CsytOneBss in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCsytOneBssUpdatableFieldsEquals(returnedCsytOneBss, getPersistedCsytOneBss(returnedCsytOneBss));

        insertedCsytOneBss = returnedCsytOneBss;
    }

    @Test
    @Transactional
    void createCsytOneBssWithExistingId() throws Exception {
        // Create the CsytOneBss with an existing ID
        csytOneBss.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsytOneBssMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isBadRequest());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMaCsytIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        csytOneBss.setMaCsyt(null);

        // Create the CsytOneBss, which fails.

        restCsytOneBssMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaThuebaoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        csytOneBss.setMaThuebao(null);

        // Create the CsytOneBss, which fails.

        restCsytOneBssMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaDuanIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        csytOneBss.setMaDuan(null);

        // Create the CsytOneBss, which fails.

        restCsytOneBssMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTrangthaiIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        csytOneBss.setTrangthai(null);

        // Create the CsytOneBss, which fails.

        restCsytOneBssMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkThoigianCapnhatIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        csytOneBss.setThoigianCapnhat(null);

        // Create the CsytOneBss, which fails.

        restCsytOneBssMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCsytOneBsses() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList
        restCsytOneBssMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csytOneBss.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCsyt").value(hasItem(DEFAULT_MA_CSYT)))
            .andExpect(jsonPath("$.[*].maThuebao").value(hasItem(DEFAULT_MA_THUEBAO)))
            .andExpect(jsonPath("$.[*].maDuan").value(hasItem(DEFAULT_MA_DUAN)))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].thoigianCapnhat").value(hasItem(DEFAULT_THOIGIAN_CAPNHAT.toString())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH)))
            .andExpect(jsonPath("$.[*].maSpdv").value(hasItem(DEFAULT_MA_SPDV)))
            .andExpect(jsonPath("$.[*].maHopdong").value(hasItem(DEFAULT_MA_HOPDONG)))
            .andExpect(jsonPath("$.[*].ngaykyHopdong").value(hasItem(DEFAULT_NGAYKY_HOPDONG.toString())))
            .andExpect(jsonPath("$.[*].hieulucTu").value(hasItem(DEFAULT_HIEULUC_TU.toString())))
            .andExpect(jsonPath("$.[*].hieulucDen").value(hasItem(DEFAULT_HIEULUC_DEN.toString())))
            .andExpect(jsonPath("$.[*].tenKhachhang").value(hasItem(DEFAULT_TEN_KHACHHANG)))
            .andExpect(jsonPath("$.[*].diachiKhachhang").value(hasItem(DEFAULT_DIACHI_KHACHHANG)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].lastUser").value(hasItem(DEFAULT_LAST_USER)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED))));
    }

    @Test
    @Transactional
    void getCsytOneBss() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get the csytOneBss
        restCsytOneBssMockMvc
            .perform(get(ENTITY_API_URL_ID, csytOneBss.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(csytOneBss.getId().intValue()))
            .andExpect(jsonPath("$.maCsyt").value(DEFAULT_MA_CSYT))
            .andExpect(jsonPath("$.maThuebao").value(DEFAULT_MA_THUEBAO))
            .andExpect(jsonPath("$.maDuan").value(DEFAULT_MA_DUAN))
            .andExpect(jsonPath("$.trangthai").value(DEFAULT_TRANGTHAI))
            .andExpect(jsonPath("$.thoigianCapnhat").value(DEFAULT_THOIGIAN_CAPNHAT.toString()))
            .andExpect(jsonPath("$.maTinh").value(DEFAULT_MA_TINH))
            .andExpect(jsonPath("$.maSpdv").value(DEFAULT_MA_SPDV))
            .andExpect(jsonPath("$.maHopdong").value(DEFAULT_MA_HOPDONG))
            .andExpect(jsonPath("$.ngaykyHopdong").value(DEFAULT_NGAYKY_HOPDONG.toString()))
            .andExpect(jsonPath("$.hieulucTu").value(DEFAULT_HIEULUC_TU.toString()))
            .andExpect(jsonPath("$.hieulucDen").value(DEFAULT_HIEULUC_DEN.toString()))
            .andExpect(jsonPath("$.tenKhachhang").value(DEFAULT_TEN_KHACHHANG))
            .andExpect(jsonPath("$.diachiKhachhang").value(DEFAULT_DIACHI_KHACHHANG))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.lastUser").value(DEFAULT_LAST_USER))
            .andExpect(jsonPath("$.lastModified").value(sameInstant(DEFAULT_LAST_MODIFIED)));
    }

    @Test
    @Transactional
    void getCsytOneBssesByIdFiltering() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        Long id = csytOneBss.getId();

        defaultCsytOneBssFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultCsytOneBssFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultCsytOneBssFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaCsytIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maCsyt equals to
        defaultCsytOneBssFiltering("maCsyt.equals=" + DEFAULT_MA_CSYT, "maCsyt.equals=" + UPDATED_MA_CSYT);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaCsytIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maCsyt in
        defaultCsytOneBssFiltering("maCsyt.in=" + DEFAULT_MA_CSYT + "," + UPDATED_MA_CSYT, "maCsyt.in=" + UPDATED_MA_CSYT);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaCsytIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maCsyt is not null
        defaultCsytOneBssFiltering("maCsyt.specified=true", "maCsyt.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaCsytContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maCsyt contains
        defaultCsytOneBssFiltering("maCsyt.contains=" + DEFAULT_MA_CSYT, "maCsyt.contains=" + UPDATED_MA_CSYT);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaCsytNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maCsyt does not contain
        defaultCsytOneBssFiltering("maCsyt.doesNotContain=" + UPDATED_MA_CSYT, "maCsyt.doesNotContain=" + DEFAULT_MA_CSYT);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaThuebaoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maThuebao equals to
        defaultCsytOneBssFiltering("maThuebao.equals=" + DEFAULT_MA_THUEBAO, "maThuebao.equals=" + UPDATED_MA_THUEBAO);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaThuebaoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maThuebao in
        defaultCsytOneBssFiltering("maThuebao.in=" + DEFAULT_MA_THUEBAO + "," + UPDATED_MA_THUEBAO, "maThuebao.in=" + UPDATED_MA_THUEBAO);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaThuebaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maThuebao is not null
        defaultCsytOneBssFiltering("maThuebao.specified=true", "maThuebao.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaThuebaoContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maThuebao contains
        defaultCsytOneBssFiltering("maThuebao.contains=" + DEFAULT_MA_THUEBAO, "maThuebao.contains=" + UPDATED_MA_THUEBAO);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaThuebaoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maThuebao does not contain
        defaultCsytOneBssFiltering("maThuebao.doesNotContain=" + UPDATED_MA_THUEBAO, "maThuebao.doesNotContain=" + DEFAULT_MA_THUEBAO);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaDuanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maDuan equals to
        defaultCsytOneBssFiltering("maDuan.equals=" + DEFAULT_MA_DUAN, "maDuan.equals=" + UPDATED_MA_DUAN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaDuanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maDuan in
        defaultCsytOneBssFiltering("maDuan.in=" + DEFAULT_MA_DUAN + "," + UPDATED_MA_DUAN, "maDuan.in=" + UPDATED_MA_DUAN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaDuanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maDuan is not null
        defaultCsytOneBssFiltering("maDuan.specified=true", "maDuan.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaDuanContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maDuan contains
        defaultCsytOneBssFiltering("maDuan.contains=" + DEFAULT_MA_DUAN, "maDuan.contains=" + UPDATED_MA_DUAN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaDuanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maDuan does not contain
        defaultCsytOneBssFiltering("maDuan.doesNotContain=" + UPDATED_MA_DUAN, "maDuan.doesNotContain=" + DEFAULT_MA_DUAN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai equals to
        defaultCsytOneBssFiltering("trangthai.equals=" + DEFAULT_TRANGTHAI, "trangthai.equals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai in
        defaultCsytOneBssFiltering("trangthai.in=" + DEFAULT_TRANGTHAI + "," + UPDATED_TRANGTHAI, "trangthai.in=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai is not null
        defaultCsytOneBssFiltering("trangthai.specified=true", "trangthai.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai is greater than or equal to
        defaultCsytOneBssFiltering(
            "trangthai.greaterThanOrEqual=" + DEFAULT_TRANGTHAI,
            "trangthai.greaterThanOrEqual=" + UPDATED_TRANGTHAI
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai is less than or equal to
        defaultCsytOneBssFiltering("trangthai.lessThanOrEqual=" + DEFAULT_TRANGTHAI, "trangthai.lessThanOrEqual=" + SMALLER_TRANGTHAI);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai is less than
        defaultCsytOneBssFiltering("trangthai.lessThan=" + UPDATED_TRANGTHAI, "trangthai.lessThan=" + DEFAULT_TRANGTHAI);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTrangthaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where trangthai is greater than
        defaultCsytOneBssFiltering("trangthai.greaterThan=" + SMALLER_TRANGTHAI, "trangthai.greaterThan=" + DEFAULT_TRANGTHAI);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat equals to
        defaultCsytOneBssFiltering(
            "thoigianCapnhat.equals=" + DEFAULT_THOIGIAN_CAPNHAT,
            "thoigianCapnhat.equals=" + UPDATED_THOIGIAN_CAPNHAT
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat in
        defaultCsytOneBssFiltering(
            "thoigianCapnhat.in=" + DEFAULT_THOIGIAN_CAPNHAT + "," + UPDATED_THOIGIAN_CAPNHAT,
            "thoigianCapnhat.in=" + UPDATED_THOIGIAN_CAPNHAT
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat is not null
        defaultCsytOneBssFiltering("thoigianCapnhat.specified=true", "thoigianCapnhat.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat is greater than or equal to
        defaultCsytOneBssFiltering(
            "thoigianCapnhat.greaterThanOrEqual=" + DEFAULT_THOIGIAN_CAPNHAT,
            "thoigianCapnhat.greaterThanOrEqual=" + UPDATED_THOIGIAN_CAPNHAT
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat is less than or equal to
        defaultCsytOneBssFiltering(
            "thoigianCapnhat.lessThanOrEqual=" + DEFAULT_THOIGIAN_CAPNHAT,
            "thoigianCapnhat.lessThanOrEqual=" + SMALLER_THOIGIAN_CAPNHAT
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat is less than
        defaultCsytOneBssFiltering(
            "thoigianCapnhat.lessThan=" + UPDATED_THOIGIAN_CAPNHAT,
            "thoigianCapnhat.lessThan=" + DEFAULT_THOIGIAN_CAPNHAT
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByThoigianCapnhatIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where thoigianCapnhat is greater than
        defaultCsytOneBssFiltering(
            "thoigianCapnhat.greaterThan=" + SMALLER_THOIGIAN_CAPNHAT,
            "thoigianCapnhat.greaterThan=" + DEFAULT_THOIGIAN_CAPNHAT
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maTinh equals to
        defaultCsytOneBssFiltering("maTinh.equals=" + DEFAULT_MA_TINH, "maTinh.equals=" + UPDATED_MA_TINH);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaTinhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maTinh in
        defaultCsytOneBssFiltering("maTinh.in=" + DEFAULT_MA_TINH + "," + UPDATED_MA_TINH, "maTinh.in=" + UPDATED_MA_TINH);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maTinh is not null
        defaultCsytOneBssFiltering("maTinh.specified=true", "maTinh.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaTinhContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maTinh contains
        defaultCsytOneBssFiltering("maTinh.contains=" + DEFAULT_MA_TINH, "maTinh.contains=" + UPDATED_MA_TINH);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaTinhNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maTinh does not contain
        defaultCsytOneBssFiltering("maTinh.doesNotContain=" + UPDATED_MA_TINH, "maTinh.doesNotContain=" + DEFAULT_MA_TINH);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaSpdvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maSpdv equals to
        defaultCsytOneBssFiltering("maSpdv.equals=" + DEFAULT_MA_SPDV, "maSpdv.equals=" + UPDATED_MA_SPDV);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaSpdvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maSpdv in
        defaultCsytOneBssFiltering("maSpdv.in=" + DEFAULT_MA_SPDV + "," + UPDATED_MA_SPDV, "maSpdv.in=" + UPDATED_MA_SPDV);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaSpdvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maSpdv is not null
        defaultCsytOneBssFiltering("maSpdv.specified=true", "maSpdv.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaSpdvContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maSpdv contains
        defaultCsytOneBssFiltering("maSpdv.contains=" + DEFAULT_MA_SPDV, "maSpdv.contains=" + UPDATED_MA_SPDV);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaSpdvNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maSpdv does not contain
        defaultCsytOneBssFiltering("maSpdv.doesNotContain=" + UPDATED_MA_SPDV, "maSpdv.doesNotContain=" + DEFAULT_MA_SPDV);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaHopdongIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maHopdong equals to
        defaultCsytOneBssFiltering("maHopdong.equals=" + DEFAULT_MA_HOPDONG, "maHopdong.equals=" + UPDATED_MA_HOPDONG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaHopdongIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maHopdong in
        defaultCsytOneBssFiltering("maHopdong.in=" + DEFAULT_MA_HOPDONG + "," + UPDATED_MA_HOPDONG, "maHopdong.in=" + UPDATED_MA_HOPDONG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaHopdongIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maHopdong is not null
        defaultCsytOneBssFiltering("maHopdong.specified=true", "maHopdong.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaHopdongContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maHopdong contains
        defaultCsytOneBssFiltering("maHopdong.contains=" + DEFAULT_MA_HOPDONG, "maHopdong.contains=" + UPDATED_MA_HOPDONG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMaHopdongNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where maHopdong does not contain
        defaultCsytOneBssFiltering("maHopdong.doesNotContain=" + UPDATED_MA_HOPDONG, "maHopdong.doesNotContain=" + DEFAULT_MA_HOPDONG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong equals to
        defaultCsytOneBssFiltering("ngaykyHopdong.equals=" + DEFAULT_NGAYKY_HOPDONG, "ngaykyHopdong.equals=" + UPDATED_NGAYKY_HOPDONG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong in
        defaultCsytOneBssFiltering(
            "ngaykyHopdong.in=" + DEFAULT_NGAYKY_HOPDONG + "," + UPDATED_NGAYKY_HOPDONG,
            "ngaykyHopdong.in=" + UPDATED_NGAYKY_HOPDONG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong is not null
        defaultCsytOneBssFiltering("ngaykyHopdong.specified=true", "ngaykyHopdong.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong is greater than or equal to
        defaultCsytOneBssFiltering(
            "ngaykyHopdong.greaterThanOrEqual=" + DEFAULT_NGAYKY_HOPDONG,
            "ngaykyHopdong.greaterThanOrEqual=" + UPDATED_NGAYKY_HOPDONG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong is less than or equal to
        defaultCsytOneBssFiltering(
            "ngaykyHopdong.lessThanOrEqual=" + DEFAULT_NGAYKY_HOPDONG,
            "ngaykyHopdong.lessThanOrEqual=" + SMALLER_NGAYKY_HOPDONG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong is less than
        defaultCsytOneBssFiltering("ngaykyHopdong.lessThan=" + UPDATED_NGAYKY_HOPDONG, "ngaykyHopdong.lessThan=" + DEFAULT_NGAYKY_HOPDONG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByNgaykyHopdongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where ngaykyHopdong is greater than
        defaultCsytOneBssFiltering(
            "ngaykyHopdong.greaterThan=" + SMALLER_NGAYKY_HOPDONG,
            "ngaykyHopdong.greaterThan=" + DEFAULT_NGAYKY_HOPDONG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu equals to
        defaultCsytOneBssFiltering("hieulucTu.equals=" + DEFAULT_HIEULUC_TU, "hieulucTu.equals=" + UPDATED_HIEULUC_TU);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu in
        defaultCsytOneBssFiltering("hieulucTu.in=" + DEFAULT_HIEULUC_TU + "," + UPDATED_HIEULUC_TU, "hieulucTu.in=" + UPDATED_HIEULUC_TU);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu is not null
        defaultCsytOneBssFiltering("hieulucTu.specified=true", "hieulucTu.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu is greater than or equal to
        defaultCsytOneBssFiltering(
            "hieulucTu.greaterThanOrEqual=" + DEFAULT_HIEULUC_TU,
            "hieulucTu.greaterThanOrEqual=" + UPDATED_HIEULUC_TU
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu is less than or equal to
        defaultCsytOneBssFiltering("hieulucTu.lessThanOrEqual=" + DEFAULT_HIEULUC_TU, "hieulucTu.lessThanOrEqual=" + SMALLER_HIEULUC_TU);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu is less than
        defaultCsytOneBssFiltering("hieulucTu.lessThan=" + UPDATED_HIEULUC_TU, "hieulucTu.lessThan=" + DEFAULT_HIEULUC_TU);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucTuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucTu is greater than
        defaultCsytOneBssFiltering("hieulucTu.greaterThan=" + SMALLER_HIEULUC_TU, "hieulucTu.greaterThan=" + DEFAULT_HIEULUC_TU);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen equals to
        defaultCsytOneBssFiltering("hieulucDen.equals=" + DEFAULT_HIEULUC_DEN, "hieulucDen.equals=" + UPDATED_HIEULUC_DEN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen in
        defaultCsytOneBssFiltering(
            "hieulucDen.in=" + DEFAULT_HIEULUC_DEN + "," + UPDATED_HIEULUC_DEN,
            "hieulucDen.in=" + UPDATED_HIEULUC_DEN
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen is not null
        defaultCsytOneBssFiltering("hieulucDen.specified=true", "hieulucDen.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen is greater than or equal to
        defaultCsytOneBssFiltering(
            "hieulucDen.greaterThanOrEqual=" + DEFAULT_HIEULUC_DEN,
            "hieulucDen.greaterThanOrEqual=" + UPDATED_HIEULUC_DEN
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen is less than or equal to
        defaultCsytOneBssFiltering(
            "hieulucDen.lessThanOrEqual=" + DEFAULT_HIEULUC_DEN,
            "hieulucDen.lessThanOrEqual=" + SMALLER_HIEULUC_DEN
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen is less than
        defaultCsytOneBssFiltering("hieulucDen.lessThan=" + UPDATED_HIEULUC_DEN, "hieulucDen.lessThan=" + DEFAULT_HIEULUC_DEN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByHieulucDenIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where hieulucDen is greater than
        defaultCsytOneBssFiltering("hieulucDen.greaterThan=" + SMALLER_HIEULUC_DEN, "hieulucDen.greaterThan=" + DEFAULT_HIEULUC_DEN);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTenKhachhangIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where tenKhachhang equals to
        defaultCsytOneBssFiltering("tenKhachhang.equals=" + DEFAULT_TEN_KHACHHANG, "tenKhachhang.equals=" + UPDATED_TEN_KHACHHANG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTenKhachhangIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where tenKhachhang in
        defaultCsytOneBssFiltering(
            "tenKhachhang.in=" + DEFAULT_TEN_KHACHHANG + "," + UPDATED_TEN_KHACHHANG,
            "tenKhachhang.in=" + UPDATED_TEN_KHACHHANG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTenKhachhangIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where tenKhachhang is not null
        defaultCsytOneBssFiltering("tenKhachhang.specified=true", "tenKhachhang.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTenKhachhangContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where tenKhachhang contains
        defaultCsytOneBssFiltering("tenKhachhang.contains=" + DEFAULT_TEN_KHACHHANG, "tenKhachhang.contains=" + UPDATED_TEN_KHACHHANG);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByTenKhachhangNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where tenKhachhang does not contain
        defaultCsytOneBssFiltering(
            "tenKhachhang.doesNotContain=" + UPDATED_TEN_KHACHHANG,
            "tenKhachhang.doesNotContain=" + DEFAULT_TEN_KHACHHANG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByDiachiKhachhangIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where diachiKhachhang equals to
        defaultCsytOneBssFiltering(
            "diachiKhachhang.equals=" + DEFAULT_DIACHI_KHACHHANG,
            "diachiKhachhang.equals=" + UPDATED_DIACHI_KHACHHANG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByDiachiKhachhangIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where diachiKhachhang in
        defaultCsytOneBssFiltering(
            "diachiKhachhang.in=" + DEFAULT_DIACHI_KHACHHANG + "," + UPDATED_DIACHI_KHACHHANG,
            "diachiKhachhang.in=" + UPDATED_DIACHI_KHACHHANG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByDiachiKhachhangIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where diachiKhachhang is not null
        defaultCsytOneBssFiltering("diachiKhachhang.specified=true", "diachiKhachhang.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByDiachiKhachhangContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where diachiKhachhang contains
        defaultCsytOneBssFiltering(
            "diachiKhachhang.contains=" + DEFAULT_DIACHI_KHACHHANG,
            "diachiKhachhang.contains=" + UPDATED_DIACHI_KHACHHANG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByDiachiKhachhangNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where diachiKhachhang does not contain
        defaultCsytOneBssFiltering(
            "diachiKhachhang.doesNotContain=" + UPDATED_DIACHI_KHACHHANG,
            "diachiKhachhang.doesNotContain=" + DEFAULT_DIACHI_KHACHHANG
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where mota equals to
        defaultCsytOneBssFiltering("mota.equals=" + DEFAULT_MOTA, "mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where mota in
        defaultCsytOneBssFiltering("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA, "mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where mota is not null
        defaultCsytOneBssFiltering("mota.specified=true", "mota.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMotaContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where mota contains
        defaultCsytOneBssFiltering("mota.contains=" + DEFAULT_MOTA, "mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where mota does not contain
        defaultCsytOneBssFiltering("mota.doesNotContain=" + UPDATED_MOTA, "mota.doesNotContain=" + DEFAULT_MOTA);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastUserIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastUser equals to
        defaultCsytOneBssFiltering("lastUser.equals=" + DEFAULT_LAST_USER, "lastUser.equals=" + UPDATED_LAST_USER);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastUserIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastUser in
        defaultCsytOneBssFiltering("lastUser.in=" + DEFAULT_LAST_USER + "," + UPDATED_LAST_USER, "lastUser.in=" + UPDATED_LAST_USER);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastUser is not null
        defaultCsytOneBssFiltering("lastUser.specified=true", "lastUser.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastUserContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastUser contains
        defaultCsytOneBssFiltering("lastUser.contains=" + DEFAULT_LAST_USER, "lastUser.contains=" + UPDATED_LAST_USER);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastUserNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastUser does not contain
        defaultCsytOneBssFiltering("lastUser.doesNotContain=" + UPDATED_LAST_USER, "lastUser.doesNotContain=" + DEFAULT_LAST_USER);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified equals to
        defaultCsytOneBssFiltering("lastModified.equals=" + DEFAULT_LAST_MODIFIED, "lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified in
        defaultCsytOneBssFiltering(
            "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED,
            "lastModified.in=" + UPDATED_LAST_MODIFIED
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified is not null
        defaultCsytOneBssFiltering("lastModified.specified=true", "lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified is greater than or equal to
        defaultCsytOneBssFiltering(
            "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED,
            "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified is less than or equal to
        defaultCsytOneBssFiltering(
            "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED,
            "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
        );
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified is less than
        defaultCsytOneBssFiltering("lastModified.lessThan=" + UPDATED_LAST_MODIFIED, "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCsytOneBssesByLastModifiedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        // Get all the csytOneBssList where lastModified is greater than
        defaultCsytOneBssFiltering(
            "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED,
            "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
        );
    }

    private void defaultCsytOneBssFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCsytOneBssShouldBeFound(shouldBeFound);
        defaultCsytOneBssShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCsytOneBssShouldBeFound(String filter) throws Exception {
        restCsytOneBssMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csytOneBss.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCsyt").value(hasItem(DEFAULT_MA_CSYT)))
            .andExpect(jsonPath("$.[*].maThuebao").value(hasItem(DEFAULT_MA_THUEBAO)))
            .andExpect(jsonPath("$.[*].maDuan").value(hasItem(DEFAULT_MA_DUAN)))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].thoigianCapnhat").value(hasItem(DEFAULT_THOIGIAN_CAPNHAT.toString())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH)))
            .andExpect(jsonPath("$.[*].maSpdv").value(hasItem(DEFAULT_MA_SPDV)))
            .andExpect(jsonPath("$.[*].maHopdong").value(hasItem(DEFAULT_MA_HOPDONG)))
            .andExpect(jsonPath("$.[*].ngaykyHopdong").value(hasItem(DEFAULT_NGAYKY_HOPDONG.toString())))
            .andExpect(jsonPath("$.[*].hieulucTu").value(hasItem(DEFAULT_HIEULUC_TU.toString())))
            .andExpect(jsonPath("$.[*].hieulucDen").value(hasItem(DEFAULT_HIEULUC_DEN.toString())))
            .andExpect(jsonPath("$.[*].tenKhachhang").value(hasItem(DEFAULT_TEN_KHACHHANG)))
            .andExpect(jsonPath("$.[*].diachiKhachhang").value(hasItem(DEFAULT_DIACHI_KHACHHANG)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].lastUser").value(hasItem(DEFAULT_LAST_USER)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(sameInstant(DEFAULT_LAST_MODIFIED))));

        // Check, that the count call also returns 1
        restCsytOneBssMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCsytOneBssShouldNotBeFound(String filter) throws Exception {
        restCsytOneBssMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCsytOneBssMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCsytOneBss() throws Exception {
        // Get the csytOneBss
        restCsytOneBssMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCsytOneBss() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the csytOneBss
        CsytOneBss updatedCsytOneBss = csytOneBssRepository.findById(csytOneBss.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCsytOneBss are not directly saved in db
        em.detach(updatedCsytOneBss);
        updatedCsytOneBss
            .maCsyt(UPDATED_MA_CSYT)
            .maThuebao(UPDATED_MA_THUEBAO)
            .maDuan(UPDATED_MA_DUAN)
            .trangthai(UPDATED_TRANGTHAI)
            .thoigianCapnhat(UPDATED_THOIGIAN_CAPNHAT)
            .maTinh(UPDATED_MA_TINH)
            .maSpdv(UPDATED_MA_SPDV)
            .maHopdong(UPDATED_MA_HOPDONG)
            .ngaykyHopdong(UPDATED_NGAYKY_HOPDONG)
            .hieulucTu(UPDATED_HIEULUC_TU)
            .hieulucDen(UPDATED_HIEULUC_DEN)
            .tenKhachhang(UPDATED_TEN_KHACHHANG)
            .diachiKhachhang(UPDATED_DIACHI_KHACHHANG)
            .mota(UPDATED_MOTA)
            .lastUser(UPDATED_LAST_USER)
            .lastModified(UPDATED_LAST_MODIFIED);

        restCsytOneBssMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCsytOneBss.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCsytOneBss))
            )
            .andExpect(status().isOk());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCsytOneBssToMatchAllProperties(updatedCsytOneBss);
    }

    @Test
    @Transactional
    void putNonExistingCsytOneBss() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        csytOneBss.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsytOneBssMockMvc
            .perform(
                put(ENTITY_API_URL_ID, csytOneBss.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss))
            )
            .andExpect(status().isBadRequest());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCsytOneBss() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        csytOneBss.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCsytOneBssMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(csytOneBss))
            )
            .andExpect(status().isBadRequest());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCsytOneBss() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        csytOneBss.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCsytOneBssMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCsytOneBssWithPatch() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the csytOneBss using partial update
        CsytOneBss partialUpdatedCsytOneBss = new CsytOneBss();
        partialUpdatedCsytOneBss.setId(csytOneBss.getId());

        partialUpdatedCsytOneBss
            .trangthai(UPDATED_TRANGTHAI)
            .thoigianCapnhat(UPDATED_THOIGIAN_CAPNHAT)
            .maTinh(UPDATED_MA_TINH)
            .hieulucTu(UPDATED_HIEULUC_TU)
            .hieulucDen(UPDATED_HIEULUC_DEN)
            .tenKhachhang(UPDATED_TEN_KHACHHANG);

        restCsytOneBssMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCsytOneBss.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCsytOneBss))
            )
            .andExpect(status().isOk());

        // Validate the CsytOneBss in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCsytOneBssUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCsytOneBss, csytOneBss),
            getPersistedCsytOneBss(csytOneBss)
        );
    }

    @Test
    @Transactional
    void fullUpdateCsytOneBssWithPatch() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the csytOneBss using partial update
        CsytOneBss partialUpdatedCsytOneBss = new CsytOneBss();
        partialUpdatedCsytOneBss.setId(csytOneBss.getId());

        partialUpdatedCsytOneBss
            .maCsyt(UPDATED_MA_CSYT)
            .maThuebao(UPDATED_MA_THUEBAO)
            .maDuan(UPDATED_MA_DUAN)
            .trangthai(UPDATED_TRANGTHAI)
            .thoigianCapnhat(UPDATED_THOIGIAN_CAPNHAT)
            .maTinh(UPDATED_MA_TINH)
            .maSpdv(UPDATED_MA_SPDV)
            .maHopdong(UPDATED_MA_HOPDONG)
            .ngaykyHopdong(UPDATED_NGAYKY_HOPDONG)
            .hieulucTu(UPDATED_HIEULUC_TU)
            .hieulucDen(UPDATED_HIEULUC_DEN)
            .tenKhachhang(UPDATED_TEN_KHACHHANG)
            .diachiKhachhang(UPDATED_DIACHI_KHACHHANG)
            .mota(UPDATED_MOTA)
            .lastUser(UPDATED_LAST_USER)
            .lastModified(UPDATED_LAST_MODIFIED);

        restCsytOneBssMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCsytOneBss.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCsytOneBss))
            )
            .andExpect(status().isOk());

        // Validate the CsytOneBss in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCsytOneBssUpdatableFieldsEquals(partialUpdatedCsytOneBss, getPersistedCsytOneBss(partialUpdatedCsytOneBss));
    }

    @Test
    @Transactional
    void patchNonExistingCsytOneBss() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        csytOneBss.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsytOneBssMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, csytOneBss.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(csytOneBss))
            )
            .andExpect(status().isBadRequest());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCsytOneBss() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        csytOneBss.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCsytOneBssMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(csytOneBss))
            )
            .andExpect(status().isBadRequest());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCsytOneBss() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        csytOneBss.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCsytOneBssMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(csytOneBss)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CsytOneBss in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCsytOneBss() throws Exception {
        // Initialize the database
        insertedCsytOneBss = csytOneBssRepository.saveAndFlush(csytOneBss);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the csytOneBss
        restCsytOneBssMockMvc
            .perform(delete(ENTITY_API_URL_ID, csytOneBss.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return csytOneBssRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected CsytOneBss getPersistedCsytOneBss(CsytOneBss csytOneBss) {
        return csytOneBssRepository.findById(csytOneBss.getId()).orElseThrow();
    }

    protected void assertPersistedCsytOneBssToMatchAllProperties(CsytOneBss expectedCsytOneBss) {
        assertCsytOneBssAllPropertiesEquals(expectedCsytOneBss, getPersistedCsytOneBss(expectedCsytOneBss));
    }

    protected void assertPersistedCsytOneBssToMatchUpdatableProperties(CsytOneBss expectedCsytOneBss) {
        assertCsytOneBssAllUpdatablePropertiesEquals(expectedCsytOneBss, getPersistedCsytOneBss(expectedCsytOneBss));
    }
}
