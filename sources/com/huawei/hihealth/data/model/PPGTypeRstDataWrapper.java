package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PPGTypeRstDataWrapper {

    @SerializedName("algRstFlag")
    int algRstFlag;

    @SerializedName("mPPGTypeRstBeanList")
    List<PPGTypeRstData> ppgTypeRstBeanList = new ArrayList();

    public int getAlgRstFlag() {
        return this.algRstFlag;
    }

    public List<PPGTypeRstData> getPpgTypeRstBeanList() {
        return this.ppgTypeRstBeanList;
    }
}
