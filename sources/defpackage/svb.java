package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.wear.oversea.impl.HttpConnTask;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class svb extends HttpConnTask<sud, suo> {
    private static final String d = "CardStatusQueryTask";

    public svb(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.impl.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(suo suoVar) {
        if (suoVar == null || swe.b((CharSequence) suoVar.h(), true) || swe.b((CharSequence) suoVar.g(), true) || swe.b((CharSequence) suoVar.e, true)) {
            stq.c(d, "prepareRequestStr, params invalid.");
            return null;
        }
        return swd.e(suoVar.g(), suoVar.c(), c(swh.b(suoVar.h(), "nfc.get.list.card", suoVar.d()), suoVar), this.mContext);
    }

    private JSONObject c(JSONObject jSONObject, suo suoVar) {
        if (jSONObject == null) {
            return null;
        }
        stq.d(d, "createDataStr headerStr : " + jSONObject.toString(), true);
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("header", jSONObject);
            if (!swe.b((CharSequence) suoVar.e, true)) {
                jSONObject2.put("cplc", suoVar.e);
            }
            if (TextUtils.isEmpty(suoVar.a())) {
                jSONObject2.put("flag", "21");
            } else {
                jSONObject2.put("flag", suoVar.a());
            }
            if (!swe.b((CharSequence) suoVar.e(), true)) {
                jSONObject2.put("queryFlag", suoVar.e());
            }
            if (!swe.b((CharSequence) suoVar.b(), true)) {
                jSONObject2.put("reserved", suoVar.b());
            }
            return jSONObject2;
        } catch (JSONException unused) {
            stq.e(d, "createDataStr, params invalid.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.impl.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public sud readErrorResponse(int i, String str) {
        sud sudVar = new sud();
        sudVar.g = i;
        sudVar.c(str);
        return sudVar;
    }

    private sue e(JSONObject jSONObject) {
        try {
            sue sueVar = new sue();
            sueVar.s(swd.c(jSONObject, HwPayConstant.KEY_USER_ID));
            sueVar.f(swd.c(jSONObject, "cplc"));
            sueVar.e(swd.c(jSONObject, "aid"));
            sueVar.p(swd.c(jSONObject, "status"));
            sueVar.h(swd.c(jSONObject, "dpanid"));
            sueVar.t(swd.c(jSONObject, "source"));
            sueVar.d(swd.c(jSONObject, "cardNumber"));
            sueVar.a(swd.c(jSONObject, "cardName"));
            sueVar.c(swd.d(jSONObject, "cardType"));
            sueVar.d(swd.d(jSONObject, "personalizedstatus"));
            sueVar.j(swd.c(jSONObject, "eidCode"));
            sueVar.l(swd.c(jSONObject, "panEnrollmentId"));
            sueVar.x(swd.c(jSONObject, "provisionedTokenId"));
            sueVar.g(swd.c(jSONObject, "metaDataModTime"));
            sueVar.r(swd.c(jSONObject, "lastModified"));
            sueVar.q(swd.c(jSONObject, "terminal"));
            sueVar.i(swd.c(jSONObject, "issuerid"));
            sueVar.b(swd.d(jSONObject, "tsp"));
            sueVar.e(swd.d(jSONObject, "tokenStatus"));
            if (jSONObject.has("reserved")) {
                try {
                    sueVar.m(swd.c(jSONObject, "reserved"));
                    JSONObject jSONObject2 = new JSONObject(swd.c(jSONObject, "reserved"));
                    sueVar.h(swd.c(jSONObject2, "dpanid"));
                    sueVar.o(swd.c(jSONObject2, "productId"));
                    sueVar.k(swd.c(jSONObject2, "tokenRefId"));
                    sueVar.c(swd.c(jSONObject2, "appletVersion"));
                    JSONArray jSONArray = jSONObject2.getJSONArray("operation");
                    if (jSONArray != null) {
                        sueVar.n(jSONArray.getString(jSONArray.length() - 1));
                    }
                } catch (JSONException unused) {
                    stq.e(d, "createCardItemFromJson JSONException : reserved fail ", true);
                }
            }
            String c = swd.c(jSONObject, "balance");
            if (TextUtils.isEmpty(c)) {
                return sueVar;
            }
            try {
                sueVar.b(String.valueOf(Float.parseFloat(c) / 100.0f));
                return sueVar;
            } catch (NumberFormatException unused2) {
                stq.e(d, "createCardItemFromJson JSONException : NumberFormatException", true);
                return sueVar;
            }
        } catch (JSONException unused3) {
            stq.e(d, "createCardItemFromJson JSONException : ", true);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.impl.HttpConnTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public sud readSuccessResponse(int i, String str, JSONObject jSONObject) {
        sud sudVar = new sud();
        sudVar.g = i;
        if (i == 0 && jSONObject != null) {
            try {
                sudVar.b(swd.c(jSONObject, "devStatus"));
                sudVar.a(swd.d(jSONObject, "count"));
                sudVar.d(swd.c(jSONObject, "createtime"));
                if (jSONObject.has("inCloudCount")) {
                    sudVar.d(swd.d(jSONObject, "inCloudCount"));
                }
                sue sueVar = null;
                JSONArray jSONArray = jSONObject.has("inCloudData") ? jSONObject.getJSONArray("inCloudData") : null;
                if (jSONArray != null) {
                    sudVar.a(new ArrayList());
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        sue e = e(jSONArray.getJSONObject(i2));
                        if (e != null) {
                            sudVar.c().add(e);
                        }
                    }
                }
                JSONArray jSONArray2 = jSONObject.has("data") ? jSONObject.getJSONArray("data") : null;
                if (jSONArray2 != null) {
                    sudVar.b(new ArrayList());
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                        String str2 = d;
                        StringBuilder sb = new StringBuilder();
                        sb.append(" nfc.get.list.card ");
                        sb.append(jSONObject2 != null ? jSONObject2.toString() : "");
                        stq.c(str2, sb.toString());
                        if (jSONObject2 != null) {
                            sueVar = e(jSONObject2);
                        }
                        if (sueVar != null) {
                            sudVar.e().add(sueVar);
                        }
                    }
                }
            } catch (JSONException unused) {
                stq.e(d, "readSuccessResponse, JSONException : ", true);
                sudVar.g = -99;
            }
        }
        return sudVar;
    }
}
