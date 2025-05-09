package com.huawei.healthcloud.plugintrack.model;

/* loaded from: classes4.dex */
public interface TimeUpdater {
    public static final int TIME_INCREMENT_DEFAULT = 1000;

    String getFormattedTime();

    long getSportDuration();

    long getSportDurationBySecond();

    void increaseOrDecreaseSportDuration();
}
