package com.huawei.health.trackprocess.api;

import android.content.Context;
import com.huawei.health.trackprocess.callback.PluginCloudTrackCallback;
import com.huawei.health.trackprocess.callback.PluginTrackMapCallback;
import defpackage.gki;
import defpackage.gkm;
import defpackage.gkn;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface PluginLocationApi {
    void getCloudTrack(PluginCloudTrackCallback pluginCloudTrackCallback);

    gki getEphRequest(List<String> list);

    Map<String, String> getEphResponse(gkm gkmVar);

    int getOptimizedTrack(String str, String str2, boolean z, PluginTrackMapCallback pluginTrackMapCallback, boolean z2, String str3);

    void pluginRxnGenerateEphemeris();

    void pluginRxnNativeInit();

    void postMotionPath(List<gkn> list);

    void setContext(Context context);

    void setExtraData(boolean z, boolean z2, Context context, String str);

    void setSerCountry(String str);
}
