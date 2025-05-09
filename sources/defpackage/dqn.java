package defpackage;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class dqn {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("gradeList")
    protected List<b> f11788a;

    @SerializedName("descriptionid")
    protected int c;

    @SerializedName("type")
    protected int e;

    public int c() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public List<b> a() {
        return this.f11788a;
    }

    public void d(List<b> list) {
        this.f11788a = list;
    }

    public String toString() {
        return "BloodPressureFeatureInfo{type=" + this.e + ", gradeList=" + this.f11788a + '}';
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("dia")
        protected List<Integer> f11789a;

        @SerializedName("titleId")
        protected int b;

        @SerializedName(NotificationCompat.CATEGORY_SYSTEM)
        protected List<Integer> c;

        @SerializedName("grade")
        protected int d;

        @SerializedName("orAnd")
        protected int e;

        public int e() {
            return this.d;
        }

        public void e(int i) {
            this.d = i;
        }

        public int d() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public int c() {
            return this.e;
        }

        public void b(int i) {
            this.e = i;
        }

        public List<Integer> a() {
            return this.c;
        }

        public void e(List<Integer> list) {
            this.c = list;
        }

        public List<Integer> b() {
            return this.f11789a;
        }

        public void c(List<Integer> list) {
            this.f11789a = list;
        }

        public String toString() {
            return "Categorization{grade=" + this.d + ", titleId=" + this.b + ", orAnd=" + this.e + ", sys=" + this.c + ", dia=" + this.f11789a + '}';
        }
    }
}
