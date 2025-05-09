package com.huawei.health.marketing.request;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.Map;

/* loaded from: classes3.dex */
public class QuestionResultRsp extends CloudCommonReponse {
    private Map<String, Long> questionSums;
    private String userOptionId;

    public void setQuestionSums(Map<String, Long> map) {
        this.questionSums = map;
    }

    public void setUserOptionId(String str) {
        this.userOptionId = str;
    }

    public Map<String, Long> getQuestionSums() {
        return this.questionSums;
    }

    public String getUserOptionId() {
        return this.userOptionId;
    }
}
