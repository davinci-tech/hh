package com.huawei.health.pluginlocation.Utils;

import android.content.Context;
import java.util.LinkedHashMap;

/* loaded from: classes8.dex */
public interface BiApi {
    void init(Context context);

    void setEvent(String str, LinkedHashMap<String, String> linkedHashMap);

    void setEventWithReportImmediately(String str, LinkedHashMap<String, String> linkedHashMap);
}
