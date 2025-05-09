package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.consent.constant.ConsentStatus;
import com.huawei.hms.ads.consent.inter.Consent;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class af extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        Consent.getInstance(context).setConsentStatus(ConsentStatus.forValue(new JSONObject(str).optInt(JsbMapKeyNames.H5_CONSENT_STATUS, ConsentStatus.UNKNOWN.getValue())));
        b(remoteCallResultCallback, true);
    }

    public af() {
        super("pps.set.consentstatus");
    }
}
