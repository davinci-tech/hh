package com.huawei.hms.iap;

import android.content.Intent;
import com.huawei.hmf.tasks.Task;
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

/* loaded from: classes4.dex */
public interface IapClient {

    /* loaded from: classes9.dex */
    public interface PriceType {
        public static final int IN_APP_CONSUMABLE = 0;
        public static final int IN_APP_NONCONSUMABLE = 1;
        public static final int IN_APP_SUBSCRIPTION = 2;
    }

    Task<ConsumeOwnedPurchaseResult> consumeOwnedPurchase(ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq);

    Task<PurchaseIntentResult> createPurchaseIntent(PurchaseIntentReq purchaseIntentReq);

    @Deprecated
    Task<PurchaseIntentResult> createPurchaseIntentWithPrice(PurchaseIntentWithPriceReq purchaseIntentWithPriceReq);

    void enablePendingPurchase();

    Task<IsEnvReadyResult> isEnvReady();

    Task<IsEnvReadyResult> isEnvReady(IsEnvReadyReq isEnvReadyReq);

    Task<IsEnvReadyResult> isEnvReady(boolean z);

    Task<IsSandboxActivatedResult> isSandboxActivated(IsSandboxActivatedReq isSandboxActivatedReq);

    Task<OwnedPurchasesResult> obtainOwnedPurchaseRecord(OwnedPurchasesReq ownedPurchasesReq);

    Task<OwnedPurchasesResult> obtainOwnedPurchases(OwnedPurchasesReq ownedPurchasesReq);

    Task<ProductInfoResult> obtainProductInfo(ProductInfoReq productInfoReq);

    PurchaseResultInfo parsePurchaseResultInfoFromIntent(Intent intent);

    RedeemCodeResultInfo parseRedeemCodeResultInfoFromIntent(Intent intent);

    Task<ScanRedeemCodeResult> scanRedeemCode();

    Task<StartIapActivityResult> startIapActivity(StartIapActivityReq startIapActivityReq);
}
