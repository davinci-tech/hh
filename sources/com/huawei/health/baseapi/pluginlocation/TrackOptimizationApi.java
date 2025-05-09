package com.huawei.health.baseapi.pluginlocation;

import android.content.Context;
import com.huawei.health.baseapi.pluginlocation.callback.HwPluginCloudTrackCallback;
import com.huawei.health.baseapi.pluginlocation.callback.HwPluginTrackMapCallback;
import defpackage.exp;
import java.util.List;

/* loaded from: classes8.dex */
public interface TrackOptimizationApi {
    void getCloudTrack(HwPluginCloudTrackCallback hwPluginCloudTrackCallback);

    int getOptimizedTrack(String str, String str2, boolean z, HwPluginTrackMapCallback hwPluginTrackMapCallback, boolean z2, String str3);

    void postMotionPath(List<exp> list);

    void setExtraData(boolean z, boolean z2, Context context, String str);
}
