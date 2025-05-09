package defpackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class usu implements Cloneable {
    private boolean g;
    private File o;
    private List<usq> h = new ArrayList();
    private List<usj> b = new ArrayList();
    private usi d = new usi();
    private usl e = new usl();

    /* renamed from: a, reason: collision with root package name */
    private usk f17536a = new usk();
    private usr f = new usr();
    private ust j = new ust();
    private boolean c = false;
    private long i = -1;

    public List<usq> c() {
        return this.h;
    }

    public usl e() {
        return this.e;
    }

    public void c(usl uslVar) {
        this.e = uslVar;
    }

    public usk d() {
        return this.f17536a;
    }

    public void a(usk uskVar) {
        this.f17536a = uskVar;
    }

    public boolean g() {
        return this.g;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public File j() {
        return this.o;
    }

    public void c(File file) {
        this.o = file;
    }

    public usr b() {
        return this.f;
    }

    public void e(usr usrVar) {
        this.f = usrVar;
    }

    public ust f() {
        return this.j;
    }

    public void c(ust ustVar) {
        this.j = ustVar;
    }

    public boolean h() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public long a() {
        return this.i;
    }

    public void d(long j) {
        this.i = j;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
