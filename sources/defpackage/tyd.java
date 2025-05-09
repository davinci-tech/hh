package defpackage;

import android.net.Uri;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class tyd {
    public static String a(String str, String str2) {
        try {
            Uri parse = Uri.parse(str);
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            String[] strArr = (String[]) queryParameterNames.toArray(new String[queryParameterNames.size()]);
            Arrays.sort(strArr);
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (String str3 : strArr) {
                if (z) {
                    sb.append("&");
                } else {
                    z = true;
                }
                sb.append(str3);
                sb.append("=");
                sb.append(parse.getQueryParameter(str3));
            }
            return d(sb.toString().getBytes(), str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String d(byte[] bArr, String str) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(str.getBytes(), "HmacSHA256"));
            return a(mac.doFinal(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static String d(String str) {
        Throwable th;
        Closeable closeable;
        IOException e;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ?? r0 = 0;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setConnectTimeout(2500);
                httpURLConnection.setReadTimeout(1500);
                httpURLConnection.setUseCaches(true);
                httpURLConnection.setRequestProperty("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
                if (200 == httpURLConnection.getResponseCode()) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr, 0, 1024);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            if (byteArrayOutputStream.size() > 0) {
                                String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                                tyh.c(inputStream);
                                tyh.c(byteArrayOutputStream);
                                return str2;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            tyc.a(" get " + e.toString());
                            tyh.c(inputStream);
                            tyh.c(byteArrayOutputStream);
                            return null;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        byteArrayOutputStream = null;
                    } catch (Throwable th2) {
                        r0 = inputStream;
                        th = th2;
                        closeable = null;
                        tyh.c(r0);
                        tyh.c(closeable);
                        throw th;
                    }
                } else {
                    tyc.b(" get response error: " + httpURLConnection.getResponseCode());
                    inputStream = null;
                    byteArrayOutputStream = null;
                }
            } catch (Throwable th3) {
                r0 = str;
                th = th3;
            }
        } catch (IOException e4) {
            e = e4;
            inputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            closeable = null;
        }
        tyh.c(inputStream);
        tyh.c(byteArrayOutputStream);
        return null;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b)));
        }
        return sb.toString();
    }
}
