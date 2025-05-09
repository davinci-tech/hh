package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.ui.main.stories.nps.https.HttpUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class rbv {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.util.HashMap, java.util.HashMap<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.OutputStream] */
    public static String e(Context context, String str, String str2, HashMap<String, String> hashMap) {
        InputStream inputStream;
        String str3;
        HttpURLConnection d = d(str);
        ?? r1 = 0;
        InputStream inputStream2 = null;
        r1 = 0;
        String str4 = null;
        if (d == null) {
            return null;
        }
        d.setDoOutput(true);
        d.setDoInput(true);
        d.setUseCaches(false);
        try {
            d.setRequestMethod("POST");
            HttpUtils.setHeader(context, d, hashMap);
            try {
                try {
                    if (TextUtils.isEmpty(str2)) {
                        hashMap = 0;
                    } else {
                        hashMap = d.getOutputStream();
                        hashMap = hashMap;
                        if (hashMap != 0) {
                            try {
                                hashMap.write(str2.getBytes(StandardCharsets.UTF_8));
                                hashMap.flush();
                                hashMap = hashMap;
                            } catch (IOException unused) {
                                inputStream = null;
                                LogUtil.e("HttpPostUtil", "postRequest IOException");
                                HttpUtils.closeStream(inputStream, hashMap);
                                LogUtil.b("HttpPostUtil", "exit AchieveHttps.postRequest ", str4);
                                return str4;
                            } catch (Throwable th) {
                                th = th;
                                HttpUtils.closeStream(r1, hashMap);
                                throw th;
                            }
                        }
                    }
                    d.connect();
                    int responseCode = d.getResponseCode();
                    d(context, d.getHeaderField("Set-Cookie"));
                    LogUtil.d("HttpPostUtil", "postRequest-->responseCode:", Integer.valueOf(responseCode));
                    if (responseCode == 200) {
                        inputStream = d.getInputStream();
                        try {
                            str3 = new String(HttpUtils.readInputStream(inputStream), StandardCharsets.UTF_8);
                            inputStream2 = inputStream;
                        } catch (IOException unused2) {
                            LogUtil.e("HttpPostUtil", "postRequest IOException");
                            HttpUtils.closeStream(inputStream, hashMap);
                            LogUtil.b("HttpPostUtil", "exit AchieveHttps.postRequest ", str4);
                            return str4;
                        }
                    } else {
                        str3 = null;
                    }
                    HttpUtils.closeStream(inputStream2, hashMap);
                    str4 = str3;
                } catch (Throwable th2) {
                    r1 = context;
                    th = th2;
                }
            } catch (IOException unused3) {
                inputStream = null;
                hashMap = 0;
            } catch (Throwable th3) {
                th = th3;
                hashMap = 0;
            }
            LogUtil.b("HttpPostUtil", "exit AchieveHttps.postRequest ", str4);
            return str4;
        } catch (ProtocolException unused4) {
            LogUtil.e("HttpPostUtil", "postRequest ProtocolException!");
            return null;
        }
    }

    private static void d(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.b("HttpPostUtil", "cookie ", str);
        String[] split = str.replace("[", "").replace("]", "").split(";");
        if (split == null || split.length <= 0) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "cookie", split[0], (StorageParams) null);
    }

    public static HttpURLConnection d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            URL url = new URL(str);
            try {
                HttpURLConnection httpURLConnection = URLConnectionInstrumentation.openConnection(url.openConnection()) instanceof HttpURLConnection ? (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection()) : null;
                if (httpURLConnection != null) {
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    try {
                        httpURLConnection.setRequestMethod("GET");
                    } catch (ProtocolException unused) {
                        LogUtil.e("HttpPostUtil", "initConnection ProtocolException!");
                    }
                }
                return httpURLConnection;
            } catch (IOException unused2) {
                LogUtil.e("HttpPostUtil", "initConnection IOException!");
                return null;
            }
        } catch (MalformedURLException unused3) {
            LogUtil.e("HttpPostUtil", "initConnection MalformedURLException!");
            return null;
        }
    }
}
