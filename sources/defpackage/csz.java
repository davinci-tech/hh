package defpackage;

import android.text.TextUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
public class csz {
    private byte[] c = new byte[16];
    private byte[] b = new byte[16];

    public byte[] c() {
        return (byte[]) this.c.clone();
    }

    public byte[] e() {
        return (byte[]) this.b.clone();
    }

    public void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            cpw.e(true, "CoapEncryptExpression", "snAppRandom or snDeviceRandom is not right!");
            return;
        }
        if (str.length() != 16 || str2.length() != 16) {
            cpw.e(true, "CoapEncryptExpression", "snAppRandom or snDeviceRandom length is not right!");
            return;
        }
        byte[] d = ctv.d(str);
        byte[] d2 = ctv.d(str2);
        byte[] bArr = new byte[d.length + d2.length];
        System.arraycopy(d, 0, bArr, 0, d.length);
        System.arraycopy(d2, 0, bArr, d.length, d2.length);
        cty.b();
        byte[] bArr2 = new byte[0];
        try {
            bArr2 = ctv.e(bArr, 1, 32);
        } catch (InvalidKeyException e) {
            cpw.c(true, "CoapEncryptExpression", e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            cpw.c(true, "CoapEncryptExpression", e2.getMessage());
        }
        if (bArr2 == null || bArr2.length != 32) {
            cpw.e(true, "CoapEncryptExpression", "get digest error! ");
            return;
        }
        byte[] bArr3 = this.c;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
        cpw.c(true, "CoapEncryptExpression", "psk = ", ctv.a(this.c));
        int length = this.c.length;
        byte[] bArr4 = this.b;
        System.arraycopy(bArr2, length, bArr4, 0, bArr4.length);
        cpw.c(true, "CoapEncryptExpression", "iv = ", ctv.a(this.b));
    }
}
