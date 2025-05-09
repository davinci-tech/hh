package defpackage;

import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hihealth.data.type.HiHealthDataType;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iwq {
    public static void b(HiDataDeleteOption hiDataDeleteOption, ikv ikvVar) throws iwt {
        if (hiDataDeleteOption == null) {
            throw new iwt("validDeleteOption deleteOption is null");
        }
        a(hiDataDeleteOption.getTimes());
        b(hiDataDeleteOption.getTypes());
        b(ikvVar);
    }

    private static void b(ikv ikvVar) throws iwt {
        int g = ikvVar.g();
        if (g <= 0) {
            ReleaseLogUtil.d("HiH_HiDataDeleteValid", "deleteHealthData() who <= 0");
            throw new iwt("deleteHealthData() who <= 0");
        }
        if (HiCommonUtil.d(ikvVar.c())) {
            ReleaseLogUtil.d("HiH_HiDataDeleteValid", "deleteHealthData() clientIDs is null who = ", Integer.valueOf(g));
            throw new iwt("deleteHealthData() clientIDs is null");
        }
    }

    private static void b(int[] iArr) throws iwt {
        if (iArr == null || iArr.length <= 0) {
            throw new iwt("validType types is null");
        }
        HiHealthDataType.Category e = HiHealthDataType.e(iArr[0]);
        for (int i : iArr) {
            if (!HiHealthDataType.f(i) && !HiConfigDataType.j(i)) {
                throw new iwt("validType types canot delete type = " + i);
            }
            if (e != HiHealthDataType.e(i)) {
                throw new iwt("validType types is not same category type = " + i);
            }
        }
    }

    private static void a(List<HiTimeInterval> list) throws iwt {
        if (list == null || list.isEmpty()) {
            throw new iwt("validTimes times is null or empty");
        }
        for (HiTimeInterval hiTimeInterval : list) {
            long startTime = hiTimeInterval.getStartTime();
            long endTime = hiTimeInterval.getEndTime();
            if (startTime > endTime) {
                throw new iwt("validTimes startTime > endTime startTime = " + startTime + ",endTime = " + endTime);
            }
        }
    }
}
