package com.huawei.hwdevice.phoneprocess.mgr.httpproxy;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.blt;
import health.compact.a.utils.StringUtils;

/* loaded from: classes5.dex */
public class HttpRequestInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f6334a;
    private String b;
    private CloudType c;
    private DeviceInfo d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private int j;
    private long o;

    public DeviceInfo d() {
        return this.d;
    }

    public void a(DeviceInfo deviceInfo) {
        this.d = deviceInfo;
    }

    public long k() {
        return this.o;
    }

    public void c(long j) {
        this.o = j;
    }

    public String g() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String b() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String i() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public CloudType a() {
        return this.c;
    }

    public void b(CloudType cloudType) {
        this.c = cloudType;
    }

    public int j() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public int h() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public String f() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public String e() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int c() {
        return this.f6334a;
    }

    public void d(int i) {
        this.f6334a = i;
    }

    public boolean o() {
        return StringUtils.g(g());
    }

    public enum CloudType {
        COMMON_CLOUD(0),
        HEALTH_CLOUD(1);

        private final int value;

        CloudType(int i) {
            this.value = i;
        }

        public static CloudType from(int i) {
            for (CloudType cloudType : values()) {
                if (cloudType.value == i) {
                    return cloudType;
                }
            }
            return null;
        }
    }

    public String toString() {
        return "HttpRequestInfo{deviceId='" + blt.a(this.d.getDeviceIdentify()) + ", requestId='" + this.o + ", host='" + this.f + ", grsHostKey='" + this.e + ", path='" + this.h + ", cloudType=" + this.c + ", isForeground=" + this.j + ", method='" + this.g + ", headers=" + this.i + ", body=" + this.b + ", contextType=" + this.f6334a + '}';
    }
}
