package com.huawei.health.marketing.datatype;

import android.content.Context;
import android.widget.LinearLayout;
import defpackage.jdy;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: classes3.dex */
public class MarketingOption {
    private final WeakReference<Context> contextWeakReference;
    private String extendParam;
    private Map<Integer, LinearLayout> layoutMap;
    private final int pageId;
    private final Map<Integer, ResourceResultInfo> resultInfoMap;
    private final Map<String, ?> triggerEventParams;
    private final int triggerEventType;

    public MarketingOption(Builder builder) {
        this.contextWeakReference = new WeakReference<>(builder.context);
        this.layoutMap = builder.layoutMap;
        this.triggerEventParams = (Map) jdy.d(builder.triggerEventParams);
        this.pageId = builder.pageId;
        this.triggerEventType = builder.typeId;
        this.resultInfoMap = (Map) jdy.d(builder.resultInfoMap);
        this.extendParam = builder.extendParam;
    }

    public static class Builder {
        private Context context;
        private String extendParam;
        private Map<Integer, LinearLayout> layoutMap;
        private int pageId;
        private Map<Integer, ResourceResultInfo> resultInfoMap;
        private Map<String, ?> triggerEventParams;
        private int typeId;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTriggerEventParams(Map<String, ?> map) {
            this.triggerEventParams = (Map) jdy.d(map);
            return this;
        }

        public Builder setPageId(int i) {
            this.pageId = i;
            return this;
        }

        public Builder setTypeId(int i) {
            this.typeId = i;
            return this;
        }

        public Builder setLayoutMap(Map<Integer, LinearLayout> map) {
            this.layoutMap = map;
            return this;
        }

        public Builder setResultInfoMap(Map<Integer, ResourceResultInfo> map) {
            this.resultInfoMap = (Map) jdy.d(map);
            return this;
        }

        public Builder setExtendParam(String str) {
            this.extendParam = str;
            return this;
        }

        public MarketingOption build() {
            return new MarketingOption(this);
        }
    }

    public Context getContext() {
        return this.contextWeakReference.get();
    }

    public Map<String, ?> getTriggerEventParams() {
        return this.triggerEventParams;
    }

    public Map<Integer, LinearLayout> getLayoutMap() {
        return this.layoutMap;
    }

    public int getPageId() {
        return this.pageId;
    }

    public int getTriggerEventType() {
        return this.triggerEventType;
    }

    public Map<Integer, ResourceResultInfo> getResultInfoMap() {
        return this.resultInfoMap;
    }

    public String getExtendParam() {
        return this.extendParam;
    }

    public void cancel() {
        this.layoutMap = null;
    }
}
