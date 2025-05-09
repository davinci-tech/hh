package com.huawei.hihealthservice.auth;

/* loaded from: classes7.dex */
public class HiScope {
    private int mId;
    private String mName;
    private String[] mPermissions;
    private String mUri;

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String[] getPermissions() {
        return (String[]) this.mPermissions.clone();
    }

    public void setPermissions(String[] strArr) {
        this.mPermissions = (String[]) strArr.clone();
    }

    public String getUri() {
        return this.mUri;
    }

    public void setUri(String str) {
        this.mUri = str;
    }
}
