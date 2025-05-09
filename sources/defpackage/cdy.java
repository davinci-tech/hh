package defpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class cdy implements Serializable {
    private static final long serialVersionUID = -5599807658382352185L;
    private int b;
    private int c;
    private int f;
    private int g;
    private int i;
    private int o;
    private String h = "";
    private int e = 1;
    private String j = "";

    /* renamed from: a, reason: collision with root package name */
    private int f656a = 1;
    private List<cdu> d = new ArrayList(2);

    public int f() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public int h() {
        return this.i;
    }

    public void a(int i) {
        this.i = i;
    }

    public String g() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int b() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public int j() {
        return this.o;
    }

    public void f(int i) {
        this.o = i;
    }

    public int d() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public String a() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public void h(int i) {
        this.f656a = i;
    }

    public int i() {
        return this.f656a;
    }

    public List<cdu> e() {
        return this.d;
    }

    public int o() {
        return this.f;
    }

    public void j(int i) {
        this.f = i;
    }

    public void e(cdu cduVar) {
        this.d.add(cduVar);
    }

    public String toString() {
        return "PageModuleObject{mName='" + this.h + "', mLayout='" + this.c + "', mModuleType='" + this.b + "', mPageType='" + this.i + "', pageModuleId='" + this.g + "', mDisplay = " + this.e + "', mTextPosition ='" + this.f + "', mMoreInfoUrl ='" + this.j + "', mCardItemList='" + this.d.toString() + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && (obj instanceof cdy) && this.g == ((cdy) obj).g;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.g));
    }
}
