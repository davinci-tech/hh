package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jnm extends HttpConnTask<jnd, jna> {
    public jnm(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String prepareRequestString(jna jnaVar) {
        if (jnaVar == null) {
            LogUtil.h("DownloadInstallAppletTask", "prepareRequestString request null.");
            return null;
        }
        if (TextUtils.isEmpty(jnaVar.g()) || TextUtils.isEmpty(jnaVar.f())) {
            LogUtil.h("DownloadInstallAppletTask", "prepareRequestString invalid param.");
            return null;
        }
        return jnn.a(jnaVar.o(), jnaVar.k(), b(jnn.a(jnaVar.n(), "download.install.app", jnaVar.q()), jnaVar));
    }

    private static JSONObject b(JSONObject jSONObject, jna jnaVar) {
        if (jSONObject == null) {
            LogUtil.h("DownloadInstallAppletTask", "createDataString invalid param.");
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("issuerid", jnaVar.g());
            jSONObject2.put("userid", jnaVar.s());
            jSONObject2.put("cplc", jnaVar.h());
            jSONObject2.put("appletAid", jnaVar.f());
            jSONObject2.put("seChipManuFacturer", jnaVar.l());
            jSONObject2.put("deviceModel", jnaVar.i());
            jSONObject2.put("clientVersion", jnaVar.j());
            if (!TextUtils.isEmpty(jnaVar.s())) {
                jSONObject2.put("userid", jnaVar.s());
            }
            if (!TextUtils.isEmpty(jnaVar.m())) {
                jSONObject2.put(CommonUtil.IMEI, jnaVar.m());
            }
            if (!TextUtils.isEmpty(jnaVar.d())) {
                jSONObject2.put("onlyCap", jnaVar.d());
            }
            return jSONObject2;
        } catch (JSONException unused) {
            LogUtil.b("DownloadInstallAppletTask", "createRequestString JSONException");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public jnd readErrorResponse(int i) {
        LogUtil.a("DownloadInstallAppletTask", "readErrorResponse errorCode :", Integer.valueOf(i));
        jnd jndVar = new jnd();
        jndVar.b(i);
        return jndVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public jnd readSuccessResponse(JSONObject jSONObject) {
        jnd jndVar = new jnd();
        if (jSONObject == null) {
            LogUtil.h("DownloadInstallAppletTask", "dataObject is null.");
            jndVar.b(-1);
            return jndVar;
        }
        try {
            if (jSONObject.has("returnCode")) {
                int m = health.compact.a.CommonUtil.m(BaseApplication.getContext(), jSONObject.getString("returnCode"));
                jndVar.b(m);
                if (m == 0) {
                    jndVar.a(jnn.e(jSONObject, "transactionid"));
                    if (jSONObject.has("nextStep")) {
                        jndVar.b(jnn.e(jSONObject, "nextStep"));
                    }
                    JSONArray jSONArray = jSONObject.has("apduList") ? jSONObject.getJSONArray("apduList") : null;
                    if (jSONArray != null) {
                        jndVar.e(jnu.a(jSONArray));
                    }
                }
            } else {
                jndVar.b(-1);
            }
        } catch (JSONException unused) {
            LogUtil.b("DownloadInstallAppletTask", "readSuccessResponse JSONException.");
            jndVar.b(-1);
        }
        return jndVar;
    }
}
