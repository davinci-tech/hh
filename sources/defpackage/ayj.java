package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class ayj {

    @SerializedName("enabled")
    private final boolean d;

    @SerializedName("alarmInfoList")
    private final List<d> e;

    public ayj(boolean z, List<d> list) {
        this.d = z;
        this.e = list;
    }

    public boolean c() {
        return this.d;
    }

    public List<d> b() {
        return this.e;
    }

    public String toString() {
        return "HealthModeReminderCloudBean{mIsEnabled=" + this.d + ", mCloudReminderBeanList=" + this.e + '}';
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("minute")
        private final int f285a;

        @SerializedName("hour")
        private final int b;

        public d(int i, int i2) {
            this.f285a = i2;
            this.b = i;
        }

        public int c() {
            return this.f285a;
        }

        public int a() {
            return this.b;
        }

        public String toString() {
            return "CloudReminderBean{mMinute=" + this.f285a + ", mHour=" + this.b + '}';
        }
    }
}
