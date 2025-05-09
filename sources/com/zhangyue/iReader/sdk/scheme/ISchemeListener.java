package com.zhangyue.iReader.sdk.scheme;

/* loaded from: classes7.dex */
public interface ISchemeListener {
    public static final int NO_SUPPORT_APPLICATION = 400;
    public static final int PARAMETERS_ERROR = 401;
    public static final int SUCCEEDED = 200;
    public static final int URL_ERROR = 402;

    void onError(int i, String str);

    void onSuccess(Object obj);
}
