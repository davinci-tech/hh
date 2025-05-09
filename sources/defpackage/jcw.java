package defpackage;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jcw {

    /* renamed from: a, reason: collision with root package name */
    private int f13747a;
    private String b;
    private ArrayList<jcz> c;
    private int d;
    private ArrayList<jda> e;

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public void d(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public ArrayList<jcz> e() {
        return (ArrayList) jdy.d(this.c);
    }

    public void e(ArrayList<jcz> arrayList) {
        this.c = (ArrayList) jdy.d(arrayList);
    }

    public void b(ArrayList<jda> arrayList) {
        this.e = (ArrayList) jdy.d(arrayList);
    }

    public int d() {
        return ((Integer) jdy.d(Integer.valueOf(this.f13747a))).intValue();
    }

    public void b(int i) {
        this.f13747a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String a() {
        return (String) jdy.d(this.b);
    }

    public void c(String str) {
        this.b = (String) jdy.d(str);
    }
}
