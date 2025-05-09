package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kah {
    public static String d() {
        if (!kae.d()) {
            LogUtil.h("ContactExtraDataUtils", "has no hicall permission.");
            return "";
        }
        return d(kaq.b(BaseApplication.getContext()));
    }

    public static String b() {
        return d(kap.e());
    }

    public static String c() {
        if (!kae.a()) {
            LogUtil.h("ContactExtraDataUtils", "not supported contacts config.");
            return "";
        }
        if (!kae.c()) {
            LogUtil.h("ContactExtraDataUtils", "have no contacts permissions.");
            return "";
        }
        return d(kag.a(BaseApplication.getContext()));
    }

    private static String d(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            LogUtil.h("ContactExtraDataUtils", "toJsonString: jsonObject is null or empty.");
            return "";
        }
        return jSONObject.toString();
    }

    private static String d(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h("ContactExtraDataUtils", "toJsonString: jsonArray is null or empty.");
            return "";
        }
        return jSONArray.toString();
    }
}
