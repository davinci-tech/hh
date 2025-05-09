package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class phs extends phq {

    @SerializedName("taskList")
    private List<a> c;

    public String h() {
        return n() ? "" : this.c.get(0).d();
    }

    public int f() {
        if (n()) {
            return -1;
        }
        return this.c.get(0).i();
    }

    public int i() {
        if (n()) {
            return -1;
        }
        return this.c.get(0).e();
    }

    public int c() {
        if (n()) {
            return -1;
        }
        return this.c.get(0).c();
    }

    public int j() {
        if (n()) {
            return -1;
        }
        return this.c.get(0).a();
    }

    public int d() {
        if (n()) {
            return -1;
        }
        return this.c.get(0).b();
    }

    public int e() {
        if (n()) {
            return -1;
        }
        return ((c) this.c.get(0).j().get(0)).a();
    }

    public int g() {
        if (n()) {
            return -1;
        }
        return ((c) this.c.get(0).j().get(0)).b();
    }

    public boolean n() {
        return koq.b(this.c) || koq.b(this.c.get(0).j());
    }

    @Override // defpackage.phq
    public String toString() {
        return "ThreeCircleTaskResponse{resultCode=" + b() + ", resultDesc=" + a() + ", ThreeRingTaskInfo=" + this.c + '}';
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("lastAcquirePoints")
        private int f16137a;

        @SerializedName("subscriptionStatus")
        private int b;

        @SerializedName("taskBeginTime")
        private long c;

        @SerializedName("days")
        private int d;

        @SerializedName("dayGoalMap")
        private Map<String, Integer> e;

        @SerializedName("taskPoints")
        private int f;

        @SerializedName(ParsedFieldTag.TASK_NAME)
        private String g;

        @SerializedName("taskEndTime")
        private long h;

        @SerializedName("taskId")
        private String i;

        @SerializedName("taskDays")
        private int j;

        @SerializedName("taskSubscribeEndTime")
        private long l;

        @SerializedName("userPeriodInfos")
        private List<c> m;

        @SerializedName(ParsedFieldTag.KAKA_TASK_RULE)
        private int n;

        @SerializedName("taskScenarios")
        private int o;

        /* JADX INFO: Access modifiers changed from: private */
        public String d() {
            return this.i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int i() {
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int e() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a() {
            return this.j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int c() {
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int b() {
            return this.f16137a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<c> j() {
            return this.m;
        }

        public String toString() {
            return " ThreeRingTaskInfo{taskId=" + this.i + ", taskName=" + this.g + ", taskScenarios=" + this.o + "taskRule=" + this.n + ", taskPoints=" + this.f + ", taskBeginTime=" + this.c + ", taskEndTime=" + this.h + ", taskDays=" + this.j + "taskSubscribeEndTime=" + this.l + ", subscriptionStatus=" + this.b + ", days=" + this.d + "dayGoalMap=" + this.e + ", userPeriodInfo=" + this.m + '}';
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("periodTaskStatus")
        private int f16138a;

        @SerializedName("periodTaskPoints")
        private int c;

        /* JADX INFO: Access modifiers changed from: private */
        public int a() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int b() {
            return this.f16138a;
        }

        public String toString() {
            return "UserPeriodInfo{PeriodTaskPoints=" + this.c + ", periodTaskStatus=" + this.f16138a + '}';
        }
    }
}
