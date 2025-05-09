package com.huawei.maps.offlinedata.service.device.consts;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: com.huawei.maps.offlinedata.service.device.consts.a$a, reason: collision with other inner class name */
    public enum EnumC0168a {
        MAP_SUCCESS(0),
        MAP_ERROR_NO_ENOUGH_SPACE(1),
        MAP_ERROR_NO_UPDATE(2),
        MAP_ERROR_IN_USE(3),
        MAP_ERROR_DEVICE_ERROR(4);

        private final int f;

        EnumC0168a(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }
    }

    public enum c {
        SUCCESS("0"),
        FAILURE("1");

        private final String c;

        c(String str) {
            this.c = str;
        }

        public String a() {
            return this.c;
        }
    }

    public enum b {
        QUERY_DEVICE_REMAIN_SPACE(1, HiAnalyticsConstant.KeyAndValue.NUMBER_01),
        SYNC_POLITICAL_PERSPECTIVE(2, com.huawei.hms.ads.dynamic.a.t),
        QUERY_DEVICE_DOWNLOADED_MAP_INFO(3, "03"),
        TRANSMIT_MAP_HANDSHAKE(4, "04"),
        DELETE_DEVICE_MAP(5, "05"),
        DEVICE_PING_MAP(6, "06"),
        DEVICE_REQUEST_POLITICAL_PERSPECTIVE(7, "07");

        private final int h;
        private final String i;

        b(int i, String str) {
            this.h = i;
            this.i = str;
        }

        public int a() {
            return this.h;
        }

        public String b() {
            return this.i;
        }
    }
}
