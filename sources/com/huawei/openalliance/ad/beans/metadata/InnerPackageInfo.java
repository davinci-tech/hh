package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class InnerPackageInfo {
    private String packageName;
    private String versionName;

    public int hashCode() {
        String str = this.packageName;
        if (str != null) {
            return str.hashCode();
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof InnerPackageInfo) {
            InnerPackageInfo innerPackageInfo = (InnerPackageInfo) obj;
            if (!TextUtils.isEmpty(this.packageName) && this.packageName.equals(innerPackageInfo.packageName)) {
                return true;
            }
        }
        return false;
    }
}
