package com.huawei.hwcloudjs.f;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes9.dex */
public class c {
    public static List<String> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.optString(i));
            }
        }
        return arrayList;
    }
}
