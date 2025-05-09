package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwcloudmodel.model.unite.SportTotal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HiValidatorUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class isw {

    static class a {
        private static final isw e = new isw();
    }

    public static isw e() {
        return a.e;
    }

    public List<SportTotal> d(List<HiHealthData> list, Map<Integer, SportTotal> map) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            c(arrayList, hiHealthData, HiDateUtil.c(hiHealthData.getStartTime()), 2, hiHealthData.getTimeZone(), map);
        }
        return arrayList;
    }

    public List<SportTotal> e(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            ikv f = iis.d().f(hiHealthData.getClientId());
            if (f == null) {
                return null;
            }
            int i = (int) hiHealthData.getDouble("deviceStepStat");
            int i2 = (int) hiHealthData.getDouble("deviceCalorieStat");
            float f2 = (float) hiHealthData.getDouble("deviceAltitudeStat");
            if (!HiValidatorUtil.a(i) || !HiValidatorUtil.e(i2) || !HiValidatorUtil.e(f2)) {
                ReleaseLogUtil.c("HiH_SportDataSwitch", "localDeviceTotalStatToCloud out of range hiHealthData = ", HiJsonUtil.e(hiHealthData));
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put("errorCode", Integer.toString(-1));
                linkedHashMap.put("data", HiJsonUtil.e(hiHealthData));
                ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_BIG_DATA_ERROR_2129012.value(), linkedHashMap, true);
            } else {
                SportBasicInfo sportBasicInfo = new SportBasicInfo();
                sportBasicInfo.configSteps(i);
                sportBasicInfo.configCalorie(i2);
                sportBasicInfo.configDistance((int) hiHealthData.getDouble("deviceDistanceStat"));
                sportBasicInfo.configAltitude(f2);
                sportBasicInfo.configDuration((int) hiHealthData.getDouble("deviceDurationStat"));
                sportBasicInfo.configFloor((int) hiHealthData.getDouble("deviceFloorStat"));
                SportTotal sportTotal = new SportTotal();
                sportTotal.setDataId(Long.valueOf(hiHealthData.getDataId()));
                sportTotal.setSportBasicInfo(sportBasicInfo);
                sportTotal.setRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
                sportTotal.setTimeZone(hiHealthData.getTimeZone());
                sportTotal.setSportType(0);
                sportTotal.setDeviceCode(Long.valueOf(f.a()));
                if (!a(arrayList, sportTotal)) {
                    arrayList.add(sportTotal);
                }
            }
        }
        return arrayList;
    }

    private boolean a(List<SportTotal> list, SportTotal sportTotal) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        for (SportTotal sportTotal2 : list) {
            if (sportTotal2.getDeviceCode().equals(sportTotal.getDeviceCode()) && sportTotal2.getRecordDay().equals(sportTotal.getRecordDay()) && sportTotal2.getSportType().equals(sportTotal.getSportType())) {
                LogUtil.c("Debug_SportDataSwitch", "hasSameDeviceCode sportTotal=", sportTotal.toString());
                SportBasicInfo sportBasicInfo = sportTotal2.getSportBasicInfo();
                SportBasicInfo sportBasicInfo2 = sportTotal.getSportBasicInfo();
                if (sportBasicInfo.fetchSteps() >= sportBasicInfo2.fetchSteps()) {
                    return true;
                }
                sportBasicInfo.configSteps(sportBasicInfo2.fetchSteps());
                return true;
            }
        }
        return false;
    }

    private void c(List<SportTotal> list, HiHealthData hiHealthData, int i, int i2, String str, Map<Integer, SportTotal> map) {
        SportTotal sportTotal = new SportTotal();
        int i3 = (int) hiHealthData.getDouble("sport_step_sum");
        int i4 = (int) hiHealthData.getDouble("sport_distance_sum");
        int i5 = (int) hiHealthData.getDouble("sport_calorie_sum");
        int i6 = (int) hiHealthData.getDouble("sport_duration_sum");
        float f = (float) hiHealthData.getDouble("sport_altitude_offset_sum");
        if (!HiValidatorUtil.a(i3) || !HiValidatorUtil.e(i5) || !HiValidatorUtil.e(f)) {
            ReleaseLogUtil.c("HiH_SportDataSwitch", "addAllTotal STEPS or CALORIE or altitude is out of range hiHealthData = ", hiHealthData);
            return;
        }
        if (HiDateUtil.c(System.currentTimeMillis()) == i) {
            ReleaseLogUtil.e("HiH_SportDataSwitch", "addAllTotal date=", Integer.valueOf(i), ": s=", Integer.valueOf(i3), ", d=", Integer.valueOf(i4), ", c=", Integer.valueOf(i5));
        }
        SportBasicInfo e = iup.e(Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Float.valueOf(f / 10.0f), 0, Integer.valueOf(i6 / 60), 0);
        sportTotal.setDataId(Long.valueOf(hiHealthData.getDataId()));
        sportTotal.setSportBasicInfo(e);
        sportTotal.setRecordDay(Integer.valueOf(i));
        sportTotal.setDataSource(Integer.valueOf(i2));
        sportTotal.setTimeZone(str);
        sportTotal.setSportType(0);
        map.put(Integer.valueOf(i), sportTotal);
        list.add(sportTotal);
    }
}
