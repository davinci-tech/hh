package defpackage;

import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class ayi {

    /* renamed from: a, reason: collision with root package name */
    private String f284a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int g;
    private Map<Integer, List<ayd>> i;

    public int d() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int a() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int g() {
        return this.g;
    }

    public void d(int i) {
        this.g = i;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public Map<Integer, List<ayd>> i() {
        return this.i;
    }

    public void c(Map<Integer, List<ayd>> map) {
        this.i = map;
    }

    public int e() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public String c() {
        return this.f284a;
    }

    public void a(String str) {
        this.f284a = str;
    }

    public String toString() {
        return "WeeklyReportBean{mStartDate=" + this.e + ", mEndDate=" + this.c + ", mWeekNumber=" + this.g + ", mCloverNumber=" + this.b + ", mWeeklyData=" + this.i + ", mCompleteNumber=" + this.d + ", mDateSegment='" + this.f284a + "'}";
    }
}
