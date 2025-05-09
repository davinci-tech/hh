package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.httputil.BasePassCardTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class sva extends BasePassCardTask<suy, suw> {
    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    public int getErrorLogConstant() {
        return 0;
    }

    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    public void makeResponseData(sua suaVar, JSONObject jSONObject) throws JSONException {
    }

    public sva(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(suw suwVar) {
        if (suwVar == null) {
            stq.e("ApplyForParticipationTask", "prepareRequestStr, invalid param", false);
            return "";
        }
        JSONObject b = swh.b(suwVar.h(), "/api/v1/client/applyForParticipation", suwVar.d());
        stq.b("ApplyForParticipationTask", "srcTranID = " + suwVar.h());
        return b(b, suwVar).toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public suy readErrorResponse(int i) {
        stq.c("ApplyForParticipationTask", "readErrorResponse errorCode is " + i, false);
        suy suyVar = new suy();
        suyVar.g = i;
        if (i == -1) {
            suyVar.g = -1;
        } else if (i == -3) {
            suyVar.g = 1;
        } else if (i == -2) {
            suyVar.g = -2;
        } else {
            stq.c("ApplyForParticipationTask", "readErrorResponse others " + i, false);
        }
        return suyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public suy readSuccessResponse(String str) {
        stq.b("ApplyForParticipationTask", "readSuccessResponse");
        suy suyVar = new suy();
        resolveResponse(suyVar, str);
        return suyVar;
    }

    private JSONObject b(JSONObject jSONObject, suw suwVar) {
        JSONObject jSONObject2;
        try {
            jSONObject2 = new JSONObject();
        } catch (JSONException unused) {
            jSONObject2 = null;
        }
        try {
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("cplc", suwVar.a());
            jSONObject2.put("deviceId", suwVar.e());
            jSONObject2.put("deviceType", "UDID");
        } catch (JSONException unused2) {
            stq.e("ApplyForParticipationTask", " createDataStr parse json error:JSONException", true);
            return jSONObject2;
        }
        return jSONObject2;
    }

    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    public String getTag() {
        return "ApplyForParticipationTask";
    }
}
