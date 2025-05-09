package com.huawei.health.algorithm.api;

import android.content.Context;
import com.huawei.health.algorithm.callback.IBaseResponseCallback;
import com.huawei.health.algorithm.callback.IConnectCallback;
import com.huawei.health.algorithm.callback.ISleepMonitorCallback;
import com.huawei.health.algorithm.callback.IStateCallback;
import defpackage.bzd;
import defpackage.bze;
import defpackage.bzg;
import defpackage.bzi;
import defpackage.bzl;
import java.util.Set;

/* loaded from: classes3.dex */
public interface SleepMonitorAlgorithmApi {
    public static final int STATE_FINISHED = 6;
    public static final int STATE_INTERRUPT = 5;
    public static final int STATE_NONE = 0;
    public static final int STATE_PREPARE = 1;
    public static final int STATE_RUNNING = 3;

    void connect(Context context, String str, IConnectCallback iConnectCallback, IStateCallback iStateCallback);

    void createReport(bze bzeVar, ISleepMonitorCallback iSleepMonitorCallback);

    void disconnect();

    boolean forceDeleteAudioFile(String str);

    bze getMonitorInfo();

    int getRegularScore(bzl bzlVar);

    bzg getSleepState();

    boolean isRecording();

    void pauseRecord(IBaseResponseCallback iBaseResponseCallback);

    Set<bzi> querySleepVoiceInfo(long j, long j2);

    void restoreRecord(IBaseResponseCallback iBaseResponseCallback);

    void restoreSleep(IBaseResponseCallback iBaseResponseCallback);

    void setAudioMaximumThreshold(int i);

    boolean setMonitorInfo(Set<bzi> set);

    void startSleepMonitor(bzd bzdVar, IBaseResponseCallback iBaseResponseCallback);

    void stopSleepMonitor(ISleepMonitorCallback iSleepMonitorCallback);
}
