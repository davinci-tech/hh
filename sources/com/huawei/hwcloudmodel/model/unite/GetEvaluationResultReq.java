package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes5.dex */
public class GetEvaluationResultReq {
    private long participateTime;
    private String type = "SLEEP_QUALITY";

    public GetEvaluationResultReq(long j) {
        this.participateTime = j;
    }

    public String toString() {
        return "GetEvaluationResultReq{type=" + this.type + "participateTime=" + this.participateTime + '}';
    }
}
