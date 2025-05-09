package com.huawei.hwcloudjs.service.hms;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hwcloudjs.core.JsCallback;

/* loaded from: classes5.dex */
class f implements OnCompleteListener<Void> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ JsCallback f6239a;
    final /* synthetic */ HmsCoreApi b;

    @Override // com.huawei.hmf.tasks.OnCompleteListener
    public void onComplete(Task<Void> task) {
        String str;
        if (task != null) {
            com.huawei.hwcloudjs.f.d.c("HmsCoreApi", "signOut task is not null", true);
            if (task.isSuccessful()) {
                com.huawei.hwcloudjs.f.d.c("HmsCoreApi", "signOut successed", true);
                this.f6239a.success();
                return;
            }
            Exception exception = task.getException();
            if (exception != null && (exception instanceof ApiException)) {
                ApiException apiException = (ApiException) exception;
                com.huawei.hwcloudjs.f.d.b("HmsCoreApi", "signOut failed, statusCode:" + apiException.getStatusCode() + " msg:" + apiException.getStatusMessage(), true);
                this.f6239a.failure(apiException.getStatusCode(), apiException.getStatusMessage());
                return;
            }
            str = "signOut getException failed";
        } else {
            str = "signOut failed, task is null";
        }
        com.huawei.hwcloudjs.f.d.b("HmsCoreApi", str, true);
        this.f6239a.failure(str);
    }

    f(HmsCoreApi hmsCoreApi, JsCallback jsCallback) {
        this.b = hmsCoreApi;
        this.f6239a = jsCallback;
    }
}
