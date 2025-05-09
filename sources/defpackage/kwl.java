package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;
import java.util.Objects;

/* loaded from: classes5.dex */
public class kwl extends CommonSegment {
    private static final long serialVersionUID = 2768423466126702180L;

    /* renamed from: a, reason: collision with root package name */
    private int f14662a;
    private int b;
    private int c;
    private int d;
    private int e;
    private long g;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 6;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    public int a() {
        return this.f14662a;
    }

    public void d(int i) {
        this.f14662a = i;
    }

    public long i() {
        return this.g;
    }

    public void e(long j) {
        this.g = j;
    }

    public int b() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public int e() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int d() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(40);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(a()).append(";").append("st=").append(i()).append(";").append("sc=").append(b()).append(";").append("spw=").append(e()).append(";").append("spp=").append(d()).append(";").append("shr=").append(c()).append(";").append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            LogUtil.h("RowingMachineSegment", "strArray is invalid");
            return;
        }
        this.f14662a = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.g = CommonUtil.b(strArr[2].split("=")[1], -1L);
        this.d = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.b = CommonUtil.e(strArr[4].split("=")[1], -1);
        this.e = CommonUtil.e(strArr[5].split("=")[1], -1);
        this.c = CommonUtil.e(strArr[6].split("=")[1], -1);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f14662a == ((kwl) obj).f14662a;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f14662a));
    }
}
