package defpackage;

import com.huawei.wisesecurity.kfs.ha.message.BaseReportMsgBuilder;

/* loaded from: classes7.dex */
public class twl extends BaseReportMsgBuilder {
    @Override // com.huawei.wisesecurity.kfs.ha.message.ReportMsgBuilder
    public String getEventId() {
        return "applyCredential";
    }

    public twl e(String str) {
        this.linkedHashMap.put("cty", str);
        return this;
    }

    public twl a(String str) {
        this.linkedHashMap.put("credentialPackageName", str);
        return this;
    }

    public twl b() {
        this.linkedHashMap.put("flavor", "product");
        return this;
    }
}
