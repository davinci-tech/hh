package com.huawei.openalliance.ad.inter.data;

import com.huawei.openalliance.ad.constant.ErrorCode;

/* loaded from: classes9.dex */
public final class BannerSize {
    public static final String BANNER_STR = "BANNER";
    public static final String LARGE_BANNER_STR = "LARGE_BANNER";

    /* renamed from: a, reason: collision with root package name */
    private int f7042a;
    private int b;
    public static final BannerSize BANNER = new BannerSize(1080, 170);
    public static final BannerSize LARGE_BANNER = new BannerSize(1080, ErrorCode.ERROR_CODE_NO_PACKAGE);

    public int b() {
        return this.b;
    }

    public int a() {
        return this.f7042a;
    }

    private BannerSize(int i, int i2) {
        this.f7042a = i;
        this.b = i2;
    }
}
