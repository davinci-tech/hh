package com.huawei.hms.support.api.paytask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.iap.util.a;
import com.huawei.hms.iapfull.IapFullAPIFactory;
import com.huawei.hms.support.api.entity.pay.HwWalletInfoRequest;
import com.huawei.hms.support.api.entity.pay.InternalPayRequest;
import com.huawei.hms.support.api.entity.pay.OrderRequest;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.entity.pay.ProductDetailRequest;
import com.huawei.hms.support.api.entity.pay.ProductPayRequest;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoRequest;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;
import com.huawei.hms.support.api.pay.GetWalletUiIntentResult;
import com.huawei.hms.support.api.pay.HwWalletInfoResult;
import com.huawei.hms.support.api.pay.OrderResult;
import com.huawei.hms.support.api.pay.PayResult;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.hms.support.api.pay.ProductDetailResult;
import com.huawei.hms.support.api.pay.ProductPayResultInfo;
import com.huawei.hms.support.api.pay.PurchaseInfoResult;
import com.huawei.hms.support.api.paytask.fullsdk.FailureTask;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class PayClientEntryImpl implements PayClient {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f5968a;
    private PayClientImpl b;
    private PayClientFullImpl c;

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public void setSubAppId(String str) {
        PayClientImpl a2 = a();
        if (a2 == null) {
            HMSLog.e("PayClientImpl", "setSubAppId payClient is null");
            return;
        }
        try {
            a2.setSubAppId(str);
        } catch (ApiException unused) {
            HMSLog.d("PayClientImpl", "setSubAppId fail");
        }
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<HwWalletInfoResult> queryWalletInfo(HwWalletInfoRequest hwWalletInfoRequest) {
        Checker.checkNonNull(hwWalletInfoRequest);
        PayClientImpl a2 = a();
        return a2 != null ? a2.queryWalletInfo(hwWalletInfoRequest) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<PayResult> productPay(ProductPayRequest productPayRequest) {
        Checker.checkNonNull(productPayRequest);
        Context context = this.f5968a.get();
        if (context == null) {
            return new FailureTask();
        }
        if (!a.a(context, productPayRequest.reservedInfor, "ProductPay")) {
            PayClientImpl a2 = a();
            return a2 != null ? a2.productPay(productPayRequest) : new FailureTask();
        }
        HMSLog.i("PayClientImpl", "productPay with Full SDK branch.");
        PayClientFullImpl b = b();
        return b != null ? b.productPay(productPayRequest) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<PayResult> pay(PayReq payReq) {
        Checker.checkNonNull(payReq);
        Context context = this.f5968a.get();
        if (context == null) {
            return new FailureTask();
        }
        if (a.a(context, payReq.reservedInfor, payReq.country, payReq.currency, IapFullAPIFactory.PAY_TRADE_TYPE)) {
            HMSLog.i("PayClientImpl", "pay with Full SDK branch.");
            PayClientFullImpl b = b();
            return b != null ? b.pay(payReq) : new FailureTask();
        }
        HMSLog.i("PayClientImpl", "pay with IAP SDK branch.");
        PayClientImpl a2 = a();
        return a2 != null ? a2.pay(payReq) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<PayResult> internalPay(InternalPayRequest internalPayRequest) {
        Checker.checkNonNull(internalPayRequest);
        PayClientImpl a2 = a();
        return a2 != null ? a2.internalPay(internalPayRequest) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<GetWalletUiIntentResult> getWalletUiIntent(int i) {
        PayClientImpl a2 = a();
        return a2 != null ? a2.getWalletUiIntent(i) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<PurchaseInfoResult> getPurchaseInfo(PurchaseInfoRequest purchaseInfoRequest) {
        Checker.checkNonNull(purchaseInfoRequest);
        Context context = this.f5968a.get();
        if (context == null) {
            return new FailureTask();
        }
        if (!a.a(context, purchaseInfoRequest.reservedInfor, "Purchaseinfo")) {
            PayClientImpl a2 = a();
            return a2 != null ? a2.getPurchaseInfo(purchaseInfoRequest) : new FailureTask();
        }
        HMSLog.i("PayClientImpl", "getProductDetails with Full SDK branch.");
        PayClientFullImpl b = b();
        return b != null ? b.getPurchaseInfo(purchaseInfoRequest) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public ProductPayResultInfo getProductPayResultFromIntent(Intent intent) {
        Checker.checkNonNull(intent);
        PayClientImpl a2 = a();
        if (a2 != null) {
            return a2.getProductPayResultFromIntent(intent);
        }
        return null;
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<ProductDetailResult> getProductDetails(ProductDetailRequest productDetailRequest) {
        Checker.checkNonNull(productDetailRequest);
        Context context = this.f5968a.get();
        if (context == null) {
            return new FailureTask();
        }
        if (!a.a(context, productDetailRequest.reservedInfor, "Productdetail")) {
            PayClientImpl a2 = a();
            return a2 != null ? a2.getProductDetails(productDetailRequest) : new FailureTask();
        }
        HMSLog.i("PayClientImpl", "getProductDetails with Full SDK branch.");
        PayClientFullImpl b = b();
        return b != null ? b.getProductDetails(productDetailRequest) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public PayResultInfo getPayResultInfoFromIntent(Intent intent) {
        Checker.checkNonNull(intent);
        PayClientImpl a2 = a();
        if (a2 != null) {
            return a2.getPayResultInfoFromIntent(intent);
        }
        return null;
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<OrderResult> getOrderDetail(OrderRequest orderRequest) {
        Checker.checkNonNull(orderRequest);
        Context context = this.f5968a.get();
        if (context == null) {
            return new FailureTask();
        }
        if (!a.a(context, orderRequest.reservedInfor, "GetOrderDetail")) {
            PayClientImpl a2 = a();
            return a2 != null ? a2.getOrderDetail(orderRequest) : new FailureTask();
        }
        HMSLog.i("PayClientImpl", "getOrderDetail with Full SDK branch.");
        PayClientFullImpl b = b();
        return b != null ? b.getOrderDetail(orderRequest) : new FailureTask();
    }

    @Override // com.huawei.hms.support.api.paytask.PayClient
    public Task<PayResult> addWithholdingPlan(WithholdRequest withholdRequest) {
        Checker.checkNonNull(withholdRequest);
        Context context = this.f5968a.get();
        if (context == null) {
            return new FailureTask();
        }
        if (a.a(context, withholdRequest.reservedInfor, withholdRequest.country, withholdRequest.currency, IapFullAPIFactory.WITHHOLD_TRADE_TYPE)) {
            HMSLog.i("PayClientImpl", "withhold with Full SDK branch.");
            PayClientFullImpl b = b();
            return b != null ? b.addWithholdingPlan(withholdRequest) : new FailureTask();
        }
        HMSLog.i("PayClientImpl", "withhold with IAP SDK branch.");
        PayClientImpl a2 = a();
        return a2 != null ? a2.addWithholdingPlan(withholdRequest) : new FailureTask();
    }

    private PayClientFullImpl b() {
        if (this.c == null) {
            Context context = this.f5968a.get();
            if (context == null) {
                HMSLog.e("PayClientImpl", "getPayClientFullImpl context is null.");
                return null;
            }
            this.c = new PayClientFullImpl(context);
        }
        return this.c;
    }

    private PayClientImpl a() {
        if (this.b == null) {
            Context context = this.f5968a.get();
            if (context == null) {
                HMSLog.e("PayClientImpl", "getPayClientImpl context is null.");
                return null;
            }
            this.b = context instanceof Activity ? new PayClientImpl((Activity) context) : new PayClientImpl(context);
        }
        return this.b;
    }

    PayClientEntryImpl(Context context) {
        this.f5968a = new WeakReference<>(context);
    }

    PayClientEntryImpl(Activity activity) {
        this.f5968a = new WeakReference<>(activity);
    }
}
