package com.huawei.health.vip.datatypes;

/* loaded from: classes8.dex */
public class AdminMemberInfo {
    private boolean effective;
    private int memberType;

    public boolean isEffective() {
        return this.effective;
    }

    public void setEffective(boolean z) {
        this.effective = z;
    }

    public int getMemberType() {
        return this.memberType;
    }

    public void setMemberType(int i) {
        this.memberType = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("effective=");
        sb.append(this.effective);
        sb.append(" memberType=");
        sb.append(this.memberType);
        return sb.toString();
    }
}
