package com.huawei.harmonyos.interwork.base.ability;

/* loaded from: classes3.dex */
public interface IAbilityStartCallback {
    public static final int ABILITY_QUERY_FAILED = 1;
    public static final int ABILITY_START_SUCCESS = 0;
    public static final int FREE_INSTALL_NOT_SUPPORTED = 6;
    public static final int LOADING_CONFLICT = 5;
    public static final int LOADING_ERROR = 4;
    public static final int NETWORK_ERROR = 2;
    public static final int PARAMETER_INVALID = 9;
    public static final int PERMISSION_VERIFY_FAILED = 8;
    public static final int REMOTE_INCOMPATIBLE = 10;
    public static final int SERVICE_ERROR = 7;
    public static final int SYSTEM_ERROR = 3;

    void onStartResult(int i);
}
