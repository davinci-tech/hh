package defpackage;

import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kwq extends CommonSegment {
    private static final long serialVersionUID = -8701188853113981276L;

    /* renamed from: a, reason: collision with root package name */
    private int f14664a;
    private long b;
    private int c;
    private int d;
    private int e;
    private long f;
    private long g;
    private long h;
    private int j;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 9;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 1;
    }

    public kwq() {
        this.j = 0;
    }

    public kwq(kwq kwqVar) {
        this.j = 0;
        if (kwqVar != null) {
            this.c = kwqVar.c;
            this.g = kwqVar.g;
            this.b = kwqVar.b;
            this.f14664a = kwqVar.f14664a;
            this.e = kwqVar.e;
            this.d = kwqVar.d;
            this.f = kwqVar.f;
            this.h = kwqVar.h;
            this.j = kwqVar.j;
            return;
        }
        f();
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public long h() {
        return this.g;
    }

    public int a() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public void b(long j) {
        this.g = j;
    }

    public long d() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public int b() {
        return this.f14664a;
    }

    public void b(int i) {
        this.f14664a = i;
    }

    public int e() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public long g() {
        return this.f;
    }

    public void e(long j) {
        this.f = j;
    }

    public long j() {
        return this.h;
    }

    public void d(long j) {
        this.h = j;
    }

    public int i() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(50);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(c()).append(";").append("st=").append(h()).append(";").append("sd=").append(d()).append(";").append("shr=").append(b()).append(";").append("str=").append(g()).append(";").append("std=").append(j()).append(";").append("stp=").append(i()).append(";").append("sas=").append(a()).append(";").append("sac=").append(e()).append(";").append(System.lineSeparator());
    }

    public final void f() {
        this.g = -1L;
        this.b = -1L;
        this.f14664a = -1;
        this.f = -1L;
        this.h = -1L;
        this.d = -1;
        this.e = -1;
        this.j = 0;
    }

    public final void n() {
        this.g = 0L;
        this.b = 0L;
        this.f14664a = 0;
        this.f = 0L;
        this.h = 0L;
        this.d = 0;
        this.e = 0;
        this.j = 0;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.c = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.g = CommonUtil.b(strArr[2].split("=")[1], -1L);
        this.b = CommonUtil.b(strArr[3].split("=")[1], -1L);
        this.f14664a = CommonUtil.e(strArr[4].split("=")[1], -1);
        this.f = CommonUtil.b(strArr[5].split("=")[1], -1L);
        this.h = CommonUtil.b(strArr[6].split("=")[1], -1L);
        this.j = CommonUtil.e(strArr[7].split("=")[1], -1);
        this.e = CommonUtil.e(strArr[8].split("=")[1], -1);
        this.d = CommonUtil.e(strArr[9].split("=")[1], -1);
    }
}
