package com.huawei.haf.router.facade.model;

/* loaded from: classes.dex */
public enum TypeKind {
    BOOLEAN(0),
    BYTE(1),
    SHORT(2),
    INT(3),
    LONG(4),
    CHAR(5),
    FLOAT(6),
    DOUBLE(7),
    STRING(8),
    SERIALIZABLE(9),
    PARCELABLE(10),
    OBJECT(11);

    private final int mType;

    TypeKind(int i) {
        this.mType = i;
    }

    public int type() {
        return this.mType;
    }
}
