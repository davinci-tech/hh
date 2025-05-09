package com.huawei.hihealth.api;

import android.content.Context;

/* loaded from: classes.dex */
public interface LogApi {
    void d(String str, Object... objArr);

    void e(String str, Object... objArr);

    String getCurrentActivity();

    void i(String str, Object... objArr);

    boolean isBeta();

    boolean isSystemInfoAuthorized(Context context);

    String logAnonymousException(Exception exc);

    void v(String str, Object... objArr);

    void w(String str, Object... objArr);
}
