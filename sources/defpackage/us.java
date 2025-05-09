package defpackage;

import com.huawei.hihealth.data.model.TrackSwimSegment;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class us {
    private Map<Integer, Float> b;
    private Map<String, String> e;
    private List<TrackSwimSegment> f;
    private Map<Double, Double> h;
    private long o = 0;

    /* renamed from: a, reason: collision with root package name */
    private float f17524a = 0.0f;
    private float j = 0.0f;
    private int i = 0;
    private int k = 0;
    private boolean d = false;
    private int g = 0;
    private int l = 0;
    private int c = 0;

    public Map<Double, Double> j() {
        return this.h;
    }

    public void c(Map<Double, Double> map) {
        this.h = map;
    }

    public float d() {
        return ((Float) jdy.d(Float.valueOf(this.f17524a))).floatValue();
    }

    public float n() {
        return this.j;
    }

    public void d(float f) {
        this.j = f;
    }

    public void c(float f) {
        this.f17524a = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    public Map<Integer, Float> b() {
        return (Map) jdy.d(this.b);
    }

    public void b(Map<Integer, Float> map) {
        this.b = (Map) jdy.d(map);
    }

    public int f() {
        return ((Integer) jdy.d(Integer.valueOf(this.i))).intValue();
    }

    public void a(Map<String, String> map) {
        this.e = map;
    }

    public void d(int i) {
        this.i = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int g() {
        return ((Integer) jdy.d(Integer.valueOf(this.k))).intValue();
    }

    public void e(int i) {
        this.k = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public boolean a() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.d))).booleanValue();
    }

    public void d(boolean z) {
        this.d = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public void c(int i) {
        this.g = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long h() {
        return ((Long) jdy.d(Long.valueOf(this.o))).longValue();
    }

    public void b(long j) {
        this.o = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.g))).intValue();
    }

    public void a(int i) {
        this.l = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<TrackSwimSegment> e() {
        return (List) jdy.d(this.f);
    }

    public void b(List<TrackSwimSegment> list) {
        this.f = list;
    }

    public int i() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("mSportType ");
        stringBuffer.append(this.g).append(" mTrackType ").append(this.l).append(System.lineSeparator());
        stringBuffer.append("mTotalDistance ").append(this.i).append("mTotalSteps").append(this.k).append(System.lineSeparator());
        stringBuffer.append("mIsSupportStep ").append(this.d).append("mAvgPace").append(this.f17524a).append(System.lineSeparator());
        stringBuffer.append("mPaceMap ").append(this.b.toString());
        return stringBuffer.toString();
    }
}
