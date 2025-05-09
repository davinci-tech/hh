package com.huawei.pluginsleepdetection;

import android.content.Context;

/* loaded from: classes6.dex */
public interface SleepRecordNotifyApi {
    void cancelAlarm();

    void closeNotification();

    String getCacheKey();

    boolean isInTimeRange();

    void pullUpAlarm(Context context);

    void showNotification(Context context);
}
