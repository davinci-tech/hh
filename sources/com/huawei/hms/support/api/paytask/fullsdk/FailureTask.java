package com.huawei.hms.support.api.paytask.fullsdk;

import android.app.Activity;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class FailureTask<T extends Result> extends Task<T> {

    /* renamed from: a, reason: collision with root package name */
    private int f5973a;
    private String b;

    @Override // com.huawei.hmf.tasks.Task
    public Task<T> addOnSuccessListener(OnSuccessListener<T> onSuccessListener) {
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Exception getException() {
        return null;
    }

    @Override // com.huawei.hmf.tasks.Task
    public T getResult() {
        return null;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <E extends Throwable> T getResultThrowException(Class<E> cls) throws Throwable {
        return null;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isCanceled() {
        return false;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isComplete() {
        return true;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isSuccessful() {
        return false;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<T> addOnSuccessListener(Executor executor, OnSuccessListener<T> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<T> addOnSuccessListener(Activity activity, OnSuccessListener<T> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<T> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<T> addOnFailureListener(OnFailureListener onFailureListener) {
        if (onFailureListener == null) {
            return this;
        }
        onFailureListener.onFailure(new ApiException(new Status(this.f5973a, this.b)));
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<T> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    public FailureTask(int i, String str) {
        this.f5973a = i;
        this.b = str;
    }

    public FailureTask() {
        this(-1, "context weak ref is recycled");
    }
}
