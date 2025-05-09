package com.huawei.ui.commonui.linechart.utils;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class AsyncSelectorMultiRun {
    private Handler mHandler;
    private List<Action> mActions = new ArrayList();
    private boolean mIsExecuting = false;
    private boolean mIsFinished = false;

    public interface Action {
        void execute(Map map);
    }

    public static class e implements Action {
        @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun.Action
        public void execute(Map map) {
        }
    }

    protected abstract void onFailed(int i);

    protected abstract void onSuccess();

    public AsyncSelectorMultiRun(Handler handler) {
        this.mHandler = handler;
    }

    public void add(Action action) {
        synchronized (this) {
            if (this.mIsExecuting) {
                throw new RuntimeException("mIsExecuting not allow add action!!!:" + action);
            }
            this.mActions.add(action);
        }
    }

    public void actionEnd(Action action) {
        synchronized (this) {
            if (this.mIsFinished) {
                return;
            }
            if (this.mActions.contains(action)) {
                this.mActions.remove(action);
                if (this.mActions.size() == 0) {
                    onSuccess();
                    this.mIsFinished = true;
                }
            }
        }
    }

    public void run() {
        synchronized (this) {
            this.mIsExecuting = true;
            for (final Action action : this.mActions) {
                if (!(action instanceof e)) {
                    this.mHandler.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun.1
                        @Override // java.lang.Runnable
                        public void run() {
                            action.execute(null);
                        }
                    });
                }
            }
        }
    }
}
