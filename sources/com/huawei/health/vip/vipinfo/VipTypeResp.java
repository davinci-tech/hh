package com.huawei.health.vip.vipinfo;

import com.huawei.health.vip.datatypes.MemberTypeInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes8.dex */
public class VipTypeResp extends CloudCommonReponse {
    private List<MemberTypeInfo> memberTypeInfoList;

    public void setMemberTypeInfoList(List<MemberTypeInfo> list) {
        this.memberTypeInfoList = list;
    }

    public List<MemberTypeInfo> getMemberTypeInfoList() {
        return this.memberTypeInfoList;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "VipTypeResp{memberTypeInfoList=" + this.memberTypeInfoList + '}';
    }
}
