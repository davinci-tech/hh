package com.huawei.devicepair.api;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import defpackage.bgf;
import defpackage.bgh;

/* loaded from: classes3.dex */
public interface MessageNotificationApi {
    void enabledAppNotification(Context context, bgf bgfVar);

    boolean isAppNotificationEnabled(Context context, bgf bgfVar);

    boolean isMessagePermission(Context context);

    boolean isSupportNotify(String str);

    void queryApplicationInfoList(Context context, String str, IBaseResponseCallback iBaseResponseCallback);

    int queryMessageNotificationStatus();

    bgh queryNotificationRemind(String str);

    bgf setApplicationNotificationStatus(Context context, String str, String str2, int i);

    int setMessageNotificationStatus(Context context, String str, int i);

    boolean setNotificationRemindStatus(Context context, int i, int i2);
}
