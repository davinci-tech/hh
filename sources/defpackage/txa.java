package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.ucs.credential.entity.EcKeyPair;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;

/* loaded from: classes7.dex */
public class txa {
    public static EcKeyPair a(Context context) throws twc {
        try {
            return d(context);
        } catch (twc unused) {
            EcKeyPair generateEcKeyPair = UcsLib.generateEcKeyPair(context);
            if (generateEcKeyPair == null) {
                throw new twc(1022L, "key is null");
            }
            if (txc.d == null) {
                txc.d = new tte();
            }
            txc txcVar = (txc) txc.e;
            txcVar.b("ucs_aes_alias_rootKey");
            byte[] e = tua.e(CipherAlg.AES_GCM.getIvLen());
            txcVar.c = e;
            twi.c("ucs_ec_credential_enc_sp_key", twe.a(e, 2) + ":" + twe.a(txcVar.c("ucs_aes_alias_rootKey", generateEcKeyPair.getPublicKey()), 10) + ":" + twe.a(txcVar.c("ucs_aes_alias_rootKey", generateEcKeyPair.getPrivateKey()), 10), context);
            return generateEcKeyPair;
        }
    }

    public static EcKeyPair d(Context context) throws twc {
        EcKeyPair.Builder newBuilder = EcKeyPair.newBuilder();
        String b = twi.b("ucs_ec_credential_enc_sp_key", "", context);
        if (TextUtils.isEmpty(b)) {
            throw new twc(1022L, "no cache key");
        }
        String[] split = b.split(":");
        if (split.length != 3) {
            throw new twc(1022L, "invalid cache key");
        }
        if (txc.d == null) {
            txc.d = new tte();
        }
        txc txcVar = (txc) txc.e;
        txcVar.b("ucs_aes_alias_rootKey");
        txcVar.c = twe.c(split[0], 2);
        byte[] e = txcVar.e("ucs_aes_alias_rootKey", twe.c(split[1], 10));
        byte[] e2 = txcVar.e("ucs_aes_alias_rootKey", twe.c(split[2], 10));
        newBuilder.publicKey(e);
        newBuilder.privateKey(e2);
        return newBuilder.build();
    }

    public static void a(EcKeyPair ecKeyPair) {
        byte[] privateKey = ecKeyPair.getPrivateKey();
        if (privateKey == null) {
            return;
        }
        int length = privateKey.length;
        for (int i = 0; i < length; i++) {
            privateKey[i] = 0;
        }
    }
}
