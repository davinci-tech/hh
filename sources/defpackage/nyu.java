package defpackage;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes6.dex */
public class nyu implements Serializable {
    private static final long serialVersionUID = 6072518949116079534L;

    /* renamed from: a, reason: collision with root package name */
    private int f15562a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private List<nzn> f;
    private String g;
    private String h;
    private boolean i;
    private boolean j;
    private String l;
    private String m;
    private int n;

    public int a() {
        return this.f15562a;
    }

    public void b(int i) {
        this.f15562a = i;
    }

    public boolean g() {
        return this.e;
    }

    public void e(boolean z) {
        this.e = z;
    }

    public String f() {
        return this.l;
    }

    public void i(String str) {
        this.l = str;
    }

    public List<nzn> e() {
        return this.f;
    }

    public String h() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public String d() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public int i() {
        return this.n;
    }

    public void d(int i) {
        this.n = i;
    }

    public boolean n() {
        return this.j;
    }

    public void d(boolean z) {
        this.j = z;
    }

    public boolean j() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void b(String str) {
        this.g = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public String b() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(List<nzn> list) {
        this.f = list;
    }

    public String toString() {
        return "DeclarationBean{mTitle='" + this.m + "', mFeatureId='" + this.c + "', mType=" + this.n + ", mIsStartBox=" + this.j + ", mIsEndBox=" + this.i + ", mVersion='" + this.l + "', mIsChecked=" + this.e + ", mShortVersion='" + this.g + "', mBaseVersion='" + this.d + "', mPrivacyVersion='" + this.h + "', mAgreementVersion='" + this.b + "', mIndex=" + this.f15562a + ", mParts=" + this.f + '}';
    }
}
