package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class GetCommentRsp extends CloudCommonReponse {

    @SerializedName("remainder")
    private int remainder;

    public int getRemainder() {
        return this.remainder;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetCommentRsp{remainder=" + this.remainder + '}';
    }
}
