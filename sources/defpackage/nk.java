package defpackage;

import com.careforeyou.library.enums.Protocal_Type;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class nk implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    private String f15338a;
    private String b;
    private Protocal_Type c;
    private int d = -1;
    private int e;
    private int h;

    public Protocal_Type b() {
        return this.c;
    }

    public void d(Protocal_Type protocal_Type) {
        this.c = protocal_Type;
    }

    public int i() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        return this.f15338a;
    }

    public void d(String str) {
        this.f15338a = str;
    }

    public int e() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public int d() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }
}
