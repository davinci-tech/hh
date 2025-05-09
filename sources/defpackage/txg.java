package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.ucs.credential.entity.UcsKeyStoreProvider;
import com.huawei.wisesecurity.ucs_credential.n0;
import defpackage.ttf;
import defpackage.ttg;
import defpackage.ttq;
import java.security.cert.Certificate;

/* loaded from: classes7.dex */
public class txg implements n0 {

    /* renamed from: a, reason: collision with root package name */
    public static ttd f17402a;
    public static final txg c = new txg();
    public static final Object e = new Object();

    public void b(String str) throws twa {
        try {
            if (f17402a.hasAlias(str)) {
                twb.a("KeyStoreManager", "the alias exists", new Object[0]);
                return;
            }
            try {
                f17402a.generate(new ttf.d().c(str).e(KfsKeyPurpose.PURPOSE_ALL).b(HealthData.ECG).b());
                twb.a("KeyStoreManager", "generateKeyPair OK", new Object[0]);
            } catch (ttn e2) {
                twb.e("KeyStoreManager", txb.a(e2, twf.e("generateKeyPair failed, ")), new Object[0]);
                throw new twa(txb.a(e2, twf.e("generateKeyPair failed , exception ")));
            }
        } catch (ttn e3) {
            twb.e("KeyStoreManager", txb.a(e3, twf.e("containsAlias failed, ")), new Object[0]);
            throw new twa(txb.a(e3, twf.e("containsAlias failed , exception ")));
        }
    }

    public Certificate[] a(String str) throws twa {
        try {
            return f17402a.getCertificateChain(str);
        } catch (ttn e2) {
            twb.e("KeyStoreManager", txb.a(e2, twf.e("getCertificateChain failed, ")), new Object[0]);
            throw new twa(txb.a(e2, twf.e("getCertificateChain failed , exception ")));
        }
    }

    public byte[] e(String str, byte[] bArr) throws twa {
        byte[] bArr2;
        synchronized (e) {
            try {
                try {
                    bArr2 = new ttg.d(f17402a.getProvider()).a(CipherAlg.RSA_OAEP).withKeyStoreAlias(str).build().getDecryptHandler().from(bArr).to();
                } catch (ttn e2) {
                    twb.e("KeyStoreManager", "doDecrypt failed, " + e2.getMessage(), new Object[0]);
                    throw new twa("doDecrypt failed , exception " + e2.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArr2;
    }

    @Override // com.huawei.wisesecurity.ucs_credential.n0
    public byte[] a(String str, String str2) throws twa {
        byte[] sign;
        synchronized (e) {
            try {
                try {
                    sign = ((KfsSigner) new ttq.e(f17402a.getProvider()).withAlg(SignAlg.RSA_SHA256_PSS).withKeyStoreAlias(str).build()).getSignHandler().from(str2).sign();
                } catch (ttn e2) {
                    twb.e("KeyStoreManager", "doSign failed, " + e2.getMessage(), new Object[0]);
                    throw new twa("doSign failed , exception " + e2.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return sign;
    }

    public void e(UcsKeyStoreProvider ucsKeyStoreProvider) {
        f17402a = new ttd();
    }
}
