package defpackage;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs_credential.k;

/* loaded from: classes7.dex */
public class twg extends k {
    @Override // com.huawei.wisesecurity.ucs_credential.k
    public byte[] a(Credential credential, Context context) throws twc {
        try {
            if (txg.f17402a == null) {
                txg.c.e(null);
            }
            byte[] e = txg.c.e("ucs_alias_rootKey", credential.getKekBytes());
            if (e != null && e.length != 0) {
                return e;
            }
            twk.c(context);
            twb.e("KeyStoreParseHandler", "KeyStore doDecrypt failure.", new Object[0]);
            throw new twc(1020L, "KeyStore doDecrypt failure.");
        } catch (Throwable th) {
            twk.c(context);
            String str = "decrypt kek get exception : " + th.getMessage();
            throw two.e("KeyStoreParseHandler", str, new Object[0], 1020L, str);
        }
    }
}
