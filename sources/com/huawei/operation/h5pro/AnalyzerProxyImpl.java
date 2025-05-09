package com.huawei.operation.h5pro;

import android.content.Context;
import com.huawei.health.h5pro.dfx.bi.AnalyzerProxy;
import defpackage.ixx;
import defpackage.iyb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AnalyzerProxyImpl implements AnalyzerProxy {
    private static final String BI_KEY_CONTENT_LOWER = "content";
    private static final String BI_KEY_CONTENT_UPPER = "CONTENT";
    private static final String BI_KEY_PAGE_PROPERTIES = "pageProperties";
    private static final String BI_KEY_SCENE = "scene";

    @Override // com.huawei.health.h5pro.dfx.bi.AnalyzerProxy
    public boolean putBiEvent(Context context, String str, LinkedHashMap<String, String> linkedHashMap, JSONObject jSONObject) {
        JSONObject jSONObject2;
        Map<String, Object> jsonObject2Map;
        if (!jSONObject.has(BI_KEY_PAGE_PROPERTIES) && !jSONObject.has(BI_KEY_SCENE)) {
            return false;
        }
        iyb iybVar = new iyb();
        JSONArray optJSONArray = jSONObject.optJSONArray(BI_KEY_PAGE_PROPERTIES);
        jSONObject.remove(BI_KEY_PAGE_PROPERTIES);
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(jsonObject2Map(optJSONArray.optJSONObject(i)));
            }
            iybVar.d(arrayList);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(BI_KEY_SCENE);
        jSONObject.remove(BI_KEY_SCENE);
        if (optJSONObject != null) {
            iybVar.e(jsonObject2Map(optJSONObject));
        }
        if (jSONObject.has("content")) {
            jSONObject2 = jSONObject.optJSONObject("content");
            jSONObject.remove("content");
        } else if (jSONObject.has(BI_KEY_CONTENT_UPPER)) {
            jSONObject2 = jSONObject.optJSONObject(BI_KEY_CONTENT_UPPER);
            jSONObject.remove(BI_KEY_CONTENT_UPPER);
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            jsonObject2Map = jsonObject2Map(jSONObject2);
        } else {
            jsonObject2Map = jsonObject2Map(jSONObject);
        }
        jsonObject2Map.putAll(linkedHashMap);
        iybVar.d(jsonObject2Map);
        ixx.d().a(context, str, iybVar, 0);
        return true;
    }

    private Map<String, Object> jsonObject2Map(JSONObject jSONObject) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            linkedHashMap.put(next, jSONObject.opt(next));
        }
        return linkedHashMap;
    }
}
