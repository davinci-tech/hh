package com.huawei.hwcloudjs.service.hms;

import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwidauth.api.ResultCallBack;
import defpackage.kqm;
import defpackage.kqp;

/* loaded from: classes5.dex */
class q implements ResultCallBack<kqp> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ JsCallback f6255a;
    final /* synthetic */ HmsLiteCoreApi b;

    @Override // com.huawei.hwidauth.api.ResultCallBack
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(kqp kqpVar) {
        com.huawei.hwcloudjs.f.d.c("HmsLiteCoreApi", "cancelAuthorization onResult begin", true);
        if (kqpVar != null) {
            try {
                kqm status = kqpVar.getStatus();
                int e = status.e();
                if (e == 200) {
                    com.huawei.hwcloudjs.f.d.c("HmsLiteCoreApi", "cancelAuthorization success", true);
                    this.f6255a.success();
                } else {
                    com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", "cancelAuthorization failed", true);
                    this.f6255a.failure(e, status.a());
                }
                return;
            } catch (RuntimeException unused) {
                this.f6255a.failure("cancelAuthorization exception");
                com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", "cancelAuthorization exception", true);
                return;
            }
        }
        this.f6255a.failure("cancelAuthorization result is null");
        com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", "result is null", true);
    }

    q(HmsLiteCoreApi hmsLiteCoreApi, JsCallback jsCallback) {
        this.b = hmsLiteCoreApi;
        this.f6255a = jsCallback;
    }
}
