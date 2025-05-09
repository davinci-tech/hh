package com.huawei.hms.kit.awareness.donate.message;

import com.huawei.hms.kit.awareness.b.a.c;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class Session {
    private static final String SOURCE_TYPE = "1";
    private static final String TAG = "Session";
    private static long lastMessageId = 1;
    private String deviceCategory;
    private String deviceModel;
    private String deviceName;
    private boolean isAgreePrivacy;
    private String messageId;
    private String messageName;
    private String messageVersion;
    private String packageName;

    public String toJsonString() {
        try {
            return new JSONObject().put("messageName", this.messageName).put("messageId", this.messageId).put("messageVersion", this.messageVersion).put("deviceModel", this.deviceModel).put("deviceCategory", this.deviceCategory).put("deviceName", this.deviceName).put("packageName", this.packageName).put("sourceType", "1").put("isAgreePrivacy", this.isAgreePrivacy).toString();
        } catch (JSONException unused) {
            c.d(TAG, "Invalid session", new Object[0]);
            return "";
        }
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setMessageVersion(String str) {
        this.messageVersion = str;
    }

    public void setMessageName(String str) {
        this.messageName = str;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDeviceCategory(String str) {
        this.deviceCategory = str;
    }

    public void setAgreePrivacy(boolean z) {
        this.isAgreePrivacy = z;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getMessageVersion() {
        return this.messageVersion;
    }

    public String getMessageName() {
        return this.messageName;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDeviceCategory() {
        return this.deviceCategory;
    }
}
