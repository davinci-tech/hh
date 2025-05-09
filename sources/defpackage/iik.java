package defpackage;

import android.os.Build;
import android.text.TextUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

/* loaded from: classes4.dex */
public class iik {
    public static String a() {
        String e = e();
        return !TextUtils.isEmpty(e) ? e : Build.MODEL;
    }

    private static String e() {
        try {
            Object c = c("android.os.SystemProperties", "get", new Class[]{String.class}, new Object[]{"ro.config.marketing_name"});
            if (c != null && (c instanceof String)) {
                return (String) c;
            }
        } catch (ClassCastException e) {
            ReleaseLogUtil.d("HiH_HiHealthDBHelper", e.getMessage());
        } catch (Exception e2) {
            ReleaseLogUtil.d("HiH_HiHealthDBHelper", "getMarketingName Exception", LogAnonymous.b((Throwable) e2));
        }
        return "";
    }

    private static Object c(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return d(Class.forName(str), str2, clsArr, objArr);
        } catch (ClassNotFoundException e) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "ClassNotFoundException" + e.getMessage(), e);
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "staticFun Exception", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static Object d(Class cls, String str, Class[] clsArr, Object[] objArr) throws Exception {
        b(cls, clsArr, objArr);
        try {
            try {
                return cls.getMethod(str, clsArr).invoke(null, objArr);
            } catch (IllegalAccessException e) {
                ReleaseLogUtil.c("HiH_HiHealthDBHelper", "IllegalAccessException" + e.getMessage(), e);
                return null;
            } catch (IllegalArgumentException e2) {
                ReleaseLogUtil.c("HiH_HiHealthDBHelper", "IllegalArgumentException" + e2.getMessage(), e2);
                return null;
            } catch (InvocationTargetException e3) {
                ReleaseLogUtil.c("HiH_HiHealthDBHelper", "InvocationTargetException" + e3.getMessage(), e3);
                return null;
            }
        } catch (NoSuchMethodException e4) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "NoSuchMethodException" + e4.getMessage(), e4);
        } catch (SecurityException e5) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "SecurityException" + e5.getMessage(), e5);
        } catch (Exception e6) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "staticFun Exception", LogAnonymous.b((Throwable) e6));
        }
    }

    private static void b(Class cls, Class[] clsArr, Object[] objArr) throws InvalidParameterException {
        if (cls == null) {
            throw new InvalidParameterException("class is null in staticFun");
        }
        if (clsArr == null) {
            if (objArr != null) {
                throw new InvalidParameterException("paramsType is null, but params is not null");
            }
        } else {
            if (objArr == null) {
                throw new InvalidParameterException("paramsType or params should be same");
            }
            if (clsArr.length == objArr.length) {
                return;
            }
            throw new InvalidParameterException("paramsType len:" + clsArr.length + " should equal params.len:" + objArr.length);
        }
    }
}
