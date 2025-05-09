package defpackage;

import java.util.Map;

/* loaded from: classes6.dex */
public class mde extends mcz {

    /* renamed from: a, reason: collision with root package name */
    private String f14891a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private String e;
    private int f;

    public mde() {
        super(13);
    }

    public String d() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public int c() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public int b() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public void b(String str) {
        this.e = str;
    }

    public String e() {
        return this.f14891a;
    }

    public void a(String str) {
        this.f14891a = str;
    }

    public Map<String, Object> a() {
        return this.c;
    }

    public void c(Map<String, Object> map) {
        this.c = map;
    }

    public String toString() {
        return "KakaUpdateReturnBody{rewardKaka=" + this.d + ", totalKaka=" + this.f + ", resultDes='" + this.e + "', results=" + this.c + '}';
    }
}
