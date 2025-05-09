package com.huawei.health.manager.util;

import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes.dex */
public class HiHealthHelper {
    private HiHealthHelper() {
    }

    public static HiHealthData b(int i, int i2, String str) {
        if (str == null) {
            LogUtil.b("Step_HiHealthHelper", "deviceUuid is null");
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i2);
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setTimeInterval(TimeUtil.d(System.currentTimeMillis()), System.currentTimeMillis());
        hiHealthData.setValue(i);
        return hiHealthData;
    }

    public static HiAggregateOption b() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        int b = DateFormatUtil.b(System.currentTimeMillis());
        hiAggregateOption.setTimeInterval(String.valueOf(b), String.valueOf(b), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(new int[]{40011, 40012, 40013, 40002, 40003, SmartMsgConstant.MSG_TYPE_RIDE_USER, 40004, 47101, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()});
        hiAggregateOption.setConstantsKey(new String[]{"walk_step", "run_step", "climb_step", ParsedFieldTag.STEP_SUM, "calorie_sum", "altitude_sum", "distance_sum", "intensity_time", "active_hours"});
        LogUtil.c("Step_HiHealthHelper", "syncStepsWithHiHealth...");
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    public static HiDataInsertOption b(List<HiHealthData> list) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list);
        return hiDataInsertOption;
    }

    public static int c(int i, int i2) {
        int i3 = (i != 0 && i2 >= i) ? 1 : 0;
        LogUtil.a("Step_HiHealthHelper", "getGoalState  currentValue= ", Integer.valueOf(i2), ",goalValue=", Integer.valueOf(i), ",resultState=", Integer.valueOf(i3));
        return i3;
    }
}
