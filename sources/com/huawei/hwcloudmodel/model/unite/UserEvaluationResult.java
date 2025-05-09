package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes9.dex */
public class UserEvaluationResult {
    private long participateTime;
    private String result;
    private String type = "SLEEP_QUALITY";

    public UserEvaluationResult(long j, String str) {
        this.participateTime = j;
        this.result = str;
    }

    public String fetchType() {
        return this.type;
    }

    public void configureType(String str) {
        this.type = str;
    }

    public long fetchParticipateTime() {
        return this.participateTime;
    }

    public void configureParticipateTime(long j) {
        this.participateTime = j;
    }

    public String fetchResult() {
        return this.result;
    }

    public void configureResult(String str) {
        this.result = str;
    }

    public String toString() {
        return "UserEvaluationResult{type='" + this.type + "', participateTime=" + this.participateTime + ", result='" + this.result + "'}";
    }
}
