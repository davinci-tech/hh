package defpackage;

import com.huawei.hwidauth.utils.b.b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public final class ksn implements b {
    private static ksn d;

    private ksn() {
    }

    public static ksn e() {
        ksn ksnVar;
        synchronized (ksn.class) {
            if (d == null) {
                d = new ksn();
            }
            ksnVar = d;
        }
        return ksnVar;
    }

    private static int b() {
        try {
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            Method declaredMethod = cls.getDeclaredMethod("getDefaultSim", null);
            Object invoke = cls.getDeclaredMethod("getDefault", null).invoke(null, null);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(invoke, null)).intValue();
        } catch (Error e) {
            ksy.a("mutiCardMTKImpl", "" + e.getClass().getSimpleName(), true);
            return -1;
        } catch (Exception e2) {
            ksy.a("mutiCardMTKImpl", "" + e2.getClass().getSimpleName(), true);
            return -1;
        }
    }

    private static Object d() {
        try {
            Class<?> cls = Class.forName("com.mediatek.telephony.TelephonyManagerEx");
            return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            ksy.a("mutiCardMTKImpl", " getDefaultTelephonyManagerEx wrong " + e.getClass().getSimpleName(), true);
            return null;
        }
    }

    @Override // com.huawei.hwidauth.utils.b.b
    public int a() {
        return b();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0096  */
    @Override // com.huawei.hwidauth.utils.b.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(int r10) {
        /*
            r9 = this;
            java.lang.String r0 = "getSubscriberId exception:"
            java.lang.String r1 = "mutiCardMTKImpl"
            java.lang.String r2 = ""
            java.lang.Class r3 = java.lang.Integer.TYPE
            java.lang.Class[] r3 = new java.lang.Class[]{r3}
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            r5 = 1
            java.lang.Object r6 = d()     // Catch: java.lang.Exception -> L2c java.lang.IllegalArgumentException -> L45 java.lang.IllegalAccessException -> L5e java.lang.NoSuchMethodException -> L77
            if (r6 == 0) goto L8f
            java.lang.Class r7 = r6.getClass()     // Catch: java.lang.Exception -> L2c java.lang.IllegalArgumentException -> L45 java.lang.IllegalAccessException -> L5e java.lang.NoSuchMethodException -> L77
            java.lang.String r8 = "getSubscriberId"
            java.lang.reflect.Method r3 = r7.getMethod(r8, r3)     // Catch: java.lang.Exception -> L2c java.lang.IllegalArgumentException -> L45 java.lang.IllegalAccessException -> L5e java.lang.NoSuchMethodException -> L77
            java.lang.Object r3 = r3.invoke(r6, r4)     // Catch: java.lang.Exception -> L2c java.lang.IllegalArgumentException -> L45 java.lang.IllegalAccessException -> L5e java.lang.NoSuchMethodException -> L77
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L2c java.lang.IllegalArgumentException -> L45 java.lang.IllegalAccessException -> L5e java.lang.NoSuchMethodException -> L77
            goto L90
        L2c:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r0)
            java.lang.Class r0 = r3.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            defpackage.ksy.c(r1, r0, r5)
            goto L8f
        L45:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r0)
            java.lang.Class r0 = r3.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            defpackage.ksy.c(r1, r0, r5)
            goto L8f
        L5e:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r0)
            java.lang.Class r0 = r3.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            defpackage.ksy.c(r1, r0, r5)
            goto L8f
        L77:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r0)
            java.lang.Class r0 = r3.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            defpackage.ksy.c(r1, r0, r5)
        L8f:
            r3 = r2
        L90:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L9a
            java.lang.String r3 = r9.d(r10)
        L9a:
            if (r3 != 0) goto L9d
            goto L9e
        L9d:
            r2 = r3
        L9e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksn.a(int):java.lang.String");
    }

    private String d(int i) {
        ksy.b("mutiCardMTKImpl", "getMTKPlmn", true);
        Class<?>[] clsArr = {Integer.TYPE};
        try {
            int intValue = ((Integer) Class.forName("android.telephony.SubscriptionManager").getMethod("getSlotId", clsArr).invoke(null, Integer.valueOf(i))).intValue();
            if (intValue == -1) {
                return null;
            }
            Object d2 = d();
            return (String) d2.getClass().getMethod("getSimOperator", clsArr).invoke(d2, Integer.valueOf(intValue));
        } catch (ClassNotFoundException unused) {
            ksy.c("mutiCardMTKImpl", "ClassNotFoundException", true);
            return null;
        } catch (IllegalAccessException unused2) {
            ksy.c("mutiCardMTKImpl", "IllegalAccessException", true);
            return null;
        } catch (NoSuchMethodException unused3) {
            ksy.c("mutiCardMTKImpl", "NoSuchMethodException", true);
            return null;
        } catch (InvocationTargetException unused4) {
            ksy.c("mutiCardMTKImpl", "InvocationTargetException", true);
            return null;
        }
    }

    @Override // com.huawei.hwidauth.utils.b.b
    public int b(int i) {
        Class<?>[] clsArr = {Integer.TYPE};
        Object[] objArr = {Integer.valueOf(i)};
        try {
            Object d2 = d();
            if (d2 != null) {
                return ((Integer) d2.getClass().getDeclaredMethod("getSimState", clsArr).invoke(d2, objArr)).intValue();
            }
        } catch (IllegalArgumentException e) {
            ksy.c("mutiCardMTKImpl", " getSimState wrong " + e.getClass().getSimpleName(), true);
        } catch (NoSuchMethodException e2) {
            ksy.c("mutiCardMTKImpl", " getSimState wrong " + e2.getClass().getSimpleName(), true);
        } catch (InvocationTargetException e3) {
            ksy.c("mutiCardMTKImpl", " getSimState wrong " + e3.getClass().getSimpleName(), true);
        } catch (Throwable th) {
            ksy.c("mutiCardMTKImpl", " getSimState wrong " + th.getClass().getSimpleName(), true);
        }
        return 0;
    }

    @Override // com.huawei.hwidauth.utils.b.b
    public String c(int i) {
        return "";
    }
}
