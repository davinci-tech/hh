package defpackage;

import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import java.util.Map;

/* loaded from: classes.dex */
public class msa {

    /* renamed from: a, reason: collision with root package name */
    private String f15141a;
    private String b;
    private DeviceDownloadSourceInfo c;
    private long d;
    private String e;
    private String f;
    private Map<String, String> g;
    private String h = "3";
    private int i;
    private String j;
    private msk l;
    private String m;
    private String n;
    private String o;

    public String g() {
        return (String) jdy.d(this.h);
    }

    public void h(String str) {
        this.h = (String) jdy.d(str);
    }

    public String k() {
        return (String) jdy.d(this.o);
    }

    public void g(String str) {
        this.o = (String) jdy.d(str);
    }

    public String b() {
        return (String) jdy.d(this.m);
    }

    public void e(String str) {
        this.m = (String) jdy.d(str);
    }

    public String h() {
        return (String) jdy.d(this.n);
    }

    public void b(String str) {
        this.n = (String) jdy.d(str);
    }

    public long c() {
        return ((Long) jdy.d(Long.valueOf(this.d))).longValue();
    }

    public void b(long j) {
        this.d = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public String d() {
        return (String) jdy.d(this.f15141a);
    }

    public void c(String str) {
        this.f15141a = (String) jdy.d(str);
    }

    public String a() {
        return (String) jdy.d(this.b);
    }

    public void a(String str) {
        this.b = (String) jdy.d(str);
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.i))).intValue();
    }

    public void e(int i) {
        this.i = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void d(String str) {
        this.e = (String) jdy.d(str);
    }

    public msk j() {
        return (msk) jdy.d(this.l);
    }

    public void d(msk mskVar) {
        this.l = (msk) jdy.d(mskVar);
    }

    public String i() {
        return this.j;
    }

    public void j(String str) {
        this.j = str;
    }

    public String n() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public Map<String, String> f() {
        return (Map) jdy.d(this.g);
    }

    public void e(Map<String, String> map) {
        this.g = (Map) jdy.d(map);
    }

    public void d(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        this.c = (DeviceDownloadSourceInfo) jdy.d(deviceDownloadSourceInfo);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PluginIndexFilePlugins{, uuid='");
        stringBuffer.append(this.m);
        stringBuffer.append("', version='");
        stringBuffer.append(this.n);
        stringBuffer.append("', digest='");
        stringBuffer.append(this.f15141a);
        stringBuffer.append("', descUrl='");
        stringBuffer.append(this.e);
        stringBuffer.append("', protocol='");
        stringBuffer.append(this.j);
        stringBuffer.append("', supportMode='");
        stringBuffer.append(this.f);
        stringBuffer.append("', versionRule='");
        msk mskVar = this.l;
        if (mskVar != null) {
            stringBuffer.append(mskVar.toString());
        }
        stringBuffer.append(", downloadSource='");
        DeviceDownloadSourceInfo deviceDownloadSourceInfo = this.c;
        if (deviceDownloadSourceInfo != null) {
            stringBuffer.append(deviceDownloadSourceInfo.toString());
        }
        stringBuffer.append("'}");
        return stringBuffer.toString();
    }
}
