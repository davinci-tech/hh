package com.amap.api.maps.model.particle;

/* loaded from: classes8.dex */
public class ConstantRotationOverLife extends RotationOverLife {
    private float rotate;

    public ConstantRotationOverLife(float f) {
        this.rotate = f;
        this.type = 0;
    }

    @Override // com.amap.api.maps.model.particle.RotationOverLife
    public float getRotate() {
        return this.rotate;
    }
}
