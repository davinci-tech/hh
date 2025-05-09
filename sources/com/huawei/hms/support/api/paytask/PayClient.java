package com.huawei.hms.support.api.paytask;

import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
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

/* loaded from: classes4.dex */
public interface PayClient {
    Task<PayResult> addWithholdingPlan(WithholdRequest withholdRequest);

    Task<OrderResult> getOrderDetail(OrderRequest orderRequest);

    PayResultInfo getPayResultInfoFromIntent(Intent intent);

    Task<ProductDetailResult> getProductDetails(ProductDetailRequest productDetailRequest);

    ProductPayResultInfo getProductPayResultFromIntent(Intent intent);

    Task<PurchaseInfoResult> getPurchaseInfo(PurchaseInfoRequest purchaseInfoRequest);

    Task<GetWalletUiIntentResult> getWalletUiIntent(int i);

    Task<PayResult> internalPay(InternalPayRequest internalPayRequest);

    Task<PayResult> pay(PayReq payReq);

    Task<PayResult> productPay(ProductPayRequest productPayRequest);

    Task<HwWalletInfoResult> queryWalletInfo(HwWalletInfoRequest hwWalletInfoRequest);

    void setSubAppId(String str) throws ApiException;
}
