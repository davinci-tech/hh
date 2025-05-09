package com.huawei.hms.ads.dynamicloader;

import android.content.Context;

/* loaded from: classes9.dex */
public final class f {
    private static Context a(Context context, String str, int i, String str2) {
        return "v2".equals(str2) ? new i(context, str, i) : new e(context, str, i);
    }
}
