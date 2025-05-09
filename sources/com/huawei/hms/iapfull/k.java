package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.iapfull.network.model.ReportPayResultResponse;

/* loaded from: classes9.dex */
class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ ReportPayResultResponse f4721a;
    final /* synthetic */ Headers b;
    final /* synthetic */ l c;

    /* JADX WARN: Removed duplicated region for block: B:10:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0073  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r7 = this;
            com.huawei.hms.iapfull.network.model.ReportPayResultResponse r0 = r7.f4721a
            java.lang.String r0 = r0.getReturnCode()
            java.lang.String r1 = "0"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L82
            java.lang.String r0 = "request success..."
            java.lang.String r1 = "ReportPayResutCallback"
            com.huawei.hms.iapfull.y0.b(r1, r0)
            com.huawei.hms.framework.network.restclient.Headers r0 = r7.b
            if (r0 == 0) goto L76
            com.huawei.hms.iapfull.network.model.ReportPayResultResponse r2 = r7.f4721a
            java.lang.String r3 = "HW-IAP-SIGN"
            java.lang.String r3 = r0.get(r3)
            java.lang.String r4 = "HW-IAP-SIGN-GROUP"
            java.lang.String r0 = r0.get(r4)
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r5 = ""
            if (r4 != 0) goto L53
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch: org.json.JSONException -> L4c
            r4.<init>(r0)     // Catch: org.json.JSONException -> L4c
            int r0 = r4.length()     // Catch: org.json.JSONException -> L4c
            if (r0 <= 0) goto L53
            r0 = 0
            org.json.JSONObject r0 = r4.getJSONObject(r0)     // Catch: org.json.JSONException -> L4c
            java.lang.String r4 = "signType"
            java.lang.String r4 = r0.optString(r4)     // Catch: org.json.JSONException -> L4c
            java.lang.String r6 = "sign"
            java.lang.String r5 = r0.optString(r6)     // Catch: org.json.JSONException -> L4d
            goto L54
        L4c:
            r4 = r5
        L4d:
            java.lang.String r0 = "setNewSign，headerSignJson parsing JSONException"
            com.huawei.hms.iapfull.y0.a(r1, r0)
            goto L54
        L53:
            r4 = r5
        L54:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L63
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L63
            java.lang.String r4 = "RSA256"
            goto L64
        L63:
            r3 = r5
        L64:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L6d
            r2.setNewSign(r3)
        L6d:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L76
            r2.setSignatureAlgorithm(r4)
        L76:
            com.huawei.hms.iapfull.l r0 = r7.c
            com.huawei.hms.iapfull.s0 r0 = com.huawei.hms.iapfull.l.a(r0)
            com.huawei.hms.iapfull.network.model.ReportPayResultResponse r1 = r7.f4721a
            r0.a(r1)
            goto L97
        L82:
            com.huawei.hms.iapfull.l r0 = r7.c
            com.huawei.hms.iapfull.s0 r0 = com.huawei.hms.iapfull.l.a(r0)
            com.huawei.hms.iapfull.network.model.ReportPayResultResponse r1 = r7.f4721a
            java.lang.String r1 = r1.getReturnCode()
            com.huawei.hms.iapfull.network.model.ReportPayResultResponse r2 = r7.f4721a
            java.lang.String r2 = r2.getReturnDesc()
            r0.a(r1, r2)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.iapfull.k.run():void");
    }

    k(l lVar, ReportPayResultResponse reportPayResultResponse, Headers headers) {
        this.c = lVar;
        this.f4721a = reportPayResultResponse;
        this.b = headers;
    }
}
