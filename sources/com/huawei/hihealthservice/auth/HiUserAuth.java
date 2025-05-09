package com.huawei.hihealthservice.auth;

/* loaded from: classes7.dex */
public class HiUserAuth {
    private String mClientId;
    private String mExpireId;
    private String mOpenId;
    private String mScope;

    public String getScope() {
        return this.mScope;
    }

    public String getOpenId() {
        return this.mOpenId;
    }

    public String getExpireIn() {
        return this.mExpireId;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiUserAuth{scope='");
        stringBuffer.append(this.mScope).append("', mOpenId='");
        stringBuffer.append(this.mOpenId).append("', expire_id='");
        stringBuffer.append(this.mExpireId).append("', mClientId='");
        stringBuffer.append(this.mClientId).append("'}");
        return stringBuffer.toString();
    }
}
