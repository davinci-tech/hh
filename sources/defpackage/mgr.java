package defpackage;

import com.amap.api.col.p0003sl.it;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.Locale;

/* loaded from: classes6.dex */
public class mgr {
    private final int dr;
    private final String ds;
    private final int dt;
    public static final mgr ag = c("firstUseDate", 9001);
    public static final mgr df = a(ParsedFieldTag.TOTAL_DAYS, 9002);
    public static final mgr du = a("totalActiveDaysCurrentYear", 10015);
    public static final mgr dd = a(BleConstants.TOTAL_CALORIES, AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED);
    public static final mgr ab = a("distanceCompare", 9009);
    public static final mgr g = a("caloriesCompare", 9010);

    /* renamed from: do, reason: not valid java name */
    public static final mgr f123do = a("totalStep", 3001);
    public static final mgr dn = a("totalStepDistance", 3002);
    public static final mgr bk = a("maxStepContinuousDays", 10026);
    public static final mgr cs = a("stepCompare", IEventListener.EVENT_ID_DEVICE_SCAN_FINISH);
    public static final mgr cv = a("stepOverTenThousand", IEventListener.EVENT_ID_DEVICE_RTSP_CONN);
    public static final mgr bl = a("maxStep", 3003);
    public static final mgr bj = j("maxStepDay", IEventListener.EVENT_ID_DEVICE_CONN_FAIL);
    public static final mgr bp = a("maxWalkMonth", IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC);
    public static final mgr bn = a("maxWalkSeason", IEventListener.EVENT_ID_DEVICE_UPDATE);
    public static final mgr bi = a("maxWalkSeasonSteps", 3009);
    public static final mgr dm = a("walkCount", 3010);
    public static final mgr ck = a(KakaConstants.SLEEP_DURATION, 8002);
    public static final mgr ch = a("sleepCount", MainLoginCallBack.MSG_START_APK_SERVICE_ERROR);
    public static final mgr cn = b("sleepScore", 8001);
    public static final mgr cm = b("maxSleepScoreOfSeason", 10017);
    public static final mgr cp = b("maxSleepScoreValueOfSeason", 10018);
    public static final mgr cr = b("fallAsleepTimeLess24", 10019);
    public static final mgr cq = b("minSleepDurationDay", 10020);
    public static final mgr bv = b("minSleepDuration", 10021);
    public static final mgr cj = j("fallAsleepTime", MainLoginCallBack.MSG_HWID_ACCOUNT_NOT_LOGIN);
    public static final mgr ct = j("wakeupTime", MainLoginCallBack.MSG_NO_NETWORK);
    public static final mgr ci = b("breLevel", MainLoginCallBack.MSG_HMS_VERSION_ERROR);
    public static final mgr co = a("sleepScoreCompare", MainLoginCallBack.MSG_SHOW_HMS_DIALOG);
    public static final mgr dh = a("totalRunDistance", 2001);
    public static final mgr ce = a("runDistanceCompare", 2007);
    public static final mgr cg = a("runNumberOfTimes", 2002);
    public static final mgr cf = a("runNumberOfDays", 10022);
    public static final mgr by = a("maxRunContinuousDays", 10023);
    public static final mgr cb = a("bestPace", 10024);
    public static final mgr ca = a("bestPaceCompare", 10025);
    public static final mgr cd = a("runMaxDistance", 2003);
    public static final mgr cc = c("runMaxDistanceDay", 2004);
    public static final mgr cl = a("timeOfDay", 2005);
    public static final mgr ao = a("halfMarathonCount", 9004);
    public static final mgr aq = a("fullMarathonCount", 9005);
    public static final mgr ap = a("halfMarathonBestPace", 2008);
    public static final mgr ar = c("halfMarathonTimestamp", 2009);
    public static final mgr bf = a("marathonBestPace", 2010);
    public static final mgr bg = c("marathonTimestamp", 2011);
    public static final mgr db = a("totalCycleDistance", 1001);
    public static final mgr y = a("cycleDistanceCompare", 1005);
    public static final mgr v = a("cycleNumberOfTimes", 1002);
    public static final mgr bh = a("maxCycleContinuousDays", PrebakedEffectId.RT_SPEED_UP);
    public static final mgr u = a("cycleMaxDistance", 1003);
    public static final mgr x = c("cycleMaxDistanceDay", 1004);
    public static final mgr dj = a("totalDuration", 4001);
    public static final mgr di = a("totalDurationExcludeRunCourse", PrebakedEffectId.RT_JUMP);
    public static final mgr de = a("totalCourseDay", 10029);
    public static final mgr am = a("maxFitnessContinuousDays", PrebakedEffectId.RT_COIN_DROP);
    public static final mgr aa = j("favoriteCourseName", PrebakedEffectId.RT_HEARTBEAT);
    public static final mgr cx = a("maxSwimContinuousDays", 10032);
    public static final mgr al = a("fitnessNumberOfTimes", 4002);
    public static final mgr an = a("fitnessDurationCompare", 4006);
    public static final mgr ak = a("fitnessMaxDuration", WearableStatusCodes.DATA_ITEM_TOO_LARGE);
    public static final mgr bm = c("fitnessMaxDurationDay", WearableStatusCodes.INVALID_TARGET_NODE);
    public static final mgr ad = a("favoriteCourses", 4007);
    public static final mgr ai = j("favoriteSportType", WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED);
    public static final mgr ah = a("favoriteSportSumDuration", 4013);
    public static final mgr aj = e("fitnessTypeMap", 4014);
    public static final mgr bo = c("swimMaxDistanceDay", 4009);
    public static final mgr br = a("swimMaxDistance", 4010);
    public static final mgr cy = a("swimSumDistance", 4011);
    public static final mgr cz = a("swimSumTime", PrebakedEffectId.RT_FOOTSTEP);
    public static final mgr da = a("swimSumDistanceCompare", PrebakedEffectId.RT_ICE);
    public static final mgr cw = a("swimSumCount", 4012);
    public static final mgr bq = j("medalIdList", 5001);
    public static final mgr as = a("annualFlagNum", PrebakedEffectId.RT_DRAWING_ARROW);
    public static final mgr bx = a("annualReachFlagNum", 10034);
    public static final mgr aw = a("headlineNum", 10035);
    public static final mgr q = a("maxContinuousHeadlineDay", PrebakedEffectId.RT_SNIPER_RIFLE);
    public static final mgr au = a("headlineDayNum", PrebakedEffectId.RT_ASSAULT_RIFLE);
    public static final mgr av = a("hypnoticMusicNum", 10038);
    public static final mgr af = a("favoriteHypnoticMusicType", 10039);
    public static final mgr dk = j("totalNumOfMusicType", 10050);
    public static final mgr ae = c("firstBeVipTime", PrebakedEffectId.RT_FAST_MOVING);
    public static final mgr w = a("currentVipstatus", PrebakedEffectId.RT_FLY);
    public static final mgr at = a("joinActivityNum", 10001);
    public static final mgr ax = d("JoinActivityCompletionRate", 10002);
    public static final mgr z = a("favoriteActivityType", 10003);
    public static final mgr cu = e("starAcquired", AuthCode.StatusCode.PERMISSION_EXPIRED);
    public static final mgr dg = a(BleConstants.TOTAL_DISTANCE, 9003);
    public static final mgr c = a("bloodPressureTotalNum", 10004);
    public static final mgr d = a("bloodPressureNormalNum", 10005);
    public static final mgr e = d("bloodPressureNormalRatio", 10006);

    /* renamed from: a, reason: collision with root package name */
    public static final mgr f14983a = d("bloodPressureNormalRatioCompare", 10007);
    public static final mgr i = a("bloodSugarTotalNum", 10008);
    public static final mgr j = a("bloodSugarNormalNum", 10009);
    public static final mgr h = d("bloodSugarNormalRatio", 10010);
    public static final mgr f = d("bloodSugarNormalRatioCompare", 10011);
    public static final mgr b = b("averageWeightThisYear", 10012);
    public static final mgr dq = a("weightMeasureTimes", 10013);
    public static final mgr dp = d("BMI", PrebakedEffectId.RT_LIGHTNING);
    public static final mgr dl = b("weightCompare", PrebakedEffectId.RT_CALENDAR_DATE);
    public static final mgr ac = a("dietRecordDayNum", 10016);
    public static final mgr dc = c("threeRingReachNum", PrebakedEffectId.RT_SPRING);
    public static final mgr bd = a("maxContinuousThreeRingDay", PrebakedEffectId.RT_SWING);
    public static final mgr bs = a("perfectCloverReachNum", PrebakedEffectId.RT_WIND);
    public static final mgr be = a("maxContinuousPerfectCloverDay", PrebakedEffectId.RT_VICTORY);
    public static final mgr s = a("cloverLeafReachNum", PrebakedEffectId.RT_AWARD);
    public static final mgr o = a("climbHillCount", 10051);
    public static final mgr n = a("climbHillDistance", 10052);
    public static final mgr l = d("climbHillAltitude", 10053);
    public static final mgr m = d("climbHillMaxAltitude", 10054);
    public static final mgr p = c("climbHillMaxAltitudeDay", 10055);
    public static final mgr t = a("climbHillMaxAltitudeDistance", 10056);
    public static final mgr r = a("climbHillMaxAltitudeDuration", 10057);
    public static final mgr k = d("climbHillMaxAltitudeCreepingWave", 10058);
    public static final mgr bu = a("playBadmintonCount", 10059);
    public static final mgr bw = a("playBadmintonDuration", 10060);
    public static final mgr bt = a("playBadmintonMaxDuration", 10061);
    public static final mgr bz = c("playBadmintonMaxDurationDay", 10062);
    public static final mgr az = a("jumpRopeCount", 10063);
    public static final mgr bc = c("jumpRopeNum", 10064);
    public static final mgr ba = a("jumpRopeMaxNum", 10065);
    public static final mgr ay = c("jumpRopeMaxNumDay", 10066);
    public static final mgr bb = a("jumpRopeMaxContinuousDays", 10067);

    public mgr(String str, int i2, int i3) {
        Preconditions.checkNotNull(str);
        this.ds = str;
        this.dt = i2;
        this.dr = i3;
    }

    public int e() {
        return this.dr;
    }

    public String a() {
        return this.ds;
    }

    public int c() {
        return this.dt;
    }

    public String toString() {
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[2];
        objArr[0] = this.ds;
        objArr[1] = this.dr == 1 ? "i" : it.i;
        return String.format(locale, "%s(%s)", objArr);
    }

    public static mgr a(String str, int i2) {
        return new mgr(str, i2, 1);
    }

    public static mgr d(String str, int i2) {
        return new mgr(str, i2, 2);
    }

    public static mgr j(String str, int i2) {
        return new mgr(str, i2, 3);
    }

    public static mgr c(String str, int i2) {
        return new mgr(str, i2, 4);
    }

    public static mgr b(String str, int i2) {
        return new mgr(str, i2, 5);
    }

    public static mgr e(String str, int i2) {
        return new mgr(str, i2, 6);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mgr)) {
            return false;
        }
        mgr mgrVar = (mgr) obj;
        return this.ds.equals(mgrVar.ds) && this.dr == mgrVar.dr;
    }

    public final int hashCode() {
        return this.ds.hashCode();
    }
}
