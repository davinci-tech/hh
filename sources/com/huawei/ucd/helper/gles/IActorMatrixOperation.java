package com.huawei.ucd.helper.gles;

import defpackage.nkj;

/* loaded from: classes9.dex */
public interface IActorMatrixOperation {
    float[] getMVPMatrix();

    float[] getModelMatrix();

    float[] getModelViewMatrix();

    float[] getProjectMatrix();

    float[] getViewMatrix();

    void rotate(float f, float f2, float f3, float f4);

    void scale(float f, float f2, float f3);

    void scale(nkj nkjVar);

    void setCamera(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    void setProjectFrustum(float f, float f2, float f3, float f4, float f5, float f6);

    void setProjectOrtho(float f, float f2, float f3, float f4, float f5, float f6);

    void setTranslation(float f, float f2, float f3);

    void translate(float f, float f2, float f3);

    void translate(nkj nkjVar);
}
