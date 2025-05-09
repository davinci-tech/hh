package defpackage;

import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import java.util.Map;

/* loaded from: classes3.dex */
public class cvk {

    /* renamed from: a, reason: collision with root package name */
    private DeviceDownloadSourceInfo f11500a;
    private String b;
    private String c;
    private String d;
    private long e;
    private String f;
    private Map<String, String> g;
    private String h;
    private int i;
    private String j = "3";
    private String k;
    private String l;
    private String m;
    private cvf n;
    private String o;

    public String b() {
        return this.h;
    }

    public void j(String str) {
        this.h = str;
    }

    public String f() {
        return (String) jdy.d(this.j);
    }

    public void i(String str) {
        this.j = (String) jdy.d(str);
    }

    public String g() {
        return (String) jdy.d(this.o);
    }

    public void f(String str) {
        this.o = (String) jdy.d(str);
    }

    public String e() {
        return (String) jdy.d(this.l);
    }

    public void d(String str) {
        this.l = (String) jdy.d(str);
    }

    public String a() {
        return (String) jdy.d(this.k);
    }

    public void a(String str) {
        this.k = (String) jdy.d(str);
    }

    public void d(long j) {
        this.e = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void b(String str) {
        this.b = (String) jdy.d(str);
    }

    public void c(String str) {
        this.c = (String) jdy.d(str);
    }

    public void c(int i) {
        this.i = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void e(String str) {
        this.d = (String) jdy.d(str);
    }

    public cvf d() {
        return (cvf) jdy.d(this.n);
    }

    public void e(cvf cvfVar) {
        this.n = (cvf) jdy.d(cvfVar);
    }

    public void g(String str) {
        this.f = str;
    }

    public void h(String str) {
        this.m = str;
    }

    public Map<String, String> i() {
        return (Map) jdy.d(this.g);
    }

    public void a(Map<String, String> map) {
        this.g = (Map) jdy.d(map);
    }

    public DeviceDownloadSourceInfo c() {
        return (DeviceDownloadSourceInfo) jdy.d(this.f11500a);
    }

    public void b(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        this.f11500a = (DeviceDownloadSourceInfo) jdy.d(deviceDownloadSourceInfo);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PluginIndexFilePlugins{, uuid='");
        stringBuffer.append(this.l);
        stringBuffer.append("', version='");
        stringBuffer.append(this.k);
        stringBuffer.append("', digest='");
        stringBuffer.append(this.b);
        stringBuffer.append("', descUrl='");
        stringBuffer.append(this.d);
        stringBuffer.append("', protocol='");
        stringBuffer.append(this.f);
        stringBuffer.append("', supportMode='");
        stringBuffer.append(this.m);
        stringBuffer.append("', versionRule='");
        stringBuffer.append(this.n.toString());
        stringBuffer.append(", downloadSource='");
        stringBuffer.append(this.f11500a.toString());
        stringBuffer.append("'}");
        return stringBuffer.toString();
    }
}
