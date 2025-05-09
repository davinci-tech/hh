package defpackage;

import com.huawei.wisesecurity.kfs.ha.message.BaseReportMsgBuilder;

/* loaded from: classes7.dex */
public class twv extends BaseReportMsgBuilder {
    @Override // com.huawei.wisesecurity.kfs.ha.message.ReportMsgBuilder
    public String getEventId() {
        return "credentialFromString";
    }

    public twv c(String str) {
        this.linkedHashMap.put("cty", str);
        return this;
    }

    public twv b(String str) {
        this.linkedHashMap.put("credentialAppName", str);
        return this;
    }

    public twv b(int i) {
        this.linkedHashMap.put("akSkVersion", String.valueOf(i));
        return this;
    }

    public twv d() {
        this.linkedHashMap.put("flavor", "product");
        return this;
    }
}
