package com.huawei.hms.network.file.core.util;

import android.os.Process;
import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class e {
    private static volatile e b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    g f5640a = new g(Process.myPid(), new SecureRandom().nextInt(16));

    public long a() {
        return this.f5640a.a();
    }

    public static e b() {
        if (b != null) {
            return b;
        }
        synchronized (c) {
            if (b == null) {
                b = new e();
            }
        }
        return b;
    }

    private e() {
    }
}
