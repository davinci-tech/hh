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
public class jno extends HttpConnTask<jne, jmz> {
    public jno(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String prepareRequestString(jmz jmzVar) {
        if (jmzVar == null) {
            LogUtil.h("DeleteAppletTask", "prepareRequestString, request null");
            return null;
        }
        if (TextUtils.isEmpty(jmzVar.g()) || TextUtils.isEmpty(jmzVar.f())) {
            LogUtil.h("DeleteAppletTask", "prepareRequestString, invalid param");
            return null;
        }
        return jnn.a(jmzVar.o(), jmzVar.k(), c(jnn.a(jmzVar.n(), "delete.app", jmzVar.q()), jmzVar));
    }

    private static JSONObject c(JSONObject jSONObject, jmz jmzVar) {
        if (jSONObject == null) {
            LogUtil.h("DeleteAppletTask", "prepareRequestString, request null");
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("issuerid", jmzVar.g());
            jSONObject2.put("cplc", jmzVar.h());
            jSONObject2.put("appletAid", jmzVar.f());
            jSONObject2.put("deviceModel", jmzVar.i());
            jSONObject2.put("seChipManuFacturer", jmzVar.l());
            if (!TextUtils.isEmpty(jmzVar.s())) {
                jSONObject2.put("userid", jmzVar.s());
            }
            if (!TextUtils.isEmpty(jmzVar.m())) {
                jSONObject2.put(CommonUtil.IMEI, jmzVar.m());
            }
            if (!TextUtils.isEmpty(jmzVar.d())) {
                jSONObject2.put("flag", jmzVar.d());
            }
            return jSONObject2;
        } catch (JSONException unused) {
            LogUtil.b("DeleteAppletTask", "createRequestString JSONException");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public jne readErrorResponse(int i) {
        LogUtil.a("DeleteAppletTask", "readErrorResponse errorCode :", Integer.valueOf(i));
        jne jneVar = new jne();
        jneVar.b(i);
        return jneVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public jne readSuccessResponse(JSONObject jSONObject) {
        jne jneVar = new jne();
        if (jSONObject == null) {
            LogUtil.h("DeleteAppletTask", "jsonObject is null");
            jneVar.b(-1);
            return jneVar;
        }
        try {
            if (jSONObject.has("returnCode")) {
                int m = health.compact.a.CommonUtil.m(BaseApplication.getContext(), jSONObject.getString("returnCode"));
                jneVar.b(m);
                if (m == 0) {
                    jneVar.a(jnn.e(jSONObject, "transactionid"));
                    if (jSONObject.has("nextStep")) {
                        jneVar.d(jnn.e(jSONObject, "nextStep"));
                    }
                    JSONArray jSONArray = jSONObject.has("apduList") ? jSONObject.getJSONArray("apduList") : null;
                    if (jSONArray != null) {
                        jneVar.e(jnu.a(jSONArray));
                    }
                }
            } else {
                jneVar.b(-1);
            }
        } catch (JSONException unused) {
            LogUtil.b("DeleteAppletTask", "readSuccessResponse,JSONException");
            jneVar.b(-1);
        }
        return jneVar;
    }
}
