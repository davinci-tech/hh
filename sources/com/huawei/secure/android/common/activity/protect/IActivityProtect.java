package com.huawei.secure.android.common.activity.protect;

import android.os.Message;

/* loaded from: classes9.dex */
public interface IActivityProtect {
    void finishLaunchActivity(Message message);

    void finishPauseActivity(Message message);

    void finishResumeActivity(Message message);

    void finishStopActivity(Message message);
}
