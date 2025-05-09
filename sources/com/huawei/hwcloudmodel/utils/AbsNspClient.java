package com.huawei.hwcloudmodel.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.utils.HttpConnectionAdaptor;
import com.huawei.up.utils.NSPException;
import defpackage.jce;
import defpackage.kub;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;

/* loaded from: classes5.dex */
public abstract class AbsNspClient {
    protected static final String APP_ID = "appId";
    protected static final String BI_UPLOAD_ENABLE = "improveState";
    protected static final String CURRENT_MANUFACTURER = "currentManufacturer";
    protected static final String DEVICE_IMEI = "deviceId";
    protected static final String DEVICE_TYPE = "deviceType";
    private static final int FILE_TAIL = -1;
    protected static final String GZIP = "gzip";
    private static final int GZIP_BUFFER_SIZE = 8192;
    protected static final String IS_MANUALLY = "isManually";
    protected static final String IS_TRACKING_ENABLED = "isTrackingEnabled";
    protected static final String I_VERSION = "iVersion";
    protected static final String LANGUAGE = "language";
    protected static final String OAID = "oaId";
    private static final int OTHER_BUFFER_SIZE = 1024;
    protected static final int PRIVACY_GOODS = 10;
    private static final int RESPONSE_REDIRECT = 302;
    private static final int RESPONSE_SUCCESS = 200;
    protected static final String SITE_ID = "siteId";
    protected static final String SOURCE = "source";
    protected static final String SYS_VERSION = "sysVersion";
    private static final String TAG = "UIME_AbsNspClient";
    protected static final String TOKEN = "token";
    protected static final String TOKEN_TYPE = "tokenType";
    protected static final String TS = "ts";
    public static final int TYPE_SPORT_HEALTH_DATA = 2;
    protected static final String UP_DEVICE_TYPE = "upDeviceType";
    public static final int URL_TYPE = 1;
    public static final int URL_TYPE_WIFI_SERVICE = 2;
    protected static final String USER_PRIVACY_KEY = "cloud_user_privacy";

    protected abstract jce callService(String str, Map<String, Object> map, int i, int i2, int i3) throws NSPException;

    protected abstract jce callService(String str, Map<String, Object> map, int i, int i2, int i3, int i4) throws NSPException;

    public String call(String str, Map<String, Object> map, int i, int i2, int i3) throws NSPException {
        jce callService = callService(str, map, i, i2, i3);
        if (callService.b() == null) {
            LogUtil.h(TAG, "call param 5 Request failed.");
            throw new NSPException(callService.d(), "call param 5 Request failed.");
        }
        try {
            return Utils.c(callService.b());
        } catch (Exception e) {
            LogUtil.b(TAG, "call param 5 Parse failed.");
            throw new NSPException(callService.d(), "call param 5 Parse failed.", e);
        }
    }

    public String wifiCall(String str, Map<String, Object> map, int i, int i2, int i3) throws NSPException {
        jce callService = callService(str, map, i, i2, i3, 2);
        if (callService.b() == null) {
            LogUtil.h(TAG, "wifiCall param 5 Request failed.");
            throw new NSPException(callService.d(), "wifiCall param 5 Request failed.");
        }
        try {
            String c = Utils.c(callService.b());
            if (c != null) {
                LogUtil.c(TAG, "wifiCall = ", c.replaceAll("\"devId\":\".*?\"", "\"devId\";\"***\"").replaceAll("\"psk\":\".*?\"", "\"psk\";\"***\"").replaceAll("\"mac\":\".*?\"", "\"mac\";\"***\"").replaceAll("\"sn\":\".*?\"", "\"sn\";\"***\"").replaceAll("\"verifyCode\":\".*?\"", "\"verifyCode\";\"***\""));
            }
            return c;
        } catch (Exception e) {
            LogUtil.b(TAG, "wifiCall param 5 Parse failed.");
            throw new NSPException(callService.d(), "wifiCall param 5 Parse failed.", e);
        }
    }

    protected jce request(Context context, String str, String str2, int i, int i2, int i3) throws NSPException {
        kub kubVar;
        HttpClient httpClient;
        try {
            if (i3 == 2) {
                httpClient = null;
                kubVar = HttpConnectionAdaptor.getLimitedHttpClient(context, str);
            } else {
                kubVar = null;
                httpClient = HttpConnectionAdaptor.getHttpClient(context, str);
            }
            HttpPost httpPost = HttpConnectionAdaptor.getHttpPost(str, i, i2, false);
            HttpHost httpHost = HttpConnectionAdaptor.getHttpHost(str);
            if (httpPost == null) {
                throw new NSPException(2, "Service unavailable.");
            }
            httpPost.addHeader(j2.v, "gzip");
            httpPost.addHeader("Content-Encoding", "gzip");
            setHttpHeader(context, httpPost);
            jce jceVar = new jce();
            try {
                try {
                    try {
                        try {
                            try {
                                sendHttpRequest(kubVar, null, httpPost, str2, httpClient, jceVar, httpHost, i3);
                                checkResponseCode(jceVar);
                                return jceVar;
                            } catch (IOException e) {
                                LogUtil.b(TAG, "request param 6 Service IOException ", e.getMessage());
                                throw new NSPException(6, NSPException.EXP_NET_ERROR_STR, e);
                            }
                        } catch (NullPointerException e2) {
                            LogUtil.b(TAG, "request param 6 Service NullPointerException ", e2.getMessage());
                            throw new NSPException(6, NSPException.EXP_NET_ERROR_STR, e2);
                        }
                    } catch (RuntimeException e3) {
                        throw e3;
                    }
                } catch (Exception e4) {
                    LogUtil.b(TAG, "request param 6 Service unavailable Exception");
                    throw new NSPException(6, NSPException.EXP_NET_ERROR_STR, e4);
                }
            } finally {
                if (!httpPost.isAborted()) {
                    httpPost.abort();
                }
            }
        } catch (Exception e5) {
            LogUtil.b(TAG, "request getHttpClient Exception");
            throw new NSPException(6, NSPException.EXP_NET_ERROR_STR, e5);
        }
    }

    private void setHttpHeader(Context context, HttpPost httpPost) throws NSPException {
        String e = KeyValDbManager.b(context).e("user_id");
        httpPost.setHeader("x-huid", e);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h(TAG, "setHttpHeader huid isEmpty");
            LoginInit.getInstance(context).moveInfoFromSPTODB();
            throw new NSPException(1, "huid is empty.");
        }
        if ("0".equals(e)) {
            LogUtil.b(TAG, "setHttpHeader huid is 0");
            throw new NSPException(1, "huid is 0.");
        }
        httpPost.setHeader("x-version", dealAiLifeBetaName(context));
        httpPost.setHeader("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        httpPost.setHeader("Connection", w9.j);
    }

    private String dealAiLifeBetaName(Context context) {
        String c = CommonUtil.c(context);
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "key_download_config");
        return (b == null || !b.contains("lfhealthtest2")) ? c : "and_health_10.1.1.999";
    }

    private void sendHttpRequest(kub kubVar, HttpResponse httpResponse, HttpPost httpPost, String str, HttpClient httpClient, jce jceVar, HttpHost httpHost, int i) throws Exception {
        HttpResponse execute;
        if (str == null) {
            LogUtil.b(TAG, "sendHttpRequest,request data is null!");
            return;
        }
        httpPost.setEntity(new ByteArrayEntity(lql.e(str.getBytes("UTF-8"))));
        if (i == 2) {
            execute = kubVar.a(httpHost, httpPost);
        } else {
            execute = !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpPost) : ApacheClientInstrumentation.execute(httpClient, httpHost, httpPost);
        }
        LogUtil.a(TAG, "httpResponse status =", Integer.valueOf(execute.getStatusLine().getStatusCode()));
        if (execute.getStatusLine().getStatusCode() == 200 || execute.getStatusLine().getStatusCode() == 302) {
            if (execute.getEntity() != null) {
                jceVar.b(parseHttpResponse(execute));
            } else {
                LogUtil.h(TAG, "httpResponse.getEntity is null");
            }
        }
    }

    private void checkResponseCode(jce jceVar) throws NSPException {
        if (jceVar.a() > 0) {
            String c = Utils.c(jceVar.b());
            if (c == null) {
                throw new NSPException(jceVar.a(), "Unknown error");
            }
            throw new NSPException(jceVar.a(), c);
        }
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00b9: MOVE (r5 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:36:0x00b9 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[Catch: IOException -> 0x007d, IllegalStateException -> 0x007f, Exception -> 0x0084, all -> 0x00b8, LOOP:0: B:18:0x006d->B:21:0x0074, LOOP_END, TryCatch #3 {all -> 0x00b8, blocks: (B:19:0x006d, B:21:0x0074, B:23:0x0078, B:28:0x0090, B:30:0x00a2, B:32:0x0084), top: B:7:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0078 A[EDGE_INSN: B:22:0x0078->B:23:0x0078 BREAK  A[LOOP:0: B:18:0x006d->B:21:0x0074], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected byte[] parseHttpResponse(org.apache.http.HttpResponse r10) {
        /*
            r9 = this;
            org.apache.http.HttpEntity r10 = r10.getEntity()
            java.lang.String r0 = "UIME_AbsNspClient"
            r1 = 0
            if (r10 != 0) goto L15
            java.lang.String r10 = "parseHttpResponse entity = null."
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r10)
            byte[] r10 = new byte[r1]
            return r10
        L15:
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>()
            r3 = 2
            r4 = 1
            r5 = 0
            org.apache.http.Header r6 = r10.getContentEncoding()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            if (r6 == 0) goto L28
            java.lang.String r6 = r6.getValue()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            goto L29
        L28:
            r6 = r5
        L29:
            java.lang.String r7 = "gzip"
            boolean r7 = r7.equals(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            if (r7 != 0) goto L52
            java.lang.String r7 = "z"
            boolean r6 = r7.equals(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            if (r6 == 0) goto L3b
            goto L52
        L3b:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.lang.String r7 = "parseHttpResponse Gzip not support"
            r6[r1] = r7     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            com.huawei.hwlogsmodel.LogUtil.c(r0, r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.io.InputStream r10 = r10.getContent()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            r7.<init>(r10)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            goto L6d
        L52:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.lang.String r7 = "parseHttpResponse Gzip support"
            r6[r1] = r7     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            com.huawei.hwlogsmodel.LogUtil.c(r0, r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            r6 = 8192(0x2000, float:1.148E-41)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.util.zip.GZIPInputStream r8 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            java.io.InputStream r10 = r10.getContent()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            r8.<init>(r10)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83 java.io.IOException -> L8e java.lang.IllegalStateException -> La0
        L6d:
            int r10 = r7.read(r6)     // Catch: java.io.IOException -> L7d java.lang.IllegalStateException -> L7f java.lang.Exception -> L84 java.lang.Throwable -> Lb8
            r8 = -1
            if (r10 == r8) goto L78
            r2.write(r6, r1, r10)     // Catch: java.io.IOException -> L7d java.lang.IllegalStateException -> L7f java.lang.Exception -> L84 java.lang.Throwable -> Lb8
            goto L6d
        L78:
            byte[] r5 = r2.toByteArray()     // Catch: java.io.IOException -> L7d java.lang.IllegalStateException -> L7f java.lang.Exception -> L84 java.lang.Throwable -> Lb8
            goto Lb1
        L7d:
            r10 = move-exception
            goto L90
        L7f:
            r10 = move-exception
            goto La2
        L81:
            r10 = move-exception
            goto Lba
        L83:
            r7 = r5
        L84:
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r3 = "parseHttpResponse AbsNspClient Exception"
            r10[r1] = r3     // Catch: java.lang.Throwable -> Lb8
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)     // Catch: java.lang.Throwable -> Lb8
            goto Lb1
        L8e:
            r10 = move-exception
            r7 = r5
        L90:
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r6 = "parseHttpResponse2 error:"
            r3[r1] = r6     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r10 = r10.getMessage()     // Catch: java.lang.Throwable -> Lb8
            r3[r4] = r10     // Catch: java.lang.Throwable -> Lb8
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> Lb8
            goto Lb1
        La0:
            r10 = move-exception
            r7 = r5
        La2:
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r6 = "parseHttpResponse1 error:"
            r3[r1] = r6     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r10 = r10.getMessage()     // Catch: java.lang.Throwable -> Lb8
            r3[r4] = r10     // Catch: java.lang.Throwable -> Lb8
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> Lb8
        Lb1:
            com.huawei.haf.common.os.FileUtils.d(r7)
            com.huawei.haf.common.os.FileUtils.d(r2)
            return r5
        Lb8:
            r10 = move-exception
            r5 = r7
        Lba:
            com.huawei.haf.common.os.FileUtils.d(r5)
            com.huawei.haf.common.os.FileUtils.d(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwcloudmodel.utils.AbsNspClient.parseHttpResponse(org.apache.http.HttpResponse):byte[]");
    }
}
