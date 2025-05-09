package com.huawei.hwcloudjs.service.hms;

import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwidauth.api.ResultCallBack;
import defpackage.kqg;

/* loaded from: classes5.dex */
class p implements ResultCallBack<kqg> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ JsCallback f6254a;
    final /* synthetic */ HmsLiteCoreApi b;

    @Override // com.huawei.hwidauth.api.ResultCallBack
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(kqg kqgVar) {
        if (kqgVar == null) {
            this.f6254a.failure("signOut result is null");
            com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", "signOut result is null", true);
        } else if (kqgVar.e()) {
            com.huawei.hwcloudjs.f.d.c("HmsLiteCoreApi", "signOut success", true);
            this.f6254a.success();
        } else {
            com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", "signOut failed", true);
            this.f6254a.failure(kqgVar.getStatus().e(), kqgVar.getStatus().a());
        }
    }

    p(HmsLiteCoreApi hmsLiteCoreApi, JsCallback jsCallback) {
        this.b = hmsLiteCoreApi;
        this.f6254a = jsCallback;
    }
}
