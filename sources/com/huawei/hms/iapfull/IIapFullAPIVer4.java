package com.huawei.hms.iapfull;

import android.content.Intent;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseRequest;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyRequest;
import com.huawei.hms.iapfull.bean.WebOwnedPurchasesRequest;
import com.huawei.hms.iapfull.bean.WebProductInfoRequest;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;

/* loaded from: classes4.dex */
public interface IIapFullAPIVer4 {
    void consumeOwnedPurchase(WebConsumeOwnedPurchaseRequest webConsumeOwnedPurchaseRequest, WebPayCallback webPayCallback);

    Intent getPmsPayIntent(WebProductPayRequest webProductPayRequest);

    void isEnvReady(WebIsEnvReadyRequest webIsEnvReadyRequest, WebPayCallback webPayCallback);

    void obtainOwnedPurchaseRecord(WebOwnedPurchasesRequest webOwnedPurchasesRequest, WebPayCallback webPayCallback);

    void obtainOwnedPurchases(WebOwnedPurchasesRequest webOwnedPurchasesRequest, WebPayCallback webPayCallback);

    void obtainProductInfo(WebProductInfoRequest webProductInfoRequest, WebPayCallback webPayCallback);
}
