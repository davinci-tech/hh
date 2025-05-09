package com.huawei.haf.handler;

import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.HandlerCenter;

/* loaded from: classes.dex */
final class ExtendHandlerWithMain implements ExtendHandler {

    /* renamed from: a, reason: collision with root package name */
    private final TaskHandler f2114a;
    private final Handler.Callback b;

    @Override // com.huawei.haf.handler.ExtendHandler
    public void quit(boolean z) {
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void postTask(Runnable runnable) {
        TaskHandler taskHandler = this.f2114a;
        taskHandler.yJ_(Message.obtain(taskHandler, runnable), 0L, this.b);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void postTask(Runnable runnable, long j) {
        TaskHandler taskHandler = this.f2114a;
        taskHandler.yJ_(Message.obtain(taskHandler, runnable), j, this.b);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendEmptyMessage(int i) {
        yr_(Message.obtain(this.f2114a, i), this.b, 0L);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendEmptyMessage(int i, long j) {
        yr_(Message.obtain(this.f2114a, i), this.b, j);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendMessage(Message message) {
        yr_(message, this.b, 0L);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendMessage(Message message, long j) {
        yr_(message, this.b, j);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public boolean hasMessages(int i) {
        return this.f2114a.hasMessages(i, this.b);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void removeTasks(Runnable runnable) {
        this.f2114a.removeCallbacks(runnable, this.b);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void removeMessages(int i) {
        this.f2114a.removeMessages(i, this.b);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void removeTasksAndMessages() {
        this.f2114a.removeCallbacksAndMessages(this.b);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        TaskHandler taskHandler = this.f2114a;
        taskHandler.yJ_(Message.obtain(taskHandler, runnable), 0L, this.b);
    }

    private void yr_(Message message, Handler.Callback callback, long j) {
        if (message.getCallback() == null && (callback instanceof HandlerCenter.OnlyTaskHandlerCallback)) {
            throw new UnsupportedOperationException("The Handler.Callback used does not support processing Message.what messages");
        }
        this.f2114a.yJ_(message, j, this.b);
    }
}
