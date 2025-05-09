package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class phk {

    @SerializedName("accumPerfectGoalAchievedDays")
    private e c;

    public e a() {
        return this.c;
    }

    public static class e {

        @SerializedName("latestPerfect")
        private int b;

        @SerializedName("value")
        private int c;

        @SerializedName("latestDate")
        private int d;

        public int b() {
            return this.c;
        }

        public int c() {
            return this.d;
        }

        public int d() {
            return this.b;
        }

        public String toString() {
            return "PD{value=" + this.c + ", lD=" + this.d + ", lP=" + this.b + '}';
        }
    }
}
