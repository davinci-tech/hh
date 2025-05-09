package com.huawei.featureuserprofile.util;

import android.os.Handler;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class AsyncSelectorSerialize {
    private static final String TAG = "AsyncSelectorSerialize";
    private Handler mHandler;
    private List<Action> mActions = new ArrayList();
    private Iterator<Action> mIterator = null;
    private boolean mIsFinished = false;
    private Action mExecuting = null;

    public interface Action {
        void execute(Map map);

        int getFailedValue();
    }

    public static abstract class BaseAction implements Action {
        @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
        public abstract void execute(Map map);

        @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
        public int getFailedValue() {
            return -1;
        }
    }

    protected abstract void onFailed(int i);

    protected abstract void onSuccess(Map map);

    public AsyncSelectorSerialize(Handler handler) {
        this.mHandler = null;
        this.mHandler = handler;
    }

    public void add(Action action) {
        this.mActions.add(action);
    }

    public void postError() {
        synchronized (this) {
            Action action = this.mExecuting;
            if (action == null) {
                LogUtil.h(TAG, "postError when no Action executing,error");
                return;
            }
            this.mIsFinished = true;
            final int failedValue = action.getFailedValue();
            this.mHandler.post(new Runnable() { // from class: com.huawei.featureuserprofile.util.AsyncSelectorSerialize.2
                @Override // java.lang.Runnable
                public void run() {
                    AsyncSelectorSerialize.this.onFailed(failedValue);
                }
            });
        }
    }

    public void next(final Map map) {
        synchronized (this) {
            if (this.mIsFinished) {
                LogUtil.h(TAG, "AsyncSelectorSerialize success,why you call this");
                return;
            }
            Iterator<Action> iterator = getIterator();
            if (!iterator.hasNext()) {
                this.mIsFinished = true;
                this.mHandler.post(new Runnable() { // from class: com.huawei.featureuserprofile.util.AsyncSelectorSerialize.4
                    @Override // java.lang.Runnable
                    public void run() {
                        AsyncSelectorSerialize.this.onSuccess(map);
                    }
                });
            } else {
                final Action next = iterator.next();
                this.mExecuting = next;
                this.mHandler.post(new Runnable() { // from class: com.huawei.featureuserprofile.util.AsyncSelectorSerialize.1
                    @Override // java.lang.Runnable
                    public void run() {
                        next.execute(map);
                    }
                });
            }
        }
    }

    public void run() {
        synchronized (this) {
            this.mIterator = this.mActions.iterator();
            next(null);
        }
    }

    private Iterator<Action> getIterator() {
        Iterator<Action> it;
        synchronized (this) {
            it = this.mIterator;
        }
        return it;
    }
}
