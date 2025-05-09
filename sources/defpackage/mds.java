package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginachievement.connectivity.config.AUserProfile;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mds {
    public static int b() {
        return 20003;
    }

    public static boolean b(int i) {
        return (8 == i || i == 0 || 11 == i || 13 == i || i == 17) ? false : true;
    }

    public static boolean a() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static JSONObject a(AUserProfile aUserProfile, JSONObject jSONObject, int i) {
        return c(jSONObject, aUserProfile, i);
    }

    private static JSONObject c(JSONObject jSONObject, AUserProfile aUserProfile, int i) {
        if (aUserProfile == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            LogUtil.a("PLGACHIEVE_AchievePuller", "HmsLite tokenType == ", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            jSONObject.put("token", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
            jSONObject.put("appId", aUserProfile.getAppId());
            jSONObject.put("deviceType", aUserProfile.getDeviceType());
            jSONObject.put("deviceId", aUserProfile.getDeviceId());
            jSONObject.put("sysVersion", aUserProfile.getSysVersion());
            jSONObject.put("bindDeviceType", aUserProfile.getBindDeviceType());
            jSONObject.put("appType", String.valueOf(aUserProfile.getAppType()));
            jSONObject.put("ts", String.valueOf(c()));
            jSONObject.put("iVersion", String.valueOf(i));
            jSONObject.put("language", aUserProfile.getLanguage());
            jSONObject.put("environment", String.valueOf(aUserProfile.getEnvironment()));
            jSONObject.put("currentManufacturer", Build.MANUFACTURER);
            if (Utils.o()) {
                jSONObject.put("siteId", String.valueOf(Utils.d()));
            }
            jSONObject.put("upDeviceType", aUserProfile.getUpDeviceType());
            jSONObject.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchievePuller", "JSONException ", e.getMessage());
        }
        return jSONObject;
    }

    public static HashMap<String, String> c(AUserProfile aUserProfile) {
        HashMap<String, String> hashMap = new HashMap<>(2);
        if (aUserProfile == null) {
            return hashMap;
        }
        hashMap.put("x-huid", aUserProfile.getHuid());
        hashMap.put("x-version", aUserProfile.getAppVersion());
        return hashMap;
    }

    public static String d(int i) {
        StringBuffer stringBuffer = new StringBuffer(32);
        if (10 == i) {
            stringBuffer.append("/activity/getActivityInfo");
            return stringBuffer.toString();
        }
        return b(i, stringBuffer);
    }

    private static String b(int i, StringBuffer stringBuffer) {
        if (i < 10) {
            return c(i, stringBuffer);
        }
        return d(i, stringBuffer);
    }

    private static String c(int i, StringBuffer stringBuffer) {
        switch (i) {
            case 0:
                stringBuffer.append("/achievement/getPersonalInfo");
                break;
            case 1:
                stringBuffer.append("/achievement/getKakaList");
                break;
            case 2:
                stringBuffer.append("/achievement/report/weekly");
                break;
            case 3:
                stringBuffer.append("/achievement/report/monthly");
                break;
            case 4:
                stringBuffer.append("/achievement/getMessages");
                break;
            case 5:
                stringBuffer.append("/achievement/exchange");
                break;
            case 6:
                stringBuffer.append("/achievement/getLanguageResFileUrl");
                break;
            case 7:
                stringBuffer.append("/achievement/takeMedal");
                break;
            case 8:
                stringBuffer.append("/achievement/getMedalConfig");
                break;
            case 9:
                stringBuffer.append("/achievement/setEvent");
                break;
            default:
                LogUtil.a("PLGACHIEVE_AchievePuller", "getTenUrl invalid contentType");
                break;
        }
        return stringBuffer.toString();
    }

    private static String d(int i, StringBuffer stringBuffer) {
        switch (i) {
            case 11:
                stringBuffer.append("/achievement/getMyTaskListV2");
                break;
            case 12:
                stringBuffer.append("/achievement/updateUserTastStatusV2");
                break;
            case 13:
                stringBuffer.append("/achievement/getUserExperienceInfo");
                break;
            case 14:
                stringBuffer.append("/achievement/updateUserExperience");
                break;
            case 15:
                stringBuffer.append("/achievement/getKakaRedeem");
                break;
            case 16:
                stringBuffer.append("/achievement/kakaRedeem");
                break;
            case 17:
                stringBuffer.append("/achievement/getMyTaskList");
                break;
            case 18:
                stringBuffer.append("/achievement/updateUserTastStatus");
                break;
            case 19:
                stringBuffer.append("/achievement/signIn");
                break;
            case 20:
                stringBuffer.append("/achievement/getSignInRecord");
                break;
            case 21:
                stringBuffer.append("/achievement/getGifts");
                break;
            case 22:
                stringBuffer.append("/achievement/redeemGift");
                break;
            case 23:
                stringBuffer.append("/achievement/getRedeemRecord");
                break;
            default:
                stringBuffer.append(c(i));
                break;
        }
        return stringBuffer.toString();
    }

    private static String c(int i) {
        switch (i) {
            case 24:
                return "/achievement/getImageLabel";
            case 25:
                return "/achievement/getExperienceHistory";
            case 26:
                return "/achievement/delayKaka";
            default:
                LogUtil.a("PLGACHIEVE_AchievePuller", "getUrl invalid contentType");
                return "";
        }
    }

    private static long c() {
        return System.currentTimeMillis();
    }

    public static void a(UserAchieveWrapper userAchieveWrapper, String str, Context context) {
        if (userAchieveWrapper == null || context == null) {
            return;
        }
        if (ResultCode.CODE_SUBMIT_COUNT_MAX.equals(str)) {
            LogUtil.a("PLGACHIEVE_AchievePuller", "UPDATE_USER_LEVEL:repeat", str);
            mct.b(context, "levelEventKey", "");
        } else if ("0".equals(str)) {
            LogUtil.a("PLGACHIEVE_AchievePuller", "UPDATE_USER_LEVEL:", str);
            mee.b(context).c(200, userAchieveWrapper);
        } else {
            LogUtil.a("PLGACHIEVE_AchievePuller", "UPDATE_USER_LEVEL:", str);
        }
    }

    public static boolean e(UserAchieveWrapper userAchieveWrapper, String str) {
        if (userAchieveWrapper == null) {
            return false;
        }
        return !(str == null || "0".equals(str)) || mlk.a(userAchieveWrapper) || mlj.b(userAchieveWrapper);
    }
}
