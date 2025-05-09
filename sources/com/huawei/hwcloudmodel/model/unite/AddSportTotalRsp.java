package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class AddSportTotalRsp extends CloudCommonReponse {

    @SerializedName("paramValidDetail")
    private ParamValidDetail mParamValidDetail;

    public void setParamValidDetail(ParamValidDetail paramValidDetail) {
        this.mParamValidDetail = paramValidDetail;
    }

    public ParamValidDetail getParamValidDetail() {
        return this.mParamValidDetail;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddSportTotalRsp {paramValidDetail = ");
        stringBuffer.append(this.mParamValidDetail);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
