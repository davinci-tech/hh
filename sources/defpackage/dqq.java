package defpackage;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class dqq {

    @SerializedName("thresholdList")
    protected List<a> c;

    @SerializedName("type")
    protected String e;

    public String d() {
        return this.e;
    }

    public List<a> a() {
        return this.c;
    }

    public String toString() {
        return "DynamicBloodPressureFeatureInfo{type='" + this.e + "', thresholdList=" + this.c + '}';
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("dia")
        protected List<Integer> f11791a;

        @SerializedName(NotificationCompat.CATEGORY_SYSTEM)
        protected List<Integer> d;

        public List<Integer> b() {
            return this.d;
        }

        public List<Integer> e() {
            return this.f11791a;
        }

        public String toString() {
            return "DynamicBloodPressureTypeCategorization{sys=" + this.d + ", dia=" + this.f11791a + '}';
        }
    }
}
