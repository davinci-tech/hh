package com.huawei.hms.maps.model;

/* loaded from: classes4.dex */
public final class Dash extends PatternItem {
    public static final float MIN_LENGTH = 0.0f;

    @Override // com.huawei.hms.maps.model.PatternItem
    public String toString() {
        return "Type is Dash and the paramLength is" + String.valueOf(this.length);
    }

    public Dash(float f) {
        super(0, Math.max(f, 0.0f));
    }
}
