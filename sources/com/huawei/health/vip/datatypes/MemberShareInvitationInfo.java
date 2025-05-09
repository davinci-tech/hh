package com.huawei.health.vip.datatypes;

/* loaded from: classes8.dex */
public class MemberShareInvitationInfo {
    private AdminMemberInfo adminMemberinfo;
    private MemberShareInvitationDetail invitation;

    public MemberShareInvitationDetail getInvitation() {
        return this.invitation;
    }

    public void setInvitation(MemberShareInvitationDetail memberShareInvitationDetail) {
        this.invitation = memberShareInvitationDetail;
    }

    public AdminMemberInfo getAdminMemberinfo() {
        return this.adminMemberinfo;
    }

    public void setAdminMemberinfo(AdminMemberInfo adminMemberInfo) {
        this.adminMemberinfo = adminMemberInfo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("invitation=");
        sb.append(this.invitation);
        sb.append(" adminMemberinfo=");
        sb.append(this.adminMemberinfo);
        return sb.toString();
    }
}
