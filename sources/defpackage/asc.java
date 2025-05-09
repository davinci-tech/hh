package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;

/* loaded from: classes.dex */
public class asc {
    private static volatile asc e;

    private asc() {
    }

    public static asc e() {
        if (e == null) {
            synchronized (asc.class) {
                if (e == null) {
                    e = new asc();
                }
            }
        }
        return e;
    }

    public void b(Runnable runnable) {
        ThreadPoolManager.d().execute(runnable);
    }
}
