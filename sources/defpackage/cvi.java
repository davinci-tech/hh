package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class cvi extends cvr {
    private long b = -1;
    private List<cvo> d = new ArrayList(5);
    private int e = -1;

    /* renamed from: a, reason: collision with root package name */
    private int f11498a = Integer.MAX_VALUE;

    public int c() {
        return this.f11498a;
    }

    public void a(int i) {
        this.f11498a = i;
    }

    public long d() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public List<cvo> b() {
        return this.d;
    }

    public void b(List<cvo> list) {
        this.d = list;
    }

    public void d(int i) {
        this.e = i;
    }

    @Override // defpackage.cvr
    public String toString() {
        return "SamplePoint{mSrcPkgName=" + getSrcPkgName() + ", mWearPkgName=" + getWearPkgName() + ", mCommandId=" + getCommandId() + ", mDictTypeId=" + this.b + this.d + '}';
    }
}
