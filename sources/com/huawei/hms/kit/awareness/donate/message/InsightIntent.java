package com.huawei.hms.kit.awareness.donate.message;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class InsightIntent {
    private static final int INITIAL_CAPACITY = 2;
    private static final int MAX_CAPACITY = 20;
    private static final String PAYLOAD_KEY_CUSTOM_FORM_INFO = "customFormInfo";
    private static final String PAYLOAD_KEY_INTENT_ACTION_INFO = "intentActionInfo";
    private static final String PAYLOAD_KEY_INTENT_ENTITY_INFO = "intentEntityInfo";
    private static final String PAYLOAD_KEY_INTENT_TARGET_INFO = "intentTargetInfo";
    private String identifier;
    private final Map<String, JSONObject> intentInfo = new HashMap(2);
    private String intentName;
    private String intentVersion;

    public InsightIntent setIntentVersion(String str) {
        this.intentVersion = str;
        return this;
    }

    public InsightIntent setIntentTargetInfo(JSONObject jSONObject) {
        return addIntentInfo(PAYLOAD_KEY_INTENT_TARGET_INFO, jSONObject);
    }

    public InsightIntent setIntentName(String str) {
        this.intentName = str;
        return this;
    }

    public InsightIntent setIntentEntityInfo(JSONObject jSONObject) {
        return addIntentInfo(PAYLOAD_KEY_INTENT_ENTITY_INFO, jSONObject);
    }

    public InsightIntent setIntentActionInfo(JSONObject jSONObject) {
        return addIntentInfo(PAYLOAD_KEY_INTENT_ACTION_INFO, jSONObject);
    }

    public InsightIntent setIdentifier(String str) {
        this.identifier = str;
        return this;
    }

    public InsightIntent setCustomFormInfo(JSONObject jSONObject) {
        return addIntentInfo(PAYLOAD_KEY_CUSTOM_FORM_INFO, jSONObject);
    }

    public String getIntentVersion() {
        return this.intentVersion;
    }

    public JSONObject getIntentTargetInfo() {
        return this.intentInfo.getOrDefault(PAYLOAD_KEY_INTENT_TARGET_INFO, new JSONObject());
    }

    public String getIntentName() {
        return this.intentName;
    }

    public Map<String, JSONObject> getIntentInfo() {
        return this.intentInfo;
    }

    public JSONObject getIntentEntityInfo() {
        return this.intentInfo.getOrDefault(PAYLOAD_KEY_INTENT_ENTITY_INFO, new JSONObject());
    }

    public JSONObject getIntentActionInfo() {
        return this.intentInfo.getOrDefault(PAYLOAD_KEY_INTENT_ACTION_INFO, new JSONObject());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public JSONObject getCustomFormInfo() {
        return this.intentInfo.getOrDefault(PAYLOAD_KEY_CUSTOM_FORM_INFO, new JSONObject());
    }

    public InsightIntent addIntentInfo(String str, JSONObject jSONObject) {
        if (!TextUtils.isEmpty(str) && (this.intentInfo.containsKey(str) || this.intentInfo.size() < 20)) {
            this.intentInfo.put(str, jSONObject);
        }
        return this;
    }

    public InsightIntent(String str) {
        this.intentName = str;
    }
}
