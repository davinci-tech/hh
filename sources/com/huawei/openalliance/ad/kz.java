package com.huawei.openalliance.ad;

import org.json.JSONException;

/* loaded from: classes5.dex */
public class kz<R> extends ku<R> {

    /* renamed from: a, reason: collision with root package name */
    private Class<R> f7154a;

    @Override // com.huawei.openalliance.ad.ku
    protected R a(String str) {
        try {
            return (R) com.huawei.openalliance.ad.utils.be.a(str, this.f7154a, new Class[0]);
        } catch (JSONException e) {
            ho.c("JsonObjDataConverter", "convertStringToData json JSONException");
            throw e;
        }
    }

    public kz(Class<R> cls) {
        this.f7154a = cls;
    }
}
