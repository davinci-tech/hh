package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.report.bean.H5ResponseData;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mhs {
    public static H5ResponseData d(String str) {
        LogUtil.c("PLGACHIEVE_H5CalRule", "getH5Params str:", str);
        try {
            return (H5ResponseData) HiJsonUtil.e(str, H5ResponseData.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PLGACHIEVE_H5CalRule", "parseDataInfo trackMetaData is error!");
            return null;
        }
    }

    public static HashMap<String, String> c() {
        HashMap<String, String> hashMap = new HashMap<>(2);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("PLGACHIEVE_H5CalRule", "getHeaders huid null");
            return hashMap;
        }
        hashMap.put("x-huid", accountInfo);
        hashMap.put("x-version", CommonUtil.e(BaseApplication.getContext()));
        LogUtil.c("PLGACHIEVE_H5CalRule", "getH5Params mapHeaders:", hashMap.toString());
        return hashMap;
    }

    public static JSONObject c(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            long b = mht.b(i, true);
            long b2 = mht.b(i, false);
            jSONObject.put(Constants.START_DATE, e(b));
            jSONObject.put("endDate", e(b2));
            jSONObject.put("pageNo", "100");
            jSONObject.put(IAchieveDBMgr.PARAM_PAGE_SIZE, "100");
            jSONObject.put(CloudParamKeys.CLIENT_TYPE, eil.a());
            jSONObject.put("countryCode", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
            jSONObject.put("siteId", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
            jSONObject.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            jSONObject.put("token", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
            jSONObject.put("clientVersion", eaa.c(BaseApplication.getContext()));
            jSONObject.put("language", mtj.e(null));
            jSONObject.put("appId", BaseApplication.getAppPackage());
            jSONObject.put("appType", String.valueOf(AppTypeUtils.getAppType()));
            String deviceId = LoginInit.getInstance(BaseApplication.getContext()).getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = "clientnull";
            }
            jSONObject.put("deviceId", deviceId);
            jSONObject.put("deviceType", String.valueOf(CommonUtil.h(BaseApplication.getContext())));
            jSONObject.put("sysVersion", Build.VERSION.RELEASE);
            jSONObject.put("timeZone", HiDateUtil.d((String) null));
            jSONObject.put("ts", String.valueOf(System.currentTimeMillis()));
            jSONObject.put("operType", "1");
            jSONObject.put("infoType", "2");
            LogUtil.c("PLGACHIEVE_H5CalRule", "getH5Params JsonParams:", jSONObject.toString());
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_H5CalRule", "JSONException  ", e.getMessage());
        }
        return jSONObject;
    }

    public static String e(long j) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(j));
    }

    public static long a(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str);
        } catch (ParseException e) {
            LogUtil.b("PLGACHIEVE_H5CalRule", "dayToTime exception ", e);
            date = null;
        }
        if (date != null) {
            return date.getTime();
        }
        return 0L;
    }

    public static void b(HashMap<String, Integer> hashMap, String str) {
        if (hashMap == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (hashMap.containsKey(str)) {
            hashMap.put(str, Integer.valueOf(hashMap.get(str).intValue() + 1));
        } else {
            hashMap.put(str, 1);
        }
    }
}
