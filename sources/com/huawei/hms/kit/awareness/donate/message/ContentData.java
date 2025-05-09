package com.huawei.hms.kit.awareness.donate.message;

import android.util.ArrayMap;
import com.huawei.hms.kit.awareness.b.a.c;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ContentData {
    private int payloadKeySize = 0;
    private Map<String, String> header = new ArrayMap();
    private Map<String, Object> payload = new ArrayMap();

    public JSONObject toJson() {
        return new JSONObject().put("header", new JSONObject(this.header)).put("payload", new JSONObject(this.payload));
    }

    public ContentData setPayload(String str, String[] strArr) {
        this.payload.put(str, strArr);
        this.payloadKeySize++;
        return this;
    }

    public ContentData setPayload(String str, JSONObject jSONObject) {
        this.payload.put(str, jSONObject);
        this.payloadKeySize += jSONObject == null ? 1 : jSONObject.length();
        return this;
    }

    public ContentData setPayload(String str, String str2) {
        this.payload.put(str, str2);
        this.payloadKeySize++;
        return this;
    }

    public ContentData setPayload(String str, Boolean bool) {
        this.payload.put(str, bool);
        this.payloadKeySize++;
        return this;
    }

    public ContentData setHeader(String str, String str2) {
        this.header.put(str, str2);
        return this;
    }

    public Map<String, Object> getPayload() {
        return this.payload;
    }

    public int getKeySize() {
        return this.header.keySet().size() + this.payloadKeySize;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public JSONObject getDefaultEventCommonInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timeZone", TimeZone.getDefault().getDisplayName(false, 0));
            jSONObject.put("createTime", System.currentTimeMillis());
        } catch (JSONException unused) {
            c.d("ContentData", "EventCommonInfo init error.", new Object[0]);
        }
        return jSONObject;
    }
}
