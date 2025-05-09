package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class WearFilterPreview {

    @SerializedName("orderIndex")
    private String orderIndex;

    @SerializedName("previewName")
    private String previewName;

    @SerializedName("uniqueId")
    private String uniqueId;

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String str) {
        this.uniqueId = str;
    }

    public String getPreviewName() {
        return this.previewName;
    }

    public void setPreviewName(String str) {
        this.previewName = str;
    }

    public String getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(String str) {
        this.orderIndex = str;
    }
}
