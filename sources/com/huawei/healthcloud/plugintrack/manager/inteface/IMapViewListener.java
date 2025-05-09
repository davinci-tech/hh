package com.huawei.healthcloud.plugintrack.manager.inteface;

/* loaded from: classes.dex */
public interface IMapViewListener {
    void pauseSport(int i);

    void resumeSport(int i);

    void startSport();

    void stopSport(boolean z);

    void updateGpsStatus(int i);

    void updateSportStatusWhenLockScreen(int i);
}
