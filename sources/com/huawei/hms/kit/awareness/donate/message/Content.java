package com.huawei.hms.kit.awareness.donate.message;

import com.huawei.hms.kit.awareness.b.a.c;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class Content {
    private static final String TAG = "Content";
    private List<ContentData> contentData = Collections.emptyList();
    private String contentVersion;

    public String toJsonString() {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<ContentData> it = this.contentData.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
            return new JSONObject().put("contentVersion", this.contentVersion).put("contentData", jSONArray).toString();
        } catch (JSONException unused) {
            c.d(TAG, "Invalid content", new Object[0]);
            return "";
        }
    }

    public void setContentVersion(String str) {
        this.contentVersion = str;
    }

    public void setContentData(List<ContentData> list) {
        this.contentData = list;
    }

    public String getContentVersion() {
        return this.contentVersion;
    }

    public int getContentKeySize() {
        return this.contentData.stream().mapToInt(new ToIntFunction() { // from class: com.huawei.hms.kit.awareness.donate.message.Content$$ExternalSyntheticLambda0
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((ContentData) obj).getKeySize();
            }
        }).sum();
    }

    public List<ContentData> getContentData() {
        return this.contentData;
    }
}
