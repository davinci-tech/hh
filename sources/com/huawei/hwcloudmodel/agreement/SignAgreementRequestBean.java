package com.huawei.hwcloudmodel.agreement;

import java.util.List;

/* loaded from: classes5.dex */
public class SignAgreementRequestBean {
    private List<SignAgreementRequestInfo> signInfo;

    public SignAgreementRequestBean(List<SignAgreementRequestInfo> list) {
        this.signInfo = list;
    }

    public List<SignAgreementRequestInfo> getSignInfo() {
        return this.signInfo;
    }

    public void setSignInfo(List<SignAgreementRequestInfo> list) {
        this.signInfo = list;
    }
}
