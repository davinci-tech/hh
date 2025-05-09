package defpackage;

import android.content.Context;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eaa {
    private static JSONObject i() {
        return null;
    }

    public static JSONObject a(List<Integer> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("marketingApiArgs", e(list));
            jSONObject.put("section16_9Series_01Args", c());
            jSONObject.put("section1_1List_01Args", g());
            jSONObject.put("section1_1Card_01Args", h());
            jSONObject.put("section4_5Card_01Args", i());
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "getExtraArgs JSONException");
        }
        return jSONObject;
    }

    private static JSONObject e(List<Integer> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CloudParamKeys.CLIENT_TYPE, d());
            jSONObject.put("clientVersion", c(BaseApplication.getContext()));
            jSONObject.put("language", mtj.e(null));
            jSONObject.put("countryCode", LoginInit.getInstance(BaseApplication.getContext()).getCountryCode(null));
            jSONObject.put(BleConstants.LIMIT, "500");
            jSONObject.put("page", 1);
            JSONArray jSONArray = new JSONArray();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("positionIds", jSONArray);
            String b = b();
            LogUtil.a("ArgsUtil", "marketing url: ", b);
            jSONObject.put("url", b);
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "getMarketingApiArgs JSONException");
        }
        return jSONObject;
    }

    private static JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pageStart", 0);
            jSONObject.put(IAchieveDBMgr.PARAM_PAGE_SIZE, 50);
            jSONObject.put("lang", e());
            jSONObject.put("my", 1);
            String a2 = a();
            LogUtil.a("ArgsUtil", "course url: ", a2);
            jSONObject.put("url", a2);
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "getSection1_1List_01Args JSONException");
        }
        return jSONObject;
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("courseCategory", 137);
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "getSection16_9Series_01Args JSONException");
        }
        return jSONObject;
    }

    private static JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("courseCategory", 0);
            jSONObject.put("pageNum", 0);
            jSONObject.put("lang", e());
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "getSection1_1Card_01Args JSONException");
        }
        return jSONObject;
    }

    private static String d() {
        return nsn.ae(BaseApplication.getContext()) ? "5" : SystemInfo.a() ? "1" : SystemInfo.d() ? SystemInfo.h() ? "2" : "1" : "3";
    }

    public static String c(Context context) {
        String e = CommonUtil.e(context);
        return e.contains(Constants.LINK) ? e.substring(0, e.indexOf(Constants.LINK)) : e;
    }

    private static String e() {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
        if (locale == null) {
            LogUtil.h("ArgsUtil", "acquireLanguagePostFix(), locale == null");
            return null;
        }
        return mtj.e(locale);
    }

    private static String a() {
        return GRSManager.a(com.huawei.haf.application.BaseApplication.e()).getUrl("sportSuggestUrl");
    }

    private static String b() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("marketingUrl");
    }
}
