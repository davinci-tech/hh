package defpackage;

import javax.crypto.SecretKey;

/* loaded from: classes7.dex */
public class vdq extends vdg {
    private final SecretKey b;
    private final vdt d;

    public vdq(vcp vcpVar, vdt vdtVar, SecretKey secretKey) {
        this(vcpVar, vdtVar, secretKey, null);
    }

    public vdq(vcp vcpVar, vdt vdtVar, SecretKey secretKey, Object obj) {
        super(vcpVar, obj);
        if (vdtVar == null) {
            throw new NullPointerException("PSK identity must not be null!");
        }
        if (secretKey != null) {
            String algorithm = secretKey.getAlgorithm();
            if (!"MAC".equals(algorithm) && !"PSK".equals(algorithm)) {
                throw new IllegalArgumentException("Secret must be either MAC for master secret, or PSK for secret key, but not " + algorithm + "!");
            }
        }
        this.d = vdtVar;
        this.b = secretKey;
    }

    public vdt b() {
        return this.d;
    }

    public SecretKey c() {
        return this.b;
    }
}
