package defpackage;

import com.huawei.ads.adsrec.rank.sortation.IModelScoreData;
import java.util.List;

/* loaded from: classes2.dex */
public class vt {

    /* renamed from: a, reason: collision with root package name */
    private Integer f17718a;
    private List<String> b;
    private final List<String> c;
    private Integer d;
    private final String e;
    private final int f;
    private String g;
    private Integer h;
    private String i;
    private String j;
    private volatile IModelScoreData l;
    private String m;
    private Integer n;

    public String toString() {
        return "AdRecallParam{appPkgName='" + this.e + "', adSlotIds=" + this.c + ", maxCount=" + this.f17718a + ", adType=" + this.f + ", clientRequestId='" + this.j + "'}";
    }

    public void b(Integer num) {
        this.h = num;
    }

    public void e(Integer num) {
        this.n = num;
    }

    public void e(String str) {
        this.g = str;
    }

    public void d(String str) {
        this.i = str;
    }

    public void c(Integer num) {
        this.d = num;
    }

    public void b(String str) {
        this.m = str;
    }

    public void d(List<String> list) {
        this.b = list;
    }

    public Integer n() {
        return this.h;
    }

    public Integer l() {
        return this.n;
    }

    public Integer o() {
        return this.f17718a;
    }

    public Integer j() {
        return this.d;
    }

    public String f() {
        return this.m;
    }

    public int h() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String i() {
        return this.i;
    }

    public IModelScoreData e() {
        return this.l;
    }

    public String c() {
        return this.j;
    }

    public String a() {
        String str = this.e;
        return str == null ? this.m : str;
    }

    public List<String> b() {
        return this.b;
    }

    public void b(int i) {
        if (this.n == null) {
            this.n = Integer.valueOf(i);
        }
    }

    public List<String> d() {
        return this.c;
    }

    public vt(String str, String str2, List<String> list, Integer num, int i) {
        this.e = str;
        this.j = str2;
        this.c = list;
        this.f17718a = num;
        this.f = i;
    }
}
