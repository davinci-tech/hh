package com.huawei.hms.support.api.paytask.fullsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iapfull.IapFullAPIFactory;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoRequest;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoResp;
import com.huawei.hms.support.api.pay.PurchaseInfoResult;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public class PurchaseInfoTask extends Task<PurchaseInfoResult> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f5978a;
    private boolean b;
    private PurchaseInfoResult c = new PurchaseInfoResult();
    private OnSuccessListener<PurchaseInfoResult> d;
    private OnFailureListener e;

    @Override // com.huawei.hmf.tasks.Task
    public Exception getException() {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hmf.tasks.Task
    public <E extends Throwable> PurchaseInfoResult getResultThrowException(Class<E> cls) throws Throwable {
        return null;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isCanceled() {
        return false;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isSuccessful() {
        return this.b;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isComplete() {
        return this.f5978a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hmf.tasks.Task
    public PurchaseInfoResult getResult() {
        return this.c;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<PurchaseInfoResult> addOnSuccessListener(Executor executor, OnSuccessListener<PurchaseInfoResult> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<PurchaseInfoResult> addOnSuccessListener(OnSuccessListener<PurchaseInfoResult> onSuccessListener) {
        if (onSuccessListener != null) {
            this.d = onSuccessListener;
        }
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<PurchaseInfoResult> addOnSuccessListener(Activity activity, OnSuccessListener<PurchaseInfoResult> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<PurchaseInfoResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<PurchaseInfoResult> addOnFailureListener(OnFailureListener onFailureListener) {
        if (onFailureListener != null) {
            if (isComplete()) {
                onFailureListener.onFailure(new IapApiException(this.c.getStatus()));
            } else {
                this.e = onFailureListener;
            }
        }
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<PurchaseInfoResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    private WebPurchaseInfoRequest a(PurchaseInfoRequest purchaseInfoRequest) {
        WebPurchaseInfoRequest webPurchaseInfoRequest = new WebPurchaseInfoRequest();
        webPurchaseInfoRequest.setAppId(purchaseInfoRequest.getAppId());
        webPurchaseInfoRequest.setMerchantId(purchaseInfoRequest.getMerchantId());
        webPurchaseInfoRequest.setPageNo(purchaseInfoRequest.getPageNo());
        webPurchaseInfoRequest.setPriceType(purchaseInfoRequest.getPriceType());
        webPurchaseInfoRequest.setProductId(purchaseInfoRequest.getProductId());
        webPurchaseInfoRequest.setReservedInfor(purchaseInfoRequest.getReservedInfor());
        webPurchaseInfoRequest.setSign(purchaseInfoRequest.getSign());
        webPurchaseInfoRequest.setSignatureAlgorithm(purchaseInfoRequest.getSignatureAlgorithm());
        webPurchaseInfoRequest.setTs(purchaseInfoRequest.getTs());
        return webPurchaseInfoRequest;
    }

    public PurchaseInfoTask(Context context, PurchaseInfoRequest purchaseInfoRequest) {
        if (context != null) {
            IapFullAPIFactory.createIapFullAPI(context).getPurchaseInfo(a(purchaseInfoRequest), new WebPayCallback() { // from class: com.huawei.hms.support.api.paytask.fullsdk.PurchaseInfoTask.1
                @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
                public void onSuccess(String str) {
                    PurchaseInfoResp purchaseInfoResp = new PurchaseInfoResp();
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JsonUtil.jsonToEntity(str, purchaseInfoResp);
                        } catch (IllegalArgumentException e) {
                            HMSLog.e("ProductDetailTask", "ProductDetailResp jsonToEntity " + e.getMessage());
                        }
                    }
                    PurchaseInfoResult purchaseInfoResult = new PurchaseInfoResult(purchaseInfoResp);
                    PurchaseInfoTask.this.b = true;
                    PurchaseInfoTask.this.c = purchaseInfoResult;
                    PurchaseInfoTask.this.f5978a = true;
                    if (PurchaseInfoTask.this.d != null) {
                        PurchaseInfoTask.this.d.onSuccess(PurchaseInfoTask.this.c);
                    }
                }

                @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
                public void onFailure(int i, String str) {
                    PurchaseInfoResp purchaseInfoResp = new PurchaseInfoResp();
                    purchaseInfoResp.setCommonStatus(new Status(i, str));
                    PurchaseInfoTask.this.b = false;
                    PurchaseInfoTask.this.c = new PurchaseInfoResult(purchaseInfoResp);
                    PurchaseInfoTask.this.f5978a = true;
                    if (PurchaseInfoTask.this.e != null) {
                        PurchaseInfoTask.this.e.onFailure(new IapApiException(PurchaseInfoTask.this.c.getStatus()));
                    }
                }
            });
            return;
        }
        this.c.setStatus(new Status(30001, "param is error"));
        this.b = false;
        this.f5978a = true;
    }
}
