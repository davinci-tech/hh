package com.huawei.hms.support.api.paytask;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.entity.pay.PayResp;
import com.huawei.hms.support.api.pay.PayResult;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes4.dex */
public class PayTaskApiCall extends BasePayServiceTaskApiCall<PayHmsClient, PayResult> {

    /* renamed from: a, reason: collision with root package name */
    private String f5971a;

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        if (a()) {
            return 60000300;
        }
        return super.getMinApkVersion();
    }

    @Override // com.huawei.hms.support.api.paytask.BasePayServiceTaskApiCall
    protected void dealSuccess(TaskCompletionSource<PayResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("PayTaskApiCall", "dealSuccess");
        PayResp payResp = new PayResp();
        if (!TextUtils.isEmpty(str)) {
            JsonUtil.jsonToEntity(str, payResp);
        }
        PayResult payResult = new PayResult();
        payResult.setStatus(getStatus(responseErrorCode));
        taskCompletionSource.setResult(payResult);
    }

    private boolean a() {
        if (TextUtils.isEmpty(this.f5971a)) {
            return false;
        }
        HMSLog.i("PayTaskApiCall", "Use the SHA256WithRSA/PSS algorithm.");
        return true;
    }

    public PayTaskApiCall(String str, String str2, String str3, String str4) {
        super(str, str2, str3);
        this.f5971a = str4;
        if (a()) {
            setApiLevel(4);
        }
    }

    public PayTaskApiCall(String str, String str2, String str3) {
        super(str, str2);
        this.f5971a = str3;
        if (a()) {
            setApiLevel(4);
        }
    }
}
