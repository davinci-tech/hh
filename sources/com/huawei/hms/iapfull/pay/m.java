package com.huawei.hms.iapfull.pay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.huawei.health.R;
import com.huawei.hms.iapfull.a1;
import com.huawei.hms.iapfull.network.model.AliPaySignResponse;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.secure.android.common.intent.IntentUtils;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private Activity f4760a;
    private a b;

    interface a {
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(com.huawei.hms.iapfull.pay.m r7, java.util.Map r8) {
        /*
            java.lang.String r0 = "out_trade_no"
            java.lang.String r1 = "alipay_trade_app_pay_response"
            java.lang.String r2 = "sign"
            java.lang.String r3 = ""
            r7.getClass()
            java.lang.String r4 = "alipay result -->"
            java.lang.String r5 = "ThirdPayHelper"
            com.huawei.hms.iapfull.y0.b(r5, r4)
            if (r8 == 0) goto L80
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L1c
            goto L80
        L1c:
            java.lang.String r4 = "resultStatus"
            java.lang.Object r4 = r8.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 == 0) goto L2c
            java.lang.String r4 = "4000"
        L2c:
            java.lang.String r6 = "9000"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L85
            java.lang.String r4 = "result"
            java.lang.Object r8 = r8.get(r4)
            java.lang.String r8 = (java.lang.String) r8
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L6b
            r4.<init>(r8)     // Catch: org.json.JSONException -> L6b
            boolean r8 = r4.has(r2)     // Catch: org.json.JSONException -> L6b
            if (r8 == 0) goto L4c
            java.lang.String r8 = r4.optString(r2)     // Catch: org.json.JSONException -> L6b
            goto L4d
        L4c:
            r8 = r3
        L4d:
            boolean r2 = r4.has(r1)     // Catch: org.json.JSONException -> L69
            if (r2 == 0) goto L67
            java.lang.String r1 = r4.optString(r1)     // Catch: org.json.JSONException -> L69
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L6d
            r2.<init>(r1)     // Catch: org.json.JSONException -> L6d
            boolean r4 = r2.has(r0)     // Catch: org.json.JSONException -> L6d
            if (r4 == 0) goto L72
            java.lang.String r3 = r2.optString(r0)     // Catch: org.json.JSONException -> L6d
            goto L72
        L67:
            r0 = r3
            goto L74
        L69:
            r1 = r3
            goto L6d
        L6b:
            r8 = r3
            r1 = r8
        L6d:
            java.lang.String r0 = "dealAlipaySuccessResult jsonException"
            com.huawei.hms.iapfull.y0.a(r5, r0)
        L72:
            r0 = r3
            r3 = r1
        L74:
            com.huawei.hms.iapfull.pay.m$a r7 = r7.b
            if (r7 == 0) goto L85
            com.huawei.hms.iapfull.pay.j r7 = (com.huawei.hms.iapfull.pay.j) r7
            java.lang.String r1 = "success"
            r7.a(r1, r3, r8, r0)
            goto L85
        L80:
            java.lang.String r7 = "resultMap is empty"
            com.huawei.hms.iapfull.y0.b(r5, r7)
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.iapfull.pay.m.a(com.huawei.hms.iapfull.pay.m, java.util.Map):void");
    }

    protected void a(a aVar) {
        this.b = aVar;
    }

    protected void a(AliPaySignResponse aliPaySignResponse) {
        a(this.f4760a, aliPaySignResponse.getAliPayUri());
    }

    public static boolean a(Activity activity, String str, String str2) {
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(activity, str);
        createWXAPI.registerApp(str);
        WXOpenBusinessWebview.Req req = new WXOpenBusinessWebview.Req();
        req.businessType = 12;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pre_entrustweb_id", str2);
        req.queryInfo = hashMap;
        boolean sendReq = createWXAPI.sendReq(req);
        if (!sendReq) {
            y0.a("ThirdPayHelper", "callWechatSign activity is not found");
            a1.a(activity, R.string._2130851294_res_0x7f0235de, 0);
        }
        y0.b("ThirdPayHelper", "callWechatSign result " + sendReq);
        return sendReq;
    }

    public static boolean a(Activity activity, String str) {
        return a(activity, Uri.parse(str));
    }

    private static boolean a(Activity activity, Uri uri) {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, uri);
        intent.setPackage("com.eg.android.AlipayGphone");
        boolean safeStartActivityForResultStatic = IntentUtils.safeStartActivityForResultStatic(activity, intent, 1);
        if (!safeStartActivityForResultStatic) {
            y0.a("ThirdPayHelper", "callAliPaySignAPK alipays activity is not found");
            a1.a(activity, R.string._2130851256_res_0x7f0235b8, 0);
        }
        return safeStartActivityForResultStatic;
    }

    protected m(Activity activity, String str) {
        this.f4760a = activity;
    }
}
