package com.huawei.android.airsharing.api;

import android.content.Context;

/* loaded from: classes2.dex */
public interface IPlayerManager extends IServerManager {
    HwMediaPosition getPosition();

    HwServer getRenderingServer();

    int getVolume();

    boolean init(Context context);

    boolean isRendering();

    boolean pause();

    boolean play(HwMediaInfo hwMediaInfo, ProjectionDevice projectionDevice);

    boolean playMedia(HwMediaInfo hwMediaInfo, boolean z, HwObject hwObject);

    boolean playMedia(String str, String str2, EHwMediaInfoType eHwMediaInfoType, String str3, int i);

    boolean resume();

    boolean seek(String str);

    boolean setVolume(int i);

    boolean stop();
}
