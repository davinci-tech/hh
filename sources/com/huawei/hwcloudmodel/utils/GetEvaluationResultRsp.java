package com.huawei.hwcloudmodel.utils;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.UserEvaluationResult;
import java.util.List;

/* loaded from: classes9.dex */
public class GetEvaluationResultRsp extends CloudCommonReponse {
    private List<UserEvaluationResult> evaluationResults;
    private Boolean hasMore;
    private long timestamp;

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetEvaluationResultRsp{evaluationResults=" + this.evaluationResults + ", hasMore=" + this.hasMore + ", timestamp=" + this.timestamp + '}';
    }
}
