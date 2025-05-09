package defpackage;

import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class qkb {

    /* renamed from: a, reason: collision with root package name */
    private long f16449a;
    private ArrayList<e> d;

    public long a() {
        return this.f16449a;
    }

    public void b(long j) {
        this.f16449a = j;
    }

    public ArrayList<e> d() {
        return this.d;
    }

    public void d(List<e> list) {
        if (list == null) {
            LogUtil.e("BloodSugarHistoryData", "BloodSugarHistoryData setOneDayList oneDayList is null");
        } else {
            this.d = new ArrayList<>(list);
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private boolean f16450a = true;
        private boolean b;
        private int c;
        private int d;
        private String e;
        private long f;
        private String g;
        private double h;
        private double i;
        private int j;
        private long l;
        private long m;

        public long j() {
            return this.f;
        }

        public void d(long j) {
            this.f = j;
        }

        public long m() {
            return this.l;
        }

        public void e(long j) {
            this.l = j;
        }

        public int a() {
            return this.j;
        }

        public void b(int i) {
            this.j = i;
        }

        public double f() {
            return this.i;
        }

        public void e(double d) {
            this.i = d;
        }

        public int b() {
            return this.c;
        }

        public void e(int i) {
            this.c = i;
        }

        public boolean n() {
            return this.f16450a;
        }

        public void c(boolean z) {
            this.f16450a = z;
        }

        public String e() {
            return this.e;
        }

        public void d(String str) {
            this.e = str;
        }

        public String i() {
            return this.g;
        }

        public void a(String str) {
            this.g = str;
        }

        public int c() {
            return this.d;
        }

        public void d(int i) {
            this.d = i;
        }

        public long h() {
            return this.m;
        }

        public void a(long j) {
            this.m = j;
        }

        public double g() {
            return this.h;
        }

        public boolean d() {
            return this.b;
        }

        public void d(boolean z) {
            this.b = z;
        }
    }
}
