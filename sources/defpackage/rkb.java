package defpackage;

import android.content.ContentValues;

/* loaded from: classes7.dex */
public class rkb {

    /* renamed from: a, reason: collision with root package name */
    private int f16794a;
    private int b;
    private ContentValues c;
    private String[] d;
    private int e;
    private long f;
    private String g;
    private int[] h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private String m;
    private long n;
    private boolean o;
    private int[] q;

    public rkb() {
        this(0L, 0L, 0, "", "");
    }

    public rkb(long j, long j2, int i, String str, String str2) {
        this.c = new ContentValues();
        this.n = j;
        this.f = j2;
        this.k = i;
        this.g = str;
        this.m = str2;
    }

    public rkb(int i, String str, String str2) {
        this.c = new ContentValues();
        this.j = i;
        this.g = str;
        this.m = str2;
    }

    public int c() {
        return this.f16794a;
    }

    public void d(int i) {
        this.f16794a = i;
    }

    public long o() {
        return this.n;
    }

    public void c(long j) {
        this.n = j;
    }

    public long h() {
        return this.f;
    }

    public void d(long j) {
        this.f = j;
    }

    public String[] d() {
        String[] strArr = this.d;
        return strArr == null ? new String[0] : (String[]) strArr.clone();
    }

    public void c(String[] strArr) {
        if (strArr == null) {
            this.d = null;
        } else {
            this.d = (String[]) strArr.clone();
        }
    }

    public int b() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int[] l() {
        int[] iArr = this.q;
        return iArr == null ? new int[0] : (int[]) iArr.clone();
    }

    public void a(int[] iArr) {
        if (iArr == null) {
            this.q = null;
        } else {
            this.q = (int[]) iArr.clone();
        }
    }

    public int f() {
        return this.i;
    }

    public void a(int i) {
        this.i = i;
    }

    public int m() {
        return this.k;
    }

    public void c(int i) {
        this.k = i;
    }

    public int e() {
        return this.j;
    }

    public int a() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public String i() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public boolean n() {
        return this.o;
    }

    public void d(boolean z) {
        this.o = z;
    }

    public int[] g() {
        int[] iArr = this.h;
        return iArr == null ? new int[0] : (int[]) iArr.clone();
    }

    public void c(int[] iArr) {
        if (iArr == null) {
            this.h = null;
        } else {
            this.h = (int[]) iArr.clone();
        }
    }

    public String j() {
        return this.m;
    }

    public void e(String str) {
        this.m = str;
    }

    public boolean k() {
        return this.l;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void e(String str, int i) {
        if (this.c.containsKey(str)) {
            this.c.remove(str);
        }
        this.c.put(str, Integer.valueOf(i));
    }

    public int a(String str) {
        Integer asInteger = this.c.getAsInteger(str);
        if (asInteger == null) {
            return Integer.MIN_VALUE;
        }
        return asInteger.intValue();
    }
}
