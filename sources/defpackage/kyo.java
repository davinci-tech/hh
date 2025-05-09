package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kyo {
    public static kxl c(String str, boolean z) {
        kxl kxlVar;
        kxl d;
        kxl kxlVar2 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int parseInt = Integer.parseInt(jSONObject.getString("status"));
            ReleaseLogUtil.e("R_Weight_BuildNewVersionInfoXmlUtil", " check new version status= ", Integer.valueOf(parseInt));
            kxlVar = new kxl();
            try {
                if (z) {
                    d = a(jSONObject, parseInt);
                } else {
                    d = d(jSONObject, parseInt);
                }
                kxlVar2 = d;
                c(kxlVar2, jSONObject);
                if (kxlVar2 != null || parseInt != 0) {
                    return kxlVar2;
                }
                kxlVar = new kxl();
                kxlVar.a(1);
                return kxlVar;
            } catch (RuntimeException unused) {
                LogUtil.b("BuildNewVersionInfoXmlUtil", "buildNewVersionInfoXml RuntimeException");
                return kxlVar;
            } catch (Exception unused2) {
                LogUtil.b("BuildNewVersionInfoXmlUtil", "buildNewVersionInfoXml Exception");
                return kxlVar;
            }
        } catch (RuntimeException unused3) {
            kxlVar = kxlVar2;
        } catch (Exception unused4) {
            kxlVar = kxlVar2;
        }
    }

    private static kxl a(JSONObject jSONObject, int i) throws JSONException {
        JSONArray jSONArray;
        if (i == 1) {
            kxl kxlVar = new kxl();
            kxlVar.a(i);
            return kxlVar;
        }
        if (!jSONObject.has("versionPackageCheckResults") || (jSONArray = jSONObject.getJSONArray("versionPackageCheckResults")) == null || jSONArray.length() != 1) {
            return null;
        }
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        int parseInt = Integer.parseInt(jSONObject2.getString("status"));
        LogUtil.a("BuildNewVersionInfoXmlUtil", "getVersionEnterpriseInfo check new version status= ", Integer.valueOf(parseInt));
        return d(jSONObject2, parseInt);
    }

    private static kxl d(JSONObject jSONObject, int i) throws JSONException {
        JSONArray jSONArray;
        if (i == 1) {
            kxl kxlVar = new kxl();
            kxlVar.a(i);
            return kxlVar;
        }
        if (!jSONObject.has("components") || (jSONArray = jSONObject.getJSONArray("components")) == null || jSONArray.length() != 1) {
            return null;
        }
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        LogUtil.a("BuildNewVersionInfoXmlUtil", "getComponentsInfo componentjson is ", jSONObject2.toString());
        kxl kxlVar2 = new kxl();
        kxlVar2.a(i);
        b(kxlVar2, jSONObject2);
        if (!jSONObject2.has("ruleAttr")) {
            return kxlVar2;
        }
        String string = jSONObject2.getString("ruleAttr");
        LogUtil.a("BuildNewVersionInfoXmlUtil", "getComponentsInfo ruleAttr = ", string);
        e(kxlVar2, string);
        return kxlVar2;
    }

    private static void c(kxl kxlVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        if (jSONObject.has(NetworkService.Constants.CONFIG_SERVICE) && (jSONObject2 = jSONObject.getJSONObject(NetworkService.Constants.CONFIG_SERVICE)) != null && jSONObject2.has("forceRemind") && kxlVar != null) {
            kxlVar.b(Integer.parseInt(jSONObject2.getString("forceRemind")));
        }
        if (!jSONObject.has("autoPollingCycle") || kxlVar == null) {
            return;
        }
        kxlVar.e(Integer.parseInt(jSONObject.getString("autoPollingCycle")));
        LogUtil.a("BuildNewVersionInfoXmlUtil", "buildNewVersionInfoXml autoPollingCycle=", Integer.valueOf(kxlVar.b()));
    }

    private static void b(kxl kxlVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("name")) {
            kxlVar.i(jSONObject.getString("name"));
        }
        if (jSONObject.has("version")) {
            kxlVar.r(jSONObject.getString("version"));
        }
        if (jSONObject.has("versionID")) {
            kxlVar.t(jSONObject.getString("versionID"));
        }
        if (jSONObject.has("description")) {
            kxlVar.e(jSONObject.getString("description"));
        }
        if (jSONObject.has("url")) {
            kxlVar.q(jSONObject.getString("url"));
        }
        if (jSONObject.has("createTime")) {
            kxlVar.d(jSONObject.getString("createTime"));
        }
        if (jSONObject.has("size")) {
            kxlVar.g(jSONObject.getString("size"));
        }
        if (jSONObject.has("componentID")) {
            try {
                kxlVar.c(Integer.parseInt(jSONObject.getString("componentID")));
            } catch (NumberFormatException unused) {
                LogUtil.a("BuildNewVersionInfoXmlUtil", "handleComponentJson NumberFormatException");
            }
        }
    }

    private static void e(kxl kxlVar, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BuildNewVersionInfoXmlUtil", "handleRuleAttr rules is empty");
            return;
        }
        try {
            kxo kxoVar = new kxo(BaseApplication.getContext(), str);
            LogUtil.a("BuildNewVersionInfoXmlUtil", "handleRuleAttr minAppCode is ", Integer.valueOf(kxoVar.e()), "appForcedUpdate is ", kxoVar.a(), "forcedUpdate is ", kxoVar.d());
            if (kxoVar.e() != 0) {
                kxlVar.d(kxoVar.e());
            }
            if (!TextUtils.isEmpty(kxoVar.a()) && "true".equals(kxoVar.a())) {
                kxlVar.c(kxoVar.a());
            }
            if (TextUtils.isEmpty(kxoVar.d()) || !"true".equals(kxoVar.d())) {
                return;
            }
            kxlVar.j(kxoVar.d());
        } catch (NumberFormatException | JSONException unused) {
            LogUtil.b("BuildNewVersionInfoXmlUtil", "handleRuleAttr NumberFormatException");
        }
    }
}
