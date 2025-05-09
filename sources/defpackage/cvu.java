package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cvu extends cvr {
    private long d = -1;
    private long e = -1;
    private long b = -1;

    /* renamed from: a, reason: collision with root package name */
    private List<cvv> f11507a = new ArrayList(5);
    private byte[] c = null;

    public long e() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public long d() {
        return this.e;
    }

    public void d(long j) {
        this.e = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public List<cvv> a() {
        return this.f11507a;
    }

    public void e(List<cvv> list) {
        this.f11507a = list;
    }

    public byte[] c() {
        return this.c;
    }

    public void d(byte[] bArr) {
        this.c = bArr;
    }

    private String h() {
        List<cvv> list = this.f11507a;
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(5);
        Iterator<cvv> it = this.f11507a.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    @Override // defpackage.cvr
    public String toString() {
        return "SamplePoint{mSrcPkgName=" + getSrcPkgName() + ", mWearPkgName=" + getWearPkgName() + ", mCommandId=" + getCommandId() + ", mDictTypeId=" + this.d + ", mStartTime=" + this.e + ", mEndTime=" + this.b + ", mDataInfoList=" + h() + ", mMetaData=" + blt.b(blq.d(this.c)) + '}';
    }
}
