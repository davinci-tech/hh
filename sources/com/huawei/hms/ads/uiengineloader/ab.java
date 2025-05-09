package com.huawei.hms.ads.uiengineloader;

/* loaded from: classes4.dex */
public final class ab {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4365a = "EmuiUtil";
    private static final String b = "EMUI_SDK_INT";
    private static final String c = "com.huawei.android.os.BuildEx$VERSION";
    private static final int d = -1;
    private static final int e = 7;
    private static final int f = 8;
    private static final int g = 9;
    private static final int h = 10;
    private static final int i = 11;
    private static final int j = 14;
    private static final int k = 15;
    private static final int l = 17;
    private static final int m = 30;
    private static final int n = 31;
    private static final int o = 40;
    private static final int p = 41;
    private static final int q = 50;
    private static final int r = 60;
    private static final int s = 81;
    private static final int t = 90;
    private static int u = -1;
    private static int v = d();

    /* JADX WARN: Removed duplicated region for block: B:5:0x0031 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int d() {
        /*
            java.lang.String r0 = "com.huawei.android.os.BuildEx$VERSION"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L19 java.lang.SecurityException -> L1c java.lang.NoSuchFieldException -> L1f java.lang.IllegalAccessException -> L22 java.lang.ClassNotFoundException -> L25
            java.lang.String r1 = "EMUI_SDK_INT"
            java.lang.reflect.Field r1 = r0.getDeclaredField(r1)     // Catch: java.lang.Exception -> L19 java.lang.SecurityException -> L1c java.lang.NoSuchFieldException -> L1f java.lang.IllegalAccessException -> L22 java.lang.ClassNotFoundException -> L25
            java.lang.reflect.Field[] r2 = new java.lang.reflect.Field[]{r1}     // Catch: java.lang.Exception -> L19 java.lang.SecurityException -> L1c java.lang.NoSuchFieldException -> L1f java.lang.IllegalAccessException -> L22 java.lang.ClassNotFoundException -> L25
            r3 = 1
            java.lang.reflect.AccessibleObject.setAccessible(r2, r3)     // Catch: java.lang.Exception -> L19 java.lang.SecurityException -> L1c java.lang.NoSuchFieldException -> L1f java.lang.IllegalAccessException -> L22 java.lang.ClassNotFoundException -> L25
            java.lang.Object r0 = r1.get(r0)     // Catch: java.lang.Exception -> L19 java.lang.SecurityException -> L1c java.lang.NoSuchFieldException -> L1f java.lang.IllegalAccessException -> L22 java.lang.ClassNotFoundException -> L25
            goto L2d
        L19:
            java.lang.String r0 = "getEMUIVersionCode exception "
            goto L27
        L1c:
            java.lang.String r0 = "getEMUIVersionCode SecurityException"
            goto L27
        L1f:
            java.lang.String r0 = "getEMUIVersionCode NoSuchFieldException"
            goto L27
        L22:
            java.lang.String r0 = "getEMUIVersionCode IllegalAccessException"
            goto L27
        L25:
            java.lang.String r0 = "getEMUIVersionCode ClassNotFoundException"
        L27:
            java.lang.String r1 = "EmuiUtil"
            com.huawei.hms.ads.uiengineloader.af.c(r1, r0)
            r0 = 0
        L2d:
            boolean r1 = r0 instanceof java.lang.Integer
            if (r1 != 0) goto L33
            r0 = 0
            return r0
        L33:
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengineloader.ab.d():int");
    }

    private static void c() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
            af.a(f4365a, "isNeed2UseHwEmui :");
            if (str != null) {
                if (str.contains("EmotionUI_3.0")) {
                    u = 30;
                    return;
                }
                if (str.contains("EmotionUI_3.1")) {
                    u = 31;
                    return;
                }
                if (str.contains("EmotionUI_4.0")) {
                    u = 40;
                    return;
                }
                if (str.contains("EmotionUI_4.1")) {
                    u = 41;
                    return;
                }
                if (str.contains("EmotionUI_5.0")) {
                    u = 50;
                } else if (str.contains("EmotionUI_6.0")) {
                    u = 60;
                } else {
                    u = -1;
                }
            }
        } catch (Throwable th) {
            af.d(f4365a, "dealTypeUnknow Exception:" + th.getClass().getSimpleName());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void b() {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengineloader.ab.b():void");
    }

    public static boolean a() {
        return u == 50;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengineloader.ab.<clinit>():void");
    }
}
