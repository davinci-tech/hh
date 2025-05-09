package com.huawei.hms.support.api.paytask;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.HwWalletInoResp;
import com.huawei.hms.support.api.pay.HwWalletInfoResult;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes9.dex */
public class HwWalletInfoTaskApiCall extends BasePayServiceTaskApiCall<PayHmsClient, HwWalletInfoResult> {
    @Override // com.huawei.hms.support.api.paytask.BasePayServiceTaskApiCall
    protected void dealSuccess(TaskCompletionSource<HwWalletInfoResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("HwWalletInfoTaskApiCall", "dealSuccess");
        HwWalletInoResp hwWalletInoResp = new HwWalletInoResp();
        if (!TextUtils.isEmpty(str)) {
            JsonUtil.jsonToEntity(str, hwWalletInoResp);
        }
        HwWalletInfoResult hwWalletInfoResult = new HwWalletInfoResult();
        Status status = new Status(responseErrorCode.getStatusCode(), responseErrorCode.getErrorReason());
        hwWalletInfoResult.setStatus(status);
        hwWalletInfoResult.setStatus(status);
        hwWalletInfoResult.setResult(hwWalletInoResp.getResult());
        taskCompletionSource.setResult(hwWalletInfoResult);
    }

    public HwWalletInfoTaskApiCall(String str, String str2) {
        super(str, str2);
    }
}
