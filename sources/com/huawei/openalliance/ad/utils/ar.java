package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import okhttp3.Request;

/* loaded from: classes5.dex */
public class ar {
    public boolean a() {
        try {
            new Request.Builder();
            return true;
        } catch (Throwable th) {
            ho.d("HttpUtils", "check okhttp error:%s", th.getClass().getSimpleName());
            return false;
        }
    }
}
