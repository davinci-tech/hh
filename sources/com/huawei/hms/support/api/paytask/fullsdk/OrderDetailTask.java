package com.huawei.hms.support.api.paytask.fullsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iapfull.IapFullAPIFactory;
import com.huawei.hms.iapfull.bean.WebOrderRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.OrderRequest;
import com.huawei.hms.support.api.entity.pay.OrderResp;
import com.huawei.hms.support.api.pay.OrderResult;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public class OrderDetailTask extends Task<OrderResult> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f5974a;
    private boolean b;
    private OrderResult c = new OrderResult();
    private OnSuccessListener<OrderResult> d;
    private OnFailureListener e;

    @Override // com.huawei.hmf.tasks.Task
    public Exception getException() {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hmf.tasks.Task
    public <E extends Throwable> OrderResult getResultThrowException(Class<E> cls) throws Throwable {
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
        return this.f5974a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hmf.tasks.Task
    public OrderResult getResult() {
        return this.c;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<OrderResult> addOnSuccessListener(Executor executor, OnSuccessListener<OrderResult> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<OrderResult> addOnSuccessListener(OnSuccessListener<OrderResult> onSuccessListener) {
        if (onSuccessListener != null) {
            this.d = onSuccessListener;
        }
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<OrderResult> addOnSuccessListener(Activity activity, OnSuccessListener<OrderResult> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<OrderResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<OrderResult> addOnFailureListener(OnFailureListener onFailureListener) {
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
    public Task<OrderResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    private WebOrderRequest a(OrderRequest orderRequest) {
        WebOrderRequest webOrderRequest = new WebOrderRequest();
        webOrderRequest.setMerchantId(orderRequest.getMerchantId());
        webOrderRequest.setRequestId(orderRequest.getRequestId());
        webOrderRequest.setKeyType(orderRequest.getKeyType());
        webOrderRequest.setTime(orderRequest.getTime());
        webOrderRequest.setReservedInfor(orderRequest.getReservedInfor());
        webOrderRequest.setSign(orderRequest.getSign());
        webOrderRequest.setSignatureAlgorithm(orderRequest.getSignatureAlgorithm());
        return webOrderRequest;
    }

    public OrderDetailTask(Context context, OrderRequest orderRequest) {
        if (context != null) {
            IapFullAPIFactory.createIapFullAPI(context).getOrderDetail(a(orderRequest), new WebPayCallback() { // from class: com.huawei.hms.support.api.paytask.fullsdk.OrderDetailTask.1
                @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
                public void onSuccess(String str) {
                    OrderResp orderResp = new OrderResp();
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JsonUtil.jsonToEntity(str, orderResp);
                        } catch (IllegalArgumentException e) {
                            HMSLog.e("ProductDetailTask", "OrderResp jsonToEntity " + e.getMessage());
                        }
                    }
                    OrderResult orderResult = new OrderResult(orderResp);
                    OrderDetailTask.this.b = true;
                    OrderDetailTask.this.c = orderResult;
                    OrderDetailTask.this.f5974a = true;
                    if (OrderDetailTask.this.d != null) {
                        OrderDetailTask.this.d.onSuccess(OrderDetailTask.this.c);
                    }
                }

                @Override // com.huawei.hms.iapfull.webpay.callback.WebPayCallback
                public void onFailure(int i, String str) {
                    OrderResp orderResp = new OrderResp();
                    orderResp.setCommonStatus(new Status(i, str));
                    OrderDetailTask.this.b = false;
                    OrderDetailTask.this.c = new OrderResult(orderResp);
                    OrderDetailTask.this.f5974a = true;
                    if (OrderDetailTask.this.e != null) {
                        OrderDetailTask.this.e.onFailure(new IapApiException(OrderDetailTask.this.c.getStatus()));
                    }
                }
            });
            return;
        }
        this.c.setStatus(new Status(30001, "param is error"));
        this.b = false;
        this.f5974a = true;
    }
}
