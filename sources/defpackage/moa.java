package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class moa implements Serializable, Cloneable {
    private static final long serialVersionUID = 6825022930100640483L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("exeDate")
    private List<Integer> f15076a;

    @SerializedName("intensityZone")
    private List<Integer> b;

    @SerializedName("endTime")
    private long c;

    @SerializedName("level")
    private String d;

    @SerializedName("isExecutable")
    private boolean e;

    @SerializedName("targetPoints")
    private double f;

    @SerializedName("levelDescription")
    private String g;

    @SerializedName("runDate")
    private List<Integer> h;

    @SerializedName("startTime")
    private long i;

    @SerializedName("targetDuration")
    private int j;

    @SerializedName("totalTrainingDays")
    private int k;

    @SerializedName("weekNo")
    private int l;

    @SerializedName("weeklyCourse")
    private List<mnu> o;

    public int f() {
        return this.l;
    }

    public void b(int i) {
        this.l = i;
    }

    public String b() {
        return this.d;
    }

    public String d() {
        return this.g;
    }

    public long a() {
        return this.i;
    }

    public void d(long j) {
        this.i = j;
    }

    public long e() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public List<Integer> c() {
        return this.b;
    }

    private void e(List<Integer> list) {
        this.b = list;
    }

    public void c(List<mnu> list) {
        this.o = list;
    }

    public List<mnu> j() {
        return this.o;
    }

    private void a(List<Integer> list) {
        this.h = list;
    }

    private void d(List<Integer> list) {
        this.f15076a = list;
    }

    public int i() {
        return this.k;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public Object clone() {
        try {
            if (!(super.clone() instanceof moa)) {
                return new moa();
            }
            moa moaVar = (moa) super.clone();
            if (this.h != null) {
                moaVar.a(new ArrayList(this.h));
            }
            if (this.f15076a != null) {
                moaVar.d(new ArrayList(this.f15076a));
            }
            if (this.b != null) {
                moaVar.e(new ArrayList(this.b));
            }
            if (this.o != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<mnu> it = this.o.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                moaVar.c(arrayList);
            }
            return moaVar;
        } catch (CloneNotSupportedException e) {
            LogUtil.e("PlanWeekDataBean clone failed", LogAnonymous.b((Throwable) e));
            throw new AssertionError();
        }
    }
}
