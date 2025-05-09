package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class jei {
    public static int d(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HttpResCallback httpResCallback) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (e(str)) {
            return jef.c(str, hashMap, hashMap2, httpResCallback);
        }
        return jej.b(str, hashMap, hashMap2, httpResCallback);
    }

    public static String b(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (e(str)) {
            return jef.b(str, hashMap, hashMap2);
        }
        return jej.b(str, hashMap, hashMap2);
    }

    public static Bitmap bGs_(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (e(str)) {
            return jef.bGr_(str);
        }
        return bGt_(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.lang.Object[]] */
    private static Bitmap bGt_(String str) {
        ?? r1;
        Bitmap bitmap;
        ByteArrayOutputStream byteArrayOutputStream;
        OutputStream outputStream;
        InputStream inputStream = null;
        if (e(str)) {
            return null;
        }
        try {
            r1 = new URL(str);
        } catch (MalformedURLException e) {
            LogUtil.b("HwCommonModelHttpUtils", "optionBitmapHttps MalformedURLException exception = ", e.getMessage());
            r1 = 0;
        }
        if (r1 != 0) {
            try {
                try {
                    LogUtil.c("HwCommonModelHttpUtils", "imgUrl contains https. fileUrl is not null");
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(r1.openConnection());
                    jej.d(httpsURLConnection);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.connect();
                    r1 = httpsURLConnection.getInputStream();
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    LogUtil.c("HwCommonModelHttpUtils", new Object[]{"conn.getInputStream()=", r1});
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[512];
                        int i = 0;
                        do {
                            int read = r1.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                            i += read;
                        } while (i <= 10485760);
                        bitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, i);
                        inputStream = r1;
                    } catch (IOException unused) {
                        LogUtil.b("HwCommonModelHttpUtils", "optionBitmapHttps IOException,download failed");
                        d(r1);
                        c(byteArrayOutputStream);
                        return null;
                    }
                } catch (IOException unused2) {
                    byteArrayOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = null;
                    inputStream = r1;
                    d(inputStream);
                    c(outputStream);
                    throw th;
                }
            } catch (IOException unused3) {
                r1 = 0;
                byteArrayOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                outputStream = null;
                d(inputStream);
                c(outputStream);
                throw th;
            }
        } else {
            bitmap = null;
            byteArrayOutputStream = null;
        }
        d(inputStream);
        c(byteArrayOutputStream);
        return bitmap;
    }

    private static boolean e(String str) {
        if (!str.contains(File.pathSeparator)) {
            return false;
        }
        int indexOf = str.indexOf(File.pathSeparator);
        if (indexOf < 0) {
            LogUtil.h("HwCommonModelHttpUtils", "isHttpProtocol index is ", Integer.valueOf(indexOf));
            return false;
        }
        try {
            String substring = str.substring(0, indexOf);
            LogUtil.a("HwCommonModelHttpUtils", "protocol:", substring);
            return "http".equalsIgnoreCase(substring);
        } catch (StringIndexOutOfBoundsException e) {
            LogUtil.b("HwCommonModelHttpUtils", "isHttpProtocol StringIndexOutOfBoundsException = ", e.getMessage());
            return false;
        }
    }

    public static String c(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry instanceof Map.Entry) {
                Map.Entry<String, String> entry2 = entry;
                String key = entry2.getKey();
                String value = entry2.getValue();
                sb.append("&");
                sb.append((Object) key);
                sb.append("=");
                sb.append((Object) value);
            }
        }
        String trim = sb.toString().trim();
        return trim.length() < 1 ? "" : trim.substring(1);
    }

    public static void a(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        boolean z = hashMap == null || hashMap.isEmpty();
        if (httpURLConnection == null || z) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry instanceof Map.Entry) {
                Map.Entry<String, String> entry2 = entry;
                String key = entry2.getKey();
                String value = entry2.getValue();
                if (key != null && value != null) {
                    httpURLConnection.setRequestProperty(String.valueOf(key), String.valueOf(value));
                    LogUtil.c("HwCommonModelHttpUtils", "setHeader key value");
                }
            }
        }
    }

    public static byte[] e(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        int i = 0;
        do {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            i += read;
        } while (i <= 10485760);
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        c(byteArrayOutputStream);
        return byteArray;
    }

    public static void c(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                try {
                    outputStream.close();
                    try {
                        outputStream.close();
                        return;
                    } catch (IOException unused) {
                        LogUtil.b("HwCommonModelHttpUtils", "closeOutputStream IOException,closing failure");
                        return;
                    }
                } catch (IOException unused2) {
                    LogUtil.b("HwCommonModelHttpUtils", "closeOutputStream IOException,closing failure");
                    try {
                        outputStream.close();
                        return;
                    } catch (IOException unused3) {
                        LogUtil.b("HwCommonModelHttpUtils", "closeOutputStream IOException,closing failure");
                        return;
                    }
                }
            } catch (Throwable th) {
                try {
                    outputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("HwCommonModelHttpUtils", "closeOutputStream IOException,closing failure");
                }
                throw th;
            }
        }
        LogUtil.b("HwCommonModelHttpUtils", "closeOutputStream outputStream is null");
    }

    public static void d(InputStream inputStream) {
        if (inputStream != null) {
            try {
                try {
                    inputStream.close();
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException unused) {
                        LogUtil.b("HwCommonModelHttpUtils", "closeInputStream IOException,closing failure");
                        return;
                    }
                } catch (IOException unused2) {
                    LogUtil.b("HwCommonModelHttpUtils", "closeInputStream IOException,closing failure");
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException unused3) {
                        LogUtil.b("HwCommonModelHttpUtils", "closeInputStream IOException,closing failure");
                        return;
                    }
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("HwCommonModelHttpUtils", "closeInputStream IOException,closing failure");
                }
                throw th;
            }
        }
        LogUtil.b("HwCommonModelHttpUtils", "closeInputStream inputStream is null");
    }

    public static Bitmap bGu_(InputStream inputStream) {
        Bitmap bitmap = null;
        try {
            byte[] e = e(inputStream);
            if (e != null && e.length > 0) {
                bitmap = BitmapFactory.decodeByteArray(e, 0, e.length, null);
            } else {
                LogUtil.h("HwCommonModelHttpUtils", "index is out of Bounds in bytes.");
            }
        } catch (IOException unused) {
            LogUtil.b("HwCommonModelHttpUtils", "readInputStream IOException,conversion failed");
        } catch (IllegalArgumentException e2) {
            LogUtil.b("HwCommonModelHttpUtils", "readInputStream IllegalArgumentException", e2.getMessage());
        }
        return bitmap;
    }
}
