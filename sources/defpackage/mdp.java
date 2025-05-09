package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.manager.service.AchievePersonalDataObserver;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdp {
    public static boolean e(int i) {
        return i == 15 || i == 16;
    }

    private static UserAchieveWrapper b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(0);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            AchieveInfo achieveInfo = new AchieveInfo();
            achieveInfo.setUserLevel(jSONObject.optInt(ParsedFieldTag.USER_LEVEL));
            achieveInfo.saveMedals(mdn.d(jSONObject.optJSONArray(ParsedFieldTag.USER_MEDALS)));
            achieveInfo.setUserPoint(jSONObject.optInt(ParsedFieldTag.KAKA_SUM));
            achieveInfo.setUserReachStandardDays(jSONObject.optDouble(ParsedFieldTag.DAY_LEVEL));
            achieveInfo.setSyncTimestamp(jSONObject.optLong("timestamp"));
            JSONObject optJSONObject = jSONObject.optJSONObject(ParsedFieldTag.KAKA_DELAY_INFO);
            if (optJSONObject != null) {
                achieveInfo.setExpirationKaka(mdn.e(ParsedFieldTag.KAKA_EXPIRATION_KAKA, optJSONObject));
            }
            LogUtil.a("PLGACHIEVE_AchievePersonalParser", "parsePersonal mds of achieveInfo:", achieveInfo.acquireMedals());
            JSONObject optJSONObject2 = jSONObject.optJSONObject(ParsedFieldTag.ACHIEVEMENT_REPORT);
            JSONObject optJSONObject3 = jSONObject.optJSONObject(ParsedFieldTag.ACHIEVEMENT_REPORT_EXTEND);
            JSONObject optJSONObject4 = jSONObject.optJSONObject(ParsedFieldTag.NPES_BEST_RESULT);
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(0);
            if (optJSONObject2 == null) {
                userAchieveWrapper2.setAchieveInfo(achieveInfo);
                return userAchieveWrapper2;
            }
            if (Utils.o()) {
                LogUtil.h("PLGACHIEVE_AchievePersonalParser", "parseTotalRecord is isOverseaAndNoOperation.");
                if (optJSONObject2.has(ParsedFieldTag.TOTAL_DAYS)) {
                    AchievePersonalDataObserver.a(optJSONObject2.optInt(ParsedFieldTag.TOTAL_DAYS));
                }
            } else {
                TotalRecord totalRecord = new TotalRecord();
                d(totalRecord, optJSONObject2, optJSONObject3);
                userAchieveWrapper2.setTotalRecord(totalRecord);
                SingleDayRecord singleDayRecord = new SingleDayRecord();
                e(singleDayRecord, optJSONObject2, optJSONObject3, optJSONObject4);
                userAchieveWrapper2.setSingleDayRecord(singleDayRecord);
            }
            userAchieveWrapper2.setAchieveInfo(achieveInfo);
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchievePersonalParser", "parsePersonal Exception:", e.getMessage());
            return new UserAchieveWrapper(0);
        }
    }

    public static double d(double d) {
        try {
            return Double.parseDouble(NumberFormat.getInstance().parse(UnitUtil.e(d / 1000.0d, 1, 2)).toString());
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchievePersonalParser", "NumberFormatException error");
            return 0.0d;
        } catch (ParseException unused2) {
            LogUtil.b("PLGACHIEVE_AchievePersonalParser", "ParseException error");
            return 0.0d;
        }
    }

    public static UserAchieveWrapper e(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i == 10) {
            return mdi.d(str);
        }
        if (i != 12) {
            if (i == 14) {
                return mdm.a(str);
            }
            if (i != 18) {
                if (i != 26) {
                    switch (i) {
                        case 0:
                            return b(str);
                        case 1:
                            return mdi.f(str);
                        case 2:
                            return mdr.c(str);
                        case 3:
                            return mdr.d(str);
                        case 4:
                            return mdj.d(str);
                        case 5:
                            return mdi.b(str);
                        case 6:
                            return mdj.c(str);
                        case 7:
                            return mdj.b(str);
                        default:
                            UserAchieveWrapper a2 = a(i, str);
                            LogUtil.a("PLGACHIEVE_AchievePersonalParser", "continue doParse content type");
                            return a2;
                    }
                }
                return mdi.c(str);
            }
        }
        return mdi.b(i, str);
    }

    private static UserAchieveWrapper a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (i != 24) {
                if (i == 25) {
                    return mdm.c(str);
                }
                UserAchieveWrapper d = d(i, str);
                LogUtil.a("PLGACHIEVE_AchievePersonalParser", "continue doParse content type");
                return d;
            }
            mdm.d(str);
        }
        return null;
    }

    public static UserAchieveWrapper c(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (i == 15) {
                return mdi.j(str);
            }
            if (i == 16) {
                return mdi.h(str);
            }
            LogUtil.h("PLGACHIEVE_AchievePersonalParser", "doKakaParse invalide content type");
        }
        return null;
    }

    private static UserAchieveWrapper d(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            switch (i) {
                case 19:
                    return mdi.a(str);
                case 20:
                    return mdi.e(str);
                case 21:
                    return mdi.i(str);
                case 22:
                    return mdi.g(str);
                case 23:
                    return mdi.m(str);
                default:
                    LogUtil.h("PLGACHIEVE_AchievePersonalParser", "parseKakaCheckInOrGiftResult invalide content type");
                    break;
            }
        }
        return null;
    }

    public static ArrayList<UserAchieveWrapper> c(int i, String str, Context context) {
        ArrayList<UserAchieveWrapper> arrayList = new ArrayList<>(0);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        if (i == 0) {
            return mdj.e(str);
        }
        if (i == 8) {
            return mdj.c(str, context);
        }
        if (i == 11) {
            return mdi.c(str, context, 11);
        }
        if (i == 13) {
            return mdm.b(str);
        }
        if (i == 17) {
            return mdi.c(str, context, 17);
        }
        LogUtil.a("PLGACHIEVE_AchievePersonalParser", "doParse invalide content type");
        return arrayList;
    }

    private static void e(SingleDayRecord singleDayRecord, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        singleDayRecord.setSteps(mdn.e(ParsedFieldTag.MAX_STEPS, jSONObject));
        singleDayRecord.setStepsDate(jSONObject.optLong(ParsedFieldTag.MAX_STEPS_DATE));
        if (jSONObject.has(ParsedFieldTag.MAX_DISTANCE)) {
            singleDayRecord.saveDistance(d(jSONObject.optDouble(ParsedFieldTag.MAX_DISTANCE)));
        }
        if (jSONObject.has(ParsedFieldTag.MAX_DISTANCE_DATE)) {
            singleDayRecord.setDistanceDate(jSONObject.optLong(ParsedFieldTag.MAX_DISTANCE_DATE));
        }
        if (jSONObject.has(ParsedFieldTag.MAX_PACE)) {
            singleDayRecord.saveMatchSpeed(jSONObject.optInt(ParsedFieldTag.MAX_PACE));
        }
        if (jSONObject.has(ParsedFieldTag.MAX_PACE_DATE)) {
            singleDayRecord.setMatchSpeedDate(jSONObject.optLong(ParsedFieldTag.MAX_PACE_DATE));
        }
        singleDayRecord.saveBestRunDistance(mdn.b(ParsedFieldTag.BEST_RUN_DISTANCE, jSONObject2));
        singleDayRecord.saveBestRunPace(mdn.b(ParsedFieldTag.BEST_RUN_PACE, jSONObject2));
        singleDayRecord.saveBestStepDay(mdn.b(ParsedFieldTag.BEST_DAY_STEP, jSONObject2));
        singleDayRecord.saveBestCycleDistance(mdn.b(ParsedFieldTag.BEST_CYCLE_DISTANCE, jSONObject2));
        singleDayRecord.saveBestCycleSpeed(mdn.b(ParsedFieldTag.BEST_CYCLE_SPEED, jSONObject2));
        singleDayRecord.saveBestWalkDistance(mdn.b(ParsedFieldTag.BEST_WALK_DISTANCE, jSONObject2));
        singleDayRecord.saveBestRun3KMPace(mdn.b(ParsedFieldTag.BEST_RUN_3KM_PACE, jSONObject2));
        singleDayRecord.saveBestRun5KMPace(mdn.b(ParsedFieldTag.BEST_RUN_5KM_PACE, jSONObject2));
        singleDayRecord.saveBestRun10KMPace(mdn.b(ParsedFieldTag.BEST_RUN_10KM_PACE, jSONObject2));
        singleDayRecord.saveBestRunHMPace(mdn.b(ParsedFieldTag.BEST_RUN_HM_PACE, jSONObject2));
        singleDayRecord.saveBestRunFMPace(mdn.b(ParsedFieldTag.BEST_RUN_FM_PACE, jSONObject2));
        singleDayRecord.saveBestRopeContinuousCount(mdn.b(ParsedFieldTag.BEST_ROPE_CONTINUOUS_COUNT, jSONObject2));
        singleDayRecord.saveBestRopeDuration(mdn.b(ParsedFieldTag.BEST_ROPE_DURATION, jSONObject2));
        singleDayRecord.saveBestRopeSingCount(mdn.b(ParsedFieldTag.BEST_ROPE_SINGLE_COUNT, jSONObject2));
        singleDayRecord.saveBestRopeSpeed(mdn.b(ParsedFieldTag.BEST_ROPE_SPEED, jSONObject2));
        if (jSONObject3 != null) {
            singleDayRecord.setNpeBestResult(jSONObject3.toString());
        }
        LogUtil.a("PLGACHIEVE_AchievePersonalParser", "myAchieveCloud parseSingleRecord ", singleDayRecord.toString());
    }

    private static void d(TotalRecord totalRecord, JSONObject jSONObject, JSONObject jSONObject2) {
        mcz d = meh.c(BaseApplication.e()).d(1, new HashMap(16));
        if (!(d instanceof TotalRecord) || ((TotalRecord) d).getSteps() <= 0.0d) {
            totalRecord.setDistance(jSONObject.optDouble(ParsedFieldTag.SUM_DISTANCE));
            totalRecord.setSteps(jSONObject.optDouble(ParsedFieldTag.SUM_STEPS));
            totalRecord.saveCalorie(jSONObject.optDouble(ParsedFieldTag.ORIGINAL_SUM_KCAL));
            LogUtil.a("PLGACHIEVE_AchievePersonalParser", "parseTotalRecord data from reportObj.");
        } else {
            totalRecord.setDistance(-0.1d);
            totalRecord.setSteps(-0.1d);
            totalRecord.saveCalorie(-0.1d);
        }
        totalRecord.setDays(jSONObject.optInt(ParsedFieldTag.TOTAL_DAYS));
        totalRecord.setStartDate(jSONObject.optLong(ParsedFieldTag.FIRST_DATE));
        totalRecord.setEndDate(jSONObject.optLong(ParsedFieldTag.LAST_DATE));
        totalRecord.setStepsRanking(jSONObject.optDouble(ParsedFieldTag.SUM_STEPS_DESC));
        totalRecord.saveAccumulatedHealthTime(mdn.b(ParsedFieldTag.TOTAL_FITNESS_DURATION, jSONObject2));
        totalRecord.saveAccumulatedCycleDistance(mdn.b(ParsedFieldTag.TOTAL_CYCLE_DISTANCE, jSONObject2));
        totalRecord.saveAccumulatedRunDistance(mdn.b(ParsedFieldTag.TOTAL_RUN_DISTANCE, jSONObject2));
        totalRecord.saveAccumulatedWalkDistance(mdn.b(ParsedFieldTag.TOTAL_WALK_DISTANCE, jSONObject2));
        LogUtil.a("PLGACHIEVE_AchievePersonalParser", "myAchieveCloud parseTotalRecord: ", totalRecord.toString());
    }
}
