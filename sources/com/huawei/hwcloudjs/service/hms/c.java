package com.huawei.hwcloudjs.service.hms;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.HmsCoreApi;

/* loaded from: classes5.dex */
class c implements OnCompleteListener<AuthHuaweiId> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HmsCoreApi.LoginReq f6236a;
    final /* synthetic */ JsCallback b;
    final /* synthetic */ HmsCoreApi c;

    @Override // com.huawei.hmf.tasks.OnCompleteListener
    public void onComplete(Task<AuthHuaweiId> task) {
        try {
            this.c.a(task.getResultThrowException(ApiException.class), this.f6236a.needAuthCode, this.b);
        } catch (ApiException e) {
            this.c.a(e, this.b);
        }
    }

    c(HmsCoreApi hmsCoreApi, HmsCoreApi.LoginReq loginReq, JsCallback jsCallback) {
        this.c = hmsCoreApi;
        this.f6236a = loginReq;
        this.b = jsCallback;
    }
}
