package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.network.embedded.j2;
import com.tencent.connect.common.Constants;
import defpackage.low;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class lou {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14817a = new Object();
    private static final String b = "GBAHttpConnectionUtils";
    private static lou e;
    private String c;
    private los d;

    public static lou e() {
        lou louVar;
        synchronized (f14817a) {
            if (e == null) {
                e = new lou();
            }
            louVar = e;
        }
        return louVar;
    }

    public String d(Context context, String str, int i, String str2) throws Exception {
        if (loq.b.booleanValue()) {
            loq.c(b, "Gba auth step 1, slotId=" + i);
        }
        lot d = d(String.format("sip:%1$s@ims.mnc%2$s.mcc%3$s.3gppnetwork.org", str, lop.b(str), lop.a(str)), lod.c(), str2, (String) null);
        if (loq.b.booleanValue()) {
            loq.c(b, "Gba auth step 1. resultCode=" + d.d());
        }
        if (200 == d.d()) {
            return d.e();
        }
        if (401 == d.d()) {
            low.b(d.e().replace("Digest ", " "));
            String e2 = low.e("realm");
            String[] split = e2 != null ? e2.split("@") : null;
            if (split != null && split.length == 2) {
                this.c = split[1];
                if ("3GPP-bootstrapping".equals(split[0])) {
                    return b(context, str, i, str2, d.e());
                }
            } else {
                this.c = "";
            }
        } else if (98 == d.d()) {
            return String.valueOf(98);
        }
        return String.valueOf(99);
    }

    private String b(Context context, String str, int i, String str2, String str3) throws Exception {
        if (loq.b.booleanValue()) {
            loq.c(b, "Gba auth step 2, slotId=" + i);
        }
        String format = String.format("%1$s@ims.mnc%2$s.mcc%3$s.3gppnetwork.org", str, lop.b(str), lop.a(str));
        lot c = c(String.format(lod.e(), lop.b(str), lop.a(str)), "Digest nonce=\"\",uri=\"/\",username=\"" + format + "\",realm=\"" + String.format("bsf.mnc%1$s.mcc%2$s.pub.3gppnetwork.org", lop.b(str), lop.a(str)) + "\",response=\"\"");
        if (401 == c.d()) {
            return b(context, i, str2, str3, c.e());
        }
        if (98 == c.d()) {
            return String.valueOf(98);
        }
        if (403 == c.d()) {
            return String.valueOf(403);
        }
        return String.valueOf(99);
    }

    private String b(Context context, int i, String str, String str2, String str3) throws Exception {
        if (loq.b.booleanValue()) {
            loq.c(b, "Gba auth step 3, slotId=" + i);
        }
        String d = lnc.d(context, i);
        String format = String.format("%1$s@ims.mnc%2$s.mcc%3$s.3gppnetwork.org", d, lop.b(d), lop.a(d));
        String format2 = String.format(lod.e(), lop.b(d), lop.a(d));
        String format3 = String.format("bsf.mnc%1$s.mcc%2$s.pub.3gppnetwork.org", lop.b(d), lop.a(d));
        low.b(str3.replace("Digest ", " "));
        String e2 = low.e(Constants.NONCE);
        String e3 = low.e("opaque");
        String e4 = low.e("qop");
        String e5 = low.e("algorithm");
        low.a b2 = low.b(context, e2, i);
        String a2 = b2 != null ? low.a(b2.c) : null;
        byte[] e6 = low.e(b2, d, this.c, this.d);
        String e7 = low.e(format, format3, a2, "GET");
        String c = low.c();
        lot c2 = c(format2, "Digest username=\"" + format + "\",realm=\"" + format3 + "\",nonce=\"" + e2 + "\",response=\"" + low.e(e7, low.b(e2, c, e4, "GET:/")) + "\",uri=\"/\",opaque=\"" + e3 + "\",qop=" + e4 + ",nc=00000001,algorithm=" + e5 + ",cnonce=\"" + c + "\"");
        if (200 == c2.d()) {
            return e(context, d, str, d(str2, low.a(e6), c2.e()));
        }
        if (98 == c2.d()) {
            return String.valueOf(98);
        }
        return String.valueOf(99);
    }

    private String e(Context context, String str, String str2, String str3) {
        if (loq.b.booleanValue()) {
            loq.c(b, "Gba auth step 4.");
        }
        lot d = d(String.format("sip:%1$s@ims.mnc%2$s.mcc%3$s.3gppnetwork.org", str, lop.b(str), lop.a(str)), lod.c(), str2, str3);
        if (200 == d.d()) {
            if (str != null) {
                lop.d(context, str, "authorization", str3);
            }
            return d.e();
        }
        if (98 == d.d()) {
            return String.valueOf(98);
        }
        return String.valueOf(99);
    }

    private String d(String str, String str2, String str3) {
        low.b(str.replace("Digest ", " "));
        Matcher matcher = Pattern.compile("[\\S\\s]*(<btid>[\\s\\S]*</btid>)[\\s\\S]*").matcher(str3);
        String replace = matcher.matches() ? matcher.group(1).replace("<btid>", "").replace("</btid>", "") : "";
        String e2 = low.e("realm");
        String e3 = low.e(Constants.NONCE);
        String c = low.c();
        String e4 = low.e("opaque");
        String e5 = low.e("algorithm");
        String e6 = low.e("qop");
        if (str2 == null) {
            return null;
        }
        return "Digest username=\"" + replace + "\",realm=\"" + e2 + "\",nonce=\"" + e3 + "\",response=\"" + low.e(low.e(replace, e2, str2, "POST"), low.b(e3, c, e6, "POST:/")) + "\",uri=\"/\",opaque=\"" + e4 + "\",qop=" + e6 + ",nc=00000001,algorithm=" + e5 + ",cnonce=\"" + c + "\"";
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private defpackage.lot d(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            java.lang.Boolean r0 = defpackage.loq.b
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L33
            java.lang.String r0 = defpackage.lou.b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getGBAContentFromPost->start.urlParams="
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r2 = ", authorization="
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = ", sip="
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = ", urlAddress="
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            defpackage.loq.c(r0, r1)
        L33:
            lot r0 = new lot
            r0.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto Ld7
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 == 0) goto L46
            goto Ld7
        L46:
            r1 = 98
            r2 = 0
            javax.net.ssl.HttpsURLConnection r5 = r4.b(r6, r8, r5)     // Catch: java.lang.Throwable -> Lbd java.io.IOException -> Lc0
            if (r5 != 0) goto L5b
            r0.e(r1)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            if (r5 == 0) goto L57
            r5.disconnect()
        L57:
            d(r2)
            return r0
        L5b:
            r5.connect()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            java.lang.String r6 = r5.getCipherSuite()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            los r6 = defpackage.los.c(r6)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            r4.d = r6     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            java.io.OutputStream r2 = r5.getOutputStream()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            boolean r6 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            if (r6 != 0) goto L79
            byte[] r6 = r7.getBytes()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            r2.write(r6)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
        L79:
            r2.flush()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            int r6 = r5.getResponseCode()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            r7 = 401(0x191, float:5.62E-43)
            if (r6 != r7) goto L96
            java.lang.String r7 = "www-Authenticate"
            java.lang.String r7 = r5.getHeaderField(r7)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            if (r8 != 0) goto La3
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            goto La5
        L96:
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto La3
            java.lang.String r7 = d(r5)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            goto La5
        La3:
            java.lang.String r7 = ""
        La5:
            r0.e(r6)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            r0.e(r7)     // Catch: java.lang.Throwable -> Lb4 java.io.IOException -> Lb9
            if (r5 == 0) goto Lb0
            r5.disconnect()
        Lb0:
            d(r2)
            return r0
        Lb4:
            r6 = move-exception
            r3 = r2
            r2 = r5
            r5 = r3
            goto Lce
        Lb9:
            r3 = r2
            r2 = r5
            r5 = r3
            goto Lc1
        Lbd:
            r6 = move-exception
            r5 = r2
            goto Lce
        Lc0:
            r5 = r2
        Lc1:
            r0.e(r1)     // Catch: java.lang.Throwable -> Lcd
            if (r2 == 0) goto Lc9
            r2.disconnect()
        Lc9:
            d(r5)
            return r0
        Lcd:
            r6 = move-exception
        Lce:
            if (r2 == 0) goto Ld3
            r2.disconnect()
        Ld3:
            d(r5)
            throw r6
        Ld7:
            r5 = 99
            r0.e(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lou.d(java.lang.String, java.lang.String, java.lang.String, java.lang.String):lot");
    }

    private HttpsURLConnection b(String str, String str2, String str3) {
        Throwable th;
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnection2 = null;
        try {
            try {
                httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
            } catch (IOException unused) {
            }
        } catch (Throwable th2) {
            th = th2;
            httpsURLConnection = httpsURLConnection2;
        }
        try {
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("User-Agent", "3gpp-gba");
            httpsURLConnection.setRequestProperty("ES-Version", "1.0.7");
            httpsURLConnection.setRequestProperty("ES-APP-ID", "00104605");
            httpsURLConnection.setRequestProperty("ES-APP-Key", "0C22D5E56878F9D3");
            httpsURLConnection.setRequestProperty("Accept", "application/json");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            httpsURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpsURLConnection.setRequestProperty("X-3GPP-Intended-IDENTITY", str3);
            if (!TextUtils.isEmpty(str2)) {
                httpsURLConnection.setRequestProperty("Authorization", str2);
            }
            if (httpsURLConnection == null) {
                return httpsURLConnection;
            }
            httpsURLConnection.disconnect();
            return httpsURLConnection;
        } catch (IOException unused2) {
            httpsURLConnection2 = httpsURLConnection;
            loq.c(b, "getGBAContentFromPost->IOException.");
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
            return httpsURLConnection2;
        } catch (Throwable th3) {
            th = th3;
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            throw th;
        }
    }

    private lot c(String str, String str2) throws Exception {
        Throwable th;
        HttpURLConnection httpURLConnection;
        String trim;
        if (loq.b.booleanValue()) {
            loq.c(b, "getGBAContentFromGet urlAddress = " + str + ", authorization = " + str2);
        }
        lot lotVar = new lot();
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            loq.c(b, "authorization or urlAddress is null");
            lotVar.e(99);
            return lotVar;
        }
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.setConnectTimeout(30000);
                        httpURLConnection.setReadTimeout(30000);
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("User-Agent", "Bootstrapping Client Agent");
                        httpURLConnection.setRequestProperty(j2.v, "identity");
                        httpURLConnection.setRequestProperty("Accept", "*/*");
                        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                        httpURLConnection.setRequestProperty("Authorization", str2);
                        httpURLConnection.connect();
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == 401) {
                            String headerField = httpURLConnection.getHeaderField("www-Authenticate");
                            if (!TextUtils.isEmpty(headerField)) {
                                trim = headerField.trim();
                                lotVar.e(responseCode);
                                lotVar.e(trim);
                            }
                            trim = "";
                            lotVar.e(responseCode);
                            lotVar.e(trim);
                        } else {
                            if (responseCode == 200) {
                                trim = d(httpURLConnection).trim();
                                lotVar.e(responseCode);
                                lotVar.e(trim);
                            }
                            trim = "";
                            lotVar.e(responseCode);
                            lotVar.e(trim);
                        }
                    } catch (IOException unused) {
                        httpURLConnection2 = httpURLConnection;
                        loq.c(b, "getGBAContentFromGet->IOException.");
                        lotVar.e(98);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return lotVar;
                    } catch (Throwable th2) {
                        th = th2;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                }
                if (loq.b.booleanValue()) {
                    loq.c(b, "response: " + lotVar.toString());
                }
                loq.c(b, "doPostRequest->success.");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return lotVar;
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
            }
        } catch (IOException unused2) {
        }
    }

    private static String d(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                        d(inputStream);
                        d(byteArrayOutputStream);
                        return byteArrayOutputStream2;
                    }
                }
            } catch (Throwable th) {
                th = th;
                d(inputStream);
                d(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    private static void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                loq.g(b, "closeStream->close error");
            }
        }
    }
}
