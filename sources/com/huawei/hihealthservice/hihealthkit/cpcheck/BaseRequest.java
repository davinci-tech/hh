package com.huawei.hihealthservice.hihealthkit.cpcheck;

import defpackage.ipj;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class BaseRequest<T> {
    protected Map<String, Object> mParams = new HashMap(10);
    protected String mUrl;

    public ipj.e<T> getRequestParamsBuilder() {
        return new ipj.e(this.mUrl).a("GET").c(this.mParams);
    }
}
