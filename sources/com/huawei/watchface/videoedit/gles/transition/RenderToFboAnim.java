package com.huawei.watchface.videoedit.gles.transition;

import android.opengl.GLES20;
import com.huawei.watchface.videoedit.gles.AbstractDraw;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class RenderToFboAnim extends AbstractDraw {
    public RenderToFboAnim(int i) {
        super(i);
        Utils.vboBindData(this.mVbo[0], this.mVertex);
    }

    @Override // com.huawei.watchface.videoedit.gles.AbstractDraw
    public void draw(int i) {
        GLES20.glUseProgram(this.mProgramId);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.mInputTextureLocation, 0);
        Utils.bindVbo(this.mVertexPosition, this.mFragmentPosition, this.mVbo);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glFlush();
        GLES20.glDisableVertexAttribArray(this.mVertexPosition);
        GLES20.glDisableVertexAttribArray(this.mFragmentPosition);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
    }
}
