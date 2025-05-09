package com.huawei.hianalytics.framework.listener;

/* loaded from: classes4.dex */
public class HAEventManager {
    public static final HAEventManager b = new HAEventManager();

    /* renamed from: a, reason: collision with root package name */
    public IHAEventListener f3866a;

    public void setEventListener(IHAEventListener iHAEventListener) {
        this.f3866a = iHAEventListener;
    }

    public IHAEventListener getEventListener() {
        return this.f3866a;
    }

    public static HAEventManager getInstance() {
        return b;
    }
}
