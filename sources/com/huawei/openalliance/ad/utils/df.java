package com.huawei.openalliance.ad.utils;

/* loaded from: classes5.dex */
public class df implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private static com.huawei.openalliance.ad.cs f7696a;
    private Runnable b;

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.b;
        if (runnable != null) {
            try {
                runnable.run();
            } finally {
                try {
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
    }

    public static void a(com.huawei.openalliance.ad.cs csVar) {
        f7696a = csVar;
    }

    public df(Runnable runnable) {
        this.b = runnable;
    }
}
