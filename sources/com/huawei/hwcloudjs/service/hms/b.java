package com.huawei.hwcloudjs.service.hms;

import com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.result.HuaweiIdAuthResult;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.HmsCoreApi;
import com.huawei.hwcloudjs.service.hms.a;

/* loaded from: classes5.dex */
class b implements a.c {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HmsCoreApi.LoginReq f6234a;
    final /* synthetic */ JsCallback b;
    final /* synthetic */ HmsCoreApi c;

    @Override // com.huawei.hwcloudjs.service.hms.a.c
    public void a(a.b bVar) {
        HuaweiIdAuthResult parseHuaweiIdFromIntent = HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.parseHuaweiIdFromIntent(bVar.a());
        if (parseHuaweiIdFromIntent == null) {
            com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "getSignInIntent failed, result is null", true);
            this.b.failure("getSignInIntent failed, result is null");
            return;
        }
        com.huawei.hwcloudjs.f.d.c("HmsCoreApi", "getSignInIntent result is not null", true);
        if (!parseHuaweiIdFromIntent.isSuccess()) {
            com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "getSignInIntent failed, statusCode:" + parseHuaweiIdFromIntent.getStatus().getStatusCode() + " msg:" + parseHuaweiIdFromIntent.getStatus().getStatusMessage(), true);
            this.b.failure(parseHuaweiIdFromIntent.getStatus().getStatusCode(), parseHuaweiIdFromIntent.getStatus().getStatusMessage());
            return;
        }
        AuthHuaweiId huaweiId = parseHuaweiIdFromIntent.getHuaweiId();
        if (huaweiId != null) {
            com.huawei.hwcloudjs.f.d.c("HmsCoreApi", "getSignInIntent authHuaweiId is not null", true);
            this.c.a(huaweiId, this.f6234a.needAuthCode, this.b);
            return;
        }
        com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "getSignInIntent authHuaweiId is null", true);
        com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "getSignInIntent statusCode:" + parseHuaweiIdFromIntent.getStatus().getStatusCode() + " msg:" + parseHuaweiIdFromIntent.getStatus().getStatusMessage(), true);
        this.b.failure(1);
    }

    b(HmsCoreApi hmsCoreApi, HmsCoreApi.LoginReq loginReq, JsCallback jsCallback) {
        this.c = hmsCoreApi;
        this.f6234a = loginReq;
        this.b = jsCallback;
    }
}
