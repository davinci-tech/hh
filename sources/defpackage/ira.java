package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.HiCommonUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class ira {
    private static final Map<Integer, String> e;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(46018, "RestingHeartRate");
        hashMap.put(47203, BleConstants.BLOOD_OXYGEN);
        hashMap.put(2034, "Stress");
        hashMap.put(44105, "AllSleepTime");
        hashMap.put(40002, "Step");
        hashMap.put(47101, "ExerciseIntensity");
        hashMap.put(40003, "Calorie");
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()), "ActiveHour");
    }

    public static int[] c(int i) {
        return i == 30029 ? new int[]{30001} : HiHealthDataType.e(i) == HiHealthDataType.Category.SET ? HiHealthDataType.d(i) : new int[]{i};
    }

    public static void bBZ_(ContentValues contentValues, String str, String str2) {
        if (contentValues == null || !contentValues.containsKey(str)) {
            return;
        }
        boolean containsKey = contentValues.containsKey(str2);
        double doubleValue = containsKey ? contentValues.getAsDouble(str2).doubleValue() : 0.0d;
        contentValues.put(str2, Double.valueOf(contentValues.getAsDouble(str).doubleValue()));
        if (containsKey) {
            contentValues.put(str, Double.valueOf(doubleValue));
        } else {
            contentValues.remove(str);
        }
    }

    public static double d(HiHealthData hiHealthData) {
        double d = hiHealthData.getDouble("protein");
        if (d != 0.0d) {
            return d;
        }
        double d2 = hiHealthData.getDouble("bodyWeight");
        double d3 = hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
        double d4 = hiHealthData.getDouble(BleConstants.BONE_SALT);
        double d5 = hiHealthData.getDouble(BleConstants.MOISTURE);
        if (d3 == 0.0d || d4 == 0.0d || d5 == 0.0d) {
            return 0.0d;
        }
        return HiCommonUtil.b(d2, d3, d5, d4);
    }

    public static List<String> c(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            Map<Integer, String> map = e;
            if (map.containsKey(Integer.valueOf(i))) {
                arrayList.add(map.get(Integer.valueOf(i)));
            }
        }
        LogUtil.d("HiHealthKitDataConverter", "trendDataType2FieldName result: ", HiJsonUtil.e(arrayList));
        return arrayList;
    }

    public static Set<Integer> d() {
        return Collections.unmodifiableSet(e.keySet());
    }
}
