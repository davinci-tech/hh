package defpackage;

import com.huawei.hms.network.NetworkKit;
import com.huawei.wisesecurity.ucs_credential.i0;

/* loaded from: classes7.dex */
public class twu extends NetworkKit.Callback {
    @Override // com.huawei.hms.network.NetworkKit.Callback
    public void onResult(boolean z) {
        if (z) {
            twb.e("RemoteRestClient", "Networkkit init success", new Object[0]);
        } else {
            twb.e("RemoteRestClient", " Networkkit init failed", new Object[0]);
        }
    }

    public twu(i0 i0Var) {
    }
}
