package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.wear.oversea.httputil.HttpConnTask;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sub extends HttpConnTask<stz, stw> {
    private static final Pattern c = Pattern.compile("(\\\\+/)");
    private static final String d = "QueryOverSeaMangerTask";
    private String b;

    public sub(Context context, String str) {
        super(context, str);
        this.b = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(stw stwVar) {
        if (stwVar == null || swe.b((CharSequence) stwVar.h(), true) || swe.b((CharSequence) stwVar.g(), true) || stwVar.e().contains("Error")) {
            stq.c(d, "QueryOverSeaMangerTask prepareRequestStr, params invalid.", false);
            return " ";
        }
        String h = stwVar.h();
        this.b = h;
        JSONObject e = e(swh.b(h, "/api/v1/client/wearQueryHuaweiPayUsability", stwVar.d()), stwVar);
        stq.c(d, "QueryOverSeaMangerTask prepareRequestStr, commander= /api/v1/client/wearQueryHuaweiPayUsability reportRequestMessageJson= " + e);
        return e != null ? e(e.toString()) : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public stz readErrorResponse(int i) {
        String str = d;
        stq.c(str, " readErrorResponse  ", false);
        stz stzVar = new stz();
        stzVar.g = i;
        stq.b(str, "QueryOverSeaMangerTask readErrorResponse, returnCode = " + stzVar.g);
        suk.d(str + " readErrorResponse, commander= /api/v1/client/wearQueryHuaweiPayUsability,QueryOverSeaMangerTask, returnCode = " + stzVar.g);
        return stzVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public stz readSuccessResponse(String str) {
        String str2 = d;
        stq.c(str2, " readSuccessResponse  ", false);
        stz stzVar = new stz();
        c(stzVar, str);
        stq.d(str2, " readSuccessResponse, commander= /api/v1/client/wearQueryHuaweiPayUsability,readSuccessResponse, returnCode = " + stzVar.g + "response = " + str, false);
        return stzVar;
    }

    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    public void onReportEvent(String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "/api/v1/client/wearQueryHuaweiPayUsability";
        }
        if (TextUtils.isEmpty(str5)) {
            str5 = this.b;
        }
        String str7 = "commander:" + str3 + "; srcTranID:" + str5 + ";  ; result:" + str + "; returnCode:" + str2 + "; ";
        stq.d(d, getSubProcessPrefix() + "HttpConnTask onReportEvent, action:" + str4 + ", Report data: " + str7, true);
        StringBuilder sb = new StringBuilder();
        sb.append(str7);
        sb.append(" exception:");
        sb.append(str6);
        suk.e(str4, sb.toString());
    }

    private JSONObject e(JSONObject jSONObject, stw stwVar) {
        JSONObject jSONObject2 = null;
        if (jSONObject == null) {
            stq.e(d, " createDataStr, headerObject is null.", false);
            return null;
        }
        try {
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("header", jSONObject);
                jSONObject3.put(FaqConstants.FAQ_ROMVERSION, stwVar.e());
                jSONObject3.put("wearClientVersion", stwVar.i());
                jSONObject3.put("terminal", stwVar.b());
                jSONObject3.put("location", stwVar.a());
                return jSONObject3;
            } catch (JSONException unused) {
                jSONObject2 = jSONObject3;
                stq.e(d, " createDataStr parse json error:JSONException", false);
                return jSONObject2;
            }
        } catch (JSONException unused2) {
        }
    }

    private void c(stz stzVar, String str) {
        if (swe.b((CharSequence) str, true)) {
            stq.c(d, "responseStr is null", false);
            stzVar.g = 8;
            return;
        }
        try {
            String str2 = d;
            stq.d(str2, " resolveResponse  " + str, true);
            JSONObject jSONObject = new JSONObject(str);
            String c2 = swd.c(jSONObject, "returnCode");
            String c3 = swd.c(jSONObject, "errorCode");
            swd.c(jSONObject, "errorMsg");
            if (c3 != null) {
                stq.c(str2, ", " + (" code:" + c3), false);
                stzVar.g = Integer.parseInt(c3);
                return;
            }
            if (c2 == null) {
                stq.e(str2, "returnCode is invalid.", false);
                stzVar.g = 8;
                return;
            }
            stzVar.g = Integer.parseInt(c2);
            stq.c(str2, " readSuccessResponse  " + stzVar.g, false);
            String c4 = swd.c(jSONObject, "returnDesc");
            if (stzVar.g != 0) {
                stzVar.g = 8;
                stq.e(str2, " returnDesc : returnCode " + (" code:" + stzVar.g), false);
                return;
            }
            stq.c(str2, " returnDesc  " + c4, false);
            if (jSONObject.has("returnDesc")) {
                stzVar.e(c4);
            }
            String c5 = swd.c(jSONObject, "result");
            String str3 = " result:" + stzVar.f17231a;
            stq.c(str2, " result  " + str3, false);
            if (jSONObject.has("result")) {
                stzVar.a(Integer.parseInt(c5));
                stzVar.g = Integer.parseInt(c5);
                stq.b(str2, "readSuccessResponse resultCode =" + str3);
            }
            if (stzVar.g != 0) {
                stq.c(str2, ", returnCode : " + (" code:" + stzVar.g), false);
            }
        } catch (NoSuchMethodError unused) {
            stq.e(d, ", NoSuchMethodError : ", false);
            stzVar.g = 8;
        } catch (NumberFormatException unused2) {
            stq.e(d, ", NumberFormatException : ", false);
            stzVar.g = 8;
        } catch (JSONException unused3) {
            stq.e(d, ", JSONException : ", false);
            stzVar.g = 8;
        }
    }

    private static String e(String str) {
        return c.matcher(str).replaceAll("/");
    }
}
