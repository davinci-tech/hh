package defpackage;

import java.security.PublicKey;
import java.security.cert.CertPath;

/* loaded from: classes7.dex */
public final class vcl extends vdg {

    /* renamed from: a, reason: collision with root package name */
    private final vdb f17666a;
    private final PublicKey c;
    private final CertPath e;

    public vcl(vcp vcpVar, CertPath certPath, Object obj) {
        super(vcpVar, obj);
        this.e = certPath;
        this.c = null;
        this.f17666a = null;
    }

    public vcl(vcp vcpVar, PublicKey publicKey, Object obj) {
        super(vcpVar, obj);
        this.e = null;
        this.c = publicKey;
        this.f17666a = null;
    }

    public vcl(vcp vcpVar, vdb vdbVar, Object obj) {
        super(vcpVar, obj);
        if (vdbVar == null) {
            throw new NullPointerException("exception must not be null!");
        }
        this.e = null;
        this.c = null;
        this.f17666a = vdbVar;
    }

    public CertPath b() {
        return this.e;
    }

    public PublicKey e() {
        return this.c;
    }

    public vdb c() {
        return this.f17666a;
    }
}
