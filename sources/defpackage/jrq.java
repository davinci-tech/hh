package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;

/* loaded from: classes5.dex */
public class jrq {
    private static ThreadPoolManager d = ThreadPoolManager.a(2, 4);

    public static void b(String str, Runnable runnable) {
        d.d(str, runnable);
    }
}
