package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jqe {

    /* renamed from: a, reason: collision with root package name */
    private int f14022a;
    private int b;
    private int d;
    private int e;

    public void a(int i) {
        this.b = i;
    }

    public void e(int i) {
        this.f14022a = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public int d() {
        return this.b;
    }

    public int a() {
        return this.f14022a;
    }

    public int e() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public static List<jqe> d(int i) {
        jqe jqeVar = new jqe();
        jqeVar.a(1);
        jqeVar.e(3);
        jqeVar.c(500);
        jqeVar.b(2);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(jqeVar);
        jqe jqeVar2 = new jqe();
        jqeVar2.a(3);
        jqeVar2.e(3);
        jqeVar2.c(100);
        jqeVar2.b(2);
        arrayList.add(jqeVar2);
        jqe jqeVar3 = new jqe();
        jqeVar3.a(4);
        jqeVar3.e(3);
        if (i == 1) {
            jqeVar3.c(3600);
        } else if (i == 6) {
            jqeVar3.c(21600);
        } else {
            jqeVar3.c(3600);
        }
        jqeVar3.b(2);
        arrayList.add(jqeVar3);
        return arrayList;
    }

    public String toString() {
        return "DeviceReportThroshold{dataType=" + this.b + ", valueType=" + this.f14022a + ", value=" + this.e + ", action=" + this.d + '}';
    }
}
