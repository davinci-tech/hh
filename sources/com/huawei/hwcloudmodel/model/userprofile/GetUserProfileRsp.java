package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.watchface.videoedit.gles.Constant;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class GetUserProfileRsp extends CloudCommonReponse {

    @SerializedName(Constant.BASIC)
    private UserBasicInfo basic;

    @SerializedName("customDefine")
    private Map<String, String> customDefine;

    @SerializedName("goals")
    private List<UserGoalsInfo> goals;

    @SerializedName("sleepCycle")
    private UserSleepCycleInfo sleepCycle;

    public List<UserGoalsInfo> getGoals() {
        return this.goals;
    }

    public void setGoals(List<UserGoalsInfo> list) {
        this.goals = list;
    }

    public UserBasicInfo getBasic() {
        return this.basic;
    }

    public void setBasic(UserBasicInfo userBasicInfo) {
        this.basic = userBasicInfo;
    }

    public Map<String, String> getCustomDefine() {
        return this.customDefine;
    }

    public void setCustomDefine(Map<String, String> map) {
        this.customDefine = map;
    }

    public UserSleepCycleInfo getSleepCycle() {
        return this.sleepCycle;
    }

    public void setSleepCycle(UserSleepCycleInfo userSleepCycleInfo) {
        this.sleepCycle = userSleepCycleInfo;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetUserProfileRsp{basic=" + this.basic + ", goals=" + this.goals + ", sleepCycle=" + this.sleepCycle + ", customDefine=" + this.customDefine + '}';
    }
}
