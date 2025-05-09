package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.operation.utils.Constants;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* loaded from: classes2.dex */
public final class hy {
    public static Proxy a(Context context) {
        try {
            return a(context, new URI("http://restsdk.amap.com"));
        } catch (Throwable th) {
            iv.c(th, "pu", "gp");
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0076, code lost:
    
        if (r10 == (-1)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
    
        r18 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a9, code lost:
    
        r18 = 80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a7, code lost:
    
        if (r10 == (-1)) goto L49;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x00d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x014d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f2 A[Catch: all -> 0x016b, TryCatch #6 {all -> 0x016b, blocks: (B:99:0x00cd, B:64:0x00e7, B:66:0x00f2, B:68:0x0106, B:70:0x010c, B:74:0x0118, B:86:0x011f, B:88:0x0125, B:90:0x012b, B:94:0x0137), top: B:4:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0141 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x013e  */
    /* JADX WARN: Type inference failed for: r0v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v14, types: [java.util.Locale] */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v8, types: [int] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [int] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.content.ContentResolver, android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.net.Proxy b(android.content.Context r19) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.hy.b(android.content.Context):java.net.Proxy");
    }

    private static String a(String str) {
        return ia.c(str);
    }

    private static String a() {
        String str;
        try {
            str = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            iv.c(th, "pu", "gdh");
            str = null;
        }
        return str == null ? Constants.NULL : str;
    }

    private static Proxy a(Context context, URI uri) {
        Proxy proxy;
        if (c(context)) {
            try {
                List<Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty() || (proxy = select.get(0)) == null) {
                    return null;
                }
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                iv.c(th, "pu", "gpsc");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return hr.o(context) == 0;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            iv.c(th, "pu", "gdp");
            return -1;
        }
    }
}
