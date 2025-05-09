package com.huawei.haf.mediaprocess;

/* loaded from: classes.dex */
public class VideoProcessException extends Exception {
    public VideoProcessException(String str) {
        super(str);
    }

    public VideoProcessException(Exception exc) {
        super(exc.getMessage(), exc.getCause());
    }
}
