package com.huawei.health.manager.util;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MotionType {
    private static final Map<Integer, Integer> d;

    public static int b(int i) {
        switch (i) {
            case 20002:
                return 4;
            case 20003:
                return 3;
            case 20004:
                return 6;
            case 20005:
                return 2;
            default:
                return 0;
        }
    }

    public static int c(int i, int i2) {
        if (i == 2) {
            return 20005;
        }
        if (i == 3) {
            return 20003;
        }
        if (i == 4) {
            return 20002;
        }
        if (i != 6) {
            return i2;
        }
        return 20004;
    }

    static {
        HashMap hashMap = new HashMap(16);
        d = hashMap;
        hashMap.put(2, 1);
        hashMap.put(6, 2);
        hashMap.put(3, 3);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(0, 6);
        hashMap.put(1, 7);
    }

    private MotionType() {
    }

    public static int b(int i, int i2) {
        Map<Integer, Integer> map = d;
        Integer num = map.get(Integer.valueOf(i));
        Integer num2 = map.get(Integer.valueOf(i2));
        if (num != null) {
            return (num2 != null && num.intValue() > num2.intValue()) ? i2 : i;
        }
        if (num2 != null) {
            return i2;
        }
        return 0;
    }

    public static int a(int i) {
        return c(i, 20002);
    }
}
