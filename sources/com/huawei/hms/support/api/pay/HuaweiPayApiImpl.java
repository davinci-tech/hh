package com.huawei.hms.support.api.pay;

import android.content.Intent;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.ErrorResultImpl;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.api.entity.pay.GamePayReq;
import com.huawei.hms.support.api.entity.pay.GameProductPayReq;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.api.entity.pay.HwWalletInfoRequest;
import com.huawei.hms.support.api.entity.pay.HwWalletInoResp;
import com.huawei.hms.support.api.entity.pay.InternalPayRequest;
import com.huawei.hms.support.api.entity.pay.OrderRequest;
import com.huawei.hms.support.api.entity.pay.OrderResp;
import com.huawei.hms.support.api.entity.pay.PayNaming;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.entity.pay.PayResp;
import com.huawei.hms.support.api.entity.pay.ProductDetailRequest;
import com.huawei.hms.support.api.entity.pay.ProductDetailResp;
import com.huawei.hms.support.api.entity.pay.ProductPayRequest;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoInnerRequest;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoRequest;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoResp;
import com.huawei.hms.support.api.entity.pay.WalletIntentResp;
import com.huawei.hms.support.api.entity.pay.WalletUiIntentReq;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;

/* loaded from: classes4.dex */
public class HuaweiPayApiImpl implements HuaweiPayApi {
    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<HwWalletInfoResult> queryWalletInfo(HuaweiApiClient huaweiApiClient, HwWalletInfoRequest hwWalletInfoRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter queryWalletInfo");
        return Util.getHmsVersion(huaweiApiClient.getContext()) < 20602000 ? new h(CommonCode.ErrorCode.HMS_VERSION_CONFIGER_INVALID) : new i(huaweiApiClient, PayNaming.walletQuery, hwWalletInfoRequest);
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<PayResult> productPay(HuaweiApiClient huaweiApiClient, ProductPayRequest productPayRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter productPay");
        HiAnalyticsUtil.getInstance().onEvent(huaweiApiClient.getContext().getApplicationContext(), HiAnalyticsConstant.KeyAndValue.START_PAY, productPayRequest.requestId);
        return new f(huaweiApiClient, PayNaming.pms, new GameProductPayReq(productPayRequest, huaweiApiClient.getCpID()));
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<PayResult> pay(HuaweiApiClient huaweiApiClient, PayReq payReq) {
        HMSLog.i("HuaweiPayApiImpl", "Enter pay");
        HiAnalyticsUtil.getInstance().onEvent(huaweiApiClient.getContext().getApplicationContext(), HiAnalyticsConstant.KeyAndValue.START_PAY, payReq.requestId);
        return new f(huaweiApiClient, PayNaming.pay, new GamePayReq(payReq, huaweiApiClient.getCpID()));
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<PayResult> internalPay(HuaweiApiClient huaweiApiClient, InternalPayRequest internalPayRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter internalPay");
        return Util.getHmsVersion(huaweiApiClient.getContext()) < 20601000 ? new e(CommonCode.ErrorCode.HMS_VERSION_CONFIGER_INVALID) : new f(huaweiApiClient, PayNaming.internalPay, internalPayRequest);
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<GetWalletUiIntentResult> getWalletUiIntent(HuaweiApiClient huaweiApiClient, int i2) {
        HMSLog.i("HuaweiPayApiImpl", "Enter getWalletUiIntent type:" + i2);
        if (Util.getHmsVersion(huaweiApiClient.getContext()) < 20602000) {
            return new d(CommonCode.ErrorCode.HMS_VERSION_CONFIGER_INVALID);
        }
        WalletUiIntentReq walletUiIntentReq = new WalletUiIntentReq();
        walletUiIntentReq.setType(i2);
        return a(huaweiApiClient, walletUiIntentReq);
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<PurchaseInfoResult> getPurchaseInfo(HuaweiApiClient huaweiApiClient, PurchaseInfoRequest purchaseInfoRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter getPurchaseInfo");
        return Util.getHmsVersion(huaweiApiClient.getContext()) < 20601000 ? new b(CommonCode.ErrorCode.HMS_VERSION_CONFIGER_INVALID) : new c(huaweiApiClient, PayNaming.purchaseinfo, new PurchaseInfoInnerRequest(purchaseInfoRequest));
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public ProductPayResultInfo getProductPayResultFromIntent(Intent intent) {
        if (intent == null) {
            HMSLog.e("HuaweiPayApiImpl", "getProductPayResultFromIntent intent is null");
            return null;
        }
        ProductPayResultInfo productPayResultInfo = new ProductPayResultInfo();
        productPayResultInfo.setReturnCode(com.huawei.hms.support.api.pay.util.a.a(intent, "returnCode", 1));
        productPayResultInfo.setOrderID(com.huawei.hms.support.api.pay.util.a.a(intent, "orderID"));
        productPayResultInfo.setErrMsg(com.huawei.hms.support.api.pay.util.a.a(intent, "errMsg"));
        productPayResultInfo.setProductNo(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_PRODUCT_NO));
        productPayResultInfo.setMicrosAmount(com.huawei.hms.support.api.pay.util.a.a(intent, "microsAmount", 0L));
        productPayResultInfo.setCurrency(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_CURRENCY));
        productPayResultInfo.setRequestId(com.huawei.hms.support.api.pay.util.a.a(intent, "requestId"));
        productPayResultInfo.setMerchantId(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_MERCHANTID));
        productPayResultInfo.setTime(com.huawei.hms.support.api.pay.util.a.a(intent, "time"));
        productPayResultInfo.setCountry(com.huawei.hms.support.api.pay.util.a.a(intent, "country"));
        productPayResultInfo.setSign(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_SIGN));
        productPayResultInfo.setNewSign(com.huawei.hms.support.api.pay.util.a.a(intent, "newSign"));
        productPayResultInfo.setSignatureAlgorithm(com.huawei.hms.support.api.pay.util.a.a(intent, "signatureAlgorithm"));
        HMSLog.i("HuaweiPayApiImpl", "final product pay result info::" + productPayResultInfo.getReturnCode());
        return productPayResultInfo;
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<ProductDetailResult> getProductDetails(HuaweiApiClient huaweiApiClient, ProductDetailRequest productDetailRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter getProductDetails");
        return new g(huaweiApiClient, PayNaming.productdetail, productDetailRequest);
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PayResultInfo getPayResultInfoFromIntent(Intent intent) {
        if (intent == null) {
            HMSLog.e("HuaweiPayApiImpl", "getPayResultInfoFromIntent intent is null");
            return null;
        }
        PayResultInfo payResultInfo = new PayResultInfo();
        payResultInfo.setReturnCode(com.huawei.hms.support.api.pay.util.a.a(intent, "returnCode", 1));
        payResultInfo.setUserName(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_USER_NAME));
        payResultInfo.setOrderID(com.huawei.hms.support.api.pay.util.a.a(intent, "orderID"));
        payResultInfo.setAmount(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_AMOUNT));
        payResultInfo.setCountry(com.huawei.hms.support.api.pay.util.a.a(intent, "country"));
        payResultInfo.setCurrency(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_CURRENCY));
        payResultInfo.setWithholdID(com.huawei.hms.support.api.pay.util.a.a(intent, "withholdID"));
        payResultInfo.setRequestId(com.huawei.hms.support.api.pay.util.a.a(intent, "requestId"));
        payResultInfo.setErrMsg(com.huawei.hms.support.api.pay.util.a.a(intent, "errMsg"));
        payResultInfo.setTime(com.huawei.hms.support.api.pay.util.a.a(intent, "time"));
        payResultInfo.setSign(com.huawei.hms.support.api.pay.util.a.a(intent, HwPayConstant.KEY_SIGN));
        payResultInfo.setNewSign(com.huawei.hms.support.api.pay.util.a.a(intent, "newSign"));
        payResultInfo.setSignatureAlgorithm(com.huawei.hms.support.api.pay.util.a.a(intent, "signatureAlgorithm"));
        HMSLog.i("HuaweiPayApiImpl", "final pay result info::" + payResultInfo.getReturnCode());
        return payResultInfo;
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<OrderResult> getOrderDetail(HuaweiApiClient huaweiApiClient, OrderRequest orderRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter getOrderDetail");
        return new a(huaweiApiClient, PayNaming.getOrderDetail, orderRequest);
    }

    @Override // com.huawei.hms.support.api.pay.HuaweiPayApi
    public PendingResult<PayResult> addWithholdingPlan(HuaweiApiClient huaweiApiClient, WithholdRequest withholdRequest) {
        HMSLog.i("HuaweiPayApiImpl", "Enter addWithholdingPlan");
        return Util.getHmsVersion(huaweiApiClient.getContext()) < 20504000 ? new e(CommonCode.ErrorCode.HMS_VERSION_CONFIGER_INVALID) : new f(huaweiApiClient, PayNaming.withhold, withholdRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status b(PayResp payResp) {
        if (payResp.intent != null) {
            HMSLog.i("HuaweiPayApiImpl", "getStatus, resp intent");
            return new Status(payResp.retCode, (String) null, payResp.intent);
        }
        if (payResp.getPendingIntent() != null) {
            HMSLog.i("HuaweiPayApiImpl", "getStatus, resp pendingIntent");
            return new Status(payResp.retCode, (String) null, payResp.getPendingIntent());
        }
        HMSLog.i("HuaweiPayApiImpl", "getStatus, resp null");
        return new Status(payResp.retCode, null);
    }

    /* loaded from: classes9.dex */
    static class a extends PendingResultImpl<OrderResult, OrderResp> {
        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public OrderResult onComplete(OrderResp orderResp) {
            if (orderResp == null) {
                HMSLog.e("HuaweiPayApiImpl", "order resp is null or orderResp.returnCode is null");
                return null;
            }
            HMSLog.i("HuaweiPayApiImpl", "getOrderDetail resp :" + orderResp.getReturnCode());
            OrderResult orderResult = new OrderResult(orderResp);
            orderResult.setStatus(new Status(orderResp.getReturnCode(), orderResp.getReturnDesc()));
            return orderResult;
        }

        public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }
    }

    /* loaded from: classes9.dex */
    static class c extends PendingResultImpl<PurchaseInfoResult, PurchaseInfoResp> {
        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PurchaseInfoResult onComplete(PurchaseInfoResp purchaseInfoResp) {
            if (purchaseInfoResp == null) {
                HMSLog.e("HuaweiPayApiImpl", "order resp is null or orderResp.returnCode is null");
                return null;
            }
            HMSLog.i("HuaweiPayApiImpl", "getPurchaseInfo resp :" + purchaseInfoResp.getRtnCode());
            return new PurchaseInfoResult(purchaseInfoResp);
        }

        public c(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }
    }

    static class f extends PendingResultImpl<PayResult, PayResp> {
        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PayResult onComplete(PayResp payResp) {
            if (payResp == null) {
                HMSLog.e("HuaweiPayApiImpl", "pay resp is null");
                return null;
            }
            HMSLog.i("HuaweiPayApiImpl", "pay resp :" + payResp.retCode);
            PayResult payResult = new PayResult();
            payResult.setStatus(HuaweiPayApiImpl.b(payResp));
            return payResult;
        }

        public f(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }
    }

    /* loaded from: classes9.dex */
    static class g extends PendingResultImpl<ProductDetailResult, ProductDetailResp> {
        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ProductDetailResult onComplete(ProductDetailResp productDetailResp) {
            if (productDetailResp == null) {
                HMSLog.e("HuaweiPayApiImpl", "produuctDetailResp is null");
                return null;
            }
            HMSLog.i("HuaweiPayApiImpl", "produuctDetail resp :" + productDetailResp.returnCode);
            ProductDetailResult productDetailResult = new ProductDetailResult();
            productDetailResult.setStatus(new Status(productDetailResp.returnCode, productDetailResp.errMsg));
            productDetailResult.setFailList(productDetailResp.getFailList());
            productDetailResult.setProductList(productDetailResp.getProductList());
            productDetailResult.setRequestId(productDetailResp.getRequestId());
            return productDetailResult;
        }

        public g(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }
    }

    /* loaded from: classes9.dex */
    static class i extends PendingResultImpl<HwWalletInfoResult, HwWalletInoResp> {
        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HwWalletInfoResult onComplete(HwWalletInoResp hwWalletInoResp) {
            if (hwWalletInoResp == null || hwWalletInoResp.getCommonStatus() == null) {
                HMSLog.e("HuaweiPayApiImpl", "HwWalletInfoResult resp is null");
                return null;
            }
            Status commonStatus = hwWalletInoResp.getCommonStatus();
            HMSLog.i("HuaweiPayApiImpl", "HwWalletInoResp resp code :" + commonStatus.getStatusCode());
            HMSLog.i("HuaweiPayApiImpl", "HwWalletInoResp resp msg :" + commonStatus.getStatusMessage());
            HwWalletInfoResult hwWalletInfoResult = new HwWalletInfoResult();
            hwWalletInfoResult.setStatus(new Status(commonStatus.getStatusCode(), commonStatus.getStatusMessage(), commonStatus.getResolution()));
            hwWalletInfoResult.setResult(hwWalletInoResp.getResult());
            return hwWalletInfoResult;
        }

        public i(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }
    }

    /* loaded from: classes9.dex */
    static class b extends ErrorResultImpl<PurchaseInfoResult> {
        public b(int i) {
            super(i);
        }
    }

    /* loaded from: classes9.dex */
    static class d extends ErrorResultImpl<GetWalletUiIntentResult> {
        public d(int i) {
            super(i);
        }
    }

    /* loaded from: classes9.dex */
    static class e extends ErrorResultImpl<PayResult> {
        public e(int i) {
            super(i);
        }
    }

    /* loaded from: classes9.dex */
    static class h extends ErrorResultImpl<HwWalletInfoResult> {
        public h(int i) {
            super(i);
        }
    }

    private static PendingResultImpl<GetWalletUiIntentResult, WalletIntentResp> a(HuaweiApiClient huaweiApiClient, WalletUiIntentReq walletUiIntentReq) {
        return new PendingResultImpl<GetWalletUiIntentResult, WalletIntentResp>(huaweiApiClient, PayNaming.getwalletintent, walletUiIntentReq) { // from class: com.huawei.hms.support.api.pay.HuaweiPayApiImpl.1
            @Override // com.huawei.hms.support.api.PendingResultImpl
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public GetWalletUiIntentResult onComplete(WalletIntentResp walletIntentResp) {
                if (walletIntentResp == null || walletIntentResp.getCommonStatus() == null) {
                    HMSLog.e("HuaweiPayApiImpl", "WalletUiIntentReq resp is null");
                    return null;
                }
                Status commonStatus = walletIntentResp.getCommonStatus();
                GetWalletUiIntentResult getWalletUiIntentResult = new GetWalletUiIntentResult();
                getWalletUiIntentResult.setStatus(new Status(commonStatus.getStatusCode(), commonStatus.getStatusMessage(), commonStatus.getResolution()));
                getWalletUiIntentResult.setPendingIntent(walletIntentResp.getPendingIntent());
                HMSLog.i("HuaweiPayApiImpl", "getWalletUiIntent onComplete");
                return getWalletUiIntentResult;
            }
        };
    }
}
