package com.huawei.pluginfitnessadvice.cloudmodelparse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes6.dex */
public abstract class CloudDataParseBase<T> implements CloudDataParse<T> {
    protected int mType;

    public CloudDataParseBase(int i) {
        this.mType = i;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<T> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            T parse = parse(str, jSONArray.optJSONObject(i));
            if (parse != null) {
                arrayList.add(parse);
            }
        }
        return arrayList;
    }
}
