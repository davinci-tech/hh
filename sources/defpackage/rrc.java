package defpackage;

import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;

/* loaded from: classes7.dex */
public class rrc {
    public static HiAggregateOption c(rkb rkbVar, int[] iArr, String[] strArr, int i) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        if (rkbVar != null) {
            hiAggregateOption.setReadType(rkbVar.m());
            hiAggregateOption.setDeviceUuid(rkbVar.i());
            hiAggregateOption.setStartTime(rkbVar.o());
            hiAggregateOption.setEndTime(rkbVar.h());
            hiAggregateOption.setType(iArr);
            hiAggregateOption.setConstantsKey(strArr);
            hiAggregateOption.setAggregateType(i);
            hiAggregateOption.setGroupUnitType(3);
            hiAggregateOption.setSortOrder(1);
        }
        return hiAggregateOption;
    }

    public static HiAggregateOption b(rkb rkbVar, int[] iArr, String[] strArr, int i, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        if (rkbVar != null) {
            hiAggregateOption.setReadType(rkbVar.m());
            hiAggregateOption.setDeviceUuid(rkbVar.i());
            hiAggregateOption.setStartTime(rkbVar.o());
            hiAggregateOption.setEndTime(rkbVar.h());
            hiAggregateOption.setType(iArr);
            hiAggregateOption.setConstantsKey(strArr);
            hiAggregateOption.setAggregateType(i);
            hiAggregateOption.setGroupUnitType(i2);
            hiAggregateOption.setSortOrder(1);
        }
        return hiAggregateOption;
    }

    public static HiDataAggregateProOption a(HiAggregateOption hiAggregateOption, String str) {
        return HiDataAggregateProOption.builder().c(hiAggregateOption).c(str).c();
    }

    public static HiDataReadOption b(rkb rkbVar, int[] iArr) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (rkbVar != null) {
            hiDataReadOption.setReadType(rkbVar.m());
            hiDataReadOption.setDeviceUuid(rkbVar.i());
            hiDataReadOption.setStartTime(rkbVar.o());
            hiDataReadOption.setEndTime(rkbVar.h());
            hiDataReadOption.setType(iArr);
            hiDataReadOption.setSortOrder(1);
        }
        return hiDataReadOption;
    }

    public static HiDataReadProOption b(HiDataReadOption hiDataReadOption, String str) {
        return HiDataReadProOption.builder().e(hiDataReadOption).a(str).e();
    }

    public static HiDataDeleteProOption a(HiDataDeleteOption hiDataDeleteOption, rkb rkbVar) {
        return HiDataDeleteProOption.builder().d(hiDataDeleteOption).e(Integer.valueOf(rkbVar.e())).c(0).e(rkbVar.i()).d(rkbVar.j()).d((Integer) 1).d();
    }
}
