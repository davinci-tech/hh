package defpackage;

import com.huawei.hwappdfxmgr.threads.ThreadsCallBack;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public final class ixm {
    private static ThreadsCallBack d;

    public static void e(String str, long j) {
        ThreadsCallBack threadsCallBack = d;
        if (threadsCallBack != null) {
            threadsCallBack.dumpThreadsMessage(str, j);
        } else {
            LogUtil.a(str, "the mCallback is null");
        }
    }

    public static void c(ThreadsCallBack threadsCallBack) {
        d = threadsCallBack;
    }

    public static void c() {
        d = null;
    }
}
