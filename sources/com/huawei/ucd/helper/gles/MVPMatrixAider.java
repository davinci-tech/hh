package com.huawei.ucd.helper.gles;

import android.opengl.Matrix;
import java.util.Stack;

/* loaded from: classes9.dex */
public class MVPMatrixAider {
    private ProjectType j;
    private float[] d = new float[16];
    private float[] f = new float[16];

    /* renamed from: a, reason: collision with root package name */
    private float[] f8741a = new float[16];
    private float[] c = new float[16];
    private float[] e = new float[16];
    private float[] g = new float[16];
    public Stack<float[]> b = new Stack<>();

    enum ProjectType {
        ORTHO,
        FRUSTUM
    }

    public void j() {
        this.b.push((float[]) this.f8741a.clone());
    }

    public void g() {
        this.f8741a = this.b.pop();
    }

    public void i() {
        this.d = null;
        this.f = null;
        this.f8741a = null;
        this.c = null;
        this.e = null;
        this.g = null;
    }

    public float[] h() {
        Matrix.multiplyMM(this.g, 0, this.d, 0, this.f, 0);
        return (float[]) this.g.clone();
    }

    public float[] a() {
        Matrix.multiplyMM(this.e, 0, this.f, 0, this.f8741a, 0);
        float[] fArr = this.e;
        Matrix.multiplyMM(fArr, 0, this.d, 0, fArr, 0);
        return (float[]) this.e.clone();
    }

    public float[] c() {
        Matrix.multiplyMM(this.c, 0, this.f, 0, this.f8741a, 0);
        return (float[]) this.c.clone();
    }

    public float[] d() {
        return (float[]) this.d.clone();
    }

    public float[] e() {
        return (float[]) this.f.clone();
    }

    public float[] b() {
        return (float[]) this.f8741a.clone();
    }

    public void f() {
        Matrix.setRotateM(this.f8741a, 0, 0.0f, 1.0f, 0.0f, 0.0f);
    }

    public void a(float f, float f2, float f3) {
        Matrix.translateM(this.f8741a, 0, f, f2, f3);
    }

    public void d(float f, float f2, float f3, float f4) {
        Matrix.rotateM(this.f8741a, 0, f, f2, f3, f4);
    }

    public void b(float f, float f2, float f3) {
        Matrix.scaleM(this.f8741a, 0, f, f2, f3);
    }

    public void a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        Matrix.setLookAtM(this.f, 0, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public void c(float f, float f2, float f3, float f4, float f5, float f6) {
        Matrix.frustumM(this.d, 0, f, f2, f3, f4, f5, f6);
        this.j = ProjectType.FRUSTUM;
    }

    public void e(float f, float f2, float f3, float f4, float f5, float f6) {
        Matrix.orthoM(this.d, 0, f, f2, f3, f4, f5, f6);
        this.j = ProjectType.ORTHO;
    }
}
