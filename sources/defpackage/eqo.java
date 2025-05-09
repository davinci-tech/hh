package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes3.dex */
public class eqo implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("actualCalorie")
    private Float f12172a;

    @SerializedName("wearType")
    private Integer aa;

    @SerializedName("weekSequence")
    private Integer ab;

    @SerializedName("version")
    private String ac;

    @SerializedName(WorkoutRecord.Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID)
    private String ad;

    @SerializedName("actualDistance")
    private Integer b;

    @SerializedName("bestPace")
    private Integer c;

    @SerializedName("actionSummary")
    private String d;

    @SerializedName("by")
    private Integer e;

    @SerializedName("calorie")
    private Float f;

    @SerializedName(ParsedFieldTag.TASK_COMPLETE_TIME)
    private Long g;

    @SerializedName("displayorder")
    private Integer h;

    @SerializedName("completionRate")
    private Float i;

    @SerializedName("distance")
    private Integer j;

    @SerializedName("heartRateUp")
    private Integer k;

    @SerializedName("extend")
    private String l;

    @SerializedName("duration")
    private Integer m;

    @SerializedName("heartRateDown")
    private Integer n;

    @SerializedName("heartRateList")
    private List<c> o;

    @SerializedName(HwExerciseConstants.JSON_NAME_TRAINING_POINTS)
    private Integer p;

    @SerializedName("invalidHeartRateList")
    private List<c> q;

    @SerializedName(ParsedFieldTag.NPES_TOTAL_SCORE)
    private Long r;

    @SerializedName("recordModeType")
    private Integer s;

    @SerializedName("id")
    private Integer t;

    @SerializedName("trainingDate")
    private Long u;

    @SerializedName("trainingLoadPeak")
    private Integer v;

    @SerializedName("name")
    private String w;

    @SerializedName("planId")
    private String x;

    @SerializedName("oxygen")
    private Double y;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String z;

    private eqo(d dVar) {
        this.t = dVar.q;
        this.e = dVar.b;
        this.x = dVar.v;
        this.ab = dVar.ad;
        this.u = dVar.w;
        this.z = dVar.z;
        this.w = dVar.x;
        this.h = dVar.i;
        this.j = dVar.h;
        this.f = dVar.f;
        this.b = dVar.d;
        this.f12172a = dVar.f12173a;
        this.i = dVar.j;
        this.m = dVar.n;
        this.k = dVar.l;
        this.n = dVar.o;
        this.c = dVar.c;
        this.g = dVar.g;
        this.y = dVar.u;
        this.v = dVar.y;
        this.ad = dVar.ab;
        this.d = dVar.e;
        this.ac = dVar.aa;
        this.aa = dVar.ac;
        this.l = dVar.m;
        this.p = dVar.t;
        this.r = dVar.r;
        this.s = dVar.s;
        this.o = dVar.k;
        this.q = dVar.p;
    }

    public static class c {

        @SerializedName("time")
        private Long d;

        @SerializedName(IndoorEquipManagerApi.KEY_HEART_RATE)
        private Integer e;

        private c(C0303c c0303c) {
            this.e = c0303c.d;
            this.d = c0303c.e;
        }

        /* renamed from: eqo$c$c, reason: collision with other inner class name */
        public static final class C0303c {
            private Integer d;
            private Long e;

            public C0303c d(Integer num) {
                this.d = num;
                return this;
            }

            public C0303c a(Long l) {
                this.e = l;
                return this;
            }

            public c c() {
                return new c(this);
            }
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.m();
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private Float f12173a;
        private String aa;
        private String ab;
        private Integer ac;
        private Integer ad;
        private Integer b;
        private Integer c;
        private Integer d;
        private String e;
        private Float f;
        private Long g;
        private Integer h;
        private Integer i;
        private Float j;
        private List<c> k;
        private Integer l;
        private String m;
        private Integer n;
        private Integer o;
        private List<c> p;
        private Integer q;
        private Long r;
        private Integer s;
        private Integer t;
        private Double u;
        private String v;
        private Long w;
        private String x;
        private Integer y;
        private String z;

        public d g(Integer num) {
            this.q = num;
            return this;
        }

        public d a(Integer num) {
            this.b = num;
            return this;
        }

        public d d(String str) {
            this.v = str;
            return this;
        }

        public d o(Integer num) {
            this.ad = num;
            return this;
        }

        public d b(Long l) {
            this.w = l;
            return this;
        }

        public d g(String str) {
            this.z = str;
            return this;
        }

        public d e(String str) {
            this.x = str;
            return this;
        }

        public d d(Integer num) {
            this.i = num;
            return this;
        }

        public d e(Integer num) {
            this.h = num;
            return this;
        }

        public d e(Float f) {
            this.f = f;
            return this;
        }

        public d b(Integer num) {
            this.d = num;
            return this;
        }

        public d c(Float f) {
            this.f12173a = f;
            return this;
        }

        public d d(Float f) {
            this.j = f;
            return this;
        }

        public d f(Integer num) {
            this.n = num;
            return this;
        }

        public d h(Integer num) {
            this.l = num;
            return this;
        }

        public d i(Integer num) {
            this.o = num;
            return this;
        }

        public d c(Integer num) {
            this.c = num;
            return this;
        }

        public d c(Long l) {
            this.g = l;
            return this;
        }

        public d b(Double d) {
            this.u = d;
            return this;
        }

        public d m(Integer num) {
            this.y = num;
            return this;
        }

        public d a(String str) {
            this.ab = str;
            return this;
        }

        public d c(String str) {
            this.e = str;
            return this;
        }

        public d f(String str) {
            this.aa = str;
            return this;
        }

        public d n(Integer num) {
            this.ac = num;
            return this;
        }

        public d b(String str) {
            this.m = str;
            return this;
        }

        public d e(List<c> list) {
            this.k = list;
            return this;
        }

        public d e(Long l) {
            this.r = l;
            return this;
        }

        public d j(Integer num) {
            this.s = num;
            return this;
        }

        public d a(List<c> list) {
            this.p = list;
            return this;
        }

        public eqo a() {
            return new eqo(this);
        }
    }
}
