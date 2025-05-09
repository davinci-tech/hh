package com.huawei.health.suggestion.ui.fitness.helper;

/* loaded from: classes4.dex */
public interface MediaPlayCallBack {
    public static final int STATE_CUT = 4;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_PLAY = 1;
    public static final int STATE_START = 0;
    public static final int STATE_STOP = 3;
    public static final int TYPE_ID = 1;
    public static final int TYPE_PATH = 0;

    void mediaPlayCallBack(int i, int i2, int i3);
}
