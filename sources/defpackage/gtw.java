package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class gtw {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12935a = new Object();
    private static gtw b;
    private CopyOnWriteArrayList<knv> j = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<knv> d = new CopyOnWriteArrayList<>();
    private int e = 0;
    private int g = 0;
    private int c = 20;

    public static gtw e() {
        gtw gtwVar;
        synchronized (f12935a) {
            if (b == null) {
                b = new gtw();
            }
            gtwVar = b;
        }
        return gtwVar;
    }

    public void b() {
        this.j.clear();
        this.d.clear();
        if (d()) {
            LogUtil.a("Track_LinkageStepManager", "cleanStepArray");
            gso.e().c().stopStepPoint();
        }
    }

    public void d(knv knvVar) {
        c(knvVar, 20);
    }

    public void c(knv knvVar, int i) {
        d(knvVar, i == 20 ? this.j : this.d);
    }

    public void d(knv knvVar, CopyOnWriteArrayList<knv> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null) {
            ReleaseLogUtil.d("Track_LinkageStepManager", "constructStepArray stepArray null");
            return;
        }
        if (d(copyOnWriteArrayList)) {
            copyOnWriteArrayList.remove(0);
        }
        copyOnWriteArrayList.add(knvVar);
        LogUtil.a("Track_LinkageStepManager", "new stepArray = ", Integer.valueOf(copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).d()), " reportTime = ", Long.valueOf(copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).b()), "mCutStepSource = ", Integer.valueOf(this.c));
    }

    public boolean d(CopyOnWriteArrayList<knv> copyOnWriteArrayList) {
        return copyOnWriteArrayList.size() >= 11;
    }

    public boolean h(int i) {
        return this.j.size() >= i || (this.d.size() != 0 && this.d.size() >= i / 5);
    }

    public void b(int i) {
        c(i, d() ? this.d : this.j);
        if (d()) {
            LogUtil.a("Track_LinkageStepManager", "initLinkageStep");
            gso.e().c().startStepPoint();
        }
    }

    private boolean d() {
        return this.c == 10;
    }

    public void c(int i, CopyOnWriteArrayList<knv> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        this.e = i;
        this.g = copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).d();
        LogUtil.a("Track_LinkageStepManager", "initLinkageStep mLastNormalSteps:", Integer.valueOf(this.e), " mStartLinkageSteps:", Integer.valueOf(this.g));
    }

    public int b(int i, int i2) {
        return b(i, i2 == 10 ? this.d : this.j);
    }

    public int b(int i, CopyOnWriteArrayList<knv> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return 0;
        }
        if (copyOnWriteArrayList.size() < i + 1) {
            LogUtil.h("Track_LinkageStepManager", "getNewSteps mWearStepArray size ", Integer.valueOf(copyOnWriteArrayList.size()), "interval ", Integer.valueOf(i));
            return 0;
        }
        return copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).d() - copyOnWriteArrayList.get((copyOnWriteArrayList.size() - i) - 1).d();
    }

    public int a() {
        int c = c(this.c == 10 ? this.d : this.j);
        LogUtil.a("Track_LinkageStepManager", "linkageSteps is ", Integer.valueOf(c), "time is ", Long.valueOf(System.currentTimeMillis()));
        return c;
    }

    public int e(int i) {
        CopyOnWriteArrayList<knv> d = d(i);
        if (d == null || d.size() <= 0) {
            return 0;
        }
        return d.get(d.size() - 1).d();
    }

    public int e(int i, int i2) {
        CopyOnWriteArrayList<knv> d = d(i);
        if (koq.b(d, i2)) {
            return 0;
        }
        return (d.get(i2).d() - this.g) + this.e;
    }

    public CopyOnWriteArrayList<knv> d(int i) {
        return i == 10 ? this.d : this.j;
    }

    public int a(int i) {
        CopyOnWriteArrayList<knv> d = d(i);
        if (d == null || d.size() <= 0) {
            return 0;
        }
        return d.size();
    }

    public int c(CopyOnWriteArrayList<knv> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return 0;
        }
        return (copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).d() - this.g) + this.e;
    }

    public long c(int i) {
        return b(i == 10 ? this.d : this.j);
    }

    public long b(CopyOnWriteArrayList<knv> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return 0L;
        }
        return copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).b();
    }

    public void g(int i) {
        this.c = i;
    }
}
