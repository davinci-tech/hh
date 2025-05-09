package defpackage;

import com.huawei.wearengine.p2p.SendCallback;
import java.util.TimerTask;

/* loaded from: classes9.dex */
public class tqa extends TimerTask {

    /* renamed from: a, reason: collision with root package name */
    private String f17341a;
    private final Object b;
    private SendCallback c;
    private boolean d;

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        synchronized (this.b) {
            tov.a("TimeoutTask", "sendUuid:" + this.f17341a + ", TimeoutTask is complete.");
            if (b()) {
                tov.a("TimeoutTask", "sendUuid:" + this.f17341a + ", TimeoutTask is cancelled.");
                return;
            }
            cancel();
            SendCallback sendCallback = this.c;
            if (sendCallback != null) {
                sendCallback.onSendResult(206);
            }
        }
    }

    @Override // java.util.TimerTask
    public boolean cancel() {
        this.d = true;
        return super.cancel();
    }

    boolean b() {
        return this.d;
    }

    public final Object e() {
        return this.b;
    }
}
