package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mhv {
    public static Map<Integer, Float> c(List<HiHealthData> list) {
        Map<Integer, Float> hashMap = new HashMap<>(16);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_StepRule", "computeEverySeasonStep stepList is empty");
            return hashMap;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            hashMap = d(it.next(), hashMap);
        }
        return hashMap;
    }

    private static Map<Integer, Float> d(HiHealthData hiHealthData, Map<Integer, Float> map) {
        if (hiHealthData == null) {
            return map;
        }
        long startTime = hiHealthData.getStartTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        int i = calendar.get(2);
        int i2 = i == 0 ? 4 : (i + 2) / 3;
        float f = hiHealthData.getFloat("sum");
        if (map.containsKey(Integer.valueOf(i2))) {
            f += map.get(Integer.valueOf(i2)).floatValue();
        }
        map.put(Integer.valueOf(i2), Float.valueOf(f));
        return map;
    }

    public static int b(Map<Integer, Float> map) {
        int i = 0;
        if (map != null && map.size() != 0) {
            float f = 0.0f;
            for (Map.Entry<Integer, Float> entry : map.entrySet()) {
                float floatValue = entry.getValue().floatValue();
                if (floatValue > f) {
                    i = entry.getKey().intValue();
                    f = floatValue;
                }
            }
        }
        return i;
    }
}
