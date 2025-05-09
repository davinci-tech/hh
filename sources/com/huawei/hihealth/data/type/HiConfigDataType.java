package com.huawei.hihealth.data.type;

import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class HiConfigDataType {
    private static final Map<Integer, Integer> b;
    private static final Object[][] d;
    private static final Map<Integer, Integer> e;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        HashMap hashMap2 = new HashMap();
        b = hashMap2;
        Integer valueOf = Integer.valueOf(SmartMsgConstant.MSG_TYPE_TOLD_USER_MEASURE_BLOOD_SUGAR);
        d = new Object[][]{new Object[]{valueOf, 0, 1, null, null, new int[]{SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_RECOMMEND}, new int[]{2}, 3, true}, new Object[]{70002, 2, 1, null, null, null, null, null}, new Object[]{70003, 0, 1, null, null, new int[]{SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_WEEK_DETAIL}, 1, 3, false}};
        hashMap.put(valueOf, 0);
        hashMap.put(70002, 1);
        hashMap.put(70003, 2);
        hashMap2.put(Integer.valueOf(SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_RECOMMEND), 0);
        hashMap2.put(Integer.valueOf(SmartMsgConstant.MSG_TYPE_SLEEP_SERVICE_CORE_SLEEP_WEEK_DETAIL), 2);
    }

    private HiConfigDataType() {
    }

    public static boolean j(int i) {
        Map<Integer, Integer> map = e;
        if (!map.containsKey(Integer.valueOf(i))) {
            return false;
        }
        Boolean bool = (Boolean) d[map.get(Integer.valueOf(i)).intValue()][8];
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static int[] b(int i) {
        Map<Integer, Integer> map = e;
        if (!map.containsKey(Integer.valueOf(i))) {
            return null;
        }
        int[] iArr = (int[]) d[map.get(Integer.valueOf(i)).intValue()][5];
        if (iArr == null) {
            return null;
        }
        return iArr;
    }

    public static boolean g(int i) {
        return b.containsKey(Integer.valueOf(i));
    }

    public static int a(int i) {
        Object[] objArr = d[b.get(Integer.valueOf(i)).intValue()];
        int[] iArr = (int[]) objArr[5];
        int[] iArr2 = (int[]) objArr[6];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i == iArr[i2]) {
                return iArr2[i2];
            }
        }
        return -1;
    }

    public static Object d(int i, int i2) {
        return d[i][i2];
    }

    public static Object[] c(int i) {
        return d[i];
    }

    public static Integer e(int i) {
        return e.get(Integer.valueOf(i));
    }

    public static boolean i(int i) {
        return e.containsKey(Integer.valueOf(i));
    }

    public static Integer d(int i) {
        return b.get(Integer.valueOf(i));
    }

    public static boolean f(int i) {
        return b.containsKey(Integer.valueOf(i));
    }
}
