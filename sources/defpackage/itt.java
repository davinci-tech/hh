package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.dataswitch.ExerciseIntensityStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.HealthStatisticsSwitch;
import com.huawei.hihealthservice.sync.dataswitch.SportStatSwitch;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddSportTotalReq;
import com.huawei.hwcloudmodel.model.unite.AddSportTotalRsp;
import com.huawei.hwcloudmodel.model.unite.ParamValidDetail;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwcloudmodel.model.unite.SportTotal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.ui.openservice.OpenServiceUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HiValidatorUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class itt {
    private static Context g;
    private iis f;
    private HealthStatisticsSwitch i;
    private ExerciseIntensityStatSwitch j;
    private int k;
    private boolean m;
    private int n;
    private jbs o;
    private static final int[] c = {40002, 40004, 40003, SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER, SmartMsgConstant.MSG_TYPE_RIDE_USER};
    private static final String[] d = {"sport_step_sum", "sport_distance_sum", "sport_calorie_sum", "sport_duration_sum", "sport_altitude_offset_sum"};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f13605a = {47102, 47103, 47104, 47105, 47106, 47101, 47107, 47108, 47109};
    private static final String[] b = {OpenServiceUtil.Location.STEP, "RUN", "CYCLE", "FITNESS", "HEART", "TOTAL", "CLIMB", "SWIM", "UNKNOWHIGH"};
    private static final int[] h = {DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING_STAT.value()};
    private static final String[] e = {"stepGoalValue", "stepGoalState", "stepIsRing", "calorieGoalValue", "calorieGoalState", "calorieIsRingNew", "durationGoalValue", "durationGoalState", "durationIsRing", "activeGoalValue", "activeGoalState", "activeIsRing"};

    private itt() {
        this.n = 0;
        this.k = 0;
        this.m = false;
        this.f = iis.d();
        this.o = jbs.a(g);
        this.j = new ExerciseIntensityStatSwitch(g);
        this.i = new HealthStatisticsSwitch(g);
    }

    static class c {
        private static final itt e = new itt();
    }

    public static itt e(Context context) {
        g = context.getApplicationContext();
        return c.e;
    }

    public void c(int i, HiDataReadOption hiDataReadOption, HiSyncOption hiSyncOption, SportStatSwitch sportStatSwitch) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "pushData() begin !");
        this.n = 0;
        this.k = 0;
        this.m = iuz.i();
        if (!ism.l()) {
            ReleaseLogUtil.d("HiH_HiSyncTtlSptStat", "pushData() dataPrivacy switch is closed, push end!");
            return;
        }
        ivc.c(5.0d, "SYNC_SPORT_STAT_DOWNLOAD_PERCENT_MAX");
        int h2 = this.f.h(i);
        if (h2 <= 0) {
            LogUtil.h("HiH_HiSyncTtlSptStat", "pushData() no statClient get, maybe no data need to pushData!");
        } else {
            a(h2, i, hiDataReadOption, hiSyncOption, sportStatSwitch);
            ism.f().b(i);
        }
        ivc.b(g);
        ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "pushData() end!");
    }

    private void a(int i, int i2, HiDataReadOption hiDataReadOption, HiSyncOption hiSyncOption, SportStatSwitch sportStatSwitch) throws iut {
        List<HiHealthData> list;
        while (this.n < 2) {
            HashMap hashMap = new HashMap(1);
            int[] iArr = {50, this.m};
            List<HiHealthData> e2 = iuz.e(g, i, c, d, iArr);
            if (!HiCommonUtil.d(e2)) {
                LogUtil.a("HiH_HiSyncTtlSptStat", "uploadSportStatData localSportBasicInfo=" + HiJsonUtil.e(sportStatSwitch.d(e2, hashMap)));
            }
            Context context = g;
            int[] iArr2 = f13605a;
            List<HiHealthData> e3 = iuz.e(context, i, iArr2, b, iArr);
            if (!HiCommonUtil.d(e3)) {
                this.j.c(e3, hashMap);
            }
            LogUtil.a("HiH_HiSyncTtlSptStat", "uploadSportStatData localExerciseIntensityStats=", HiJsonUtil.e(e3));
            List<HiHealthData> e4 = iuz.e(g, i, new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()}, new String[]{"countActiveHour"}, iArr);
            if (!HiCommonUtil.d(e4)) {
                this.i.c(e4, hashMap);
            }
            LogUtil.a("HiH_HiSyncTtlSptStat", "uploadSportStatData localActiveHour=", HiJsonUtil.e(e4));
            Context context2 = g;
            int[] iArr3 = h;
            List<HiHealthData> e5 = iuz.e(context2, i, iArr3, e, iArr);
            if (!HiCommonUtil.d(e5)) {
                this.i.e(e5, hashMap);
            }
            ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "uploadSportStatData localThreeCircleDatas=", HiJsonUtil.e(e5));
            LogUtil.a("HiH_HiSyncTtlSptStat", "uploadSportStatData SportTotal =", HiJsonUtil.e(hashMap));
            List<Integer> i3 = this.f.i(i2);
            if (HiCommonUtil.d(i3)) {
                list = null;
            } else {
                LogUtil.c("HiH_HiSyncTtlSptStat", "uploadDeviceStatData() has client");
                list = c(i3, hiDataReadOption);
            }
            if (!a(e2, list, hiSyncOption, sportStatSwitch, i, hashMap)) {
                LogUtil.h("HiH_HiSyncTtlSptStat", "uploadSportStatData failed, clientId=", Integer.valueOf(i));
            } else {
                iuz.e(g, e3, iArr2, i);
                iuz.e(g, e4, new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()}, i);
                iuz.e(g, e5, iArr3, i);
            }
        }
        this.n = 0;
    }

    private List<HiHealthData> c(List<Integer> list, HiDataReadOption hiDataReadOption) {
        return ijt.b().c(list, hiDataReadOption, 200);
    }

    private List<SportTotal> b(List<HiHealthData> list, SportStatSwitch sportStatSwitch) {
        ArrayList arrayList = new ArrayList(10);
        if (!HiCommonUtil.d(list)) {
            List<SportTotal> c2 = sportStatSwitch.c(list);
            if (!HiCommonUtil.d(c2)) {
                arrayList.addAll(c2);
            }
        }
        return arrayList;
    }

    private boolean a(List<HiHealthData> list, List<HiHealthData> list2, HiSyncOption hiSyncOption, SportStatSwitch sportStatSwitch, int i, Map<Integer, SportTotal> map) throws iut {
        if (!this.m) {
            int i2 = this.k + 1;
            this.k = i2;
            iuz.e(i2, hiSyncOption.getSyncManual());
        } else {
            int i3 = this.k + 1;
            this.k = i3;
            if (i3 > 3) {
                this.n += 2;
                return false;
            }
        }
        List<SportTotal> b2 = b(list2, sportStatSwitch);
        if (map.size() > 0) {
            Iterator<SportTotal> it = map.values().iterator();
            while (it.hasNext()) {
                b2.add(it.next());
            }
        }
        if (HiCommonUtil.d(b2)) {
            this.n += 2;
            LogUtil.h("HiH_HiSyncTtlSptStat", "addSportStatDataOne sportTotals is null or empty");
            return false;
        }
        AddSportTotalReq b3 = b(b2, b2.get(0).getTimeZone(), hiSyncOption);
        while (this.n < 2) {
            AddSportTotalRsp e2 = this.o.e(b3);
            if (e2 != null && e2.getResultCode().intValue() == 1001) {
                final StringBuilder sb = new StringBuilder("");
                b3.getTotalInfo().forEach(new Consumer() { // from class: itv
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        itt.d(sb, (SportTotal) obj);
                    }
                });
                LinkedHashMap linkedHashMap = new LinkedHashMap(1);
                linkedHashMap.put("sportTotalMsg", sb.toString());
                ParamValidDetail paramValidDetail = e2.getParamValidDetail();
                iuz.b(e2, paramValidDetail, (LinkedHashMap<String, String>) linkedHashMap);
                ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "addSportStat failed ! resultCode is ", e2.getResultCode(), ", resultDesc is ", paramValidDetail == null ? e2.getResultDesc() : paramValidDetail.toString());
                if (paramValidDetail == null) {
                    this.n++;
                } else {
                    a(list, list2, paramValidDetail, i);
                    this.n++;
                }
            } else if (!ius.a(e2, false)) {
                this.n++;
            } else {
                a(list, list2, null, i);
                ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "addSportStat success, mUploadCount is ", Integer.valueOf(this.k));
                LogUtil.a("HiH_HiSyncTtlSptStat", "stat is ", HiJsonUtil.e(b2));
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "addDataToCloud failed ! mUploadCount is ", Integer.valueOf(this.k));
        LogUtil.a("HiH_HiSyncTtlSptStat", "stat is ", HiJsonUtil.e(b2));
        return false;
    }

    static /* synthetic */ void d(StringBuilder sb, SportTotal sportTotal) {
        SportBasicInfo sportBasicInfo = sportTotal.getSportBasicInfo();
        if (sportBasicInfo != null) {
            sb.append("day:");
            sb.append(sportTotal.getRecordDay());
            sb.append(" basicInfo:");
            sb.append(sportBasicInfo);
            sb.append(" ");
            sb.append(" checkStep:");
            sb.append(HiValidatorUtil.a(sportBasicInfo.fetchSteps()));
            sb.append(" ");
        }
    }

    private void a(List<HiHealthData> list, List<HiHealthData> list2, ParamValidDetail paramValidDetail, int i) {
        if (!HiCommonUtil.d(list)) {
            if (paramValidDetail != null) {
                iuz.b(list, paramValidDetail);
            }
            iuz.d(g, list, c, i);
        }
        if (HiCommonUtil.d(list2)) {
            return;
        }
        if (paramValidDetail != null) {
            iuz.b(list2, paramValidDetail);
        }
        iuz.h(g, list2);
    }

    private AddSportTotalReq b(List<SportTotal> list, String str, HiSyncOption hiSyncOption) {
        AddSportTotalReq addSportTotalReq = new AddSportTotalReq();
        addSportTotalReq.setTotalInfo(list);
        addSportTotalReq.setTimeZone(str);
        for (SportTotal sportTotal : list) {
            if (sportTotal.getRecordDay().intValue() == HiDateUtil.c(System.currentTimeMillis())) {
                LogUtil.c("HiH_HiSyncTtlSptStat", "getAddSportTotalReq upload today stat may let cloud do push ,pushAction is ", Integer.valueOf(hiSyncOption.getPushAction()), " day is ", sportTotal.getRecordDay());
                addSportTotalReq.setIsForce(hiSyncOption.getPushAction());
            }
        }
        return addSportTotalReq;
    }
}
