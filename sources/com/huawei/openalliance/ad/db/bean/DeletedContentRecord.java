package com.huawei.openalliance.ad.db.bean;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class DeletedContentRecord extends a implements Serializable {
    public static final String CONTENT_ID = "contentId";
    public static final String DELETE_TIMSTAMP = "deleteTimestamp";
    private static final long serialVersionUID = 30424300;
    private String contentId;
    private long deleteTimestamp;

    public String a() {
        return this.contentId;
    }

    public DeletedContentRecord(String str, long j) {
        this.contentId = str;
        this.deleteTimestamp = j;
    }

    public DeletedContentRecord() {
    }
}
