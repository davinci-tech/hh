package com.huawei.hms.iap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.huawei.hms.iap.task.IapFailureTask;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class d implements IapClient {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f4654a;
    private String b;
    private boolean c;
    private String d;
    private f e;
    private e f;

    @Override // com.huawei.hms.iap.IapClient
    public Task<StartIapActivityResult> startIapActivity(StartIapActivityReq startIapActivityReq) {
        Checker.checkNonNull(startIapActivityReq);
        f a2 = a();
        return a2 != null ? a2.startIapActivity(startIapActivityReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ScanRedeemCodeResult> scanRedeemCode() {
        f a2 = a();
        return a2 != null ? a2.scanRedeemCode() : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public RedeemCodeResultInfo parseRedeemCodeResultInfoFromIntent(Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        f a2 = a();
        return a2 != null ? a2.parseRedeemCodeResultInfoFromIntent(intent) : new RedeemCodeResultInfo();
    }

    @Override // com.huawei.hms.iap.IapClient
    public PurchaseResultInfo parsePurchaseResultInfoFromIntent(Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        if (!com.huawei.hms.iap.util.c.a(intent, "isFullSdk", false)) {
            f a2 = a();
            return a2 != null ? a2.parsePurchaseResultInfoFromIntent(intent) : new PurchaseResultInfo();
        }
        HMSLog.i("IapClientEntryImpl", "parsePurchaseResultInfoFromIntent with Full SDK branch.");
        e c = c();
        return c != null ? c.parsePurchaseResultInfoFromIntent(intent) : new PurchaseResultInfo();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ProductInfoResult> obtainProductInfo(ProductInfoReq productInfoReq) {
        Checker.checkNonNull(productInfoReq);
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "obtainProductInfo context is null.");
            return new IapFailureTask();
        }
        if (!com.huawei.hms.iap.util.a.a(context, productInfoReq.getReservedInfor())) {
            f a2 = a();
            return a2 != null ? a2.obtainProductInfo(productInfoReq) : new IapFailureTask();
        }
        HMSLog.i("IapClientEntryImpl", "obtainProductInfo with Full SDK branch.");
        e c = c();
        return c != null ? c.obtainProductInfo(productInfoReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<OwnedPurchasesResult> obtainOwnedPurchases(OwnedPurchasesReq ownedPurchasesReq) {
        Checker.checkNonNull(ownedPurchasesReq);
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "obtainOwnedPurchases context is null.");
            return new IapFailureTask();
        }
        if (!com.huawei.hms.iap.util.a.a(context, ownedPurchasesReq.getReservedInfor())) {
            f a2 = a();
            return a2 != null ? a2.obtainOwnedPurchases(ownedPurchasesReq) : new IapFailureTask();
        }
        HMSLog.i("IapClientEntryImpl", "obtainOwnedPurchases with Full SDK branch.");
        e c = c();
        return c != null ? c.obtainOwnedPurchases(ownedPurchasesReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<OwnedPurchasesResult> obtainOwnedPurchaseRecord(OwnedPurchasesReq ownedPurchasesReq) {
        Checker.checkNonNull(ownedPurchasesReq);
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "obtainOwnedPurchaseRecord context is null.");
            return new IapFailureTask();
        }
        if (!com.huawei.hms.iap.util.a.a(context, ownedPurchasesReq.getReservedInfor())) {
            f a2 = a();
            return a2 != null ? a2.obtainOwnedPurchaseRecord(ownedPurchasesReq) : new IapFailureTask();
        }
        HMSLog.i("IapClientEntryImpl", "obtainOwnedPurchaseRecord with Full SDK branch.");
        e c = c();
        return c != null ? c.obtainOwnedPurchaseRecord(ownedPurchasesReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsSandboxActivatedResult> isSandboxActivated(IsSandboxActivatedReq isSandboxActivatedReq) {
        Checker.checkNonNull(isSandboxActivatedReq);
        f a2 = a();
        return a2 != null ? a2.isSandboxActivated(isSandboxActivatedReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady(boolean z) {
        f a2 = a();
        return a2 != null ? a2.isEnvReady(z) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady(IsEnvReadyReq isEnvReadyReq) {
        Checker.checkNonNull(isEnvReadyReq);
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "isEnvReady context is null.");
            return new IapFailureTask();
        }
        if (!com.huawei.hms.iap.util.a.a(context, isEnvReadyReq.getReservedInfor())) {
            return new IapFailureTask();
        }
        HMSLog.i("IapClientEntryImpl", "isEnvReady with Full SDK branch.");
        e c = c();
        return c != null ? c.isEnvReady(isEnvReadyReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<IsEnvReadyResult> isEnvReady() {
        return isEnvReady(false);
    }

    @Override // com.huawei.hms.iap.IapClient
    public void enablePendingPurchase() {
        f a2 = a();
        if (a2 != null) {
            a2.enablePendingPurchase();
        }
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<PurchaseIntentResult> createPurchaseIntentWithPrice(PurchaseIntentWithPriceReq purchaseIntentWithPriceReq) {
        f a2 = a();
        return a2 != null ? a2.createPurchaseIntentWithPrice(purchaseIntentWithPriceReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<PurchaseIntentResult> createPurchaseIntent(PurchaseIntentReq purchaseIntentReq) {
        Checker.checkNonNull(purchaseIntentReq);
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "createPurchaseIntent context is null.");
            return new IapFailureTask();
        }
        if (!com.huawei.hms.iap.util.a.a(context, purchaseIntentReq.getReservedInfor())) {
            f a2 = a();
            return a2 != null ? a2.createPurchaseIntent(purchaseIntentReq) : new IapFailureTask();
        }
        HMSLog.i("IapClientEntryImpl", "createPurchaseIntent with Full SDK branch.");
        e c = c();
        return c != null ? c.createPurchaseIntent(purchaseIntentReq) : new IapFailureTask();
    }

    @Override // com.huawei.hms.iap.IapClient
    public Task<ConsumeOwnedPurchaseResult> consumeOwnedPurchase(ConsumeOwnedPurchaseReq consumeOwnedPurchaseReq) {
        Checker.checkNonNull(consumeOwnedPurchaseReq);
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "consumeOwnedPurchase context is null.");
            return new IapFailureTask();
        }
        if (!com.huawei.hms.iap.util.a.a(context, consumeOwnedPurchaseReq.getReservedInfor())) {
            f a2 = a();
            return a2 != null ? a2.consumeOwnedPurchase(consumeOwnedPurchaseReq) : new IapFailureTask();
        }
        HMSLog.i("IapClientEntryImpl", "consumeOwnedPurchase with Full SDK branch.");
        e c = c();
        return c != null ? c.consumeOwnedPurchase(consumeOwnedPurchaseReq) : new IapFailureTask();
    }

    private e c() {
        char c;
        e eVar;
        e eVar2 = this.f;
        if (eVar2 != null) {
            return eVar2;
        }
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "getIapClientFullImpl context is null.");
            return null;
        }
        String b = b();
        b.hashCode();
        int hashCode = b.hashCode();
        if (hashCode == -1528914616) {
            if (b.equals("MCP_MODE")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -463465798) {
            if (hashCode == 545923237 && b.equals("CONSIGNMENT_MODE")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (b.equals("GENERAL_MODE")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                eVar = new e(context);
            } else if (c == 2) {
                eVar = new e(context, this.d);
            }
            this.f = eVar;
        } else {
            this.f = new e(context, TextUtils.isEmpty(this.b) ? this.d : this.b);
        }
        return this.f;
    }

    private String b() {
        return this.c ? !TextUtils.isEmpty(this.d) ? "CONSIGNMENT_MODE" : "GENERAL_MODE" : (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.d)) ? "GENERAL_MODE" : "MCP_MODE";
    }

    private f a() {
        char c;
        f fVar;
        f fVar2 = this.e;
        if (fVar2 != null) {
            return fVar2;
        }
        Context context = this.f4654a.get();
        if (context == null) {
            HMSLog.e("IapClientEntryImpl", "getIapClientImpl context is null.");
            return null;
        }
        String b = b();
        b.hashCode();
        int hashCode = b.hashCode();
        if (hashCode == -1528914616) {
            if (b.equals("MCP_MODE")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -463465798) {
            if (hashCode == 545923237 && b.equals("CONSIGNMENT_MODE")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (b.equals("GENERAL_MODE")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                fVar = context instanceof Activity ? new f((Activity) context) : new f(context);
            } else if (c == 2) {
                fVar = context instanceof Activity ? new f((Activity) context, this.d, this.c) : new f(context, this.d, this.c);
            }
            this.e = fVar;
        } else {
            String str = TextUtils.isEmpty(this.b) ? this.d : this.b;
            this.e = context instanceof Activity ? new f((Activity) context, str) : new f(context, str);
        }
        return this.e;
    }

    d(Context context, String str, boolean z) {
        this.f4654a = new WeakReference<>(context);
        this.d = str;
        this.c = z;
    }

    d(Context context, String str) {
        this.f4654a = new WeakReference<>(context);
        this.b = str;
    }

    d(Context context) {
        this.f4654a = new WeakReference<>(context);
    }

    d(Activity activity, String str, boolean z) {
        this.f4654a = new WeakReference<>(activity);
        this.d = str;
        this.c = z;
    }

    d(Activity activity, String str) {
        this.f4654a = new WeakReference<>(activity);
        this.b = str;
    }

    d(Activity activity) {
        this.f4654a = new WeakReference<>(activity);
    }
}
