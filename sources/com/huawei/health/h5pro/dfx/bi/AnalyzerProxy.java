package com.huawei.health.h5pro.dfx.bi;

import android.content.Context;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public interface AnalyzerProxy {
    boolean putBiEvent(Context context, String str, LinkedHashMap<String, String> linkedHashMap, JSONObject jSONObject);
}
