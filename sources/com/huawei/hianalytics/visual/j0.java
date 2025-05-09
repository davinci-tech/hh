package com.huawei.hianalytics.visual;

import com.huawei.openalliance.ad.constant.Constants;
import java.util.UUID;

/* loaded from: classes4.dex */
public class j0 {

    /* renamed from: a, reason: collision with root package name */
    public String f3930a;
    public long b = System.currentTimeMillis();

    public j0(String str) {
        this.f3930a = str;
    }

    public static j0 a() {
        return new j0(UUID.randomUUID().toString().replaceAll(Constants.LINK, ""));
    }
}
