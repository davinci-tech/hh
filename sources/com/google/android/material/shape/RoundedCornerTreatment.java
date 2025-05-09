package com.google.android.material.shape;

/* loaded from: classes8.dex */
public class RoundedCornerTreatment extends CornerTreatment {
    private final float radius;

    public RoundedCornerTreatment(float f) {
        this.radius = f;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        shapePath.reset(0.0f, this.radius * f2);
        float f3 = this.radius * 2.0f * f2;
        shapePath.addArc(0.0f, 0.0f, f3, f3, f + 180.0f, 90.0f);
    }
}
