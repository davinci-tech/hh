package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cvo {
    private long d = -1;

    /* renamed from: a, reason: collision with root package name */
    private long f11504a = -1;
    private long e = -1;
    private List<cvv> b = new ArrayList(5);
    private byte[] c = null;

    public long e() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public long c() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public long d() {
        return this.f11504a;
    }

    public void c(long j) {
        this.f11504a = j;
    }

    public List<cvv> a() {
        return this.b;
    }

    public void a(List<cvv> list) {
        this.b = list;
    }

    public byte[] b() {
        return this.c;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    private String f() {
        List<cvv> list = this.b;
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(5);
        Iterator<cvv> it = this.b.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    public String toString() {
        return "SamplePoint{mModifyTime=" + this.e + ", mStartTime=" + this.d + ", mEndTime=" + this.f11504a + ", mDataInfoList=" + f() + ", mMetaData=" + blt.b(blq.d(this.c)) + '}';
    }
}
