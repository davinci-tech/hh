package com.huawei.hms.update.ui;

/* loaded from: classes9.dex */
public class ConfigChangeHolder {
    private static volatile ConfigChangeHolder b;

    /* renamed from: a, reason: collision with root package name */
    private boolean f6085a = false;

    private ConfigChangeHolder() {
    }

    public static ConfigChangeHolder getInstance() {
        if (b == null) {
            synchronized (ConfigChangeHolder.class) {
                if (b == null) {
                    b = new ConfigChangeHolder();
                }
            }
        }
        return b;
    }

    public boolean isChanged() {
        return this.f6085a;
    }

    public void setChanged(boolean z) {
        this.f6085a = z;
    }
}
