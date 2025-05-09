package com.huawei.hms.iap.task;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.iap.entity.OwnedPurchasesReq;
import com.huawei.hms.iap.entity.OwnedPurchasesResult;
import com.huawei.hms.iap.entity.d;
import com.huawei.hms.iapfull.IIapFullAPIVer4;
import com.huawei.hms.iapfull.bean.WebOwnedPurchasesRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes4.dex */
public class ObtainOwnedPurchasesTask extends BaseIapFullTask<OwnedPurchasesResult, OwnedPurchasesReq> {
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    protected void setResult() {
        this.mResult = new OwnedPurchasesResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    public void handleRequest(OwnedPurchasesReq ownedPurchasesReq, IIapFullAPIVer4 iIapFullAPIVer4) {
        iIapFullAPIVer4.obtainOwnedPurchases(a(ownedPurchasesReq), new WebPayCallback() { // from class: com.huawei.hms.iap.task.ObtainOwnedPurchasesTask.1
            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onSuccess(String str) {
                d dVar = new d();
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JsonUtil.jsonToEntity(str, dVar);
                    } catch (IllegalArgumentException e) {
                        HMSLog.e("ObtainOwnedPurchasesTask", "OwnedPurchasesResp jsonToEntity " + e.getMessage());
                    }
                }
                ObtainOwnedPurchasesTask.this.mResult = new OwnedPurchasesResult();
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setReturnCode(dVar.getReturnCode());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setErrMsg(dVar.getErrMsg());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setStatus(dVar.getCommonStatus());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setContinuationToken(dVar.getContinuationToken());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setInAppPurchaseDataList(dVar.getInAppPurchaseDataList());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setInAppSignature(dVar.getInAppSignatureList());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setItemList(dVar.getItemList());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setPlacedInappPurchaseDataList(dVar.getPlacedInappPurchaseDataList());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setPlacedInappSignatureList(dVar.getPlacedInappSignatureList());
                ((OwnedPurchasesResult) ObtainOwnedPurchasesTask.this.mResult).setSignatureAlgorithm(dVar.getSignatureAlgorithm());
                ObtainOwnedPurchasesTask.this.handleRequestSuccess();
            }

            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onFailure(int i, String str) {
                ObtainOwnedPurchasesTask.this.handleRequestFailed(i, str);
            }
        });
    }

    private WebOwnedPurchasesRequest a(OwnedPurchasesReq ownedPurchasesReq) {
        if (ownedPurchasesReq == null) {
            return new WebOwnedPurchasesRequest();
        }
        WebOwnedPurchasesRequest webOwnedPurchasesRequest = new WebOwnedPurchasesRequest();
        webOwnedPurchasesRequest.setContinuationToken(ownedPurchasesReq.getContinuationToken());
        webOwnedPurchasesRequest.setPriceType(ownedPurchasesReq.getPriceType());
        webOwnedPurchasesRequest.setReservedInfor(ownedPurchasesReq.getReservedInfor());
        webOwnedPurchasesRequest.setSignatureAlgorithm(ownedPurchasesReq.getSignatureAlgorithm());
        return webOwnedPurchasesRequest;
    }

    public ObtainOwnedPurchasesTask(Context context, OwnedPurchasesReq ownedPurchasesReq) {
        super(context, ownedPurchasesReq);
    }
}
