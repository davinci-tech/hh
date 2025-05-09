package com.huawei.haf.bundle;

import android.content.Context;

/* loaded from: classes.dex */
public interface AppBundleSetting {
    boolean isAllowAutoUpdateModule();

    boolean isAllowDownloadModule(Context context, String str);

    boolean isAllowUsingMobileUpdateModule();

    void setAllowAutoUpdateModule(boolean z);

    void setAllowDownloadModule(Context context, String str, boolean z);

    void setAllowUsingMobileUpdateModule(boolean z);

    void setTestEnvironment(String str, boolean z, String str2);

    void updateModuleState(Context context, String str, boolean z);
}
