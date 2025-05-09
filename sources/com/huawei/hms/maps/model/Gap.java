package com.huawei.hms.maps.model;

/* loaded from: classes4.dex */
public final class Gap extends PatternItem {
    public static final float MIN_LENGTH = 0.0f;
    public final float length;

    @Override // com.huawei.hms.maps.model.PatternItem
    public String toString() {
        return "Type is Gap and the length is" + String.valueOf(this.length);
    }

    @Override // com.huawei.hms.maps.model.PatternItem
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.huawei.hms.maps.model.PatternItem
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Gap(float f) {
        super(2, Math.max(f, 0.0f));
        this.length = Math.max(f, 0.0f);
    }
}
