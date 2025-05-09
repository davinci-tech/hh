package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.GRSManager;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class SetUserProfileReq implements IRequest {

    @SerializedName(Constant.BASIC)
    private UserBasicInfo basic;

    @SerializedName("customDefine")
    private Map<String, String> customDefine;

    @SerializedName("goals")
    private List<UserGoalsInfo> goals;

    @SerializedName("sleepCycle")
    private UserSleepCycleInfo sleepCycle;

    public UserBasicInfo getBasic() {
        return this.basic;
    }

    public void setBasic(UserBasicInfo userBasicInfo) {
        this.basic = userBasicInfo;
    }

    public List<UserGoalsInfo> getGoals() {
        return this.goals;
    }

    public void setGoals(List<UserGoalsInfo> list) {
        this.goals = list;
    }

    public UserSleepCycleInfo getSleepCycle() {
        return this.sleepCycle;
    }

    public void setSleepCycle(UserSleepCycleInfo userSleepCycleInfo) {
        this.sleepCycle = userSleepCycleInfo;
    }

    public Map<String, String> getCustomDefine() {
        return this.customDefine;
    }

    public void setCustomDefine(Map<String, String> map) {
        this.customDefine = map;
    }

    public String toString() {
        return "SetUserProfileReq{basic=" + this.basic + ", goals=" + this.goals + ", sleepCycle=" + this.sleepCycle + ", customDefine=" + this.customDefine + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/setUserProfile";
    }
}
