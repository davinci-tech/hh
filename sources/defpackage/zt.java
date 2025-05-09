package defpackage;

import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.List;

/* loaded from: classes8.dex */
public class zt {

    /* renamed from: a, reason: collision with root package name */
    private String f17776a;
    private String b;
    private String c;
    private List<UnstructData> d;
    private String e;
    private int f;
    private long g;
    private String h;
    private String j;

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.j = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.c = str;
    }

    public List<UnstructData> c() {
        return this.d;
    }

    public void c(List<UnstructData> list) {
        this.d = list;
    }

    public String d() {
        return this.f17776a;
    }

    public void d(String str) {
        this.e = str;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.j;
    }

    public long g() {
        return this.g;
    }

    public int h() {
        return this.f;
    }

    public String j() {
        return this.h;
    }

    public String toString() {
        return "[id = " + this.e + " , unstruct_uuuid = " + this.j + " , uuid = " + this.h + " , recycleStatus = " + this.f + ", extraParam = " + this.b + "]";
    }
}
