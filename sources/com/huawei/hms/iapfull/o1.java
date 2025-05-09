package com.huawei.hms.iapfull;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseRequest;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyRequest;
import com.huawei.hms.iapfull.bean.WebOrderRequest;
import com.huawei.hms.iapfull.bean.WebOwnedPurchasesRequest;
import com.huawei.hms.iapfull.bean.WebProductDetailRequest;
import com.huawei.hms.iapfull.bean.WebProductInfoRequest;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoRequest;
import com.huawei.hms.iapfull.network.model.WebPayRequest;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.util.SafeBase64;
import com.huawei.secure.android.common.util.SafeString;
import com.tencent.open.utils.HttpUtils;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class o1 {
    private HashMap<String, String> b(String str) {
        String str2 = "";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("Connection", "Keep-Alive");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceType", a1.b());
            jSONObject.put("deviceIdType", 9);
            jSONObject.put("deviceId", SafeString.substring(UUID.randomUUID().toString().replace(Constants.LINK, ""), 0, 16));
            jSONObject.put("deviceBrand", Build.BRAND);
            jSONObject.put("manufacturer", Build.MANUFACTURER);
            str2 = jSONObject.toString();
        } catch (JSONException unused) {
            y0.a("WebPayRepository", "getDeviceInfo JSONException");
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("HW-IAP-DEVICE", str2);
        }
        if (TextUtils.isEmpty(str)) {
            y0.b("WebPayRepository", "getWebHeaders: reservedInfor is null!");
            return hashMap;
        }
        try {
            String str3 = "Basic " + SafeBase64.encodeToString(("AT:" + new JSONObject(new JSONObject(str).optString("accountInfo")).optString("accessToken")).getBytes(Charset.defaultCharset()), 2);
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put("Authorization", str3);
            }
        } catch (JSONException unused2) {
            y0.a("WebPayRepository", "getWebHeaders: JSONException");
        }
        return hashMap;
    }

    public void b(Context context, WebProductPayRequest webProductPayRequest, n1 n1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebProductPayVer4: network unavailable");
            n1Var.a(60005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
            return;
        }
        w wVar = new w(n1Var);
        webProductPayRequest.setApplicationID(a(webProductPayRequest.getReservedInfor()));
        webProductPayRequest.setClientID(a(webProductPayRequest.getReservedInfor(), "clientID"));
        y0.b("WebPayRepository", "startWebProductPayVer4");
        com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).createPurchase(b(webProductPayRequest.getReservedInfor()), webProductPayRequest).enqueue(new com.huawei.hms.iapfull.network.g(wVar));
    }

    public void b(Context context, WebOwnedPurchasesRequest webOwnedPurchasesRequest, i1 i1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebObtainOwnedPurchases: network unavailable");
            i1Var.a(60005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
            return;
        }
        s sVar = new s(i1Var);
        webOwnedPurchasesRequest.setApplicationID(a(webOwnedPurchasesRequest.getReservedInfor()));
        webOwnedPurchasesRequest.setClientID(a(webOwnedPurchasesRequest.getReservedInfor(), "clientID"));
        y0.b("WebPayRepository", "startWebObtainOwnedPurchases");
        com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).obtainOwnedPurchases(b(webOwnedPurchasesRequest.getReservedInfor()), webOwnedPurchasesRequest).enqueue(new com.huawei.hms.iapfull.network.g(sVar));
    }

    public void a(Context context, WebPayRequest webPayRequest, n1 n1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebPay: network unavailable");
            n1Var.a(30005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            y yVar = new y(n1Var);
            y0.b("WebPayRepository", "startWebPay");
            com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).webPay(b(webPayRequest.getReservedInfor()), webPayRequest).enqueue(new com.huawei.hms.iapfull.network.g(yVar));
        }
    }

    public void a(Context context, WebPurchaseInfoRequest webPurchaseInfoRequest, m1 m1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebGetPurchaseInfo: network unavailable");
            m1Var.a(30005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            e0 e0Var = new e0(m1Var);
            y0.b("WebPayRepository", "startWebGetPurchaseInfo");
            com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).getPurchaseInfo(b(webPurchaseInfoRequest.getReservedInfor()), webPurchaseInfoRequest).enqueue(new com.huawei.hms.iapfull.network.g(e0Var));
        }
    }

    public void a(Context context, WebProductPayRequest webProductPayRequest, n1 n1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebProductPay: network unavailable");
            n1Var.a(30005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            y yVar = new y(n1Var);
            y0.b("WebPayRepository", "startWebProductPay");
            com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).webProductPay(b(webProductPayRequest.getReservedInfor()), webProductPayRequest).enqueue(new com.huawei.hms.iapfull.network.g(yVar));
        }
    }

    public void a(Context context, WebProductInfoRequest webProductInfoRequest, l1 l1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebObtainProductInfo: network unavailable");
            l1Var.a(60005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
            return;
        }
        c0 c0Var = new c0(l1Var);
        webProductInfoRequest.setApplicationID(a(webProductInfoRequest.getReservedInfor()));
        webProductInfoRequest.setClientID(a(webProductInfoRequest.getReservedInfor(), "clientID"));
        y0.b("WebPayRepository", "startWebObtainProductInfo");
        com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).obtainProductInfo(b(webProductInfoRequest.getReservedInfor()), webProductInfoRequest).enqueue(new com.huawei.hms.iapfull.network.g(c0Var));
    }

    public void a(Context context, WebProductDetailRequest webProductDetailRequest, k1 k1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebGetProductDetail: network unavailable");
            k1Var.a(30005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            a0 a0Var = new a0(k1Var);
            y0.b("WebPayRepository", "startWebGetProductDetail");
            com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).getProductDetail(b(webProductDetailRequest.getReservedInfor()), webProductDetailRequest).enqueue(new com.huawei.hms.iapfull.network.g(a0Var));
        }
    }

    public void a(Context context, WebOwnedPurchasesRequest webOwnedPurchasesRequest, i1 i1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "obtainOwnedPurchaseRecord: network unavailable");
            i1Var.a(60005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
            return;
        }
        s sVar = new s(i1Var);
        webOwnedPurchasesRequest.setApplicationID(a(webOwnedPurchasesRequest.getReservedInfor()));
        webOwnedPurchasesRequest.setClientID(a(webOwnedPurchasesRequest.getReservedInfor(), "clientID"));
        y0.b("WebPayRepository", "obtainOwnedPurchaseRecord");
        com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).obtainOwnedPurchaseRecord(b(webOwnedPurchasesRequest.getReservedInfor()), webOwnedPurchasesRequest).enqueue(new com.huawei.hms.iapfull.network.g(sVar));
    }

    public void a(Context context, WebOrderRequest webOrderRequest, j1 j1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebGetOrderDetail: network unavailable");
            j1Var.a(30005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            u uVar = new u(j1Var);
            y0.b("WebPayRepository", "startWebGetOrderDetail");
            com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).getOrderDetail(b(webOrderRequest.getReservedInfor()), webOrderRequest).enqueue(new com.huawei.hms.iapfull.network.g(uVar));
        }
    }

    public void a(Context context, WebIsEnvReadyRequest webIsEnvReadyRequest, h1 h1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "isEnvReady: network unavailable");
            h1Var.a(60005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
            return;
        }
        p pVar = new p(h1Var);
        webIsEnvReadyRequest.setCountry(a(webIsEnvReadyRequest.getReservedInfor(), "countryCode"));
        webIsEnvReadyRequest.setApplicationID(a(webIsEnvReadyRequest.getReservedInfor()));
        webIsEnvReadyRequest.setClientID(a(webIsEnvReadyRequest.getReservedInfor(), "clientID"));
        y0.b("WebPayRepository", "isEnvReady");
        com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).isEnvReady(b(webIsEnvReadyRequest.getReservedInfor()), webIsEnvReadyRequest).enqueue(new com.huawei.hms.iapfull.network.g(pVar));
    }

    public void a(Context context, WebConsumeOwnedPurchaseRequest webConsumeOwnedPurchaseRequest, g1 g1Var) {
        if (a(context)) {
            y0.a("WebPayRepository", "startWebConsumeOwnedPurchase: network unavailable");
            g1Var.a(60005, HttpUtils.NetworkUnavailableException.ERROR_INFO);
            return;
        }
        n nVar = new n(g1Var);
        webConsumeOwnedPurchaseRequest.setApplicationID(a(webConsumeOwnedPurchaseRequest.getReservedInfor()));
        webConsumeOwnedPurchaseRequest.setClientID(a(webConsumeOwnedPurchaseRequest.getReservedInfor(), "clientID"));
        y0.b("WebPayRepository", "startWebConsumeOwnedPurchase");
        com.huawei.hms.iapfull.network.c.a(context.getApplicationContext(), g0.a().b).consumeOwnedPurchase(b(webConsumeOwnedPurchaseRequest.getReservedInfor()), webConsumeOwnedPurchaseRequest).enqueue(new com.huawei.hms.iapfull.network.g(nVar));
    }

    private boolean a(Context context) {
        return !a1.b(context);
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            y0.b("WebPayRepository", "extractParamFromReservedInfor: reservedInfor is null!");
            return "";
        }
        try {
            return new JSONObject(new JSONObject(str).optString("accountInfo")).optString(str2);
        } catch (JSONException unused) {
            y0.a("WebPayRepository", "extractParamFromReservedInfor: JSONException");
            return "";
        }
    }

    private String a(String str) {
        String a2 = a(str, "subAppID");
        return !TextUtils.isEmpty(a2) ? a2 : a(str, HwPayConstant.KEY_APPLICATIONID);
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final o1 f4737a = new o1();
    }

    public static o1 a() {
        return b.f4737a;
    }

    private o1() {
    }
}
