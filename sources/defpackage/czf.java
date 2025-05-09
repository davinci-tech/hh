package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class czf {
    public static dsw b(int i, String str, String str2) {
        return d(i, drd.d(str, str2, (String) null) + File.separator + "heart_rate_control_configuration" + File.separator + "heart_rate_control_course.json");
    }

    public static dsy e(int i, String str, String str2) {
        return e(i, drd.d(str, str2, (String) null) + File.separator + "heart_rate_control_configuration" + File.separator + "heart_rate_control_config.json");
    }

    public static dsw d(int i, String str) {
        LogUtil.a("HeartRateControlConfigParser", "loadHeartRateControlInfo");
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("HeartRateControlConfigParser", "loadHeartRateControlInfo tempPathList == null");
            return null;
        }
        if (!new File(c).exists()) {
            LogUtil.h("HeartRateControlConfigParser", "loadHeartRateControlInfo file not exists");
            return null;
        }
        JSONObject a2 = EzPluginManager.a(new File(str));
        if (a2 == null) {
            LogUtil.h("HeartRateControlConfigParser", "jsonObject is null");
            return null;
        }
        dsw dswVar = (dsw) new Gson().fromJson(a2.toString(), new TypeToken<dsw>() { // from class: czf.1
        }.getType());
        if (dswVar == null) {
            LogUtil.h("HeartRateControlConfigParser", "heartRateControlInfo is null");
            return dswVar;
        }
        czl.d(i, dswVar);
        return dswVar;
    }

    public static dsy e(int i, String str) {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("HeartRateControlConfigParser", "loadHeartRateControlInfo tempPathList == null");
            return null;
        }
        if (!new File(c).exists()) {
            LogUtil.h("HeartRateControlConfigParser", "loadHeartRateControlInfo file not exists");
            return null;
        }
        JSONObject a2 = EzPluginManager.a(new File(str));
        if (a2 == null) {
            return null;
        }
        dsy dsyVar = (dsy) new Gson().fromJson(a2.toString(), new TypeToken<dsy>() { // from class: czf.4
        }.getType());
        if (dsyVar == null) {
            LogUtil.h("HeartRateControlConfigParser", "speedControlConfigInfo is null");
            return dsyVar;
        }
        czl.d(i, dsyVar);
        return dsyVar;
    }
}
