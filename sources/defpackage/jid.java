package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jid {

    /* renamed from: a, reason: collision with root package name */
    private int f13863a;
    private int b;
    private int c;
    private int d;

    public void d(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public void a(int i) {
        this.f13863a = i;
    }

    public int c() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int a() {
        return this.d;
    }

    public int e() {
        return this.f13863a;
    }

    public static List<jid> e(int i) {
        jid jidVar = new jid();
        jidVar.d(1);
        jidVar.c(3);
        jidVar.b(500);
        jidVar.a(2);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(jidVar);
        jid jidVar2 = new jid();
        jidVar2.d(3);
        jidVar2.c(3);
        jidVar2.b(100);
        jidVar2.a(2);
        arrayList.add(jidVar2);
        jid jidVar3 = new jid();
        jidVar3.d(4);
        jidVar3.c(3);
        if (i == 1) {
            jidVar3.b(3600);
        } else if (i == 6) {
            jidVar3.b(21600);
        } else {
            jidVar3.b(3600);
        }
        jidVar3.a(2);
        arrayList.add(jidVar3);
        return arrayList;
    }

    public String toString() {
        return "DeviceReportThroshold{dataType=" + this.b + ", valueType=" + this.c + ", value=" + this.d + ", action=" + this.f13863a + '}';
    }
}
