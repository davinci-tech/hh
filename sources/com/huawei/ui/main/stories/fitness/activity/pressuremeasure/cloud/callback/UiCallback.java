package com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback;

import android.os.Handler;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback;

/* loaded from: classes6.dex */
public abstract class UiCallback<T> {
    public boolean isCanceled() {
        return false;
    }

    public abstract void onFailure(int i, String str);

    public void onProgress(long j, long j2) {
    }

    public abstract void onSuccess(T t);

    public void onSuccess(Handler handler, final T t) {
        if (handler != null) {
            handler.post(new Runnable() { // from class: puo
                @Override // java.lang.Runnable
                public final void run() {
                    UiCallback.this.m820x621b175a(t);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$onSuccess$0$com-huawei-ui-main-stories-fitness-activity-pressuremeasure-cloud-callback-UiCallback, reason: not valid java name */
    public /* synthetic */ void m820x621b175a(Object obj) {
        if (obj != 0) {
            onSuccess(obj);
        }
    }
}
