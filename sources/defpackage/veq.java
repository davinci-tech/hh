package defpackage;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes7.dex */
public class veq extends GeneralSecurityException {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f17695a;
    private final byte[] d;

    public veq() {
        this("MAC validation failed");
    }

    public veq(String str) {
        super(str);
        this.d = vbj.c;
        this.f17695a = vbj.c;
    }

    public veq(byte[] bArr, byte[] bArr2) {
        super("MAC validation failed");
        this.d = Arrays.copyOf(bArr, bArr.length);
        this.f17695a = Arrays.copyOf(bArr2, bArr2.length);
    }
}
