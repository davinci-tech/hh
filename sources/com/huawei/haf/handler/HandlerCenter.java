package com.huawei.haf.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.text.TextUtils;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class HandlerCenter implements Executor {

    /* renamed from: a, reason: collision with root package name */
    private static volatile HandlerCenter f2115a;
    private final String b;
    private final long c;
    private final int d;
    private final Map<String, AutoTaskHandler> e = new HashMap();

    public static final class OnlyTaskHandlerCallback implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            return false;
        }
    }

    private HandlerCenter(String str, int i, long j) {
        this.b = str;
        this.d = i;
        this.c = e(j);
    }

    public static HandlerCenter d() {
        if (f2115a == null) {
            synchronized (HandlerCenter.class) {
                if (f2115a == null) {
                    f2115a = e("haf_hc_", 0, 60000L);
                }
            }
        }
        return f2115a;
    }

    public static HandlerCenter a(String str) {
        return new HandlerCenter(str, 10, 60000L);
    }

    public static HandlerCenter e(String str, int i, long j) {
        return new HandlerCenter(str, i, j);
    }

    public static ExtendHandler e(String str) {
        return yu_(null, null, str);
    }

    public static ExtendHandler yt_(Handler.Callback callback, String str) {
        return yu_(null, callback, str);
    }

    public static ExtendHandler yu_(HandlerCenter handlerCenter, Handler.Callback callback, String str) {
        return new ExtendHandlerWithCenter(b(handlerCenter), ys_(callback), c(str));
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        yw_(runnable, null, 0L, c((String) null));
    }

    public void e(String str, boolean z) {
        if ("default".equals(str) && f2115a == this) {
            LogUtil.a("HAF_HandlerCenter", "Can't actively quit this default handler");
        } else {
            b(str, z);
        }
    }

    public void a(Runnable runnable) {
        yw_(runnable, null, 0L, c((String) null));
    }

    public void e(Runnable runnable, long j) {
        yw_(runnable, null, j, c((String) null));
    }

    public void d(Runnable runnable, String str) {
        yw_(runnable, null, 0L, c(str));
    }

    void yw_(Runnable runnable, Handler.Callback callback, long j, String str) {
        if (runnable == null) {
            return;
        }
        synchronized (this.e) {
            AutoTaskHandler autoTaskHandler = this.e.get(str);
            if (autoTaskHandler == null) {
                autoTaskHandler = b(str);
            }
            if (!autoTaskHandler.yE_(runnable, j, callback)) {
                b(str, true);
                yw_(runnable, callback, j, str);
            }
        }
    }

    void yA_(Message message, Handler.Callback callback, long j, String str) {
        if (message.getCallback() == null && (callback instanceof OnlyTaskHandlerCallback)) {
            throw new UnsupportedOperationException("The Handler.Callback used does not support processing Message.what messages");
        }
        synchronized (this.e) {
            AutoTaskHandler autoTaskHandler = this.e.get(str);
            if (autoTaskHandler == null) {
                autoTaskHandler = b(str);
            }
            if (!autoTaskHandler.yD_(message, j, callback)) {
                b(str, true);
                yA_(message, callback, j, str);
            }
        }
    }

    boolean yv_(int i, Handler.Callback callback, String str) {
        AutoTaskHandler d = d(str);
        if (d != null) {
            return d.hasMessages(i, callback);
        }
        return false;
    }

    void yz_(Runnable runnable, Handler.Callback callback, String str) {
        AutoTaskHandler d = d(str);
        if (d != null) {
            d.removeCallbacks(runnable, callback);
        }
    }

    void yy_(int i, Handler.Callback callback, String str) {
        AutoTaskHandler d = d(str);
        if (d != null) {
            d.removeMessages(i, callback);
        }
    }

    void yx_(Handler.Callback callback, String str) {
        AutoTaskHandler d = d(str);
        if (d != null) {
            d.removeCallbacksAndMessages(callback);
        }
    }

    static Handler.Callback ys_(Handler.Callback callback) {
        return callback == null ? new OnlyTaskHandlerCallback() : callback;
    }

    private static HandlerCenter b(HandlerCenter handlerCenter) {
        return handlerCenter == null ? d() : handlerCenter;
    }

    private static String c(String str) {
        return TextUtils.isEmpty(str) ? "default" : str;
    }

    private AutoTaskHandler b(String str) {
        HandlerThread handlerThread = new HandlerThread(e(str, this.b), this.d);
        handlerThread.start();
        AutoTaskHandler autoTaskHandler = new AutoTaskHandler(this, handlerThread.getLooper(), str);
        this.e.put(str, autoTaskHandler);
        LogUtil.c("HAF_HandlerCenter", "add ", autoTaskHandler, ", size=", Integer.valueOf(this.e.size()));
        return autoTaskHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z) {
        synchronized (this.e) {
            AutoTaskHandler f = f(str);
            if (f != null) {
                f.b(z);
            }
        }
    }

    private AutoTaskHandler f(String str) {
        AutoTaskHandler remove = this.e.remove(str);
        if (remove != null) {
            LogUtil.c("HAF_HandlerCenter", "remove ", remove, ", size=", Integer.valueOf(this.e.size()));
        }
        return remove;
    }

    private AutoTaskHandler d(String str) {
        AutoTaskHandler autoTaskHandler;
        synchronized (this.e) {
            autoTaskHandler = this.e.get(str);
        }
        return autoTaskHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c() {
        int size;
        synchronized (this.e) {
            size = this.e.size();
        }
        return size;
    }

    private static String e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str2 + str;
    }

    private static long e(long j) {
        if (j == 0) {
            return 0L;
        }
        return Math.min(Math.max(j, PreConnectManager.CONNECT_INTERNAL), 300000L);
    }

    static class AutoTaskHandler extends TaskHandler {
        private static Field b = ReflectionUtils.a((Class<?>) MessageQueue.class, "mMessages");

        /* renamed from: a, reason: collision with root package name */
        private final String f2116a;
        private volatile boolean c;
        private final HandlerCenter e;

        AutoTaskHandler(HandlerCenter handlerCenter, Looper looper, String str) {
            super(looper);
            this.e = handlerCenter;
            this.f2116a = str;
        }

        boolean yE_(Runnable runnable, long j, Handler.Callback callback) {
            if (this.c) {
                return false;
            }
            return yD_(Message.obtain(this, runnable), j, callback);
        }

        boolean yD_(Message message, long j, Handler.Callback callback) {
            if (this.c || !yJ_(message, j, callback)) {
                return false;
            }
            a();
            return true;
        }

        void b(boolean z) {
            this.c = true;
            if (z) {
                getLooper().quitSafely();
            } else {
                getLooper().quit();
            }
        }

        @Override // com.huawei.haf.handler.TaskHandler
        protected void yI_(Message message) {
            if (this.c || !yB_(message)) {
                return;
            }
            if (d()) {
                a();
                return;
            }
            if (c()) {
                synchronized (this.e.e) {
                    if (!d()) {
                        this.e.b(this.f2116a, true);
                        return;
                    }
                }
            }
            a();
            LogUtil.c("HAF_HandlerCenter", "idle ", this, ", cur size=", Integer.valueOf(this.e.c()));
        }

        @Override // android.os.Handler
        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("TaskHandler{handlerKey=");
            sb.append(this.f2116a);
            if (!TextUtils.isEmpty(this.e.b)) {
                sb.append(", prefix=");
                sb.append(this.e.b);
            }
            sb.append(", idleTime=");
            sb.append(this.e.c);
            sb.append(", HC=");
            sb.append(System.identityHashCode(this.e));
            sb.append(", TH=");
            sb.append(b());
            sb.append("}");
            return sb.toString();
        }

        private boolean c() {
            return (this.e.c == 0 || b == null) ? false : true;
        }

        private boolean yB_(Message message) {
            return message.what == 100 && message.obj == this;
        }

        private void a() {
            removeMessages(100, this);
            Message obtain = Message.obtain(this, 100);
            obtain.obj = this;
            sendMessageDelayed(obtain, c() ? this.e.c : 600000L);
        }

        private boolean d() {
            Field field = b;
            if (field == null) {
                return true;
            }
            try {
                Object obj = field.get(getLooper().getQueue());
                if (!(obj instanceof Message)) {
                    return false;
                }
                Message message = (Message) obj;
                if (message.getTarget() != this) {
                    LogUtil.a("HAF_HandlerCenter", "exist other handler message in the queue. handler=", message.getTarget());
                }
                return true;
            } catch (IllegalAccessException e) {
                LogUtil.a("HAF_HandlerCenter", "isExistMessages invoke error, ex=", LogUtil.a(e));
                b = null;
                return true;
            }
        }
    }
}
