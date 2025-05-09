package com.huawei.watchface.videoedit.gles.background;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.gles.ShaderManager;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class GaussianBlurBackground extends Background {
    private static final float STRIP_BIG = 8.0f;
    private static final float STRIP_SMALL = 2.0f;
    private int mDirLocation;
    private int[] mFbo;
    private int[] mFboTexId;
    private float[] mIdentityMatrix;

    public GaussianBlurBackground(ShaderManager shaderManager) {
        super(shaderManager.getShader(Constant.GAUSSIAN), shaderManager);
        this.mFbo = new int[2];
        this.mFboTexId = new int[2];
        this.mIdentityMatrix = new float[16];
        Utils.vboBindData(this.mVbo[0], this.mVertex);
        this.mShaderManager = shaderManager;
        this.mDirLocation = GLES20.glGetUniformLocation(this.mProgramId, "dir");
        this.mFbo[0] = shaderManager.getFboId(Constant.FBO);
        this.mFboTexId[0] = shaderManager.getFboTextureId(Constant.FBO);
        this.mFbo[1] = shaderManager.getFboId(Constant.FBO_TWO);
        this.mFboTexId[1] = shaderManager.getFboTextureId(Constant.FBO_TWO);
        Matrix.setIdentityM(this.mIdentityMatrix, 0);
    }

    @Override // com.huawei.watchface.videoedit.gles.background.Background
    public void draw(int i, float[] fArr, int i2) {
        GLES20.glUseProgram(this.mProgramId);
        GLES20.glBindFramebuffer(36160, this.mFbo[0]);
        GLES20.glClear(16384);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.mInputTextureLocation, 0);
        GLES20.glUniformMatrix4fv(this.mMvpLocation, 1, false, this.mIdentityMatrix, 0);
        Utils.vboBindData(this.mVbo[0], this.mVertex);
        Utils.bindVbo(this.mVertexPosition, this.mFragmentPosition, this.mVbo);
        GLES20.glUniform2f(this.mDirLocation, 0.0f, 8.0f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, this.mFbo[1]);
        GLES20.glClear(16384);
        GLES20.glBindTexture(3553, this.mFboTexId[0]);
        GLES20.glUniform2f(this.mDirLocation, 8.0f, 0.0f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, this.mFbo[0]);
        GLES20.glBindTexture(3553, this.mFboTexId[1]);
        GLES20.glUniform2f(this.mDirLocation, 0.0f, 2.0f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, i2);
        GLES20.glClear(16384);
        Matrix.scaleM(this.mMvpMatrix, 0, fArr, 0, 1.5f, 1.5f, 1.0f);
        GLES20.glUniformMatrix4fv(this.mMvpLocation, 1, false, this.mMvpMatrix, 0);
        Utils.vboBindData(this.mVbo[0], this.mOutScreenVertex);
        Utils.bindVbo(this.mVertexPosition, this.mFragmentPosition, this.mVbo);
        GLES20.glBindTexture(3553, this.mFboTexId[0]);
        GLES20.glUniform2f(this.mDirLocation, 2.0f, 0.0f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glDisableVertexAttribArray(this.mVertexPosition);
        GLES20.glDisableVertexAttribArray(this.mFragmentPosition);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
    }
}
