package com.huawei.haf.handler;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
class TaskHandler extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private final String f2118a;

    protected void yI_(Message message) {
    }

    TaskHandler(Looper looper) {
        super(looper);
        this.f2118a = String.valueOf(System.identityHashCode(this));
    }

    protected final boolean yJ_(Message message, long j, Handler.Callback callback) {
        yH_(message, callback);
        if (sendMessageDelayed(message, j)) {
            return true;
        }
        yG_(message);
        return false;
    }

    protected final String b() {
        return this.f2118a;
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        Handler.Callback yG_ = yG_(message);
        if (yG_ == null) {
            super.dispatchMessage(message);
        } else if (!yG_.handleMessage(message)) {
            super.dispatchMessage(message);
        }
        yI_(message);
    }

    private void yH_(Message message, Handler.Callback callback) {
        if (message.obj == this) {
            return;
        }
        if (message.obj != null) {
            message.getData().putBinder(this.f2118a, new MessageObjectWapper(message.obj));
        }
        message.obj = callback;
    }

    private Handler.Callback yG_(Message message) {
        if (message.obj == this) {
            return null;
        }
        Handler.Callback callback = message.obj instanceof Handler.Callback ? (Handler.Callback) message.obj : null;
        message.obj = null;
        Bundle peekData = message.peekData();
        if (peekData != null) {
            IBinder binder = peekData.getBinder(this.f2118a);
            if (binder instanceof MessageObjectWapper) {
                message.obj = ((MessageObjectWapper) binder).b;
                peekData.remove(this.f2118a);
            }
        }
        return callback;
    }

    static class MessageObjectWapper extends Binder {
        final Object b;

        MessageObjectWapper(Object obj) {
            this.b = obj;
        }
    }
}
