package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class HwSportTypeGroup {

    @SerializedName("groupId")
    private String mGroupId;

    @SerializedName(ComplaintConstants.GROUP_NAME_PARAM_KEY)
    private String mGroupName;

    @SerializedName("groupNameRes")
    private String mGroupNameRse;

    @SerializedName("sportList")
    private ArrayList<String> mSportList;

    public String getGroupId() {
        return this.mGroupId;
    }

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public void setGroupName(String str) {
        this.mGroupName = str;
    }

    public String getGroupNameRse() {
        return this.mGroupNameRse;
    }

    public void setmGroupNameRse(String str) {
        this.mGroupNameRse = str;
    }

    public ArrayList<String> getSportList() {
        return (ArrayList) this.mSportList.clone();
    }

    public void setSportList(ArrayList<String> arrayList) {
        this.mSportList = arrayList;
    }
}
