package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class te {
    public static void a(Context context, JSONObject jSONObject) {
        ms.a(context).a(RTCMethods.DC_BRIDGE, jSONObject.toString(), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.te.1
            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                String str2;
                if (callResult != null) {
                    try {
                        if (callResult.getCode() == 200) {
                            if (ho.a()) {
                                str2 = "query DC_BRIDGE from hms success!";
                                ho.a("DcServiceCmdManager", str2);
                            }
                            return;
                        }
                    } catch (Throwable th) {
                        ho.d("DcServiceCmdManager", "get DC_BRIDGE from hms err : %s", th.getClass().getSimpleName());
                        return;
                    }
                }
                if (ho.a()) {
                    str2 = "failed to query DC_BRIDGE from hms";
                    ho.a("DcServiceCmdManager", str2);
                }
            }
        }, String.class);
    }

    public static void a(final Context context, final tf tfVar) {
        if (tfVar == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.te.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String b = fh.b(context).b(ConfigMapKeys.REDIRECTION_APP_LIST, "");
                    ho.a("DcServiceCmdManager", "redirectionAppList from configMap : %s", b);
                    List<String> a2 = com.huawei.openalliance.ad.utils.cz.a(b, ",");
                    if (!com.huawei.openalliance.ad.utils.bg.a(a2) && a2.contains(tfVar.d())) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("dc_service_cmd", 10001);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("taskId", tfVar.c());
                        jSONObject2.put("contentId", tfVar.b());
                        jSONObject2.put(SdkCfgSha256Record.PKGNAME, tfVar.d());
                        jSONObject2.put("activityName", tfVar.e());
                        jSONObject2.put("triggerTime", System.currentTimeMillis());
                        ho.a("DcServiceCmdManager", "send direction match record : %s", jSONObject2.toString());
                        jSONObject.put(RemoteMessageConst.MessageBody.PARAM, jSONObject2);
                        te.a(context, jSONObject);
                        return;
                    }
                    ho.a("DcServiceCmdManager", "%s is not in app list", tfVar.d());
                } catch (JSONException e) {
                    ho.d("DcServiceCmdManager", "json exception sendRedirectionMatchRecord : %s", e.getClass().getSimpleName());
                }
            }
        });
    }
}
