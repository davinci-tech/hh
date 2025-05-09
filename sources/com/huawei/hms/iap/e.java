package com.huawei.hms.iap;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.entity.BaseReq;
import com.huawei.hms.iap.entity.ConsumeOwnedPurchaseReq;
import com.huawei.hms.iap.entity.ConsumeOwnedPurchaseResult;
import com.huawei.hms.iap.entity.IsEnvReadyReq;
import com.huawei.hms.iap.entity.IsEnvReadyResult;
import com.huawei.hms.iap.entity.IsSandboxActivatedReq;
import com.huawei.hms.iap.entity.IsSandboxActivatedResult;
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
import com.huawei.hms.iap.task.ConsumeOwnedPurchaseTask;
import com.huawei.hms.iap.task.CreatePurchaseTask;
import com.huawei.hms.iap.task.IsEnvReadyTask;
import com.huawei.hms.iap.task.ObtainOwnedPurchaseRecordTask;
import com.huawei.hms.iap.task.ObtainOwnedPurchasesTask;
import com.huawei.hms.iap.task.ProductInfoTask;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class e implements IapClient {

    /* renamed from: a, reason: collision with root package name */
    private Context f4655a;
    private final String b;

    @Override // com.huawei.hms.iap.IapClient
    public Task<StartIapActivityResult> startIapActivity(StartIapActivityReq startIapActivityReq) {
        throw new UnsupportedOperationException("startIapActivity");
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ScanRedeemCodeResult> scanRedeemCode() {
        throw new UnsupportedOperationException("scanRedeemCode");
    }

    @Override // com.huawei.hms.iap.IapClient
    public RedeemCodeResultInfo parseRedeemCodeResultInfoFromIntent(Intent intent) {
        throw new UnsupportedOperationException("parseRedeemCodeResultInfoFromIntent");
    }

    @Override // com.huawei.hms.iap.IapClient
    public PurchaseResultInfo parsePurchaseResultInfoFromIntent(Intent intent) {
        HMSLog.i("IapClientFullImpl", "parsePurchaseResultInfoFromIntent");
        if (intent == null) {
            intent = new Intent();
        }
        PurchaseResultInfo purchaseResultInfo = new PurchaseResultInfo();
        purchaseResultInfo.setReturnCode(com.huawei.hms.iap.util.c.a(intent, "returnCode", 1));
        purchaseResultInfo.setErrMsg(com.huawei.hms.iap.util.c.a(intent, "errMsg"));
        purchaseResultInfo.setInAppPurchaseData(com.huawei.hms.iap.util.c.a(intent, "inAppPurchaseData"));
        purchaseResultInfo.setInAppDataSignature(com.huawei.hms.iap.util.c.a(intent, "inAppDataSignature"));
        purchaseResultInfo.setSignatureAlgorithm(com.huawei.hms.iap.util.c.a(intent, "signatureAlgorithm"));
        HMSLog.i("IapClientFullImpl", "parsePurchaseResultInfoFromIntent:" + purchaseResultInfo.getReturnCode());
        return purchaseResultInfo;
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ProductInfoResult> obtainProductInfo(ProductInfoReq productInfoReq) {
        Checker.checkNonNull(productInfoReq);
        a(productInfoReq);
        return new ProductInfoTask(this.f4655a, productInfoReq);
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<OwnedPurchasesResult> obtainOwnedPurchases(OwnedPurchasesReq ownedPurchasesReq) {
        Checker.checkNonNull(ownedPurchasesReq);
        a(ownedPurchasesReq);
        return new ObtainOwnedPurchasesTask(this.f4655a, ownedPurchasesReq);
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<OwnedPurchasesResult> obtainOwnedPurchaseRecord(OwnedPurchasesReq ownedPurchasesReq) {
        Checker.checkNonNull(ownedPurchasesReq);
        a(ownedPurchasesReq);
        return new ObtainOwnedPurchaseRecordTask(this.f4655a, ownedPurchasesReq);
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsSandboxActivatedResult> isSandboxActivated(IsSandboxActivatedReq isSandboxActivatedReq) {
        throw new UnsupportedOperationException("isSandboxActivated");
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady(boolean z) {
        throw new UnsupportedOperationException("isEnvReady");
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady(IsEnvReadyReq isEnvReadyReq) {
        Checker.checkNonNull(isEnvReadyReq);
        a(isEnvReadyReq);
        return new IsEnvReadyTask(this.f4655a, isEnvReadyReq);
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady() {
        throw new UnsupportedOperationException("isEnvReady");
    }

    @Override // com.huawei.hms.iap.IapClient
    public void enablePendingPurchase() {
        throw new UnsupportedOperationException("enablePendingPurchase");
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<PurchaseIntentResult> createPurchaseIntentWithPrice(PurchaseIntentWithPriceReq purchaseIntentWithPriceReq) {
        throw new UnsupportedOperationException("createPurchaseIntentWithPrice");
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<PurchaseIntentResult> createPurchaseIntent(PurchaseIntentReq purchaseIntentReq) {
        Checker.checkNonNull(purchaseIntentReq);
        a(purchaseIntentReq);
        return new CreatePurchaseTask(this.f4655a, purchaseIntentReq);
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ConsumeOwnedPurchaseResult> consumeOwnedPurchase(ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq) {
        Checker.checkNonNull(consumeOwnedPurchaseReq);
        a(consumeOwnedPurchaseReq);
        return new ConsumeOwnedPurchaseTask(this.f4655a, consumeOwnedPurchaseReq);
    }

    private void a(BaseReq baseReq) {
        if (TextUtils.isEmpty(this.b)) {
            HMSLog.e("IapClientFullImpl", "addSceneAppId, appId is empty!");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(baseReq.getReservedInfor());
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("accountInfo"));
            jSONObject2.put("subAppID", this.b);
            jSONObject.put("accountInfo", jSONObject2.toString());
            baseReq.setReservedInfor(jSONObject.toString());
        } catch (JSONException unused) {
            HMSLog.e("IapClientFullImpl", "addSceneAppId, JSONException");
        }
    }

    public e(Context context, String str) {
        this.f4655a = context;
        this.b = str;
    }

    e(Context context) {
        this(context, null);
    }
}
