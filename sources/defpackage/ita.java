package defpackage;

import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import health.compact.a.HiDateUtil;
import health.compact.a.HiValidatorUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ita {
    public static List<igo> d(SportBasicInfo sportBasicInfo, igo igoVar) {
        ArrayList arrayList = new ArrayList(5);
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        float fetchAltitude = sportBasicInfo.fetchAltitude() * 10.0f;
        if (HiValidatorUtil.a(fetchSteps)) {
            double d = fetchAltitude;
            if (HiValidatorUtil.e(d)) {
                if (HiValidatorUtil.b(fetchDistance)) {
                    arrayList.add(igoVar.a(40034, fetchDistance, 2));
                }
                if (HiValidatorUtil.b(fetchDuration)) {
                    arrayList.add(igoVar.a(40044, fetchDuration * 60, 13));
                }
                if (HiValidatorUtil.b(fetchSteps)) {
                    arrayList.add(igoVar.a(40013, fetchSteps, 1));
                }
                if (HiValidatorUtil.b(fetchCalorie)) {
                    arrayList.add(igoVar.a(40024, fetchCalorie, 3));
                }
                if (HiValidatorUtil.e(fetchAltitude)) {
                    arrayList.add(igoVar.a(SmartMsgConstant.MSG_TYPE_RIDE_USER, d, 4));
                }
                return arrayList;
            }
        }
        LogUtil.b("Debug_SportDataSwitch", "getClimbStatTable STEP is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
        return null;
    }

    public static List<igo> e(SportBasicInfo sportBasicInfo, igo igoVar) {
        ArrayList arrayList = new ArrayList(5);
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        float fetchFloor = sportBasicInfo.fetchFloor() * 30;
        if (HiValidatorUtil.a(fetchSteps)) {
            double d = fetchFloor;
            if (HiValidatorUtil.e(d)) {
                if (HiValidatorUtil.b(fetchSteps)) {
                    arrayList.add(igoVar.a(40013, fetchSteps, 1));
                }
                if (HiValidatorUtil.b(fetchDistance)) {
                    arrayList.add(igoVar.a(40034, fetchDistance, 2));
                }
                if (HiValidatorUtil.b(fetchDuration)) {
                    arrayList.add(igoVar.a(40044, fetchDuration * 60, 13));
                }
                if (HiValidatorUtil.b(fetchCalorie)) {
                    arrayList.add(igoVar.a(40024, fetchCalorie, 3));
                }
                if (HiValidatorUtil.e(fetchFloor)) {
                    arrayList.add(igoVar.a(SmartMsgConstant.MSG_TYPE_RIDE_USER, d, 4));
                }
                return arrayList;
            }
        }
        LogUtil.b("Debug_SportDataSwitch", "getStairStatTable STEP is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
        return null;
    }

    public static List<igo> e(SportBasicInfo sportBasicInfo, igo igoVar, int i) {
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        float fetchAltitude = (sportBasicInfo.fetchAltitude() * 10.0f) + (sportBasicInfo.fetchFloor() * 30);
        if (HiValidatorUtil.a(fetchSteps) && HiValidatorUtil.e(fetchCalorie)) {
            double d = fetchAltitude;
            if (HiValidatorUtil.e(d)) {
                ArrayList arrayList = new ArrayList(5);
                if (HiDateUtil.c(System.currentTimeMillis()) == i) {
                    LogUtil.a("HiH_SportDataSwitch", "getSportStatTable date=", Integer.valueOf(i), ": s=", Integer.valueOf(fetchSteps), ", d=", Integer.valueOf(fetchDistance), ", c=", Integer.valueOf(fetchCalorie));
                }
                if (HiValidatorUtil.b(fetchSteps)) {
                    arrayList.add(igoVar.a(40002, fetchSteps, 1));
                }
                if (HiValidatorUtil.b(fetchDistance)) {
                    arrayList.add(igoVar.a(40004, fetchDistance, 2));
                }
                if (HiValidatorUtil.b(fetchDuration)) {
                    arrayList.add(igoVar.a(SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER, fetchDuration * 60, 13));
                }
                if (HiValidatorUtil.b(fetchCalorie)) {
                    arrayList.add(igoVar.a(40003, fetchCalorie, 3));
                }
                if (HiValidatorUtil.e(fetchAltitude)) {
                    arrayList.add(igoVar.a(SmartMsgConstant.MSG_TYPE_RIDE_USER, d, 4));
                }
                return arrayList;
            }
        }
        LogUtil.b("HiH_SportDataSwitch", "getSportStatTable STEP OR CALORIE or altitude is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
        return null;
    }
}
