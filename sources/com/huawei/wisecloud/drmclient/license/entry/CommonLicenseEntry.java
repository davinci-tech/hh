package com.huawei.wisecloud.drmclient.license.entry;

/* loaded from: classes7.dex */
public class CommonLicenseEntry {
    CommonHeaderEntry header;
    CommonPayloadEntry payload;

    public CommonLicenseEntry(CommonHeaderEntry commonHeaderEntry, CommonPayloadEntry commonPayloadEntry) {
        this.header = commonHeaderEntry;
        this.payload = commonPayloadEntry;
    }

    public CommonHeaderEntry getHeader() {
        return this.header;
    }

    public CommonPayloadEntry getPayload() {
        return this.payload;
    }
}
