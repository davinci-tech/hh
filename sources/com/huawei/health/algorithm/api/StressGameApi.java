package com.huawei.health.algorithm.api;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.huawei.health.algorithm.callback.StressGameBindCallback;
import com.huawei.health.algorithm.callback.StressGameNoticeInterface;

/* loaded from: classes3.dex */
public interface StressGameApi {
    void bindService(Context context, ServiceConnection serviceConnection);

    void closeMeasure();

    void openMeasure();

    void setStressGameServiceCallBack(IBinder iBinder, StressGameNoticeInterface stressGameNoticeInterface, StressGameBindCallback stressGameBindCallback);

    void startTimer();

    void stopTimer();

    void unbindService(Context context, ServiceConnection serviceConnection);
}
