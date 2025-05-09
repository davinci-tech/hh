package defpackage;

import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kws extends CommonSegment {
    private static final long serialVersionUID = 5871138592553728173L;

    /* renamed from: a, reason: collision with root package name */
    private int f14666a;
    private int b;
    private int c;
    private int d;
    private int e;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 5;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    public int d() {
        return this.f14666a;
    }

    public void e(int i) {
        this.f14666a = i;
    }

    public int b() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int a() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int e() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(30);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(d()).append(";").append("sbst=").append(b()).append(";").append("sdst=").append(a()).append(";").append("shs=").append(e()).append(";").append("sst=").append(c()).append(";").append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.f14666a = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.c = CommonUtil.e(strArr[2].split("=")[1], -1);
        this.e = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.d = CommonUtil.e(strArr[4].split("=")[1], -1);
        this.b = CommonUtil.e(strArr[5].split("=")[1], -1);
    }
}
