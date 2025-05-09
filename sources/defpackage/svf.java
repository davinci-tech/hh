package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.httputil.BasePassCardTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class svf extends BasePassCardTask<svd, svc> {
    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    public int getErrorLogConstant() {
        return 0;
    }

    public svf(Context context, String str) {
        super(context, str);
    }

    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    public void makeResponseData(sua suaVar, JSONObject jSONObject) throws JSONException {
        if (suaVar instanceof svd) {
            svd svdVar = (svd) suaVar;
            try {
                if (jSONObject.has("status")) {
                    svdVar.d(jSONObject.getString("status"));
                    return;
                }
                return;
            } catch (JSONException unused) {
                stq.e("GetParticipationStatusTask", "readSuccessResponse, JSONException");
                svdVar.e(-99);
                return;
            }
        }
        stq.e("GetParticipationStatusTask", "response instanceof GetParticipationStatusResponse is false", false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(svc svcVar) {
        if (svcVar == null) {
            stq.e("GetParticipationStatusTask", "prepareRequestStr, invalid param", false);
            return "";
        }
        JSONObject b = swh.b(svcVar.h(), "/api/v1/client/getParticipationStatus", svcVar.d());
        stq.b("GetParticipationStatusTask", "srcTranID = " + svcVar.h());
        return d(b, svcVar).toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public svd readErrorResponse(int i) {
        stq.c("GetParticipationStatusTask", "readErrorResponse errorCode is " + i, false);
        svd svdVar = new svd();
        svdVar.g = i;
        if (i == -1) {
            svdVar.g = -1;
        } else if (i == -3) {
            svdVar.g = 1;
        } else if (i == -2) {
            svdVar.g = -2;
        } else {
            stq.c("GetParticipationStatusTask", "readErrorResponse others " + i, false);
        }
        return svdVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public svd readSuccessResponse(String str) {
        svd svdVar = new svd();
        resolveResponse(svdVar, str);
        return svdVar;
    }

    private JSONObject d(JSONObject jSONObject, svc svcVar) {
        JSONObject jSONObject2;
        try {
            jSONObject2 = new JSONObject();
        } catch (JSONException unused) {
            jSONObject2 = null;
        }
        try {
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("cplc", svcVar.b());
            jSONObject2.put("deviceId", svcVar.e());
            jSONObject2.put("deviceType", "UDID");
        } catch (JSONException unused2) {
            stq.e("GetParticipationStatusTask", " createDataStr parse json error:JSONException", true);
            return jSONObject2;
        }
        return jSONObject2;
    }

    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    public String getTag() {
        return "GetParticipationStatusTask";
    }
}
