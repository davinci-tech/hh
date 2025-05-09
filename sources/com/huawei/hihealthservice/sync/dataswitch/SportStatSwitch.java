package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.SportTotalData;
import com.huawei.hwcloudmodel.model.unite.ActiveHourBasic;
import com.huawei.hwcloudmodel.model.unite.ExerciseTimeBasic;
import com.huawei.hwcloudmodel.model.unite.GoalAchieveBasic;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwcloudmodel.model.unite.SportTotal;
import com.huawei.operation.OperationKey;
import defpackage.igo;
import defpackage.ijz;
import defpackage.ikr;
import defpackage.iku;
import defpackage.ikv;
import defpackage.isw;
import defpackage.isy;
import defpackage.isz;
import defpackage.ita;
import defpackage.iup;
import defpackage.iut;
import defpackage.iuz;
import defpackage.ivz;
import defpackage.iwe;
import defpackage.koq;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class SportStatSwitch {

    /* renamed from: a, reason: collision with root package name */
    private ExerciseIntensityStatSwitch f4217a;
    private igo b = new igo();
    private Context c;
    private HealthStatisticsSwitch d;

    public SportStatSwitch(Context context) {
        this.c = context.getApplicationContext();
        this.f4217a = new ExerciseIntensityStatSwitch(this.c);
        this.d = new HealthStatisticsSwitch(this.c);
    }

    public List<igo> a(List<SportTotalData> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (SportTotalData sportTotalData : list) {
            if (sportTotalData == null) {
                LogUtil.c("Debug_SportStatSwitch", "sportTotalData is null ");
            } else {
                List<igo> b = b(sportTotalData, i);
                if (b != null && !b.isEmpty()) {
                    arrayList.addAll(b);
                }
            }
        }
        return arrayList;
    }

    public List<HiHealthData> b(List<SportTotalData> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (SportTotalData sportTotalData : list) {
            if (sportTotalData.getDeviceCode().longValue() != 0) {
                try {
                    List<HiHealthData> c = c(sportTotalData, i);
                    if (c != null || !c.isEmpty()) {
                        arrayList.addAll(c);
                    }
                } catch (iut e) {
                    ReleaseLogUtil.c("HiH_SportStatSwitch", "convertSportTotalDataToHealthData exception e=", e.getMessage());
                }
            }
        }
        return arrayList;
    }

    private List<HiHealthData> c(SportTotalData sportTotalData, int i) throws iut {
        ikv a2 = iku.a(this.c).a(1, i, sportTotalData.getDeviceCode().longValue());
        if (a2 == null) {
            ReleaseLogUtil.c("Debug_SportStatSwitch", "saveDeviceStat hiHealthContext is null");
            return Collections.emptyList();
        }
        a2.i(1);
        if (sportTotalData.getSportBasicInfo() == null) {
            ReleaseLogUtil.c("Debug_SportStatSwitch", "basicInfo null", sportTotalData);
            return Collections.emptyList();
        }
        return Arrays.asList(c(901, r8.fetchSteps(), sportTotalData, a2), c(902, r8.fetchDistance(), sportTotalData, a2), c(903, r8.fetchCalorie(), sportTotalData, a2), c(904, r8.fetchAltitude(), sportTotalData, a2), c(905, r8.fetchFloor(), sportTotalData, a2), c(TypedValues.Custom.TYPE_REFERENCE, r8.fetchDuration(), sportTotalData, a2));
    }

    public List<igo> b(SportTotalData sportTotalData, int i) {
        igo d;
        if (sportTotalData.getDeviceCode().longValue() != 0) {
            return Collections.emptyList();
        }
        int intValue = sportTotalData.getRecordDay().intValue();
        int intValue2 = sportTotalData.getSportType().intValue();
        SportBasicInfo sportBasicInfo = sportTotalData.getSportBasicInfo();
        String timeZone = sportTotalData.getTimeZone();
        ikv a2 = ikr.b(this.c).a(0, i, 0);
        if (a2 == null) {
            ReleaseLogUtil.d("Debug_SportStatSwitch", "switchSportTotalDataToDayStat statClient is null");
            return null;
        }
        com.huawei.hwlogsmodel.LogUtil.c("Debug_SportStatSwitch", "switchSportTotalDataToDayStat sportType is ", Integer.valueOf(intValue2));
        ArrayList arrayList = new ArrayList(16);
        if (sportBasicInfo != null) {
            b(sportTotalData, i, intValue2, sportBasicInfo);
            List<igo> c = c(intValue2, sportBasicInfo, intValue);
            if (!HiCommonUtil.d(c)) {
                arrayList.addAll(c);
            }
        }
        ExerciseTimeBasic exerciseTimeBasic = sportTotalData.getExerciseTimeBasic();
        if (exerciseTimeBasic != null) {
            List<igo> c2 = this.f4217a.c(exerciseTimeBasic);
            if (!HiCommonUtil.d(c2)) {
                arrayList.addAll(c2);
            }
        }
        ActiveHourBasic activeHourBasic = sportTotalData.getActiveHourBasic();
        if (activeHourBasic != null && (d = this.d.d(activeHourBasic)) != null) {
            arrayList.add(d);
        }
        GoalAchieveBasic goalAchieveBasic = sportTotalData.getGoalAchieveBasic();
        if (goalAchieveBasic != null) {
            List<igo> b = this.d.b(HiHealthDictManager.d(this.c).m(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()), goalAchieveBasic);
            if (!HiCommonUtil.d(b)) {
                arrayList.addAll(b);
            }
        }
        com.huawei.hwlogsmodel.LogUtil.a("Debug_SportStatSwitch", "switchSportTotalDataToDayStat " + HiJsonUtil.e(arrayList));
        if (koq.b(arrayList)) {
            ReleaseLogUtil.d("Debug_SportStatSwitch", "switchSportTotalDataToDayStat dayStatTables is null or empty");
            return null;
        }
        return this.b.e(arrayList, timeZone, a2.b(), intValue);
    }

    private void b(SportTotalData sportTotalData, int i, int i2, SportBasicInfo sportBasicInfo) {
        int fetchSteps;
        if (i2 == 0 && sportTotalData.getDeviceCode().longValue() == 0 && sportTotalData.getRecordDay().intValue() == HiDateUtil.c(System.currentTimeMillis()) && (fetchSteps = sportBasicInfo.fetchSteps() - iuz.a(this.c, i, HiDateUtil.c(System.currentTimeMillis()), 40002)) > 0) {
            String b = ijz.c().b(i);
            String str = b + "step_sum_dvalue";
            com.huawei.hwlogsmodel.LogUtil.c("Debug_SportStatSwitch", "switchSportTotalDataToDayStat huid is ", b, ", key is ", str);
            int c = iwe.c(this.c, b) + fetchSteps;
            iwe.a(this.c, b, c);
            com.huawei.hwlogsmodel.LogUtil.c("Debug_SportStatSwitch", "switchSportTotalDataToDayStat sportStepDvalueDB is ", String.valueOf(c), ", stepDvalue is ", Integer.valueOf(fetchSteps));
            KeyValDbManager.b(this.c).e(str, String.valueOf(c));
            if (fetchSteps > 5000) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
                linkedHashMap.put("errorCode", Integer.toString(1007));
                ivz.d(this.c).e(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap, false);
            }
        }
    }

    private HiHealthData c(int i, double d, SportTotalData sportTotalData, ikv ikvVar) {
        HiHealthData hiHealthData = new HiHealthData(i);
        hiHealthData.setStartTime(HiDateUtil.t(HiDateUtil.a(sportTotalData.getRecordDay().intValue())));
        hiHealthData.setEndTime(HiDateUtil.f(HiDateUtil.a(sportTotalData.getRecordDay().intValue())));
        hiHealthData.setUserId(ikvVar.g());
        hiHealthData.setAppId(ikvVar.e());
        hiHealthData.setDeviceId(ikvVar.d());
        hiHealthData.setClientId(ikvVar.b());
        hiHealthData.setSyncStatus(ikvVar.i());
        hiHealthData.setValue(d);
        hiHealthData.setTimeZone(sportTotalData.getTimeZone());
        return hiHealthData;
    }

    public List<SportTotal> d(List<HiHealthData> list) {
        return isy.d().b(list);
    }

    public List<SportTotal> d(List<HiHealthData> list, Map<Integer, SportTotal> map) {
        return isw.e().d(list, map);
    }

    public List<SportTotal> c(List<HiHealthData> list) {
        return isw.e().e(list);
    }

    private List<igo> c(int i, SportBasicInfo sportBasicInfo, int i2) {
        if (i == 0) {
            return ita.e(sportBasicInfo, this.b, i2);
        }
        if (i == 1) {
            return ita.e(sportBasicInfo, this.b);
        }
        if (i == 2) {
            return ita.d(sportBasicInfo, this.b);
        }
        if (i == 3) {
            return isz.a(sportBasicInfo, this.b);
        }
        if (i == 4) {
            return isz.e(sportBasicInfo, this.b);
        }
        if (i == 5) {
            return isz.b(sportBasicInfo, this.b);
        }
        if (i != 9) {
            return null;
        }
        return isz.c(sportBasicInfo, this.b);
    }

    public List<SportTotalData> b(List<SportTotalData> list) {
        com.huawei.hwlogsmodel.LogUtil.a("Debug_SportStatSwitch", "start mergeClimbAndFloor");
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (SportTotalData sportTotalData : list) {
            if (sportTotalData.getSportType().intValue() == 1) {
                d(sportTotalData);
                arrayList.add(sportTotalData);
            } else if (sportTotalData.getSportType().intValue() == 2) {
                arrayList.add(sportTotalData);
            } else {
                arrayList2.add(sportTotalData);
            }
        }
        if (arrayList.isEmpty()) {
            com.huawei.hwlogsmodel.LogUtil.a("Debug_SportStatSwitch", "mergeClimbAndFloor no such data");
            return arrayList2;
        }
        arrayList2.addAll(e(arrayList));
        return arrayList2;
    }

    private List<SportTotalData> e(List<SportTotalData> list) {
        SparseArray sparseArray = new SparseArray(list.size());
        for (SportTotalData sportTotalData : list) {
            if (sportTotalData != null) {
                int intValue = sportTotalData.getRecordDay().intValue();
                com.huawei.hwlogsmodel.LogUtil.c("Debug_SportStatSwitch", "mergeOneDayStat key is ", Integer.valueOf(intValue));
                SportTotalData sportTotalData2 = (SportTotalData) sparseArray.get(intValue);
                if (sportTotalData2 != null) {
                    sportTotalData2 = e(sportTotalData2, sportTotalData);
                    com.huawei.hwlogsmodel.LogUtil.c("Debug_SportStatSwitch", "mergeOneDayStat need merged key is ", Integer.valueOf(intValue));
                }
                sparseArray.put(intValue, sportTotalData2);
            }
        }
        ArrayList arrayList = new ArrayList(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add((SportTotalData) sparseArray.valueAt(i));
        }
        return arrayList;
    }

    private void d(SportTotalData sportTotalData) {
        sportTotalData.setSportType(2);
        SportBasicInfo sportBasicInfo = sportTotalData.getSportBasicInfo();
        sportBasicInfo.configAltitude((sportBasicInfo.fetchFloor() * 30) / 10.0f);
        sportBasicInfo.configFloor(0);
    }

    private SportTotalData e(SportTotalData sportTotalData, SportTotalData sportTotalData2) {
        SportTotalData sportTotalData3 = new SportTotalData();
        sportTotalData3.setSportType(sportTotalData.getSportType());
        sportTotalData3.setDeviceCode(sportTotalData.getDeviceCode());
        sportTotalData3.setDataSource(sportTotalData.getDataSource());
        sportTotalData3.setRecordDay(sportTotalData.getRecordDay());
        sportTotalData3.setTimeZone(sportTotalData.getTimeZone());
        sportTotalData3.setSportBasicInfo(iup.e(Integer.valueOf(sportTotalData.getSportBasicInfo().fetchSteps() + sportTotalData2.getSportBasicInfo().fetchSteps()), Integer.valueOf(sportTotalData.getSportBasicInfo().fetchDistance() + sportTotalData2.getSportBasicInfo().fetchDistance()), Integer.valueOf(sportTotalData.getSportBasicInfo().fetchCalorie() + sportTotalData2.getSportBasicInfo().fetchCalorie()), Float.valueOf(sportTotalData.getSportBasicInfo().fetchAltitude() + sportTotalData2.getSportBasicInfo().fetchAltitude()), Integer.valueOf(sportTotalData.getSportBasicInfo().fetchFloor() + sportTotalData2.getSportBasicInfo().fetchFloor()), Integer.valueOf(sportTotalData.getSportBasicInfo().fetchDuration() + sportTotalData2.getSportBasicInfo().fetchDuration()), Integer.valueOf(sportTotalData.getSportBasicInfo().fetchCount() + sportTotalData2.getSportBasicInfo().fetchCount())));
        return sportTotalData3;
    }
}
