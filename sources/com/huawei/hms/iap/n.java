package com.huawei.hms.iap;

import android.text.TextUtils;
import com.huawei.hms.iap.entity.PurchaseIntentReq;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.openalliance.ad.constant.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class n extends b {

    /* renamed from: a, reason: collision with root package name */
    private PurchaseIntentReq f4661a;

    @Override // com.huawei.hms.iap.a
    protected int a() {
        if (a(this.f4661a.getReservedInfor())) {
            return Constants.HMS_VERSION_CODE_61000000;
        }
        if (f() || e()) {
            return 60600000;
        }
        if (g()) {
            return 60300000;
        }
        if (c() || d()) {
            return 50300000;
        }
        return super.a();
    }

    private boolean g() {
        if (this.f4661a.getPriceType() == 2 || !com.huawei.hms.iap.util.b.b(getRequestJson(), "enablePendingPurchases")) {
            return false;
        }
        HMSLog.i("PurchaseIntentTaskApiCall", "Enable pending purchase.");
        return true;
    }

    private boolean f() {
        if (!d()) {
            return false;
        }
        try {
            if (new JSONObject(this.f4661a.getReservedInfor()).has("offerCode")) {
                HMSLog.i("PurchaseIntentTaskApiCall", "isSubscriptionBenefit");
                return true;
            }
        } catch (JSONException unused) {
            HMSLog.w("PurchaseIntentTaskApiCall", "JSONException");
        }
        return false;
    }

    private boolean e() {
        if (this.f4661a.getPriceType() != 2 && d()) {
            try {
                JSONObject optJSONObject = new JSONObject(this.f4661a.getReservedInfor()).optJSONObject("orderPurchaseExtensionInformation");
                if (optJSONObject == null || new JSONObject(optJSONObject.optString("purchaseExtension")).optJSONObject("memberPoints") == null) {
                    return false;
                }
                HMSLog.i("PurchaseIntentTaskApiCall", "isPassMemberPoints");
                return true;
            } catch (JSONException unused) {
                HMSLog.w("PurchaseIntentTaskApiCall", "JSONException");
            }
        }
        return false;
    }

    private boolean d() {
        PurchaseIntentReq purchaseIntentReq = this.f4661a;
        if (purchaseIntentReq == null || TextUtils.isEmpty(purchaseIntentReq.getReservedInfor())) {
            return false;
        }
        HMSLog.i("PurchaseIntentTaskApiCall", "reservedInfor is not empty");
        return true;
    }

    private boolean c() {
        PurchaseIntentReq purchaseIntentReq = this.f4661a;
        if (purchaseIntentReq == null || TextUtils.isEmpty(purchaseIntentReq.getSignatureAlgorithm())) {
            return false;
        }
        HMSLog.i("PurchaseIntentTaskApiCall", "Use the SHA256WithRSA/PSS algorithm.");
        return true;
    }

    private void b() {
        int i;
        if (a(this.f4661a.getReservedInfor())) {
            i = 11;
        } else {
            if (f()) {
                a(8);
                return;
            }
            if (e()) {
                a(8);
                return;
            }
            if (!g()) {
                if (c()) {
                    a(4);
                    return;
                } else {
                    if (d()) {
                        a(4);
                        return;
                    }
                    return;
                }
            }
            i = 7;
        }
        a(i);
    }

    public n(String str, PurchaseIntentReq purchaseIntentReq, String str2, String str3) {
        super(str, JsonUtil.createJsonString(purchaseIntentReq), str2, str3);
        this.f4661a = purchaseIntentReq;
        b();
    }
}
