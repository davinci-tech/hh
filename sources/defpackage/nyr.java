package defpackage;

import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class nyr {

    /* renamed from: a, reason: collision with root package name */
    private String f15560a;
    private int b;
    private int c;
    private String d;
    private int e;
    private Map<String, cvl> f;
    private String g;
    private Map<String, String> h;
    private String i;
    private DeviceDownloadSourceInfo j;
    private int k;
    private String m;
    private List<String> o;

    public int o() {
        return this.k;
    }

    public void e(int i) {
        this.k = i;
    }

    public String b() {
        return this.f15560a;
    }

    public void c(String str) {
        this.f15560a = str;
    }

    public String i() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public List<String> k() {
        return this.o;
    }

    public void a(List<String> list) {
        this.o = list;
    }

    public String h() {
        return this.g;
    }

    public Map<String, cvl> f() {
        return this.f;
    }

    public void e(Map<String, cvl> map) {
        this.f = map;
    }

    public void a(String str) {
        this.g = str;
    }

    public String c() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public Map<String, String> j() {
        return this.h;
    }

    public void a(Map<String, String> map) {
        this.h = map;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int d() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String n() {
        return this.m;
    }

    public void d(String str) {
        this.m = str;
    }

    public DeviceDownloadSourceInfo g() {
        return this.j;
    }

    public void d(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        this.j = deviceDownloadSourceInfo;
    }

    public int e() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public boolean equals(Object obj) {
        String str;
        if (!(obj instanceof nyr) || (str = this.f15560a) == null) {
            return false;
        }
        return str.equals(((nyr) obj).b());
    }

    public int hashCode() {
        String str = this.f15560a;
        if (str != null) {
            return str.hashCode();
        }
        return super.hashCode();
    }
}
