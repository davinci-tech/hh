package com.huawei.hmf.services;

/* loaded from: classes4.dex */
public class ApiSpec {
    private boolean isSingleton;
    private Class<?> type;
    private String typeName;

    public ApiSpec(Class<?> cls) {
        this.type = cls;
    }

    public boolean isSingleton() {
        return this.isSingleton;
    }

    public void setSingleton(boolean z) {
        this.isSingleton = z;
    }

    public Class<?> getType() {
        return this.type;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }
}
