package com.huawei.health.device.api;

import android.app.Activity;
import android.content.Context;
import com.huawei.health.device.callback.VersionNoSupportCallback;

/* loaded from: classes3.dex */
public interface VersionVerifyUtilApi {
    boolean isPublishVersion(String str, String str2);

    void noSupportDevice(Context context, Activity activity);

    void noSupportDevice(Context context, Activity activity, VersionNoSupportCallback versionNoSupportCallback);
}
