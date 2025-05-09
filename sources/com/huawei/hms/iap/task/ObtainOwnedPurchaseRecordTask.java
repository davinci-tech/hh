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

/* loaded from: classes9.dex */
public class ObtainOwnedPurchaseRecordTask extends BaseIapFullTask<OwnedPurchasesResult, OwnedPurchasesReq> {
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    protected void setResult() {
        this.mResult = new OwnedPurchasesResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.iap.task.BaseIapFullTask
    public void handleRequest(OwnedPurchasesReq ownedPurchasesReq, IIapFullAPIVer4 iIapFullAPIVer4) {
        iIapFullAPIVer4.obtainOwnedPurchaseRecord(createRequest(ownedPurchasesReq), new WebPayCallback() { // from class: com.huawei.hms.iap.task.ObtainOwnedPurchaseRecordTask.1
            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onSuccess(String str) {
                d dVar = new d();
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JsonUtil.jsonToEntity(str, dVar);
                    } catch (IllegalArgumentException e) {
                        HMSLog.e("ObtainOwnedPurchaseRecordTask", "OwnedPurchasesResp jsonToEntity " + e.getMessage());
                    }
                }
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setReturnCode(dVar.getReturnCode());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setErrMsg(dVar.getErrMsg());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setStatus(dVar.getCommonStatus());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setContinuationToken(dVar.getContinuationToken());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setInAppPurchaseDataList(dVar.getInAppPurchaseDataList());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setInAppSignature(dVar.getInAppSignatureList());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setItemList(dVar.getItemList());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setPlacedInappPurchaseDataList(dVar.getPlacedInappPurchaseDataList());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setPlacedInappSignatureList(dVar.getPlacedInappSignatureList());
                ((OwnedPurchasesResult) ObtainOwnedPurchaseRecordTask.this.mResult).setSignatureAlgorithm(dVar.getSignatureAlgorithm());
                ObtainOwnedPurchaseRecordTask.this.handleRequestSuccess();
            }

            @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
            public void onFailure(int i, String str) {
                ObtainOwnedPurchaseRecordTask.this.handleRequestFailed(i, str);
            }
        });
    }

    protected WebOwnedPurchasesRequest createRequest(OwnedPurchasesReq ownedPurchasesReq) {
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

    public ObtainOwnedPurchaseRecordTask(Context context, OwnedPurchasesReq ownedPurchasesReq) {
        super(context, ownedPurchasesReq);
    }
}
