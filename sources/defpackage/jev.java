package defpackage;

import android.content.Context;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public class jev {
    private static final Object c = new Object();

    public static void e(Context context) {
        synchronized (c) {
            try {
                try {
                    System.loadLibrary("sqlcipher");
                } catch (UnsatisfiedLinkError e) {
                    ReleaseLogUtil.c("R_DatabaseLoadUtils", "loadLibs exception ", ExceptionUtils.d(e));
                    throw new UnsatisfiedLinkError("loadLib：libsqlcipher.so throw exception");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
