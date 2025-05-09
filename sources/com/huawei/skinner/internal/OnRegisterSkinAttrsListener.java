package com.huawei.skinner.internal;

/* loaded from: classes6.dex */
public interface OnRegisterSkinAttrsListener {
    public static final int EMPTY_SKIN_ATTRS = 30007;
    public static final int FORCE_INIT = 30001;
    public static final int INIT = 30000;
    public static final int REGISTER_BY_PLUGIN = 30002;
    public static final int REGISTER_BY_SCAN = 30003;
    public static final int REGISTER_FAILED = 30006;
    public static final int REGISTER_FROM_CACHE = 30004;
    public static final int REGISTER_SUCCESS = 30005;

    void onCompleted(int i, int i2);

    void onStart(int i);
}
