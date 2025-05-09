package defpackage;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs_credential.k;

/* loaded from: classes7.dex */
public class tws extends k {
    @Override // com.huawei.wisesecurity.ucs_credential.k
    public byte[] a(Credential credential, Context context) throws twc {
        return UcsLib.decryptKek(credential.getKekBytes(), credential.getAlg());
    }
}
