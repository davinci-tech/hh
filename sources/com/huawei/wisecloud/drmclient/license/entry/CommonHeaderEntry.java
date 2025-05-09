package com.huawei.wisecloud.drmclient.license.entry;

/* loaded from: classes7.dex */
public class CommonHeaderEntry {
    private String nonce;
    private int signAlg;
    private String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getNonce() {
        return this.nonce;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public int getSignAlg() {
        return this.signAlg;
    }

    public void setSignAlg(int i) {
        this.signAlg = i;
    }
}
