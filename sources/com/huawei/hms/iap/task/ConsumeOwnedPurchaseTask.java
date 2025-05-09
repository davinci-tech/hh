package com.huawei.hms.iap.task;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.iap.entity.ConsumeOwnedPurchaseReq;
import com.huawei.hms.iap.entity.ConsumeOwnedPurchaseResult;
import com.huawei.hms.iap.entity.a;
import com.huawei.hms.iapfull.IIapFullAPIVer4;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes9.dex */
public class ConsumeOwnedPurchaseTask extends BaseIapFullTask<ConsumeOwnedPurchaseResult, ConsumeOwnedPurchaseReq> {
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    protected void setResult() {
        this.mResult = new ConsumeOwnedPurchaseResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    public void handleRequest(ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq, IIapFullAPIVer4 iIapFullAPIVer4) {
        iIapFullAPIVer4.consumeOwnedPurchase(a(consumeOwnedPurchaseReq), new WebPayCallback() { // from class: com.huawei.hms.iap.task.ConsumeOwnedPurchaseTask.1
            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onSuccess(String str) {
                a aVar = new a();
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JsonUtil.jsonToEntity(str, aVar);
                    } catch (IllegalArgumentException e) {
                        HMSLog.e("ConsumeOwnedPurchaseTask", "ConsumeOwnedPurchaseResp jsonToEntity " + e.getMessage());
                    }
                }
                ConsumeOwnedPurchaseTask.this.mResult = new ConsumeOwnedPurchaseResult();
                ((ConsumeOwnedPurchaseResult) ConsumeOwnedPurchaseTask.this.mResult).setReturnCode(aVar.getReturnCode());
                ((ConsumeOwnedPurchaseResult) ConsumeOwnedPurchaseTask.this.mResult).setErrMsg(aVar.getErrMsg());
                ((ConsumeOwnedPurchaseResult) ConsumeOwnedPurchaseTask.this.mResult).setStatus(aVar.getCommonStatus());
                ((ConsumeOwnedPurchaseResult) ConsumeOwnedPurchaseTask.this.mResult).setConsumePurchaseData(aVar.getConsumePurchaseData());
                ((ConsumeOwnedPurchaseResult) ConsumeOwnedPurchaseTask.this.mResult).setDataSignature(aVar.getDataSignature());
                ((ConsumeOwnedPurchaseResult) ConsumeOwnedPurchaseTask.this.mResult).setSignatureAlgorithm(aVar.getSignatureAlgorithm());
                ConsumeOwnedPurchaseTask.this.handleRequestSuccess();
            }

            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onFailure(int i, String str) {
                ConsumeOwnedPurchaseTask.this.handleRequestFailed(i, str);
            }
        });
    }

    private WebConsumeOwnedPurchaseRequest a(ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq) {
        if (consumeOwnedPurchaseReq == null) {
            return new WebConsumeOwnedPurchaseRequest();
        }
        WebConsumeOwnedPurchaseRequest webConsumeOwnedPurchaseRequest = new WebConsumeOwnedPurchaseRequest();
        webConsumeOwnedPurchaseRequest.setReservedInfor(consumeOwnedPurchaseReq.getReservedInfor());
        webConsumeOwnedPurchaseRequest.setSignatureAlgorithm(consumeOwnedPurchaseReq.getSignatureAlgorithm());
        webConsumeOwnedPurchaseRequest.setDeveloperChallenge(consumeOwnedPurchaseReq.getDeveloperChallenge());
        webConsumeOwnedPurchaseRequest.setPurchaseToken(consumeOwnedPurchaseReq.getPurchaseToken());
        return webConsumeOwnedPurchaseRequest;
    }

    public ConsumeOwnedPurchaseTask(Context context, ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq) {
        super(context, consumeOwnedPurchaseReq);
    }
}
