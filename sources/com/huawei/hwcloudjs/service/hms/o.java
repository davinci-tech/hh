package com.huawei.hwcloudjs.service.hms;

import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwidauth.api.ResultCallBack;
import defpackage.kqo;

/* loaded from: classes5.dex */
class o implements ResultCallBack<kqo> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ JsCallback f6253a;
    final /* synthetic */ HmsLiteCoreApi b;

    @Override // com.huawei.hwidauth.api.ResultCallBack
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(kqo kqoVar) {
        com.huawei.hwcloudjs.f.d.c("HmsLiteCoreApi", "signInOauth onResult", true);
        this.b.a(kqoVar, this.f6253a);
    }

    o(HmsLiteCoreApi hmsLiteCoreApi, JsCallback jsCallback) {
        this.b = hmsLiteCoreApi;
        this.f6253a = jsCallback;
    }
}
