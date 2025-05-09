package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.OperationUtilsApi;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes8.dex */
public class gdy {
    public static String b(String str, ceb cebVar) {
        LogUtil.c("Suggestion_RecommendActivityHelper", "getOperationActivityUrl activityUrl = ", str);
        if (cebVar.p() == 4) {
            LogUtil.a("Suggestion_RecommendActivityHelper", "Enter ACTIVITY_TEMPLATETYPE_NO_DETAIL");
            return cebVar.a();
        }
        return d(str, cebVar);
    }

    private static String d(String str, ceb cebVar) {
        String str2;
        String str3 = str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath();
        if (cebVar.h() == -1) {
            str2 = str3 + "activityShare";
        } else if (a(cebVar)) {
            str2 = str3 + "rankList";
        } else {
            str2 = str3 + "calendar";
        }
        if (cebVar.p() == 5) {
            str2 = str2 + "New";
        }
        String str4 = str2 + ".html?activityId=" + cebVar.c();
        if (cebVar.h() == -1) {
            return str4;
        }
        return str4 + "&activityName=" + cebVar.b();
    }

    public static boolean d(ceb cebVar) {
        return cebVar.f() == 400;
    }

    private static boolean a(ceb cebVar) {
        OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
        if (operationUtilsApi == null) {
            return false;
        }
        return operationUtilsApi.isChallengeActivity(cebVar.f());
    }

    public static int b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.h("Suggestion_RecommendActivityHelper", "curDate or startDate or endDate is empty");
            return 2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str3);
            Date parse3 = simpleDateFormat.parse(str2);
            if (parse.getTime() < parse3.getTime()) {
                return 0;
            }
            if (parse3.getTime() > parse.getTime() || parse.getTime() > parse2.getTime()) {
                if (parse2.getTime() < parse.getTime()) {
                    return -1;
                }
                LogUtil.h("Suggestion_RecommendActivityHelper", "current time is invalid , use default status:  ", 1);
            }
            return 1;
        } catch (ParseException e) {
            LogUtil.b("Suggestion_RecommendActivityHelper", "getGMTtoLocal(),Exception e = ", LogAnonymous.b((Throwable) e));
            return 2;
        }
    }

    public static boolean c(String str) {
        LogUtil.a("Suggestion_RecommendActivityHelper", "judgeVersionSupport enter!");
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        AdapterHealthMgrApi adapterHealthMgrApi = (AdapterHealthMgrApi) Services.a("HwAdpaterHealthMgr", AdapterHealthMgrApi.class);
        if (adapterHealthMgrApi == null) {
            LogUtil.b("Suggestion_RecommendActivityHelper", "getAppData : adapterHealthMgrApi is null.");
            return false;
        }
        Map<String, String> appData = adapterHealthMgrApi.getAppData(new String[]{"getAppInfo"});
        LogUtil.c("Suggestion_RecommendActivityHelper", "mMapInfo ", appData.toString());
        String str2 = appData.get("version");
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        LogUtil.c("Suggestion_RecommendActivityHelper", "version from app = ", str2);
        LogUtil.c("Suggestion_RecommendActivityHelper", "version from cloud = ", str);
        int indexOf = str2.indexOf(Constants.LINK);
        LogUtil.c("Suggestion_RecommendActivityHelper", "index:  ", Integer.valueOf(indexOf));
        if (indexOf >= 0) {
            str2 = str2.substring(0, indexOf);
        }
        while (!"".equals(str2)) {
            int e = nsn.e(str2.substring(0, str2.contains(".") ? str2.indexOf(".") : str2.length()));
            if (!str2.contains(".")) {
                str2 = "";
            } else {
                str2 = str2.substring(str2.indexOf(".") + 1);
            }
            int e2 = nsn.e(str.substring(0, str.contains(".") ? str.indexOf(".") : str.length()));
            str = str.substring(str.indexOf(".") + 1);
            if (e > e2) {
                return true;
            }
            if (e < e2) {
                return false;
            }
        }
        return true;
    }

    public static void d(Context context, boolean z) {
        SharedPreferenceManager.e(context, Integer.toString(10011), "SOCIAL_FRAGMENT_IS_NEED_UPDATE_ACTIVITY", z ? "TRUE_VALUE" : "FALSE_VALUE", new StorageParams());
    }
}
