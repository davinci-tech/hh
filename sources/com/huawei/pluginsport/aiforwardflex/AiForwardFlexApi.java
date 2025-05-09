package com.huawei.pluginsport.aiforwardflex;

import android.content.Context;
import android.media.Image;
import androidx.collection.SimpleArrayMap;

/* loaded from: classes6.dex */
public interface AiForwardFlexApi {
    public static final int FRONT_FACING_CAMERA = 0;
    public static final int INVALID_VALUE = -1;
    public static final int REAR_FACING_CAMERA = 1;
    public static final int SUCCESS = 0;

    int initModel(Context context, int i, int i2);

    void setExamStage(int i);

    void setExamType(int i);

    void setImage(Image image);

    int setModelInfo(int i, int i2, int i3);

    void setSkeletonListener(OnSportSkeletonListenerWrapper onSportSkeletonListenerWrapper);

    void setSportCodeListener(OnSportCodeListenerWrapper onSportCodeListenerWrapper);

    int setSurfaceRotation(int i);

    void setValue(SimpleArrayMap<String, Object> simpleArrayMap);

    boolean unloadModel();
}
