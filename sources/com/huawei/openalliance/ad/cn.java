package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.constant.OsType;
import java.util.List;

/* loaded from: classes5.dex */
public class cn extends cc {
    private static co c;
    private static final byte[] d = new byte[0];
    private CountryCodeBean e;

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean d() {
        return a();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.content.Context r8) {
        /*
            r7 = this;
            java.lang.String r0 = "ThirdDeviceImpl"
            r1 = 1
            r2 = 0
            java.lang.String r3 = "android.os.SystemProperties"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Throwable -> L37
            r4 = 2
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L37
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r2] = r6     // Catch: java.lang.Throwable -> L37
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch: java.lang.Throwable -> L37
            r5[r1] = r6     // Catch: java.lang.Throwable -> L37
            java.lang.String r6 = "getInt"
            java.lang.reflect.Method r5 = r3.getMethod(r6, r5)     // Catch: java.lang.Throwable -> L37
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L37
            java.lang.String r6 = "ro.miui.notch"
            r4[r2] = r6     // Catch: java.lang.Throwable -> L37
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L37
            r4[r1] = r6     // Catch: java.lang.Throwable -> L37
            java.lang.Object r3 = r5.invoke(r3, r4)     // Catch: java.lang.Throwable -> L37
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L37
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L37
            if (r3 != 0) goto L35
            goto L51
        L35:
            r3 = r1
            goto L52
        L37:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "isNotchEnable mi Throwable:"
            r4.<init>(r5)
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.huawei.openalliance.ad.ho.c(r0, r3)
        L51:
            r3 = r2
        L52:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "isNotchEnable xiaomi, hasNotch = %s"
            com.huawei.openalliance.ad.ho.a(r0, r5, r4)
            if (r3 != 0) goto L86
            android.content.pm.PackageManager r4 = r8.getPackageManager()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r5 = "com.oppo.feature.screen.heteromorphism"
            boolean r3 = r4.hasSystemFeature(r5)     // Catch: java.lang.Throwable -> L6c
            goto L86
        L6c:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "isNotchEnable oppo Throwable:"
            r5.<init>(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.huawei.openalliance.ad.ho.c(r0, r4)
        L86:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "isNotchEnable oppo, hasNotch = %s"
            com.huawei.openalliance.ad.ho.a(r0, r5, r4)
            if (r3 != 0) goto Le1
            java.lang.ClassLoader r8 = r8.getClassLoader()     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.String r4 = "android.util.FtFeature"
            java.lang.Class r8 = r8.loadClass(r4)     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            r4[r2] = r5     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.String r5 = "isFeatureSupport"
            java.lang.reflect.Method r4 = r8.getMethod(r5, r4)     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            r6 = 32
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            r5[r2] = r6     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Object r5 = r4.invoke(r8, r5)     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            boolean r3 = r5.booleanValue()     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            r5 = 8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            r1[r2] = r5     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Object r8 = r4.invoke(r8, r1)     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            boolean r3 = r8.booleanValue()     // Catch: java.lang.Exception -> Ld4 java.lang.NoSuchMethodException -> Ld7 java.lang.ClassNotFoundException -> Lda
            goto Le1
        Ld4:
            java.lang.String r8 = "hasNotchAtVivo Exception"
            goto Ldc
        Ld7:
            java.lang.String r8 = "hasNotchAtVivo NoSuchMethodException"
            goto Ldc
        Lda:
            java.lang.String r8 = "hasNotchAtVivo ClassNotFoundException"
        Ldc:
            java.lang.String r1 = "Notch"
            com.huawei.openalliance.ad.ho.d(r1, r8)
        Le1:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r1 = "isNotchEnable vivo, hasNotch = %s"
            com.huawei.openalliance.ad.ho.a(r0, r1, r8)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.cn.a(android.content.Context):boolean");
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean a() {
        String b = com.huawei.openalliance.ad.utils.ct.a().b();
        if (TextUtils.isEmpty(b)) {
            b = this.e.a();
        }
        return "CN".equalsIgnoreCase(b);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public int a(View view) {
        int identifier;
        DisplayCutout displayCutout;
        int i = -1;
        if (view == null) {
            return -1;
        }
        try {
            if (Build.VERSION.SDK_INT >= 28 && view.getRootWindowInsets() != null && (displayCutout = view.getRootWindowInsets().getDisplayCutout()) != null) {
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                if (!com.huawei.openalliance.ad.utils.bg.a(boundingRects)) {
                    i = boundingRects.get(0).height();
                }
            }
            if (i < 0 && (identifier = this.f6674a.getResources().getIdentifier("notch_height", "dimen", OsType.ANDROID)) > 0) {
                i = this.f6674a.getResources().getDimensionPixelSize(identifier);
            }
            if (i >= 0) {
                return i;
            }
            int identifier2 = this.f6674a.getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID);
            if (identifier2 > 0) {
                i = this.f6674a.getResources().getDimensionPixelSize(identifier2);
            }
            if (i == 0) {
                return 110;
            }
            return i;
        } catch (Throwable th) {
            ho.b("ThirdDeviceImpl", "getNotchHeight err: %s", th.getClass().getSimpleName());
            return i;
        }
    }

    private static co c(Context context) {
        co coVar;
        synchronized (d) {
            if (c == null) {
                c = new cn(context);
            }
            coVar = c;
        }
        return coVar;
    }

    public static co b(Context context) {
        return c(context);
    }

    protected cn(Context context) {
        super(context);
        this.e = new CountryCodeBean(context);
    }
}
