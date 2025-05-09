package com.huawei.pluginsport.airopeskipping;

import android.content.Context;
import android.media.Image;

/* loaded from: classes6.dex */
public interface AiRopeSkippingApi {
    public static final int EXECUTE_FAIL = -1;
    public static final int EXECUTE_SUCCESS = 0;
    public static final int FRONT_FACING_CAMERA = 0;
    public static final int REAR_FACING_CAMERA = 1;

    int initModel(Context context, int i, int i2);

    int initModel(String str, int i, int i2);

    boolean reset();

    void setImage(Image image);

    int setModelInfo(int i, int i2, int i3);

    void setRopeListener(OnJumpRopeListenerWrapper onJumpRopeListenerWrapper);

    void setStage(int i);

    boolean unloadModel();
}
