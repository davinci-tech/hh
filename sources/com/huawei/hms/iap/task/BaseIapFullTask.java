package com.huawei.hms.iap.task;

import android.app.Activity;
import android.content.Context;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iap.entity.OrderStatusCode;
import com.huawei.hms.iapfull.IIapFullAPIVer4;
import com.huawei.hms.iapfull.IapFullAPIFactory;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class BaseIapFullTask<V extends Result, E> extends Task<V> {
    protected boolean mIsCompleted;
    protected boolean mIsSuccessful;
    protected OnFailureListener mOnFailureListener;
    protected OnSuccessListener<V> mOnSuccessListener;
    protected V mResult;

    @Override // com.huawei.hmf.tasks.Task
    public Exception getException() {
        return null;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <E extends Throwable> V getResultThrowException(Class<E> cls) throws Throwable {
        return null;
    }

    protected abstract void handleRequest(E e, IIapFullAPIVer4 iIapFullAPIVer4);

    @Override // com.huawei.hmf.tasks.Task
    public boolean isCanceled() {
        return false;
    }

    protected abstract void setResult();

    @Override // com.huawei.hmf.tasks.Task
    public boolean isSuccessful() {
        return this.mIsSuccessful;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isComplete() {
        return this.mIsCompleted;
    }

    protected void handleRequestSuccess() {
        this.mIsSuccessful = true;
        this.mIsCompleted = true;
        OnSuccessListener<V> onSuccessListener = this.mOnSuccessListener;
        if (onSuccessListener != null) {
            onSuccessListener.onSuccess(this.mResult);
        }
    }

    protected void handleRequestFailed(int i, String str) {
        this.mIsSuccessful = false;
        this.mIsCompleted = true;
        OnFailureListener onFailureListener = this.mOnFailureListener;
        if (onFailureListener != null) {
            onFailureListener.onFailure(new IapApiException(new Status(i, str)));
        }
    }

    @Override // com.huawei.hmf.tasks.Task
    public V getResult() {
        return this.mResult;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<V> addOnSuccessListener(Executor executor, OnSuccessListener<V> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<V> addOnSuccessListener(OnSuccessListener<V> onSuccessListener) {
        if (onSuccessListener != null) {
            if (isComplete() && isSuccessful()) {
                onSuccessListener.onSuccess(this.mResult);
            } else {
                this.mOnSuccessListener = onSuccessListener;
            }
        }
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<V> addOnSuccessListener(Activity activity, OnSuccessListener<V> onSuccessListener) {
        addOnSuccessListener(onSuccessListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<V> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<V> addOnFailureListener(OnFailureListener onFailureListener) {
        if (onFailureListener != null) {
            if (!isComplete() || isSuccessful()) {
                this.mOnFailureListener = onFailureListener;
            } else {
                onFailureListener.onFailure(new IapApiException(this.mResult.getStatus()));
            }
        }
        return this;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<V> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        addOnFailureListener(onFailureListener);
        return this;
    }

    protected BaseIapFullTask(Context context, E e) {
        setResult();
        if (context != null) {
            handleRequest(e, IapFullAPIFactory.createIapFullAPIVer4(context));
            return;
        }
        HMSLog.e("BaseIapFullTask", "context is null.");
        this.mResult.setStatus(new Status(OrderStatusCode.ORDER_STATE_PARAM_ERROR, "param is error"));
        this.mIsSuccessful = false;
        this.mIsCompleted = true;
    }
}
