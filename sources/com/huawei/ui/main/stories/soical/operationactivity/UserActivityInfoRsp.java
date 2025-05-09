package com.huawei.ui.main.stories.soical.operationactivity;

import com.google.gson.annotations.SerializedName;
import defpackage.rwz;
import java.util.List;

/* loaded from: classes7.dex */
public class UserActivityInfoRsp {

    @SerializedName("resultCode")
    private String resultCode;

    @SerializedName("resultDesc")
    private String resultDesc;

    @SerializedName("userActivityInfo")
    private rwz userActivityInfo;

    @SerializedName("userActivityInfoList")
    private List<rwz> userActivityInfoList;

    public String getResultCode() {
        return this.resultCode;
    }

    public List<rwz> getUserActivityInfoList() {
        return this.userActivityInfoList;
    }

    public String toString() {
        return "UserActivityInfoRsp{resultCode='" + this.resultCode + "', resultDesc='" + this.resultDesc + "', userActivityInfo=" + this.userActivityInfo + ", userActivityInfoList=" + this.userActivityInfoList + '}';
    }
}
