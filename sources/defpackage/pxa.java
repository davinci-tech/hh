package defpackage;

import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class pxa {
    private boolean c;
    private fdp f = new fdp(SleepViewConstants.ViewTypeEnum.DAY);

    /* renamed from: a, reason: collision with root package name */
    private CopyOnWriteArrayList<pvz> f16318a = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<pwb> e = new CopyOnWriteArrayList<>();
    private volatile int b = 0;
    private volatile String d = "";
    private List<String> i = new ArrayList(16);

    public fdp f() {
        return this.f;
    }

    public void b(fdp fdpVar) {
        this.f = fdpVar;
    }

    public CopyOnWriteArrayList<pwb> b() {
        return this.e;
    }

    public void d(CopyOnWriteArrayList<pwb> copyOnWriteArrayList) {
        this.e = copyOnWriteArrayList;
    }

    public CopyOnWriteArrayList<pvz> c() {
        return this.f16318a;
    }

    public void b(CopyOnWriteArrayList<pvz> copyOnWriteArrayList) {
        this.f16318a = copyOnWriteArrayList;
    }

    public boolean j() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public int e() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public List<String> a() {
        return this.i;
    }

    public void e(List<String> list) {
        this.i = list;
    }
}
