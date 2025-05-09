package com.huawei.operation.h5pro.jsmodules;

import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;

/* loaded from: classes.dex */
public class WeChatJsApi extends JsBaseModule {
    private static final String TAG = "WeChatJsApi";

    /* JADX WARN: Removed duplicated region for block: B:14:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0069  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void launchWxMiniProgram(long r5, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r2 = "WeChatJsApi"
            if (r1 == 0) goto L1a
            java.lang.String r7 = "launchWxMiniProgram param invalid"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r7)
            java.lang.String r7 = "invalid param"
            r4.onFailureCallback(r5, r7)
            return
        L1a:
            java.lang.String r1 = "launchWxMiniProgram param is: "
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r7}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L41
            r1.<init>(r7)     // Catch: org.json.JSONException -> L41
            java.lang.String r7 = "userName"
            java.lang.String r7 = r1.getString(r7)     // Catch: org.json.JSONException -> L41
            java.lang.String r3 = "path"
            java.lang.String r0 = r1.getString(r3)     // Catch: org.json.JSONException -> L3f
            java.lang.String r3 = "miniProgramType"
            int r1 = r1.getInt(r3)     // Catch: org.json.JSONException -> L3f
            goto L53
        L3f:
            r1 = move-exception
            goto L44
        L41:
            r7 = move-exception
            r1 = r7
            r7 = r0
        L44:
            java.lang.String r3 = "launchWxMiniProgram exception "
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r2, r1)
            r1 = 0
        L53:
            boolean r3 = android.text.TextUtils.isEmpty(r7)
            if (r3 == 0) goto L69
            java.lang.String r7 = "launchWxMiniProgram userName invalid"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r7)
            java.lang.String r7 = "invalid userName"
            r4.onFailureCallback(r5, r7)
            return
        L69:
            java.lang.String r2 = "Main"
            java.lang.Class<com.huawei.health.main.api.WeChatApi> r3 = com.huawei.health.main.api.WeChatApi.class
            java.lang.Object r2 = health.compact.a.Services.c(r2, r3)
            com.huawei.health.main.api.WeChatApi r2 = (com.huawei.health.main.api.WeChatApi) r2
            r2.launchWxMiniProgram(r7, r0, r1)
            r4.onSuccessCallback(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.jsmodules.WeChatJsApi.launchWxMiniProgram(long, java.lang.String):void");
    }
}
