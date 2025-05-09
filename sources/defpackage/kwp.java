package defpackage;

import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kwp extends CommonSegment {
    private static final long serialVersionUID = -447041175639210488L;

    /* renamed from: a, reason: collision with root package name */
    private int f14663a;
    private int b = 0;
    private long c;
    private int d;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 4;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 1;
    }

    public int b() {
        return this.f14663a;
    }

    public void c(int i) {
        this.f14663a = i;
    }

    public long c() {
        return this.c;
    }

    public void d(long j) {
        this.c = j;
    }

    public int a() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(50);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(b()).append(";").append("st=").append(c()).append(";").append("shr=").append(a()).append(";").append("stp=").append(d()).append(";").append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.f14663a = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.c = CommonUtil.b(strArr[2].split("=")[1], -1L);
        this.d = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.b = CommonUtil.e(strArr[4].split("=")[1], -1);
    }
}
