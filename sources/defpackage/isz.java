package defpackage;

import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiValidatorUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class isz {
    public static List<igo> c(SportBasicInfo sportBasicInfo, igo igoVar) {
        ArrayList arrayList = new ArrayList(3);
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        if (!HiValidatorUtil.a(fetchSteps)) {
            LogUtil.b("Debug_SportDataSwitch", "getSwimStatTable STEP is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
            return null;
        }
        if (HiValidatorUtil.b(fetchDistance)) {
            arrayList.add(igoVar.a(40035, fetchDistance, 2));
        }
        if (HiValidatorUtil.b(fetchDuration)) {
            arrayList.add(igoVar.a(40045, fetchDuration * 60, 13));
        }
        if (HiValidatorUtil.b(fetchCalorie)) {
            arrayList.add(igoVar.a(40025, fetchCalorie, 3));
        }
        return arrayList;
    }

    public static List<igo> b(SportBasicInfo sportBasicInfo, igo igoVar) {
        ArrayList arrayList = new ArrayList(4);
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        if (!HiValidatorUtil.a(fetchSteps)) {
            LogUtil.b("Debug_SportDataSwitch", "getWalkStatTable STEP is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
            return null;
        }
        if (HiValidatorUtil.b(fetchSteps)) {
            arrayList.add(igoVar.a(40011, fetchSteps, 1));
        }
        if (HiValidatorUtil.b(fetchDistance)) {
            arrayList.add(igoVar.a(40031, fetchDistance, 2));
        }
        if (HiValidatorUtil.b(fetchDuration)) {
            arrayList.add(igoVar.a(40041, fetchDuration * 60, 13));
        }
        if (HiValidatorUtil.b(fetchCalorie)) {
            arrayList.add(igoVar.a(40021, fetchCalorie, 3));
        }
        return arrayList;
    }

    public static List<igo> e(SportBasicInfo sportBasicInfo, igo igoVar) {
        ArrayList arrayList = new ArrayList(4);
        int fetchSteps = sportBasicInfo.fetchSteps();
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        if (!HiValidatorUtil.a(fetchSteps)) {
            LogUtil.b("Debug_SportDataSwitch", "getRunStatTable STEP is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
            return null;
        }
        if (HiValidatorUtil.b(fetchSteps)) {
            arrayList.add(igoVar.a(40012, fetchSteps, 1));
        }
        if (HiValidatorUtil.b(fetchDistance)) {
            arrayList.add(igoVar.a(40032, fetchDistance, 2));
        }
        if (HiValidatorUtil.b(fetchDuration)) {
            arrayList.add(igoVar.a(40042, fetchDuration * 60, 13));
        }
        if (HiValidatorUtil.b(fetchCalorie)) {
            arrayList.add(igoVar.a(40022, fetchCalorie, 3));
        }
        return arrayList;
    }

    public static List<igo> a(SportBasicInfo sportBasicInfo, igo igoVar) {
        ArrayList arrayList = new ArrayList(3);
        int fetchDistance = sportBasicInfo.fetchDistance();
        int fetchCalorie = sportBasicInfo.fetchCalorie();
        int fetchDuration = sportBasicInfo.fetchDuration();
        if (!HiValidatorUtil.e(fetchCalorie)) {
            LogUtil.b("Debug_SportDataSwitch", "getRideStatTable calorie is out of rang sportBasicInfo = ", HiJsonUtil.e(sportBasicInfo));
            return null;
        }
        if (HiValidatorUtil.b(fetchDistance)) {
            arrayList.add(igoVar.a(40033, fetchDistance, 2));
        }
        if (HiValidatorUtil.b(fetchDuration)) {
            arrayList.add(igoVar.a(40043, fetchDuration * 60, 13));
        }
        if (HiValidatorUtil.b(fetchCalorie)) {
            arrayList.add(igoVar.a(40023, fetchCalorie, 3));
        }
        return arrayList;
    }
}
