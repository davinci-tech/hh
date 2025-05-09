package health.compact.a;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class FileSensitiveProtectUtil {
    public static void c() {
        File databasePath = BaseApplication.getContext().getDatabasePath("sensitive");
        if (databasePath.exists()) {
            return;
        }
        BaseApplication.getContext().getDatabasePath(new File(databasePath, "hihealth_sensitive.db").getPath());
        if (e()) {
            b(databasePath.getPath());
        }
    }

    public static String e(String str) {
        return BaseApplication.getContext().getDatabasePath(str).getParent() + File.separatorChar + "sensitive" + File.separatorChar + str;
    }

    private static void b(String str) {
        try {
            try {
                try {
                    try {
                        Class<?> cls = Class.forName("com.huawei.fileprotect.HwSfpPolicyManager");
                        cls.getMethod("setSecePolicy", Context.class, String.class).invoke(cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]), BaseApplication.getContext(), str);
                        com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
                    } catch (NoSuchMethodException e) {
                        com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "NoSuchMethodException e: ", e.getMessage());
                        com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
                    } catch (InvocationTargetException e2) {
                        e = e2;
                        com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "Exception e: ", e.getMessage());
                        com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
                    }
                } catch (ClassNotFoundException e3) {
                    com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "ClassNotFoundException e: ", e3.getMessage());
                    com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
                } catch (IllegalAccessException e4) {
                    e = e4;
                    com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "Exception e: ", e.getMessage());
                    com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
                }
            } catch (IllegalArgumentException e5) {
                e = e5;
                com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "Exception e: ", e.getMessage());
                com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
            } catch (SecurityException e6) {
                com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "SecurityException e: ", e6.getMessage());
                com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
            }
        } catch (Throwable th) {
            com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "setDirectorySecurityLevel SECE end!");
            throw th;
        }
    }

    public static boolean e() {
        Object c;
        try {
            c = ReflectionUtils.c(Class.forName("com.huawei.fileprotect.HwSfpPolicyManager"), "isSupportIudf");
        } catch (ClassNotFoundException e) {
            com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "ClassNotFoundException e: ", e.getMessage());
        } catch (IllegalArgumentException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("FileSensitiveProtectUtil", "Exception e: ", e2.getMessage());
        } finally {
            com.huawei.hwlogsmodel.LogUtil.a("FileSensitiveProtectUtil", "isSupport end!");
        }
        if (c instanceof Boolean) {
            return ((Boolean) c).booleanValue();
        }
        return false;
    }
}
