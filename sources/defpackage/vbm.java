package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertPathValidatorException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import org.eclipse.californium.elements.util.JceNames;
import org.eclipse.californium.elements.util.StandardCharsets;

/* loaded from: classes7.dex */
public class vbm {
    private static final Charset w;
    private static final Charset x;
    private static final int[] p = {12, 19, 30, 28, 20};
    private static final byte[] m = {42, -122, 72, -122, -9, 13, 1, 1, 1};
    private static final byte[] j = {42, -122, 72, -122, -9, 13, 1, 3, 1};
    private static final byte[] g = {42, -122, 72, -50, 62, 2, 1};
    private static final byte[] n = {42, -122, 72, -50, 56, 4, 1};
    private static final byte[] l = {42, -122, 72, -50, 61, 2, 1};
    private static final byte[] q = {43, 101, 110};
    private static final byte[] r = {43, 101, 111};
    private static final byte[] k = {43, 101, 112};
    private static final byte[] o = {43, 101, 113};
    private static final byte[] h = {85, 4, 3};
    private static final b s = new b(48, 65536, "SEQUENCE");
    private static final b t = new b(49, 65536, "SET");
    private static final e f = new e();
    private static final a b = new a();

    /* renamed from: a, reason: collision with root package name */
    private static final b f17647a = new b(3, 65536, "BIT STRING");
    private static final b i = new b(4, 65536, "OCTET STRING");
    private static final b c = new b(160, 65536, "CONTEXT SPECIFIC 0");
    private static final b e = new b(MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY, 65536, "CONTEXT SPECIFIC 1");
    private static final b d = new b(129, 65536, "CONTEXT SPECIFIC PRIMITIVE 1");

    static {
        Charset charset;
        Charset charset2 = null;
        try {
            charset = Charset.forName("ISO-10646-UCS-2");
        } catch (Throwable unused) {
            charset = null;
        }
        x = charset;
        try {
            charset2 = Charset.forName("ISO-10646-UCS-4");
        } catch (Throwable unused2) {
        }
        w = charset2;
        vbu.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0042 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c(byte[] r1, int r2) {
        /*
            byte[] r0 = defpackage.vbm.l
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L12
            if (r2 != 0) goto Le
            java.lang.String r1 = "EC"
            goto L86
        Le:
            java.lang.String r1 = "EC.v2"
            goto L86
        L12:
            byte[] r0 = defpackage.vbm.m
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L20
            if (r2 != 0) goto L85
            java.lang.String r1 = "RSA"
            goto L86
        L20:
            byte[] r0 = defpackage.vbm.n
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L2d
            if (r2 != 0) goto L85
            java.lang.String r1 = "DSA"
            goto L86
        L2d:
            byte[] r0 = defpackage.vbm.g
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L38
            if (r2 != 0) goto L85
            goto L42
        L38:
            byte[] r0 = defpackage.vbm.j
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L45
            if (r2 != 0) goto L85
        L42:
            java.lang.String r1 = "DH"
            goto L86
        L45:
            byte[] r0 = defpackage.vbm.k
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L55
            if (r2 != 0) goto L52
            java.lang.String r1 = "Ed25519"
            goto L86
        L52:
            java.lang.String r1 = "Ed25519.v2"
            goto L86
        L55:
            byte[] r0 = defpackage.vbm.o
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L65
            if (r2 != 0) goto L62
            java.lang.String r1 = "Ed448"
            goto L86
        L62:
            java.lang.String r1 = "Ed448.v2"
            goto L86
        L65:
            byte[] r0 = defpackage.vbm.q
            boolean r0 = java.util.Arrays.equals(r1, r0)
            if (r0 == 0) goto L75
            if (r2 != 0) goto L72
            java.lang.String r1 = "X25519"
            goto L86
        L72:
            java.lang.String r1 = "X25519.v2"
            goto L86
        L75:
            byte[] r0 = defpackage.vbm.r
            boolean r1 = java.util.Arrays.equals(r1, r0)
            if (r1 == 0) goto L85
            if (r2 != 0) goto L82
            java.lang.String r1 = "X448"
            goto L86
        L82:
            java.lang.String r1 = "X448.v2"
            goto L86
        L85:
            r1 = 0
        L86:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vbm.c(byte[], int):java.lang.String");
    }

    public static boolean d(String str) {
        return JceNames.EC.equalsIgnoreCase(str) || e(str, null) != null;
    }

    public static byte[] b(vbn vbnVar) {
        return s.b(vbnVar);
    }

    public static byte[] c(vbn vbnVar) {
        return f.d(vbnVar);
    }

    public static String g(byte[] bArr) {
        vbn vbnVar = new vbn(bArr, false);
        b bVar = s;
        return c(c(bVar.b(bVar.b(vbnVar, false), false)), 0);
    }

    public static PublicKey j(byte[] bArr) throws GeneralSecurityException {
        String g2 = g(bArr);
        if (g2 != null) {
            return e(g2).generatePublic(new X509EncodedKeySpec(bArr));
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0061 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(byte[] r5) {
        /*
            vbn r0 = new vbn
            r1 = 0
            r0.<init>(r5, r1)
            vbm$b r5 = defpackage.vbm.s
            vbn r0 = r5.b(r0, r1)
            vbm$a r2 = defpackage.vbm.b
            byte[] r3 = r2.d(r0)
            int r2 = r2.a(r3)
            java.lang.String r3 = " not supported!"
            if (r2 < 0) goto L7a
            r4 = 1
            if (r2 > r4) goto L7a
            vbn r5 = r5.b(r0, r1)     // Catch: java.lang.IllegalArgumentException -> L2a
            byte[] r5 = c(r5)     // Catch: java.lang.IllegalArgumentException -> L2a
            java.lang.String r5 = c(r5, r2)     // Catch: java.lang.IllegalArgumentException -> L2a
            goto L61
        L2a:
            r5 = move-exception
            if (r2 != r4) goto L79
            vbm$b r5 = defpackage.vbm.i
            r5.b(r0, r1)
            vbm$b r5 = defpackage.vbm.c
            vbn r5 = r5.b(r0, r1)
            byte[] r5 = c(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "0x"
            r0.<init>(r1)
            java.lang.String r1 = defpackage.vcb.e(r5)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            vbm$e r2 = defpackage.vbm.f     // Catch: java.lang.Throwable -> L5e
            java.lang.String r0 = r2.a(r5)     // Catch: java.lang.Throwable -> L5e
            java.security.spec.ECParameterSpec r5 = a(r0)     // Catch: java.lang.Throwable -> L5e
            if (r5 == 0) goto L5e
            java.lang.String r5 = "EC.v2"
            goto L5f
        L5e:
            r5 = r1
        L5f:
            if (r5 == 0) goto L62
        L61:
            return r5
        L62:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "OID "
            r1.<init>(r2)
            r1.append(r0)
            r1.append(r3)
            java.lang.String r0 = r1.toString()
            r5.<init>(r0)
            throw r5
        L79:
            throw r5
        L7a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Version 0x"
            r0.<init>(r1)
            java.lang.String r1 = java.lang.Integer.toHexString(r2)
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vbm.d(byte[]):java.lang.String");
    }

    public static c a(byte[] bArr) throws GeneralSecurityException {
        String d2 = d(bArr);
        if (d2 == null) {
            return null;
        }
        if (d2 == JceNames.ED25519v2 || d2 == JceNames.ED448v2) {
            return c(bArr);
        }
        if (d2 == JceNames.ECv2) {
            return e(bArr);
        }
        KeyFactory e2 = e(d2);
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bArr);
        c cVar = new c();
        cVar.d = e2.generatePrivate(pKCS8EncodedKeySpec);
        return cVar;
    }

    public static c e(byte[] bArr) throws GeneralSecurityException {
        vbn vbnVar = new vbn(bArr, false);
        b bVar = s;
        vbn b2 = bVar.b(vbnVar, false);
        byte[] d2 = b.d(b2);
        if (d2.length != 1 || d2[0] != 1) {
            return null;
        }
        try {
            bVar.b(b2, false);
        } catch (IllegalArgumentException unused) {
        }
        byte[] d3 = i.d(b2);
        try {
            ECParameterSpec a2 = a(f.a(c(c.b(b2, false))));
            int fieldSize = (a2.getCurve().getField().getFieldSize() + 7) / 8;
            if (d3.length != fieldSize) {
                throw new GeneralSecurityException("private key size " + d3.length + " doesn't match " + fieldSize);
            }
            ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(new BigInteger(1, d3), a2);
            c cVar = new c();
            cVar.d = KeyFactory.getInstance(JceNames.EC).generatePrivate(eCPrivateKeySpec);
            vbn b3 = f17647a.b(e.b(b2, false), false);
            if (b3.c(8) != 0) {
                return cVar;
            }
            cVar.b = b(b3, a2);
            return cVar;
        } catch (IllegalArgumentException e2) {
            throw new GeneralSecurityException(e2.getMessage(), e2);
        } catch (GeneralSecurityException e3) {
            throw e3;
        }
    }

    public static ECPublicKey b(vbn vbnVar, ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        int i2;
        int fieldSize = (eCParameterSpec.getCurve().getField().getFieldSize() + 7) / 8;
        int c2 = vbnVar.c(8);
        int a2 = vbnVar.a() / 8;
        if (c2 == 4 && a2 % 2 == 0 && (i2 = a2 / 2) == fieldSize) {
            return (ECPublicKey) KeyFactory.getInstance(JceNames.EC).generatePublic(new ECPublicKeySpec(new ECPoint(new BigInteger(1, vbnVar.a(i2)), new BigInteger(1, vbnVar.a(i2))), eCParameterSpec));
        }
        return null;
    }

    public static c c(byte[] bArr) throws GeneralSecurityException {
        vbn vbnVar = new vbn(bArr, false);
        b bVar = s;
        vbn b2 = bVar.b(vbnVar, false);
        byte[] d2 = b.d(b2);
        if (d2.length != 1 || d2[0] != 1) {
            return null;
        }
        byte[] b3 = bVar.b(b2);
        String a2 = f.a(c(bVar.b(new vbn(b3, false), false)));
        byte[] b4 = i.b(b2);
        c.b(b2, false);
        KeyFactory e2 = e(a2);
        c cVar = new c();
        vbo vboVar = new vbo(48);
        vboVar.d((byte) 48);
        int c2 = vboVar.c(8);
        vboVar.d((byte) 2);
        vboVar.d((byte) 1);
        vboVar.d((byte) 0);
        vboVar.d(b3);
        vboVar.d(b4);
        vboVar.d(c2, 8);
        cVar.d = e2.generatePrivate(new PKCS8EncodedKeySpec(vboVar.c()));
        vbo vboVar2 = new vbo(44);
        vboVar2.d((byte) 48);
        int c3 = vboVar2.c(8);
        vboVar2.d(b3);
        vboVar2.d((byte) 3);
        int c4 = vboVar2.c(8);
        vboVar2.d(d.d(b2));
        vboVar2.d(c4, 8);
        vboVar2.d(c3, 8);
        cVar.b = e2.generatePublic(new X509EncodedKeySpec(vboVar2.c()));
        return cVar;
    }

    public static void a(List<X509Certificate> list, X509Certificate x509Certificate, int i2) throws CertPathValidatorException {
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                X509Certificate x509Certificate2 = list.get(i3);
                String sigAlgName = x509Certificate2.getSigAlgName();
                if (sigAlgName.endsWith("withECDSA") || sigAlgName.endsWith("WITHECDSA")) {
                    int i4 = i3 + 1;
                    b(x509Certificate2.getSignature(), (i4 < list.size() ? list.get(i4) : x509Certificate).getPublicKey());
                }
            } catch (GeneralSecurityException e2) {
                throw new CertPathValidatorException(e2.getMessage());
            }
        }
    }

    public static void b(byte[] bArr, PublicKey publicKey) throws GeneralSecurityException {
        vbn b2 = s.b(new vbn(bArr, false), false);
        a aVar = b;
        byte[] c2 = aVar.c(b2, false);
        byte[] c3 = aVar.c(b2, false);
        BigInteger order = ((ECPublicKey) publicKey).getParams().getOrder();
        e("R", c2, order);
        e(ExifInterface.LATITUDE_SOUTH, c3, order);
    }

    private static void e(String str, byte[] bArr, BigInteger bigInteger) throws GeneralSecurityException {
        if (bArr.length == 0) {
            throw new GeneralSecurityException("ECDSA signature " + str + " is 0!");
        }
        BigInteger bigInteger2 = new BigInteger(bArr);
        if (bigInteger2.compareTo(BigInteger.ONE) < 0) {
            throw new GeneralSecurityException("ECDSA signature " + str + " is less than 1!");
        }
        if (bigInteger2.compareTo(bigInteger) < 0) {
            return;
        }
        throw new GeneralSecurityException("ECDSA signature " + str + " is not less than N!");
    }

    public static ECParameterSpec a(String str) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.EC);
        keyPairGenerator.initialize(new ECGenParameterSpec(str));
        return ((ECPublicKey) keyPairGenerator.generateKeyPair().getPublic()).getParams();
    }

    @Deprecated
    public static String e(String str, String str2) {
        return vbu.a(str, str2);
    }

    public static KeyFactory e(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(vbu.a(str, str));
    }

    public static String b(byte[] bArr) {
        vbn b2 = s.b(new vbn(bArr, false), false);
        while (b2.e()) {
            vbn b3 = t.b(b2, false);
            while (b3.e()) {
                vbn b4 = s.b(b3, false);
                if (Arrays.equals(f.d(b4), h)) {
                    try {
                        return new d(p).c(b4);
                    } catch (IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* loaded from: classes10.dex */
    public static class c {
        private PublicKey b;
        private PrivateKey d;

        public void b(c cVar) {
            PrivateKey privateKey = cVar.d;
            if (privateKey != null) {
                this.d = privateKey;
            }
            PublicKey publicKey = cVar.b;
            if (publicKey != null) {
                this.b = publicKey;
            }
        }

        public PrivateKey c() {
            return this.d;
        }

        public PublicKey d() {
            return this.b;
        }

        public void a(PublicKey publicKey) {
            this.b = publicKey;
        }
    }

    static class b {
        private final int b;
        private final int c;
        private final String e;

        public b(int i, int i2, String str) {
            this.b = i;
            this.c = i2;
            this.e = str;
        }

        public boolean d(int i) {
            return i == this.b;
        }

        public byte[] b(vbn vbnVar) {
            return c(vbnVar, true);
        }

        public byte[] d(vbn vbnVar) {
            return c(vbnVar, false);
        }

        public byte[] c(vbn vbnVar, boolean z) {
            return vbnVar.a(e(vbnVar, z));
        }

        public vbn b(vbn vbnVar, boolean z) {
            return vbnVar.b(e(vbnVar, z));
        }

        public int e(vbn vbnVar, boolean z) {
            int a2 = vbnVar.a() / 8;
            if (a2 < 2) {
                throw new IllegalArgumentException(String.format("Not enough bytes for %s! Required %d, available %d.", this.e, 2, Integer.valueOf(a2)));
            }
            vbnVar.d();
            int c = vbnVar.c(8);
            if (!d(c)) {
                vbnVar.j();
                throw new IllegalArgumentException(String.format("No %s, found %02x instead of %02x!", this.e, Integer.valueOf(c), Integer.valueOf(this.b)));
            }
            int c2 = vbnVar.c(8);
            int i = c2 + 2;
            if (c2 > 127) {
                int i2 = c2 & 127;
                if (i2 > 4) {
                    throw new IllegalArgumentException(String.format("%s length-size %d too long!", this.e, Integer.valueOf(i2)));
                }
                int a3 = vbnVar.a() / 8;
                if (i2 > a3) {
                    throw new IllegalArgumentException(String.format("%s length %d exceeds available bytes %d!", this.e, Integer.valueOf(i2), Integer.valueOf(a3)));
                }
                byte[] a4 = vbnVar.a(i2);
                int i3 = 0;
                for (byte b : a4) {
                    i3 = (i3 << 8) + (b & 255);
                }
                int i4 = i3;
                i = a4.length + i3 + 2;
                c2 = i4;
            }
            if (c2 > this.c) {
                throw new IllegalArgumentException(String.format("%s lenght %d too large! (supported maxium %d)", this.e, Integer.valueOf(c2), Integer.valueOf(this.c)));
            }
            int a5 = vbnVar.a() / 8;
            if (c2 > a5) {
                throw new IllegalArgumentException(String.format("%s lengh %d exceeds available bytes %d!", this.e, Integer.valueOf(c2), Integer.valueOf(a5)));
            }
            if (!z) {
                return c2;
            }
            vbnVar.j();
            return i;
        }
    }

    static class e extends b {
        public e() {
            super(6, 32, "OID");
        }

        public String a(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            int i = bArr[0] & 255;
            sb.append(i / 40);
            sb.append(".");
            sb.append(i % 40);
            int i2 = 1;
            while (i2 < bArr.length) {
                byte b = bArr[i2];
                if (b < 0) {
                    i2++;
                    if (i2 == bArr.length) {
                        throw new IllegalArgumentException("Invalid OID 0x" + vcb.e(bArr));
                    }
                    byte b2 = bArr[i2];
                    sb.append(".");
                    sb.append(((b & Byte.MAX_VALUE) << 7) | (b2 & Byte.MAX_VALUE));
                } else {
                    sb.append(".");
                    sb.append((int) b);
                }
                i2++;
            }
            return sb.toString();
        }
    }

    static class a extends b {
        public a() {
            super(2, 65536, "INTEGER");
        }

        public int a(byte[] bArr) {
            if (bArr == null) {
                throw new NullPointerException("INTEGER byte array must not be null!");
            }
            if (bArr.length == 0) {
                throw new IllegalArgumentException("INTEGER byte array must not be empty!");
            }
            if (bArr.length > 4) {
                throw new IllegalArgumentException("INTEGER byte array " + bArr.length + " bytes is too large for int (max. 4 bytes)!");
            }
            byte b = bArr[0];
            int i = b;
            for (int i2 = 1; i2 < bArr.length; i2++) {
                i = (i << 8) | (bArr[i2] & 255);
            }
            if ((b >= 0) ^ (i >= 0)) {
                throw new IllegalArgumentException("INTEGER byte array value overflow!");
            }
            return i;
        }
    }

    static class d extends b {
        private int[] b;
        private int c;

        public d(int... iArr) {
            super(iArr[0], 65536, "STRING");
            this.b = iArr;
        }

        @Override // vbm.b
        public boolean d(int i) {
            for (int i2 : this.b) {
                if (i2 == i) {
                    this.c = i;
                    return true;
                }
            }
            return false;
        }

        public String c(vbn vbnVar) {
            byte[] d = d(vbnVar);
            if (d == null) {
                return null;
            }
            int i = this.c;
            if (i == 19) {
                return new String(d, StandardCharsets.US_ASCII);
            }
            if (i == 12) {
                return new String(d, StandardCharsets.UTF_8);
            }
            if (i == 30) {
                if (vbm.x == null) {
                    throw new IllegalArgumentException("BMP_STRING not supported!");
                }
                return new String(d, vbm.x);
            }
            if (i == 28) {
                if (vbm.x == null) {
                    throw new IllegalArgumentException("UNIVERSAL_STRING not supported!");
                }
                return new String(d, vbm.w);
            }
            if (i != 20) {
                return null;
            }
            throw new IllegalArgumentException("TELETEX_STRING not supported!");
        }
    }
}
