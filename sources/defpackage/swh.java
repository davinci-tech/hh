package defpackage;

import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wear.oversea.httputil.BaseHttpParams;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class swh {
    public static final String e = "JSONHelper";

    public static JSONObject b(String str, String str2, boolean z) {
        if (str2 == null) {
            return null;
        }
        String str3 = e;
        stq.c(str3, "createHeaderStr commandStr : " + str2 + " srcTransID: " + str, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("srcTranID", str);
            if (z) {
                jSONObject.put("version", "1.0");
                int k = OverSeaMangerUtil.c(ssz.e()).e().k();
                stq.c(str3, "getServerTokenType = " + k, false);
                if (k == 1) {
                    b(jSONObject, str2);
                } else if (k == 2) {
                    c(jSONObject, str2);
                } else {
                    stq.c(str3, "The value of AT or ST is null", false);
                }
            } else {
                jSONObject.put("version", "1.0");
            }
            jSONObject.put("ts", System.currentTimeMillis() / 1000);
            jSONObject.put("commander", str2);
            Locale locale = Locale.getDefault();
            jSONObject.put("language", locale.getLanguage() + Constants.LINK + locale.getCountry());
            return jSONObject;
        } catch (JSONException unused) {
            stq.e(e, "createHeaderObject, params invalid.", false);
            return null;
        }
    }

    public static void b(JSONObject jSONObject, String str) {
        JSONObject b = swd.b(str);
        try {
            if (b == null) {
                jSONObject.put("serviceTokenAuth", BaseHttpParams.AUTH_SERVICE_TOKEN_ERROR);
            } else {
                jSONObject.put("serviceTokenAuth", b);
            }
        } catch (JSONException unused) {
            stq.e(e, "createHeaderObject, params invalid.", false);
        }
    }

    public static void c(JSONObject jSONObject, String str) {
        JSONObject a2 = swd.a(str);
        try {
            if (a2 == null) {
                jSONObject.put("accessTokenAuth", BaseHttpParams.AUTH_SERVICE_TOKEN_ERROR);
            } else {
                jSONObject.put("accessTokenAuth", a2);
            }
        } catch (JSONException unused) {
            stq.e(e, "createHeaderObject, params invalid.", false);
        }
    }
}
