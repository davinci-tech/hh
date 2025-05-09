package com.huawei.openalliance.ad.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.ho;
import com.huawei.operation.utils.Constants;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes5.dex */
public abstract class p implements bp {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7715a = "p";

    protected abstract Object b();

    @Override // com.huawei.openalliance.ad.utils.bp
    public Pair<Integer, Pair<String, String>> a(int i) {
        Object b = b();
        if (b == null) {
            return null;
        }
        Class<?>[] clsArr = {Integer.TYPE};
        Object[] objArr = {Integer.valueOf(i)};
        Pair<String, String> a2 = a(b, clsArr, objArr);
        Integer b2 = b(b, clsArr, objArr);
        if (a2 != null) {
            return new Pair<>(b2, a2);
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.utils.bp
    public int a() {
        Object b = b();
        if (b == null) {
            return 0;
        }
        Object a2 = ck.a(b, "getPreferredDataSubscription", (Class<?>[]) null, (Object[]) null);
        if (a2 == null || !(a2 instanceof Integer)) {
            return -1;
        }
        return ((Integer) a2).intValue();
    }

    private Integer b(Object obj, Class<?>[] clsArr, Object[] objArr) {
        Object a2 = ck.a(obj, "getNetworkType", clsArr, objArr);
        if (a2 == null || !(a2 instanceof Integer)) {
            return 0;
        }
        return (Integer) a2;
    }

    static Object a(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(str);
            return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException unused) {
            str2 = f7715a;
            str3 = "getDefaultTelephoneManager ClassNotFoundException";
            ho.c(str2, str3);
            return null;
        } catch (IllegalAccessException unused2) {
            str2 = f7715a;
            str3 = "getDefaultTelephoneManager IllegalAccessException";
            ho.c(str2, str3);
            return null;
        } catch (IllegalArgumentException unused3) {
            str2 = f7715a;
            str3 = "getDefaultTelephoneManager IllegalArgumentException";
            ho.c(str2, str3);
            return null;
        } catch (NoSuchMethodException unused4) {
            str2 = f7715a;
            str3 = "getDefaultTelephoneManager NoSuchMethodException";
            ho.c(str2, str3);
            return null;
        } catch (SecurityException unused5) {
            str2 = f7715a;
            str3 = "getDefaultTelephoneManager SecurityException";
            ho.c(str2, str3);
            return null;
        } catch (InvocationTargetException unused6) {
            str2 = f7715a;
            str3 = "getDefaultTelephoneManager InvocationTargetException";
            ho.c(str2, str3);
            return null;
        } catch (Throwable th) {
            ho.c(f7715a, "getDefaultTelephoneManager error: " + th.getClass().getSimpleName());
            return null;
        }
    }

    private Pair<String, String> a(Object obj, Class<?>[] clsArr, Object[] objArr) {
        String str;
        String str2;
        Object a2 = ck.a(obj, "getNetworkOperator", clsArr, objArr);
        String str3 = (a2 == null || !(a2 instanceof String)) ? null : (String) a2;
        if (str3 == null || Constants.NULL.equalsIgnoreCase(str3) || str3.length() <= 3) {
            str = null;
            str2 = null;
        } else {
            str2 = str3.substring(0, 3);
            str = str3.substring(3);
        }
        if (str2 == null || str == null) {
            return null;
        }
        return new Pair<>(str2, str);
    }
}
