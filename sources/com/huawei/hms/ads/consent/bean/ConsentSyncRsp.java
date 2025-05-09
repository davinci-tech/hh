package com.huawei.hms.ads.consent.bean;

import java.util.List;

/* loaded from: classes9.dex */
public class ConsentSyncRsp extends ConsentSyncBase {
    private int retcode;
    private Long timestamp;

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setRetcode(int i) {
        this.retcode = i;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int getRetcode() {
        return this.retcode;
    }

    public ConsentSyncRsp(List<String> list, int i) {
        super(list, i);
    }

    public ConsentSyncRsp() {
    }
}
