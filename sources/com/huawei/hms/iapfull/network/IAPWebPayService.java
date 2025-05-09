package com.huawei.hms.iapfull.network;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseRequest;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseResponse;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyRequest;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyResponse;
import com.huawei.hms.iapfull.bean.WebObtainOwnedPurchasesResponse;
import com.huawei.hms.iapfull.bean.WebOrderRequest;
import com.huawei.hms.iapfull.bean.WebOrderResp;
import com.huawei.hms.iapfull.bean.WebOwnedPurchasesRequest;
import com.huawei.hms.iapfull.bean.WebProductDetailRequest;
import com.huawei.hms.iapfull.bean.WebProductDetailResponse;
import com.huawei.hms.iapfull.bean.WebProductInfoRequest;
import com.huawei.hms.iapfull.bean.WebProductInfoResp;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoRequest;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoResp;
import com.huawei.hms.iapfull.network.model.WebPayPayResponse;
import com.huawei.hms.iapfull.network.model.WebPayRequest;
import java.util.HashMap;

/* loaded from: classes4.dex */
public interface IAPWebPayService {
    @POST("/api/webpay/purchase/v1/consumeOwnedPurchase")
    Submit<WebConsumeOwnedPurchaseResponse> consumeOwnedPurchase(@HeaderMap HashMap<String, String> hashMap, @Body WebConsumeOwnedPurchaseRequest webConsumeOwnedPurchaseRequest);

    @POST("/api/webpay/purchase/v1/createPurchase")
    Submit<WebPayPayResponse> createPurchase(@HeaderMap HashMap<String, String> hashMap, @Body WebProductPayRequest webProductPayRequest);

    @POST("/api/webpay/pay/v1/getOrderDetail")
    Submit<WebOrderResp> getOrderDetail(@HeaderMap HashMap<String, String> hashMap, @Body WebOrderRequest webOrderRequest);

    @POST("/api/webpay/pay/v1/getProductDetail")
    Submit<WebProductDetailResponse> getProductDetail(@HeaderMap HashMap<String, String> hashMap, @Body WebProductDetailRequest webProductDetailRequest);

    @POST("/api/webpay/pay/v1/getPurchaseInfo")
    Submit<WebPurchaseInfoResp> getPurchaseInfo(@HeaderMap HashMap<String, String> hashMap, @Body WebPurchaseInfoRequest webPurchaseInfoRequest);

    @POST("/api/webpay/purchase/v1/isEnvReady")
    Submit<WebIsEnvReadyResponse> isEnvReady(@HeaderMap HashMap<String, String> hashMap, @Body WebIsEnvReadyRequest webIsEnvReadyRequest);

    @POST("/api/webpay/purchase/v1/obtainOwnedPurchaseRecord")
    Submit<WebObtainOwnedPurchasesResponse> obtainOwnedPurchaseRecord(@HeaderMap HashMap<String, String> hashMap, @Body WebOwnedPurchasesRequest webOwnedPurchasesRequest);

    @POST("/api/webpay/purchase/v1/obtainOwnedPurchases")
    Submit<WebObtainOwnedPurchasesResponse> obtainOwnedPurchases(@HeaderMap HashMap<String, String> hashMap, @Body WebOwnedPurchasesRequest webOwnedPurchasesRequest);

    @POST("/api/webpay/purchase/v1/obtainProductInfo")
    Submit<WebProductInfoResp> obtainProductInfo(@HeaderMap HashMap<String, String> hashMap, @Body WebProductInfoRequest webProductInfoRequest);

    @POST("/api/webpay/pay/v1/pay")
    Submit<WebPayPayResponse> webPay(@HeaderMap HashMap<String, String> hashMap, @Body WebPayRequest webPayRequest);

    @POST("/api/webpay/pay/v1/productPay")
    Submit<WebPayPayResponse> webProductPay(@HeaderMap HashMap<String, String> hashMap, @Body WebProductPayRequest webProductPayRequest);
}
