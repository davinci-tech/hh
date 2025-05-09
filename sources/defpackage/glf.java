package defpackage;

import com.huawei.operation.utils.Constants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\bHÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/huawei/health/trusport_ee/Sample;", "", "timestamp", "", "speed", "", "steprate", "hr", "", "(JFFI)V", "getHr", "()I", "getSpeed", "()F", "getSteprate", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", WatchFaceConstant.HASHCODE, "toString", "", "Builder", "trusport_ee_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class glf {

    /* renamed from: a, reason: collision with root package name */
    private final long f12848a;
    private final int b;
    private final float c;
    private final float d;

    public glf(long j, float f, float f2, int i) {
        this.f12848a = j;
        this.d = f;
        this.c = f2;
        this.b = i;
    }

    /* renamed from: b, reason: from getter */
    public final long getF12848a() {
        return this.f12848a;
    }

    public /* synthetic */ glf(long j, float f, float f2, int i, int i2, uib uibVar) {
        this((i2 & 1) != 0 ? 0L : j, (i2 & 2) != 0 ? glt.b() : f, (i2 & 4) != 0 ? glt.e() : f2, (i2 & 8) != 0 ? glt.a() : i);
    }

    /* renamed from: d, reason: from getter */
    public final float getD() {
        return this.d;
    }

    /* renamed from: e, reason: from getter */
    public final float getC() {
        return this.c;
    }

    /* renamed from: a, reason: from getter */
    public final int getB() {
        return this.b;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/huawei/health/trusport_ee/Sample$Builder;", "", "timestamp", "", "(J)V", "hr", "", "speed", "", "steprate", "build", "Lcom/huawei/health/trusport_ee/Sample;", "setHr", "value", "setSpeed", "setSteprate", "trusport_ee_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class c {
        private final long e;
        private float b = glt.b();
        private float c = glt.e();

        /* renamed from: a, reason: collision with root package name */
        private int f12849a = glt.a();

        public c(long j) {
            this.e = j;
        }

        public final c b(float f) {
            this.b = f;
            return this;
        }

        public final c d(float f) {
            this.c = f;
            return this;
        }

        public final c e(int i) {
            this.f12849a = i;
            return this;
        }

        public final glf d() {
            return new glf(this.e, this.b, this.c, this.f12849a);
        }
    }

    public String toString() {
        return "Sample(timestamp=" + this.f12848a + ", speed=" + this.d + ", steprate=" + this.c + ", hr=" + this.b + Constants.RIGHT_BRACKET_ONLY;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.f12848a) * 31) + Float.hashCode(this.d)) * 31) + Float.hashCode(this.c)) * 31) + Integer.hashCode(this.b);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof glf)) {
            return false;
        }
        glf glfVar = (glf) other;
        return this.f12848a == glfVar.f12848a && Float.compare(this.d, glfVar.d) == 0 && Float.compare(this.c, glfVar.c) == 0 && this.b == glfVar.b;
    }

    public glf() {
        this(0L, 0.0f, 0.0f, 0, 15, null);
    }
}
