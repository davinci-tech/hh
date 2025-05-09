package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class p extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        int i;
        if (ho.a()) {
            ho.a("JsbFeedbackToggle", "start");
        }
        String optString = new JSONObject(str).optString(JsbMapKeyNames.FEEDBACK_CODE, "3");
        ContentRecord b = b(context, str);
        if (b != null) {
            new com.huawei.openalliance.ad.analysis.h(context).f(b, optString);
            i = 1000;
        } else {
            ho.a("JsbFeedbackToggle", "ad not exist");
            i = 3002;
        }
        a(remoteCallResultCallback, this.f7108a, i, null, true);
    }

    public p() {
        super("pps.feedback.toggle");
    }
}
