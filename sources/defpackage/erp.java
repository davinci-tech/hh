package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.SearchCondition;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.util.List;

/* loaded from: classes3.dex */
public class erp implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("difficulty")
    private List<Integer> f12194a;

    @SerializedName("classList")
    private List<Integer> b;

    @SerializedName("equipments")
    private List<Integer> c;

    @SerializedName("commodityFlag")
    private Integer d;

    @SerializedName("fuzzyKeyWords")
    private String e;

    @SerializedName("replacedWorkoutId")
    private String f;

    @SerializedName("searchCondition")
    private SearchCondition g;

    @SerializedName("my")
    private Integer h;

    @SerializedName("lang")
    private String i;

    @SerializedName("coachGender")
    private Integer j;

    @SerializedName("secondClassifyId")
    private Integer k;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private Integer l;

    @SerializedName("pageStart")
    private Integer m;

    @SerializedName("primaryClassifyId")
    private Integer n;

    @SerializedName("secondClassifyList")
    private List<Integer> o;

    @SerializedName("workoutRank")
    private Integer p;

    @SerializedName("supportWear")
    private Integer q;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)
    private List<Integer> r;

    @SerializedName("trainingPoints")
    private List<Integer> s;

    @SerializedName("userDefinedType")
    private Integer t;

    private erp(e eVar) {
        this.m = eVar.n;
        this.l = eVar.o;
        this.i = eVar.f;
        this.q = eVar.s;
        this.n = eVar.m;
        this.b = eVar.c;
        this.o = eVar.l;
        this.k = eVar.k;
        this.s = eVar.t;
        this.f12194a = eVar.f12195a;
        this.c = eVar.e;
        this.h = eVar.h;
        this.p = eVar.r;
        this.r = eVar.q;
        this.d = eVar.b;
        this.e = eVar.d;
        this.t = eVar.p;
        this.g = eVar.i;
        this.j = eVar.j;
        this.f = eVar.g;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.an();
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private List<Integer> f12195a;
        private Integer b;
        private List<Integer> c;
        private String d;
        private List<Integer> e;
        private String f;
        private String g;
        private Integer h;
        private SearchCondition i;
        private Integer j;
        private Integer k;
        private List<Integer> l;
        private Integer m;
        private Integer n;
        private Integer o;
        private Integer p;
        private List<Integer> q;
        private Integer r;
        private Integer s;
        private List<Integer> t;

        public e e(Integer num) {
            this.n = num;
            return this;
        }

        public e c(Integer num) {
            this.o = num;
            return this;
        }

        public e c(String str) {
            this.f = str;
            return this;
        }

        public e h(Integer num) {
            this.s = num;
            return this;
        }

        public e f(Integer num) {
            this.m = num;
            return this;
        }

        public e a(List<Integer> list) {
            this.c = list;
            return this;
        }

        public e e(List<Integer> list) {
            this.l = list;
            return this;
        }

        public e g(Integer num) {
            this.k = num;
            return this;
        }

        public e d(List<Integer> list) {
            this.t = list;
            return this;
        }

        public e b(List<Integer> list) {
            this.f12195a = list;
            return this;
        }

        public e c(List<Integer> list) {
            this.e = list;
            return this;
        }

        public e a(Integer num) {
            this.h = num;
            return this;
        }

        public e i(Integer num) {
            this.r = num;
            return this;
        }

        public e i(List<Integer> list) {
            this.q = list;
            return this;
        }

        public e d(Integer num) {
            this.b = num;
            return this;
        }

        public e b(String str) {
            this.d = str;
            return this;
        }

        public e j(Integer num) {
            this.p = num;
            return this;
        }

        public e b(Integer num) {
            this.j = num;
            return this;
        }

        public e e(SearchCondition searchCondition) {
            this.i = searchCondition;
            return this;
        }

        public e d(String str) {
            this.g = str;
            return this;
        }

        public erp d() {
            return new erp(this);
        }
    }
}
