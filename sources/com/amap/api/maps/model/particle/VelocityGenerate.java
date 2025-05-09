package com.amap.api.maps.model.particle;

/* loaded from: classes8.dex */
public abstract class VelocityGenerate {
    protected final int TYPE_DEFAULT = -1;
    protected final int TYPE_RANDOMVELOCITYBETWEENTWOCONSTANTS = 0;
    protected int type = -1;

    public abstract float getX();

    public abstract float getY();

    public abstract float getZ();
}
