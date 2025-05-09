package com.huawei.operation.h5pro.ble.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;

/* loaded from: classes9.dex */
public class JsReadFileBean {

    @SerializedName("endIndex")
    private Long endIndex;

    @SerializedName(ContentResource.FILE_NAME)
    private String fileName;

    @SerializedName("startIndex")
    private Long startIndex;

    public String getFileName() {
        return this.fileName;
    }

    public Long getStartIndex() {
        return this.startIndex;
    }

    public Long getEndIndex() {
        return this.endIndex;
    }
}
