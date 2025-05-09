package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;

/* loaded from: classes3.dex */
public class bjc {

    /* renamed from: a, reason: collision with root package name */
    private boolean f401a;
    private HandshakeCommandBase d;

    public bjc(boolean z) {
        this(z, null);
    }

    public bjc(boolean z, HandshakeCommandBase handshakeCommandBase) {
        this.f401a = z;
        this.d = handshakeCommandBase;
    }

    public boolean b() {
        return this.f401a;
    }

    public void c(boolean z) {
        this.f401a = z;
    }

    public HandshakeCommandBase d() {
        return this.d;
    }

    public void c(HandshakeCommandBase handshakeCommandBase) {
        this.d = handshakeCommandBase;
    }
}
