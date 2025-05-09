package com.huawei.hwcloudmodel.model.wjx;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class SurveyIdResponse extends CloudCommonReponse {

    @SerializedName("surveyId")
    private String surveyId;

    public String getSurveyId() {
        return this.surveyId;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("surveyId=");
        sb.append(this.surveyId);
        return sb.toString();
    }
}
