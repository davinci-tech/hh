package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class quh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("overview")
    private qts f16593a;

    @SerializedName("modifiedTime")
    private long b;

    @SerializedName("localDate")
    private int c;

    @SerializedName("time")
    private long d;

    @SerializedName("meals")
    private List<qul> e;

    @SerializedName("timeZone")
    private String i;

    public quh(int i, long j, qts qtsVar, List<qul> list, String str) {
        this.d = j;
        this.f16593a = qtsVar;
        this.e = list;
        this.i = str;
        this.c = i;
    }

    public void e(long j) {
        this.d = j;
    }

    public void h() {
        if (String.valueOf(this.d).matches("\\d{13}")) {
            this.d /= 1000;
        }
        if (koq.b(this.e)) {
            return;
        }
        Iterator<qul> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().l();
        }
    }

    public void e() {
        if (koq.b(this.e)) {
            LogUtil.h("DietRecord", "filterDuplicatesMeal mMeals is empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (qul qulVar : this.e) {
            if (arrayList.contains(qulVar)) {
                if (!koq.b(qulVar.c())) {
                    int indexOf = arrayList.indexOf(qulVar);
                    if (koq.b(((qul) arrayList.get(indexOf)).c())) {
                        arrayList.set(indexOf, qulVar);
                    } else {
                        arrayList.add(qulVar);
                    }
                }
            } else {
                arrayList.add(qulVar);
            }
        }
        this.e = arrayList;
        g();
    }

    public void a(qul qulVar) {
        if (qulVar == null) {
            return;
        }
        if (this.e == null) {
            this.e = new ArrayList();
        }
        qul d = d(qulVar.h());
        if (d != null) {
            LogUtil.h("DietRecord", "has such meal");
            if (qulVar.g() > 0) {
                d.a(qulVar.g());
            }
            d.c(qulVar.i());
            d.c(qulVar.b() + d.b());
            d.c().addAll(qulVar.c());
            g();
            return;
        }
        this.e.add(qulVar);
        qts qtsVar = this.f16593a;
        if (qtsVar == null) {
            LogUtil.h("DietRecord", "addMeal overview is null");
        } else {
            qtsVar.d(qulVar.b());
        }
    }

    public boolean b(qul qulVar) {
        if (qulVar == null) {
            LogUtil.h("DietRecord", "updateMeal meal is null");
            return false;
        }
        List<qul> list = this.e;
        if (list == null || !list.contains(qulVar)) {
            LogUtil.h("DietRecord", "meals is empty or meals is not contains meal");
            return false;
        }
        int indexOf = this.e.indexOf(qulVar);
        if (indexOf < 0) {
            LogUtil.h("DietRecord", "no such meal");
            return false;
        }
        this.e.set(indexOf, qulVar);
        g();
        return true;
    }

    public boolean e(qul qulVar) {
        if (qulVar == null) {
            ReleaseLogUtil.d("DietRecord", "recordMeal meal is null");
            return false;
        }
        if (this.e == null) {
            this.e = new ArrayList();
        }
        LogUtil.a("DietRecord", "recordMeal meal is: ", qulVar, "recordMeal mMeals is: ", this.e);
        int indexOf = this.e.indexOf(qulVar);
        if (indexOf < 0) {
            LogUtil.a("DietRecord", "recordMeal no such meal");
            a(qulVar);
            return true;
        }
        if (qulVar.i() > this.e.get(indexOf).i()) {
            this.e.set(indexOf, qulVar);
            g();
            return true;
        }
        LogUtil.h("DietRecord", "recordMeal modifiedTime is not big");
        return false;
    }

    private void g() {
        if (this.f16593a == null) {
            LogUtil.h("DietRecord", "updateInTake mOverview is null");
            return;
        }
        float f = 0.0f;
        for (qul qulVar : this.e) {
            if (qulVar != null) {
                f += qulVar.b();
            }
        }
        this.f16593a.e(f);
    }

    private qul d(int i) {
        if (koq.b(this.e)) {
            LogUtil.h("DietRecord", "mMeals is empty");
            return null;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            qul qulVar = this.e.get(i2);
            if (qulVar.h() == i) {
                return qulVar;
            }
        }
        return null;
    }

    public String j() {
        return this.i;
    }

    public long f() {
        return this.d;
    }

    public qts d() {
        return this.f16593a;
    }

    public List<qul> a() {
        if (koq.b(this.e)) {
            return new ArrayList();
        }
        return this.e;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DietRecord{LocalDate=");
        sb.append(this.c);
        sb.append(",time=");
        sb.append(this.d);
        sb.append(",timeZone=");
        sb.append(this.i);
        sb.append(", overview=");
        sb.append(this.f16593a);
        sb.append(", meals=");
        List<qul> list = this.e;
        sb.append(list == null ? new ArrayList() : list.toString());
        sb.append(", mModifiedTime=");
        sb.append(this.b);
        sb.append('}');
        return sb.toString();
    }
}
