package com.huawei.watchface.videoedit.gles.drawer;

import android.opengl.GLES20;
import com.huawei.watchface.videoedit.gles.CurvedAnimation;
import com.huawei.watchface.videoedit.gles.glutils.TimeRange;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class BaseAnimation extends CurvedAnimation {
    public BaseAnimation(int i, TimeRange timeRange) {
        super(i, timeRange);
        this.mVertex = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        Utils.vboBindData(this.mVbo[0], this.mVertex);
    }

    @Override // com.huawei.watchface.videoedit.gles.CurvedAnimation
    public void draw(long j, int i, int i2) {
        super.draw(j, i, i2);
        if (this.mIsFinished) {
            return;
        }
        GLES20.glBindFramebuffer(36160, i2);
        if (this.mBackground == null) {
            GLES20.glClear(16384);
        }
        GLES20.glUseProgram(this.mProgramId);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.mInputTextureLocation, 0);
        GLES20.glUniformMatrix4fv(this.mMvpLocation, 1, false, this.mMvpMatrix, 0);
        Utils.bindVbo(this.mVertexPosition, this.mFragmentPosition, this.mVbo);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glDisableVertexAttribArray(this.mVertexPosition);
        GLES20.glDisableVertexAttribArray(this.mFragmentPosition);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
    }
}
