package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;

/* loaded from: classes5.dex */
public class kwr extends CommonSegment {
    private static final long serialVersionUID = 4898624082127999532L;

    /* renamed from: a, reason: collision with root package name */
    private String f14665a;
    private int c;
    private int d;
    private int e;
    private int g = -1;
    private int j = -1;
    private int f = -1;
    private int b = -1;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 8;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    public String e() {
        return this.f14665a;
    }

    public void a(String str) {
        this.f14665a = str;
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int c() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public int b() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public void h(int i) {
        this.g = i;
    }

    public void e(int i) {
        this.j = i;
    }

    public void i(int i) {
        this.f = i;
    }

    public void c(int i) {
        this.b = i;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        LogUtil.a("TrackExerciseSegment", "toTrackString");
        return "";
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        if (stringBuffer != null) {
            LogUtil.a("TrackExerciseSegment", "toTrackString stringBuffer");
        }
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr != null) {
            LogUtil.a("TrackExerciseSegment", "fromTrackString");
        }
    }

    public String toString() {
        return "TrackExerciseSegment{mActionId='" + this.f14665a + ", mActionResultType=" + this.c + ", mActionResultValue=" + this.e + ", mActionTargetValue=" + this.d + ", mSectionStrengthType=" + this.g + ", mSectionStrengthStatistics=" + this.j + ", mSectionStrengthUpper=" + this.f + ", mSectionStrengthLower=" + this.b + '}';
    }
}
