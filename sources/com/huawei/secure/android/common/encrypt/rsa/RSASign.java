package com.huawei.secure.android.common.encrypt.rsa;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.b;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;

/* loaded from: classes6.dex */
public abstract class RSASign {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8599a = "SHA256WithRSA";
    private static final String b = "SHA256WithRSA/PSS";
    private static final String c = "RSASign";
    private static final String d = "UTF-8";
    private static final String e = "";

    private static String a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            b.b(c, "sign content or key is null");
            return "";
        }
        PrivateKey privateKey = EncryptUtil.getPrivateKey(str2);
        return z ? newSign(str, privateKey) : sign(str, privateKey);
    }

    public static boolean isBuildVersionHigherThan23() {
        return true;
    }

    public static String newSign(String str, String str2) {
        if (isBuildVersionHigherThan23()) {
            return a(str, str2, true);
        }
        b.b(c, "sdk version is too low");
        return "";
    }

    public static boolean newVerifySign(String str, String str2, String str3) {
        if (isBuildVersionHigherThan23()) {
            return a(str, str2, str3, true);
        }
        b.b(c, "sdk version is too low");
        return false;
    }

    @Deprecated
    public static String sign(String str, String str2) {
        return a(str, str2, false);
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, String str3) {
        return a(str, str2, str3, false);
    }

    @Deprecated
    public static String sign(String str, PrivateKey privateKey) {
        return a(str, privateKey, false);
    }

    @Deprecated
    public static boolean verifySign(String str, String str2, PublicKey publicKey) {
        return a(str, str2, publicKey, false);
    }

    public static boolean verifySign(byte[] bArr, byte[] bArr2, PublicKey publicKey, boolean z) {
        Signature signature;
        if (bArr != null && publicKey != null && bArr2 != null) {
            try {
                if (!RSAEncrypt.isPublicKeyLengthRight((RSAPublicKey) publicKey)) {
                    b.b(c, "publicKey length is too short");
                    return false;
                }
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance(f8599a);
                }
                signature.initVerify(publicKey);
                signature.update(bArr);
                return signature.verify(bArr2);
            } catch (ClassCastException unused) {
                b.b(c, "verifySign: ClassCastException");
                return false;
            } catch (GeneralSecurityException e2) {
                b.b(c, "check sign exception: " + e2.getMessage());
                return false;
            } catch (Exception e3) {
                b.b(c, "exception : " + e3.getMessage());
                return false;
            }
        }
        b.b(c, "content or publicKey is null.");
        return false;
    }

    public static String newSign(String str, PrivateKey privateKey) {
        if (!isBuildVersionHigherThan23()) {
            b.b(c, "sdk version is too low");
            return "";
        }
        return a(str, privateKey, true);
    }

    public static boolean newVerifySign(String str, String str2, PublicKey publicKey) {
        if (!isBuildVersionHigherThan23()) {
            b.b(c, "sdk version is too low");
            return false;
        }
        return a(str, str2, publicKey, true);
    }

    public static byte[] sign(byte[] bArr, PrivateKey privateKey, boolean z) {
        Signature signature;
        byte[] bArr2 = new byte[0];
        if (bArr != null && privateKey != null) {
            try {
                if (!RSAEncrypt.isPrivateKeyLengthRight((RSAPrivateKey) privateKey)) {
                    b.b(c, "privateKey length is too short");
                    return bArr2;
                }
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance(f8599a);
                }
                signature.initSign(privateKey);
                signature.update(bArr);
                return signature.sign();
            } catch (ClassCastException unused) {
                b.b(c, "sign: ClassCastException");
                return bArr2;
            } catch (InvalidAlgorithmParameterException e2) {
                b.b(c, "sign InvalidAlgorithmParameterException: " + e2.getMessage());
                return bArr2;
            } catch (InvalidKeyException e3) {
                b.b(c, "sign InvalidKeyException: " + e3.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e4) {
                b.b(c, "sign NoSuchAlgorithmException: " + e4.getMessage());
                return bArr2;
            } catch (SignatureException e5) {
                b.b(c, "sign SignatureException: " + e5.getMessage());
                return bArr2;
            } catch (Exception e6) {
                b.b(c, "sign Exception: " + e6.getMessage());
                return bArr2;
            }
        }
        b.b(c, "content or privateKey is null.");
        return bArr2;
    }

    private static String a(String str, PrivateKey privateKey, boolean z) {
        try {
            return Base64.encodeToString(sign(str.getBytes("UTF-8"), privateKey, z), 0);
        } catch (UnsupportedEncodingException e2) {
            b.b(c, "sign UnsupportedEncodingException: " + e2.getMessage());
            return "";
        }
    }

    private static boolean a(String str, String str2, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2)) {
            RSAPublicKey publicKey = EncryptUtil.getPublicKey(str3);
            if (z) {
                return newVerifySign(str, str2, publicKey);
            }
            return verifySign(str, str2, publicKey);
        }
        b.b(c, "content or public key or sign value is null");
        return false;
    }

    private static boolean a(String str, String str2, PublicKey publicKey, boolean z) {
        try {
            return verifySign(str.getBytes("UTF-8"), Base64.decode(str2, 0), publicKey, z);
        } catch (UnsupportedEncodingException e2) {
            b.b(c, "verifySign UnsupportedEncodingException: " + e2.getMessage());
            return false;
        } catch (Exception e3) {
            b.b(c, "base64 decode Exception : " + e3.getMessage());
            return false;
        }
    }

    public static boolean verifySign(ByteBuffer byteBuffer, byte[] bArr, PublicKey publicKey, boolean z) {
        Signature signature;
        if (byteBuffer != null && publicKey != null && bArr != null) {
            try {
                if (!RSAEncrypt.isPublicKeyLengthRight((RSAPublicKey) publicKey)) {
                    b.b(c, "publicKey length is too short");
                    return false;
                }
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance(f8599a);
                }
                signature.initVerify(publicKey);
                signature.update(byteBuffer);
                return signature.verify(bArr);
            } catch (ClassCastException unused) {
                b.b(c, "verifySign: ClassCastException");
                return false;
            } catch (GeneralSecurityException e2) {
                b.b(c, "check sign exception: " + e2.getMessage());
                return false;
            } catch (Exception e3) {
                b.b(c, "exception : " + e3.getMessage());
                return false;
            }
        }
        b.b(c, "content or publicKey is null.");
        return false;
    }

    public static byte[] sign(ByteBuffer byteBuffer, PrivateKey privateKey, boolean z) {
        Signature signature;
        byte[] bArr = new byte[0];
        if (byteBuffer != null && privateKey != null) {
            try {
                if (!RSAEncrypt.isPrivateKeyLengthRight((RSAPrivateKey) privateKey)) {
                    b.b(c, "privateKey length is too short");
                    return bArr;
                }
                if (z) {
                    signature = Signature.getInstance("SHA256WithRSA/PSS");
                    signature.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                } else {
                    signature = Signature.getInstance(f8599a);
                }
                signature.initSign(privateKey);
                signature.update(byteBuffer);
                byte[] sign = signature.sign();
                b.c(c, "result is : " + Arrays.toString(sign));
                return sign;
            } catch (ClassCastException unused) {
                b.b(c, "sign: ClassCastException");
                return bArr;
            } catch (InvalidAlgorithmParameterException e2) {
                b.b(c, "sign InvalidAlgorithmParameterException: " + e2.getMessage());
                return bArr;
            } catch (InvalidKeyException e3) {
                b.b(c, "sign InvalidKeyException: " + e3.getMessage());
                return bArr;
            } catch (NoSuchAlgorithmException e4) {
                b.b(c, "sign NoSuchAlgorithmException: " + e4.getMessage());
                return bArr;
            } catch (SignatureException e5) {
                b.b(c, "sign SignatureException: " + e5.getMessage());
                return bArr;
            } catch (Exception e6) {
                b.b(c, "sign Exception: " + e6.getMessage());
                return bArr;
            }
        }
        b.b(c, "content or privateKey is null.");
        return bArr;
    }
}
