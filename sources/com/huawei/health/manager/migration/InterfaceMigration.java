package com.huawei.health.manager.migration;

import android.content.Context;

/* loaded from: classes.dex */
public interface InterfaceMigration {
    boolean filter(String str);

    void migration(Context context);
}
