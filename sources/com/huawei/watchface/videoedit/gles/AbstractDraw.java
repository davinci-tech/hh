package com.huawei.watchface.videoedit.gles;

import android.opengl.GLES20;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public abstract class AbstractDraw {
    public static final String TAG = "AbstractDraw";
    protected int mFragmentPosition;
    protected int mInputTextureLocation;
    protected int mMvpLocation;
    protected int mProcessBarLocation;
    protected int mProgramId;
    protected ShaderManager mShaderManager;
    protected int[] mVbo;
    protected int mVertexPosition;
    protected float[] mVertex = {-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    protected float[] mMvpMatrix = new float[16];
    protected float[] mVpMatrix = new float[16];

    public void draw(int i) {
    }

    public void draw(int i, float[] fArr) {
    }

    public AbstractDraw(int i) {
        int[] iArr = new int[1];
        this.mVbo = iArr;
        this.mProgramId = i;
        GLES20.glGenBuffers(1, iArr, 0);
        init();
    }

    protected void init() {
        this.mVertexPosition = GLES20.glGetAttribLocation(this.mProgramId, "vPosition");
        this.mFragmentPosition = GLES20.glGetAttribLocation(this.mProgramId, "fPosition");
        this.mMvpLocation = GLES20.glGetUniformLocation(this.mProgramId, "vMVPMatrix");
        this.mProcessBarLocation = GLES20.glGetUniformLocation(this.mProgramId, "processBar");
        this.mInputTextureLocation = GLES20.glGetUniformLocation(this.mProgramId, "inputTexture");
    }

    protected void updateVertex(float f, float f2) {
        setCoordinateXy(this.mVertex, f2, -f2, -f, f);
        Utils.vboBindData(this.mVbo[0], this.mVertex);
    }

    protected void updateVertex(float[] fArr, float f, float f2) {
        setCoordinateXy(fArr, f2, -f2, -f, f);
        Utils.vboBindData(this.mVbo[0], fArr);
    }

    private void setCoordinateXy(float[] fArr, float f, float f2, float f3, float f4) {
        fArr[9] = f;
        fArr[13] = f;
        fArr[1] = f2;
        fArr[5] = f2;
        fArr[0] = f3;
        fArr[8] = f3;
        fArr[4] = f4;
        fArr[12] = f4;
    }

    public void clean() {
        GLES20.glDeleteBuffers(1, this.mVbo, 0);
    }
}
