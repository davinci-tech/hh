package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bmp {

    /* renamed from: a, reason: collision with root package name */
    private String f437a;
    private String b;
    private int d;
    private List<bmp> e;

    public bmp(int i, String str, String str2) {
        this.d = i;
        this.f437a = str2;
        this.b = str;
    }

    public bmp() {
        this.e = new ArrayList(16);
    }

    public List<bmp> c() {
        return this.e;
    }

    public int e() {
        return this.d;
    }

    public String a() {
        return this.f437a;
    }

    public void e(String str) {
        this.f437a = str;
    }

    public String d() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }
}
