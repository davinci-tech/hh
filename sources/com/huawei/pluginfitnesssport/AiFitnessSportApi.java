package com.huawei.pluginfitnesssport;

import android.content.Context;
import android.media.Image;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;

/* loaded from: classes6.dex */
public interface AiFitnessSportApi {
    int getSportVersion();

    int initModel(Context context, int i, int i2);

    void registerSensor(Context context, OnSensorListenerWrapper onSensorListenerWrapper);

    void setCartoonView(int i, int i2);

    void setCheckStandListener(OnCheckStandListenerWrapper onCheckStandListenerWrapper);

    void setImage(Image image);

    int setModelInfo(int i, int i2, int i3);

    int setMotionTemplate(String str);

    void setPause();

    void setPoseResult(BaseResponseCallback baseResponseCallback);

    void setResume();

    void setSportSkeletonListener(OnSportSkeletonListenerWrapper onSportSkeletonListenerWrapper);

    void setTrainSkeletonListener(OnTrainSkeletonListenerWrapper onTrainSkeletonListenerWrapper);

    void setType(int i);

    boolean unloadModel();

    void unregisterSensor(Context context);
}
