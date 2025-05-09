package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.RecentMonthRecord;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
class mdr {
    static UserAchieveWrapper d(String str) {
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(3);
        RecentMonthRecord recentMonthRecord = new RecentMonthRecord();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            int optInt = jSONObject.optInt("reportNo");
            recentMonthRecord.setReportNo(optInt);
            int optInt2 = jSONObject.optInt(ParsedFieldTag.MIN_REPORT_NO);
            recentMonthRecord.setMinReportNo(optInt2);
            recentMonthRecord.saveEndDate(jSONObject.optLong("endDate"));
            recentMonthRecord.saveFirstDate(jSONObject.optLong(ParsedFieldTag.FIRST_DATE));
            recentMonthRecord.setEndUtcDate(jSONObject.optLong(ParsedFieldTag.END_UTC_DATE));
            if (!mdn.e(optString)) {
                if (optInt2 != 0) {
                    optInt = optInt2;
                }
                recentMonthRecord.setMinReportNo(optInt);
                userAchieveWrapper.setRecentMonthRecord(recentMonthRecord);
                return userAchieveWrapper;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(ParsedFieldTag.USER_REPORT);
            if (optJSONObject == null) {
                userAchieveWrapper.setRecentMonthRecord(recentMonthRecord);
                return userAchieveWrapper;
            }
            recentMonthRecord.setSteps(optJSONObject.optDouble(ParsedFieldTag.SUM_STEPS_NEW));
            double d = mdp.d(optJSONObject.optDouble(ParsedFieldTag.SUM_RUN_DISTANCE));
            recentMonthRecord.setDistance(d);
            recentMonthRecord.setKakaNum(optJSONObject.optInt("kaka"));
            recentMonthRecord.setPrice(optJSONObject.optInt(ParsedFieldTag.PRICE));
            recentMonthRecord.setMedals(mdn.b(optJSONObject.optJSONArray(ParsedFieldTag.USER_MEDALS)));
            recentMonthRecord.setDistanceRanking(mdn.d(ParsedFieldTag.SUM_RUN_DISTANCE_DESC, optJSONObject, d, 1));
            recentMonthRecord.setStepsRanking(optJSONObject.optDouble(ParsedFieldTag.SUM_STEPS_DESC));
            userAchieveWrapper.setRecentMonthRecord(recentMonthRecord);
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveReportParser", "parseMonthly Exception:", e.getMessage());
            userAchieveWrapper.setRecentMonthRecord(recentMonthRecord);
            return userAchieveWrapper;
        }
    }

    static UserAchieveWrapper c(String str) {
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(2);
        RecentWeekRecord recentWeekRecord = new RecentWeekRecord();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            userAchieveWrapper.setResultCode(optString);
            int optInt = jSONObject.optInt("reportNo");
            recentWeekRecord.setReportNo(optInt);
            int optInt2 = jSONObject.optInt(ParsedFieldTag.MIN_REPORT_NO);
            recentWeekRecord.setMinReportNo(optInt2);
            recentWeekRecord.saveEndDate(jSONObject.optLong("endDate"));
            recentWeekRecord.saveFirstDate(jSONObject.optLong(ParsedFieldTag.FIRST_DATE));
            recentWeekRecord.setEndUtcDate(jSONObject.optLong(ParsedFieldTag.END_UTC_DATE));
            if (!mdn.e(optString)) {
                if (optInt2 != 0) {
                    optInt = optInt2;
                }
                recentWeekRecord.setMinReportNo(optInt);
                userAchieveWrapper.setRecentWeekRecord(recentWeekRecord);
                return userAchieveWrapper;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(ParsedFieldTag.USER_REPORT);
            if (optJSONObject == null) {
                userAchieveWrapper.setRecentWeekRecord(recentWeekRecord);
                return userAchieveWrapper;
            }
            recentWeekRecord.setSteps(optJSONObject.optDouble(ParsedFieldTag.SUM_STEPS_NEW));
            double d = mdp.d(optJSONObject.optDouble(ParsedFieldTag.SUM_RUN_DISTANCE));
            recentWeekRecord.setDistance(d);
            recentWeekRecord.setKakaNum(optJSONObject.optInt("kaka"));
            recentWeekRecord.setPrice(optJSONObject.optInt(ParsedFieldTag.PRICE));
            recentWeekRecord.setUserMedals(mdn.b(optJSONObject.optJSONArray(ParsedFieldTag.USER_MEDALS)));
            recentWeekRecord.setDistanceRanking(mdn.d(ParsedFieldTag.SUM_RUN_DISTANCE_DESC, optJSONObject, d, 0));
            recentWeekRecord.setStepsRanking(optJSONObject.optDouble(ParsedFieldTag.SUM_STEPS_DESC));
            userAchieveWrapper.setRecentWeekRecord(recentWeekRecord);
            return userAchieveWrapper;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveReportParser", "parseWeekly Exception:", e.getMessage());
            userAchieveWrapper.setRecentWeekRecord(recentWeekRecord);
            return userAchieveWrapper;
        }
    }
}
