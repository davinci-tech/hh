package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.Map;

/* loaded from: classes5.dex */
public class GetRunLevelRsp extends CloudCommonReponse {

    @SerializedName("data")
    private Map<Integer, RunLevelDetail> data;

    @SerializedName("userRunLevelData")
    private UserRunLevelData userRunLevelData;

    public Map<Integer, RunLevelDetail> getData() {
        return this.data;
    }

    public void setData(Map<Integer, RunLevelDetail> map) {
        this.data = map;
    }

    public UserRunLevelData getUserRunLevelData() {
        return this.userRunLevelData;
    }

    public void setUserRunLevelData(UserRunLevelData userRunLevelData) {
        this.userRunLevelData = userRunLevelData;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("GetRunLevelRsp{data=");
        sb.append(this.data);
        sb.append(", userRunLevelData=");
        sb.append(this.userRunLevelData);
        sb.append('}');
        return sb.toString();
    }

    public String printString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("dataOne=");
        sb.append(this.userRunLevelData.getLastRecordDay());
        sb.append("dataTwo=");
        sb.append(this.userRunLevelData.getLastCurrentRunLevel());
        sb.append("dataThree=");
        sb.append(this.userRunLevelData.getLastCondition());
        sb.append("dataFour=");
        sb.append(this.userRunLevelData.getLastFitness());
        sb.append("dataFive=");
        sb.append(this.userRunLevelData.getLastFatigue());
        sb.append("dataSix=");
        sb.append(this.userRunLevelData.getLastRanking());
        return sb.toString();
    }
}
