package defpackage;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.List;

/* loaded from: classes7.dex */
public final class vcc extends vdg {
    private final PublicKey b;
    private final List<X509Certificate> d;
    private final PrivateKey e;

    public vcc(vcp vcpVar, Object obj) {
        super(vcpVar, obj);
        this.e = null;
        this.b = null;
        this.d = null;
    }

    public PrivateKey d() {
        return this.e;
    }

    public PublicKey c() {
        return this.b;
    }

    public List<X509Certificate> b() {
        return this.d;
    }
}
