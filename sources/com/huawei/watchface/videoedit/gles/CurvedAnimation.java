package com.huawei.watchface.videoedit.gles;

import android.opengl.Matrix;
import android.view.animation.Interpolator;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.gles.animations.AlwaysOneInterpolator;
import com.huawei.watchface.videoedit.gles.animations.MeshChanger;
import com.huawei.watchface.videoedit.gles.animations.TransformAnim;
import com.huawei.watchface.videoedit.gles.animations.TransformAnimConfig;
import com.huawei.watchface.videoedit.gles.background.Background;
import com.huawei.watchface.videoedit.gles.glutils.TimeRange;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class CurvedAnimation extends AbstractDraw {
    protected Background mBackground;
    protected boolean mIsFinished;
    protected boolean mIsReversed;
    protected MeshChanger mMeshChanger;
    protected float mProcess;
    protected Interpolator mShaderInterpolator;
    protected TimeRange mTimeRange;
    protected TransformAnim mTransformAnim;

    public void draw(long j, int i) {
    }

    public void draw(long j, int i, float[] fArr) {
    }

    public CurvedAnimation setTexturePath(String str) {
        return this;
    }

    public CurvedAnimation(int i, TimeRange timeRange) {
        super(i);
        this.mIsFinished = false;
        this.mIsReversed = false;
        this.mTimeRange = timeRange;
        this.mShaderInterpolator = new AlwaysOneInterpolator();
        Utils.vboBindData(this.mVbo[0], this.mVertex);
    }

    public void draw(long j, int i, int i2) {
        float process = this.mTimeRange.getProcess(j);
        this.mProcess = process;
        if (process < 0.0f) {
            this.mIsFinished = true;
            return;
        }
        this.mIsFinished = false;
        updateMvpMatrix();
        Background background = this.mBackground;
        if (background != null) {
            background.draw(i, this.mMvpMatrix, i2);
        }
        MeshChanger meshChanger = this.mMeshChanger;
        if (meshChanger != null) {
            meshChanger.changeMesh(this.mVertex, this.mProcess, this.mVbo[0]);
        }
    }

    public void draw(long j) {
        float process = this.mTimeRange.getProcess(j);
        this.mProcess = process;
        if (process < 0.0f) {
            this.mIsFinished = true;
            return;
        }
        this.mIsFinished = false;
        updateMvpMatrix();
        MeshChanger meshChanger = this.mMeshChanger;
        if (meshChanger != null) {
            meshChanger.changeMesh(this.mVertex, this.mProcess, this.mVbo[0]);
        }
    }

    private void updateMvpMatrix() {
        if (this.mTransformAnim != null) {
            Matrix.multiplyMM(this.mMvpMatrix, 0, this.mVpMatrix, 0, this.mTransformAnim.getMatrix(this.mProcess), 0);
        } else {
            this.mMvpMatrix = this.mVpMatrix;
        }
    }

    public void setVpMatrix(float[] fArr) {
        if (fArr == null) {
            HwLog.e(AbstractDraw.TAG, "vpMatrix is null");
        } else {
            System.arraycopy(fArr, 0, this.mVpMatrix, 0, fArr.length);
        }
    }

    public void updateAnimVertex(float f, float f2) {
        updateAnimVertex(f, f2, false);
    }

    public void updateAnimVertex(float f, float f2, boolean z) {
        float f3;
        float f4;
        if (f2 == -1.0f) {
            HwLog.d(AbstractDraw.TAG, "updateAnimVertex, viewport is invalid.");
            return;
        }
        HwLog.d(AbstractDraw.TAG, "materialRatio: " + f + ", viewPortRatio: " + f2);
        float f5 = 1.0f;
        if (!z ? !(f > f2 || Utils.floatEquare(f, f2)) : f2 <= f) {
            f3 = f2 / f;
            f4 = f2;
        } else {
            f4 = f * 1.0f;
            f3 = 1.0f;
        }
        updateVertex(f4, f3);
        Background background = this.mBackground;
        if (background != null) {
            if (f2 > f) {
                f5 = f2 / f;
            } else {
                f2 = f * 1.0f;
            }
            background.updateOutScreenVertex(f2, f5);
        }
    }

    public CurvedAnimation setTimeRange(TimeRange timeRange) {
        this.mTimeRange = timeRange;
        return this;
    }

    public boolean contains(long j) {
        return this.mTimeRange.contains(j);
    }

    public CurvedAnimation setBackground(Background background) {
        this.mBackground = background;
        return this;
    }

    public CurvedAnimation setMeshChanger(MeshChanger meshChanger) {
        this.mMeshChanger = meshChanger;
        return this;
    }

    public CurvedAnimation setTransformAnimConfig(TransformAnimConfig transformAnimConfig) {
        this.mTransformAnim = new TransformAnim(transformAnimConfig);
        return this;
    }

    @Override // com.huawei.watchface.videoedit.gles.AbstractDraw
    public void clean() {
        super.clean();
        Background background = this.mBackground;
        if (background != null) {
            background.clean();
        }
    }

    public Interpolator getShaderInterpolator() {
        return this.mShaderInterpolator;
    }

    public void setShaderInterpolator(Interpolator interpolator) {
        this.mShaderInterpolator = interpolator;
    }

    public boolean isReversed() {
        return this.mIsReversed;
    }

    public void setReversed(boolean z) {
        this.mIsReversed = z;
    }

    public TimeRange getTimeRange() {
        return this.mTimeRange;
    }
}
