package defpackage;

import com.huawei.hihealth.HiHealthData;
import health.compact.a.HiCommonUtil;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class ikv {

    /* renamed from: a, reason: collision with root package name */
    private long f13418a;
    private int b;
    private List<Integer> c;
    private long d;
    private int e;
    private int f;
    private int g;
    private String h;
    private int i;
    private int j;
    private int l;

    public ikv() {
    }

    public ikv(int i) {
        this.b = i;
    }

    public ikv(String str) {
        this.h = str;
    }

    public ikv(int i, String str) {
        this.b = i;
        this.h = str;
    }

    public ikv(int i, int i2) {
        this.b = i;
        this.l = i2;
    }

    public ikv(int i, int i2, int i3, int i4) {
        this.b = i;
        this.i = i2;
        this.l = i3;
        this.e = i4;
    }

    public ikv(int i, int i2, int i3) {
        this.b = i3;
        this.i = i2;
        this.l = i;
    }

    public int i() {
        return this.j;
    }

    public void i(int i) {
        this.j = i;
    }

    public String f() {
        return this.h;
    }

    public int b() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public int e() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int d() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public int g() {
        return this.l;
    }

    public void f(int i) {
        this.l = i;
    }

    public long a() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public void b(int i) {
        this.f = i;
    }

    public int h() {
        return this.f;
    }

    public List<Integer> c() {
        return this.c;
    }

    public void c(List<Integer> list) {
        this.c = list;
    }

    public void c(int i) {
        this.g = i;
    }

    public int j() {
        return this.g;
    }

    public void a(long j) {
        this.f13418a = j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiHealthContext{app=");
        stringBuffer.append(this.b);
        stringBuffer.append(", device=").append(this.i);
        stringBuffer.append(", client=").append(this.e);
        stringBuffer.append(", who=").append(this.l);
        stringBuffer.append(", packageName='").append(this.h).append("', syncType=");
        stringBuffer.append(this.j);
        stringBuffer.append(", cloudDevice=").append(this.d);
        stringBuffer.append(", needUploadData=").append(this.f);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public static void b(HiHealthData hiHealthData, ikv ikvVar) {
        if (HiCommonUtil.b(hiHealthData)) {
            LogUtil.c("HiH_HiHealthContext", "setSource hiHealthDatas == null");
            return;
        }
        if (HiCommonUtil.b(ikvVar)) {
            LogUtil.c("HiH_HiHealthContext", "setSource hiContext == null");
            return;
        }
        hiHealthData.setUserId(ikvVar.g());
        hiHealthData.setAppId(ikvVar.e());
        hiHealthData.setDeviceId(ikvVar.d());
        hiHealthData.setClientId(ikvVar.b());
        hiHealthData.setSyncStatus(ikvVar.i());
    }
}
