package com.huawei.health.suggestion;

import android.content.Context;
import android.os.Bundle;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.pluginfitnessadvice.FitWorkout;

/* loaded from: classes8.dex */
public interface RunAdapter {
    void continueBackgroundMusic(String str);

    String getDeviceModel();

    boolean isRealTimeGuidance();

    void pauseBackgroundMusic(String str);

    void playBackgroundMusic(String str, String str2);

    void playStateSound(int i, String str);

    void playStateSound(String str, String str2);

    void playStateSound(int[] iArr, String str);

    void playStateSound(String[] strArr, String str);

    void registerTargetUpdateListener(ITargetUpdateListener iTargetUpdateListener);

    void showDetails(String str);

    void showTrackAfterSketch();

    void start(Bundle bundle);

    void start(String str, RunWorkout runWorkout, RunCallback runCallback, Context context);

    boolean start(Context context, FitWorkout fitWorkout, RecordPlanInfo recordPlanInfo);

    void stopPlayAudio(String str);
}
