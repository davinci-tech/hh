package com.huawei.operation.adapter;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SportSummary implements Serializable {
    private static final String TAG = "PluginOperation_SportSummary";
    private static final long serialVersionUID = 7354068980342929920L;
    private int mDate;
    private int mSportType;
    private SportUnit mSportUnit;

    public void configDate(int i) {
        this.mDate = i;
    }

    public int getDate() {
        return this.mDate;
    }

    public void configSportType(int i) {
        this.mSportType = i;
    }

    public int getSportType() {
        return this.mSportType;
    }

    public void configSportUnit(SportUnit sportUnit) {
        this.mSportUnit = sportUnit;
    }

    public SportUnit getSportUnit() {
        return this.mSportUnit;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (this.mSportUnit != null) {
                jSONObject2 = new JSONObject(this.mSportUnit.toString());
            }
            jSONObject.put("date", this.mDate);
            jSONObject.put(BleConstants.SPORT_TYPE, this.mSportType);
            jSONObject.put("sportUnit", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "SportSummary toString error : ", e.getMessage());
        }
        return jSONObject.toString();
    }

    public static class SportUnit implements Serializable {
        private static final long serialVersionUID = 9222189279699260960L;
        private int mCalorie;
        private int mDistance;
        private int mSteps;

        public void configSteps(int i) {
            this.mSteps = i;
        }

        public int getSteps() {
            return this.mSteps;
        }

        public void configDistance(int i) {
            this.mDistance = i;
        }

        public int getDistance() {
            return this.mDistance;
        }

        public void configCalorie(int i) {
            this.mCalorie = i;
        }

        public int getCalorie() {
            return this.mCalorie;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MedalConstants.EVENT_STEPS, this.mSteps);
                jSONObject.put("distance", this.mDistance);
                jSONObject.put("calorie", this.mCalorie);
            } catch (JSONException e) {
                LogUtil.b(SportSummary.TAG, "SportUnit toString error : ", e.getMessage());
            }
            return jSONObject.toString();
        }
    }
}
