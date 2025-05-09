package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jng extends HttpConnTask<jnb, jmw> {
    public jng(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String prepareRequestString(jmw jmwVar) {
        if (jmwVar == null) {
            LogUtil.h("ApplyApduTask", "prepareRequestString request is null");
            return null;
        }
        if (TextUtils.isEmpty(jmwVar.g()) || TextUtils.isEmpty(jmwVar.f())) {
            LogUtil.h("ApplyApduTask", "prepareRequestString invalid param");
            return null;
        }
        if (jmwVar.a().isEmpty() || jmwVar.c() != jmwVar.a().size()) {
            LogUtil.h("ApplyApduTask", "prepareRequestString ApduList is null");
        }
        return jnn.a(jmwVar.o(), jmwVar.k(), a(jnn.a(jmwVar.n(), "get.apdu", jmwVar.q()), jmwVar));
    }

    private static JSONObject a(JSONObject jSONObject, jmw jmwVar) {
        if (jSONObject == null) {
            LogUtil.h("ApplyApduTask", "prepareRequestString headerObject is null");
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("issuerid", jmwVar.g());
            jSONObject2.put("appletAid", jmwVar.f());
            jSONObject2.put("cplc", jmwVar.h());
            jSONObject2.put("transactionid", jmwVar.n());
            jSONObject2.put("apduCount", jmwVar.c());
            jSONObject2.put("seChipManuFacturer", jmwVar.l());
            jSONObject2.put("deviceModel", jmwVar.i());
            jSONObject2.put("clientVersion", jmwVar.j());
            if (!TextUtils.isEmpty(jmwVar.d())) {
                jSONObject2.put("commandId", jmwVar.d());
            }
            jSONObject2.put("apduList", d(jmwVar.a()));
            if (!TextUtils.isEmpty(jmwVar.m())) {
                jSONObject2.put(CommonUtil.IMEI, jmwVar.m());
            }
            if (!TextUtils.isEmpty(jmwVar.b())) {
                jSONObject2.put("partnerId", jmwVar.b());
            }
            if (!TextUtils.isEmpty(jmwVar.e())) {
                jSONObject2.put("currentStep", jmwVar.e());
            }
            return jSONObject2;
        } catch (JSONException unused) {
            LogUtil.b("ApplyApduTask", "createRequestString JSONException");
            return null;
        }
    }

    public static JSONArray d(List<jmu> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    for (jmu jmuVar : list) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("apduNo", jmuVar.b());
                        jSONObject.put("apduContent", jmuVar.c());
                        if (!TextUtils.isEmpty(jmuVar.a())) {
                            jSONObject.put("apduStatus", jmuVar.a());
                        }
                        if (!TextUtils.isEmpty(jmuVar.e())) {
                            jSONObject.put(AwarenessRequest.Field.COMMAND, jmuVar.e());
                        }
                        if (!TextUtils.isEmpty(jmuVar.d())) {
                            jSONObject.put("checker", jmuVar.d());
                        }
                        jSONArray.put(jSONObject);
                    }
                }
            } catch (JSONException unused) {
                LogUtil.b("ApplyApduTask", "createRequestString JSONException");
            }
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public jnb readErrorResponse(int i) {
        LogUtil.a("ApplyApduTask", "readErrorResponse errorCode", Integer.valueOf(i));
        jnb jnbVar = new jnb();
        jnbVar.b(i);
        return jnbVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public jnb readSuccessResponse(JSONObject jSONObject) {
        jnb jnbVar = new jnb();
        if (jSONObject == null) {
            LogUtil.h("ApplyApduTask", "object is null");
            jnbVar.b(-1);
            return jnbVar;
        }
        try {
            if (jSONObject.has("returnCode")) {
                int m = health.compact.a.CommonUtil.m(BaseApplication.getContext(), jSONObject.getString("returnCode"));
                jnbVar.b(m);
                if (m == 0) {
                    if (jSONObject.has("transactionid")) {
                        jnbVar.a(jnn.e(jSONObject, "transactionid"));
                    }
                    JSONArray jSONArray = jSONObject.has("apduList") ? jSONObject.getJSONArray("apduList") : null;
                    if (jSONArray != null) {
                        jnbVar.e(jnu.a(jSONArray));
                    }
                    if (jSONObject.has("nextStep")) {
                        jnbVar.e(jnn.e(jSONObject, "nextStep"));
                    }
                }
            } else {
                jnbVar.b(-1);
            }
        } catch (JSONException unused) {
            LogUtil.b("ApplyApduTask", "readSuccessResponse JSONException");
            jnbVar.b(-1);
        }
        return jnbVar;
    }
}
