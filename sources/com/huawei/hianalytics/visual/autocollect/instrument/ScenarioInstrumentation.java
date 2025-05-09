package com.huawei.hianalytics.visual.autocollect.instrument;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.utils.JsonUtils;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.beans.HAInAppPurchaseData;
import com.huawei.hianalytics.visual.m0;
import com.huawei.hms.iap.entity.PurchaseIntentReq;
import com.huawei.hms.iap.entity.PurchaseResultInfo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ScenarioInstrumentation {

    /* renamed from: a, reason: collision with root package name */
    public static final Set<Integer> f3907a = new HashSet(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 17));

    public static final class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ScenarioInstrumentation.reportEventSignOut();
        }
    }

    public static final class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3908a;

        public b(int i) {
            this.f3908a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScenarioInstrumentation.reportEventSignIn(this.f3908a);
        }
    }

    public static void iapCreatePurchaseIntent(PurchaseIntentReq purchaseIntentReq) {
        if (com.huawei.hianalytics.visual.b.a().b()) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().a(EventType.IAP_PURCHASE)) {
            return;
        }
        if (purchaseIntentReq == null) {
            HiLog.w("HASI", "fail to acquire purchase product id");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(purchaseIntentReq.getProductId())) {
                jSONObject.put("$product_id", purchaseIntentReq.getProductId());
            }
            jSONObject.put("$price_type", purchaseIntentReq.getPriceType());
        } catch (JSONException unused) {
            HiLog.w("HASI", "fail to acquire purchase product id");
        }
        com.huawei.hianalytics.visual.b.a().a("$CreatPurchase", jSONObject);
    }

    public static void iapParsePurchaseResultInfo(PurchaseResultInfo purchaseResultInfo) {
        if (com.huawei.hianalytics.visual.b.a().b()) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().a(EventType.IAP_PURCHASE)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (purchaseResultInfo == null) {
            HiLog.w("HASI", "fail to acquire purchase data");
            return;
        }
        try {
        } catch (JSONException unused) {
            HiLog.w("HASI", "fail to acquire purchase data");
        }
        if (purchaseResultInfo.getReturnCode() != 0) {
            jSONObject.put("$purchase_return_code", purchaseResultInfo.getReturnCode());
            jSONObject.put("$purchase_return_err_msg", purchaseResultInfo.getErrMsg());
            com.huawei.hianalytics.visual.b.a().a("$PurchaseResult", jSONObject);
            return;
        }
        if (TextUtils.isEmpty(purchaseResultInfo.getInAppPurchaseData())) {
            HiLog.d("HASI", "fail to acquire purchase data");
            return;
        }
        HAInAppPurchaseData hAInAppPurchaseData = (HAInAppPurchaseData) JsonUtils.toObjectNoException(purchaseResultInfo.getInAppPurchaseData(), HAInAppPurchaseData.class, new Class[0]);
        if (hAInAppPurchaseData == null) {
            HiLog.d("HASI", "fail to acquire purchase data");
            return;
        }
        jSONObject.put("$order_id", hAInAppPurchaseData.orderId);
        jSONObject.put("$product_kind", hAInAppPurchaseData.kind);
        jSONObject.put("$product_id", hAInAppPurchaseData.productId);
        jSONObject.put("$product_name", hAInAppPurchaseData.productName);
        jSONObject.put("$purchase_time", hAInAppPurchaseData.purchaseTime);
        jSONObject.put("$purchase_state", hAInAppPurchaseData.purchaseState);
        jSONObject.put("$product_currency", hAInAppPurchaseData.currency);
        jSONObject.put("$pay_price", hAInAppPurchaseData.price);
        jSONObject.put("$pay_type", hAInAppPurchaseData.payType);
        jSONObject.put("$pay_order_id", hAInAppPurchaseData.payOrderId);
        com.huawei.hianalytics.visual.b.a().a("$PurchaseResult", jSONObject);
    }

    public static void onEventSignIn(int i) {
        if (com.huawei.hianalytics.visual.b.a().b()) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().a(EventType.SIGNIN)) {
            return;
        }
        m0.a().execute(new b(i));
    }

    public static void onEventSignOut() {
        if (com.huawei.hianalytics.visual.b.a().b()) {
            return;
        }
        if (com.huawei.hianalytics.visual.b.a().a(EventType.SIGNOUT)) {
            return;
        }
        m0.a().execute(new a());
    }

    public static void reportEventSignIn(int i) {
        com.huawei.hianalytics.visual.b.a().a("$SignIn", reportSignInEvent(i));
    }

    public static void reportEventSignOut() {
        com.huawei.hianalytics.visual.b.a().a("$SignOut", new JSONObject());
    }

    public static JSONObject reportSignInEvent(int i) {
        JSONObject jSONObject = new JSONObject();
        if (!f3907a.contains(Integer.valueOf(i))) {
            return jSONObject;
        }
        try {
            jSONObject.put("$sign_in_provider", i);
        } catch (Exception unused) {
            HiLog.w("HASI", "fail to acquire signIn provider");
        }
        return jSONObject;
    }
}
