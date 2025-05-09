package defpackage;

/* loaded from: classes3.dex */
public class cvp extends cvr {

    /* renamed from: a, reason: collision with root package name */
    private long f11505a = -1;
    private int d = -1;
    private byte[] b = null;

    public long e() {
        return this.f11505a;
    }

    public void a(long j) {
        this.f11505a = j;
    }

    public int a() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public byte[] c() {
        return this.b;
    }

    public void e(byte[] bArr) {
        this.b = bArr;
    }

    @Override // defpackage.cvr
    public String toString() {
        return "SampleEvent{mSrcPkgName=" + getSrcPkgName() + ", mWearPkgName=" + getWearPkgName() + ", mCommandId=" + getCommandId() + ", mEventId=" + this.f11505a + ", mEventLevel=" + this.d + ", mEventData=" + blt.b(blq.d(this.b)) + '}';
    }
}
