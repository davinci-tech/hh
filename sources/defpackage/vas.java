package defpackage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import org.eclipse.californium.elements.auth.AbstractExtensiblePrincipal;

/* loaded from: classes7.dex */
public class vas extends AbstractExtensiblePrincipal<vas> {

    /* renamed from: a, reason: collision with root package name */
    private String f17636a;
    private final PublicKey e;

    public vas(PublicKey publicKey) {
        this(publicKey, null);
    }

    private vas(PublicKey publicKey, var varVar) {
        super(varVar);
        if (publicKey == null) {
            throw new NullPointerException("Public key must not be null");
        }
        this.e = publicKey;
        c(publicKey.getEncoded());
    }

    public vas(byte[] bArr) throws GeneralSecurityException {
        this(bArr, null, null);
    }

    private vas(byte[] bArr, String str, var varVar) throws GeneralSecurityException {
        super(varVar);
        if (bArr == null) {
            throw new NullPointerException("SubjectPublicKeyInfo must not be null");
        }
        try {
            String g = vbm.g(bArr);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bArr);
            try {
                if (str != null) {
                    if (g != null) {
                        if (!vbu.c(g, str)) {
                            throw new GeneralSecurityException(String.format("Provided key algorithm %s doesn't match %s!", str, g));
                        }
                    }
                    this.e = vbm.e(str).generatePublic(x509EncodedKeySpec);
                    c(bArr);
                    return;
                }
                if (g == null) {
                    throw new GeneralSecurityException("Key algorithm could not be determined!");
                }
                this.e = vbm.e(str).generatePublic(x509EncodedKeySpec);
                c(bArr);
                return;
            } catch (RuntimeException e) {
                throw new GeneralSecurityException(e.getMessage());
            }
            str = g;
        } catch (IllegalArgumentException e2) {
            throw new GeneralSecurityException(e2.getMessage());
        }
    }

    @Override // org.eclipse.californium.elements.auth.ExtensiblePrincipal
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public vas amend(var varVar) {
        return new vas(this.e, varVar);
    }

    private void c(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            this.f17636a = "ni:///sha-256;" + vbk.b(messageDigest.digest(), 81);
        } catch (IOException | NoSuchAlgorithmException unused) {
        }
    }

    @Override // java.security.Principal
    public final String getName() {
        return this.f17636a;
    }

    public final PublicKey b() {
        return this.e;
    }

    public final byte[] d() {
        return this.e.getEncoded();
    }

    @Override // java.security.Principal
    public String toString() {
        return "RawPublicKey Identity [" + this.f17636a + "]";
    }

    @Override // java.security.Principal
    public int hashCode() {
        if (this.e == null) {
            return 0;
        }
        return Arrays.hashCode(d());
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        vas vasVar = (vas) obj;
        if (this.e == null) {
            return vasVar.e == null;
        }
        return Arrays.equals(d(), vasVar.d());
    }
}
