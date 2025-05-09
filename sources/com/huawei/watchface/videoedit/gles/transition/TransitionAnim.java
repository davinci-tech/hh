package com.huawei.watchface.videoedit.gles.transition;

import android.opengl.GLES20;
import com.huawei.watchface.videoedit.gles.AbstractDraw;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.gles.ShaderManager;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class TransitionAnim extends AbstractDraw {
    private int mHeightLocation;
    private int mInputHeight;
    private int mInputWidth;
    private int mNextTextureLocation;
    private int mWidthLocation;

    public TransitionAnim(int i, ShaderManager shaderManager) {
        super(i);
        this.mShaderManager = shaderManager;
        this.mVertex = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        Utils.vboBindData(this.mVbo[0], this.mVertex);
        getLocation();
    }

    private void getLocation() {
        this.mNextTextureLocation = GLES20.glGetUniformLocation(this.mProgramId, "nextTexture");
        this.mWidthLocation = GLES20.glGetUniformLocation(this.mProgramId, "inputWidth");
        this.mHeightLocation = GLES20.glGetUniformLocation(this.mProgramId, "inputHeight");
    }

    public void setPrograme(int i) {
        if (this.mProgramId == i) {
            return;
        }
        this.mProgramId = i;
        init();
        getLocation();
    }

    public int draw(int i, float f) {
        return draw(i, -1, f);
    }

    public int draw(int i, int i2, float f) {
        if (f < 0.0f || f >= 1.0f) {
            return -1;
        }
        GLES20.glBindFramebuffer(36160, this.mShaderManager.getFboId(Constant.TRANSITION_FBO));
        GLES20.glClear(16384);
        GLES20.glUseProgram(this.mProgramId);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.mInputTextureLocation, 0);
        if (i2 != -1) {
            GLES20.glActiveTexture(33985);
            GLES20.glBindTexture(3553, i2);
            GLES20.glUniform1i(this.mNextTextureLocation, 1);
        }
        GLES20.glUniform1f(this.mProcessBarLocation, f);
        GLES20.glUniform1i(this.mWidthLocation, this.mInputWidth);
        GLES20.glUniform1i(this.mHeightLocation, this.mInputHeight);
        Utils.bindVbo(this.mVertexPosition, this.mFragmentPosition, this.mVbo);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glDisableVertexAttribArray(this.mVertexPosition);
        GLES20.glDisableVertexAttribArray(this.mFragmentPosition);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
        return this.mShaderManager.getFboTextureId(Constant.TRANSITION_FBO);
    }

    public void setInputWidth(int i) {
        this.mInputWidth = i;
    }

    public void setInputHeight(int i) {
        this.mInputHeight = i;
    }
}
