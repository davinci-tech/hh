package com.huawei.operation.h5pro.jsmodules;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.exh;
import defpackage.nrv;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class Social extends JsBaseModule {
    private static final int DEFAULT_SIZE = 10240;
    private static final int MAX_LINE = 2048;
    private static final long NO_CALLBACK = -1;
    private static final int TIME_OUT = 1000;
    private String mCookie;

    @JavascriptInterface
    public void getSnsToken(final long j) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.Social$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Social.this.m731lambda$getSnsToken$0$comhuaweioperationh5projsmodulesSocial(j);
            }
        });
    }

    @JavascriptInterface
    public void findUser(final long j, String str) {
        try {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).requestFindUserInfo(0, new JSONObject(str).optString("search"), new UserInfoCallback<exh.b>() { // from class: com.huawei.operation.h5pro.jsmodules.Social.1
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void infoCallback(exh.b bVar) {
                    LogUtil.h(Social.this.TAG, "findUser success !");
                    try {
                        JSONObject jSONObject = new JSONObject(nrv.e(bVar, exh.b.class));
                        jSONObject.put(HwPayConstant.KEY_USER_ID, String.valueOf(jSONObject.getLong(HwPayConstant.KEY_USER_ID)));
                        Social.this.callbackSuccess(jSONObject, j);
                    } catch (JSONException e) {
                        Social.this.callbackFail(-1, "find user fail", j);
                        LogUtil.h(Social.this.TAG, "findUser JSONException ", e.getMessage());
                    }
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i) {
                    LogUtil.h(Social.this.TAG, "findUser fail !", Integer.valueOf(i));
                    if (i == 1005) {
                        Social.this.callbackFail(-1, "find fail", j);
                    } else if (i == -1) {
                        Social.this.callbackFail(1003, "find fail", j);
                    } else {
                        Social.this.callbackFail(i, "find fail", j);
                    }
                }
            });
        } catch (JSONException unused) {
            callbackFail(-1, "getUserInfoSync fail:param invalid", j);
        }
    }

    @JavascriptInterface
    public void getUserInfo(final long j, String str) {
        try {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).requestFindUserInfo(2, new JSONObject(str).optString("huid"), new UserInfoCallback<exh.b>() { // from class: com.huawei.operation.h5pro.jsmodules.Social.2
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void infoCallback(exh.b bVar) {
                    LogUtil.h(Social.this.TAG, "getUserInfo success !");
                    try {
                        JSONObject jSONObject = new JSONObject(nrv.e(bVar, exh.b.class));
                        jSONObject.put(HwPayConstant.KEY_USER_ID, String.valueOf(jSONObject.getLong(HwPayConstant.KEY_USER_ID)));
                        Social.this.callbackSuccess(jSONObject, j);
                    } catch (JSONException e) {
                        Social.this.callbackFail(-1, "getUserInfo fail ！", j);
                        LogUtil.h(Social.this.TAG, "getUserInfo JSONException ", e.getMessage());
                    }
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i) {
                    LogUtil.h(Social.this.TAG, "getUserInfo fail !");
                    Social.this.callbackFail(i, "getUserInfo fail", j);
                }
            });
        } catch (JSONException unused) {
            callbackFail(-1, "getUserInfoSync fail:param invalid", j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cf  */
    /* renamed from: getSnsTokenSync, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m731lambda$getSnsToken$0$comhuaweioperationh5projsmodulesSocial(long r12) {
        /*
            r11 = this;
            r0 = -1
            r1 = 1
            r2 = 0
            r3 = 0
            java.lang.String r4 = r11.getSnsDomain()     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.net.URL r5 = new java.net.URL     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            r6.<init>()     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            r6.append(r4)     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.lang.String r4 = "/SNS/client/ISNS/loginSNS"
            r6.append(r4)     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            r5.<init>(r4)     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.net.URLConnection r4 = r5.openConnection()     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.net.URLConnection r4 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r4)     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            boolean r5 = r4 instanceof java.net.HttpURLConnection     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            if (r5 != 0) goto L34
            java.lang.String r4 = "visit url fail"
            r11.callbackFail(r0, r4, r12)     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            r11.closeQuietly(r3)
            return
        L34:
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> La2 org.json.JSONException -> La5 java.io.IOException -> La7
            java.lang.String r5 = r11.getLoginBody()     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r11.postRequest(r4, r5)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            int r5 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.io.InputStream r3 = r4.getInputStream()     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L6f
            java.lang.String r6 = "Set-Cookie"
            java.lang.String r6 = r4.getHeaderField(r6)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.String r6 = r11.cookieToSessionId(r6)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            if (r7 != 0) goto L6f
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r5.<init>()     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.String r7 = "JSESSIONID"
            r5.put(r7, r6)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r11.callbackSuccess(r5, r12)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            if (r4 == 0) goto L6b
            r4.disconnect()
        L6b:
            r11.closeQuietly(r3)
            return
        L6f:
            java.lang.String r6 = r11.TAG     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.String r9 = "getSnsToken error:"
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r8.append(r5)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r7[r2] = r8     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            com.huawei.hwlogsmodel.LogUtil.b(r6, r7)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            java.lang.String r6 = r11.isToString(r3)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            r11.callbackFail(r5, r6, r12)     // Catch: java.lang.Throwable -> L96 org.json.JSONException -> L9b java.io.IOException -> L9d
            if (r4 == 0) goto L92
            r4.disconnect()
        L92:
            r11.closeQuietly(r3)
            goto Lcb
        L96:
            r12 = move-exception
            r10 = r4
            r4 = r3
            r3 = r10
            goto Lcd
        L9b:
            r5 = move-exception
            goto L9e
        L9d:
            r5 = move-exception
        L9e:
            r10 = r4
            r4 = r3
            r3 = r10
            goto Laa
        La2:
            r12 = move-exception
            r4 = r3
            goto Lcd
        La5:
            r4 = move-exception
            goto La8
        La7:
            r4 = move-exception
        La8:
            r5 = r4
            r4 = r3
        Laa:
            java.lang.String r6 = r11.TAG     // Catch: java.lang.Throwable -> Lcc
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r8 = "get sns token exception:"
            r7[r2] = r8     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r2 = r5.getMessage()     // Catch: java.lang.Throwable -> Lcc
            r7[r1] = r2     // Catch: java.lang.Throwable -> Lcc
            com.huawei.hwlogsmodel.LogUtil.b(r6, r7)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r1 = r5.getMessage()     // Catch: java.lang.Throwable -> Lcc
            r11.callbackFail(r0, r1, r12)     // Catch: java.lang.Throwable -> Lcc
            if (r3 == 0) goto Lc8
            r3.disconnect()
        Lc8:
            r11.closeQuietly(r4)
        Lcb:
            return
        Lcc:
            r12 = move-exception
        Lcd:
            if (r3 == 0) goto Ld2
            r3.disconnect()
        Ld2:
            r11.closeQuietly(r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.jsmodules.Social.m731lambda$getSnsToken$0$comhuaweioperationh5projsmodulesSocial(long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void callbackSuccess(T t, long j) {
        if (j != -1) {
            onSuccessCallback(j, t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFail(int i, String str, long j) {
        if (j != -1) {
            onFailureCallback(j, str, i);
        }
    }

    private void postRequest(HttpURLConnection httpURLConnection, String str) throws IOException {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setReadTimeout(1000);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        if (!TextUtils.isEmpty(this.mCookie)) {
            httpURLConnection.setRequestProperty("Cookie", this.mCookie);
        }
        httpURLConnection.setDoOutput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
        closeQuietly(outputStream);
    }

    private String getLoginBody() {
        JSONObject jSONObject = new JSONObject();
        try {
            String b = SharedPreferenceManager.b(this.mContext, "push_token_caching", "push_token");
            LogUtil.a(this.TAG, "pushToken:", Boolean.valueOf(TextUtils.isEmpty(b)));
            LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, loginInit.getDeviceId());
            jSONObject.put("deviceType", loginInit.getDeviceType());
            jSONObject.put("appID", "com.huawei.health");
            jSONObject.put("channel", 45000001);
            jSONObject.put(CommonUtil.PARAM_PUSH_TOKEN, b);
            jSONObject.put("2".equals(loginInit.getAccountInfo(1012)) ? "accessToken" : "serviceToken", loginInit.getAccountInfo(1008));
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    private String isToString(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(DEFAULT_SIZE);
            int i = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                i++;
                if (i > 2048) {
                    LogUtil.b(this.TAG, "exceed max line");
                    break;
                }
            }
            return sb.toString();
        } catch (IOException unused) {
            LogUtil.b(this.TAG, "isToString fail");
            return "";
        }
    }

    private String cookieToSessionId(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String replace = str.replace(" ", "").replace(";Path=/SNS;HttpOnly", "");
        this.mCookie = replace;
        return replace.replace("JSESSIONID=", "");
    }

    private String getSnsDomain() {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainSnsHicloud");
        if (!TextUtils.isEmpty(url) && url.contains("%")) {
            int parseInt = Integer.parseInt(LoginInit.getInstance(this.mContext).getAccountInfo(1009));
            if (parseInt > 0) {
                return String.format(Locale.ENGLISH, url, Integer.valueOf(parseInt));
            }
            LogUtil.h(this.TAG, "formatUrl() AccountInfo is null or site id <= 0");
        }
        return url;
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
            LogUtil.h(this.TAG, "close stream fail");
        }
    }
}
