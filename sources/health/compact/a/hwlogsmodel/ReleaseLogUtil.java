package health.compact.a.hwlogsmodel;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class ReleaseLogUtil {
    private ReleaseLogUtil() {
    }

    public static void e(String str, Object... objArr) {
        LogUtil.d(str, objArr);
    }

    public static void d(String str, Object... objArr) {
        LogUtil.g(str, objArr);
    }

    public static void c(String str, Object... objArr) {
        LogUtil.e(str, objArr);
    }
}
