package defpackage;

import com.huawei.devicesdk.strategy.SendStrategy;

/* loaded from: classes3.dex */
public class biq {

    /* renamed from: a, reason: collision with root package name */
    private SendStrategy f390a;
    private long b;
    private bir c;

    public biq(long j, bir birVar, SendStrategy sendStrategy) {
        this.b = j;
        this.c = birVar;
        this.f390a = sendStrategy;
    }

    public bir b() {
        return this.c;
    }

    public SendStrategy d() {
        return this.f390a;
    }

    public long a() {
        return this.b;
    }
}
