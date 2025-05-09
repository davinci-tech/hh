package com.huawei.hms.support.api.pay;

import android.content.Intent;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.entity.pay.HwWalletInfoRequest;
import com.huawei.hms.support.api.entity.pay.InternalPayRequest;
import com.huawei.hms.support.api.entity.pay.OrderRequest;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.entity.pay.ProductDetailRequest;
import com.huawei.hms.support.api.entity.pay.ProductPayRequest;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoRequest;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;

/* loaded from: classes4.dex */
public interface HuaweiPayApi {
    PendingResult<PayResult> addWithholdingPlan(HuaweiApiClient huaweiApiClient, WithholdRequest withholdRequest);

    PendingResult<OrderResult> getOrderDetail(HuaweiApiClient huaweiApiClient, OrderRequest orderRequest);

    PayResultInfo getPayResultInfoFromIntent(Intent intent);

    PendingResult<ProductDetailResult> getProductDetails(HuaweiApiClient huaweiApiClient, ProductDetailRequest productDetailRequest);

    ProductPayResultInfo getProductPayResultFromIntent(Intent intent);

    PendingResult<PurchaseInfoResult> getPurchaseInfo(HuaweiApiClient huaweiApiClient, PurchaseInfoRequest purchaseInfoRequest);

    PendingResult<GetWalletUiIntentResult> getWalletUiIntent(HuaweiApiClient huaweiApiClient, int i);

    PendingResult<PayResult> internalPay(HuaweiApiClient huaweiApiClient, InternalPayRequest internalPayRequest);

    PendingResult<PayResult> pay(HuaweiApiClient huaweiApiClient, PayReq payReq);

    PendingResult<PayResult> productPay(HuaweiApiClient huaweiApiClient, ProductPayRequest productPayRequest);

    PendingResult<HwWalletInfoResult> queryWalletInfo(HuaweiApiClient huaweiApiClient, HwWalletInfoRequest hwWalletInfoRequest);
}
