package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class fqw {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("currRecordIndex")
    private int f12614a;

    @SerializedName("coachData")
    private CoachData b;

    @SerializedName("courseBelongType")
    private int c;

    @SerializedName("fitWorkout")
    private FitWorkout d;

    @SerializedName("entrance")
    private String e;

    @SerializedName("isShowButton")
    private boolean f;

    @SerializedName("longVideoUrl")
    private String g;

    @SerializedName("isPlanFit")
    private boolean h;

    @SerializedName("longVideoProperty")
    private int i;

    @SerializedName("planStartTime")
    private long j;

    @SerializedName("workoutPackageId")
    private String k;

    @SerializedName(ParsedFieldTag.KAKA_CHECKED_IN_RECORDES)
    private ArrayList<WorkoutRecord> l;

    @SerializedName("topicName")
    private String m;

    @SerializedName("record")
    private WorkoutRecord n;

    @SerializedName("trackType")
    private int o;

    public boolean k() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public int o() {
        return this.o;
    }

    public void e(int i) {
        this.o = i;
    }

    public long g() {
        return this.j;
    }

    public void a(long j) {
        this.j = j;
    }

    public WorkoutRecord i() {
        return this.n;
    }

    public void d(WorkoutRecord workoutRecord) {
        this.n = workoutRecord;
    }

    public boolean l() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public CoachData c() {
        return this.b;
    }

    public void e(CoachData coachData) {
        this.b = coachData;
    }

    public FitWorkout a() {
        return this.d;
    }

    public void a(FitWorkout fitWorkout) {
        this.d = fitWorkout;
    }

    public String j() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public int f() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }

    public ArrayList<WorkoutRecord> h() {
        return this.l;
    }

    public void e(ArrayList<WorkoutRecord> arrayList) {
        this.l = arrayList;
    }

    public int e() {
        return this.f12614a;
    }

    public void c(int i) {
        this.f12614a = i;
    }

    public String n() {
        return this.m;
    }

    public void b(String str) {
        this.m = str;
    }

    public String d() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public String m() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public int b() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }
}
