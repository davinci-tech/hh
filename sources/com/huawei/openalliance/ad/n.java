package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.openalliance.ad.analysis.BaseAnalysisInfo;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class n extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbCommonAnalysis", "start");
        ContentRecord b = b(context, str);
        JSONObject jSONObject = new JSONObject(str);
        boolean optBoolean = jSONObject.optBoolean(JsbMapKeyNames.CHECK_CONTENT, true);
        int optInt = jSONObject.optInt("adType", -1);
        String optString = jSONObject.optString("slotid", "");
        boolean optBoolean2 = jSONObject.optBoolean(JsbMapKeyNames.REPORT_NOW, false);
        boolean optBoolean3 = jSONObject.optBoolean(JsbMapKeyNames.CHECK_DISCARD, false);
        if (!optBoolean) {
            ho.b("JsbCommonAnalysis", "no check ContentRecord");
        } else if (b == null) {
            ho.b("JsbCommonAnalysis", "ad not exist");
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
            return;
        } else if (!a(context, b)) {
            ho.b("JsbCommonAnalysis", "ad is not in whitelist");
            a(remoteCallResultCallback, this.f7108a, IEventListener.EVENT_ID_DEVICE_CONN_FAIL, null, true);
            return;
        }
        BaseAnalysisInfo baseAnalysisInfo = (BaseAnalysisInfo) com.huawei.openalliance.ad.utils.be.b(jSONObject.getString("analysis_info"), BaseAnalysisInfo.class, new Class[0]);
        bo boVar = new bo(context);
        com.huawei.openalliance.ad.analysis.b a2 = b != null ? boVar.a(b, baseAnalysisInfo) : boVar.a(baseAnalysisInfo);
        if (a2 == null) {
            ho.c("JsbCommonAnalysis", "onAnalysis, info is null.");
            a(remoteCallResultCallback, this.f7108a, 3001, null, true);
            return;
        }
        ho.b("JsbCommonAnalysis", "onAnalysis, type: %s", a2.aK());
        if (ho.a()) {
            ho.a("JsbCommonAnalysis", "onAnalysis, info: %s", com.huawei.openalliance.ad.utils.be.b(a2));
        }
        if (b == null) {
            a2.a(optInt);
            a2.o(optString);
        }
        ou ouVar = new ou(context, sc.a(context, optInt));
        ouVar.a(b);
        ouVar.b(a2, optBoolean2, optBoolean3);
        b(remoteCallResultCallback, true);
    }

    public n() {
        super("pps.common.analysis");
    }
}
