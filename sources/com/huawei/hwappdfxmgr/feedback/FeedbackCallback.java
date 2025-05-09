package com.huawei.hwappdfxmgr.feedback;

/* loaded from: classes9.dex */
public interface FeedbackCallback {
    public static final int CONFIGURATION_MODULE_ERROR = 3;
    public static final int FEEDBACKSDK_INIT_RESULT = 16;
    public static final int FEEDBACKSDK_INIT_SUCCESS = 0;
    public static final int FUNCTION_MODULE_INTERFACE_ERROR = 6;
    public static final int PARAMETER_ERROR = 1;
    public static final int ROUTE_ADDRESS_INTERFACE_ERROR = 5;
    public static final int SERVER_ADDRESS_ERROR = 2;
    public static final int SERVER_ADDRESS_FROM_GRS_ERROR = 4;

    void onCallbackFail(int i);

    void onCallbackSuccess();
}
