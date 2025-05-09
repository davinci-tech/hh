package defpackage;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import defpackage.dqn;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class dqu {
    private static dqu e;
    private String c;
    private int d;
    private ArrayList<dqn> f;
    private ArrayList<dqn> b = new ArrayList<>();
    private ArrayList<dqn> i = new ArrayList<>();

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<dqq> f11794a = new ArrayList<>();

    private dqu() {
    }

    public static dqu b() {
        dqu dquVar;
        synchronized (dqu.class) {
            if (e == null) {
                e = new dqu();
            }
            dquVar = e;
        }
        return dquVar;
    }

    public int c() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public ArrayList<dqn> e() {
        return h();
    }

    public ArrayList<dqn> g() {
        return j();
    }

    public ArrayList<dqn> f() {
        return this.f;
    }

    public void b(ArrayList<dqn> arrayList) {
        this.f = arrayList;
    }

    public ArrayList<dqq> d() {
        return this.f11794a;
    }

    public void a(ArrayList<dqq> arrayList) {
        this.f11794a = arrayList;
    }

    public String a() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    private ArrayList<dqn> h() {
        dqn a2 = a(0, 90, 0, 60, 0, 0);
        a2.e(0);
        dqn a3 = a(91, 119, 61, 79, 1, 0);
        a3.e(1);
        dqn a4 = a(120, OldToNewMotionPath.SPORT_TYPE_AEROBICS, 80, 89, 2, 1);
        a4.e(2);
        dqn i = i();
        i.e(3);
        this.b.clear();
        this.b.add(a2);
        this.b.add(a3);
        this.b.add(a4);
        this.b.add(i);
        return this.b;
    }

    private dqn i() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        dqn.b bVar = new dqn.b();
        dqn.b bVar2 = new dqn.b();
        dqn.b bVar3 = new dqn.b();
        dqn dqnVar = new dqn();
        arrayList7.clear();
        arrayList.add(140);
        arrayList.add(159);
        arrayList2.add(90);
        arrayList2.add(99);
        bVar.e(0);
        bVar.a(3);
        bVar.b(1);
        bVar.e(arrayList);
        bVar.c(arrayList2);
        arrayList7.add(0, bVar);
        arrayList3.add(160);
        arrayList3.add(179);
        arrayList4.add(100);
        arrayList4.add(109);
        bVar2.e(1);
        bVar2.a(4);
        bVar2.b(1);
        bVar2.e(arrayList3);
        bVar2.c(arrayList4);
        arrayList7.add(1, bVar2);
        arrayList5.add(180);
        arrayList5.add(999);
        arrayList6.add(110);
        arrayList6.add(999);
        bVar3.e(2);
        bVar3.a(5);
        bVar3.b(1);
        bVar3.e(arrayList5);
        bVar3.c(arrayList6);
        arrayList7.add(2, bVar3);
        dqnVar.d(arrayList7);
        return dqnVar;
    }

    private dqn a(int i, int i2, int i3, int i4, int i5, int i6) {
        ArrayList arrayList = new ArrayList();
        dqn.b bVar = new dqn.b();
        dqn dqnVar = new dqn();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList.clear();
        arrayList2.clear();
        arrayList3.clear();
        arrayList2.add(Integer.valueOf(i));
        arrayList2.add(Integer.valueOf(i2));
        arrayList3.add(Integer.valueOf(i3));
        arrayList3.add(Integer.valueOf(i4));
        bVar.e(0);
        bVar.a(i5);
        bVar.b(i6);
        bVar.c(arrayList3);
        bVar.e(arrayList2);
        arrayList.add(bVar);
        dqnVar.d(arrayList);
        return dqnVar;
    }

    private ArrayList<dqn> j() {
        dqn a2 = a(0, 90, 0, 60, 0, 0);
        a2.e(0);
        dqn a3 = a(91, 129, 61, 84, 1, 2);
        a3.e(1);
        dqn a4 = a(OldToNewMotionPath.SPORT_TYPE_TENNIS, OldToNewMotionPath.SPORT_TYPE_AEROBICS, 85, 89, 2, 2);
        a4.e(2);
        dqn i = i();
        i.e(3);
        this.i.clear();
        this.i.add(a2);
        this.i.add(a3);
        this.i.add(a4);
        this.i.add(i);
        return this.i;
    }
}
