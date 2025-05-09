package com.huawei.phoneservice.feedbackcommon.utils;

/* loaded from: classes6.dex */
public interface OnReadListener {
    void read(Throwable th, String str);

    void unread(Throwable th, String str, int i);
}
