package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jnp extends HttpConnTask<jni, jnc> {
    public jnp(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String prepareRequestString(jnc jncVar) {
        if (jncVar == null) {
            LogUtil.h("TsmParamQueryTask", "prepareRequestString, request null");
            return null;
        }
        return jnn.a(jncVar.o(), jncVar.k(), c(jnn.a(jncVar.n(), "nfc.get.NotifyInfoInit", jncVar.q()), jncVar));
    }

    private static JSONObject c(JSONObject jSONObject, jnc jncVar) {
        if (jSONObject == null) {
            LogUtil.h("TsmParamQueryTask", "createDataString headerObject is null");
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("cplc", jncVar.h());
            jSONObject2.put("terminal", jncVar.i());
            jSONObject2.put("tsmChannel", 0);
            return jSONObject2;
        } catch (JSONException unused) {
            LogUtil.b("TsmParamQueryTask", "createDataString, params invalid.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public jni readErrorResponse(int i) {
        jni jniVar = new jni();
        jniVar.b(i);
        return jniVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.task.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jni readSuccessResponse(JSONObject jSONObject) {
        jni jniVar = new jni();
        if (jSONObject == null) {
            LogUtil.h("TsmParamQueryTask", "dataObject is null");
            jniVar.b(-1);
            return jniVar;
        }
        try {
            if (jSONObject.has("returnCode")) {
                int m = CommonUtil.m(BaseApplication.getContext(), jSONObject.getString("returnCode"));
                jniVar.b(m);
                if (m == 0) {
                    jniVar.a(jnn.e(jSONObject, "transactionid"));
                    String e = jSONObject.has("funcID") ? jnn.e(jSONObject, "funcID") : null;
                    String e2 = jSONObject.has("servicID") ? jnn.e(jSONObject, "servicID") : null;
                    if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(e2)) {
                        jniVar.b(e);
                        jniVar.c(e2);
                    }
                    LogUtil.h("readSuccessResponse, illegal funcId or servicId", new Object[0]);
                    jniVar.b(-1);
                    return jniVar;
                }
            } else {
                jniVar.b(-1);
            }
        } catch (JSONException unused) {
            LogUtil.b("TsmParamQueryTask", "readSuccessResponse,JSONException");
            jniVar.b(-1);
        }
        return jniVar;
    }
}
