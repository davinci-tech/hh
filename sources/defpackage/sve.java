package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.impl.HttpConnTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sve extends HttpConnTask<suf, sur> {
    private static final String d = "GetPassTypeIdInfoTask";

    public sve(Context context, String str) {
        super(context, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.impl.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(sur surVar) {
        if (surVar == null || swe.b((CharSequence) surVar.h(), true) || swe.b((CharSequence) surVar.g(), true) || swe.b((CharSequence) surVar.b(), true)) {
            stq.e(d, "GetPassTypeIdInfoTask prepareRequestStr, invalid param");
            return null;
        }
        return swd.e(surVar.g(), surVar.c(), surVar.b(swh.b(surVar.h(), "query.pass.type", surVar.d())), this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.impl.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public suf readErrorResponse(int i, String str) {
        suf sufVar = new suf();
        sufVar.g = i;
        sufVar.c(str);
        return sufVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.impl.HttpConnTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public suf readSuccessResponse(int i, String str, JSONObject jSONObject) {
        suf sufVar = new suf();
        sufVar.g = i;
        sufVar.c(str);
        if (i != 0) {
            setErrorInfo(jSONObject, sufVar);
        } else if (jSONObject != null) {
            try {
                if (jSONObject.has("passTypeGroup")) {
                    sufVar.b(jSONObject.getString("passTypeGroup"));
                }
                if (jSONObject.has("enableNFC")) {
                    sufVar.c(jSONObject.getBoolean("enableNFC"));
                }
                if (jSONObject.has("reserve")) {
                    sufVar.a(jSONObject.getString("reserve"));
                }
            } catch (JSONException unused) {
                sufVar.g = -99;
                stq.e(d, "GetPassTypeIdInfoTask readSuccessResponse, JSONException");
            }
        } else {
            sufVar.g = -99;
        }
        return sufVar;
    }
}
