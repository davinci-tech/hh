package com.huawei.health.h5pro.jsbridge.system.util;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface Util {
    JSONObject deviceInfo(Context context);

    boolean isAppInstalled(Context context, String str);
}
