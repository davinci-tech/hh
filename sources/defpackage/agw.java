package defpackage;

/* loaded from: classes3.dex */
public class agw {
    private static boolean c = false;
    private static boolean e = false;

    public static boolean a() {
        return true;
    }

    public static boolean d() {
        return c;
    }

    public static boolean e() {
        return e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
    
        if (android.util.Log.class.getField("HWINFO").getBoolean(android.util.Log.class) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0060, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005e, code lost:
    
        if (android.util.Log.isLoggable("MarketInstallService", 4) != false) goto L25;
     */
    static {
        /*
            java.lang.String r0 = "LogConfig"
            r1 = 0
            java.lang.Class<android.util.Log> r2 = android.util.Log.class
            java.lang.String r3 = "HWLog"
            java.lang.reflect.Field r2 = r2.getField(r3)     // Catch: java.lang.Throwable -> L12
            java.lang.Class<android.util.Log> r3 = android.util.Log.class
            boolean r2 = r2.getBoolean(r3)     // Catch: java.lang.Throwable -> L12
            goto L18
        L12:
            java.lang.String r2 = "can not get HWLog"
            android.util.Log.e(r0, r2)
            r2 = r1
        L18:
            java.lang.Class<android.util.Log> r3 = android.util.Log.class
            java.lang.String r4 = "HWModuleLog"
            java.lang.reflect.Field r3 = r3.getField(r4)     // Catch: java.lang.Throwable -> L27
            java.lang.Class<android.util.Log> r4 = android.util.Log.class
            boolean r3 = r3.getBoolean(r4)     // Catch: java.lang.Throwable -> L27
            goto L2d
        L27:
            java.lang.String r3 = "can not get HWModuleLog"
            android.util.Log.e(r0, r3)
            r3 = r1
        L2d:
            java.lang.String r4 = "MarketInstallService"
            r5 = 1
            if (r2 != 0) goto L3e
            if (r3 == 0) goto L3c
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r4, r2)
            if (r2 == 0) goto L3c
            goto L3e
        L3c:
            r2 = r1
            goto L3f
        L3e:
            r2 = r5
        L3f:
            defpackage.agw.e = r2
            java.lang.Class<android.util.Log> r2 = android.util.Log.class
            java.lang.String r6 = "HWINFO"
            java.lang.reflect.Field r2 = r2.getField(r6)     // Catch: java.lang.Throwable -> L52
            java.lang.Class<android.util.Log> r6 = android.util.Log.class
            boolean r0 = r2.getBoolean(r6)     // Catch: java.lang.Throwable -> L52
            if (r0 != 0) goto L60
            goto L57
        L52:
            java.lang.String r2 = "can not get HWINFO"
            android.util.Log.e(r0, r2)
        L57:
            if (r3 == 0) goto L61
            r0 = 4
            boolean r0 = android.util.Log.isLoggable(r4, r0)
            if (r0 == 0) goto L61
        L60:
            r1 = r5
        L61:
            defpackage.agw.c = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.agw.<clinit>():void");
    }
}
