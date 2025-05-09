package defpackage;

import android.content.Context;
import com.huawei.secure.android.common.util.SafeString;

/* loaded from: classes5.dex */
public class ksf {
    private static final String b = "a";

    static class a {
        public static ksf c = new ksf();
    }

    private ksf() {
    }

    public static ksf d() {
        return a.c;
    }

    public String c(Context context) {
        String d = d(context);
        return (d == null || d.length() < 3) ? "" : SafeString.substring(d, 0, 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            r1 = 1
            java.lang.String r2 = "phone"
            java.lang.Object r10 = r10.getSystemService(r2)     // Catch: java.lang.Throwable -> L9e
            android.telephony.TelephonyManager r10 = (android.telephony.TelephonyManager) r10     // Catch: java.lang.Throwable -> L9e
            ksj r2 = defpackage.ksj.b()     // Catch: java.lang.Throwable -> L9e
            boolean r2 = r2.c()     // Catch: java.lang.Throwable -> L9e
            r3 = 0
            r4 = 5
            if (r2 == 0) goto L6b
            java.lang.String r10 = defpackage.ksf.b     // Catch: java.lang.Throwable -> L9e
            java.lang.String r2 = "getDevicePLMN multi sim enable"
            defpackage.ksy.b(r10, r2, r1)     // Catch: java.lang.Throwable -> L9e
            ksj r2 = defpackage.ksj.b()     // Catch: java.lang.Throwable -> L9e
            com.huawei.hwidauth.utils.b.b r2 = r2.a()     // Catch: java.lang.Throwable -> L9e
            int r5 = r2.a()     // Catch: java.lang.Throwable -> L9e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9e
            java.lang.String r7 = "getDevicePLMN subId:"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L9e
            r6.append(r5)     // Catch: java.lang.Throwable -> L9e
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L9e
            defpackage.ksy.a(r10, r6, r1)     // Catch: java.lang.Throwable -> L9e
            int r6 = r2.b(r5)     // Catch: java.lang.Throwable -> L9e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9e
            java.lang.String r8 = "getDevicePLMN simState:"
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L9e
            r7.append(r6)     // Catch: java.lang.Throwable -> L9e
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L9e
            defpackage.ksy.a(r10, r7, r1)     // Catch: java.lang.Throwable -> L9e
            if (r4 != r6) goto L93
            java.lang.String r10 = r2.c(r5)     // Catch: java.lang.Throwable -> L9e
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L9e
            if (r6 == 0) goto L94
            java.lang.String r10 = r2.a(r5)     // Catch: java.lang.Throwable -> L9e
            boolean r2 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L9e
            if (r2 != 0) goto L94
            java.lang.String r10 = com.huawei.secure.android.common.util.SafeString.substring(r10, r3, r4)     // Catch: java.lang.Throwable -> L9e
            goto L94
        L6b:
            java.lang.String r2 = defpackage.ksf.b     // Catch: java.lang.Throwable -> L9e
            java.lang.String r5 = "getDevicePLMN multi sim disable"
            defpackage.ksy.b(r2, r5, r1)     // Catch: java.lang.Throwable -> L9e
            int r2 = r10.getSimState()     // Catch: java.lang.Throwable -> L9e
            if (r4 != r2) goto L93
            java.lang.String r2 = r10.getSimOperator()     // Catch: java.lang.Throwable -> L9e
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L9e
            if (r5 == 0) goto L91
            java.lang.String r10 = r10.getSubscriberId()     // Catch: java.lang.Throwable -> L9e
            boolean r2 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L9e
            if (r2 != 0) goto L94
            java.lang.String r10 = com.huawei.secure.android.common.util.SafeString.substring(r10, r3, r4)     // Catch: java.lang.Throwable -> L9e
            goto L94
        L91:
            r10 = r2
            goto L94
        L93:
            r10 = r0
        L94:
            boolean r0 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L9e
            if (r0 == 0) goto L9c
            java.lang.String r10 = "00000"
        L9c:
            r0 = r10
            goto Lba
        L9e:
            r10 = move-exception
            java.lang.String r2 = defpackage.ksf.b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Throwable:"
            r3.<init>(r4)
            java.lang.Class r10 = r10.getClass()
            java.lang.String r10 = r10.getSimpleName()
            r3.append(r10)
            java.lang.String r10 = r3.toString()
            defpackage.ksy.c(r2, r10, r1)
        Lba:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksf.d(android.content.Context):java.lang.String");
    }
}
