package com.huawei.hms.ads.consent.inner.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.ads.consent.bean.ConsentSyncRsp;
import com.huawei.hms.ads.consent.bean.network.ApiStatisticsReq;
import com.huawei.hms.ads.consent.bean.network.ApiStatisticsRsp;
import com.huawei.hms.ads.consent.bean.network.ConfirmResultReq;
import com.huawei.hms.ads.consent.bean.network.ConfirmResultRsp;
import com.huawei.openalliance.ad.analysis.c;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.qy;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.i;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ConsentImpl {
    public static void reportConsentToKit(Context context, String str, String str2, RemoteCallResultCallback<String> remoteCallResultCallback, Class<String> cls) {
        if (context != null && !TextUtils.isEmpty(str2)) {
            ms.a(context.getApplicationContext()).a(str, str2, remoteCallResultCallback, cls);
        } else {
            ho.d("ConsentImpl", "reportConsentToKit req is empty, please check it!");
            b(remoteCallResultCallback, str, -1, null);
        }
    }

    public static void reportConsentStatus(Context context, String str, String str2, RemoteCallResultCallback<ConsentSyncRsp> remoteCallResultCallback, Class<ConsentSyncRsp> cls) {
        Log.i("ConsentImpl", "reportConsentStatus");
        if (context != null && !TextUtils.isEmpty(str2)) {
            ms.a(context.getApplicationContext()).a(str, str2, remoteCallResultCallback, cls);
        } else {
            ho.d("ConsentImpl", "reportConsentStatus req is empty, please check it!");
            b(remoteCallResultCallback, str, -1, null);
        }
    }

    public static void reportConfirmResult(Context context, final String str, String str2, final RemoteCallResultCallback<ConfirmResultRsp> remoteCallResultCallback, Class<ConfirmResultRsp> cls) {
        if (cz.b(str2)) {
            ho.d("ConsentImpl", "confirmResult req is empty, please check it!");
            b(remoteCallResultCallback, str, -1, null);
            return;
        }
        ConfirmResultReq confirmResultReq = (ConfirmResultReq) be.b(str2, ConfirmResultReq.class, new Class[0]);
        if (confirmResultReq == null || confirmResultReq.getCaches() == null) {
            b(remoteCallResultCallback, str, -1, null);
            return;
        }
        h hVar = new h(context);
        String packageName = context.getPackageName();
        String e = i.e(context, packageName);
        if (TextUtils.isEmpty(packageName) || !WhiteListPkgList.inWhiteList(packageName, e)) {
            ho.a("ConsentImpl", "app set app package name: %s", packageName);
        } else {
            if (confirmResultReq.getCaches().get(0) != null) {
                try {
                    String optString = new JSONObject(confirmResultReq.getCaches().get(0).getParams()).optString(MapKeyNames.FAST_APP_PACAKAE);
                    if (!TextUtils.isEmpty(optString)) {
                        packageName = optString;
                    }
                } catch (JSONException unused) {
                    ho.c("ConsentImpl", "get pkgName failed, params is not valid json");
                }
            }
            ho.a("ConsentImpl", "fast app set app package name: %s", packageName);
        }
        hVar.a(packageName, confirmResultReq, new qy() { // from class: com.huawei.hms.ads.consent.inner.impl.ConsentImpl.1
            @Override // com.huawei.openalliance.ad.qy
            public void a() {
                ConfirmResultRsp confirmResultRsp = new ConfirmResultRsp();
                confirmResultRsp.setRetcode(200);
                ConsentImpl.b(RemoteCallResultCallback.this, str, 200, confirmResultRsp);
            }
        });
    }

    public static void reportApiStistics(Context context, String str, String str2, RemoteCallResultCallback<ApiStatisticsRsp> remoteCallResultCallback, Class<ApiStatisticsRsp> cls) {
        if (context == null || TextUtils.isEmpty(str2)) {
            ho.d("ConsentImpl", "reportApiStistics req is empty, please check it!");
            b(remoteCallResultCallback, str, -1, null);
            return;
        }
        ApiStatisticsReq apiStatisticsReq = (ApiStatisticsReq) be.b(str2, ApiStatisticsReq.class, new Class[0]);
        String packageName = context.getPackageName();
        String e = i.e(context, packageName);
        if (TextUtils.isEmpty(packageName) || !WhiteListPkgList.inWhiteList(packageName, e)) {
            ho.a("ConsentImpl", "app set app package name: %s", packageName);
        } else {
            try {
                String optString = new JSONObject(apiStatisticsReq.getParams()).optString(MapKeyNames.FAST_APP_PACAKAE);
                if (!TextUtils.isEmpty(optString)) {
                    packageName = optString;
                }
            } catch (JSONException unused) {
                ho.c("ConsentImpl", "get pkgName failed, params is not valid json");
            }
            ho.a("ConsentImpl", "fast app set app package name: %s", packageName);
        }
        new c(context).a(packageName, new com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq(apiStatisticsReq));
        ApiStatisticsRsp apiStatisticsRsp = new ApiStatisticsRsp();
        apiStatisticsRsp.setRetcode(200);
        b(remoteCallResultCallback, str, 200, apiStatisticsRsp);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void lookUpConsent(android.content.Context r5, java.lang.String r6, java.lang.String r7, com.huawei.openalliance.ad.ipc.RemoteCallResultCallback<com.huawei.hms.ads.consent.bean.network.ConsentConfigRsp> r8, java.lang.Class<com.huawei.hms.ads.consent.bean.network.ConsentConfigRsp> r9) {
        /*
            java.lang.String r9 = "begin to lookup consent config"
            java.lang.String r0 = "ConsentImpl"
            com.huawei.openalliance.ad.ho.a(r0, r9)
            boolean r9 = com.huawei.openalliance.ad.utils.cz.b(r7)
            r1 = 0
            r2 = -1
            if (r9 == 0) goto L12
            java.lang.String r5 = "consent req is empty, please check it!"
            goto L2d
        L12:
            java.lang.Class<com.huawei.hms.ads.consent.bean.network.ConsentConfigReq> r9 = com.huawei.hms.ads.consent.bean.network.ConsentConfigReq.class
            r3 = 0
            java.lang.Class[] r3 = new java.lang.Class[r3]
            java.lang.Object r9 = com.huawei.openalliance.ad.utils.be.b(r7, r9, r3)
            com.huawei.hms.ads.consent.bean.network.ConsentConfigReq r9 = (com.huawei.hms.ads.consent.bean.network.ConsentConfigReq) r9
            if (r9 != 0) goto L34
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r9 = "req is null: "
            r5.<init>(r9)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
        L2d:
            com.huawei.openalliance.ad.ho.d(r0, r5)
            b(r8, r6, r2, r1)
            return
        L34:
            com.huawei.openalliance.ad.gc r7 = com.huawei.openalliance.ad.fh.b(r5)
            java.lang.String r7 = r7.bI()
            boolean r3 = android.text.TextUtils.isEmpty(r7)
            if (r3 == 0) goto L65
            java.lang.String r7 = r9.getCountryCode()
            boolean r7 = com.huawei.openalliance.ad.utils.cz.b(r7)
            if (r7 == 0) goto L68
            com.huawei.openalliance.ad.beans.inner.CountryCodeBean r7 = new com.huawei.openalliance.ad.beans.inner.CountryCodeBean
            r7.<init>(r5)
            java.lang.String r7 = r7.a()
            com.huawei.openalliance.ad.utils.cg r3 = com.huawei.openalliance.ad.utils.cg.a(r5)
            r3.z(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r7}
            java.lang.String r4 = "look up consent, countryCode is: %s"
            com.huawei.openalliance.ad.ho.a(r0, r4, r3)
        L65:
            r9.setCountryCode(r7)
        L68:
            java.lang.String r7 = com.huawei.openalliance.ad.utils.d.a()
            r9.setLangCode(r7)
            java.lang.String r7 = r5.getPackageName()
            java.lang.String r3 = com.huawei.openalliance.ad.utils.i.e(r5, r7)
            java.lang.String r4 = r9.getPkgName()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L96
            boolean r3 = com.huawei.openalliance.ad.constant.WhiteListPkgList.inWhiteList(r7, r3)
            if (r3 != 0) goto L88
            goto L96
        L88:
            java.lang.String r7 = r9.getPkgName()
            java.lang.Object[] r3 = new java.lang.Object[]{r7}
            java.lang.String r4 = "fast app set app package name:  %s"
            com.huawei.openalliance.ad.ho.a(r0, r4, r3)
            goto La2
        L96:
            r9.setPkgName(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r7}
            java.lang.String r4 = "app set app package name: %s"
            com.huawei.openalliance.ad.ho.a(r0, r4, r3)
        La2:
            com.huawei.openalliance.ad.fx r5 = com.huawei.openalliance.ad.fb.a(r5)
            com.huawei.openalliance.ad.beans.server.ConsentConfigReq r3 = new com.huawei.openalliance.ad.beans.server.ConsentConfigReq
            r3.<init>(r9)
            com.huawei.openalliance.ad.beans.server.ConsentConfigRsp r5 = r5.a(r7, r3)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "responseCode:"
            r7.<init>(r9)
            if (r5 != 0) goto Lbb
            java.lang.String r9 = "null"
            goto Lc1
        Lbb:
            int r9 = r5.responseCode
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
        Lc1:
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            com.huawei.openalliance.ad.ho.b(r0, r7)
            if (r5 == 0) goto Ldc
            int r7 = r5.responseCode
            if (r7 != 0) goto Ldc
            com.huawei.hms.ads.consent.bean.network.ConsentConfigRsp r7 = new com.huawei.hms.ads.consent.bean.network.ConsentConfigRsp
            r7.<init>(r5)
            r5 = 200(0xc8, float:2.8E-43)
            b(r8, r6, r5, r7)
            goto Ldf
        Ldc:
            b(r8, r6, r2, r1)
        Ldf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.consent.inner.impl.ConsentImpl.lookUpConsent(android.content.Context, java.lang.String, java.lang.String, com.huawei.openalliance.ad.ipc.RemoteCallResultCallback, java.lang.Class):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void b(RemoteCallResultCallback<T> remoteCallResultCallback, String str, int i, T t) {
        if (remoteCallResultCallback != null) {
            CallResult<T> callResult = new CallResult<>();
            callResult.setCode(i);
            try {
                if (i == 200) {
                    callResult.setData(t);
                } else {
                    callResult.setMsg("");
                }
            } catch (Throwable th) {
                ho.c("ConsentImpl", "onCallResult fail " + th.getClass().getSimpleName());
                callResult.setCode(-1);
                callResult.setMsg(th.getMessage());
            }
            remoteCallResultCallback.onRemoteCallResult(str, callResult);
        }
    }
}
