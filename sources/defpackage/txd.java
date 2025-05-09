package defpackage;

import com.huawei.wisesecurity.kfs.ha.message.BaseReportMsgBuilder;

/* loaded from: classes7.dex */
public class txd extends BaseReportMsgBuilder {
    @Override // com.huawei.wisesecurity.kfs.ha.message.ReportMsgBuilder
    public String getEventId() {
        return "crypto";
    }

    public txd c() {
        this.linkedHashMap.put("flavor", "product");
        return this;
    }
}
