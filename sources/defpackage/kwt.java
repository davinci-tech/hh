package defpackage;

import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kwt extends CommonSegment {
    private static final long serialVersionUID = -5518080497760210736L;

    /* renamed from: a, reason: collision with root package name */
    private int f14667a;
    private int b;
    private int c;
    private int d;
    private int e;
    private long f;
    private int g;
    private int h;
    private long i;
    private int j;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 10;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    public int b() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public long g() {
        return this.i;
    }

    public void b(long j) {
        this.i = j;
    }

    public int d() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int h() {
        return this.h;
    }

    public void f(int i) {
        this.h = i;
    }

    public int i() {
        return this.g;
    }

    public void g(int i) {
        this.g = i;
    }

    public int e() {
        return this.f14667a;
    }

    public void a(int i) {
        this.f14667a = i;
    }

    public int c() {
        return this.e;
    }

    public int a() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public long j() {
        return this.f;
    }

    public void d(long j) {
        this.f = j;
    }

    public int f() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(80);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(b()).append(";").append("st=").append(g()).append(";").append("shr=").append(d()).append(";").append("stp=").append(h()).append(";").append("stsn=").append(i()).append(";").append("sass=").append(e()).append(";").append("smsn=").append(c()).append(";").append("sin=").append(a()).append(";").append("srt=").append(j()).append(";").append("stc=").append(f()).append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.d = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.i = CommonUtil.e(strArr[2].split("=")[1], -1);
        this.c = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.h = CommonUtil.e(strArr[4].split("=")[1], -1);
        this.g = CommonUtil.e(strArr[5].split("=")[1], -1);
        this.f14667a = CommonUtil.e(strArr[6].split("=")[1], -1);
        this.e = CommonUtil.e(strArr[7].split("=")[1], -1);
        this.b = CommonUtil.e(strArr[8].split("=")[1], -1);
        this.f = CommonUtil.e(strArr[9].split("=")[1], -1);
        this.j = CommonUtil.e(strArr[10].split("=")[1], -1);
    }
}
