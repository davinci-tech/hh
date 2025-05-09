package com.huawei.hms.iap;

import android.content.Intent;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.iap.entity.StartIapActivityReq;
import com.huawei.hms.iap.entity.StartIapActivityResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes9.dex */
public class r extends a<g, StartIapActivityResult> {

    /* renamed from: a, reason: collision with root package name */
    private StartIapActivityReq f4663a;

    @Override // com.huawei.hms.iap.a
    protected void a(TaskCompletionSource<StartIapActivityResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("StartIapActivityTaskApiCall", "dealSuccess");
        if (responseErrorCode.getParcelable() instanceof Intent) {
            HMSLog.i("StartIapActivityTaskApiCall", "onResult, getParcelable is instanceof Intent");
            taskCompletionSource.setResult(new StartIapActivityResult((Intent) responseErrorCode.getParcelable()));
        } else {
            HMSLog.e("StartIapActivityTaskApiCall", "onResult, success but no intent");
            taskCompletionSource.setException(new IapApiException(new Status(-1, "")));
        }
    }

    @Override // com.huawei.hms.iap.a
    protected int a() {
        return b() ? 60000000 : 40004000;
    }

    private boolean b() {
        StartIapActivityReq startIapActivityReq = this.f4663a;
        return startIapActivityReq != null && startIapActivityReq.getType() == 1;
    }

    public r(String str, StartIapActivityReq startIapActivityReq, String str2, String str3) {
        super(str, JsonUtil.createJsonString(startIapActivityReq), str2, str3);
        this.f4663a = startIapActivityReq;
        if (b()) {
            HMSLog.i("StartIapActivityTaskApiCall", "StartIapActivityTaskApiCall, setApiLevel 5");
            a(5);
        }
    }
}
