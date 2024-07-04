package com.project.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CsytOneBssTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CsytOneBss getCsytOneBssSample1() {
        return new CsytOneBss()
            .id(1L)
            .maCsyt("maCsyt1")
            .maThuebao("maThuebao1")
            .maDuan("maDuan1")
            .trangthai(1)
            .maTinh("maTinh1")
            .maSpdv("maSpdv1")
            .maHopdong("maHopdong1")
            .tenKhachhang("tenKhachhang1")
            .diachiKhachhang("diachiKhachhang1")
            .mota("mota1")
            .lastUser("lastUser1");
    }

    public static CsytOneBss getCsytOneBssSample2() {
        return new CsytOneBss()
            .id(2L)
            .maCsyt("maCsyt2")
            .maThuebao("maThuebao2")
            .maDuan("maDuan2")
            .trangthai(2)
            .maTinh("maTinh2")
            .maSpdv("maSpdv2")
            .maHopdong("maHopdong2")
            .tenKhachhang("tenKhachhang2")
            .diachiKhachhang("diachiKhachhang2")
            .mota("mota2")
            .lastUser("lastUser2");
    }

    public static CsytOneBss getCsytOneBssRandomSampleGenerator() {
        return new CsytOneBss()
            .id(longCount.incrementAndGet())
            .maCsyt(UUID.randomUUID().toString())
            .maThuebao(UUID.randomUUID().toString())
            .maDuan(UUID.randomUUID().toString())
            .trangthai(intCount.incrementAndGet())
            .maTinh(UUID.randomUUID().toString())
            .maSpdv(UUID.randomUUID().toString())
            .maHopdong(UUID.randomUUID().toString())
            .tenKhachhang(UUID.randomUUID().toString())
            .diachiKhachhang(UUID.randomUUID().toString())
            .mota(UUID.randomUUID().toString())
            .lastUser(UUID.randomUUID().toString());
    }
}
