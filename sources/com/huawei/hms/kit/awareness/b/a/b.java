package com.huawei.hms.kit.awareness.b.a;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4823a = "ConcurrentUtils";

    /* renamed from: com.huawei.hms.kit.awareness.b.a.b$b, reason: collision with other inner class name */
    public static class C0125b {

        /* renamed from: a, reason: collision with root package name */
        private final CountDownLatch f4825a;

        public final boolean c() {
            return this.f4825a.getCount() == 0;
        }

        public final void b() {
            this.f4825a.countDown();
        }

        public final boolean a(long j) {
            return b.b(this.f4825a, j);
        }

        public static C0125b a(int i) {
            return new C0125b(i);
        }

        private C0125b(int i) {
            this.f4825a = new CountDownLatch(i);
        }
    }

    public static final class a extends C0125b {

        /* renamed from: a, reason: collision with root package name */
        private static final int f4824a = 1;

        public static a a() {
            return new a();
        }

        private a() {
            super(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(CountDownLatch countDownLatch, long j) {
        try {
            return countDownLatch.await(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            c.d(f4823a, "CountDownLatch Waiting Failed.", new Object[0]);
            return false;
        }
    }

    private b() {
    }
}
