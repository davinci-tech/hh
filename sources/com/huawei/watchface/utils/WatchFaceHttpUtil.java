package com.huawei.watchface.utils;

import android.app.Application;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.picture.security.account.bean.HitopRespInfo;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.b;
import com.huawei.watchface.bo;
import com.huawei.watchface.cv;
import com.huawei.watchface.dg;
import com.huawei.watchface.dp;
import com.huawei.watchface.eh;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eu;
import com.huawei.watchface.ev;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class WatchFaceHttpUtil {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String HTTP_CONFIG_JSON = "http_config_json";
    private static final HashMap<String, WatchFaceSignBean> c = new HashMap<>(32);

    /* renamed from: a, reason: collision with root package name */
    public static final Map<String, String> f11198a = new ConcurrentHashMap(32);
    public static final ExecutorService b = Executors.newCachedThreadPool();
    private static Map<String, String> d = new ConcurrentHashMap();

    public static boolean a(int i) {
        return 90200008 == i;
    }

    private WatchFaceHttpUtil() {
    }

    public static WatchFaceSignBean a() {
        String a2 = LanguageUtils.a(false);
        String deviceModel = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel();
        HwLog.i("WatchFaceHttpUtil", "getSignBean() language: " + a2 + ", getSignBean deviceName:" + deviceModel);
        WatchFaceSignBean watchFaceSignBean = c.get(a2 + deviceModel);
        if (watchFaceSignBean != null) {
            return watchFaceSignBean;
        }
        HwLog.i("WatchFaceHttpUtil", "getSignBean() sign is null, get again");
        Object D = D();
        if (!(D instanceof WatchFaceSignBean)) {
            return watchFaceSignBean;
        }
        HwLog.i("WatchFaceHttpUtil", "getSignBean() getSignBeanAgain success");
        return (WatchFaceSignBean) D;
    }

    public static WatchFaceSignBean b() {
        String a2 = LanguageUtils.a(false);
        String deviceModel = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel();
        HwLog.i("WatchFaceHttpUtil", "getSignBean language:" + a2 + " getSignBean deviceName:" + deviceModel);
        return c.get(a2 + deviceModel);
    }

    public static WatchFaceSignBean c() {
        String a2 = LanguageUtils.a(false);
        String deviceName = MobileInfoHelper.getDeviceName();
        HwLog.i("WatchFaceHttpUtil", "getPhoneTypeSignBeanNoFutureTask language:" + a2 + "deviceName:" + deviceName);
        return c.get(a2 + deviceName);
    }

    private static Object D() {
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.huawei.watchface.utils.WatchFaceHttpUtil$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Object E;
                E = WatchFaceHttpUtil.E();
                return E;
            }
        });
        b.execute(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException unused) {
            HwLog.e("WatchFaceHttpUtil", "getSignBeanAgain InterruptedException!");
            return null;
        } catch (ExecutionException unused2) {
            HwLog.e("WatchFaceHttpUtil", "getSignBeanAgain ExecutionException!");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object E() throws Exception {
        HwLog.i("WatchFaceHttpUtil", "getSignBeanAgain watchFaceSignBean is null.");
        bo boVar = new bo();
        return boVar.c(boVar.c());
    }

    public static void a(WatchFaceSignBean watchFaceSignBean) {
        String a2 = LanguageUtils.a(false);
        String deviceModel = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel();
        HwLog.i("WatchFaceHttpUtil", "setSignBean language:" + a2 + "setSignBean deviceName:" + deviceModel);
        HashMap<String, WatchFaceSignBean> hashMap = c;
        hashMap.put(a2 + deviceModel, watchFaceSignBean);
        if (watchFaceSignBean != null) {
            hashMap.put(CommonConstant.StartQrLoginQrValue.QRSCENE_DEFAULT + deviceModel, watchFaceSignBean);
            return;
        }
        HwLog.e("WatchFaceHttpUtil", "signBean is null.");
    }

    public static void b(WatchFaceSignBean watchFaceSignBean) {
        String a2 = LanguageUtils.a(false);
        String deviceName = MobileInfoHelper.getDeviceName();
        HwLog.i("WatchFaceHttpUtil", "setGreySignBean language:" + a2 + "setGreySignBean deviceName:" + deviceName);
        HashMap<String, WatchFaceSignBean> hashMap = c;
        hashMap.put(a2 + deviceName, watchFaceSignBean);
        if (watchFaceSignBean != null) {
            hashMap.put(CommonConstant.StartQrLoginQrValue.QRSCENE_DEFAULT + deviceName, watchFaceSignBean);
            return;
        }
        HwLog.e("WatchFaceHttpUtil", "signBean is null.");
    }

    public static String d() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getSignUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/service/getFrontParam.do?";
    }

    public static String e() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getWatchFaceUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/v2/getThemeList.do?";
    }

    public static String f() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getDesignerUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/getDeveloper.do?";
    }

    public static String g() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getSystemParamUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/getSystemParam.do?";
    }

    public static String h() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getOnlineStateUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/getSupportOnlineInfo.do?";
    }

    public static String i() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getQueryAll() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/ttdservice/service/v1/subscription/queryall?";
    }

    public static String j() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getBatchReportUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/tis/service/v1/usrbehavior/applyrecord/batchreport.do?";
    }

    public static String a(String str, String str2, LinkedHashMap<String, String> linkedHashMap, Class cls) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            eh ehVar = new eh();
            ehVar.d();
            ehVar.c(str);
            ehVar.a(cls);
            ehVar.d(str2);
            a((Map<String, String>) linkedHashMap);
            ehVar.a(linkedHashMap);
            ev a2 = eu.a().a(str, str2, linkedHashMap, b(linkedHashMap), ehVar);
            str3 = a2 != null ? a2.a() : null;
            if (!n.a(Environment.getApplicationContext()).i()) {
                if (n.a(Environment.getApplicationContext()).a(new HitopRespInfo(str, f(str3), str3))) {
                    dg.a((Map<String, String>) linkedHashMap, "usertoken", n.a(Environment.getApplicationContext()).f());
                    n.a(Environment.getApplicationContext()).c(true);
                    return a(str, str2, linkedHashMap, cls);
                }
            }
            ehVar.e(str3);
            ehVar.a(a2 != null ? a2.b() : null);
            ehVar.e();
            ehVar.a_();
        }
        FlavorConfig.printNetworkLog(str, str2, GsonHelper.toJson(linkedHashMap), str3);
        return str3;
    }

    private static String b(LinkedHashMap<String, String> linkedHashMap) {
        if (!dg.a((Map<?, ?>) linkedHashMap, (Object) HTTP_CONFIG_JSON)) {
            return null;
        }
        String a2 = dg.a((Map<String, String>) linkedHashMap, HTTP_CONFIG_JSON);
        dg.b(linkedHashMap, HTTP_CONFIG_JSON);
        return a2;
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("resultcode");
            if (TextUtils.isEmpty(str2)) {
                str2 = jSONObject.optString("resultCode");
            }
        } catch (Exception e) {
            HwLog.e("WatchFaceHttpUtil", "getResultCode fail:" + HwLog.printException(e));
        }
        return str2 == null ? "" : str2;
    }

    private static void a(HttpURLConnection httpURLConnection, LinkedHashMap<String, String> linkedHashMap) {
        if (httpURLConnection == null || ArrayUtils.a(linkedHashMap)) {
            return;
        }
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            try {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            } catch (IllegalArgumentException e) {
                HwLog.i("WatchFaceHttpUtil", "generatePenetrateHeaders() e: " + HwLog.printException((Exception) e));
            }
        }
        HwLog.i("WatchFaceHttpUtil", "generatePenetrateHeaders");
    }

    private static String a(InputStream inputStream) {
        if (inputStream == null) {
            HwLog.e("WatchFaceHttpUtil", "getResponseLine inputStream is null");
            return "";
        }
        return a(inputStream, "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.BufferedReader] */
    private static String a(InputStream inputStream, String str) {
        ?? r2;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        InputStreamReader inputStreamReader3;
        ?? bufferedReader;
        char c2;
        InputStreamReader inputStreamReader4 = null;
        try {
            try {
                inputStreamReader3 = new InputStreamReader(inputStream, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader3);
                } catch (UnsupportedEncodingException unused) {
                } catch (IOException unused2) {
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                StringBuffer stringBuffer = new StringBuffer(32);
                while (true) {
                    int read = bufferedReader.read();
                    if (read == -1 || (c2 = (char) read) == '\n') {
                        break;
                    }
                    if (stringBuffer.length() >= 104857600) {
                        throw new IllegalStateException("input too long");
                    }
                    stringBuffer.append(c2);
                }
                str = stringBuffer.toString();
                a(inputStreamReader3, (BufferedReader) bufferedReader, inputStream);
            } catch (UnsupportedEncodingException unused3) {
                inputStreamReader4 = bufferedReader;
                inputStreamReader2 = inputStreamReader4;
                inputStreamReader4 = inputStreamReader3;
                HwLog.e("WatchFaceHttpUtil", "getResponseLine doRequest UnsupportedEncodingException!");
                r2 = inputStreamReader2;
                a(inputStreamReader4, (BufferedReader) r2, inputStream);
                return str;
            } catch (IOException unused4) {
                inputStreamReader4 = bufferedReader;
                inputStreamReader = inputStreamReader4;
                inputStreamReader4 = inputStreamReader3;
                HwLog.e("WatchFaceHttpUtil", "getResponseLine doRequest IOException!");
                r2 = inputStreamReader;
                a(inputStreamReader4, (BufferedReader) r2, inputStream);
                return str;
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader4 = bufferedReader;
                r2 = inputStreamReader4;
                inputStreamReader4 = inputStreamReader3;
                a(inputStreamReader4, (BufferedReader) r2, inputStream);
                throw th;
            }
        } catch (UnsupportedEncodingException unused5) {
            inputStreamReader2 = null;
        } catch (IOException unused6) {
            inputStreamReader = null;
        } catch (Throwable th4) {
            th = th4;
            r2 = 0;
        }
        return str;
    }

    private static void a(InputStreamReader inputStreamReader, BufferedReader bufferedReader, InputStream inputStream) {
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException unused) {
                HwLog.e("WatchFaceHttpUtil", "closeStream doRequest close InputStreamReader error!");
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                HwLog.e("WatchFaceHttpUtil", "closeStream doRequest close BufferedReader error!");
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused3) {
                HwLog.e("WatchFaceHttpUtil", "closeStream doRequest close response error!");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] a(java.lang.String r2, boolean r3, javax.net.ssl.HttpsURLConnection r4, javax.net.ssl.HostnameVerifier r5) {
        /*
            java.lang.String r0 = "WatchFaceHttpUtil"
            if (r2 == 0) goto L10
            java.lang.String r1 = "UTF-8"
            byte[] r2 = r2.getBytes(r1)     // Catch: java.io.UnsupportedEncodingException -> Lb
            goto L11
        Lb:
            java.lang.String r2 = "getData UnsupportedEncodingException"
            com.huawei.watchface.utils.HwLog.e(r0, r2)
        L10:
            r2 = 0
        L11:
            if (r2 != 0) goto L19
            java.lang.String r3 = "getData null post dataInfos!"
            com.huawei.watchface.utils.HwLog.e(r0, r3)
            return r2
        L19:
            r4.setHostnameVerifier(r5)
            a(r4, r2)
            java.lang.String r5 = "getData set end"
            com.huawei.watchface.utils.HwLog.i(r0, r5)
            if (r3 != 0) goto L2d
            java.lang.String r3 = "Accept-Encoding"
            java.lang.String r5 = "identity"
            r4.setRequestProperty(r3, r5)
        L2d:
            java.lang.String r3 = "getData setRequestProperty jsonObject already!"
            com.huawei.watchface.utils.HwLog.i(r0, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.WatchFaceHttpUtil.a(java.lang.String, boolean, javax.net.ssl.HttpsURLConnection, javax.net.ssl.HostnameVerifier):byte[]");
    }

    private static InputStream a(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (UnsupportedEncodingException unused) {
            HwLog.e("WatchFaceHttpUtil", "getInputStream UnsupportedEncodingException");
            return null;
        } catch (IOException unused2) {
            HwLog.e("WatchFaceHttpUtil", "getInputStream IOException");
            return null;
        }
    }

    private static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException unused) {
                HwLog.e("WatchFaceHttpUtil", "closeOutputStream IOException");
            }
        }
    }

    public static String a(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        if (str == null || str2 == null) {
            HwLog.e("WatchFaceHttpUtil", "doHttpsPost url or data is null.");
            return null;
        }
        return a(str, str2, false, linkedHashMap);
    }

    private static String a(String str, String str2, boolean z, LinkedHashMap<String, String> linkedHashMap) {
        try {
            URL url = new URL(str);
            try {
                HttpsURLConnection httpsURLConnection = URLConnectionInstrumentation.openConnection(url.openConnection()) instanceof HttpsURLConnection ? (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection()) : null;
                HwLog.i("WatchFaceHttpUtil", "doPostRequest (HttpsURLConnection)url.openConnection();");
                if (httpsURLConnection == null) {
                    HwLog.e("WatchFaceHttpUtil", "doPostRequest urlConnection is null.");
                    return null;
                }
                if (a(httpsURLConnection)) {
                    return null;
                }
                try {
                    httpsURLConnection.setRequestMethod("POST");
                    if (str.contains("servicesupport/theme/service/v2/images/file/create?")) {
                        httpsURLConnection.setConnectTimeout(5000);
                        httpsURLConnection.setReadTimeout(5000);
                    }
                    a(httpsURLConnection, linkedHashMap);
                    HwLog.i("WatchFaceHttpUtil", "doPostRequest setRequestMethod(HTTP_POST);!");
                    byte[] a2 = a(str2, z, httpsURLConnection, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                    if (a2 == null) {
                        HwLog.e("WatchFaceHttpUtil", "doPostRequest dataInfos is null.");
                        return null;
                    }
                    InputStream a3 = a(a2, httpsURLConnection);
                    String a4 = a(a3);
                    if (a3 != null) {
                        try {
                            a3.close();
                        } catch (IOException unused) {
                            HwLog.e("WatchFaceHttpUtil", "doPostRequest close response IOException error!");
                        }
                    }
                    return a4;
                } catch (ProtocolException unused2) {
                    HwLog.e("WatchFaceHttpUtil", "doPostRequest setRequestMethod failed");
                    return null;
                }
            } catch (IOException unused3) {
                HwLog.e("WatchFaceHttpUtil", "doPostRequest openConnection failed");
                return null;
            }
        } catch (MalformedURLException unused4) {
            HwLog.e("WatchFaceHttpUtil", "doPostRequest MalformedURLException");
            return null;
        }
    }

    private static boolean a(HttpsURLConnection httpsURLConnection) {
        try {
            httpsURLConnection.setSSLSocketFactory(SSFCompatiableSystemCA.getInstance(Environment.getApplicationContext(), EncryptUtil.genSecureRandom()));
            HwLog.i("WatchFaceHttpUtil", "SecureSSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER !");
            return false;
        } catch (IOException unused) {
            HwLog.e("WatchFaceHttpUtil", "setScoketFactoryFunc IOException");
            return true;
        } catch (KeyManagementException unused2) {
            HwLog.e("WatchFaceHttpUtil", "setScoketFactoryFunc KeyStoreException");
            return true;
        } catch (KeyStoreException unused3) {
            HwLog.e("WatchFaceHttpUtil", "setScoketFactoryFunc KeyManagementException");
            return true;
        } catch (NoSuchAlgorithmException unused4) {
            HwLog.e("WatchFaceHttpUtil", "setScoketFactoryFunc NoSuchAlgorithmException");
            return true;
        } catch (CertificateException unused5) {
            HwLog.e("WatchFaceHttpUtil", "setScoketFactoryFunc CertificateException");
            return true;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0039: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:16:0x0039 */
    private static InputStream a(byte[] bArr, HttpsURLConnection httpsURLConnection) {
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3 = null;
        try {
            try {
                httpsURLConnection.connect();
                HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream connect");
                outputStream = httpsURLConnection.getOutputStream();
                try {
                    HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream getoutput");
                    outputStream.write(bArr);
                    HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream write");
                    HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream finally closeOutputStream!");
                    a(outputStream);
                    return a((HttpURLConnection) httpsURLConnection);
                } catch (IOException unused) {
                    HwLog.e("WatchFaceHttpUtil", "getHttpsResponseStream IOException");
                    HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream finally closeOutputStream!");
                    a(outputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                outputStream3 = outputStream2;
                HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream finally closeOutputStream!");
                a(outputStream3);
                throw th;
            }
        } catch (IOException unused2) {
            outputStream = null;
        } catch (Throwable th2) {
            th = th2;
            HwLog.i("WatchFaceHttpUtil", "getHttpsResponseStream finally closeOutputStream!");
            a(outputStream3);
            throw th;
        }
    }

    public static boolean a(String str) {
        int indexOf = str.indexOf(":");
        return indexOf != -1 && "http".equalsIgnoreCase(SafeString.substring(str, 0, indexOf));
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        String requestProperty = httpURLConnection.getRequestProperty("Content-Type");
        HwLog.i("WatchFaceHttpUtil", "setUrlConnectionCommon() contentType: " + requestProperty);
        if (TextUtils.isEmpty(requestProperty)) {
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        }
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
        httpURLConnection.setRequestProperty("Connection", w9.j);
        String valueOf = String.valueOf(cv.e());
        httpURLConnection.setRequestProperty("x-brandChannel", valueOf);
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext != null) {
            String accountBrand = HwWatchFaceApi.getInstance(applicationContext).getAccountBrand();
            httpURLConnection.setRequestProperty("x-accountBrand", accountBrand);
            String packageName = applicationContext.getPackageName();
            httpURLConnection.setRequestProperty("x-packagename", packageName);
            HwLog.i("WatchFaceHttpUtil", "setUrlConnectionCommon() accountBrand: " + accountBrand + ", pkgName: " + packageName);
        }
        String valueOf2 = String.valueOf(cv.c());
        httpURLConnection.setRequestProperty("x-appBrand", valueOf2);
        HwLog.i("WatchFaceHttpUtil", "setUrlConnectionCommon() brandChannel: " + valueOf + ", appBrandId: " + valueOf2);
    }

    public static Map<String, String> k() {
        Map<String, String> map = f11198a;
        if (dg.isEmpty(map)) {
            dg.a(map, "x-brandChannel", String.valueOf(cv.e()));
            dg.a(map, "x-appBrand", String.valueOf(cv.c()));
            dg.a(map, "x-packagename", Environment.b());
        }
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext != null) {
            dg.a(map, "x-accountBrand", HwWatchFaceApi.getInstance(applicationContext).getAccountBrand());
        }
        return map;
    }

    public static String l() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getWatchFaceUrlBase() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/";
    }

    public static String m() {
        return b("watchFace");
    }

    public static String b(final String str) {
        try {
            FutureTask futureTask = new FutureTask(new Callable() { // from class: com.huawei.watchface.utils.WatchFaceHttpUtil$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    String g;
                    g = WatchFaceHttpUtil.g(str);
                    return g;
                }
            });
            b.execute(futureTask);
            return (String) futureTask.get();
        } catch (InterruptedException unused) {
            HwLog.e("WatchFaceHttpUtil", "getBaseUrl InterruptedException!");
            return "";
        } catch (ExecutionException unused2) {
            HwLog.e("WatchFaceHttpUtil", "getBaseUrl ExecutionException!");
            return "";
        } catch (Exception unused3) {
            HwLog.e("WatchFaceHttpUtil", "getBaseUrl Exception!");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String g(String str) throws Exception {
        String commonCountryCode = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode();
        String str2 = str + "_" + commonCountryCode;
        if (!TextUtils.isEmpty(d.get(str2))) {
            HwLog.d("WatchFaceHttpUtil", "get getBaseUrl from grsUrlMap");
            return d.get(str2);
        }
        HwLog.i("WatchFaceHttpUtil", "get getNoCheckUrl");
        String a2 = b.a().a(str, commonCountryCode);
        d.put(str2, a2);
        return a2;
    }

    private static void a(Map<String, String> map) {
        if (map == null) {
            new LinkedHashMap();
            return;
        }
        if (ArrayUtils.a(map)) {
            return;
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String key = next.getKey();
            String value = next.getValue();
            if (key == null || value == null) {
                it.remove();
            }
        }
    }

    public static String a(LinkedHashMap<String, ?> linkedHashMap) {
        if (ArrayUtils.a(linkedHashMap)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, ?> entry : linkedHashMap.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                HwLog.i("WatchFaceHttpUtil", "convertMapParamsToJson: " + HwLog.printException((Exception) e));
            }
        }
        return jSONObject.toString();
    }

    public static void n() {
        HashMap<String, WatchFaceSignBean> hashMap = c;
        if (hashMap == null) {
            return;
        }
        hashMap.clear();
    }

    public static String c(String str) {
        HwLog.i("WatchFaceHttpUtil", "getWatchFaceH5Url() countryCode: " + str);
        String j = dp.j(Environment.getApplicationContext(), str);
        if (!TextUtils.isEmpty(j)) {
            return j;
        }
        HwLog.i("WatchFaceHttpUtil", "getWatchFaceH5Url() url is empty.");
        String a2 = b.a().a("watchFaceH5", str);
        dp.b(Environment.getApplicationContext(), str, a2);
        return a2;
    }

    public static String d(String str) {
        HwLog.i("WatchFaceHttpUtil", "getWatchFaceUrl() countryCode: " + str);
        String i = dp.i(Environment.getApplicationContext(), str);
        if (!TextUtils.isEmpty(i)) {
            return i;
        }
        HwLog.i("WatchFaceHttpUtil", "getWatchFaceUrl() url is empty.");
        String a2 = b.a().a("watchFace", str);
        dp.a(Environment.getApplicationContext(), str, a2);
        return a2;
    }

    public static String o() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getTryOutWatchFaceInfoUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/service/v2/experience/apply?";
    }

    public static String p() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getBatchCreateAddurl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/service/v2/order/add?";
    }

    public static String q() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getDownloadQueryUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/service/v2/downloadinfo/query?";
    }

    public static String r() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getDownloadQueryUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/service/v2/downloadinfo/queryfree?";
    }

    public static String s() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getPayResultUrl() baseUrl is null");
            return "";
        }
        return m + "/servicesupport/theme/tis/service/v2/payresult/add?";
    }

    public static String t() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getBatchCreateAddurl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/service/v1/coupons/unlock.do?";
    }

    public static String u() {
        String b2 = b("campaign");
        if (TextUtils.isEmpty(b2)) {
            HwLog.e("WatchFaceHttpUtil", "getPpsAdUploadUrl() baseUrl is null");
            return "";
        }
        return b2 + "music-mcs-service/v2/service/ppsJoinResult/report";
    }

    public static String v() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getBuyHistoryUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/service/v2/getBuySourceByIds";
    }

    public static String getFileCreateResourceUrl() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getFileCreateResourceUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/service/v2/images/file/create?";
    }

    public static String getPortraitDetachedUrl() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getPortraitDetachedUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/service/v1/portrait/detached?";
    }

    public static String getPortraitDeleteUrl() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getPortraitDeleteUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/service/v2/images/file/delete?";
    }

    public static String w() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getPersonalizedContentUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/getPersonalizedContent.do?";
    }

    public static String b(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        if (TextUtils.isEmpty(str)) {
            HwLog.e("WatchFaceHttpUtil", "doPost url is empty...");
            return null;
        }
        if (a(str)) {
            str = e(str);
        }
        return a(str, str2, linkedHashMap);
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return SafeString.replace(str, "http:", "https:");
    }

    public static String x() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getThemeAppSignInUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/v1/service/user/signin";
    }

    public static String y() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.e("WatchFaceHttpUtil", "getThemeAppRefreshUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/tis/v1/service/token/refresh";
    }

    public static String z() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.w("WatchFaceHttpUtil", "getJoinVipUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/service/v1/resourceinfo/similar/get";
    }

    public static String A() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.w("WatchFaceHttpUtil", "getJoinVipUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/ttdservice/service/v1/subscription/create";
    }

    public static String B() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.w("WatchFaceHttpUtil", "getJoinVipUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/ttdservice/service/v1/product/querybytype?";
    }

    public static String C() {
        String m = m();
        if (TextUtils.isEmpty(m)) {
            HwLog.w("WatchFaceHttpUtil", "getQueryCouponsUrl() baseUrl is null");
            return "";
        }
        return m + "servicesupport/theme/service/v1/coupons/byproduct.do?";
    }
}
