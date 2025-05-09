package com.huawei.hmf.services.inject;

/* loaded from: classes9.dex */
public final class InjectValue {
    private final Type mType;
    private final Object mValue;

    public enum Type {
        EXPLICIT_INJECT,
        IMPLICIT_INJECT
    }

    public InjectValue(Type type, Object obj) {
        this.mType = type;
        this.mValue = obj;
    }

    public Type getType() {
        return this.mType;
    }

    public Object getValue() {
        return this.mValue;
    }
}
