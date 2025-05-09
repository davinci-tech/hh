package com.huawei.hms.iap;

import android.text.TextUtils;
import com.huawei.hms.iap.entity.PurchaseIntentWithPriceReq;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes9.dex */
public class o extends b {

    /* renamed from: a, reason: collision with root package name */
    private PurchaseIntentWithPriceReq f4662a;

    @Override // com.huawei.hms.iap.a
    protected int a() {
        return a(this.f4662a.getReservedInfor()) ? Constants.HMS_VERSION_CODE_61000000 : (c() || d()) ? 50300000 : 40002000;
    }

    private boolean d() {
        PurchaseIntentWithPriceReq purchaseIntentWithPriceReq = this.f4662a;
        if (purchaseIntentWithPriceReq == null || TextUtils.isEmpty(purchaseIntentWithPriceReq.getReservedInfor())) {
            return false;
        }
        HMSLog.i("PurchaseIntentWithPriceTaskApiCall", "reservedInfor is not empty");
        return true;
    }

    private boolean c() {
        PurchaseIntentWithPriceReq purchaseIntentWithPriceReq = this.f4662a;
        if (purchaseIntentWithPriceReq == null || TextUtils.isEmpty(purchaseIntentWithPriceReq.getSignatureAlgorithm())) {
            return false;
        }
        HMSLog.i("PurchaseIntentWithPriceTaskApiCall", "Use the SHA256WithRSA/PSS algorithm.");
        return true;
    }

    private void b() {
        if (a(this.f4662a.getReservedInfor())) {
            a(11);
        } else if (c()) {
            a(4);
        } else if (d()) {
            a(4);
        }
    }

    public o(String str, PurchaseIntentWithPriceReq purchaseIntentWithPriceReq, String str2, String str3) {
        super(str, JsonUtil.createJsonString(purchaseIntentWithPriceReq), str2, str3);
        this.f4662a = purchaseIntentWithPriceReq;
        b();
    }
}
