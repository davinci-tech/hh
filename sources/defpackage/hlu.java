package defpackage;

import android.content.Context;
import android.opengl.GLES20;

/* loaded from: classes4.dex */
public class hlu extends hlx {

    /* renamed from: a, reason: collision with root package name */
    protected int[] f13241a;
    protected int[] b;

    public hlu(Context context, int i, int i2) {
        super(context, i, i2);
    }

    @Override // defpackage.hlx
    protected void e() {
        this.g.clear();
        this.g.put(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    @Override // defpackage.hlx
    public void e(int i, int i2, int i3, int i4) {
        super.e(i, i2, i3, i4);
        a();
    }

    private void a() {
        if (this.b != null) {
            c();
        }
        int[] iArr = new int[1];
        this.b = iArr;
        GLES20.glGenFramebuffers(1, iArr, 0);
        int[] iArr2 = new int[1];
        this.f13241a = iArr2;
        hlz.d(iArr2);
        GLES20.glBindTexture(3553, this.f13241a[0]);
        GLES20.glTexImage2D(3553, 0, 6408, this.f, this.h, 0, 6408, 5121, null);
        GLES20.glBindFramebuffer(36160, this.b[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f13241a[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    public void c() {
        int[] iArr = this.f13241a;
        if (iArr != null) {
            GLES20.glDeleteTextures(1, iArr, 0);
            this.f13241a = null;
        }
        int[] iArr2 = this.b;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(1, iArr2, 0);
            this.b = null;
        }
    }
}
