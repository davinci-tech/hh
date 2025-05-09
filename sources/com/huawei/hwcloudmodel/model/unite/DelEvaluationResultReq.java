package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes5.dex */
public class DelEvaluationResultReq {
    private long participateTime;
    private String type = "SLEEP_QUALITY";

    public DelEvaluationResultReq(long j) {
        this.participateTime = j;
    }

    public long fetchParticipateTime() {
        return this.participateTime;
    }

    public void configureParticipateTime(long j) {
        this.participateTime = j;
    }

    public String toString() {
        return "DelEvaluationResultReq{type=" + this.type + "participateTime=" + this.participateTime + '}';
    }
}
