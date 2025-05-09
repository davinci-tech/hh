package defpackage;

import com.huawei.hihealth.HiHealthData;
import java.util.List;

/* loaded from: classes7.dex */
public class iwc {
    public static long b(List<HiHealthData> list, HiHealthData hiHealthData) {
        long j = 0;
        if (list != null) {
            for (HiHealthData hiHealthData2 : list) {
                if (hiHealthData2.getModifiedTime() >= j) {
                    j = hiHealthData2.getModifiedTime();
                }
            }
            j++;
        }
        return (j >= 1388509200000L || hiHealthData.getStartTime() >= System.currentTimeMillis()) ? j : hiHealthData.getStartTime();
    }
}
