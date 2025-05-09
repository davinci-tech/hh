package defpackage;

import com.amap.api.services.core.AMapException;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class irj {
    private static final Map<Integer, Integer> c;

    static {
        HashMap hashMap = new HashMap(16);
        c = hashMap;
        hashMap.put(Integer.valueOf(AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR), 0);
        hashMap.put(2002, 0);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_SCORE.value()), 0);
        hashMap.put(50001, 0);
        hashMap.put(2104, 3);
        hashMap.put(Integer.valueOf(HiHealthDataType.b), 3);
        hashMap.put(2103, 3);
        hashMap.put(2109, 3);
    }

    public static int a(int i) {
        Map<Integer, Integer> map = c;
        if (map.containsKey(Integer.valueOf(i))) {
            return map.get(Integer.valueOf(i)).intValue();
        }
        return -1;
    }
}
