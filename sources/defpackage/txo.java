package defpackage;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.entity.EcKeyPair;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs_credential.k;

/* loaded from: classes7.dex */
public class txo extends k {
    @Override // com.huawei.wisesecurity.ucs_credential.k
    public byte[] a(Credential credential, Context context) throws twc {
        try {
            String[] split = credential.getKekString().split(":");
            if (split.length < 3) {
                twb.e("KeyStoreECIESParseHandler", "EC kek doDecrypt failure. kek string invalid", new Object[0]);
                throw new twc(1020L, "EC kek doDecrypt failure. kek string invalid");
            }
            byte[] c = twe.c(split[0], 0);
            byte[] c2 = twe.c(split[1], 0);
            byte[] c3 = twe.c(split[2], 0);
            EcKeyPair d = txa.d(context);
            byte[] decryptKekWithEc = UcsLib.decryptKekWithEc(c3, 1, c, c2, d.getPrivateKey());
            if (decryptKekWithEc == null || decryptKekWithEc.length == 0) {
                twb.e("KeyStoreECIESParseHandler", "KeyStore doDecrypt failure.", new Object[0]);
                throw new twc(1020L, "KeyStore doDecrypt failure.");
            }
            txa.a(d);
            return decryptKekWithEc;
        } catch (twc e) {
            if (credential.getKekVersion() == 6) {
                twk.e(context);
            } else {
                twi.a("ucs_ec_huks_sp_key_t", 0, context);
            }
            StringBuilder e2 = twf.e("decrypt kek get exception : ");
            e2.append(e.getMessage());
            String sb = e2.toString();
            throw two.e("KeyStoreECIESParseHandler", sb, new Object[0], 1020L, sb);
        }
    }
}
