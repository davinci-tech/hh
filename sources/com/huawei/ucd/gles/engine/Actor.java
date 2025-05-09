package com.huawei.ucd.gles.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.huawei.ucd.helper.gles.IActorMatrixOperation;
import com.huawei.ucd.helper.gles.IGLActor;
import com.huawei.ucd.helper.gles.MVPMatrixAider;
import com.huawei.ucd.medal.MedalViewCallback;
import defpackage.njw;
import defpackage.nka;
import defpackage.nkc;
import defpackage.nke;
import defpackage.nkj;
import defpackage.nkk;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class Actor implements IGLActor, IActorMatrixOperation {
    private static final String TAG = "Actor";
    protected Context mContext;
    protected nkc mDefaultTextureOptions;
    protected nke mMaterial;
    private Actor mParentActor;
    private IGLActor.RequestRenderListener mRequestRenderListener;
    protected int mSurfaceHeight;
    protected int mSurfaceWidth;
    protected nkk mRunMaterialBeforeDraw = new nkk();
    protected nkk mRunOnceOnDraw = new nkk();
    protected nkk mRunOnceBeforeDraw = new nkk();
    protected MVPMatrixAider mMatrixAider = new MVPMatrixAider();
    protected int mVertexCount = 0;
    protected int mProgramID = -1;
    protected int mTextureID = -1;
    protected String mDefaultMaterialName = "gles_engine_material/default_simple.mat";
    private boolean mIsMaterialSetFromOutside = false;
    private boolean mIsSurfaceCreated = false;
    protected boolean mIsResumed = false;
    private nkj mPreLocation = new nkj();

    protected void onShaderLocationInit() {
    }

    public Actor(Context context) {
        this.mContext = context;
        this.mMatrixAider.f();
        njw.c(TAG, njw.b() + " getModelMatrix=" + Arrays.toString(getModelMatrix()));
    }

    public Actor getParentActor() {
        return this.mParentActor;
    }

    public void setParent(Actor actor) {
        this.mParentActor = actor;
    }

    public void onResume() {
        this.mIsResumed = true;
    }

    public void onPause() {
        this.mIsResumed = false;
    }

    public void onDestroy() {
        GLES20.glDeleteProgram(this.mProgramID);
        nkk nkkVar = this.mRunOnceBeforeDraw;
        if (nkkVar != null) {
            nkkVar.c();
            this.mRunOnceBeforeDraw = null;
        }
        nkk nkkVar2 = this.mRunOnceOnDraw;
        if (nkkVar2 != null) {
            nkkVar2.c();
            this.mRunOnceOnDraw = null;
        }
        MVPMatrixAider mVPMatrixAider = this.mMatrixAider;
        if (mVPMatrixAider != null) {
            mVPMatrixAider.i();
            this.mMatrixAider = null;
        }
        nke nkeVar = this.mMaterial;
        if (nkeVar != null) {
            nkeVar.d();
            this.mMaterial = null;
        }
        this.mParentActor = null;
        this.mRequestRenderListener = null;
    }

    public void setTexture(Bitmap bitmap) {
        setTexture(bitmap, true);
    }

    public void setTexture(Bitmap bitmap, MedalViewCallback medalViewCallback) {
        setTexture(bitmap, true, medalViewCallback);
    }

    public void setTexture(Bitmap bitmap, boolean z, MedalViewCallback medalViewCallback) {
        setTexture(bitmap, this.mDefaultTextureOptions, z, medalViewCallback);
    }

    public void setTexture(final Bitmap bitmap, final nkc nkcVar, final boolean z, final MedalViewCallback medalViewCallback) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.gles.engine.Actor.4
            @Override // java.lang.Runnable
            public void run() {
                if (Actor.this.mTextureID == -1) {
                    Actor.this.mTextureID = nka.cwx_(bitmap, nkcVar, z, medalViewCallback);
                } else {
                    GLES20.glActiveTexture(33984);
                    nka.cwv_(bitmap, medalViewCallback);
                }
            }
        });
    }

    public void setTexture(Bitmap bitmap, boolean z) {
        setTexture(bitmap, this.mDefaultTextureOptions, z);
    }

    public void setTexture(final Bitmap bitmap, final nkc nkcVar, final boolean z) {
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.gles.engine.Actor.5
            @Override // java.lang.Runnable
            public void run() {
                if (Actor.this.mTextureID == -1) {
                    Actor.this.mTextureID = nka.cww_(bitmap, nkcVar, z);
                } else {
                    GLES20.glActiveTexture(33984);
                    nka.cwu_(bitmap);
                }
            }
        });
    }

    public void initTexture(int i) {
        this.mTextureID = i;
    }

    protected void onDraw() {
        GLES20.glUseProgram(this.mProgramID);
        this.mRunOnceOnDraw.b();
    }

    public void setMaterialFromAssets(final String str) {
        this.mIsMaterialSetFromOutside = true;
        this.mRunMaterialBeforeDraw.b(new Runnable() { // from class: com.huawei.ucd.gles.engine.Actor.3
            @Override // java.lang.Runnable
            public void run() {
                Actor actor = Actor.this;
                actor.mMaterial = new nke(actor.mContext, str);
                Actor actor2 = Actor.this;
                actor2.mProgramID = actor2.mMaterial.e();
                njw.c(Actor.TAG, "setMaterialFromAssets materialFile=" + str + " mProgramID=" + Actor.this.mProgramID);
            }
        });
        runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.gles.engine.Actor.1
            @Override // java.lang.Runnable
            public void run() {
                Actor.this.onShaderLocationInit();
            }
        });
    }

    protected int getMaterialHandler(String str) {
        Integer valueOf;
        nke nkeVar = this.mMaterial;
        if (nkeVar == null || (valueOf = Integer.valueOf(nkeVar.c(str))) == null) {
            return -1;
        }
        return valueOf.intValue();
    }

    public void runOnceBeforeDraw(Runnable runnable) {
        this.mRunOnceBeforeDraw.b(runnable);
    }

    public void runOnceOnDraw(Runnable runnable) {
        this.mRunOnceOnDraw.b(runnable);
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public void onSurfaceCreated() {
        this.mIsSurfaceCreated = true;
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public void onSurfaceChanged(int i, int i2) {
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public void onDrawFrame() {
        if (this.mProgramID == -1 && !this.mIsMaterialSetFromOutside) {
            setMaterialFromAssets(this.mDefaultMaterialName);
        }
        this.mRunMaterialBeforeDraw.b();
        this.mRunOnceBeforeDraw.b();
        if (this.mProgramID == -1) {
            setMaterialFromAssets(this.mDefaultMaterialName);
        }
        if (this.mProgramID == -1) {
            njw.c(TAG, "onDrawFrame mProgramID == -1");
            throw new RuntimeException("mProgramID == -1");
        }
        onDraw();
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public boolean isSurfaceCreated() {
        return this.mIsSurfaceCreated;
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public void requestRender() {
        IGLActor.RequestRenderListener requestRenderListener = this.mRequestRenderListener;
        if (requestRenderListener != null) {
            requestRenderListener.onRequestRenderCalled();
        }
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public void setRequestRenderListener(IGLActor.RequestRenderListener requestRenderListener) {
        this.mRequestRenderListener = requestRenderListener;
    }

    @Override // com.huawei.ucd.helper.gles.IGLActor
    public IGLActor.RequestRenderListener getRequestRenderListener() {
        return this.mRequestRenderListener;
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void translate(float f, float f2, float f3) {
        this.mMatrixAider.a(f, f2, f3);
        this.mPreLocation = this.mPreLocation.c(f, f2, f3);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void translate(nkj nkjVar) {
        this.mMatrixAider.a(nkjVar.d, nkjVar.c, nkjVar.b);
        this.mPreLocation = this.mPreLocation.c(nkjVar);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void setTranslation(float f, float f2, float f3) {
        this.mMatrixAider.a(f - this.mPreLocation.d, f2 - this.mPreLocation.c, f3 - this.mPreLocation.b);
        this.mPreLocation = new nkj(f, f2, f3);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void rotate(float f, float f2, float f3, float f4) {
        this.mMatrixAider.d(f, f2, f3, f4);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void scale(float f, float f2, float f3) {
        this.mMatrixAider.b(f, f2, f3);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void scale(nkj nkjVar) {
        this.mMatrixAider.b(nkjVar.d, nkjVar.c, nkjVar.b);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void setCamera(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.mMatrixAider.a(f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void setProjectFrustum(float f, float f2, float f3, float f4, float f5, float f6) {
        this.mMatrixAider.c(f, f2, f3, f4, f5, f6);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public void setProjectOrtho(float f, float f2, float f3, float f4, float f5, float f6) {
        this.mMatrixAider.e(f, f2, f3, f4, f5, f6);
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public float[] getMVPMatrix() {
        return this.mMatrixAider.a();
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public float[] getModelMatrix() {
        return this.mMatrixAider.b();
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public float[] getViewMatrix() {
        return this.mMatrixAider.e();
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public float[] getModelViewMatrix() {
        return this.mMatrixAider.c();
    }

    @Override // com.huawei.ucd.helper.gles.IActorMatrixOperation
    public float[] getProjectMatrix() {
        return this.mMatrixAider.d();
    }
}
