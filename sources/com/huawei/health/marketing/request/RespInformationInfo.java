package com.huawei.health.marketing.request;

import java.util.List;

/* loaded from: classes3.dex */
public class RespInformationInfo {
    private List<InformationInfo> informations;
    private int resultCode;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public List<InformationInfo> getInformations() {
        return this.informations;
    }

    public void setInformations(List<InformationInfo> list) {
        this.informations = list;
    }
}
