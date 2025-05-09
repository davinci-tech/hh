package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.httputil.HttpConnTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class svj extends HttpConnTask<suc, suu> {

    /* renamed from: a, reason: collision with root package name */
    private String f17249a;
    private String d;

    public svj(Context context, String str) {
        super(context, str);
        this.d = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(suu suuVar) {
        if (suuVar == null || swe.b((CharSequence) suuVar.b(), true) || swe.b((CharSequence) suuVar.e(), true)) {
            stq.d("QuerySupportBusinessTask", " prepareRequestStr, params invalid.", false);
            suk.d("QuerySupportBusinessTask prepareRequestStr, commander= nfc.get.support.business ,prepareRequestStr, params invalid. ");
            return null;
        }
        this.d = suuVar.h();
        return swd.e(suuVar.g(), suuVar.c(), e(swh.b(suuVar.h(), "nfc.get.support.business", suuVar.d()), suuVar), this.mContext);
    }

    private JSONObject e(JSONObject jSONObject, suu suuVar) {
        JSONObject jSONObject2 = null;
        if (jSONObject == null) {
            stq.c("QuerySupportBusinessTask", " createDataStr, headerObject is null.", false);
            return null;
        }
        try {
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("header", jSONObject);
                if (!swe.b((CharSequence) suuVar.b(), true)) {
                    jSONObject3.put("location", suuVar.b());
                }
                if (!swe.b((CharSequence) suuVar.e(), true)) {
                    jSONObject3.put("terminal", suuVar.e());
                }
                return jSONObject3;
            } catch (JSONException unused) {
                jSONObject2 = jSONObject3;
                stq.c("QuerySupportBusinessTask", " createDataStr parse json error", false);
                return jSONObject2;
            }
        } catch (JSONException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public suc readErrorResponse(int i) {
        suc sucVar = new suc();
        sucVar.g = i;
        if (-1 == i) {
            sucVar.g = -1;
        } else if (-3 == i) {
            sucVar.g = 1;
        } else if (-2 == i) {
            sucVar.g = -2;
        }
        stq.c("QuerySupportBusinessTask", "querySupportBusiness, returnCode = " + sucVar.g, false);
        suk.d("QuerySupportBusinessTask readErrorResponse, commander= nfc.get.support.business,querySupportBusiness, returnCode = " + sucVar.g);
        return sucVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x024d  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r14v7, types: [boolean] */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.suc readSuccessResponse(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 611
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.svj.readSuccessResponse(java.lang.String):suc");
    }

    private suc c(int i, String str, JSONObject jSONObject) {
        JSONObject jSONObject2;
        stq.b("QuerySupportBusinessTask", "QuerySupportedCardListByTerminalTask readSuccessResponse, srcTransactionID= " + this.d + " returnCode= " + i);
        StringBuilder sb = new StringBuilder();
        suc sucVar = new suc();
        sucVar.g = i;
        sucVar.d((String) null);
        if (jSONObject != null) {
            try {
                if (jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null) {
                    String string = jSONObject2.getString("srcTranID");
                    sb.append("srcTranId=");
                    sb.append(string);
                }
            } catch (JSONException unused) {
                stq.b("QuerySupportBusinessTask", "QuerySupportedCardListByTerminalTask readSuccessResponse, JSONException");
            }
        }
        if (i == 0) {
            try {
                if (!swe.b((CharSequence) swd.c(jSONObject, "serviceList"), false)) {
                    sucVar.d(swd.c(jSONObject, "serviceList"));
                }
            } catch (JSONException unused2) {
                stq.c("QuerySupportBusinessTask", "readSuccessResponse, JSONException : ", true);
                sucVar.g = -99;
            }
        }
        if (!stt.e()) {
            stq.b("QuerySupportBusinessTask", "QuerySupportBusinessTask readSuccessResponse, commander= nfc.get.support.business returnCode= " + i + " returnDesc= " + str + " dataObject: " + sb.toString());
        }
        return sucVar;
    }

    public boolean c(String str) {
        if (str != null && !"".equals(str)) {
            try {
                Long valueOf = Long.valueOf(Long.parseLong(str));
                if (valueOf.longValue() <= 2147483647L) {
                    if (valueOf.longValue() >= -2147483648L) {
                        return true;
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    public void onReportEvent(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = "commander:" + str3 + "; srcTranID:" + str5 + ";  ; result:" + str + "; returnCode:" + str2 + "; ";
        stq.c("QuerySupportBusinessTask", getSubProcessPrefix() + "HttpConnTask onReportEvent, action:" + str4 + ", Report data: " + str7, false);
        StringBuilder sb = new StringBuilder();
        sb.append(str7);
        sb.append(" exception:");
        sb.append(str6);
        suk.e(str4, sb.toString());
    }
}
