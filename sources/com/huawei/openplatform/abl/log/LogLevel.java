package com.huawei.openplatform.abl.log;

import androidx.exifinterface.media.ExifInterface;

/* loaded from: classes5.dex */
public abstract class LogLevel {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int WARN = 5;

    public static String priorityToString(int i) {
        return i != 3 ? i != 4 ? i != 5 ? i != 6 ? String.valueOf(i) : ExifInterface.LONGITUDE_EAST : "W" : "I" : "D";
    }
}
