package defpackage;

import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.callback.StatusCallback;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes8.dex */
public class bka {
    private CopyOnWriteArraySet<FrameReceiver> b;
    private CopyOnWriteArraySet<StatusCallback> e;

    public CopyOnWriteArraySet<StatusCallback> a() {
        return this.e;
    }

    public CopyOnWriteArraySet<FrameReceiver> b() {
        return this.b;
    }

    private bka() {
        this.e = new CopyOnWriteArraySet<>();
        this.b = new CopyOnWriteArraySet<>();
    }

    public static bka c() {
        return c.e;
    }

    public void c(StatusCallback statusCallback) {
        this.e.add(statusCallback);
    }

    public void d(StatusCallback statusCallback) {
        this.e.remove(statusCallback);
    }

    public void c(FrameReceiver frameReceiver) {
        this.b.add(frameReceiver);
    }

    public void b(FrameReceiver frameReceiver) {
        this.b.remove(frameReceiver);
    }

    static class c {
        private static bka e = new bka();
    }
}
