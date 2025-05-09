package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public abstract class bj {
    public static void a(Context context, int i, String str) {
        if (i < 4) {
            i = 4;
        }
        if (TextUtils.isEmpty(str)) {
            str = cw.a(context);
            if (TextUtils.isEmpty(str)) {
                ho.c("LogTool", "enable log failed, due to root path is null");
                return;
            }
        }
        ho.a(i, str, "HiAd");
    }

    public static void a(Context context) {
        a(context, 4, null);
    }
}
