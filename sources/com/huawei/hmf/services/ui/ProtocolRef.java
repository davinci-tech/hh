package com.huawei.hmf.services.ui;

/* loaded from: classes9.dex */
public class ProtocolRef {
    private Class<?> mInterface;
    private Object mValue;

    ProtocolRef(Class<?> cls, Object obj) {
        this.mValue = obj;
        this.mInterface = cls;
    }

    public Class<?> getType() {
        return this.mInterface;
    }

    public <T> T getValue() {
        return (T) this.mValue;
    }
}
