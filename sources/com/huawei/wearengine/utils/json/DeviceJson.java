package com.huawei.wearengine.utils.json;

import defpackage.tov;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class DeviceJson extends JSONObject {
    private static final String TAG = "DeviceJson";

    private DeviceJson() {
    }

    public static class Builder {
        private String mWearEngineDeviceId;
        private String mReservedness = "";
        private String mCapability = "";
        private String mBasicInfo = "";
        private String mIdentify = "";

        public Builder setReservedness(String str) {
            this.mReservedness = str;
            return this;
        }

        public Builder setCapability(String str) {
            this.mCapability = str;
            return this;
        }

        public Builder setBasicInfo(String str) {
            this.mBasicInfo = str;
            return this;
        }

        public Builder setIdentify(String str) {
            this.mIdentify = str;
            return this;
        }

        public Builder setWearEngineDeviceId(String str) {
            this.mWearEngineDeviceId = str;
            return this;
        }

        public DeviceJson build() {
            DeviceJson deviceJson = new DeviceJson();
            try {
                deviceJson.put("device_reservedness", this.mReservedness);
                deviceJson.put("device_capability", this.mCapability);
                deviceJson.put("device_basic_info", this.mBasicInfo);
                deviceJson.put("device_identify", this.mIdentify);
                deviceJson.put("device_wear_engine_device_id", this.mWearEngineDeviceId);
            } catch (JSONException unused) {
                tov.c(DeviceJson.TAG, "build catch JSONException");
            }
            return deviceJson;
        }
    }
}
