package com.huawei.hwcloudmodel.model.unite;

import java.util.List;

/* loaded from: classes5.dex */
public class AddEvaluationResultReq {
    private List<UserEvaluationResult> evaluationResults;

    public AddEvaluationResultReq(List<UserEvaluationResult> list) {
        this.evaluationResults = list;
    }

    public String toString() {
        return "AddEvaluationResultReq{evaluationResults=" + this.evaluationResults + '}';
    }
}
