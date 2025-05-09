package com.huawei.haf.common.dfx.block;

import android.os.Looper;
import android.text.TextUtils;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import com.huawei.haf.common.dfx.DfxUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public final class MainThreadMonitor implements Printer {

    /* renamed from: a, reason: collision with root package name */
    private final ThreadMonitorTask f2080a;

    private MainThreadMonitor(MonitorCallback monitorCallback) {
        this.f2080a = new ThreadMonitorTask(Looper.getMainLooper().getThread(), monitorCallback == null ? new UiThreadMonitorHandler("HAF_UIBlockMonitor") : monitorCallback);
    }

    public static void a(MonitorCallback monitorCallback) {
        Looper.getMainLooper().setMessageLogging(new MainThreadMonitor(monitorCallback));
    }

    public static void e() {
        Looper.getMainLooper().setMessageLogging(null);
    }

    @Override // android.util.Printer
    public void println(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        char charAt = str.charAt(0);
        if (charAt == '>') {
            this.f2080a.e(str);
        } else if (charAt == '<') {
            this.f2080a.c();
        }
    }

    public static class UiThreadMonitorHandler extends AbstractMonitorHandler {

        /* renamed from: a, reason: collision with root package name */
        private MessageHistory f2083a;

        public UiThreadMonitorHandler(String str) {
            super(str, "_blockLog_%d");
        }

        @Override // com.huawei.haf.common.dfx.block.AbstractMonitorHandler, com.huawei.haf.common.dfx.block.MonitorCallback
        public void begin(Thread thread, String str, long j) {
            super.begin(thread, str, j);
            if (this.f2083a == null) {
                this.f2083a = new MessageHistory(j);
            }
        }

        @Override // com.huawei.haf.common.dfx.block.MonitorCallback
        public boolean check(Thread thread, long j, long j2) {
            if (j2 <= 0) {
                return false;
            }
            int beginTime = (int) ((j - getBeginTime()) / j2);
            if (beginTime <= 0) {
                return true;
            }
            if (beginTime > 10) {
                return false;
            }
            if (beginTime % 5 == 0) {
                saveDumpStackTraceInfo(thread, true, true, true);
                return true;
            }
            if (!LogUtil.a()) {
                saveDumpStackTraceInfo(thread, false, true, false);
            }
            return true;
        }

        @Override // com.huawei.haf.common.dfx.block.MonitorCallback
        public void end(Thread thread, Throwable th, long j, long j2) {
            if (j2 <= 0) {
                return;
            }
            long beginTime = j - getBeginTime();
            if (beginTime <= 60) {
                this.f2083a.e(j);
                return;
            }
            MessageHistory messageHistory = this.f2083a;
            messageHistory.b(new Message(messageHistory.e(j), getMessageInfo(), getBeginTime(), (int) beginTime));
            if (beginTime > 300) {
                ReleaseLogUtil.a(this.mTag, "Suspected block msg:", Long.valueOf(beginTime), ", ", getMessageInfo());
            } else if (beginTime > 120) {
                ReleaseLogUtil.b(this.mTag, "Suspected block msg:", Long.valueOf(beginTime), ", ", getMessageInfo());
            } else {
                LogUtil.d(this.mTag, "Suspected block msg:", Long.valueOf(beginTime), ", ", getMessageInfo());
            }
        }

        @Override // com.huawei.haf.common.dfx.block.AbstractMonitorHandler
        protected void dumpMessagesInfo(PrintWriter printWriter, long j, boolean z, boolean z2) {
            if (z) {
                printWriter.println("--------------------------------");
                this.f2083a.d(printWriter, j);
            }
            if (!z2 || LogUtil.a()) {
                return;
            }
            printWriter.println("--------------------------------");
            printWriter.print("TODO: ");
            Looper.getMainLooper().dump(new PrintWriterPrinter(printWriter), "");
        }
    }

    static class Message {

        /* renamed from: a, reason: collision with root package name */
        Message f2081a;
        private final int b;
        private final String c;
        private final long d;
        private final int e;

        Message(int i, String str, long j, int i2) {
            this.b = i;
            this.c = str;
            this.d = j;
            this.e = i2;
        }

        void b(PrintWriter printWriter, long j, int i, String str) {
            printWriter.append((CharSequence) str).append("Message ").append((CharSequence) String.valueOf(this.b - i)).append(": when=").append((CharSequence) String.valueOf(this.d - j)).append("ms, cost=").append((CharSequence) String.valueOf(this.e)).append("ms, info=").append((CharSequence) this.c).println();
        }
    }

    static class MessageHistory {

        /* renamed from: a, reason: collision with root package name */
        private Message f2082a;
        private Message b;
        private int c;
        private int d;
        private final long e;
        private int j;

        MessageHistory(long j) {
            this.e = j;
        }

        int e(long j) {
            int i;
            synchronized (this) {
                if (this.f2082a != null) {
                    while (true) {
                        if (j - 60000 <= this.f2082a.d) {
                            break;
                        }
                        Message message = this.f2082a.f2081a;
                        this.f2082a = message;
                        this.d--;
                        if (message == null) {
                            this.b = null;
                            break;
                        }
                    }
                }
                i = this.j;
                this.j = i + 1;
            }
            return i;
        }

        void b(Message message) {
            synchronized (this) {
                this.c++;
                if (this.f2082a == null) {
                    this.f2082a = message;
                    this.b = message;
                    this.d = 1;
                } else {
                    this.b.f2081a = message;
                    this.b = message;
                    int i = this.d;
                    if (i >= 20) {
                        this.f2082a = this.f2082a.f2081a;
                    } else {
                        this.d = i + 1;
                    }
                }
            }
        }

        void d(PrintWriter printWriter, long j) {
            synchronized (this) {
                printWriter.append("HISTORY: From ").append((CharSequence) DfxUtils.a(this.e)).append(", Total=").append((CharSequence) String.valueOf(this.j)).append(", Count(>60ms)=").append((CharSequence) String.valueOf(this.c)).append(", Last one minute list").println();
                for (Message message = this.f2082a; message != null; message = message.f2081a) {
                    message.b(printWriter, j, this.j, "  ");
                }
            }
        }
    }
}
