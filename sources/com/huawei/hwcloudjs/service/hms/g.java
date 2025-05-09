package com.huawei.hwcloudjs.service.hms;

import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hwcloudjs.core.JsCallback;

/* loaded from: classes5.dex */
class g implements ResultCallback<Status> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ JsCallback f6240a;
    final /* synthetic */ HmsCoreApi b;

    @Override // com.huawei.hms.support.api.client.ResultCallback
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(Status status) {
        if (status != null) {
            com.huawei.hwcloudjs.f.d.c("HmsCoreApi", "signOut status is not null", true);
            if (status.isSuccess()) {
                com.huawei.hwcloudjs.f.d.c("HmsCoreApi", "signOut successed", true);
                this.f6240a.success();
                return;
            }
            com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "signOut failed, statusCode:" + status.getStatusCode() + " msg:" + status.getStatusMessage(), true);
            this.f6240a.failure(status.getStatusCode(), status.getStatusMessage());
            return;
        }
        com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "signOut failed, status is null", true);
        this.f6240a.failure("signOut failed, status is null");
    }

    g(HmsCoreApi hmsCoreApi, JsCallback jsCallback) {
        this.b = hmsCoreApi;
        this.f6240a = jsCallback;
    }
}
