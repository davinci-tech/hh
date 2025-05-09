package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.ui.openservice.OpenServiceUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
public class cnt {

    /* renamed from: a, reason: collision with root package name */
    private int f801a;
    private int ab;
    private cnv b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int i;
    private String k;
    private String l;
    private String m;
    private String n;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String w;
    private int x;
    private String y;
    private int h = 1;
    private int o = 0;
    private int j = 0;
    private String v = OpenServiceUtil.Source.THIRD_H5;

    public String e() {
        return this.p;
    }

    public void k(String str) {
        this.p = str;
    }

    public void r(String str) {
        this.u = str;
    }

    public String c() {
        return this.l;
    }

    public void f(String str) {
        this.l = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public int a() {
        return this.ab;
    }

    public void h(int i) {
        this.ab = i;
    }

    public void m(String str) {
        this.r = str;
    }

    public void l(String str) {
        this.s = str;
    }

    public void b(String str) {
        this.e = str;
    }

    public String b() {
        return this.y;
    }

    public void q(String str) {
        this.y = str;
    }

    public void i(String str) {
        this.k = str;
    }

    public void g(int i) {
        this.x = i;
    }

    public void d(int i) {
        this.f801a = i;
    }

    public void e(String str) {
        this.g = str;
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.b = (cnv) HiJsonUtil.e(str, cnv.class);
    }

    public void g(String str) {
        this.f = str;
    }

    public void e(int i) {
        this.h = i;
    }

    public void b(int i) {
        this.o = i;
    }

    public void c(int i) {
        this.j = i;
    }

    public void j(String str) {
        this.m = str;
    }

    public void s(String str) {
        this.v = str;
    }

    public void n(String str) {
        this.t = str;
    }

    public void o(String str) {
        this.q = str;
    }

    public void t(String str) {
        this.w = str;
    }

    public void h(String str) {
        this.n = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public static void a(List<cnt> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new Comparator<cnt>() { // from class: cnt.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(cnt cntVar, cnt cntVar2) {
                return cntVar.a() - cntVar2.a();
            }
        });
    }
}
