package com.huawei.hms.update.download.api;

import android.content.Context;

/* loaded from: classes9.dex */
public interface IOtaUpdate {
    void cancel();

    void downloadPackage(IUpdateCallback iUpdateCallback, UpdateInfo updateInfo);

    Context getContext();
}
