package com.huawei.health.motiontrack.api;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import defpackage.ffd;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface TrackManagerApi {
    Drawable acquireSportTypeDrawable(int i);

    ViewHolderBase getChartViewHolder(Context context, int i);

    List<Integer> getFitnessSportTypeList(String str);

    int getHeartPostureType(int i);

    boolean getRunWorkoutStarted();

    int getSportState();

    void getTrackDraw(List<Map<Long, double[]>> list, IBaseResponseCallback iBaseResponseCallback);

    void getTrackDraw(List<Long> list, List<Long> list2, IBaseResponseCallback iBaseResponseCallback);

    void getUserRunLevelDataByRq(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback);

    boolean getVoiceEnable();

    void gotoSport(Context context, int i, int i2, float f);

    void initGolfDeviceEngineManager();

    void initTargetList(List<ffd> list);

    boolean isSportingStatus();

    void playSound(Object obj, String str);

    void playSound(String str, Object obj, String str2);

    void pourInterfaceToKitSportApi();

    void pushRqData(int i);

    MotionPath readTemporaryMotionPath(Context context, String str);

    void sendPlayerCommand(String str, int i);

    boolean sentHeartZoneSplicingMessages();

    void startNearTrackDetail();

    int startRunCourseSport(Context context, int i, int i2, float f, Bundle bundle);

    void startTrackDetail(long j, long j2);

    void startVoiceService(String str, String str2, int i);

    void stopVoiceService(String str);

    Map<Integer, Float> validPaceMap(Map<Integer, Float> map);
}
