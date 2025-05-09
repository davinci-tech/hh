package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictStat;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.syncdata.dictionary.statistics.HealthStatistics;
import com.huawei.hwcloudmodel.model.unite.ActiveHourBasic;
import com.huawei.hwcloudmodel.model.unite.GoalAchieveBasic;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwcloudmodel.model.unite.SportTotal;
import com.huawei.operation.OperationKey;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.ivz;
import defpackage.koq;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HealthStatisticsSwitch {
    private Context b;

    public HealthStatisticsSwitch(Context context) {
        this.b = context;
    }

    public List<igo> d(int i, List<HealthStatistics> list, int i2) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            ReleaseLogUtil.d("HiH_HealthStatisticsSwitch", "value is null");
            return arrayList;
        }
        ikv a2 = ikr.b(this.b).a(0, i2, 0);
        if (a2 == null) {
            return arrayList;
        }
        List<String> m = HiHealthDictManager.d(this.b).m(i);
        for (HealthStatistics healthStatistics : list) {
            long deviceCode = healthStatistics.getDeviceCode();
            if (deviceCode != 0) {
                ReleaseLogUtil.d("HiH_HealthStatisticsSwitch", "deviceCode should be 0, real deviceCode is ", Long.valueOf(deviceCode));
            } else {
                b(i, arrayList, a2, m, healthStatistics);
            }
        }
        return arrayList;
    }

    private void b(int i, List<igo> list, ikv ikvVar, List<String> list2, HealthStatistics healthStatistics) {
        JsonObject statistics = healthStatistics.getStatistics();
        if (statistics == null) {
            return;
        }
        for (String str : list2) {
            if (statistics.has(str)) {
                int d = HiHealthDictManager.d(this.b).d(i, str, 0);
                if (d == 0) {
                    ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "the key is ", str, ", and statType is not found");
                } else {
                    int c = HiHealthDictManager.d(this.b).c(i, str, 0);
                    if (c == 0) {
                        ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "the key is ", str, ", and hiHealthType is not found");
                    } else {
                        igo igoVar = new igo();
                        igoVar.b(ikvVar.b());
                        igoVar.g(1);
                        igoVar.e(healthStatistics.getRecordDay());
                        igoVar.a(statistics.get(str).getAsDouble());
                        igoVar.d(d);
                        igoVar.c(c);
                        igoVar.b(healthStatistics.getTimezone());
                        list.add(igoVar);
                    }
                }
            }
        }
    }

    public Map<String, List<HealthStatistics>> a(List<HiHealthData> list, int[] iArr) {
        HashMap hashMap = new HashMap();
        if (!koq.b(list) && !HiCommonUtil.e(iArr)) {
            for (HiHealthData hiHealthData : list) {
                for (int i : iArr) {
                    HiHealthDictStat a2 = HiHealthDictManager.d(this.b).a(i);
                    if (a2 == null) {
                        ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "dictStat is null, statType is ", Integer.valueOf(i));
                    } else {
                        HiHealthDictType h = HiHealthDictManager.d(this.b).h(i);
                        if (h == null) {
                            ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "dictType is null, statType is ", Integer.valueOf(i));
                        } else {
                            int i2 = h.i();
                            if (!hashMap.containsKey(Integer.toString(i2))) {
                                ArrayList arrayList = new ArrayList(1);
                                c(hiHealthData, a2, HiDateUtil.c(hiHealthData.getStartTime()), arrayList);
                                hashMap.put(String.valueOf(i2), arrayList);
                            } else {
                                d(hashMap, a2, hiHealthData, i2);
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private Number c(HiHealthData hiHealthData, HiHealthDictStat hiHealthDictStat) {
        String b = hiHealthDictStat.b();
        b.hashCode();
        if (b.equals("Integer")) {
            return Integer.valueOf(hiHealthData.getInt(hiHealthDictStat.c()));
        }
        if (b.equals("Long")) {
            return Long.valueOf(hiHealthData.getLong(hiHealthDictStat.c()));
        }
        return Double.valueOf(hiHealthData.getDouble(hiHealthDictStat.c()));
    }

    private void d(Map<String, List<HealthStatistics>> map, HiHealthDictStat hiHealthDictStat, HiHealthData hiHealthData, int i) {
        int c = HiDateUtil.c(hiHealthData.getStartTime());
        List<HealthStatistics> list = map.get(Integer.toString(i));
        HealthStatistics b = b(c, list);
        if (b == null) {
            c(hiHealthData, hiHealthDictStat, c, list);
        } else {
            b.getStatistics().addProperty(hiHealthDictStat.c(), c(hiHealthData, hiHealthDictStat));
        }
    }

    private void c(HiHealthData hiHealthData, HiHealthDictStat hiHealthDictStat, int i, List<HealthStatistics> list) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(hiHealthDictStat.c(), c(hiHealthData, hiHealthDictStat));
        list.add(new HealthStatistics.Builder().recordDay(i).dataSource(2).deviceCode(0L).statistics(jsonObject).timezone(hiHealthData.getTimeZone()).generateTime(hiHealthData.getModifiedTime()).build());
    }

    private HealthStatistics b(int i, List<HealthStatistics> list) {
        for (HealthStatistics healthStatistics : list) {
            if (i == healthStatistics.getRecordDay()) {
                return healthStatistics;
            }
        }
        return null;
    }

    public void c(List<HiHealthData> list, Map<Integer, SportTotal> map) {
        ArrayList arrayList = null;
        for (HiHealthData hiHealthData : list) {
            int c = HiDateUtil.c(hiHealthData.getStartTime());
            ActiveHourBasic activeHourBasic = new ActiveHourBasic();
            int i = hiHealthData.getInt("countActiveHour");
            if (i < 0 || i > 48) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                HashMap hashMap = new HashMap();
                hashMap.put("day", Integer.valueOf(c));
                hashMap.put("countActiveHour", Integer.valueOf(i));
                arrayList.add(hashMap);
            } else {
                activeHourBasic.setCountActiveHour(i);
                activeHourBasic.setGenerateTime(hiHealthData.getModifiedTime());
                if (map.get(Integer.valueOf(c)) == null) {
                    SportTotal sportTotal = new SportTotal();
                    sportTotal.setSportType(0);
                    sportTotal.setDataId(Long.valueOf(hiHealthData.getDataId()));
                    sportTotal.setRecordDay(Integer.valueOf(c));
                    sportTotal.setDataSource(2);
                    sportTotal.setActiveHourBasic(activeHourBasic);
                    sportTotal.setTimeZone(hiHealthData.getTimeZone());
                    sportTotal.setDeviceCode(0L);
                    sportTotal.setSportBasicInfo(new SportBasicInfo());
                    map.put(Integer.valueOf(c), sportTotal);
                } else {
                    map.get(Integer.valueOf(c)).setActiveHourBasic(activeHourBasic);
                }
            }
        }
        if (koq.b(arrayList)) {
            return;
        }
        String e = HiJsonUtil.e(arrayList);
        ReleaseLogUtil.d("HiH_HealthStatisticsSwitch", "countActiveHour range: [0 ,48], but IllegalCountActiveHourList=" + e);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("countActiveHourFailedToCloud", e);
        ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_BIG_DATA_ERROR_2129012.value(), linkedHashMap, true);
    }

    public void e(List<HiHealthData> list, Map<Integer, SportTotal> map) {
        for (HiHealthData hiHealthData : list) {
            int c = HiDateUtil.c(hiHealthData.getStartTime());
            GoalAchieveBasic goalAchieveBasic = new GoalAchieveBasic();
            goalAchieveBasic.setGenerateTime(hiHealthData.getModifiedTime());
            goalAchieveBasic.setStepGoalValueStat(hiHealthData.getInt("stepGoalValue"));
            goalAchieveBasic.setStepGoalStateStat(hiHealthData.getInt("stepGoalState"));
            goalAchieveBasic.setStepIsRingStat(hiHealthData.getInt("stepIsRing"));
            goalAchieveBasic.setCalorieGoalValueStat(hiHealthData.getInt("calorieGoalValue"));
            goalAchieveBasic.setCalorieGoalStateStat(hiHealthData.getInt("calorieGoalState"));
            goalAchieveBasic.setCalorieIsRingStat(hiHealthData.getInt("calorieIsRingNew"));
            goalAchieveBasic.setDurationGoalValueStat(hiHealthData.getInt("durationGoalValue"));
            goalAchieveBasic.setDurationGoalStateStat(hiHealthData.getInt("durationGoalState"));
            goalAchieveBasic.setDurationIsRingStat(hiHealthData.getInt("durationIsRing"));
            goalAchieveBasic.setActiveGoalValueStat(hiHealthData.getInt("activeGoalValue"));
            goalAchieveBasic.setActiveGoalStateStat(hiHealthData.getInt("activeGoalState"));
            goalAchieveBasic.setActiveIsRingStat(hiHealthData.getInt("activeIsRing"));
            if (map.get(Integer.valueOf(c)) == null) {
                SportTotal sportTotal = new SportTotal();
                sportTotal.setSportType(0);
                sportTotal.setDataId(Long.valueOf(hiHealthData.getDataId()));
                sportTotal.setRecordDay(Integer.valueOf(c));
                sportTotal.setDataSource(2);
                sportTotal.setTimeZone(hiHealthData.getTimeZone());
                sportTotal.setDeviceCode(0L);
                sportTotal.setSportBasicInfo(new SportBasicInfo());
                sportTotal.setGoalAchieveBasic(goalAchieveBasic);
                map.put(Integer.valueOf(c), sportTotal);
            } else {
                if (map.get(Integer.valueOf(c)).getSportBasicInfo() == null) {
                    map.get(Integer.valueOf(c)).setSportBasicInfo(new SportBasicInfo());
                }
                map.get(Integer.valueOf(c)).setGoalAchieveBasic(goalAchieveBasic);
            }
        }
    }

    public igo d(ActiveHourBasic activeHourBasic) {
        int countActiveHour = activeHourBasic.getCountActiveHour();
        if (countActiveHour <= 0) {
            return null;
        }
        igo igoVar = new igo();
        igoVar.a(countActiveHour);
        igoVar.d(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value());
        igoVar.c(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value());
        return igoVar;
    }

    public List<igo> b(List<String> list, GoalAchieveBasic goalAchieveBasic) {
        JSONObject jSONObject;
        ArrayList arrayList = new ArrayList();
        try {
            jSONObject = new JSONObject(HiJsonUtil.e(goalAchieveBasic));
        } catch (JSONException unused) {
            ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "goalAchieveBasicToLccal json ", goalAchieveBasic);
            jSONObject = null;
        }
        if (jSONObject == null) {
            return arrayList;
        }
        for (String str : list) {
            if (jSONObject.has(str)) {
                int d = HiHealthDictManager.d(this.b).d(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value(), str, 0);
                if (d == 0) {
                    ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "the key is ", str, ", and statType is not found");
                } else {
                    int c = HiHealthDictManager.d(this.b).c(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value(), str, 0);
                    if (c == 0) {
                        ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "the key is ", str, ", and hiHealthType is not found");
                    } else {
                        igo igoVar = new igo();
                        igoVar.g(1);
                        try {
                            igoVar.a(Double.valueOf(jSONObject.get(str).toString()).doubleValue());
                            igoVar.d(d);
                            igoVar.c(c);
                            arrayList.add(igoVar);
                        } catch (JSONException unused2) {
                            ReleaseLogUtil.c("HiH_HealthStatisticsSwitch", "jsonObject ", jSONObject);
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
