package com.huawei.devicesdk.entity;

/* loaded from: classes3.dex */
public enum CharacterOperationType {
    WRITE(0),
    ENABLE(1),
    DISABLE(2),
    READ(3);

    private int mCharacterOperationType;

    CharacterOperationType(int i) {
        this.mCharacterOperationType = i;
    }

    public int value() {
        return this.mCharacterOperationType;
    }

    public static CharacterOperationType getValue(int i) {
        if (i < 0 || i >= values().length) {
            return null;
        }
        return values()[i];
    }
}
