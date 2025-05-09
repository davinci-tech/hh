package com.huawei.hms.mlsdk.asr.engine.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.ml.grs.GrsListener;
import com.huawei.hms.ml.grs.GrsRegionUtils;
import com.huawei.hms.ml.grs.GrsUtils;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.mlsdk.common.MLApplicationSetting;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class HttpUtils {
    private static final String HMS_PACKAGE_NAME = "com.huawei.hwid";
    private static final String HMS_SERVICE = "com.huawei.hms.core.aidlservice";
    private static final int NET_TIMEOUT_SECONDS = 10000;
    private static final String SUCCESS = "0";
    private static final String TAG = "HttpUtils";
    private static final String TAG_LANGUAGES = "languages";
    private static final String TAG_RETCODE = "retCode";
    private static final String TAG_RETMSG = "retMsg";

    class a implements GrsListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5085a;
        final /* synthetic */ HttpClient b;
        final /* synthetic */ Callback c;

        a(String str, HttpClient httpClient, Callback callback) {
            this.f5085a = str;
            this.b = httpClient;
            this.c = callback;
        }

        @Override // com.huawei.hms.ml.grs.GrsListener
        public void onAsynGrsUrls(List<String> list) {
            if (list != null) {
                StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("requestSpeaker GRS:[");
                a2.append(list.size());
                a2.append("]");
                a2.append(list);
                Logger.i(HttpUtils.TAG, a2.toString(), true);
            }
            StringBuilder a3 = com.huawei.hms.mlsdk.asr.o.a.a((list == null || list.size() <= 0) ? "" : list.get(0));
            a3.append(this.f5085a);
            this.b.newSubmit(HttpUtils.this.buildGetRequest(this.b, HttpUtils.formate(a3.toString()))).enqueue(this.c);
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final HttpUtils f5086a = new HttpUtils(null);
    }

    /* synthetic */ HttpUtils(a aVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Request buildGetRequest(HttpClient httpClient, String str) {
        Request.Builder newRequest = httpClient.newRequest();
        newRequest.url(str);
        SmartLogger.i(TAG, "requestSpeaker domian: " + str);
        for (Map.Entry<String, String> entry : getDefaultHeader().entrySet()) {
            newRequest.addHeader(entry.getKey(), entry.getValue());
        }
        newRequest.method("GET");
        return newRequest.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formate(String str) {
        String trim = str.trim();
        if (!trim.startsWith("http://") && !trim.startsWith("https://")) {
            trim = com.huawei.hms.mlsdk.asr.o.a.a("https://", trim);
        }
        int indexOf = trim.indexOf("//");
        return trim.substring(0, indexOf) + "//" + trim.substring(indexOf + 2).replaceAll("/+", "/");
    }

    private Map<String, String> getDefaultHeader() {
        HashMap hashMap = new HashMap();
        MLApplication mLApplication = MLApplication.getInstance();
        MLApplicationSetting appSetting = mLApplication.getAppSetting();
        if (appSetting == null) {
            return hashMap;
        }
        UUID randomUUID = UUID.randomUUID();
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("X-Request-ID", String.valueOf(randomUUID));
        SmartLogger.i(TAG, "X-Request-ID: " + randomUUID);
        hashMap.put("X-User-Agent", "X-User-Agent");
        hashMap.put("appId", appSetting.getAppId());
        hashMap.put("HMS-APPLICATION-ID", appSetting.getAppId());
        hashMap.put("X-Package-Name", appSetting.getPackageName());
        hashMap.put("X-Country-Code", new c(mLApplication.getAppContext(), false).a());
        hashMap.put("supplierId", "supplierId");
        hashMap.put("accept", "application/json");
        hashMap.put("certFingerprint", appSetting.getCertFingerprint());
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + MLApplication.getInstance().getAuthorizationToken());
        hashMap.put("X-Mlkit-Version", "3.16.7.302");
        SmartLogger.i(TAG, "X-Mlkit-Version: " + appSetting.getMLSdkVersion());
        Pair<String, ResolveInfo> hmsCoreInfo = getHmsCoreInfo();
        if (hmsCoreInfo != null) {
            String str = (String) hmsCoreInfo.first;
            Log.d(TAG, "HMScore PackageName is :" + str);
            hashMap.put("isHmsCore", isPackageInternal(mLApplication.getAppContext(), str) ? "1" : "0");
        } else {
            hashMap.put("isHmsCore", isPackageInternal(mLApplication.getAppContext(), "com.huawei.hwid") ? "1" : "0");
        }
        return hashMap;
    }

    private static Pair<String, ResolveInfo> getHmsCoreInfo() {
        try {
            List<ResolveInfo> queryIntentServices = MLApplication.getInstance().getAppContext().getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core.aidlservice"), 128);
            if (queryIntentServices.size() != 0) {
                Iterator<ResolveInfo> it = queryIntentServices.iterator();
                if (it.hasNext()) {
                    ResolveInfo next = it.next();
                    return new Pair<>(next.serviceInfo.applicationInfo.packageName, next);
                }
            }
            return null;
        } catch (RuntimeException e) {
            SmartLogger.e(TAG, "getHmsCoreInfo RuntimeException e: " + e);
            return null;
        } catch (Exception e2) {
            SmartLogger.e(TAG, "getHmsCoreInfo Exception e: " + e2);
            return null;
        }
    }

    public static final HttpUtils getInstance() {
        return b.f5086a;
    }

    private boolean isPackageInternal(Context context, String str) {
        try {
            return (context.getPackageManager().getPackageInfo(str, 0).applicationInfo.flags & 1) != 0;
        } catch (RuntimeException e) {
            SmartLogger.e(TAG, "isPackageInternal Exception e: " + e);
            return false;
        } catch (Exception e2) {
            SmartLogger.e(TAG, "isPackageInternal Exception e: " + e2);
            return false;
        }
    }

    public HttpClient getOkHttpClient() {
        return getOkHttpClient(10000);
    }

    public boolean isLegalJson(String str) {
        if (TextUtils.isEmpty(str)) {
            SmartLogger.e(TAG, "Language list isEmpty.");
            return false;
        }
        JSONObject parseJson = JsonUtil.parseJson(str);
        if (parseJson == null) {
            SmartLogger.e(TAG, "Response json incorrect format.");
            return false;
        }
        if (!"0".equals(JsonUtil.getJsonStringField(parseJson, "retCode"))) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("Response error! errcode=[");
            a2.append(JsonUtil.getJsonStringField(parseJson, "retCode"));
            a2.append("] retMsg=[");
            a2.append(JsonUtil.getJsonStringField(parseJson, TAG_RETMSG));
            a2.append("]");
            SmartLogger.e(TAG, a2.toString());
            return false;
        }
        try {
            JSONArray jsonArray = JsonUtil.getJsonArray(parseJson.getJSONObject("result"), TAG_LANGUAGES);
            if (jsonArray != null && jsonArray.length() != 0) {
                return true;
            }
            SmartLogger.e(TAG, "Response json not contain languages");
            return false;
        } catch (JSONException e) {
            SmartLogger.e(TAG, "JSONException", e);
            return true;
        }
    }

    public void requestSupportLanguages(HttpClient httpClient, String str, Callback callback) {
        GrsUtils.setGrsListener(new a(str, httpClient, callback));
        String grsCountryCode = GrsRegionUtils.getGrsCountryCode();
        SmartLogger.i(TAG, "Grs-CountryCode:" + grsCountryCode);
        if (grsCountryCode != null) {
            GrsUtils.getAsrVisionSearchUrls(GrsUtils.initGrsVisionSearchClientWithCountry(MLApplication.getInstance().getAppContext(), grsCountryCode));
        } else {
            GrsUtils.getAsrUrls(MLApplication.getInstance().getAppContext(), false);
        }
    }

    private HttpUtils() {
    }

    public HttpClient getOkHttpClient(int i) {
        Context appContext = MLApplication.getInstance().getAppContext();
        HttpClient.Builder builder = new HttpClient.Builder();
        try {
            builder.sslSocketFactory((SSLSocketFactory) SecureSSLSocketFactoryNew.getInstance(appContext, new SecureRandom()), (X509TrustManager) new SecureX509TrustManager(appContext));
        } catch (IOException e) {
            SmartLogger.e(TAG, e.getMessage());
        } catch (IllegalAccessException e2) {
            SmartLogger.e(TAG, e2.getMessage());
        } catch (KeyManagementException e3) {
            SmartLogger.e(TAG, e3.getMessage());
        } catch (KeyStoreException e4) {
            SmartLogger.e(TAG, e4.getMessage());
        } catch (NoSuchAlgorithmException e5) {
            SmartLogger.e(TAG, e5.getMessage());
        } catch (CertificateException e6) {
            SmartLogger.e(TAG, e6.getMessage());
        }
        builder.hostnameVerifier((HostnameVerifier) SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER).connectTimeout(i).callTimeout(i).readTimeout(i);
        NetworkKit.getInstance().initConnectionPool(5, 5L, TimeUnit.SECONDS);
        return builder.build();
    }
}
