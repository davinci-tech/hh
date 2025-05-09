package defpackage;

import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class qrc {
    public static qkg d(List<qkg> list, long j) {
        if (koq.b(list)) {
            return new qkg();
        }
        int i = 0;
        if (list.size() <= 1) {
            return list.get(0);
        }
        int size = list.size() - 1;
        boolean z = list.get(0).h() - list.get(size).h() >= 0;
        while (size != i + 1) {
            int i2 = (i + size) / 2;
            qkg qkgVar = list.get(i2);
            long h = qkgVar.h();
            if (h == j) {
                return qkgVar;
            }
            if (h > j) {
                if (z) {
                    i = i2;
                } else {
                    size = i2;
                }
            } else if (z) {
                size = i2;
            } else {
                i = i2;
            }
        }
        if (Math.abs(j - list.get(i).h()) < Math.abs(list.get(size).h() - j)) {
            return list.get(i);
        }
        return list.get(size);
    }

    public static void a() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(cpp.a()).synCloud(hiSyncOption, null);
        LogUtil.d("HealthDataUtils", "sync cloud over");
    }
}
