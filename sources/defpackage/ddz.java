package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public class ddz extends HealthData {
    private short b;
    private short d;
    private short e;

    private static float d(short s) {
        float f;
        float f2;
        if (s == Short.MIN_VALUE) {
            return Float.MIN_VALUE;
        }
        if (s < 60) {
            return (s * 10.0f) / 60.0f;
        }
        if (s < 80) {
            return (((s - 60) * 10.0f) / 20.0f) + 10.0f;
        }
        if (s < 90) {
            return (((s - 80) * 10.0f) / 10.0f) + 20.0f;
        }
        if (s < 100) {
            f = ((s - 90) * 10.0f) / 10.0f;
            f2 = 30.0f;
        } else if (s < 110) {
            f = ((s - 100) * 10.0f) / 10.0f;
            f2 = 40.0f;
        } else {
            if (s >= 120) {
                return 60.0f;
            }
            f = ((s - 110) * 10.0f) / 10.0f;
            f2 = 50.0f;
        }
        return f + f2;
    }

    private static float e(short s) {
        float f;
        float f2;
        if (s == Short.MIN_VALUE) {
            return Float.MIN_VALUE;
        }
        if (s < 90) {
            return (s * 10.0f) / 90.0f;
        }
        if (s < 130) {
            return (((s - 90) * 10.0f) / 40.0f) + 10.0f;
        }
        if (s < 140) {
            return (((s - OldToNewMotionPath.SPORT_TYPE_TENNIS) * 10.0f) / 10.0f) + 20.0f;
        }
        if (s < 160) {
            f = ((s - 140) * 10.0f) / 20.0f;
            f2 = 30.0f;
        } else {
            if (s < 180) {
                return (((s - 160) * 10.0f) / 20.0f) + 40.0f;
            }
            if (s >= 200) {
                return 60.0f;
            }
            f = ((s - 180) * 10.0f) / 20.0f;
            f2 = 50.0f;
        }
        return f + f2;
    }

    private static boolean i(short s) {
        return s >= 60 && s < 80;
    }

    private static boolean j(short s) {
        return s >= 90 && s < 130;
    }

    public short a() {
        return this.e;
    }

    public void c(short s) {
        this.e = s;
    }

    public short c() {
        return this.d;
    }

    public void b(short s) {
        this.d = s;
    }

    public void a(short s) {
        this.b = s;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public String toString() {
        return "BloodPressure [state=" + ((int) this.b) + ", systolic=" + ((int) this.e) + ", diastolic=" + ((int) this.d) + super.toString() + "]";
    }

    public static float c(short s, short s2) {
        float d;
        if (s == Short.MIN_VALUE || s2 == Short.MIN_VALUE) {
            return Float.MIN_VALUE;
        }
        if (i(s2)) {
            d = e(s);
        } else if (j(s)) {
            d = d(s2);
        } else if (s2 >= 80 && s >= 130) {
            d = Math.max(e(s), d(s2));
        } else if (s2 < 60 && s < 90) {
            d = Math.min(e(s), d(s2));
        } else {
            d = (s2 >= 60 || s < 130) ? 60.0f : d(s2);
        }
        return new BigDecimal(d).setScale(2, 4).floatValue();
    }

    public static int b(short s, short s2) {
        return e(c(s, s2));
    }

    public static int e(float f) {
        if (Math.abs(f - Float.MIN_VALUE) < 1.0E-6d) {
            return -32768;
        }
        int i = 0;
        if (f >= a(0)) {
            i = 1;
            if (f >= a(1)) {
                i = 2;
                if (f >= a(2)) {
                    i = 3;
                    if (f >= a(3)) {
                        i = 4;
                        if (f >= a(4)) {
                            return 5;
                        }
                    }
                }
            }
        }
        return i;
    }

    public static float a(int i) {
        float floatValue = new BigDecimal((i + 1) * 10.0f).setScale(2, 4).floatValue();
        if (i == -32768) {
            return Float.MIN_VALUE;
        }
        return floatValue;
    }

    public void e(short s, short s2, short s3, long j) {
        LogUtil.a("BloodPressure", "BloodPressure setBloodPressureData()");
        setStartTime(j);
        setEndTime(j);
        c(s);
        b(s2);
        setReferData(Short.valueOf(s3));
        setType(HealthData.BLOODPRESURE);
        setSubType(HealthData.BLOODPRESURE);
        a((short) b(a(), c()));
    }
}
