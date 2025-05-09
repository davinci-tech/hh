package com.huawei.hms.push.utils.ha;

/* loaded from: classes9.dex */
public class PushAnalyticsCenter {

    /* renamed from: a, reason: collision with root package name */
    private PushBaseAnalytics f5694a;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static PushAnalyticsCenter f5695a = new PushAnalyticsCenter();
    }

    public static PushAnalyticsCenter getInstance() {
        return a.f5695a;
    }

    public PushBaseAnalytics getPushAnalytics() {
        return this.f5694a;
    }

    public void register(PushBaseAnalytics pushBaseAnalytics) {
        this.f5694a = pushBaseAnalytics;
    }
}
