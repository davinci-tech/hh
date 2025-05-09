package com.huawei.appgallery.agd.internal.support.log;

import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes2.dex */
public class AgdLog {
    public static final AgdLog LOG = new AgdLog();

    private boolean b(int i) {
        return i > 3;
    }

    public void w(String str, String str2, Throwable th) {
        b(5, str, str2, th);
    }

    public void w(String str, String str2) {
        b(5, str, str2, null);
    }

    public void i(String str, String str2, Throwable th) {
        b(4, str, str2, th);
    }

    public void i(String str, String str2) {
        b(4, str, str2, null);
    }

    public void e(String str, String str2, Throwable th) {
        b(6, str, str2, th);
    }

    public void e(String str, String str2) {
        b(6, str, str2, null);
    }

    public void d(String str, String str2, Throwable th) {
        b(3, str, str2, th);
    }

    public void d(String str, String str2) {
        b(3, str, str2, null);
    }

    private void b(int i, String str, String str2, Throwable th) {
        if (str2 == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = "[" + str + "] " + str2;
        }
        if (th != null) {
            str2 = str2 + th.getMessage();
        }
        if (b(i)) {
            Log.println(i, "AgdLog", str2.replace('\n', '_').replace('\r', '_'));
        }
    }
}
