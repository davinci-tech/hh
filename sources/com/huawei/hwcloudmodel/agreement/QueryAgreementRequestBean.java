package com.huawei.hwcloudmodel.agreement;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes5.dex */
public class QueryAgreementRequestBean {

    @SerializedName("agrInfo")
    private List<QueryAgreementRequestInfo> agreementInfo;

    public QueryAgreementRequestBean(List<QueryAgreementRequestInfo> list) {
        this.agreementInfo = list;
    }

    public List<QueryAgreementRequestInfo> getAgreementInfo() {
        return this.agreementInfo;
    }

    public void setAgreementInfo(List<QueryAgreementRequestInfo> list) {
        this.agreementInfo = list;
    }
}
