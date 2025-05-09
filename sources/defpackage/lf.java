package defpackage;

import android.content.Context;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public final class lf {
    public static final CookieManager e = new CookieManager();

    public static final class a {
        public final String b;
        public final Map<String, String> d;
        public final byte[] e;

        public a(String str, Map<String, String> map, byte[] bArr) {
            this.b = str;
            this.e = bArr;
            this.d = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s headers=%s>", this.b, this.d);
        }
    }

    public static final class b {
        public final Map<String, List<String>> b;
        public final byte[] c;
        public final String e;

        public b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.b = map;
            this.e = str;
            this.c = bArr;
        }
    }

    public static Proxy d(Context context) {
        String b2 = b(context);
        if (b2 != null && !b2.contains("wap")) {
            return null;
        }
        try {
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return null;
            }
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static lf.b d(android.content.Context r11, lf.a r12) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lf.d(android.content.Context, lf$a):lf$b");
    }

    public static String b(Context context) {
        try {
            NetworkInfo bi_ = me.bi_(null, context);
            return (bi_ == null || !bi_.isAvailable()) ? "none" : bi_.getType() == 1 ? "wifi" : bi_.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return "none";
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
