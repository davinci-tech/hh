package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;

/* loaded from: classes5.dex */
public class FeedBackRateRequest {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("problemId")
    private String problemId;

    @SerializedName(JsUtil.SCORE)
    private String score;

    public void setScore(String str) {
        this.score = str;
    }

    public void setProblemId(String str) {
        this.problemId = str;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getScore() {
        return this.score;
    }

    public String getProblemId() {
        return this.problemId;
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}
