package com.huawei.hianalytics.visual.autocollect.exposure;

import android.view.View;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ViewMark {
    public JSONObject eventAttributes;
    public final String eventId;
    public String globalId;
    public final WeakReference<View> view;
    public float visibleScale = 0.0f;

    public ViewMark(View view, String str) {
        this.view = new WeakReference<>(view);
        this.eventId = str;
    }

    public JSONObject getEventAttributes() {
        JSONObject jSONObject = this.eventAttributes;
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getGlobalId() {
        return this.globalId;
    }

    public View getView() {
        return this.view.get();
    }

    public float getVisibleScale() {
        return this.visibleScale;
    }

    public ViewMark setEventAttributes(JSONObject jSONObject) {
        this.eventAttributes = jSONObject;
        return this;
    }

    public ViewMark setGlobalId(String str) {
        this.globalId = str;
        return this;
    }

    public ViewMark setVisibleScale(float f) {
        this.visibleScale = f;
        return this;
    }
}
