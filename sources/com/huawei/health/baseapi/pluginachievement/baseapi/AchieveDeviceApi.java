package com.huawei.health.baseapi.pluginachievement.baseapi;

import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.pluginachievement.medal.model.TrackData;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public interface AchieveDeviceApi {
    void dealAchieveTrackData(ArrayList<TrackData> arrayList);

    void pingDevice(String str);

    void sendMessageToDevice(int i);

    void sendMessageToDevice(int i, String str, String str2, BaseResponseCallback<Boolean> baseResponseCallback);

    void sendMessageToDeviceForPackage(int i);

    void syncInit();
}
