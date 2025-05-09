package com.huawei.ucd.gles.engine;

import android.content.Context;
import android.opengl.GLES20;
import defpackage.njw;
import defpackage.nkc;
import defpackage.nkl;
import java.nio.FloatBuffer;

/* loaded from: classes9.dex */
public class Object3D extends Actor {

    /* renamed from: a, reason: collision with root package name */
    private int f8739a;
    private FloatBuffer b;
    private FloatBuffer c;
    private boolean d;
    private FloatBuffer e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int[] l;
    private int n;

    protected void f() {
    }

    public Object3D(Context context) {
        super(context);
        this.h = -1;
        this.j = -1;
        this.n = -1;
        this.f = -1;
        this.i = -1;
        this.f8739a = -1;
        this.g = -1;
        this.k = -1;
        this.d = false;
        this.l = new int[3];
        this.mDefaultTextureOptions = nkc.d();
        this.mDefaultMaterialName = "gles_engine_object3d/default_simple_object3d.mat";
    }

    public void a(float[] fArr, float[] fArr2, float[] fArr3) {
        e(fArr, fArr2, fArr3);
    }

    private void e(float[] fArr, float[] fArr2, float[] fArr3) {
        b(fArr, fArr2, fArr3);
        this.d = true;
        requestRender();
    }

    public void b(float[] fArr, float[] fArr2, float[] fArr3) {
        njw.c("Object3D", njw.b() + "count vertices=" + fArr.length + " normals=" + fArr2.length + " texCoors=" + fArr3.length);
        this.mVertexCount = fArr.length / 3;
        this.c = nkl.e(fArr);
        this.e = nkl.e(fArr2);
        this.b = nkl.e(fArr3);
    }

    @Override // com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onSurfaceCreated() {
        super.onSurfaceCreated();
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.gles.engine.Object3D.4
            @Override // java.lang.Runnable
            public void run() {
                Object3D.this.d();
            }
        });
    }

    protected void d() {
        GLES20.glGenBuffers(3, this.l, 0);
        GLES20.glBindBuffer(34962, this.l[0]);
        GLES20.glBufferData(34962, this.c.capacity() * 4, this.c, 35044);
        GLES20.glBindBuffer(34962, this.l[1]);
        GLES20.glBufferData(34962, this.e.capacity() * 4, this.e, 35044);
        GLES20.glBindBuffer(34962, this.l[2]);
        GLES20.glBufferData(34962, this.b.capacity() * 4, this.b, 35044);
        GLES20.glBindBuffer(34962, 0);
    }

    @Override // com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onSurfaceChanged(int i, int i2) {
        super.onSurfaceChanged(i, i2);
        GLES20.glViewport(0, 0, i, i2);
        float f = i / i2;
        setProjectFrustum(-f, f, -1.0f, 1.0f, 2.0f, 1000.0f);
        setCamera(0.0f, 0.0f, 50.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }

    @Override // com.huawei.ucd.gles.engine.Actor
    public void onShaderLocationInit() {
        this.h = e();
        this.j = b();
        this.n = j();
        this.f = a();
        this.i = h();
        this.f8739a = c();
        this.g = g();
        this.k = i();
        njw.c("Object3D", njw.b() + " muMVPMatrixHandle=" + this.h + " muModelMatrixHandle=" + this.j + " muViewMatrixHandle=" + this.n + " muModelViewMatrixHandle=" + this.f + " maPositionHandle=" + this.i + " maNormalHandle=" + this.f8739a + " maTexCoorHandle=" + this.g + " muTextureHandle=" + this.k);
    }

    protected int e() {
        return getMaterialHandler("uMVPMatrix");
    }

    protected int b() {
        return getMaterialHandler("uModelMatrix");
    }

    protected int j() {
        return getMaterialHandler("uViewMatrix");
    }

    protected int a() {
        return getMaterialHandler("uModelViewMatrix");
    }

    protected int h() {
        return getMaterialHandler("aPosition");
    }

    protected int c() {
        return getMaterialHandler("aNormal");
    }

    protected int g() {
        return getMaterialHandler("aTexCoor");
    }

    protected int i() {
        return getMaterialHandler("uTexture");
    }

    @Override // com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onDrawFrame() {
        if (this.d) {
            GLES20.glEnable(2929);
            GLES20.glEnable(2884);
            super.onDrawFrame();
            GLES20.glDisable(2929);
            GLES20.glDisable(2884);
        }
    }

    @Override // com.huawei.ucd.gles.engine.Actor
    protected void onDraw() {
        super.onDraw();
        int i = this.h;
        if (i != -1) {
            GLES20.glUniformMatrix4fv(i, 1, false, getMVPMatrix(), 0);
        }
        int i2 = this.j;
        if (i2 != -1) {
            GLES20.glUniformMatrix4fv(i2, 1, false, getModelMatrix(), 0);
        }
        int i3 = this.n;
        if (i3 != -1) {
            GLES20.glUniformMatrix4fv(i3, 1, false, getViewMatrix(), 0);
        }
        int i4 = this.f;
        if (i4 != -1) {
            GLES20.glUniformMatrix4fv(i4, 1, false, getModelViewMatrix(), 0);
        }
        if (this.i != -1) {
            GLES20.glBindBuffer(34962, this.l[0]);
            GLES20.glVertexAttribPointer(this.i, 3, 5126, false, 0, 0);
            GLES20.glEnableVertexAttribArray(this.i);
        }
        if (this.f8739a != -1) {
            GLES20.glBindBuffer(34962, this.l[1]);
            GLES20.glVertexAttribPointer(this.f8739a, 3, 5126, false, 0, 0);
            GLES20.glEnableVertexAttribArray(this.f8739a);
        }
        if (this.g != -1) {
            GLES20.glBindBuffer(34962, this.l[2]);
            GLES20.glVertexAttribPointer(this.g, 2, 5126, false, 0, 0);
            GLES20.glEnableVertexAttribArray(this.g);
        }
        GLES20.glBindBuffer(34962, 0);
        n();
        GLES20.glDrawArrays(4, 0, this.mVertexCount);
        f();
    }

    protected void n() {
        if (this.k == -1 || this.mTextureID == -1) {
            return;
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTextureID);
        GLES20.glUniform1i(this.k, 0);
    }

    @Override // com.huawei.ucd.gles.engine.Actor
    public void onDestroy() {
        FloatBuffer floatBuffer = this.c;
        if (floatBuffer != null) {
            floatBuffer.clear();
            this.c = null;
        }
        FloatBuffer floatBuffer2 = this.e;
        if (floatBuffer2 != null) {
            floatBuffer2.clear();
            this.e = null;
        }
        FloatBuffer floatBuffer3 = this.b;
        if (floatBuffer3 != null) {
            floatBuffer3.clear();
            this.b = null;
        }
        super.onDestroy();
    }
}
