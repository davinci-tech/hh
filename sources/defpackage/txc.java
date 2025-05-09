package defpackage;

import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import com.huawei.wisesecurity.ucs_credential.n0;
import defpackage.tsz;

/* loaded from: classes7.dex */
public class txc implements n0 {
    public static tte d;
    public byte[] c;
    public static final n0 e = new txc();

    /* renamed from: a, reason: collision with root package name */
    public static final Object f17401a = new Object();

    public void b(String str) throws twa {
        try {
            if (d.hasAlias(str)) {
                twb.a("KeyStoreManager", "the alias exists", new Object[0]);
                return;
            }
            try {
                d.generate(new ttf(str, 256, KfsKeyPurpose.PURPOSE_CRYPTO));
            } catch (ttn e2) {
                twb.e("KeyStoreManager", txb.a(e2, twf.e("generateKeyPair failed, ")), new Object[0]);
                throw new twa(txb.a(e2, twf.e("generateKeyPair failed , exception ")));
            }
        } catch (ttn e3) {
            twb.e("KeyStoreManager", txb.a(e3, twf.e("containsAlias failed, ")), new Object[0]);
            throw new twa(txb.a(e3, twf.e("containsAlias failed , exception ")));
        }
    }

    public byte[] c(String str, byte[] bArr) throws twa {
        byte[] bArr2;
        synchronized (f17401a) {
            byte[] bArr3 = this.c;
            if (bArr3 == null || bArr3.length <= 0) {
                throw new twa("iv must be set before AES encrypt");
            }
            try {
                bArr2 = new tsz.c(d.getProvider()).a(CipherAlg.AES_GCM).e(d.getKey(str)).a(this.c).b().getEncryptHandler().from(bArr).to();
            } catch (ttn e2) {
                twb.e("KeyStoreManager", "AES doEncrypt failed, " + e2.getMessage(), new Object[0]);
                throw new twa("AES doEncrypt failed , exception " + e2.getMessage());
            }
        }
        return bArr2;
    }

    public byte[] e(String str, byte[] bArr) throws twa {
        byte[] bArr2;
        synchronized (f17401a) {
            byte[] bArr3 = this.c;
            if (bArr3 == null || bArr3.length <= 0) {
                throw new twa("iv must be set before AES decrypt");
            }
            try {
                bArr2 = new tsz.c(d.getProvider()).a(CipherAlg.AES_GCM).e(d.getKey(str)).a(this.c).b().getDecryptHandler().from(bArr).to();
            } catch (ttn e2) {
                twb.e("KeyStoreManager", "AES doDecrypt failed, " + e2.getMessage(), new Object[0]);
                throw new twa("AES doDecrypt failed , exception " + e2.getMessage());
            }
        }
        return bArr2;
    }

    @Override // com.huawei.wisesecurity.ucs_credential.n0
    public byte[] a(String str, String str2) throws twa {
        return new byte[0];
    }
}
