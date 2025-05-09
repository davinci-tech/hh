package defpackage;

import android.content.Context;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.wear.oversea.httputil.HttpConnTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class swj extends HttpConnTask<suh, sut> {
    private static final String c = "QuerySupportedCardListByTerminalTask";
    private String b;
    private String d;

    public swj(Context context, String str) {
        super(context, str);
        this.b = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String prepareRequestStr(sut sutVar) {
        if (sutVar != null) {
            if (!swe.b((CharSequence) sutVar.h(), true) && !swe.b((CharSequence) sutVar.g(), true) && e(sutVar)) {
                this.b = sutVar.h();
                JSONObject e = e(swh.b(sutVar.h(), "query.rule.issuer", swe.b((CharSequence) sutVar.b(), true) ? sutVar.d() : true), sutVar);
                JSONObject c2 = c(sutVar);
                if (!stt.e()) {
                    stq.b(c, "QuerySupportedCardListByTerminalTask prepareRequestStr, commander= query.rule.issuer reportRequestMessageJson= " + c2);
                }
                return swd.e(sutVar.g(), sutVar.c(), e, this.mContext);
            }
        }
        stq.b(c, "QuerySupportedCardListByTerminalTask prepareRequestStr, params invalid.");
        return null;
    }

    private JSONObject c(sut sutVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("srcTransactionID", sutVar.h());
            jSONObject.put("timestamp", sutVar.m());
            jSONObject.put(FaqConstants.FAQ_ROMVERSION, sutVar.f());
            jSONObject.put("clientVersion", sutVar.o());
            jSONObject.put("terminal", sutVar.k());
            jSONObject.put("flag", sutVar.j());
            if (!swe.b((CharSequence) sutVar.b(), true)) {
                jSONObject.put("appVersion", sutVar.b());
            }
            jSONObject.put("countryCode", sutVar.a());
            jSONObject.put("appId", sutVar.e());
            return jSONObject;
        } catch (JSONException unused) {
            stq.e(c, "QuerySupportedCardListByTerminalTask reportRequestMessage parse json error", true);
            return null;
        }
    }

    private JSONObject e(JSONObject jSONObject, sut sutVar) {
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("header", jSONObject);
            jSONObject2.put("timestamp", sutVar.m());
            jSONObject2.put(FaqConstants.FAQ_ROMVERSION, sutVar.f());
            jSONObject2.put("clientVersion", sutVar.o());
            jSONObject2.put("terminal", sutVar.k());
            jSONObject2.put("sn", sutVar.i());
            jSONObject2.put("flag", sutVar.j());
            if (!swe.b((CharSequence) sutVar.b(), true)) {
                jSONObject2.put("appVersion", sutVar.b());
            }
            jSONObject2.put("countryCode", sutVar.a());
            jSONObject2.put("appId", sutVar.e());
            return jSONObject2;
        } catch (JSONException unused) {
            stq.e(c, "QuerySupportedCardListByTerminalTask createDataStr parse json error", true);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public suh readErrorResponse(int i) {
        suh suhVar = new suh();
        suhVar.g = i;
        suhVar.c(i + "");
        stq.b(c, "QuerySupportedCardListByTerminalTask readErrorResponse, srcTransactionID= " + this.b + " errorCode= " + i);
        suk.d("QuerySupportedCardListByTerminalTask readErrorResponse, commander= query.rule.issuer errorCode= " + i + " errorMessage= " + i);
        return suhVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public suh readSuccessResponse(String str) {
        JSONObject jSONObject;
        String str2;
        int d;
        String c2;
        String c3;
        String c4;
        String c5;
        int i;
        int i2 = -99;
        if (str == null) {
            onReportEvent("Error with unknown", String.valueOf(-99), "query.rule.issuer", this.receiveMsg, this.b, "");
            this.d = "21";
            return b(-99, null, null);
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            d = swd.d(jSONObject2, "keyIndex");
            c2 = swd.c(jSONObject2, "errorCode");
            c3 = swd.c(jSONObject2, "errorMsg");
            c4 = swd.c(jSONObject2, TrackConstants$Opers.RESPONSE);
        } catch (NumberFormatException e) {
            e = e;
            jSONObject = null;
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        if (c2 != null) {
            stq.a(c, getSubProcessPrefix() + "handleResponse, return code : " + c2, false);
            int parseInt = Integer.parseInt(c2);
            onReportEvent("handleResponse, return code : " + c2 + ", return msg : " + c3, c2, "query.rule.issuer", this.receiveMsg, this.b, "");
            this.d = "23";
            return b(parseInt, c3, null);
        }
        if (-1 == d && !swe.b((CharSequence) c4, true)) {
            String str3 = c;
            stq.d(str3, getSubProcessPrefix() + "handleResponse, responseDataStr : " + c4, true);
            jSONObject = new JSONObject(c4);
            try {
                try {
                    c5 = swd.c(jSONObject, "returnCode");
                } catch (JSONException e3) {
                    e = e3;
                    stq.e(c, getSubProcessPrefix() + "readSuccessResponse, JSONException : ", true);
                    str2 = ((String) null) + "readSuccessResponse, JSONException error ";
                    this.d = "22";
                    onReportEvent("readSuccessResponse, JSONException error ", String.valueOf(-99), "query.rule.issuer", this.receiveMsg, this.b, !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "");
                    return b(i2, str2, jSONObject);
                }
            } catch (NumberFormatException e4) {
                e = e4;
                stq.e(c, getSubProcessPrefix() + "readSuccessResponse, NumberFormatException : ", true);
                str2 = ((String) null) + "readSuccessResponse, NumberFormatException error ";
                this.d = "22";
                onReportEvent("readSuccessResponse, NumberFormatException error ", String.valueOf(-99), "query.rule.issuer", this.receiveMsg, this.b, !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "");
                return b(i2, str2, jSONObject);
            }
            if (c5 == null) {
                stq.d(str3, getSubProcessPrefix() + "handleResponse, returnCode is invalid.", false);
                onReportEvent("handleResponse, returnCode is invalid.", String.valueOf(-99), "query.rule.issuer", this.receiveMsg, this.b, "");
                this.d = "22";
                return b(-99, "handleResponse, returnCode is invalid.", null);
            }
            if (d(c5)) {
                i = Integer.parseInt(c5);
            } else if (swe.b(c5)) {
                if (c5.startsWith("0X") || c5.startsWith("0x")) {
                    c5 = c5.substring(2, c5.length());
                }
                i = Integer.parseInt(c5, 16);
            } else {
                if (c5.startsWith(ExifInterface.GPS_DIRECTION_TRUE)) {
                    i = Integer.parseInt(c5.substring(1, c5.length()));
                }
                i = -98;
            }
            this.d = "24";
            str2 = swd.c(jSONObject, "returnDesc");
            onReportEvent(str2, String.valueOf(i), "query.rule.issuer", this.receiveMsg, this.b, "");
            i2 = i;
            return b(i2, str2, jSONObject);
        }
        stq.d(c, getSubProcessPrefix() + "handleResponse, unexpected error from server.", false);
        onReportEvent("handleResponse, unexpected error from server.", String.valueOf(-99), "query.rule.issuer", this.receiveMsg, this.b, "");
        this.d = "22";
        return b(-99, "handleResponse, unexpected error from server.", null);
    }

    public boolean d(String str) {
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
        stq.c(c, getSubProcessPrefix() + "HttpConnTask onReportEvent, action:" + str4 + ", Report data: " + str7, false);
        StringBuilder sb = new StringBuilder();
        sb.append(str7);
        sb.append(" exception:");
        sb.append(str6);
        suk.e(str4, sb.toString());
    }

    private suh b(int i, String str, JSONObject jSONObject) {
        JSONObject jSONObject2;
        stq.b(c, "QuerySupportedCardListByTerminalTask readSuccessResponse, srcTransactionID= " + this.b + " returnCode= " + i);
        StringBuilder sb = new StringBuilder();
        suh suhVar = new suh();
        suhVar.g = i;
        suhVar.c = null;
        suhVar.b = null;
        if (jSONObject != null) {
            try {
                if (jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null) {
                    String string = jSONObject2.getString("srcTranID");
                    sb.append("srcTranId=");
                    sb.append(string);
                }
            } catch (JSONException unused) {
                stq.e(c, "QuerySupportedCardListByTerminalTask readSuccessResponse, JSONException");
            }
        }
        if (i == 0) {
            try {
                String c2 = swd.c(jSONObject, "issuers");
                String c3 = swd.c(jSONObject, "issuersAndFlag");
                suhVar.c = c2;
                suhVar.b = c3;
                sb.append(" issuers=");
                sb.append(swd.c(jSONObject, "issuers"));
                sb.append(" issuersAndFlag=");
                sb.append(swd.c(jSONObject, "issuersAndFlag"));
            } catch (JSONException unused2) {
                stq.e(c, "readSuccessResponse, JSONException : ", true);
                suhVar.g = -99;
            }
        }
        if (!stt.e()) {
            stq.b(c, "QuerySupportedCardListByTerminalTask readSuccessResponse, commander= query.rule.issuer returnCode= " + i + " returnDesc= " + str + " dataObject: " + sb.toString());
        }
        return suhVar;
    }

    private boolean e(sut sutVar) {
        if (!TextUtils.isEmpty(sutVar.k())) {
            return true;
        }
        stq.b(c, "isReqValid terminal is empty");
        return false;
    }
}
