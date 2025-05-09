package defpackage;

import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.entity.DeviceInfo;

/* loaded from: classes8.dex */
public class biv {

    /* renamed from: a, reason: collision with root package name */
    private biu f396a;
    private DeviceInfo b;
    private FrameReceiver c;
    private int d;
    private int e;
    private long g;

    public biv(FrameReceiver frameReceiver, DeviceInfo deviceInfo, biu biuVar, int i) {
        this.c = frameReceiver;
        this.b = deviceInfo;
        this.f396a = biuVar;
        this.d = i;
    }

    public void e(long j, int i) {
        this.g = j;
        this.e = i;
    }

    public long i() {
        return this.g;
    }

    public int d() {
        return this.e;
    }

    public FrameReceiver a() {
        return this.c;
    }

    public DeviceInfo e() {
        return this.b;
    }

    public biu c() {
        return this.f396a;
    }

    public int b() {
        return this.d;
    }
}
