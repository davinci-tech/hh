package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.devicesdk.connect.physical.ConsumerHandler;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bia {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f384a = new Object();
    private b d;
    private HandlerThread e;

    public bia(ConsumerHandler<Message> consumerHandler) {
        HandlerThread handlerThread = new HandlerThread("SafePhysicalConnectHandler");
        this.e = handlerThread;
        if (consumerHandler != null) {
            handlerThread.start();
            this.d = new b(this.e.getLooper(), consumerHandler);
        }
    }

    public final void c(Object obj) {
        synchronized (f384a) {
            if (d()) {
                this.d.removeCallbacksAndMessages(obj);
            }
        }
    }

    public final Message rP_(int i) {
        synchronized (f384a) {
            if (d()) {
                return this.d.obtainMessage(i);
            }
            return new Message();
        }
    }

    public final boolean rQ_(Message message) {
        synchronized (f384a) {
            if (!d()) {
                return false;
            }
            return this.d.sendMessage(message);
        }
    }

    public final void c(int i) {
        synchronized (f384a) {
            if (d()) {
                this.d.removeMessages(i);
            }
        }
    }

    public final boolean d(int i, long j) {
        synchronized (f384a) {
            if (!d()) {
                return false;
            }
            return this.d.sendEmptyMessageDelayed(i, j);
        }
    }

    public final boolean b(int i) {
        synchronized (f384a) {
            if (!d()) {
                return false;
            }
            return this.d.sendEmptyMessage(i);
        }
    }

    public final boolean rR_(Message message, long j) {
        synchronized (f384a) {
            if (!d()) {
                return false;
            }
            return this.d.sendMessageDelayed(message, j);
        }
    }

    public final boolean c(Runnable runnable) {
        synchronized (f384a) {
            if (!d()) {
                return false;
            }
            return this.d.post(runnable);
        }
    }

    static class b extends Handler {
        private ConsumerHandler<Message> d;

        b(Looper looper, ConsumerHandler<Message> consumerHandler) {
            super(looper);
            this.d = consumerHandler;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ConsumerHandler<Message> consumerHandler = this.d;
            if (consumerHandler != null) {
                consumerHandler.accept(message);
            }
        }

        void a() {
            this.d = null;
        }
    }

    public void a() {
        LogUtil.c("SafePhysicalConnectHandler", "stop physical connect handler.");
        synchronized (f384a) {
            if (d()) {
                this.d.a();
                this.d = null;
            }
        }
        this.e.quitSafely();
    }

    public boolean d() {
        boolean z;
        synchronized (f384a) {
            z = this.d != null;
        }
        return z;
    }
}
