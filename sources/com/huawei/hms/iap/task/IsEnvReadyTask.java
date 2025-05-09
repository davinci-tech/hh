package com.huawei.hms.iap.task;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.iap.entity.IsEnvReadyReq;
import com.huawei.hms.iap.entity.IsEnvReadyResult;
import com.huawei.hms.iap.entity.b;
import com.huawei.hms.iapfull.IIapFullAPIVer4;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes4.dex */
public class IsEnvReadyTask extends BaseIapFullTask<IsEnvReadyResult, IsEnvReadyReq> {
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    protected void setResult() {
        this.mResult = new IsEnvReadyResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    public void handleRequest(IsEnvReadyReq isEnvReadyReq, IIapFullAPIVer4 iIapFullAPIVer4) {
        iIapFullAPIVer4.isEnvReady(a(isEnvReadyReq), new WebPayCallback() { // from class: com.huawei.hms.iap.task.IsEnvReadyTask.1
            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onSuccess(String str) {
                b bVar = new b();
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JsonUtil.jsonToEntity(str, bVar);
                    } catch (IllegalArgumentException e) {
                        HMSLog.e("IsEnvReadyTask", "IsEnvReadyResp jsonToEntity " + e.getMessage());
                    }
                }
                IsEnvReadyTask.this.mResult = new IsEnvReadyResult();
                ((IsEnvReadyResult) IsEnvReadyTask.this.mResult).setReturnCode(bVar.getReturnCode());
                ((IsEnvReadyResult) IsEnvReadyTask.this.mResult).setErrMsg(bVar.getReturnDesc());
                ((IsEnvReadyResult) IsEnvReadyTask.this.mResult).setStatus(bVar.getCommonStatus());
                IsEnvReadyTask.this.handleRequestSuccess();
            }

            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onFailure(int i, String str) {
                IsEnvReadyTask.this.handleRequestFailed(i, str);
            }
        });
    }

    private WebIsEnvReadyRequest a(IsEnvReadyReq isEnvReadyReq) {
        if (isEnvReadyReq == null) {
            return new WebIsEnvReadyRequest();
        }
        WebIsEnvReadyRequest webIsEnvReadyRequest = new WebIsEnvReadyRequest();
        webIsEnvReadyRequest.setReservedInfor(isEnvReadyReq.getReservedInfor());
        return webIsEnvReadyRequest;
    }

    public IsEnvReadyTask(Context context, IsEnvReadyReq isEnvReadyReq) {
        super(context, isEnvReadyReq);
    }
}
