package com.huawei.ucd.medal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.os.Handler;
import android.util.Log;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.ucd.gles.engine.Object3D;
import com.huawei.ucd.helper.gles.MVPMatrixAider;
import defpackage.njw;
import defpackage.njy;
import defpackage.nka;
import defpackage.nkc;
import defpackage.nkf;
import defpackage.nkh;
import defpackage.nkj;

/* loaded from: classes9.dex */
public class Medal3D extends Object3D {

    /* renamed from: a, reason: collision with root package name */
    float f8743a;
    private int aa;
    private int[] ab;
    private int ac;
    private float ad;
    private int ae;
    private int af;
    private float ag;
    private int ah;
    private int ai;
    private float aj;
    private float ak;
    private Runnable an;
    protected MVPMatrixAider b;
    boolean c;
    private float d;
    private float e;
    private float f;
    private float g;
    private nkj h;
    private float i;
    private float j;
    private boolean k;
    private int l;
    private nkh m;
    private Handler n;
    private int o;
    private float p;
    private nkh q;
    private nkj r;
    private float s;
    private nkj t;
    private nkj u;
    private int v;
    private float w;
    private nkj x;
    private float y;
    private int z;

    public Medal3D(Context context) {
        super(context);
        this.ae = -1;
        this.ai = -1;
        this.z = -1;
        this.ac = -1;
        this.af = -1;
        this.u = new nkj(0.0f);
        this.y = 0.0f;
        this.s = 0.0f;
        this.w = 0.0f;
        this.x = new nkj(0.0f, 0.0f, -80.0f);
        this.ab = new int[4];
        this.b = new MVPMatrixAider();
        this.ah = -1;
        this.aa = -1;
        this.v = -1;
        this.o = -1;
        this.l = -1;
        this.ad = 0.5f;
        this.e = 0.0f;
        this.g = 0.0f;
        this.d = 0.0f;
        this.j = 4.0f;
        this.ak = 0.02f;
        this.aj = 1.0f;
        this.an = new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.8
            @Override // java.lang.Runnable
            public void run() {
                int i = (((int) Medal3D.this.ag) / 360) * 360;
                int i2 = ((int) Medal3D.this.ag) % 360;
                if (i2 >= 0 && i2 <= 90) {
                    Medal3D medal3D = Medal3D.this;
                    medal3D.f = (i - medal3D.ag) * Medal3D.this.aj;
                } else if (i2 > 90 && i2 < 270) {
                    Medal3D medal3D2 = Medal3D.this;
                    medal3D2.f = ((i + 180) - medal3D2.ag) * Medal3D.this.aj;
                } else if (i2 >= 270 && i2 <= 360) {
                    Medal3D medal3D3 = Medal3D.this;
                    medal3D3.f = ((i + 360) - medal3D3.ag) * Medal3D.this.aj;
                } else if (i2 >= -90 && i2 < 0) {
                    Medal3D medal3D4 = Medal3D.this;
                    medal3D4.f = (i - medal3D4.ag) * Medal3D.this.aj;
                } else if (i2 < -90 && i2 > -270) {
                    Medal3D medal3D5 = Medal3D.this;
                    medal3D5.f = ((i + AMapEngineUtils.MIN_LONGITUDE_DEGREE) - medal3D5.ag) * Medal3D.this.aj;
                } else if (i2 <= -270 && i2 >= -360) {
                    Medal3D medal3D6 = Medal3D.this;
                    medal3D6.f = ((i - 360) - medal3D6.ag) * Medal3D.this.aj;
                }
                Medal3D medal3D7 = Medal3D.this;
                medal3D7.i = medal3D7.j * Medal3D.this.d;
                Medal3D medal3D8 = Medal3D.this;
                medal3D8.e = (medal3D8.f - Medal3D.this.i) / Medal3D.this.ad;
                Medal3D medal3D9 = Medal3D.this;
                medal3D9.g = medal3D9.d + (Medal3D.this.e * Medal3D.this.ak);
                float f = Medal3D.this.ak / 2.0f;
                float f2 = Medal3D.this.d;
                float f3 = Medal3D.this.g;
                Medal3D.this.ag += f * (f2 + f3);
                if (Math.abs(Medal3D.this.ag) < 0.01f || Math.abs(Medal3D.this.e) < 0.01f) {
                    return;
                }
                Medal3D medal3D10 = Medal3D.this;
                medal3D10.e(medal3D10.ag);
                Medal3D.this.requestRender();
                Medal3D medal3D11 = Medal3D.this;
                medal3D11.d = medal3D11.g;
                Medal3D.this.n.postDelayed(Medal3D.this.an, 16L);
            }
        };
        this.k = false;
        this.mDefaultTextureOptions = new nkc.a().a(9729).d();
        this.n = new Handler();
    }

    @Override // com.huawei.ucd.gles.engine.Object3D, com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onSurfaceCreated() {
        super.onSurfaceCreated();
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.4
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                GLES20.glEnable(2929);
                GLES20.glEnable(2884);
                GLES20.glCullFace(1029);
                GLES20.glEnable(3042);
                GLES20.glBlendFunc(770, 771);
            }
        });
    }

    @Override // com.huawei.ucd.gles.engine.Object3D, com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onSurfaceChanged(int i, int i2) {
        super.onSurfaceChanged(i, i2);
        this.ab = new int[]{0, 0, i, i2};
        nkj nkjVar = new nkj(0.0f, 0.0f, 0.0f);
        this.h = nkjVar;
        setCamera(nkjVar.d, this.h.c, this.h.b, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
        this.b.c(-0.5f, 0.5f, -0.5f, 0.5f, 1.0f, 100.0f);
        this.b.a(0.0f, 30.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f);
    }

    @Override // com.huawei.ucd.gles.engine.Object3D, com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onDrawFrame() {
        if (this.c) {
            float f = this.y;
            float f2 = this.f8743a;
            this.y = f + f2;
            if (f2 > 360.0f) {
                this.f8743a = f2 - 360.0f;
            } else if (f2 < -360.0f) {
                this.f8743a = f2 + 360.0f;
            }
        }
        this.mMatrixAider.j();
        translate(this.x);
        scale(this.u);
        rotate(this.y, 0.0f, 1.0f, 0.0f);
        rotate(this.s, 1.0f, 0.0f, 0.0f);
        rotate(this.w, 0.0f, 0.0f, 1.0f);
        this.b.a(0.0f, 0.0f, -800.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        super.onDrawFrame();
        this.mMatrixAider.g();
        GLES20.glUniformMatrix4fv(this.ah, 1, false, this.b.h(), 0);
        if (this.o != -1) {
            float f3 = this.y % 360.0f;
            GLES20.glUniform1f(this.o, Math.abs(f3) > 90.0f && Math.abs(f3) < 270.0f ? 1.0f : 0.0f);
        }
    }

    @Override // com.huawei.ucd.gles.engine.Object3D, com.huawei.ucd.gles.engine.Actor
    public void onShaderLocationInit() {
        njw.c("Medal3D", njw.b());
        super.onShaderLocationInit();
        this.ae = getMaterialHandler("uLightLocation");
        this.ai = getMaterialHandler("uLight2Location");
        this.z = getMaterialHandler("uCamera");
        this.ac = getMaterialHandler("uLight1Intensity");
        this.af = getMaterialHandler("uLight2Intensity");
        this.ah = getMaterialHandler("uMVPMatrixGY");
        this.aa = getMaterialHandler("sLightImg");
        this.o = getMaterialHandler("isBg");
        this.l = getMaterialHandler("vContent");
    }

    @Override // com.huawei.ucd.gles.engine.Object3D
    public void n() {
        nkh nkhVar;
        nkj nkjVar;
        nkh nkhVar2;
        nkj nkjVar2;
        float f = this.x.d;
        float f2 = this.x.c;
        int i = this.z;
        if (i != -1) {
            GLES20.glUniform3f(i, this.h.d + f, this.h.c + f2, this.h.b);
        }
        int i2 = this.ae;
        if (i2 != -1 && (nkjVar2 = this.r) != null) {
            GLES20.glUniform3f(i2, nkjVar2.d + f, this.r.c + f2, this.r.b);
        }
        int i3 = this.ac;
        if (i3 != -1 && (nkhVar2 = this.m) != null) {
            GLES20.glUniform4f(i3, nkhVar2.b, this.m.f15344a, this.m.c, this.m.e);
        }
        int i4 = this.ai;
        if (i4 != -1 && (nkjVar = this.t) != null) {
            GLES20.glUniform3f(i4, nkjVar.d + f, this.t.c + f2, this.t.b);
        }
        int i5 = this.af;
        if (i5 != -1 && (nkhVar = this.q) != null) {
            GLES20.glUniform4f(i5, nkhVar.b, this.q.f15344a, this.q.c, this.q.e);
        }
        if (this.v != -1) {
            GLES20.glUniform1f(this.l, 1.0f);
            GLES20.glActiveTexture(33986);
            GLES20.glBindTexture(3553, this.v);
            GLES20.glUniform1i(this.aa, 2);
            Log.d("Medal3D", "onDrawArraysPre:mTextureLight != -1");
            return;
        }
        GLES20.glUniform1f(this.l, 0.0f);
    }

    public void e(float f) {
        this.y = f;
    }

    public void b(float f) {
        this.s = f;
    }

    public void d(float f) {
        this.w = f;
    }

    public void a(float f, float f2, float f3) {
        this.x.d = f;
        this.x.c = f2;
        this.x.b = f3;
    }

    public void b(float f, float f2, float f3) {
        this.u.d = f;
        this.u.c = f2;
        this.u.b = f3;
    }

    public void c(final float f, final float f2, final float f3, final float f4) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.1
            @Override // java.lang.Runnable
            public void run() {
                Medal3D.this.q = new nkh(f, f2, f3, f4);
            }
        });
        requestRender();
    }

    public void e(final float f, final float f2, final float f3, final float f4) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.5
            @Override // java.lang.Runnable
            public void run() {
                Medal3D.this.m = new nkh(f, f2, f3, f4);
            }
        });
        requestRender();
    }

    public void d(final float f, final float f2, final float f3) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.2
            @Override // java.lang.Runnable
            public void run() {
                Medal3D.this.t = new nkj(f, f2, f3);
            }
        });
        requestRender();
    }

    public void e(final float f, final float f2, final float f3) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.3
            @Override // java.lang.Runnable
            public void run() {
                Medal3D.this.r = new nkj(f, f2, f3);
            }
        });
        requestRender();
    }

    @Override // com.huawei.ucd.gles.engine.Object3D
    public int b() {
        return getMaterialHandler("uMMatrix");
    }

    @Override // com.huawei.ucd.gles.engine.Object3D
    public int i() {
        return getMaterialHandler("sTexture");
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0011, code lost:
    
        if (r4 != 3) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void cwz_(android.view.MotionEvent r4) {
        /*
            r3 = this;
            float r0 = r4.getX()
            int r4 = r4.getAction()
            if (r4 == 0) goto L3c
            r1 = 1
            if (r4 == r1) goto L38
            r1 = 2
            if (r4 == r1) goto L14
            r1 = 3
            if (r4 == r1) goto L38
            goto L3f
        L14:
            float r4 = r3.p
            float r1 = r3.y
            float r4 = r0 - r4
            r2 = 1048576000(0x3e800000, float:0.25)
            float r4 = r4 * r2
            float r1 = r1 + r4
            r3.y = r1
            r4 = 1135869952(0x43b40000, float:360.0)
            int r2 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r2 < 0) goto L29
            float r1 = r1 - r4
            r3.y = r1
        L29:
            float r1 = r3.y
            r2 = -1011613696(0xffffffffc3b40000, float:-360.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 > 0) goto L34
            float r1 = r1 + r4
            r3.y = r1
        L34:
            r3.requestRender()
            goto L3f
        L38:
            r3.m()
            goto L3f
        L3c:
            r3.o()
        L3f:
            r3.p = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ucd.medal.Medal3D.cwz_(android.view.MotionEvent):void");
    }

    private void l() {
        this.e = 0.0f;
        this.g = 0.0f;
        this.d = 0.0f;
        this.ag = this.y;
        this.j = 1.2f;
        this.aj = 4.0f;
    }

    public void k() {
        this.n.post(this.an);
    }

    public void o() {
        this.k = false;
        this.n.removeCallbacks(this.an);
    }

    private void m() {
        if (this.k) {
            return;
        }
        l();
        k();
    }

    public nkf e(float f, float f2) {
        float[] fArr = new float[4];
        GLU.gluUnProject(f, this.ab[3] - f2, 1.0f, getViewMatrix(), 0, getProjectMatrix(), 0, this.ab, 0, fArr, 0);
        float f3 = fArr[3];
        if (f3 == 0.0f) {
            njw.c("Medal3D", njw.b() + "mWinPosToGlPos[3] == 0.0f");
            return null;
        }
        float f4 = fArr[0] / f3;
        fArr[0] = f4;
        float f5 = fArr[1] / f3;
        fArr[1] = f5;
        return new nkf(f4 * 0.08f, f5 * 0.08f);
    }

    @Override // com.huawei.ucd.gles.engine.Object3D, com.huawei.ucd.gles.engine.Actor
    public void onDestroy() {
        this.h = null;
        this.r = null;
        this.t = null;
        this.m = null;
        this.q = null;
        this.u = null;
        this.x = null;
        this.ab = null;
        o();
        super.onDestroy();
    }

    public void a(float f) {
        this.k = true;
        l();
        this.d = f / 10.0f;
        k();
    }

    public void e(MedalBackContent medalBackContent) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int width = medalBackContent.c.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, medalBackContent.c.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(medalBackContent.c, 0.0f, 0.0f, new Paint());
        paint.setColor(medalBackContent.j);
        String str = medalBackContent.d[0];
        float f = 46;
        paint.setTextSize(f);
        float f2 = width;
        canvas.drawText(str, ((f2 - njy.d(str, f)) / 2.0f) + medalBackContent.b.f15343a, medalBackContent.b.e, paint);
        String str2 = medalBackContent.d[1];
        float f3 = 32;
        paint.setTextSize(f3);
        canvas.drawText(str2, ((f2 - njy.d(str2, f3)) / 2.0f) + medalBackContent.g.f15343a, medalBackContent.g.e + f, paint);
        njw.c("Medal3D", njw.b());
        cwB_(createBitmap);
    }

    public void cwA_(MedalBackContent medalBackContent, Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int width = medalBackContent.c.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, medalBackContent.c.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(medalBackContent.c, 0.0f, 0.0f, new Paint());
        if (bitmap == null) {
            paint.setColor(medalBackContent.j);
            String str = medalBackContent.d[0];
            float f = 46;
            paint.setTextSize(f);
            float f2 = width;
            canvas.drawText(str, ((f2 - njy.d(str, f)) / 2.0f) + medalBackContent.b.f15343a, medalBackContent.b.e, paint);
            String str2 = medalBackContent.d[1];
            float f3 = 32;
            paint.setTextSize(f3);
            canvas.drawText(str2, ((f2 - njy.d(str2, f3)) / 2.0f) + medalBackContent.g.f15343a, medalBackContent.g.e + f, paint);
        }
        njw.c("Medal3D", njw.b());
        cwB_(createBitmap);
    }

    public void cwB_(final Bitmap bitmap) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.Medal3D.7
            @Override // java.lang.Runnable
            public void run() {
                if (Medal3D.this.v == -1) {
                    Medal3D.this.v = nka.cww_(bitmap, nkc.d(), true);
                }
            }
        });
    }

    public void c(float f) {
        this.f8743a = f;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
