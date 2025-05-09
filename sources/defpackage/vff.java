package defpackage;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

/* loaded from: classes7.dex */
public class vff implements AlgorithmParameterSpec, Destroyable {
    private final byte[] b;
    private boolean e;

    public vff(vff vffVar) {
        if (vffVar == null) {
            throw new NullPointerException("IV missing");
        }
        byte[] bArr = vffVar.b;
        this.b = Arrays.copyOf(bArr, bArr.length);
    }

    public vff(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("IV missing");
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("IV is empty");
        }
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException("len is negative");
        }
        if (bArr.length - i < i2) {
            throw new IllegalArgumentException("Invalid offset/length combination");
        }
        this.b = Arrays.copyOfRange(bArr, i, i2 + i);
    }

    public int a() {
        return this.b.length;
    }

    public void a(vbo vboVar) {
        vboVar.d(this.b);
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vbj.b(this.b);
        this.e = true;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.e;
    }
}
