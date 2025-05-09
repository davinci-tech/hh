package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveLevelTotalInfo;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.ExperienceHistoryBean;
import com.huawei.pluginachievement.manager.model.LevelLineRecord;
import com.huawei.pluginachievement.manager.model.LevelUpdateReturnBody;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
class mdm {
    static ArrayList<UserAchieveWrapper> b(String str) {
        JSONObject jSONObject;
        String optString;
        ArrayList<UserAchieveWrapper> arrayList = new ArrayList<>(16);
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(13);
        try {
            jSONObject = new JSONObject(str);
            optString = jSONObject.optString("resultCode");
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveLevelParser", "parseLevelInfo Exception:", e.getMessage());
        }
        if (!mdn.e(optString)) {
            userAchieveWrapper.setResultCode(optString);
            arrayList.add(userAchieveWrapper);
            return arrayList;
        }
        AchieveUserLevelInfo achieveUserLevelInfo = new AchieveUserLevelInfo();
        double a2 = mdn.a(ParsedFieldTag.DAY_LEVEL, jSONObject);
        int e2 = mdn.e(ParsedFieldTag.USER_LEVEL, jSONObject);
        int e3 = mdn.e(ParsedFieldTag.USER_EXPERIENCE, jSONObject);
        long d = mdn.d("timestamp", jSONObject);
        achieveUserLevelInfo.saveUserLevel(e2);
        achieveUserLevelInfo.saveUserExperience(e3);
        achieveUserLevelInfo.saveSyncTimestamp(d);
        achieveUserLevelInfo.saveUserReachDays(a2);
        userAchieveWrapper.saveAchieveUserLevelInfo(achieveUserLevelInfo);
        arrayList.add(userAchieveWrapper);
        JSONArray optJSONArray = jSONObject.optJSONArray(ParsedFieldTag.LEVEL_USER_COUNT_LIST);
        if (optJSONArray == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelParser", "array is null");
        } else {
            e(arrayList, optJSONArray, d, jSONObject);
        }
        LogUtil.a("PLGACHIEVE_AchieveLevelParser", "parseLevelInfo get userLevel ", Integer.valueOf(e2), " userExperience ", Integer.valueOf(e3));
        return arrayList;
    }

    private static void e(ArrayList<UserAchieveWrapper> arrayList, JSONArray jSONArray, long j, JSONObject jSONObject) {
        LogUtil.a("PLGACHIEVE_AchieveLevelParser", "array size=", Integer.valueOf(jSONArray.length()));
        ArrayList arrayList2 = new ArrayList(8);
        int i = 0;
        int i2 = 0;
        while (i < jSONArray.length()) {
            AchieveLevelTotalInfo achieveLevelTotalInfo = new AchieveLevelTotalInfo();
            int i3 = i + 1;
            achieveLevelTotalInfo.saveLevel(i3);
            try {
                i2 += jSONArray.getInt(i);
                achieveLevelTotalInfo.saveUserNumber(jSONArray.getInt(i));
            } catch (JSONException e) {
                LogUtil.b("PLGACHIEVE_AchieveLevelParser", "parseLevelList Exception:", e.getMessage());
            }
            achieveLevelTotalInfo.saveSyncTime(j);
            UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(13);
            userAchieveWrapper.saveAchieveLevelTotalInfo(achieveLevelTotalInfo);
            arrayList.add(userAchieveWrapper);
            arrayList2.add(mjq.a(achieveLevelTotalInfo, i2));
            i = i3;
        }
        double a2 = mdn.a(ParsedFieldTag.LEVEL_VIP_RATE, jSONObject);
        LogUtil.a("PLGACHIEVE_AchieveLevelParser", "parseLevelList ruleRate ", Double.valueOf(a2));
        int e2 = mdn.e(ParsedFieldTag.USER_LEVEL, jSONObject);
        mct.b(BaseApplication.getContext(), "levelTypeData", "[" + e2 + "," + mdn.e(ParsedFieldTag.USER_EXPERIENCE, jSONObject) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 1) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 2) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 3) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 4) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 5) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 6) + "," + mlc.c(e2, (ArrayList<mki>) arrayList2, 7) + "," + a2 + "]");
    }

    static UserAchieveWrapper a(String str) {
        JSONObject jSONObject;
        String optString;
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(14);
        try {
            jSONObject = new JSONObject(str);
            optString = jSONObject.optString("resultCode");
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveLevelParser", "parseLevelUpdateInfo Exception:", e.getMessage());
        }
        if (!mdn.e(optString)) {
            userAchieveWrapper.setResultCode(optString);
            return userAchieveWrapper;
        }
        LevelUpdateReturnBody levelUpdateReturnBody = new LevelUpdateReturnBody();
        levelUpdateReturnBody.saveResultDes(mdn.b("resultDesc", jSONObject));
        levelUpdateReturnBody.saveRewardExperience(mdn.e(ParsedFieldTag.EXPERIENCE, jSONObject));
        levelUpdateReturnBody.saveTotalExperience(mdn.e(ParsedFieldTag.TOTAL_EXPERIENCE, jSONObject));
        userAchieveWrapper.saveLevelUpdateReturnBody(levelUpdateReturnBody);
        LogUtil.a("PLGACHIEVE_AchieveLevelParser", "levelUpdateReturnBody=", levelUpdateReturnBody.toString());
        return userAchieveWrapper;
    }

    public static void d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            LogUtil.a("PLGACHIEVE_AchieveLevelParser", "parseLevelImageLabelInfo json:", str);
            if (mdn.e(optString)) {
                String b = mdn.b("label", jSONObject);
                if (!TextUtils.isEmpty(b)) {
                    mct.b(BaseApplication.getContext(), "AchieveLevelLabel", b);
                    return;
                }
                JSONArray jSONArray = jSONObject.getJSONArray("labels");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        sb.append(jSONArray.getInt(i));
                        sb.append(",");
                    } catch (JSONException e) {
                        LogUtil.b("PLGACHIEVE_AchieveLevelParser", "parseLevelList Exception:", e.getMessage());
                    }
                }
                String sb2 = sb.toString();
                LogUtil.a("PLGACHIEVE_AchieveLevelParser", "parseLevelImageLabelInfo labels:", sb2);
                if (TextUtils.isEmpty(sb2)) {
                    return;
                }
                mct.b(BaseApplication.getContext(), "AchieveLevelLabel", sb2);
            }
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_AchieveLevelParser", "parseLevelUpdateInfo Exception:", e2.getMessage());
        }
    }

    public static UserAchieveWrapper c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            LogUtil.a("PLGACHIEVE_AchieveLevelParser", "parseLevelHistoryRecord json:", str);
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(25);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray(ParsedFieldTag.KAKA_CHECKED_IN_RECORDES);
            if (optJSONArray == null) {
                return new UserAchieveWrapper(25);
            }
            ArrayList arrayList = new ArrayList(8);
            for (int i = 0; i < optJSONArray.length(); i++) {
                Object opt = optJSONArray.opt(i);
                if (opt instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) opt;
                    ExperienceHistoryBean experienceHistoryBean = new ExperienceHistoryBean();
                    long optLong = jSONObject2.optLong(ParsedFieldTag.TASK_COMPLETE_TIME);
                    experienceHistoryBean.setCompleteTime(optLong);
                    experienceHistoryBean.setExperienceValue(jSONObject2.optInt(ParsedFieldTag.TASK_EXPERIENCE_VALUE));
                    experienceHistoryBean.setReasonId(jSONObject2.optInt(ParsedFieldTag.TASK_REASON_ID));
                    experienceHistoryBean.setNewLevel(jSONObject2.optInt(ParsedFieldTag.TASK_NEW_LEVEL));
                    experienceHistoryBean.setReasonDesc(jSONObject2.optString(ParsedFieldTag.TASK_REASON_DESC));
                    experienceHistoryBean.setMonthOfYear(mlg.a(optLong, 0));
                    experienceHistoryBean.setDayOfMonth(mlg.a(optLong, 1));
                    experienceHistoryBean.setTime(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(optLong)));
                    arrayList.add(experienceHistoryBean);
                }
            }
            LevelLineRecord levelLineRecord = new LevelLineRecord();
            levelLineRecord.setRecords(arrayList);
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(25);
            userAchieveWrapper2.setLevelLineRecord(levelLineRecord);
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveLevelParser", "parseLevelUpdateInfo Exception:", e.getMessage());
            return new UserAchieveWrapper(25);
        }
    }
}
