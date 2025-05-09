package defpackage;

import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.GRSManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pvr {

    /* renamed from: a, reason: collision with root package name */
    private static String f16277a = null;
    private static final Object b = new Object();
    private static String c = null;
    private static String d = "case_phone";
    private static String e;
    private static String f;
    private static String g;

    static {
        try {
            e = cpp.a().getFilesDir().getCanonicalPath() + File.separator + "healthdevice" + File.separator;
        } catch (IOException e2) {
            LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e2.getMessage());
        }
        c = "case_phone.xml";
        f = RecommendConstants.VER;
        g = RecommendConstants.FILE_ID;
        f16277a = RecommendConstants.DOWNLOAD_URL;
    }

    public static void d(final CasePhoneDownloadListener casePhoneDownloadListener) {
        LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "DeviceCloudUtil toDownloadSingleXmlFile()");
        if (casePhoneDownloadListener == null) {
            LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "downloadCasePhoneXml resultCallback is null");
            return;
        }
        LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "to download case_phone.xml");
        String c2 = c(d);
        final String str = e + c;
        LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "File Path = ", str);
        e(GRSManager.a(BaseApplication.getContext()).getUrl("getLatestVersion"), c2, new CasePhoneDownloadListener() { // from class: pvr.5
            @Override // com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener
            public void onResult(int i, String str2) {
                if (i == 200) {
                    LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "dowloadCasePhoneXml rescode = ", Integer.valueOf(i), " resultValue = ", str2);
                    pvr.e(pvr.d(str2), str, casePhoneDownloadListener);
                }
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener
            public void onFailed(int i, String str2) {
                casePhoneDownloadListener.onFailed(-1, str2);
            }
        });
    }

    public static pvq d(String str) {
        LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "DataManager parse single Result");
        pvq pvqVar = new pvq();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(g)) {
                pvqVar.d(jSONObject.getString(g));
            }
            if (!jSONObject.isNull(f)) {
                pvqVar.e(jSONObject.getString(f));
            }
            if (!jSONObject.isNull(f16277a)) {
                pvqVar.a(jSONObject.getString(f16277a));
            }
        } catch (JSONException e2) {
            LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e2.getMessage());
        }
        return pvqVar;
    }

    public static String c(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RecommendConstants.FILE_ID, str);
            jSONObject.put(RecommendConstants.VER, "0");
        } catch (JSONException e2) {
            LogUtil.h("PluginDevice_CasePhoneDownloadUtil", e2.getMessage());
        }
        return jSONObject.toString();
    }

    public static void e(final String str, final String str2, final CasePhoneDownloadListener casePhoneDownloadListener) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginDevice_CasePhoneDownloadUtil", "toFresh parameter is null");
            casePhoneDownloadListener.onFailed(-1, "toFresh parameter is null");
        } else {
            Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: pvr.4
                @Override // java.lang.Runnable
                public void run() {
                    pvr.c(str, str2, casePhoneDownloadListener);
                }
            });
        }
    }

    public static void c(String str, String str2, CasePhoneDownloadListener casePhoneDownloadListener) {
        LogUtil.c("PluginDevice_CasePhoneDownloadUtil", "postRequest url servicesupport/updateserver/getLatestVersion");
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith(ProxyConfig.MATCH_HTTPS)) {
                d(str, str2, casePhoneDownloadListener);
                return;
            } else {
                a(str, str2, casePhoneDownloadListener);
                return;
            }
        }
        LogUtil.h("PluginDevice_CasePhoneDownloadUtil", "postRequest httpUrl is null ");
        casePhoneDownloadListener.onFailed(-1, "postRequest httpUrl is null ");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0092 A[Catch: all -> 0x0105, IOException -> 0x010d, MalformedURLException -> 0x0113, TRY_LEAVE, TryCatch #19 {MalformedURLException -> 0x0113, IOException -> 0x010d, all -> 0x0105, blocks: (B:14:0x007f, B:16:0x0092), top: B:13:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(java.lang.String r17, java.lang.String r18, com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener r19) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pvr.d(java.lang.String, java.lang.String, com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener):void");
    }

    private static void e(CasePhoneDownloadListener casePhoneDownloadListener, int i, String str, OutputStream outputStream) {
        if (outputStream != null) {
            LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "httpsFinally2 outStream not null");
            try {
                outputStream.close();
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "httpsFinally2 outStream close");
            } catch (IOException e2) {
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "httpsFinally2 IOException : ", e2.getMessage());
            }
        } else {
            LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "httpsFinally2 outStream is null");
        }
        synchronized (b) {
            if (i == 200) {
                casePhoneDownloadListener.onResult(i, str);
            } else {
                casePhoneDownloadListener.onFailed(i, "postHttpsRequest getMessage err");
            }
        }
    }

    private static void e(HttpsURLConnection httpsURLConnection, BufferedReader bufferedReader, InputStreamReader inputStreamReader, InputStream inputStream) {
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "HttpUtil postRequest bufferedReader", e2.getMessage());
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e3) {
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e3.getMessage());
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e4.getMessage());
            }
        }
    }

    public static HttpsURLConnection a(URL url) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
        try {
            httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactoryNew.getInstance(BaseApplication.getContext()));
            httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
            httpsURLConnection.setRequestMethod("POST");
            LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "HttpUtil HttpsURLConnection postRequest setRequestMethod");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(10000);
            return httpsURLConnection;
        } catch (IllegalAccessException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
            LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e2.getMessage());
            return httpsURLConnection;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0087 A[Catch: all -> 0x00fa, IOException -> 0x0102, MalformedURLException -> 0x0108, TRY_LEAVE, TryCatch #24 {MalformedURLException -> 0x0108, IOException -> 0x0102, all -> 0x00fa, blocks: (B:15:0x0074, B:17:0x0087), top: B:14:0x0074 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r17, java.lang.String r18, com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener r19) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pvr.a(java.lang.String, java.lang.String, com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener):void");
    }

    private static void d(CasePhoneDownloadListener casePhoneDownloadListener, int i, String str, OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e2.getMessage());
            }
        }
        synchronized (b) {
            if (i == 200) {
                casePhoneDownloadListener.onResult(i, str);
            } else {
                casePhoneDownloadListener.onFailed(i, "postHttpRequest getMessage err");
            }
        }
    }

    private static void c(HttpURLConnection httpURLConnection, BufferedReader bufferedReader, InputStreamReader inputStreamReader, InputStream inputStream) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", e2.getMessage());
                return;
            }
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private static HttpURLConnection e(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
        httpURLConnection.setRequestMethod("POST");
        LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "HttpUtil postRequest setRequestMethod");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(10000);
        return httpURLConnection;
    }

    public static void e(pvq pvqVar, final String str, final CasePhoneDownloadListener casePhoneDownloadListener) {
        if (pvqVar == null) {
            LogUtil.h("PluginDevice_CasePhoneDownloadUtil", "ResourceManager toDownloadFile resultObject is null");
            casePhoneDownloadListener.onFailed(-1, "ResourceManager toDownloadFile resultObject is null");
            return;
        }
        final String d2 = pvqVar.d();
        if (TextUtils.isEmpty(d2)) {
            LogUtil.h("PluginDevice_CasePhoneDownloadUtil", "ResourceManager toDownloadFile url is null");
            casePhoneDownloadListener.onFailed(-1, "ResourceManager toDownloadFile url is null");
        } else {
            Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: pvr.3
                @Override // java.lang.Runnable
                public void run() {
                    pvr.b(d2, str, casePhoneDownloadListener);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v12, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.FileOutputStream] */
    public static void b(String str, String str2, CasePhoneDownloadListener casePhoneDownloadListener) {
        Throwable th;
        IOException e2;
        InputStream inputStream;
        MalformedURLException e3;
        InputStream inputStream2;
        LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download file");
        if (casePhoneDownloadListener != null) {
            InputStream inputStream3 = null;
            try {
                try {
                    HttpURLConnection b2 = b(new URL(str));
                    int responseCode = b2.getResponseCode();
                    LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download resCode = ", Integer.valueOf(responseCode));
                    if (responseCode == 200) {
                        InputStream inputStream4 = b2.getInputStream();
                        try {
                            File file = new File(str2.substring(0, str2.lastIndexOf(File.separator)));
                            if (!file.exists()) {
                                LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "mkdirs create_result is ", Boolean.valueOf(file.mkdirs()));
                            }
                            File file2 = new File(str2);
                            if (!file2.exists()) {
                                LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "create_result is ", Boolean.valueOf(file2.createNewFile()));
                            }
                            ?? fileOutputStream = new FileOutputStream(file2);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream4.read(bArr);
                                    if (read == -1) {
                                        break;
                                    } else {
                                        fileOutputStream.write(bArr, 0, read);
                                    }
                                }
                                fileOutputStream.flush();
                                LogUtil.a("PluginDevice_CasePhoneDownloadUtil", file2.getPath(), "----", Long.valueOf(file2.length()));
                                if (b(str2).equals(WatchFaceConstant.XML_SUFFIX)) {
                                    LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "FileManager download the extension is xml");
                                    casePhoneDownloadListener.onResult(200, MonitorResult.SUCCESS);
                                } else {
                                    casePhoneDownloadListener.onFailed(-1, "FileManager download the extension not is xml");
                                }
                                inputStream3 = inputStream4;
                                str = fileOutputStream;
                            } catch (MalformedURLException e4) {
                                e3 = e4;
                                inputStream3 = fileOutputStream;
                                InputStream inputStream5 = inputStream3;
                                inputStream3 = inputStream4;
                                inputStream2 = inputStream5;
                                casePhoneDownloadListener.onFailed(-1, "ResourceManager download MalformedURLException ");
                                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download MalformedURLException = ", e3.getMessage());
                                str = inputStream2;
                                e(inputStream3, str);
                            } catch (IOException e5) {
                                e2 = e5;
                                inputStream3 = fileOutputStream;
                                InputStream inputStream6 = inputStream3;
                                inputStream3 = inputStream4;
                                inputStream = inputStream6;
                                casePhoneDownloadListener.onFailed(-1, "ResourceManager download IOException e");
                                LogUtil.b("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download IOException = ", e2.getMessage());
                                str = inputStream;
                                e(inputStream3, str);
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream3 = fileOutputStream;
                                InputStream inputStream7 = inputStream3;
                                inputStream3 = inputStream4;
                                str = inputStream7;
                                e(inputStream3, str);
                                throw th;
                            }
                        } catch (MalformedURLException e6) {
                            e3 = e6;
                        } catch (IOException e7) {
                            e2 = e7;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        str = 0;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (MalformedURLException e8) {
                e3 = e8;
                inputStream2 = null;
            } catch (IOException e9) {
                e2 = e9;
                inputStream = null;
            } catch (Throwable th5) {
                th = th5;
                str = 0;
            }
            e(inputStream3, str);
        }
    }

    private static HttpURLConnection b(URL url) throws IOException {
        HttpURLConnection httpURLConnection;
        if (url.getProtocol().toLowerCase(Locale.ENGLISH).equals(ProxyConfig.MATCH_HTTPS)) {
            LogUtil.a("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download https");
            httpURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
        } else {
            httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
        }
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod("GET");
        return httpURLConnection;
    }

    private static String b(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf < 0) {
            lastIndexOf = 0;
        }
        return str.length() > lastIndexOf ? str.substring(lastIndexOf) : "";
    }

    public static void e(InputStream inputStream, FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                LogUtil.h("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download IOException:", e2.getMessage());
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                LogUtil.h("PluginDevice_CasePhoneDownloadUtil", "ResourceManager download IOException:", e3.getMessage());
            }
        }
    }
}
