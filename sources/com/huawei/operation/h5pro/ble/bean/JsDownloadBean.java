package com.huawei.operation.h5pro.ble.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;

/* loaded from: classes9.dex */
public class JsDownloadBean {

    @SerializedName(ContentResource.FILE_NAME)
    private String fileName;

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }
}
