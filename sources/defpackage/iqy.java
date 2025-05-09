package defpackage;

/* loaded from: classes7.dex */
public class iqy {

    /* renamed from: a, reason: collision with root package name */
    private String f13549a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String g;
    private int h;

    public iqy(String str, int i, String str2) {
        this(str, i, str2, null, null);
    }

    public iqy(String str, int i, String str2, String str3) {
        this(str, i, str2, str3, null);
    }

    public iqy(String str, int i, String str2, String str3, String str4) {
        this.d = str;
        this.h = i;
        this.f13549a = str2;
        this.e = str3;
        this.b = str4;
    }

    public iqy b(int i) {
        this.h = i;
        return this;
    }

    public void e(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.g = str;
    }

    public void e(int i) {
        this.c = i;
    }

    public void a(String str) {
        this.f13549a = str;
    }

    public String b() {
        return this.d;
    }

    public int g() {
        return this.h;
    }

    public String a() {
        return this.f13549a;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.b;
    }

    public String f() {
        return this.g;
    }

    public int e() {
        return this.c;
    }
}
