package com.huawei.hms.iapfull;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.iapfull.bean.PayResult;
import com.huawei.hms.iapfull.bean.WebOrderRequest;
import com.huawei.hms.iapfull.bean.WebProductDetailRequest;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoRequest;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;

/* loaded from: classes4.dex */
public interface IIapFullAPI {
    void getOrderDetail(WebOrderRequest webOrderRequest, WebPayCallback webPayCallback);

    Intent getPayIntent(Context context, PayRequest payRequest);

    PayResult getPayResultFromIntent(Intent intent);

    void getProductDetail(WebProductDetailRequest webProductDetailRequest, WebPayCallback webPayCallback);

    Intent getProductPayIntent(Context context, WebProductPayRequest webProductPayRequest);

    void getPurchaseInfo(WebPurchaseInfoRequest webPurchaseInfoRequest, WebPayCallback webPayCallback);

    Intent getWithholdIntent(Context context, PayRequest payRequest);
}
