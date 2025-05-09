package com.huawei.wear.oversea.httputil;

/* loaded from: classes7.dex */
public abstract class BaseProcessTrace implements WalletProcessTraceBase {
    private String processPrefix = "";
    private String subProcessPrefix = "";

    @Override // com.huawei.wear.oversea.httputil.WalletProcessTraceBase
    public void setProcessPrefix(String str, String str2) {
        this.processPrefix = str;
        this.subProcessPrefix = this.processPrefix + str2;
    }

    @Override // com.huawei.wear.oversea.httputil.WalletProcessTraceBase
    public void resetProcessPrefix() {
        this.processPrefix = "";
        this.subProcessPrefix = "";
    }

    public String getSubProcessPrefix() {
        return this.subProcessPrefix;
    }
}
