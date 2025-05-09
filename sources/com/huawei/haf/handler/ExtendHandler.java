package com.huawei.haf.handler;

import android.os.Message;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public interface ExtendHandler extends Executor {
    boolean hasMessages(int i);

    void postTask(Runnable runnable);

    void postTask(Runnable runnable, long j);

    void quit(boolean z);

    void removeMessages(int i);

    void removeTasks(Runnable runnable);

    void removeTasksAndMessages();

    void sendEmptyMessage(int i);

    void sendEmptyMessage(int i, long j);

    void sendMessage(Message message);

    void sendMessage(Message message, long j);
}
