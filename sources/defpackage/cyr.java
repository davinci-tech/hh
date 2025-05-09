package defpackage;

import com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData;
import com.huawei.health.ecologydevice.fitness.datastruct.ExclusivePlayListData;
import com.huawei.health.ecologydevice.fitness.datastruct.LogOperationsData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeModeSettingData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeVoiceCourseOperationsData;
import com.huawei.health.ecologydevice.fitness.datastruct.SettingStatusData;
import com.huawei.health.ecologydevice.fitness.datastruct.SwitchStatusData;
import com.huawei.health.ecologydevice.fitness.datastruct.VideoCourseOperationsData;
import com.huawei.health.ecologydevice.fitness.factory.RopeDataFactory;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cyr implements RopeDataFactory {
    private static final Map<Integer, BaseRopeData> b;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(1, new SwitchStatusData());
        hashMap.put(2, new SettingStatusData());
        hashMap.put(3, new RopeModeSettingData());
        hashMap.put(4, new RopeVoiceCourseOperationsData());
        hashMap.put(5, new VideoCourseOperationsData());
        hashMap.put(6, new LogOperationsData());
        hashMap.put(7, new ExclusivePlayListData());
    }

    @Override // com.huawei.health.ecologydevice.fitness.factory.RopeDataFactory
    public BaseRopeData createRopeData(int i, int i2, boolean z) {
        BaseRopeData baseRopeData = b.get(Integer.valueOf(i));
        if (z) {
            baseRopeData = b(baseRopeData);
        }
        if (baseRopeData == null) {
            return baseRopeData;
        }
        baseRopeData.setCode(i2);
        baseRopeData.setCommand(i);
        baseRopeData.setSubcontracting(true);
        baseRopeData.setRopeConfigCommand(true);
        return baseRopeData;
    }

    private BaseRopeData b(BaseRopeData baseRopeData) {
        if (baseRopeData instanceof SwitchStatusData) {
            return new SwitchStatusData();
        }
        if (baseRopeData instanceof SettingStatusData) {
            return new SettingStatusData();
        }
        if (baseRopeData instanceof RopeModeSettingData) {
            return new RopeModeSettingData();
        }
        if (baseRopeData instanceof RopeVoiceCourseOperationsData) {
            return new RopeVoiceCourseOperationsData();
        }
        if (baseRopeData instanceof VideoCourseOperationsData) {
            return new VideoCourseOperationsData();
        }
        if (baseRopeData instanceof LogOperationsData) {
            return new LogOperationsData();
        }
        if (baseRopeData instanceof ExclusivePlayListData) {
            return new ExclusivePlayListData();
        }
        LogUtil.a("RopeConfigSubFactory", "Unmatched Type");
        return null;
    }
}
