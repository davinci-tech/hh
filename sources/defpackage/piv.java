package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.utils.FoundationCommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class piv extends piu {
    private static final List<Integer> b;
    private static final List<Integer> d;
    private static final List<Integer> e;

    static {
        ArrayList arrayList = new ArrayList();
        e = arrayList;
        ArrayList arrayList2 = new ArrayList();
        d = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        b = arrayList3;
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value()));
        arrayList2.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value()));
        arrayList3.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value()));
        arrayList2.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value()));
        arrayList3.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value()));
        arrayList2.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value()));
        arrayList3.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value()));
    }

    @Override // defpackage.piu
    protected List<Integer> c() {
        return e;
    }

    @Override // defpackage.piu
    protected List<Integer> b() {
        return d;
    }

    @Override // defpackage.piu
    protected List<Integer> e() {
        return b;
    }

    @Override // defpackage.piu
    protected void d() {
        if (this.c.size() == 4) {
            this.c.remove(0);
        }
    }

    @Override // defpackage.piu
    protected HiHealthData a(HiHealthData hiHealthData) {
        HiHealthData hiHealthData2 = new HiHealthData(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value());
        hiHealthData2.setStartTime(hiHealthData.getStartTime());
        hiHealthData2.setEndTime(hiHealthData.getEndTime());
        hiHealthData2.setDeviceUuid(FoundationCommonUtil.getAndroidId(BaseApplication.e()));
        hiHealthData2.setValue(1);
        return hiHealthData2;
    }

    @Override // defpackage.piu
    protected int a() {
        return DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value();
    }
}
