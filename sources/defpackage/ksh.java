package defpackage;

import com.huawei.hwidauth.utils.b.b;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes5.dex */
public class ksh implements b {
    private static ksh d;

    public static ksh d() {
        ksh kshVar;
        synchronized (ksh.class) {
            if (d == null) {
                d = new ksh();
            }
            kshVar = d;
        }
        return kshVar;
    }

    public static Object e() {
        try {
            Class<?> cls = Class.forName("android.telephony.MSimTelephonyManager");
            return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            ksy.a("MutiCardHwImpl", " getDefaultMSimTelephonyManager wrong " + e.getClass().getSimpleName(), true);
            return null;
        }
    }

    @Override // com.huawei.hwidauth.utils.b.b
    public int a() {
        try {
            Object e = e();
            if (e != null) {
                return ((Integer) e.getClass().getMethod("getDefaultSubscription", new Class[0]).invoke(e, new Object[0])).intValue();
            }
            return 0;
        } catch (IllegalArgumentException e2) {
            ksy.c("MutiCardHwImpl", " IllegalArgumentException wrong " + e2.getClass().getSimpleName(), true);
            return -1;
        } catch (NoSuchMethodException e3) {
            ksy.c("MutiCardHwImpl", " NoSuchMethodException wrong " + e3.getClass().getSimpleName(), true);
            return -1;
        } catch (NullPointerException e4) {
            ksy.c("MutiCardHwImpl", " NullPointerException wrong " + e4.getClass().getSimpleName(), true);
            return -1;
        } catch (InvocationTargetException e5) {
            ksy.c("MutiCardHwImpl", " InvocationTargetException wrong " + e5.getClass().getSimpleName(), true);
            return -1;
        } catch (Exception e6) {
            ksy.c("MutiCardHwImpl", " Exception wrong " + e6.getClass().getSimpleName(), true);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00ad  */
    @Override // com.huawei.hwidauth.utils.b.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(int r9) {
        /*
            r8 = this;
            java.lang.String r0 = "getSubscriberId exception:"
            java.lang.String r1 = "MutiCardHwImpl"
            java.lang.String r2 = ""
            java.lang.Class r3 = java.lang.Integer.TYPE
            java.lang.Class[] r3 = new java.lang.Class[]{r3}
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            r4 = 1
            java.lang.Object r5 = e()     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalArgumentException -> L5f java.lang.NullPointerException -> L78 java.lang.NoSuchMethodException -> L91
            if (r5 == 0) goto La9
            java.lang.Class r6 = r5.getClass()     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalArgumentException -> L5f java.lang.NullPointerException -> L78 java.lang.NoSuchMethodException -> L91
            java.lang.String r7 = "getSubscriberId"
            java.lang.reflect.Method r3 = r6.getMethod(r7, r3)     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalArgumentException -> L5f java.lang.NullPointerException -> L78 java.lang.NoSuchMethodException -> L91
            java.lang.Object r9 = r3.invoke(r5, r9)     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalArgumentException -> L5f java.lang.NullPointerException -> L78 java.lang.NoSuchMethodException -> L91
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L46 java.lang.IllegalArgumentException -> L5f java.lang.NullPointerException -> L78 java.lang.NoSuchMethodException -> L91
            goto Laa
        L2d:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto La9
        L46:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto La9
        L5f:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto La9
        L78:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto La9
        L91:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
        La9:
            r9 = r2
        Laa:
            if (r9 != 0) goto Lad
            goto Lae
        Lad:
            r2 = r9
        Lae:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksh.a(int):java.lang.String");
    }

    @Override // com.huawei.hwidauth.utils.b.b
    public int b(int i) {
        int i2 = i == -1 ? 5 : 0;
        Class<?>[] clsArr = {Integer.TYPE};
        Object[] objArr = {Integer.valueOf(i)};
        try {
            Object e = e();
            return e != null ? ((Integer) e.getClass().getDeclaredMethod("getSimState", clsArr).invoke(e, objArr)).intValue() : i2;
        } catch (IllegalAccessException e2) {
            ksy.c("MutiCardHwImpl", " IllegalAccessException wrong " + e2.getClass().getSimpleName(), true);
            return i2;
        } catch (IllegalArgumentException e3) {
            ksy.c("MutiCardHwImpl", " IllegalArgumentException wrong " + e3.getClass().getSimpleName(), true);
            return i2;
        } catch (NoSuchMethodException e4) {
            ksy.c("MutiCardHwImpl", " NoSuchMethodException wrong " + e4.getClass().getSimpleName(), true);
            return i2;
        } catch (NullPointerException e5) {
            ksy.c("MutiCardHwImpl", " NullPointerException wrong " + e5.getClass().getSimpleName(), true);
            return i2;
        } catch (Throwable th) {
            ksy.c("MutiCardHwImpl", " getSimState wrong " + th.getClass().getSimpleName(), true);
            return i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00c7  */
    @Override // com.huawei.hwidauth.utils.b.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String c(int r9) {
        /*
            r8 = this;
            java.lang.String r0 = "getSimOperator exception:"
            java.lang.String r1 = "MutiCardHwImpl"
            java.lang.String r2 = ""
            java.lang.Class r3 = java.lang.Integer.TYPE
            java.lang.Class[] r3 = new java.lang.Class[]{r3}
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            r4 = 1
            java.lang.Object r5 = e()     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L47 java.lang.IllegalArgumentException -> L60 java.lang.IllegalAccessException -> L79 java.lang.NoSuchMethodException -> L92 java.lang.NullPointerException -> Lab
            if (r5 == 0) goto Lc3
            java.lang.Class r6 = r5.getClass()     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L47 java.lang.IllegalArgumentException -> L60 java.lang.IllegalAccessException -> L79 java.lang.NoSuchMethodException -> L92 java.lang.NullPointerException -> Lab
            java.lang.String r7 = "getSimOperator"
            java.lang.reflect.Method r3 = r6.getMethod(r7, r3)     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L47 java.lang.IllegalArgumentException -> L60 java.lang.IllegalAccessException -> L79 java.lang.NoSuchMethodException -> L92 java.lang.NullPointerException -> Lab
            java.lang.Object r9 = r3.invoke(r5, r9)     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L47 java.lang.IllegalArgumentException -> L60 java.lang.IllegalAccessException -> L79 java.lang.NoSuchMethodException -> L92 java.lang.NullPointerException -> Lab
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Exception -> L2d java.lang.reflect.InvocationTargetException -> L47 java.lang.IllegalArgumentException -> L60 java.lang.IllegalAccessException -> L79 java.lang.NoSuchMethodException -> L92 java.lang.NullPointerException -> Lab
            goto Lc4
        L2d:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto Lc3
        L47:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto Lc3
        L60:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto Lc3
        L79:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto Lc3
        L92:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
            goto Lc3
        Lab:
            r9 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            defpackage.ksy.c(r1, r9, r4)
        Lc3:
            r9 = r2
        Lc4:
            if (r9 != 0) goto Lc7
            goto Lc8
        Lc7:
            r2 = r9
        Lc8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksh.c(int):java.lang.String");
    }
}
