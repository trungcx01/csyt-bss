package com.project.domain;

import static com.project.domain.CsytOneBssTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CsytOneBssTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsytOneBss.class);
        CsytOneBss csytOneBss1 = getCsytOneBssSample1();
        CsytOneBss csytOneBss2 = new CsytOneBss();
        assertThat(csytOneBss1).isNotEqualTo(csytOneBss2);

        csytOneBss2.setId(csytOneBss1.getId());
        assertThat(csytOneBss1).isEqualTo(csytOneBss2);

        csytOneBss2 = getCsytOneBssSample2();
        assertThat(csytOneBss1).isNotEqualTo(csytOneBss2);
    }
}
