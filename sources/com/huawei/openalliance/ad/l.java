package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class l extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        int i;
        ho.a("JsbClickShare", "start");
        String optString = new JSONObject(str).optString(JsbMapKeyNames.H5_CSHARE_URL, "");
        ContentRecord b = b(context, str);
        if (b != null) {
            ho.a("JsbClickShare", "start dialog activity");
            com.huawei.openalliance.ad.inter.data.e a2 = pd.a(context, b, true);
            a2.V(optString);
            bx.a(context, a2, b);
            i = 1000;
        } else {
            ho.a("JsbClickShare", "ad not exist");
            i = 3002;
        }
        a(remoteCallResultCallback, this.f7108a, i, null, true);
    }

    public l() {
        super("pps.click.share");
    }
}
