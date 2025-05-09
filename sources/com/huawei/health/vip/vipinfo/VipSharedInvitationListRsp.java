package com.huawei.health.vip.vipinfo;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.vip.datatypes.MemberShareInvitationInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes8.dex */
public class VipSharedInvitationListRsp extends CloudCommonReponse {

    @SerializedName("invitationInfos")
    private List<MemberShareInvitationInfo> invitationInfos;

    public List<MemberShareInvitationInfo> getInvitationInfos() {
        return this.invitationInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("invitationInfos=");
        sb.append(this.invitationInfos);
        return sb.toString();
    }
}
