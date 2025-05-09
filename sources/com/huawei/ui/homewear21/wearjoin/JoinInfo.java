package com.huawei.ui.homewear21.wearjoin;

/* loaded from: classes6.dex */
public class JoinInfo {
    private boolean isNeedMigrate = false;
    private boolean isForceMigrate = false;

    public boolean isNeedMigrate() {
        return this.isNeedMigrate;
    }

    public void setNeedMigrate(boolean z) {
        this.isNeedMigrate = z;
    }

    public boolean isForceMigrate() {
        return this.isForceMigrate;
    }

    public void setForceMigrate(boolean z) {
        this.isForceMigrate = z;
    }

    public String toString() {
        return "JoinInfo{isNeedMigrate=" + this.isNeedMigrate + ", isForceMigrate=" + this.isForceMigrate + '}';
    }
}
