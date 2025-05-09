package com.huawei.watchface.videoedit.gles.animations;

import android.opengl.Matrix;

/* loaded from: classes9.dex */
public class TransformAnim {
    private TransformAnimConfig mConfig;
    private float[] mMMatrix = new float[16];

    public TransformAnim(TransformAnimConfig transformAnimConfig) {
        this.mConfig = transformAnimConfig;
    }

    public float[] getMatrix(float f) {
        float interpolation = this.mConfig.getRotInterp() == null ? f : this.mConfig.getRotInterp().getInterpolation(f);
        float interpolation2 = this.mConfig.getScaInterp() == null ? f : this.mConfig.getScaInterp().getInterpolation(f);
        if (this.mConfig.getPosInterp() != null) {
            f = this.mConfig.getPosInterp().getInterpolation(f);
        }
        Matrix.setIdentityM(this.mMMatrix, 0);
        Matrix.setRotateM(this.mMMatrix, 0, ((this.mConfig.getEndRot() - this.mConfig.getStartRot()) * interpolation) + this.mConfig.getStartRot(), this.mConfig.getRotAxis()[0], this.mConfig.getRotAxis()[1], this.mConfig.getRotAxis()[2]);
        Matrix.translateM(this.mMMatrix, 0, this.mConfig.getStartPos()[0] + ((this.mConfig.getEndPos()[0] - this.mConfig.getStartPos()[0]) * f), this.mConfig.getStartPos()[1] + ((this.mConfig.getEndPos()[1] - this.mConfig.getStartPos()[1]) * f), this.mConfig.getStartPos()[2] + ((this.mConfig.getEndPos()[2] - this.mConfig.getStartPos()[2]) * f));
        Matrix.scaleM(this.mMMatrix, 0, this.mConfig.getStartScale()[0] + ((this.mConfig.getEndScale()[0] - this.mConfig.getStartScale()[0]) * interpolation2), this.mConfig.getStartScale()[1] + ((this.mConfig.getEndScale()[1] - this.mConfig.getStartScale()[1]) * interpolation2), this.mConfig.getStartScale()[2] + ((this.mConfig.getEndScale()[2] - this.mConfig.getStartScale()[2]) * interpolation2));
        return (float[]) this.mMMatrix.clone();
    }
}
