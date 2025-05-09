package com.huawei.basefitnessadvice.model.intplan;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.operation.utils.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class LeaveInfo {
    private static final String TAG = "LeaveInfo";

    @SerializedName("dayNum")
    private Integer dayNum;

    @SerializedName("metadata")
    private String metadata;

    @SerializedName(Constants.START_DATE)
    private String startDate;

    private LeaveInfo(Builder builder) {
        this.metadata = builder.metadata;
        this.startDate = builder.startDate;
        this.dayNum = builder.dayNum;
    }

    public static final class Builder {
        private Integer dayNum;
        private String metadata;
        private String startDate;

        public Builder metadata(String str) {
            this.metadata = str;
            return this;
        }

        public Builder startDate(String str) {
            this.startDate = str;
            return this;
        }

        public Builder dayNum(Integer num) {
            this.dayNum = num;
            return this;
        }

        public LeaveInfo build() {
            return new LeaveInfo(this);
        }
    }

    public String setMetadata(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("weekNo", i);
            jSONObject.put("dayNo", i2);
        } catch (JSONException unused) {
            LogUtil.e(TAG, "values put error -> ");
        }
        return String.valueOf(jSONObject);
    }
}
