package com.huawei.hihealth.listener;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;
import com.huawei.hihealth.model.Notification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public interface HiSubscribeCallback {
    public static final String TAG = "HiSubscribeCallback";

    void onDataChanged(Notification notification);

    void onResult(List<Notification> list, List<Notification> list2);

    default void onResultHandler(List<Notification> list, List<Notification> list2) {
        try {
            onResult(list, list2);
        } catch (Exception unused) {
            Log.w(TAG, HiHealthError.e(33));
            onResult(new ArrayList(), Arrays.asList(new Notification(0, 33, HiHealthError.e(33))));
        }
    }

    default void onDataChangedHandler(Notification notification) {
        try {
            onDataChanged(notification);
        } catch (Exception unused) {
            Log.w(TAG, HiHealthError.e(33));
            onDataChanged(new Notification(notification.getDataType(), 33, HiHealthError.e(33)));
        }
    }
}
