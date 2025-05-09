package com.huawei.profile.coordinator.task;

import android.content.Context;

/* loaded from: classes6.dex */
public abstract class ProfileTask implements Runnable {
    public abstract String getName();

    public abstract void setContext(Context context);
}
