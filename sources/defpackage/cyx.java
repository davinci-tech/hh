package defpackage;

import com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeExtendedSubData;
import com.huawei.health.ecologydevice.fitness.factory.RopeDataFactory;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cyx implements RopeDataFactory {
    private static final Map<Integer, BaseRopeData> e;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(5, new RopeExtendedSubData());
    }

    @Override // com.huawei.health.ecologydevice.fitness.factory.RopeDataFactory
    public BaseRopeData createRopeData(int i, int i2, boolean z) {
        BaseRopeData baseRopeData = e.get(Integer.valueOf(i));
        if (z) {
            baseRopeData = e(baseRopeData);
        }
        if (baseRopeData == null) {
            return baseRopeData;
        }
        baseRopeData.setCode(i2);
        baseRopeData.setCommand(i);
        baseRopeData.setSubcontracting(true);
        baseRopeData.setRopeConfigCommand(false);
        return baseRopeData;
    }

    private BaseRopeData e(BaseRopeData baseRopeData) {
        if (baseRopeData instanceof RopeExtendedSubData) {
            return new RopeExtendedSubData();
        }
        LogUtil.a("RopeControlSubFactory", "Unmatched Type");
        return null;
    }
}
