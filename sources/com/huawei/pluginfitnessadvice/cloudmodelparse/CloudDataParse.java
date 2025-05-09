package com.huawei.pluginfitnessadvice.cloudmodelparse;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public interface CloudDataParse<T> {
    T parse(String str, JSONObject jSONObject);

    List<T> parse(String str, JSONArray jSONArray);
}
