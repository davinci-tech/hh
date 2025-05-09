package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;

/* loaded from: classes5.dex */
public class jdx {
    public static int b(Runnable runnable) {
        ThreadPoolManager.d().execute(runnable);
        return 0;
    }
}
