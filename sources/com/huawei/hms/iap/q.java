package com.huawei.hms.iap;

import android.content.Intent;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.iap.entity.ScanRedeemCodeResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes7.dex */
public class q extends a<g, ScanRedeemCodeResult> {
    @Override // com.huawei.hms.iap.a, com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 60900000;
    }

    @Override // com.huawei.hms.iap.a
    protected void a(TaskCompletionSource<ScanRedeemCodeResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("ScanRedeemCodeTaskApiCall", "dealSuccess");
        if (responseErrorCode.getParcelable() instanceof Intent) {
            HMSLog.i("ScanRedeemCodeTaskApiCall", "onResult and getParcelable is instanceof Intent");
            taskCompletionSource.setResult(new ScanRedeemCodeResult((Intent) responseErrorCode.getParcelable()));
        } else {
            HMSLog.e("ScanRedeemCodeTaskApiCall", "onResult success but no intent");
            taskCompletionSource.setException(new IapApiException(new Status(-1, "")));
        }
    }

    public q(String str, String str2) {
        super(str, JsonUtil.createJsonString(null), str2);
        setApiLevel(10);
    }
}
