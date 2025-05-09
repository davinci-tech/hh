package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.m.u.h;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.kg;
import defpackage.kh;
import defpackage.kl;
import defpackage.kr;
import defpackage.lq;
import defpackage.ls;
import defpackage.lu;
import defpackage.lv;
import defpackage.lw;
import defpackage.ly;
import defpackage.lz;
import defpackage.ma;
import defpackage.mc;
import defpackage.md;
import defpackage.mj;
import defpackage.mo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class PayTask {
    public static final Object h = h.class;
    public static long i;

    /* renamed from: a, reason: collision with root package name */
    public Activity f857a;
    public mj b;
    public final String c = "wappaygw.alipay.com/service/rest.htm";
    public final String d = "mclient.alipay.com/service/rest.htm";
    public final String e = "mclient.alipay.com/home/exterfaceAssign.htm";
    public final String f = "mclient.alipay.com/cashier/mobilepay.htm";
    public Map<String, c> g = new HashMap();

    public class a implements Runnable {
        public final /* synthetic */ boolean b;
        public final /* synthetic */ String d;
        public final /* synthetic */ H5PayCallback e;

        public a(String str, boolean z, H5PayCallback h5PayCallback) {
            this.d = str;
            this.b = z;
            this.e = h5PayCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            mo h5Pay = PayTask.this.h5Pay(new lv(PayTask.this.f857a, this.d, "payInterceptorWithUrl"), this.d, this.b);
            ma.d("mspl", "inc finished: " + h5Pay.d());
            this.e.onPayResult(h5Pay);
        }
    }

    /* loaded from: classes8.dex */
    public class c {

        /* renamed from: a, reason: collision with root package name */
        public String f858a;
        public String b;
        public String c;
        public String e;

        public c() {
            this.e = "";
            this.c = "";
            this.b = "";
            this.f858a = "";
        }

        public String a() {
            return this.e;
        }

        public String d() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public void c(String str) {
            this.f858a = str;
        }

        public void d(String str) {
            this.e = str;
        }

        public void e(String str) {
            this.b = str;
        }

        public /* synthetic */ c(PayTask payTask, a aVar) {
            this();
        }
    }

    public class e implements h.g {
        public e() {
        }

        @Override // com.alipay.sdk.m.u.h.g
        public void a() {
            PayTask.this.dismissLoading();
        }

        @Override // com.alipay.sdk.m.u.h.g
        public void b() {
        }
    }

    public PayTask(Activity activity) {
        this.f857a = activity;
        lw.c().c(this.f857a);
        this.b = new mj(activity, "去支付宝付款");
    }

    public static boolean fetchSdkConfig(Context context) {
        synchronized (PayTask.class) {
            try {
                lw.c().c(context);
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - i < kr.a().j()) {
                    return false;
                }
                i = elapsedRealtime;
                kr.a().a(lv.c(), context.getApplicationContext(), false, 4);
                return true;
            } catch (Exception e2) {
                ma.c(e2);
                return false;
            }
        }
    }

    public void dismissLoading() {
        mj mjVar = this.b;
        if (mjVar != null) {
            mjVar.b();
            this.b = null;
        }
    }

    public String fetchOrderInfoFromH5PayUrl(String str) {
        synchronized (this) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    String trim = str.trim();
                    if (trim.startsWith("https://wappaygw.alipay.com/service/rest.htm") || trim.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                        String trim2 = trim.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm\\?", "").trim();
                        if (!TextUtils.isEmpty(trim2)) {
                            return "_input_charset=\"utf-8\"&ordertoken=\"" + md.c("<request_token>", "</request_token>", md.c(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + a(this.f857a) + "\"";
                        }
                    }
                    if (trim.startsWith("https://mclient.alipay.com/service/rest.htm") || trim.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                        String trim3 = trim.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm\\?", "").trim();
                        if (!TextUtils.isEmpty(trim3)) {
                            return "_input_charset=\"utf-8\"&ordertoken=\"" + md.c("<request_token>", "</request_token>", md.c(trim3).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + a(this.f857a) + "\"";
                        }
                    }
                    if ((trim.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || trim.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm")) && ((trim.contains("alipay.wap.create.direct.pay.by.user") || trim.contains("create_forex_trade_wap")) && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm\\?", "").trim()))) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("url", str);
                        jSONObject.put("bizcontext", a(this.f857a));
                        return "new_external_info==" + jSONObject.toString();
                    }
                    a aVar = null;
                    if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com/w/trade_pay\\.do.?|mali\\.alipay\\.com/w/trade_pay\\.do.?|mclient\\.alipay\\.com/w/trade_pay\\.do.?)").matcher(str).find()) {
                        String c2 = md.c("?", "", str);
                        if (!TextUtils.isEmpty(c2)) {
                            Map<String, String> c3 = md.c(c2);
                            StringBuilder sb = new StringBuilder();
                            if (c(false, true, "trade_no", sb, c3, "trade_no", "alipay_trade_no")) {
                                c(true, false, "pay_phase_id", sb, c3, "payPhaseId", "pay_phase_id", "out_relation_id");
                                sb.append("&biz_sub_type=\"TRADE\"");
                                sb.append("&biz_type=\"trade\"");
                                String str2 = c3.get("app_name");
                                if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(c3.get(JsbMapKeyNames.H5_CLIENT_ID))) {
                                    str2 = "ali1688";
                                } else if (TextUtils.isEmpty(str2) && (!TextUtils.isEmpty(c3.get("sid")) || !TextUtils.isEmpty(c3.get("s_id")))) {
                                    str2 = "tb";
                                }
                                sb.append("&app_name=\"" + str2 + "\"");
                                if (!c(true, true, "extern_token", sb, c3, "extern_token", JsbMapKeyNames.H5_CLIENT_ID, "sid", "s_id")) {
                                    return "";
                                }
                                c(true, false, "appenv", sb, c3, "appenv");
                                sb.append("&pay_channel_id=\"alipay_sdk\"");
                                c cVar = new c(this, aVar);
                                cVar.d(c3.get("return_url"));
                                cVar.a(c3.get("show_url"));
                                cVar.e(c3.get("pay_order_id"));
                                String str3 = sb.toString() + "&bizcontext=\"" + a(this.f857a) + "\"";
                                this.g.put(str3, cVar);
                                return str3;
                            }
                        }
                    }
                    if (!trim.startsWith("https://mclient.alipay.com/cashier/mobilepay.htm") && !trim.startsWith("http://mclient.alipay.com/cashier/mobilepay.htm") && (!EnvUtils.b() || !trim.contains("mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                        if (kr.a().m() && Pattern.compile("^https?://(maliprod\\.alipay\\.com|mali\\.alipay\\.com)/batch_payment\\.do\\?").matcher(trim).find()) {
                            Uri parse = Uri.parse(trim);
                            String queryParameter = parse.getQueryParameter("return_url");
                            String queryParameter2 = parse.getQueryParameter("show_url");
                            String queryParameter3 = parse.getQueryParameter("pay_order_id");
                            String a2 = a(parse.getQueryParameter("trade_nos"), parse.getQueryParameter("alipay_trade_no"));
                            String a3 = a(parse.getQueryParameter("payPhaseId"), parse.getQueryParameter("pay_phase_id"), parse.getQueryParameter("out_relation_id"));
                            String[] strArr = new String[4];
                            strArr[0] = parse.getQueryParameter("app_name");
                            strArr[1] = !TextUtils.isEmpty(parse.getQueryParameter(JsbMapKeyNames.H5_CLIENT_ID)) ? "ali1688" : "";
                            strArr[2] = !TextUtils.isEmpty(parse.getQueryParameter("sid")) ? "tb" : "";
                            strArr[3] = !TextUtils.isEmpty(parse.getQueryParameter("s_id")) ? "tb" : "";
                            String a4 = a(strArr);
                            String a5 = a(parse.getQueryParameter("extern_token"), parse.getQueryParameter(JsbMapKeyNames.H5_CLIENT_ID), parse.getQueryParameter("sid"), parse.getQueryParameter("s_id"));
                            String a6 = a(parse.getQueryParameter("appenv"));
                            if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a4) && !TextUtils.isEmpty(a5)) {
                                String format = String.format("trade_no=\"%s\"&pay_phase_id=\"%s\"&biz_type=\"trade\"&biz_sub_type=\"TRADE\"&app_name=\"%s\"&extern_token=\"%s\"&appenv=\"%s\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"%s\"", a2, a3, a4, a5, a6, a(this.f857a));
                                c cVar2 = new c(this, null);
                                cVar2.d(queryParameter);
                                cVar2.a(queryParameter2);
                                cVar2.e(queryParameter3);
                                cVar2.c(a2);
                                this.g.put(format, cVar2);
                                return format;
                            }
                        }
                    }
                    String a7 = a(this.f857a);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("url", trim);
                    jSONObject2.put("bizcontext", a7);
                    return String.format("new_external_info==%s", jSONObject2.toString());
                }
            } finally {
                return "";
            }
            return "";
        }
    }

    public String fetchTradeToken() {
        String c2;
        synchronized (this) {
            c2 = ly.c(new lv(this.f857a, "", "fetchTradeToken"), this.f857a.getApplicationContext());
        }
        return c2;
    }

    public mo h5Pay(lv lvVar, String str, boolean z) {
        mo moVar;
        synchronized (this) {
            moVar = new mo();
            try {
                String[] split = d(lvVar, str, z).split(";");
                HashMap hashMap = new HashMap();
                for (String str2 : split) {
                    int indexOf = str2.indexOf("={");
                    if (indexOf >= 0) {
                        String substring = str2.substring(0, indexOf);
                        hashMap.put(substring, d(str2, substring));
                    }
                }
                if (hashMap.containsKey("resultStatus")) {
                    moVar.a(hashMap.get("resultStatus"));
                }
                moVar.e(d(str, hashMap));
                if (TextUtils.isEmpty(moVar.b())) {
                    kl.c(lvVar, "biz", "H5CbUrlEmpty", "");
                }
            } catch (Throwable th) {
                kl.e(lvVar, "biz", "H5CbEx", th);
                ma.c(th);
            }
        }
        return moVar;
    }

    public String pay(String str, boolean z) {
        synchronized (this) {
            if (lz.e()) {
                return kh.d();
            }
            return d(new lv(this.f857a, str, "pay"), str, z);
        }
    }

    public boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        boolean isEmpty;
        synchronized (this) {
            String fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
            if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
                ma.d("mspl", "intercepted: " + fetchOrderInfoFromH5PayUrl);
                new Thread(new a(fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
            }
            isEmpty = TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
        }
        return !isEmpty;
    }

    public Map<String, String> payV2(String str, boolean z) {
        String d;
        lv lvVar;
        Map<String, String> c2;
        synchronized (this) {
            if (lz.e()) {
                d = kh.d();
                lvVar = null;
            } else {
                lv lvVar2 = new lv(this.f857a, str, "payV2");
                d = d(lvVar2, str, z);
                lvVar = lvVar2;
            }
            c2 = mc.c(lvVar, d);
        }
        return c2;
    }

    public void showLoading() {
        mj mjVar = this.b;
        if (mjVar != null) {
            mjVar.a();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ff, code lost:
    
        if (defpackage.kr.a().y() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0162, code lost:
    
        dismissLoading();
        defpackage.kl.e(r8.f857a.getApplicationContext(), r9, r10, r9.b);
        defpackage.ma.d("mspl", "pay returning: " + r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0155, code lost:
    
        defpackage.kr.a().a(r9, r8.f857a.getApplicationContext(), false, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0153, code lost:
    
        if (defpackage.kr.a().y() != false) goto L33;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(defpackage.lv r9, java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.d(lv, java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0052 A[Catch: all -> 0x005c, TryCatch #2 {all -> 0x005c, blocks: (B:9:0x001e, B:11:0x0052, B:12:0x0057), top: B:8:0x001e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r6) {
        /*
            java.lang.String r0 = "sc"
            java.lang.String r1 = ""
            android.content.pm.PackageManager r2 = r6.getPackageManager()     // Catch: java.lang.Exception -> L18
            java.lang.String r6 = r6.getPackageName()     // Catch: java.lang.Exception -> L18
            r3 = 0
            android.content.pm.PackageInfo r6 = r2.getPackageInfo(r6, r3)     // Catch: java.lang.Exception -> L18
            java.lang.String r2 = r6.versionName     // Catch: java.lang.Exception -> L18
            java.lang.String r6 = r6.packageName     // Catch: java.lang.Exception -> L16
            goto L1e
        L16:
            r6 = move-exception
            goto L1a
        L18:
            r6 = move-exception
            r2 = r1
        L1a:
            defpackage.ma.c(r6)
            r6 = r1
        L1e:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L5c
            r3.<init>()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "appkey"
            java.lang.String r5 = "2014052600006128"
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "ty"
            java.lang.String r5 = "and_lite"
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "sv"
            java.lang.String r5 = "h.a.3.8.14"
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "an"
            r3.put(r4, r6)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r6 = "av"
            r3.put(r6, r2)     // Catch: java.lang.Throwable -> L5c
            java.lang.String r6 = "sdk_start_time"
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L5c
            r3.put(r6, r4)     // Catch: java.lang.Throwable -> L5c
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L5c
            if (r6 != 0) goto L57
            java.lang.String r6 = "h5tonative"
            r3.put(r0, r6)     // Catch: java.lang.Throwable -> L5c
        L57:
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L5c
            return r6
        L5c:
            r6 = move-exception
            defpackage.ma.c(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(android.content.Context):java.lang.String");
    }

    public static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    private boolean c(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        }
        if (z) {
            sb.append("&");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append("\"");
        return true;
    }

    private String d(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean equals = "9000".equals(map.get("resultStatus"));
        String str2 = map.get("result");
        c remove = this.g.remove(str);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String a2 = a(md.c("&callBackUrl=\"", "\"", str2), md.c("&call_back_url=\"", "\"", str2), md.c("&return_url=\"", "\"", str2), URLDecoder.decode(md.c("&return_url=", "&", str2), "utf-8"), URLDecoder.decode(md.c("&callBackUrl=", "&", str2), "utf-8"), md.c("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        if (remove != null) {
            String a3 = equals ? remove.a() : remove.d();
            if (!TextUtils.isEmpty(a3)) {
                return a3;
            }
        }
        return remove != null ? kr.a().v() : "";
    }

    private String d(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }

    private h.g b() {
        return new e();
    }

    private String a(String str, lv lvVar) {
        String e2 = lvVar.e(str);
        if (e2.contains("paymethod=\"expressGateway\"")) {
            return b(lvVar, e2);
        }
        List<kr.e> k = kr.a().k();
        if (!kr.a().o || k == null) {
            k = kg.d;
        }
        if (md.e(lvVar, this.f857a, k, true)) {
            h hVar = new h(this.f857a, lvVar, b());
            ma.d("mspl", "pay inner started: " + e2);
            String c2 = hVar.c(e2, false);
            if (!TextUtils.isEmpty(c2)) {
                if (c2.contains("resultStatus={" + com.alipay.sdk.m.j.c.ACTIVITY_NOT_START_EXIT.b() + "}")) {
                    md.c("alipaySdk", "startActivityEx", this.f857a, lvVar);
                    if (kr.a().b()) {
                        c2 = hVar.c(e2, true);
                    } else {
                        c2 = c2.replace("resultStatus={" + com.alipay.sdk.m.j.c.ACTIVITY_NOT_START_EXIT.b() + "}", "resultStatus={" + com.alipay.sdk.m.j.c.CANCELED.b() + "}");
                    }
                }
            }
            ma.d("mspl", "pay inner raw result: " + c2);
            hVar.a();
            boolean ab = kr.a().ab();
            if (!TextUtils.equals(c2, "failed") && !TextUtils.equals(c2, "scheme_failed") && (!ab || !lvVar.i())) {
                if (TextUtils.isEmpty(c2)) {
                    return kh.a();
                }
                if (!c2.contains("{\"isLogin\":\"false\"}")) {
                    return c2;
                }
                kl.b(lvVar, "biz", "LogHkLoginByIntent");
                return a(lvVar, e2, k, c2, this.f857a);
            }
            kl.b(lvVar, "biz", "LogBindCalledH5");
            return b(lvVar, e2);
        }
        kl.b(lvVar, "biz", "LogCalledH5");
        return b(lvVar, e2);
    }

    public static String a(lv lvVar, String str, List<kr.e> list, String str2, Activity activity) {
        md.d a2 = md.a(lvVar, activity, list);
        if (a2 == null || a2.c(lvVar) || a2.e() || !TextUtils.equals(a2.e.packageName, "hk.alipay.wallet")) {
            return str2;
        }
        ma.a("mspl", "PayTask not_login");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.b.put(valueOf, new Object());
        Intent intent = new Intent(activity, (Class<?>) PayResultActivity.class);
        intent.putExtra("orderSuffix", str);
        intent.putExtra("externalPkgName", activity.getPackageName());
        intent.putExtra("phonecashier.pay.hash", valueOf);
        lv.e.a(lvVar, intent);
        activity.startActivity(intent);
        synchronized (PayResultActivity.b.get(valueOf)) {
            try {
                ma.a("mspl", "PayTask wait");
                PayResultActivity.b.get(valueOf).wait();
            } catch (InterruptedException unused) {
                ma.a("mspl", "PayTask interrupted");
                return kh.a();
            }
        }
        String str3 = PayResultActivity.b.c;
        ma.a("mspl", "PayTask ret: " + str3);
        return str3;
    }

    private String b(lv lvVar, String str) {
        showLoading();
        com.alipay.sdk.m.j.c cVar = null;
        try {
            try {
                try {
                    JSONObject a2 = new lq().a(lvVar, this.f857a.getApplicationContext(), str).a();
                    String optString = a2.optString("end_code", null);
                    List<ls> b = ls.b(a2.optJSONObject("form").optJSONObject("onload"));
                    for (int i2 = 0; i2 < b.size(); i2++) {
                        if (b.get(i2).a() == com.alipay.sdk.m.r.a.Update) {
                            ls.a(b.get(i2));
                        }
                    }
                    a(lvVar, a2);
                    dismissLoading();
                    kl.c(this.f857a, lvVar, str, lvVar.b);
                    for (int i3 = 0; i3 < b.size(); i3++) {
                        ls lsVar = b.get(i3);
                        if (lsVar.a() == com.alipay.sdk.m.r.a.WapPay) {
                            String b2 = b(lvVar, lsVar);
                            dismissLoading();
                            kl.c(this.f857a, lvVar, str, lvVar.b);
                            return b2;
                        }
                        if (lsVar.a() == com.alipay.sdk.m.r.a.OpenWeb) {
                            String e2 = e(lvVar, lsVar, optString);
                            dismissLoading();
                            kl.c(this.f857a, lvVar, str, lvVar.b);
                            return e2;
                        }
                    }
                    dismissLoading();
                    kl.c(this.f857a, lvVar, str, lvVar.b);
                } catch (IOException e3) {
                    com.alipay.sdk.m.j.c b3 = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.NETWORK_ERROR.b());
                    kl.e(lvVar, "net", e3);
                    dismissLoading();
                    kl.c(this.f857a, lvVar, str, lvVar.b);
                    cVar = b3;
                }
            } catch (Throwable th) {
                ma.c(th);
                kl.e(lvVar, "biz", "H5PayDataAnalysisError", th);
                dismissLoading();
                kl.c(this.f857a, lvVar, str, lvVar.b);
            }
            if (cVar == null) {
                cVar = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.FAILED.b());
            }
            return kh.c(cVar.b(), cVar.a(), "");
        } catch (Throwable th2) {
            dismissLoading();
            kl.c(this.f857a, lvVar, str, lvVar.b);
            throw th2;
        }
    }

    private void a(lv lvVar, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString("client_key");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return;
            }
            lu.b(lw.c().d()).d(optString, optString2);
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "ParserTidClientKeyEx", th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0092, code lost:
    
        r0 = r6.c();
        r11 = defpackage.kh.c(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], defpackage.md.c(r10, r0[2]));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String e(defpackage.lv r10, defpackage.ls r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.e(lv, ls, java.lang.String):java.lang.String");
    }

    private String b(lv lvVar, ls lsVar) {
        String[] c2 = lsVar.c();
        Intent intent = new Intent(this.f857a, (Class<?>) H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", c2[0]);
        if (c2.length == 2) {
            bundle.putString("cookie", c2[1]);
        }
        intent.putExtras(bundle);
        lv.e.a(lvVar, intent);
        this.f857a.startActivity(intent);
        Object obj = h;
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e2) {
                ma.c(e2);
                return kh.a();
            }
        }
        String e3 = kh.e();
        return TextUtils.isEmpty(e3) ? kh.a() : e3;
    }

    public String getVersion() {
        return "15.8.14";
    }
}
