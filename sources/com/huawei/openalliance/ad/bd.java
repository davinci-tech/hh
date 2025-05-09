package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.RewardKeys;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class bd extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return "JsbReportCommonEvent";
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        String str2;
        int i;
        ho.a("JsbReportCommonEvent", "start");
        ContentRecord b = b(context, str);
        JSONObject jSONObject = new JSONObject(str);
        boolean optBoolean = jSONObject.optBoolean(JsbMapKeyNames.CHECK_CONTENT, true);
        boolean optBoolean2 = jSONObject.optBoolean(JsbMapKeyNames.REPORT_NOW, false);
        boolean optBoolean3 = jSONObject.optBoolean(JsbMapKeyNames.CHECK_DISCARD, false);
        if (optBoolean) {
            if (b == null) {
                ho.b("JsbReportCommonEvent", "ad not exist");
                str2 = this.f7108a;
                i = 3002;
            } else if (!a(context, b)) {
                ho.b("JsbReportCommonEvent", "ad is not in whitelist");
                str2 = this.f7108a;
                i = IEventListener.EVENT_ID_DEVICE_CONN_FAIL;
            }
            a(remoteCallResultCallback, str2, i, null, true);
        }
        ho.b("JsbReportCommonEvent", "no check ContentRecord");
        EventRecord eventRecord = (EventRecord) com.huawei.openalliance.ad.utils.be.b(jSONObject.getString(RewardKeys.EVENT_RECORD), EventRecord.class, new Class[0]);
        if (eventRecord == null) {
            ho.c("JsbReportCommonEvent", "reportEvent, eventRecord is null.");
            str2 = this.f7108a;
            i = 3001;
        } else {
            ou ouVar = new ou(context, sc.a(context, eventRecord.l()));
            ouVar.a(b);
            bp bpVar = new bp(context);
            ho.b("JsbReportCommonEvent", "start report event");
            bpVar.a(eventRecord, b, optBoolean2, optBoolean3, ouVar);
            str2 = this.f7108a;
            i = 1000;
        }
        a(remoteCallResultCallback, str2, i, null, true);
    }

    public bd() {
        super("pps.common.report");
    }
}
