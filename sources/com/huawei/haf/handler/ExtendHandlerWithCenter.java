package com.huawei.haf.handler;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class ExtendHandlerWithCenter implements ExtendHandler {

    /* renamed from: a, reason: collision with root package name */
    private final HandlerCenter f2113a;
    private final Handler.Callback c;
    private final String d;

    ExtendHandlerWithCenter(HandlerCenter handlerCenter, Handler.Callback callback, String str) {
        this.f2113a = handlerCenter;
        this.c = callback;
        this.d = str;
        if (callback instanceof BaseHandlerCallback) {
            ((BaseHandlerCallback) callback).setExtentHandler(this);
        }
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void postTask(Runnable runnable) {
        this.f2113a.yw_(runnable, this.c, 0L, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void postTask(Runnable runnable, long j) {
        this.f2113a.yw_(runnable, this.c, j, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendEmptyMessage(int i) {
        this.f2113a.yA_(Message.obtain((Handler) null, i), this.c, 0L, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendEmptyMessage(int i, long j) {
        this.f2113a.yA_(Message.obtain((Handler) null, i), this.c, j, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendMessage(Message message) {
        this.f2113a.yA_(message, this.c, 0L, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void sendMessage(Message message, long j) {
        this.f2113a.yA_(message, this.c, j, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public boolean hasMessages(int i) {
        return this.f2113a.yv_(i, this.c, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void removeTasks(Runnable runnable) {
        this.f2113a.yz_(runnable, this.c, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void removeMessages(int i) {
        this.f2113a.yy_(i, this.c, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void removeTasksAndMessages() {
        this.f2113a.yx_(this.c, this.d);
    }

    @Override // com.huawei.haf.handler.ExtendHandler
    public void quit(boolean z) {
        this.f2113a.e(this.d, z);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f2113a.yw_(runnable, this.c, 0L, this.d);
    }
}
