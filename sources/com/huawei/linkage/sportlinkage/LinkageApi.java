package com.huawei.linkage.sportlinkage;

/* loaded from: classes5.dex */
public interface LinkageApi {
    default void initSport(int i, int i2, int i3) {
    }

    void pause();

    void resume();

    default void resumeLinkage() {
    }

    default void start(int i) {
    }

    default void startLinkage(int i, int i2) {
    }

    void stop();

    void stopLinkage();
}
