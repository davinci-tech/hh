package com.huawei.healthcloud.plugintrack.ui.view.glrender.filter;

import android.content.Context;
import android.opengl.GLES20;
import com.huawei.health.R;
import defpackage.hlu;
import java.nio.Buffer;

/* loaded from: classes4.dex */
public class CameraFilter extends hlu {
    protected int p;
    private float[] q;
    protected float[] t;

    @Override // defpackage.hlu, defpackage.hlx
    public void e() {
    }

    public CameraFilter(Context context) {
        super(context, R.raw._2131886145_res_0x7f120041, R.raw._2131886144_res_0x7f120040);
        this.p = GLES20.glGetUniformLocation(this.k, "uMVPMatrix");
    }

    @Override // defpackage.hlx
    public int d(int i) {
        GLES20.glViewport(0, 0, this.f, this.h);
        GLES20.glBindFramebuffer(36160, this.b[0]);
        GLES20.glUseProgram(this.k);
        this.i.position(0);
        GLES20.glVertexAttribPointer(this.l, 2, 5126, false, 0, (Buffer) this.i);
        GLES20.glEnableVertexAttribArray(this.l);
        this.g.position(0);
        GLES20.glVertexAttribPointer(this.c, 2, 5126, false, 0, (Buffer) this.g);
        GLES20.glEnableVertexAttribArray(this.c);
        GLES20.glUniformMatrix4fv(this.j, 1, false, this.t, 0);
        GLES20.glUniformMatrix4fv(this.p, 1, false, this.q, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        GLES20.glUniform1i(this.m, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindTexture(36197, 0);
        return this.f13241a[0];
    }

    public void b(float[] fArr) {
        if (fArr != null) {
            this.t = (float[]) fArr.clone();
        }
    }

    public void e(float[] fArr) {
        if (fArr != null) {
            this.q = (float[]) fArr.clone();
        }
    }
}
