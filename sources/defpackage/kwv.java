package defpackage;

import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kwv extends CommonSegment {
    private static final long serialVersionUID = 4898624082127999532L;

    /* renamed from: a, reason: collision with root package name */
    private long f14669a;
    private long b;
    private long c;
    private long d;
    private int e;
    private int f;
    private long g;
    private int h;
    private int i;
    private int j;
    private long m;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 11;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    public int j() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public long n() {
        return this.m;
    }

    public void g(long j) {
        this.m = j;
    }

    public long c() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public int h() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public int a() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public long i() {
        return this.g;
    }

    public void e(long j) {
        this.g = j;
    }

    public long e() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public long d() {
        return this.f14669a;
    }

    public void c(long j) {
        this.f14669a = j;
    }

    public long b() {
        return this.d;
    }

    public void d(long j) {
        this.d = j;
    }

    public int f() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    public int g() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(40);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(j()).append(";").append("st=").append(n()).append(";").append("skid=").append(c()).append(";").append("msp=").append(h()).append(";").append("asp=").append(a()).append(";").append("sgps=").append(i()).append(";").append("egps=").append(e()).append(";").append("csgps=").append(d()).append(";").append("cegps=").append(b()).append(";").append("sd=").append(f()).append(";").append("sp=").append(g()).append(";").append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.i = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.m = CommonUtil.e(strArr[2].split("=")[1], -1);
        this.b = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.f = CommonUtil.e(strArr[4].split("=")[1], -1);
        this.e = CommonUtil.e(strArr[5].split("=")[1], -1);
        this.g = CommonUtil.e(strArr[6].split("=")[1], -1);
        this.c = CommonUtil.e(strArr[7].split("=")[1], -1);
        this.f14669a = CommonUtil.e(strArr[8].split("=")[1], -1);
        this.d = CommonUtil.e(strArr[9].split("=")[1], -1);
        this.j = CommonUtil.e(strArr[10].split("=")[1], -1);
        this.h = CommonUtil.e(strArr[11].split("=")[1], -1);
    }
}
