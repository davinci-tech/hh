package defpackage;

import com.huawei.pluginfitnessadvice.RoadBook;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class fop {
    private static RoadBook[] b;
    private float c;
    private int d;
    private float e = 1.0f;

    public static void b(List<RoadBook> list) {
        if (list == null) {
            return;
        }
        int a2 = a(list) + 1;
        LogUtil.d("Suggestion_CourseEquipmentSpeedSmoothHelper", "setRoadBooks:", Integer.valueOf(list.size()), " arrayNum:", Integer.valueOf(a2));
        b = new RoadBook[a2];
        for (RoadBook roadBook : list) {
            if (roadBook != null && roadBook.getDuration() >= 0) {
                b[roadBook.getDuration()] = roadBook;
            }
        }
    }

    public static RoadBook[] e() {
        return b;
    }

    public static void a() {
        b = null;
    }

    private static int a(List<RoadBook> list) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        for (RoadBook roadBook : list) {
            if (roadBook.getDuration() > i) {
                i = roadBook.getDuration();
            }
        }
        return i;
    }

    public float c(int i, int i2) {
        d(i);
        c(i2);
        return b(this.e);
    }

    private void d(int i) {
        RoadBook a2 = a(i);
        if (a2 != null) {
            this.d = a2.getSpeed();
        }
        if (this.c == 0.0f) {
            this.c = this.d;
        }
        int i2 = this.d;
        if (i2 != 0) {
            this.c = (this.c * 0.9f) + (i2 * 0.1f);
        } else {
            this.c = 0.0f;
        }
        LogUtil.d("Suggestion_CourseEquipmentSpeedSmoothHelper", "currentSecond:", Integer.valueOf(i), " currentRoadBook speed:", Integer.valueOf(this.d));
    }

    private void c(int i) {
        float f = this.c;
        if (f == 0.0f) {
            this.e = 1.0f;
            LogUtil.d("Suggestion_CourseEquipmentSpeedSmoothHelper", "mSmoothSpeed:", Float.valueOf(f), " currentSpeed:", Integer.valueOf(i), " mPlaySpeedLevel:", Float.valueOf(this.e));
        } else {
            float f2 = i / f;
            this.e = (this.e * 0.7f) + (0.3f * f2);
            LogUtil.d("Suggestion_CourseEquipmentSpeedSmoothHelper", "mSmoothSpeed:", Float.valueOf(f), " currentSpeed:", Integer.valueOf(i), " tempPlaySpeedLevel:", Float.valueOf(f2), " mPlaySpeedLevel:", Float.valueOf(this.e));
        }
    }

    private RoadBook a(int i) {
        RoadBook roadBook = null;
        if (b.length <= i) {
            return null;
        }
        while (i >= 0) {
            roadBook = b[i];
            if (roadBook != null) {
                break;
            }
            i--;
        }
        return roadBook;
    }

    private float b(float f) {
        if (f < 0.2f) {
            return 0.0f;
        }
        if (f <= 0.5f) {
            return 0.5f;
        }
        if (f <= 2.0f) {
            return Math.round(f * 10.0f) / 10.0f;
        }
        return 2.0f;
    }
}
