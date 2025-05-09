package com.huawei.threecircle;

import health.compact.a.SharedPreferenceManager;

/* loaded from: classes.dex */
public class ThreeCircleConfigUtil {
    private static boolean b;

    public enum ThreeCircleConfig {
        STEP(0),
        CALORIE(1),
        INTENSITY(1),
        ACTIVE_HOUR(1);

        private final int mIsJoin;

        ThreeCircleConfig(int i) {
            this.mIsJoin = i;
        }

        public int getType() {
            return this.mIsJoin;
        }
    }

    public static boolean d(ThreeCircleConfig threeCircleConfig) {
        for (ThreeCircleConfig threeCircleConfig2 : ThreeCircleConfig.values()) {
            if (threeCircleConfig2 == threeCircleConfig) {
                return threeCircleConfig2.mIsJoin == 1;
            }
        }
        return false;
    }

    public static void b() {
        SharedPreferenceManager.e(Integer.toString(10000), "NEW_THREE_LEAF_CIRCLE_GUIDE_SHOWN", true);
    }

    public static boolean c() {
        return SharedPreferenceManager.a(Integer.toString(10000), "NEW_THREE_LEAF_CIRCLE_GUIDE_SHOWN", false);
    }

    public static boolean d() {
        return b;
    }

    public static void b(boolean z) {
        b = z;
    }
}
