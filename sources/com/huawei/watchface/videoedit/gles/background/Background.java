package com.huawei.watchface.videoedit.gles.background;

import com.huawei.watchface.videoedit.gles.AbstractDraw;
import com.huawei.watchface.videoedit.gles.ShaderManager;

/* loaded from: classes9.dex */
public abstract class Background extends AbstractDraw {
    protected float[] mOutScreenVertex;

    public void draw(int i, float[] fArr, int i2) {
    }

    public Background(int i, ShaderManager shaderManager) {
        super(i);
        this.mOutScreenVertex = new float[]{-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f};
        this.mShaderManager = shaderManager;
    }

    public void updateOutScreenVertex(float f, float f2) {
        updateVertex(this.mOutScreenVertex, f, f2);
    }
}
