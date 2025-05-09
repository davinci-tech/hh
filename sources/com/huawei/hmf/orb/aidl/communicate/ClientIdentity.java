package com.huawei.hmf.orb.aidl.communicate;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class ClientIdentity {
    public String appID;
    public String packageName;

    public boolean isCore() {
        return true;
    }

    public ClientIdentity() {
    }

    public ClientIdentity(RequestHeader requestHeader) {
        this.appID = requestHeader.appId;
        this.packageName = requestHeader.packageName;
    }

    public boolean isValid() {
        return !(TextUtils.isEmpty(this.appID) || TextUtils.isEmpty(this.packageName));
    }

    public String toString() {
        return "ClientIdentity{appID='" + this.appID + "', packageName='" + this.packageName + "'}";
    }
}
