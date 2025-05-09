package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ActivityInfo;
import com.huawei.pluginachievement.manager.model.CalorieExchange;
import com.huawei.pluginachievement.manager.model.GiftInfo;
import com.huawei.pluginachievement.manager.model.GiftRecord;
import com.huawei.pluginachievement.manager.model.KakaCheckInReturnBody;
import com.huawei.pluginachievement.manager.model.KakaCheckinRecord;
import com.huawei.pluginachievement.manager.model.KakaDelayInfo;
import com.huawei.pluginachievement.manager.model.KakaLineRecord;
import com.huawei.pluginachievement.manager.model.KakaRecord;
import com.huawei.pluginachievement.manager.model.KakaRedeemGiftReturnBody;
import com.huawei.pluginachievement.manager.model.KakaRedeemInfo;
import com.huawei.pluginachievement.manager.model.KakaRedeemResult;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
class mdi {

    /* renamed from: a, reason: collision with root package name */
    private static final Integer[] f14896a = {10002, 10003, 10004, 10005};

    static UserAchieveWrapper b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseCalorieExchange resultCode:", optString);
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(5);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(5);
            CalorieExchange calorieExchange = new CalorieExchange(jSONObject.optInt("kaka"), jSONObject.optInt(ParsedFieldTag.K_CAL));
            userAchieveWrapper2.setExchange(calorieExchange);
            LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseCalorieExchange calorieExchange:", calorieExchange);
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseCalorieExchange Exception:", e.getMessage());
            return new UserAchieveWrapper(5);
        }
    }

    static UserAchieveWrapper j(String str) {
        JSONObject jSONObject;
        String optString;
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaRedeemInfo json:", str);
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(15);
        try {
            jSONObject = new JSONObject(str);
            optString = jSONObject.optString("resultCode");
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaRedeemInfo:", e.getMessage());
        }
        if (!mdn.e(optString)) {
            userAchieveWrapper.setResultCode(optString);
            return userAchieveWrapper;
        }
        KakaRedeemInfo kakaRedeemInfo = new KakaRedeemInfo();
        kakaRedeemInfo.setKakaSum(jSONObject.optInt(ParsedFieldTag.KAKA_SUM));
        kakaRedeemInfo.setExchangeProportion(jSONObject.optInt(ParsedFieldTag.EXCHANGE_PROPORTION));
        kakaRedeemInfo.setDailyLimit(jSONObject.optInt(ParsedFieldTag.DAILY_LIMIT));
        kakaRedeemInfo.setRedeemTimes(jSONObject.optInt(ParsedFieldTag.TIMES_REDEEM));
        kakaRedeemInfo.setRemainingKaka(e(jSONObject));
        kakaRedeemInfo.setRedeemableKaka(jSONObject.optInt(ParsedFieldTag.REDEEMABLE_KAKA));
        userAchieveWrapper.setRedeemInfo(kakaRedeemInfo);
        return userAchieveWrapper;
    }

    static UserAchieveWrapper h(String str) {
        JSONObject jSONObject;
        String optString;
        KakaRedeemResult kakaRedeemResult;
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaRedeemResult json:", str);
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(16);
        try {
            jSONObject = new JSONObject(str);
            optString = jSONObject.optString("resultCode");
            kakaRedeemResult = new KakaRedeemResult();
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaRedeemResult:", e.getMessage());
        }
        if (!mdn.e(optString)) {
            userAchieveWrapper.setResultCode(optString);
            kakaRedeemResult.setRemainingKaka(e(jSONObject));
            userAchieveWrapper.setRedeemResult(kakaRedeemResult);
            return userAchieveWrapper;
        }
        kakaRedeemResult.setKakaSum(jSONObject.optInt(ParsedFieldTag.KAKA_SUM));
        kakaRedeemResult.setRedeemTimes(jSONObject.optInt(ParsedFieldTag.TIMES_REDEEM));
        kakaRedeemResult.setRemainingKaka(e(jSONObject));
        userAchieveWrapper.setRedeemResult(kakaRedeemResult);
        return userAchieveWrapper;
    }

    static UserAchieveWrapper d(String str) {
        LogUtil.c("PLGACHIEVE_AchieveKakaParser", "enter parseActivityInfo");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseActivityInfo resultCode:", optString);
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(10);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("activity");
            ActivityInfo activityInfo = new ActivityInfo();
            activityInfo.saveActivityId(mdn.b("activityId", jSONObject2));
            activityInfo.saveBeginTime(mdn.b(ParsedFieldTag.BEGIN_DATE, jSONObject2));
            activityInfo.saveEndTime(mdn.b("endDate", jSONObject2));
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(10);
            userAchieveWrapper2.saveActivityInfo(activityInfo);
            LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseActivityInfo", activityInfo.toString());
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseActivityInfo Exception:", e.getMessage());
            return new UserAchieveWrapper(10);
        }
    }

    static UserAchieveWrapper f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(1);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(ParsedFieldTag.KAKA_DELAY_INFO);
            KakaLineRecord kakaLineRecord = new KakaLineRecord();
            if (optJSONObject != null) {
                kakaLineRecord.setKakaDelayInfo(b(jSONObject));
            }
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(1);
            JSONArray optJSONArray = jSONObject.optJSONArray(ParsedFieldTag.KAKAS);
            if (optJSONArray == null) {
                userAchieveWrapper2.setKakaLineRecord(kakaLineRecord);
                return userAchieveWrapper2;
            }
            ArrayList arrayList = new ArrayList(16);
            for (int i = 0; i < optJSONArray.length(); i++) {
                Object opt = optJSONArray.opt(i);
                if (opt instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) opt;
                    KakaRecord kakaRecord = new KakaRecord();
                    kakaRecord.setDate(jSONObject2.optLong("timestamp"));
                    kakaRecord.setKakaNum(jSONObject2.optInt("kaka"));
                    kakaRecord.setDescription(jSONObject2.optInt("reason"));
                    kakaRecord.setOccurDate(jSONObject2.optLong(ParsedFieldTag.OCCUR_DATE));
                    arrayList.add(kakaRecord);
                    LogUtil.c("PLGACHIEVE_AchieveKakaParser", "parseKakaLine record:", kakaRecord);
                }
            }
            kakaLineRecord.setKakaLineRecords(arrayList);
            userAchieveWrapper2.setKakaLineRecord(kakaLineRecord);
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaLine Exception:", e.getMessage());
            return new UserAchieveWrapper(1);
        }
    }

    private static KakaDelayInfo b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(ParsedFieldTag.KAKA_DELAY_INFO);
        if (optJSONObject == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaParser", "getKakaDelay delayObj is null.");
            return null;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaDelay ", optJSONObject.toString());
        int e = mdn.e(ParsedFieldTag.KAKA_EXPIRATION_KAKA, optJSONObject);
        KakaDelayInfo kakaDelayInfo = new KakaDelayInfo();
        kakaDelayInfo.setExpirationKaka(e);
        kakaDelayInfo.setUpdateTime(mdn.d("updateTime", optJSONObject));
        kakaDelayInfo.setBeforeYear(mdn.b(ParsedFieldTag.KAKA_BEFORE_YEAR, optJSONObject));
        kakaDelayInfo.setDelayRate(mdn.e(ParsedFieldTag.KAKA_DELAY_RATE, optJSONObject));
        return kakaDelayInfo;
    }

    static UserAchieveWrapper c(String str) {
        try {
            LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaDelay json:", str);
            String optString = new JSONObject(str).optString("resultCode");
            UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(26);
            userAchieveWrapper.setResultCode(optString);
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaDelay Exception:", e.getMessage());
            return new UserAchieveWrapper(26);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ea A[Catch: JSONException -> 0x0110, TryCatch #0 {JSONException -> 0x0110, blocks: (B:3:0x0020, B:7:0x003f, B:8:0x0080, B:10:0x0086, B:12:0x0091, B:14:0x00f3, B:15:0x009b, B:17:0x00a1, B:18:0x00ac, B:20:0x00b3, B:23:0x00df, B:24:0x00e3, B:28:0x00ea, B:30:0x00c2, B:32:0x00a8, B:34:0x00fd, B:37:0x0102), top: B:2:0x0020 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.util.ArrayList<com.huawei.pluginachievement.manager.model.UserAchieveWrapper> c(java.lang.String r18, android.content.Context r19, int r20) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mdi.c(java.lang.String, android.content.Context, int):java.util.ArrayList");
    }

    private static mdf a(Context context, JSONObject jSONObject, String str, boolean z, long j) {
        mdf mdfVar = new mdf();
        mdfVar.e(str);
        String b = mdn.b(ParsedFieldTag.TASK_NAME, jSONObject);
        if (TextUtils.isEmpty(b)) {
            b = mdn.b(ParsedFieldTag.TASK_NAME_FIRST, jSONObject);
        }
        mdfVar.g(b);
        mdfVar.n(mdn.e(ParsedFieldTag.KAKA_TASK_SCENARIO, jSONObject));
        mdfVar.r(mdn.e(ParsedFieldTag.KAKA_TASK_VIP_MATCH, jSONObject));
        String b2 = mdn.b("status", jSONObject);
        long d = mdn.d(ParsedFieldTag.TASK_MODIFY_TIME, jSONObject);
        boolean a2 = mle.a(d, j);
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "refreshKakaSyncDate name ", mdfVar.f(), " isDiff ", Boolean.valueOf(z), " isDifferentDate ", Boolean.valueOf(a2), " lastModifyTime ", Long.valueOf(d), " curTimestamp ", Long.valueOf(j), " Scenario ", Integer.valueOf(mdfVar.ac()), " taskStatus ", b2);
        if (a2) {
            LogUtil.a("PLGACHIEVE_AchieveKakaParser", "syncDate differentDays");
            e(mdfVar, context, z, b2, mdn.e(ParsedFieldTag.KAKA_TASK_RULE, jSONObject));
        } else {
            mdfVar.i(b2);
            if (z) {
                mdfVar.f(0);
            } else {
                mdfVar.f(1);
            }
        }
        c(mdfVar, jSONObject);
        mdfVar.e(d);
        mdfVar.a(j);
        return mdfVar;
    }

    private static mdf b(JSONObject jSONObject, String str, long j) {
        String b = mdn.b("status", jSONObject);
        mdf mdfVar = new mdf();
        mdfVar.e(str);
        long d = mdn.d(ParsedFieldTag.TASK_MODIFY_TIME, jSONObject);
        boolean a2 = mle.a(d, j);
        if (mle.j(str)) {
            a2 = mle.d(d, j);
        }
        mdfVar.i(b);
        if (a2) {
            mdfVar.i(String.valueOf(0));
        }
        mdfVar.f(1);
        c(mdfVar, jSONObject);
        mdfVar.e(d);
        mdfVar.a(j);
        return mdfVar;
    }

    private static void e(mdf mdfVar, Context context, boolean z, String str, int i) {
        if (String.valueOf(3).equals(str)) {
            mdfVar.i(str);
            mdfVar.f(1);
            return;
        }
        if (mle.d(i)) {
            if (!mle.f(str)) {
                mdfVar.i(String.valueOf(3));
                mdfVar.f(0);
                return;
            } else {
                mdfVar.i(str);
                mdfVar.f(1);
                return;
            }
        }
        if (z) {
            mdfVar.i(String.valueOf(0));
            mdfVar.f(0);
            mct.b(context, "taskReachInfo", "");
        } else {
            mdfVar.i(String.valueOf(0));
            mdfVar.f(1);
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "refreshKakaSyncDate UNFINISHED ", mdfVar.f(), " isDiff ", Boolean.valueOf(z), " Scenario ", Integer.valueOf(mdfVar.ac()));
    }

    private static void c(mdf mdfVar, JSONObject jSONObject) {
        mdfVar.b(mdn.b(ParsedFieldTag.TASK_KEY, jSONObject));
        mdfVar.d(1);
        mdfVar.h(0);
        mdfVar.j(mdn.e(ParsedFieldTag.TASK_TYPE, jSONObject));
        String b = mdn.b(ParsedFieldTag.TASK_NAME, jSONObject);
        if (TextUtils.isEmpty(b)) {
            b = mdn.b(ParsedFieldTag.TASK_NAME_FIRST, jSONObject);
        }
        mdfVar.g(b);
        mdfVar.f(mdn.b(ParsedFieldTag.TASK_SPECIFICATION, jSONObject));
        String b2 = mdn.b("description", jSONObject);
        if (TextUtils.isEmpty(b2)) {
            b2 = mdn.b(ParsedFieldTag.TASK_DESCRIPTION_FIRST, jSONObject);
        }
        mdfVar.d(b2);
        mdfVar.a(mdn.b(ParsedFieldTag.TASK_ICON, jSONObject));
        int e = mdn.e("kaka", jSONObject);
        if (e == 0) {
            e = mdn.e(ParsedFieldTag.TASK_REWARD_KAKA_FIRST, jSONObject);
        }
        mdfVar.a(e);
        mdfVar.c(mdn.e(ParsedFieldTag.TASK_REWARD_EXPERIENCE, jSONObject));
        mdfVar.b(mdn.d("startTime", jSONObject));
        mdfVar.d(mdn.d("endTime", jSONObject));
        mdfVar.b(mdn.e(ParsedFieldTag.TASK_REMAIN_TIMES, jSONObject));
        int e2 = mdn.e("type", jSONObject);
        int e3 = mdn.e(ParsedFieldTag.KAKA_CONTINUOUS, jSONObject);
        mdfVar.o(e2);
        mdfVar.k(e3);
        mdfVar.o(mdn.b(ParsedFieldTag.KAKA_TASK_PRE_BUTTON, jSONObject));
        mdfVar.n(mdn.b(ParsedFieldTag.KAKA_TASK_POST_BUTTON, jSONObject));
        mdfVar.c(mdn.b(ParsedFieldTag.KAKA_TASK_PRE_BTN_URL, jSONObject));
        mdfVar.g(mdn.e("category", jSONObject));
        mdfVar.q(mdn.e(ParsedFieldTag.KAKA_TASK_RULE, jSONObject));
        mdfVar.m(mdn.e("level", jSONObject));
        mdfVar.i(mdn.e(ParsedFieldTag.KAKA_TASK_BONUS_TYPE, jSONObject));
        mdfVar.e(mdn.e("weight", jSONObject));
        mdfVar.i(mdn.d("updateTime", jSONObject));
        mdfVar.n(mdn.e(ParsedFieldTag.KAKA_TASK_SCENARIO, jSONObject));
        mdfVar.r(mdn.e(ParsedFieldTag.KAKA_TASK_VIP_MATCH, jSONObject));
        mdfVar.j(mdn.b("picture", jSONObject));
        mdfVar.h(mdn.b(ParsedFieldTag.KAKA_TASK_BTN_COLOR, jSONObject));
        mdfVar.l(mdn.e(ParsedFieldTag.KAKA_TASK_LABLE_MATCH, jSONObject));
    }

    private static void b(JSONObject jSONObject, StringBuilder sb, StringBuilder sb2) {
        int e = mdn.e(ParsedFieldTag.KAKA_TASK_RULE, jSONObject);
        int e2 = mdn.e(ParsedFieldTag.KAKA_TASK_SCENARIO, jSONObject);
        int e3 = mdn.e("status", jSONObject);
        if (e2 == 0 && mle.d(e) && e3 >= 1) {
            sb.append(e);
            sb.append(",");
        }
        if (e2 == 2 && mle.d(e) && e3 >= 1) {
            sb2.append(e);
            sb2.append(",");
        }
    }

    private static void a(StringBuilder sb, StringBuilder sb2) {
        if (sb == null || TextUtils.isEmpty(sb.toString())) {
            LogUtil.h("PLGACHIEVE_AchieveKakaParser", "handleLevelNewTask kakaNewTaskRuleStr isEmpty.");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "handleLevelNewTask kakaNewTaskRuleStr:", sb.toString(), "levelNewTaskRuleStr ", sb2.toString());
        for (Integer num : f14896a) {
            if (a(String.valueOf(num), sb, sb2)) {
                LogUtil.a("PLGACHIEVE_AchieveKakaParser", "handleLevelNewTask taskRuleId:", num);
                mcv.d(BaseApplication.getContext()).c(BaseApplication.getContext(), String.valueOf(num), new HashMap(16));
            }
        }
    }

    private static boolean a(String str, StringBuilder sb, StringBuilder sb2) {
        return (sb == null || sb2 == null || !sb.toString().contains(str) || sb2.toString().contains(str)) ? false : true;
    }

    static UserAchieveWrapper b(int i, String str) {
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(i);
        try {
            JSONObject jSONObject = new JSONObject(str);
            userAchieveWrapper.setResultCode(jSONObject.optString("resultCode"));
            mde mdeVar = new mde();
            mdeVar.e(mdn.b("resultCode", jSONObject));
            mdeVar.b(mdn.b("resultDesc", jSONObject));
            mdeVar.c(mdn.e("kaka", jSONObject));
            mdeVar.a(mdn.e(ParsedFieldTag.TOTAL_KAKA, jSONObject));
            String b = mdn.b("taskId", jSONObject);
            String b2 = mdn.b(ParsedFieldTag.RESULTS, jSONObject);
            if (!TextUtils.isEmpty(b2)) {
                mdeVar.c(mdn.e(b2, new HashMap(16)));
                if (i == 12) {
                    b = c(mdeVar.a());
                }
            }
            mdeVar.a(b);
            userAchieveWrapper.saveKakaUpdateReturnBody(mdeVar);
            mle.c(b);
            LogUtil.a("PLGACHIEVE_AchieveKakaParser", "kakaUpdateReturnBody=", mdeVar.toString());
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaUpdateBody Exception:", e.getMessage());
        }
        return userAchieveWrapper;
    }

    public static UserAchieveWrapper a(String str) {
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaCheckInResult json");
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(19);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            if (!mdn.e(optString)) {
                return userAchieveWrapper;
            }
            KakaCheckInReturnBody kakaCheckInReturnBody = new KakaCheckInReturnBody();
            kakaCheckInReturnBody.setConDays(jSONObject.optInt(ParsedFieldTag.KAKA_CONSECUTIVE_DAYS));
            kakaCheckInReturnBody.setKakaSum(jSONObject.optInt(ParsedFieldTag.KAKA_SUM));
            kakaCheckInReturnBody.setKaka(jSONObject.optInt("kaka"));
            userAchieveWrapper.setKakaCheckInReturnBody(kakaCheckInReturnBody);
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaCheckInResult Exception:", e.getMessage());
            return userAchieveWrapper;
        }
    }

    public static UserAchieveWrapper e(String str) {
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaCheckedRecords json");
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(20);
        try {
            String optString = new JSONObject(str).optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            if (!mdn.e(optString)) {
                userAchieveWrapper.setKakaCheckinRecords(null);
            } else {
                userAchieveWrapper.setKakaCheckinRecords(a(str, ParsedFieldTag.KAKA_CHECKED_IN_RECORDES, KakaCheckinRecord.class));
            }
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaCheckedRecords Exception:", e.getMessage());
            return userAchieveWrapper;
        }
    }

    public static UserAchieveWrapper i(String str) {
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaGiftList json");
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(21);
        try {
            String optString = new JSONObject(str).optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            if (!mdn.e(optString)) {
                userAchieveWrapper.setKakaGiftInfos(null);
            } else {
                userAchieveWrapper.setKakaGiftInfos(a(str, ParsedFieldTag.KAKA_GET_GIFTS, GiftInfo.class));
            }
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaGiftList Exception:", e.getMessage());
            return userAchieveWrapper;
        }
    }

    public static UserAchieveWrapper g(String str) {
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseKakaRedeemGiftResult json");
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(22);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            if (!mdn.e(optString)) {
                return userAchieveWrapper;
            }
            KakaRedeemGiftReturnBody kakaRedeemGiftReturnBody = new KakaRedeemGiftReturnBody();
            kakaRedeemGiftReturnBody.setAwardId(jSONObject.optString(ParsedFieldTag.KAKA_REDEEM_GIFT_ID));
            kakaRedeemGiftReturnBody.setSumKaka(jSONObject.optInt(ParsedFieldTag.KAKA_LEFT_POINT));
            kakaRedeemGiftReturnBody.setExchangeCode(jSONObject.optString(ParsedFieldTag.KAKA_REDEEM_EXCHANGE_CODE));
            kakaRedeemGiftReturnBody.setToUseUrl(jSONObject.optString(ParsedFieldTag.KAKA_REDEEM_TO_USE_URL));
            userAchieveWrapper.setKakaRedeemGiftReturnBody(kakaRedeemGiftReturnBody);
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseKakaRedeemGiftResult Exception:", e.getMessage());
            return userAchieveWrapper;
        }
    }

    public static UserAchieveWrapper m(String str) {
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseRedeemGiftRecordList json");
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(23);
        try {
            String optString = new JSONObject(str).optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            if (!mdn.e(optString)) {
                userAchieveWrapper.setRedeemGiftRecords(null);
            } else {
                userAchieveWrapper.setRedeemGiftRecords(a(str, ParsedFieldTag.KAKA_CHECKED_IN_RECORDES, GiftRecord.class));
            }
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseRedeemGiftRecordList Exception:", e.getMessage());
            return userAchieveWrapper;
        }
    }

    public static <T> List<T> a(String str, String str2, Class<T> cls) {
        Object fromJson;
        LogUtil.a("PLGACHIEVE_AchieveKakaParser", "parseRecordList json");
        ArrayList arrayList = new ArrayList(16);
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray(str2);
            if (optJSONArray == null) {
                return arrayList;
            }
            Gson gson = new Gson();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                if (jSONObject != null && (fromJson = gson.fromJson(jSONObject.toString(), (Class<Object>) cls)) != null) {
                    arrayList.add(fromJson);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveKakaParser", "parseRecordList Exception:", e.getMessage());
            return arrayList;
        }
    }

    private static String c(Map<String, Object> map) {
        return (map == null || map.size() != 1) ? "" : map.keySet().iterator().next();
    }

    private static Integer e(JSONObject jSONObject) {
        Object opt = jSONObject.opt(ParsedFieldTag.REMAINING_KAKA);
        if (opt instanceof Integer) {
            return (Integer) opt;
        }
        return null;
    }
}
