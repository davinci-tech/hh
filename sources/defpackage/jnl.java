package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jnl extends HttpConnTask<jnh, jmy> {
    public jnl(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String prepareRequestString(jmy jmyVar) {
        if (jmyVar == null) {
            LogUtil.h("PersonalizeAppletTask", "pwrepareRequestString, request null");
            return null;
        }
        if (TextUtils.isEmpty(jmyVar.g()) || TextUtils.isEmpty(jmyVar.f())) {
            LogUtil.h("PersonalizeAppletTask", "prepareRequestString, invalid param.");
            return null;
        }
        return jnn.a(jmyVar.o(), jmyVar.k(), c(jnn.a(jmyVar.n(), "pass.mod.param", jmyVar.q()), jmyVar));
    }

    private static JSONObject c(JSONObject jSONObject, jmy jmyVar) {
        if (jSONObject == null) {
            LogUtil.h("PersonalizeAppletTask", "createDataString invalid param");
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("issuerid", jmyVar.g());
            jSONObject2.put("passTypeIdentifier", jmyVar.b());
            jSONObject2.put("cplc", jmyVar.h());
            jSONObject2.put("appletAid", jmyVar.f());
            jSONObject2.put(e.n, jmyVar.d());
            jSONObject2.put("deviceModel", jmyVar.i());
            return jSONObject2;
        } catch (JSONException unused) {
            LogUtil.b("PersonalizeAppletTask", "createDataString,JSONException");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jnh readErrorResponse(int i) {
        LogUtil.a("PersonalizeAppletTask", "readErrorResponse errorCode:", Integer.valueOf(i));
        jnh jnhVar = new jnh();
        jnhVar.b(i);
        return jnhVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public jnh readSuccessResponse(JSONObject jSONObject) {
        jnh jnhVar = new jnh();
        if (jSONObject == null) {
            LogUtil.h("PersonalizeAppletTask", "dataObject is null");
            jnhVar.b(-1);
            return jnhVar;
        }
        try {
            if (jSONObject.has("returnCode")) {
                int m = CommonUtil.m(BaseApplication.getContext(), jSONObject.getString("returnCode"));
                jnhVar.b(m);
                if (m == 0) {
                    jnhVar.a(jnn.e(jSONObject, "transactionid"));
                    jnhVar.d(jnn.e(jSONObject, "cardNo"));
                    int d = jnn.d(jSONObject, "invokeIntervalTime");
                    if (d > 0) {
                        jnhVar.c(d);
                    }
                    if (jSONObject.has("nextStep")) {
                        jnhVar.e(jnn.e(jSONObject, "nextStep"));
                    }
                    JSONArray jSONArray = jSONObject.has("apduList") ? jSONObject.getJSONArray("apduList") : null;
                    if (jSONArray != null) {
                        jnhVar.e(jnu.a(jSONArray));
                    }
                }
            } else {
                jnhVar.b(-1);
            }
        } catch (JSONException unused) {
            LogUtil.b("PersonalizeAppletTask", "readSuccessResponse,JSONException");
            jnhVar.b(-1);
        }
        return jnhVar;
    }
}
