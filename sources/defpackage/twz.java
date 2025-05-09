package defpackage;

import android.text.TextUtils;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.ucs.credential.entity.UcsKeyStoreProvider;
import com.huawei.wisesecurity.ucs_credential.n0;
import defpackage.ttf;
import defpackage.tth;
import java.security.cert.Certificate;

/* loaded from: classes7.dex */
public class twz implements n0 {
    public static KeyStoreKeyManager b;
    public static final n0 d = new twz();

    /* renamed from: a, reason: collision with root package name */
    public static final Object f17400a = new Object();

    public void b(String str) throws twa {
        try {
            if (b.hasAlias(str)) {
                twb.a("KeyStoreManager", "the alias exists", new Object[0]);
                return;
            }
            try {
                b.generate(new ttf.d().c(str).e(KfsKeyPurpose.PURPOSE_SIGN).b(256).b());
            } catch (ttn e) {
                twb.e("KeyStoreManager", txb.a(e, twf.e("generateKeyPair failed, ")), new Object[0]);
                throw new twa(txb.a(e, twf.e("generateKeyPair failed , exception ")));
            }
        } catch (ttn e2) {
            twb.e("KeyStoreManager", txb.a(e2, twf.e("containsAlias failed, ")), new Object[0]);
            throw new twa(txb.a(e2, twf.e("containsAlias failed , exception ")));
        }
    }

    public void d(UcsKeyStoreProvider ucsKeyStoreProvider) throws twa {
        b = new ttc(TextUtils.equals(ucsKeyStoreProvider.getName(), KeyStoreProvider.ANDROID_KEYSTORE.getName()) ? KeyStoreProvider.ANDROID_KEYSTORE : KeyStoreProvider.HUAWEI_KEYSTORE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001d, code lost:
    
        if (r0.getProvider() != (android.text.TextUtils.equals(r3.getName(), com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider.ANDROID_KEYSTORE.getName()) ? com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider.ANDROID_KEYSTORE : com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider.HUAWEI_KEYSTORE)) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.wisesecurity.ucs_credential.n0 c(com.huawei.wisesecurity.ucs.credential.entity.UcsKeyStoreProvider r3) throws defpackage.twa {
        /*
            com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager r0 = defpackage.twz.b
            if (r0 == 0) goto L1f
            com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider r0 = r0.getProvider()
            java.lang.String r1 = r3.getName()
            com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider r2 = com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider.ANDROID_KEYSTORE
            java.lang.String r2 = r2.getName()
            boolean r1 = android.text.TextUtils.equals(r1, r2)
            if (r1 == 0) goto L1b
            com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider r1 = com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider.ANDROID_KEYSTORE
            goto L1d
        L1b:
            com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider r1 = com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider.HUAWEI_KEYSTORE
        L1d:
            if (r0 == r1) goto L26
        L1f:
            com.huawei.wisesecurity.ucs_credential.n0 r0 = defpackage.twz.d
            twz r0 = (defpackage.twz) r0
            r0.d(r3)
        L26:
            com.huawei.wisesecurity.ucs_credential.n0 r3 = defpackage.twz.d
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.twz.c(com.huawei.wisesecurity.ucs.credential.entity.UcsKeyStoreProvider):com.huawei.wisesecurity.ucs_credential.n0");
    }

    public Certificate[] e(String str) throws twa {
        try {
            return b.getCertificateChain(str);
        } catch (ttn e) {
            twb.e("KeyStoreManager", txb.a(e, twf.e("getCertificateChain failed, ")), new Object[0]);
            throw new twa(txb.a(e, twf.e("getCertificateChain failed , exception ")));
        }
    }

    @Override // com.huawei.wisesecurity.ucs_credential.n0
    public byte[] a(String str, String str2) throws twa {
        byte[] sign;
        synchronized (f17400a) {
            try {
                try {
                    sign = ((KfsSigner) new tth.e(b.getProvider()).withAlg(SignAlg.ECDSA).withKeyStoreAlias(str).build()).getSignHandler().from(str2).sign();
                } catch (ttn e) {
                    twb.e("KeyStoreManager", "doSign failed, " + e.getMessage(), new Object[0]);
                    throw new twa("doSign failed , exception " + e.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return sign;
    }
}
