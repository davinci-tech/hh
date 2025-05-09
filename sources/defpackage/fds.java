package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fds {
    private int g;
    private String h;
    private int l;
    private long o;

    /* renamed from: a, reason: collision with root package name */
    public int[] f12462a = new int[8];
    private boolean f = false;
    private int b = 22103;
    private int d = 22101;
    private int i = 22104;
    private int e = 22102;
    private int c = 22105;
    private Map<String, Long> j = new HashMap(16);
    private List<Map<String, Long>> n = new ArrayList(16);

    public int f() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public Map<String, Long> j() {
        return this.j;
    }

    public void a(Map<String, Long> map) {
        this.j = map;
    }

    public List<Map<String, Long>> h() {
        return this.n;
    }

    public void e(List<Map<String, Long>> list) {
        this.n = list;
    }

    public int g() {
        return this.l;
    }

    public void j(int i) {
        this.l = i;
    }

    public int b() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public int e() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public int a() {
        return this.i;
    }

    public void a(int i) {
        this.i = i;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.c;
    }

    public boolean l() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public long m() {
        return this.o;
    }

    public void e(long j) {
        this.o = j;
    }

    public String i() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public void b(int i) {
        int[] iArr = this.f12462a;
        if (i < iArr.length && i > 0) {
            iArr[i] = iArr[i] + 1;
        } else {
            LogUtil.b("SleepDataQueryHelper", "out of range");
        }
    }

    public String toString() {
        return "SleepQueryHelperData{mDeviceId='" + this.h + "', mIdxSleep=" + this.g + ", mStartTime=" + this.o + ", mSleepDeviceType=" + this.l + ", mIsNeedOnlyManual=" + this.f + ", mDataTypeDeepSleep=" + this.b + ", mDataTypeShallowSleep=" + this.d + ", mDataTypeWakeSleep=" + this.i + ", mDataTypeDreamSleep=" + this.e + ", mDataTypeNoonSleep=" + this.c + ", mNoonMap=" + this.j + ", mNoonMapList=" + this.n + ", mSleepTypeCount=" + Arrays.toString(this.f12462a) + '}';
    }
}
