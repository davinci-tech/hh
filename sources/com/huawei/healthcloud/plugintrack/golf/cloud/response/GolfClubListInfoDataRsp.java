package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class GolfClubListInfoDataRsp extends CloudCommonReponse {

    @SerializedName("clubList")
    private List<GolfClubInfoData> mClubInfoList;

    public List<GolfClubInfoData> getClubInfoList() {
        return this.mClubInfoList;
    }

    public void setClubInfoList(List<GolfClubInfoData> list) {
        this.mClubInfoList = list;
    }

    public String getGolfStrikeRangeDouble2IntJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (GolfClubInfoData golfClubInfoData : this.mClubInfoList) {
                int round = (int) Math.round(golfClubInfoData.getMinStrikeRange());
                int round2 = (int) Math.round(golfClubInfoData.getMaxStrikeRange());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("id", golfClubInfoData.getGolfClubId());
                jSONObject2.put("isEnable", golfClubInfoData.isEnable());
                jSONObject2.put("maxDis", round2);
                jSONObject2.put("minDis", round);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("clubList", jSONArray);
        } catch (JSONException e) {
            LogUtil.b("GolfClubListInfoDataRsp", LogAnonymous.b((Throwable) e));
        }
        return jSONObject.toString();
    }
}
