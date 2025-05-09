package defpackage;

import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;

/* loaded from: classes.dex */
public class msq {

    /* renamed from: a, reason: collision with root package name */
    private PullHealthBiListener f15153a;
    private String b;
    private String c;
    private String d;
    private String e;
    private PullListener f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private int k;
    private String l;
    private String m;
    private String n;
    private int o;

    public String e() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String a() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public int f() {
        return this.o;
    }

    public void d(int i) {
        this.o = i;
    }

    public String j() {
        return this.i;
    }

    public void h(String str) {
        this.i = str;
    }

    public int k() {
        return this.k;
    }

    public void b(int i) {
        this.k = i;
    }

    public void b(PullListener pullListener) {
        this.f = pullListener;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public boolean b() {
        return this.h;
    }

    public void d(boolean z) {
        this.g = z;
    }

    public boolean h() {
        return this.g;
    }

    public void i(String str) {
        this.n = str;
    }

    public String l() {
        return this.n;
    }

    public String o() {
        return this.l;
    }

    public void f(String str) {
        this.l = str;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String g() {
        return this.j;
    }

    public void e(String str) {
        this.j = str;
    }

    public String d() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public String i() {
        return this.m;
    }

    public void g(String str) {
        this.m = str;
    }

    protected void e(mso msoVar) {
        int i;
        PullListener pullListener = this.f;
        if (pullListener != null) {
            pullListener.onPullingChange(this, msoVar);
        }
        if (msoVar == null || (i = msoVar.i()) == 0 || i == -8) {
            return;
        }
        msn.c().d(this);
    }

    public void e(PullHealthBiListener pullHealthBiListener) {
        this.f15153a = pullHealthBiListener;
    }

    protected void e(int i, String str) {
        PullHealthBiListener pullHealthBiListener = this.f15153a;
        if (pullHealthBiListener != null) {
            pullHealthBiListener.onPullHealthBiCalling(i, str, this);
        }
    }
}
