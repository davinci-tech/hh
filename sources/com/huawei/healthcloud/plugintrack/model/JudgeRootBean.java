package com.huawei.healthcloud.plugintrack.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
public class JudgeRootBean implements Serializable {
    private static final long serialVersionUID = -4426324113122919530L;

    @SerializedName("postureJudgeBean")
    private List<PostureJudgeBean> postureJudgeBean;

    @SerializedName("updatedTime")
    private String updatedTime;

    @SerializedName("version")
    private String version;

    public List<PostureJudgeBean> getJudge() {
        return this.postureJudgeBean;
    }
}
