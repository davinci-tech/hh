package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes8.dex */
public class ery implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("calorie")
    private Float f12209a;

    @SerializedName("endDate")
    private Long b;

    @SerializedName("distance")
    private Integer c;

    @SerializedName("difficulty")
    private Integer d;

    @SerializedName(ParsedFieldTag.BEGIN_DATE)
    private Long e;

    @SerializedName(ParsedFieldTag.GOAL)
    private Integer f;

    @SerializedName("excludedDate")
    private String g;

    @SerializedName("picture")
    private String h;

    @SerializedName("movementTimes")
    private Integer i;

    @SerializedName("name")
    private String j;

    @SerializedName("version")
    private String k;

    @SerializedName("weekCount")
    private Integer l;

    @SerializedName("type")
    private Integer m;

    @SerializedName("remindTime")
    private Integer n;

    @SerializedName("planId")
    private String o;

    @SerializedName("weekInfos")
    private List<c> s;

    private ery(d dVar) {
        this.o = dVar.o;
        this.j = dVar.i;
        this.m = dVar.n;
        this.d = dVar.d;
        this.l = dVar.m;
        this.f12209a = dVar.e;
        this.c = dVar.b;
        this.h = dVar.h;
        this.k = dVar.k;
        this.e = dVar.f12212a;
        this.b = dVar.c;
        this.g = dVar.g;
        this.i = dVar.j;
        this.f = dVar.f;
        this.n = dVar.l;
        this.s = dVar.t;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("sentence")
        private String f12210a;

        @SerializedName("stage")
        private String b;

        @SerializedName("courses")
        private List<e> c;

        @SerializedName("week_name")
        private String d;

        @SerializedName("displayorder")
        private Integer e;

        private c(d dVar) {
            this.c = dVar.e;
            this.e = dVar.d;
            this.b = dVar.b;
            this.f12210a = dVar.f12211a;
            this.d = dVar.c;
        }

        public static final class d {

            /* renamed from: a, reason: collision with root package name */
            private String f12211a;
            private String b;
            private String c;
            private Integer d;
            private List<e> e;

            public d e(List<e> list) {
                this.e = list;
                return this;
            }

            public d c(Integer num) {
                this.d = num;
                return this;
            }

            public d d(String str) {
                this.b = str;
                return this;
            }

            public d e(String str) {
                this.f12211a = str;
                return this;
            }

            public d b(String str) {
                this.c = str;
                return this;
            }

            public c c() {
                return new c(this);
            }
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("duration")
        private Integer f12213a;

        @SerializedName("description")
        private String b;

        @SerializedName("displayorder")
        private Integer c;

        @SerializedName("runningParas")
        private String d;

        @SerializedName("name")
        private String e;

        @SerializedName("version")
        private String f;

        @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
        private String g;

        @SerializedName("trainingDate")
        private Long i;

        @SerializedName("type")
        private Integer j;

        private e(b bVar) {
            this.i = bVar.g;
            this.g = bVar.j;
            this.e = bVar.c;
            this.b = bVar.d;
            this.c = bVar.b;
            this.d = bVar.e;
            this.f = bVar.i;
            this.f12213a = bVar.f12214a;
            this.j = bVar.f;
        }

        public static final class b {

            /* renamed from: a, reason: collision with root package name */
            private Integer f12214a;
            private Integer b;
            private String c;
            private String d;
            private String e;
            private Integer f;
            private Long g;
            private String i;
            private String j;

            public b a(Long l) {
                this.g = l;
                return this;
            }

            public b d(String str) {
                this.j = str;
                return this;
            }

            public b a(String str) {
                this.c = str;
                return this;
            }

            public b e(String str) {
                this.d = str;
                return this;
            }

            public b b(Integer num) {
                this.b = num;
                return this;
            }

            public b b(String str) {
                this.e = str;
                return this;
            }

            public b c(String str) {
                this.i = str;
                return this;
            }

            public b d(Integer num) {
                this.f12214a = num;
                return this;
            }

            public b e(Integer num) {
                this.f = num;
                return this;
            }

            public e c() {
                return new e(this);
            }
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bh();
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private Long f12212a;
        private Integer b;
        private Long c;
        private Integer d;
        private Float e;
        private Integer f;
        private String g;
        private String h;
        private String i;
        private Integer j;
        private String k;
        private Integer l;
        private Integer m;
        private Integer n;
        private String o;
        private List<c> t;

        public d b(String str) {
            this.o = str;
            return this;
        }

        public d a(String str) {
            this.i = str;
            return this;
        }

        public d i(Integer num) {
            this.n = num;
            return this;
        }

        public d c(Integer num) {
            this.d = num;
            return this;
        }

        public d j(Integer num) {
            this.m = num;
            return this;
        }

        public d b(Float f) {
            this.e = f;
            return this;
        }

        public d d(Integer num) {
            this.b = num;
            return this;
        }

        public d e(String str) {
            this.h = str;
            return this;
        }

        public d d(String str) {
            this.k = str;
            return this;
        }

        public d e(Long l) {
            this.f12212a = l;
            return this;
        }

        public d c(Long l) {
            this.c = l;
            return this;
        }

        public d c(String str) {
            this.g = str;
            return this;
        }

        public d e(Integer num) {
            this.j = num;
            return this;
        }

        public d b(Integer num) {
            this.f = num;
            return this;
        }

        public d a(Integer num) {
            this.l = num;
            return this;
        }

        public d c(List<c> list) {
            this.t = list;
            return this;
        }

        public ery d() {
            return new ery(this);
        }
    }
}
