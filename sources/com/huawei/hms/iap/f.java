package com.huawei.hms.iap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.iap.entity.ConsumeOwnedPurchaseReq;
import com.huawei.hms.iap.entity.ConsumeOwnedPurchaseResult;
import com.huawei.hms.iap.entity.IsEnvReadyReq;
import com.huawei.hms.iap.entity.IsEnvReadyResult;
import com.huawei.hms.iap.entity.IsSandboxActivatedReq;
import com.huawei.hms.iap.entity.IsSandboxActivatedResult;
import com.huawei.hms.iap.entity.OrderStatusCode;
import com.huawei.hms.iap.entity.OwnedPurchasesReq;
import com.huawei.hms.iap.entity.OwnedPurchasesResult;
import com.huawei.hms.iap.entity.ProductInfoReq;
import com.huawei.hms.iap.entity.ProductInfoResult;
import com.huawei.hms.iap.entity.PurchaseIntentReq;
import com.huawei.hms.iap.entity.PurchaseIntentResult;
import com.huawei.hms.iap.entity.PurchaseIntentWithPriceReq;
import com.huawei.hms.iap.entity.PurchaseResultInfo;
import com.huawei.hms.iap.entity.RedeemCodeResultInfo;
import com.huawei.hms.iap.entity.ScanRedeemCodeResult;
import com.huawei.hms.iap.entity.StartIapActivityReq;
import com.huawei.hms.iap.entity.StartIapActivityResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f extends HuaweiApi<i> implements IapClient {

    /* renamed from: a, reason: collision with root package name */
    private static final Api<i> f4659a = new Api<>(HuaweiApiAvailability.HMS_API_NAME_IAP);
    private static i b = new i();
    private static boolean c;
    private String d;

    @Override // com.huawei.hms.iap.IapClient
    public Task<StartIapActivityResult> startIapActivity(StartIapActivityReq startIapActivityReq) {
        Checker.checkNonNull(startIapActivityReq);
        return doWrite(new r("iap.startActivity", startIapActivityReq, this.d, HiAnalyticsClient.reportEntry(getContext(), "iap.startActivity", 61606300)));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ScanRedeemCodeResult> scanRedeemCode() {
        return doWrite(new q("iap.scanRedeemCode", HiAnalyticsClient.reportEntry(getContext(), "iap.scanRedeemCode", 61606300)));
    }

    @Override // com.huawei.hms.iap.IapClient
    public RedeemCodeResultInfo parseRedeemCodeResultInfoFromIntent(Intent intent) {
        HMSLog.i("IapClientImpl", "parseRedeemCodeResultInfoFromIntent");
        if (intent == null) {
            intent = new Intent();
        }
        RedeemCodeResultInfo redeemCodeResultInfo = new RedeemCodeResultInfo();
        redeemCodeResultInfo.setRedeemCode(com.huawei.hms.iap.util.c.a(intent, "redeemCode"));
        redeemCodeResultInfo.setReturnCode(com.huawei.hms.iap.util.c.a(intent, "returnCode", 1));
        HMSLog.i("IapClientImpl", "parseRedeemCodeResultInfoFromIntent:" + redeemCodeResultInfo.getReturnCode());
        return redeemCodeResultInfo;
    }

    @Override // com.huawei.hms.iap.IapClient
    public PurchaseResultInfo parsePurchaseResultInfoFromIntent(Intent intent) {
        HMSLog.i("IapClientImpl", "parsePurchaseResultInfoFromIntent");
        if (intent == null) {
            intent = new Intent();
        }
        PurchaseResultInfo purchaseResultInfo = new PurchaseResultInfo();
        purchaseResultInfo.setReturnCode(com.huawei.hms.iap.util.c.a(intent, "returnCode", 1));
        purchaseResultInfo.setErrMsg(com.huawei.hms.iap.util.c.a(intent, "errMsg"));
        purchaseResultInfo.setInAppPurchaseData(com.huawei.hms.iap.util.c.a(intent, "inAppPurchaseData"));
        purchaseResultInfo.setInAppDataSignature(com.huawei.hms.iap.util.c.a(intent, "Signature"));
        purchaseResultInfo.setSignatureAlgorithm(com.huawei.hms.iap.util.c.a(intent, "signatureAlgorithm"));
        HMSLog.i("IapClientImpl", "getBuyResultInfoFromIntent:" + purchaseResultInfo.getReturnCode());
        return purchaseResultInfo;
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ProductInfoResult> obtainProductInfo(ProductInfoReq productInfoReq) {
        HMSLog.i("IapClientImpl", "obtainProductInfo");
        Checker.checkNonNull(productInfoReq);
        return doWrite(new m("iap.getSkuDetails", JsonUtil.createJsonString(productInfoReq), this.d, HiAnalyticsClient.reportEntry(getContext(), "iap.getSkuDetails", 61606300)));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<OwnedPurchasesResult> obtainOwnedPurchases(OwnedPurchasesReq ownedPurchasesReq) {
        HMSLog.i("IapClientImpl", "obtainOwnedPurchases");
        Checker.checkNonNull(ownedPurchasesReq);
        return doWrite(new l("iap.getPurchase", ownedPurchasesReq, this.d, HiAnalyticsClient.reportEntry(getContext(), "iap.getPurchase", 61606300)));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<OwnedPurchasesResult> obtainOwnedPurchaseRecord(OwnedPurchasesReq ownedPurchasesReq) {
        HMSLog.i("IapClientImpl", "obtainOwnedPurchaseRecord");
        Checker.checkNonNull(ownedPurchasesReq);
        return doWrite(new l("iap.getPurchaseHistory", ownedPurchasesReq, this.d, HiAnalyticsClient.reportEntry(getContext(), "iap.getPurchaseHistory", 61606300)));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsSandboxActivatedResult> isSandboxActivated(IsSandboxActivatedReq isSandboxActivatedReq) {
        Checker.checkNonNull(isSandboxActivatedReq);
        return doWrite(new k("iap.isSandboxActivated", JsonUtil.createJsonString(isSandboxActivatedReq), this.d, HiAnalyticsClient.reportEntry(getContext(), "iap.isSandboxActivated", 61606300)));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady(boolean z) {
        return doWrite(new j("iap.isBillingSupported", HiAnalyticsClient.reportEntry(getContext(), "iap.isBillingSupported", 61606300), z));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady(IsEnvReadyReq isEnvReadyReq) {
        throw new UnsupportedOperationException("isEnvReady");
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady() {
        return isEnvReady(false);
    }

    @Override // com.huawei.hms.iap.IapClient
    public void enablePendingPurchase() {
        HMSLog.i("IapClientImpl", "enablePendingPurchase");
        c = true;
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<PurchaseIntentResult> createPurchaseIntentWithPrice(PurchaseIntentWithPriceReq purchaseIntentWithPriceReq) {
        HMSLog.i("IapClientImpl", "createPurchaseIntentWithPrice");
        Checker.checkNonNull(purchaseIntentWithPriceReq);
        if (!a(purchaseIntentWithPriceReq.getReservedInfor())) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new IapApiException(new Status(OrderStatusCode.ORDER_STATE_PARAM_ERROR, "reservedInfor must be a JSON string")));
            return taskCompletionSource.getTask();
        }
        HiAnalyticsUtil.getInstance().onEvent(getContext(), HiAnalyticsConstant.KeyAndValue.START_BUYWITHPRICE, b(JsonUtil.createJsonString(purchaseIntentWithPriceReq)));
        String reportEntry = HiAnalyticsClient.reportEntry(getContext(), "iap.buyWithPrice", 61606300);
        this.d = com.huawei.hms.iap.util.b.a(this.d, "enablePendingPurchases", Boolean.valueOf(c));
        return doWrite(new o("iap.buyWithPrice", purchaseIntentWithPriceReq, this.d, reportEntry));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<PurchaseIntentResult> createPurchaseIntent(PurchaseIntentReq purchaseIntentReq) {
        String reportEntry;
        HiAnalyticsUtil hiAnalyticsUtil;
        Context context;
        String b2;
        String str;
        HMSLog.i("IapClientImpl", "createPurchaseIntent");
        Checker.checkNonNull(purchaseIntentReq);
        if (purchaseIntentReq.getPriceType() == 2) {
            reportEntry = HiAnalyticsClient.reportEntry(getContext(), "iap.buy", 61606300);
            hiAnalyticsUtil = HiAnalyticsUtil.getInstance();
            context = getContext();
            b2 = b(JsonUtil.createJsonString(purchaseIntentReq));
            str = HiAnalyticsConstant.KeyAndValue.START_SUB;
        } else {
            reportEntry = HiAnalyticsClient.reportEntry(getContext(), "iap.buy", 61606300);
            hiAnalyticsUtil = HiAnalyticsUtil.getInstance();
            context = getContext();
            b2 = b(JsonUtil.createJsonString(purchaseIntentReq));
            str = HiAnalyticsConstant.KeyAndValue.START_BUY;
        }
        hiAnalyticsUtil.onEvent(context, str, b2);
        String a2 = com.huawei.hms.iap.util.b.a(this.d, "enablePendingPurchases", Boolean.valueOf(c));
        this.d = a2;
        return doWrite(new n("iap.buy", purchaseIntentReq, a2, reportEntry));
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ConsumeOwnedPurchaseResult> consumeOwnedPurchase(ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq) {
        HMSLog.i("IapClientImpl", "consumeOwnedPurchase");
        Checker.checkNonNull(consumeOwnedPurchaseReq);
        return doWrite(new c("iap.consumePurchase", consumeOwnedPurchaseReq, this.d, HiAnalyticsClient.reportEntry(getContext(), "iap.consumePurchase", 61606300)));
    }

    private String b(String str) {
        try {
            return new JSONObject(str).optString("transactionId", "");
        } catch (JSONException e) {
            HMSLog.e("IapClientImpl", "getTransactionId  exception :" + e.getMessage());
            return "";
        }
    }

    private boolean a(String str) {
        if (str == null) {
            return true;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    private void a(boolean z, String str) {
        String a2 = com.huawei.hms.iap.util.b.a(this.d, "productAppId", str);
        this.d = a2;
        this.d = com.huawei.hms.iap.util.b.a(a2, "isConsignment", Boolean.valueOf(z));
        setApiLevel(10);
    }

    f(Context context, String str, boolean z) {
        super(context, f4659a, b, new h(), 61606300);
        a(z, str);
    }

    f(Context context, String str) {
        super(context, f4659a, b, new p(), 61606300, str);
        setApiLevel(3);
    }

    f(Context context) {
        super(context, f4659a, b, new h(), 61606300);
    }

    f(Activity activity, String str, boolean z) {
        super(activity, f4659a, b, (AbstractClientBuilder) new h(), 61606300);
        a(z, str);
    }

    f(Activity activity, String str) {
        super(activity, f4659a, b, (AbstractClientBuilder) new p(), 61606300, str);
        setApiLevel(3);
    }

    f(Activity activity) {
        super(activity, f4659a, b, (AbstractClientBuilder) new h(), 61606300);
    }
}
