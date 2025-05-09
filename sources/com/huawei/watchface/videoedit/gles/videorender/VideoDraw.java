package com.huawei.watchface.videoedit.gles.videorender;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.huawei.watchface.videoedit.gles.AbstractDraw;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.gles.ShaderManager;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class VideoDraw extends AbstractDraw {
    private static final int ROTATION_180 = 180;
    private static final int ROTATION_270 = 270;
    private static final int ROTATION_90 = 90;
    private int mRotation;
    private final int mVideoTextureLocation;
    private static final float[] ORIGIN_VERTEX = {-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] VERTEX_90 = {-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    private static final float[] VERTEX_180 = {-1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f};
    private static final float[] VERTEX_270 = {-1.0f, -1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    private static final float[] ROTATION_MATRIX_90 = {0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    private static final float[] ROTATION_MATRIX_180 = {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    private static final float[] ROTATION_MATRIX_270 = {0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] FLIP_MATRIX = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};

    public VideoDraw(ShaderManager shaderManager) {
        super(shaderManager.getShader(Constant.VIDEO_DRAW));
        this.mRotation = 0;
        this.mShaderManager = shaderManager;
        this.mVertex = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        this.mVideoTextureLocation = GLES20.glGetUniformLocation(this.mProgramId, "videoTexture");
        Utils.vboBindData(this.mVbo[0], this.mVertex);
    }

    public void setRotation(int i) {
        this.mRotation = i;
    }

    public void setStMatrix(float[] fArr) {
        float[] fArr2 = new float[16];
        Matrix.multiplyMM(fArr2, 0, FLIP_MATRIX, 0, fArr, 0);
        int i = this.mRotation;
        if (i == 0) {
            this.mVertex = (float[]) ORIGIN_VERTEX.clone();
        } else if (i == 90) {
            this.mVertex = (float[]) VERTEX_90.clone();
            Matrix.multiplyMM(fArr2, 0, fArr2, 0, ROTATION_MATRIX_90, 0);
        } else if (i == 180) {
            this.mVertex = (float[]) VERTEX_180.clone();
            Matrix.multiplyMM(fArr2, 0, fArr2, 0, ROTATION_MATRIX_180, 0);
        } else if (i == 270) {
            this.mVertex = (float[]) VERTEX_270.clone();
            Matrix.multiplyMM(fArr2, 0, fArr2, 0, ROTATION_MATRIX_270, 0);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 4;
            int i4 = i3 + 2;
            this.mVertex[i4] = this.mVertex[i4] * fArr2[0];
            int i5 = i3 + 3;
            this.mVertex[i5] = this.mVertex[i5] * fArr2[5];
        }
        Utils.vboBindData(this.mVbo[0], this.mVertex);
    }

    public int draw(int i, String str) {
        if (this.mShaderManager == null) {
            return 0;
        }
        GLES20.glBindFramebuffer(36160, this.mShaderManager.getFboId(str));
        GLES20.glClear(16384);
        GLES20.glUseProgram(this.mProgramId);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        GLES20.glUniform1i(this.mVideoTextureLocation, 0);
        Utils.bindVbo(this.mVertexPosition, this.mFragmentPosition, this.mVbo);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glDisableVertexAttribArray(this.mVertexPosition);
        GLES20.glDisableVertexAttribArray(this.mFragmentPosition);
        GLES20.glBindTexture(36197, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindBuffer(34962, 0);
        GLES20.glUseProgram(0);
        return this.mShaderManager.getFboTextureId(str);
    }
}
