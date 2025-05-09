package com.huawei.health.baseapi.pluginaudiodevice;

import android.content.Context;

/* loaded from: classes3.dex */
public interface InitAudioDeviceKitApi {
    void initAudioDeviceKitAdapter(Context context);

    void initAudioDeviceKitAdapter(Context context, StateCallback<String> stateCallback);

    void obtainDeviceSn(Context context, String str, ResultCallback<String> resultCallback);

    void registerConnectStatus(Context context, String str, StateCallback<String> stateCallback);

    void unregisterConnectStatus(Context context, String str);
}
