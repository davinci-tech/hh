package com.huawei.hmf.services.ui.internal;

import com.huawei.hmf.orb.IMessageEntity;

/* loaded from: classes9.dex */
public class CallingInfo implements IMessageEntity {
    public static final transient String DESCRIPTOR = "__CallingInfo__";
    private String module;
    private String packageName;

    public String getModule() {
        return this.module;
    }

    public void setModule(String str) {
        this.module = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }
}
