package com.huawei.featureuserprofile.todo.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.operation.utils.TodoTaskInterface;
import java.util.List;

/* loaded from: classes3.dex */
public class HistoryBean extends CloudCommonReponse implements TodoTaskInterface {

    @SerializedName("activityTarget")
    private int activityTarget;

    @SerializedName("continuity")
    private int continuity;

    @SerializedName("histories")
    private List<HistoriesDetailsBean> histories;

    @SerializedName("status")
    private int status;

    @SerializedName("targetDays")
    private int targetDays;

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public int getIconId() {
        return 0;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getActivityTarget() {
        return this.activityTarget;
    }

    public void setActivityTarget(int i) {
        this.activityTarget = i;
    }

    public int getContinuity() {
        return this.continuity;
    }

    public void setContinuity(int i) {
        this.continuity = i;
    }

    public int getTargetDays() {
        return this.targetDays;
    }

    public void setTargetDays(int i) {
        this.targetDays = i;
    }

    public List<HistoriesDetailsBean> getHistories() {
        return this.histories;
    }

    public void setHistories(List<HistoriesDetailsBean> list) {
        this.histories = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "HistoryBean{status=" + this.status + ", activityTarget=" + this.activityTarget + ", continuity=" + this.continuity + ", targetDays=" + this.targetDays + ", histories=" + this.histories + '}';
    }

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public String getProgress() {
        return "";
    }
}
