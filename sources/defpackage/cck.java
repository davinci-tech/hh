package defpackage;

import com.huawei.health.trusport_ee.Activity;
import com.huawei.health.trusport_ee.Intensity;
import com.huawei.health.trusport_ee.Sex;

/* loaded from: classes3.dex */
public class cck {
    public static boolean e(int i) {
        return i >= 40 && i <= 250;
    }

    public static Activity d(int i) {
        Activity activity = Activity.TS_ACTIVITY_UNKNOWN;
        if (i == -1) {
            return Activity.TS_ACTIVITY_IDLE;
        }
        if (i == 264) {
            return Activity.TS_ACTIVITY_INDOOR_RUN;
        }
        if (i == 265) {
            return Activity.TS_ACTIVITY_INDOOR_CYCLE;
        }
        if (i != 281) {
            if (i != 282) {
                switch (i) {
                    case 257:
                        break;
                    case 258:
                        return Activity.TS_ACTIVITY_OUTDOOR_RUN;
                    case 259:
                        return Activity.TS_ACTIVITY_OUTDOOR_CYCLE;
                    case 260:
                        return Activity.TS_ACTIVITY_MOUNTAIN_HIKE;
                    case 261:
                        return Activity.TS_ACTIVITY_STAIR_CLIMBING;
                    default:
                        return activity;
                }
            }
            return Activity.TS_ACTIVITY_OUTDOOR_WALK;
        }
        return Activity.TS_ACTIVITY_INDOOR_WALK;
    }

    public static Intensity b(int i) {
        Intensity intensity = Intensity.LOW;
        if (i == 0) {
            return Intensity.LOW;
        }
        if (i != 1) {
            return i != 2 ? intensity : Intensity.HIGH;
        }
        return Intensity.MEDIUM;
    }

    public static Sex c(int i) {
        if (i == 1) {
            return Sex.MALE;
        }
        if (i == 0) {
            return Sex.FEMALE;
        }
        return Sex.UNKNOWN;
    }

    public static boolean a(float f, Activity activity) {
        return (activity == Activity.TS_ACTIVITY_INDOOR_WALK || activity == Activity.TS_ACTIVITY_OUTDOOR_WALK || activity == Activity.TS_ACTIVITY_INDOOR_RUN || activity == Activity.TS_ACTIVITY_OUTDOOR_RUN) ? Float.compare(f, 0.0f) >= 0 && Float.compare(f, 12.0f) <= 0 : Float.compare(f, 0.0f) >= 0 && Float.compare(f, 27.0f) <= 0;
    }

    public static boolean a(float f) {
        return Float.compare(f, 0.0f) >= 0 && Float.compare(f, 4.0f) <= 0;
    }
}
