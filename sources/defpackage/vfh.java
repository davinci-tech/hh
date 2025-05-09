package defpackage;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vfh {
    private static final Logger e = vha.a((Class<?>) vfh.class);

    public static void e(SecretKey secretKey) {
        if (secretKey instanceof Destroyable) {
            a((Destroyable) secretKey);
        }
    }

    public static void a(Destroyable destroyable) {
        if (destroyable != null) {
            try {
                destroyable.destroy();
            } catch (DestroyFailedException e2) {
                e.warn("Destroy on {} failed!", destroyable.getClass(), e2);
            }
        }
    }

    public static boolean b(SecretKey secretKey) {
        if (secretKey == null) {
            return true;
        }
        if (secretKey instanceof Destroyable) {
            return secretKey.isDestroyed();
        }
        return false;
    }

    public static boolean d(Destroyable destroyable) {
        return destroyable == null || destroyable.isDestroyed();
    }

    public static SecretKey e(byte[] bArr, String str) {
        return new c(bArr, str);
    }

    public static SecretKey b(byte[] bArr, int i, int i2, String str) {
        return new c(bArr, i, i2, str);
    }

    public static SecretKey a(SecretKey secretKey) {
        if (secretKey == null) {
            return null;
        }
        byte[] encoded = secretKey.getEncoded();
        c cVar = new c(encoded, secretKey.getAlgorithm());
        vbj.b(encoded);
        return cVar;
    }

    public static vff a(vff vffVar) {
        if (vffVar != null) {
            return new vff(vffVar);
        }
        return null;
    }

    public static vff b(byte[] bArr, int i, int i2) {
        return new vff(bArr, i, i2);
    }

    public static vff a(byte[] bArr) {
        return new vff(bArr, 0, bArr.length);
    }

    public static boolean c(SecretKey secretKey, SecretKey secretKey2) {
        if (secretKey == secretKey2) {
            return true;
        }
        if (secretKey == null || secretKey2 == null || !secretKey.getAlgorithm().equals(secretKey2.getAlgorithm())) {
            return false;
        }
        byte[] encoded = secretKey.getEncoded();
        byte[] encoded2 = secretKey2.getEncoded();
        boolean equals = Arrays.equals(encoded, encoded2);
        vbj.b(encoded);
        vbj.b(encoded2);
        return equals;
    }

    static class c implements KeySpec, SecretKey, Destroyable {
        private static final long serialVersionUID = 6578238307397289933L;

        /* renamed from: a, reason: collision with root package name */
        private final byte[] f17705a;
        private volatile boolean b;
        private final int d;
        private final String e;

        private c(byte[] bArr, String str) {
            this(bArr, 0, bArr == null ? 0 : bArr.length, str);
        }

        private c(byte[] bArr, int i, int i2, String str) {
            if (bArr == null) {
                throw new NullPointerException("Key missing");
            }
            if (str == null) {
                throw new NullPointerException("Algorithm missing");
            }
            if (bArr.length == 0) {
                throw new IllegalArgumentException("Empty key");
            }
            if (bArr.length - i < i2) {
                throw new IllegalArgumentException("Invalid offset/length combination");
            }
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException("len is negative");
            }
            this.f17705a = Arrays.copyOfRange(bArr, i, i2 + i);
            this.e = str;
            this.d = b();
        }

        private int b() {
            return this.d;
        }

        @Override // java.security.Key
        public String getAlgorithm() {
            return this.e;
        }

        @Override // java.security.Key
        public byte[] getEncoded() {
            if (this.b) {
                throw new IllegalStateException("secret destroyed!");
            }
            return (byte[]) this.f17705a.clone();
        }

        public int hashCode() {
            return this.d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SecretKey)) {
                return false;
            }
            SecretKey secretKey = (SecretKey) obj;
            if (!this.e.equalsIgnoreCase(secretKey.getAlgorithm())) {
                return false;
            }
            if (this.b) {
                throw new IllegalStateException("secret destroyed!");
            }
            byte[] encoded = secretKey.getEncoded();
            boolean isEqual = MessageDigest.isEqual(this.f17705a, encoded);
            vbj.b(encoded);
            return isEqual;
        }

        @Override // javax.security.auth.Destroyable
        public void destroy() throws DestroyFailedException {
            vbj.b(this.f17705a);
            this.b = true;
        }

        @Override // javax.security.auth.Destroyable
        public boolean isDestroyed() {
            return this.b;
        }

        @Override // java.security.Key
        public String getFormat() {
            return "RAW";
        }
    }
}
