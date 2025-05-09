package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class AddSportDataRsp extends CloudCommonReponse {
    private Long timestamp;

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddSportDataRsp{timestamp=");
        stringBuffer.append(this.timestamp);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
