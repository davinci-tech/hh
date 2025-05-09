package com.huawei.openalliance.ad.beans.server;

import com.huawei.openalliance.ad.beans.metadata.AdEvent;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.List;

/* loaded from: classes5.dex */
public class AnalysisReportReq extends EventReportReq {
    @Override // com.huawei.openalliance.ad.beans.server.EventReportReq, com.huawei.openalliance.ad.beans.base.ReqBean
    public String b() {
        return Constants.ANALYSIS_CONTENT_SERVER_REQ_URI;
    }

    public AnalysisReportReq(List<AdEvent> list) {
        super(list);
    }
}
