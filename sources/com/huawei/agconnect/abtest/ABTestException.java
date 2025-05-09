package com.huawei.agconnect.abtest;

import com.huawei.agconnect.exception.AGCException;

/* loaded from: classes2.dex */
public class ABTestException extends AGCException {
    public static final int HI_ANALYTICS_SDK_ERROR = 1;
    public static final int NOT_EXIST_KEY = 2;
    public static final int NUMBER_FORMAT_ERROR = 3;

    public ABTestException(String str, int i) {
        super(str, i);
    }
}
