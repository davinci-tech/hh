package com.huawei.hmf.orb.aidl.client.impl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.huawei.hmf.orb.CommonCode;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.aidl.DatagramTransport;
import com.huawei.hmf.orb.aidl.client.ApiClient;
import com.huawei.hmf.orb.aidl.client.PendingResult;
import com.huawei.hmf.orb.aidl.client.Result;
import com.huawei.hmf.orb.aidl.client.ResultCallback;
import com.huawei.hmf.orb.aidl.client.Status;
import com.huawei.hmf.services.internal.GenericTypeReflector;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public abstract class PendingResultImpl<R extends Result, T extends IMessageEntity> extends PendingResult<R> {
    private WeakReference<ApiClient> api;
    private CountDownLatch countLatch;
    private R result = null;
    protected DatagramTransport transport = null;

    public abstract R onComplete(T t);

    public PendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        init(apiClient, str, iMessageEntity, getResponseType());
    }

    public PendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        init(apiClient, str, iMessageEntity, cls);
    }

    private void init(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        if (apiClient == null) {
            throw new IllegalArgumentException("apiClient cannot be null.");
        }
        this.api = new WeakReference<>(apiClient);
        this.countLatch = new CountDownLatch(1);
        this.transport = new IPCTransport(str, iMessageEntity, cls);
    }

    protected Class<T> getResponseType() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override // com.huawei.hmf.orb.aidl.client.PendingResult
    public final R await() {
        ApiClient apiClient = this.api.get();
        if (!checkApiClient(apiClient)) {
            setResult(CommonCode.ErrorCode.CLIENT_API_INVALID, null);
            return this.result;
        }
        this.transport.send(apiClient, new DatagramTransport.CallBack() { // from class: com.huawei.hmf.orb.aidl.client.impl.PendingResultImpl.1
            @Override // com.huawei.hmf.orb.aidl.DatagramTransport.CallBack
            public void onCallback(int i, IMessageEntity iMessageEntity) {
                PendingResultImpl.this.setResult(i, iMessageEntity);
                PendingResultImpl.this.countLatch.countDown();
            }
        });
        try {
            this.countLatch.await();
        } catch (InterruptedException unused) {
            setResult(CommonCode.ErrorCode.INTERNAL_ERROR, null);
        }
        return this.result;
    }

    @Override // com.huawei.hmf.orb.aidl.client.PendingResult
    public R await(long j, TimeUnit timeUnit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("await must not be called on the UI thread");
        }
        ApiClient apiClient = this.api.get();
        if (!checkApiClient(apiClient)) {
            setResult(CommonCode.ErrorCode.CLIENT_API_INVALID, null);
            return this.result;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.transport.post(apiClient, new DatagramTransport.CallBack() { // from class: com.huawei.hmf.orb.aidl.client.impl.PendingResultImpl.2
            @Override // com.huawei.hmf.orb.aidl.DatagramTransport.CallBack
            public void onCallback(int i, IMessageEntity iMessageEntity) {
                if (!atomicBoolean.get()) {
                    PendingResultImpl.this.setResult(i, iMessageEntity);
                }
                PendingResultImpl.this.countLatch.countDown();
            }
        });
        try {
            if (!this.countLatch.await(j, timeUnit)) {
                atomicBoolean.set(true);
                setResult(CommonCode.ErrorCode.EXECUTE_TIMEOUT, null);
            }
        } catch (InterruptedException unused) {
            setResult(CommonCode.ErrorCode.INTERNAL_ERROR, null);
        }
        return this.result;
    }

    @Override // com.huawei.hmf.orb.aidl.client.PendingResult
    public final void setResultCallback(ResultCallback<R> resultCallback) {
        setResultCallback(Looper.getMainLooper(), resultCallback);
    }

    @Override // com.huawei.hmf.orb.aidl.client.PendingResult
    public final void setResultCallback(Looper looper, final ResultCallback<R> resultCallback) {
        if (looper == null) {
            looper = Looper.myLooper();
        }
        final CallbackHandler callbackHandler = new CallbackHandler(looper);
        ApiClient apiClient = this.api.get();
        if (!checkApiClient(apiClient)) {
            setResult(CommonCode.ErrorCode.CLIENT_API_INVALID, null);
            callbackHandler.post(resultCallback, this.result);
        } else {
            this.transport.post(apiClient, new DatagramTransport.CallBack() { // from class: com.huawei.hmf.orb.aidl.client.impl.PendingResultImpl.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.huawei.hmf.orb.aidl.DatagramTransport.CallBack
                public void onCallback(int i, IMessageEntity iMessageEntity) {
                    PendingResultImpl.this.setResult(i, iMessageEntity);
                    callbackHandler.post(resultCallback, PendingResultImpl.this.result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void setResult(int i, IMessageEntity iMessageEntity) {
        if (i <= 0) {
            this.result = onComplete(iMessageEntity);
        } else {
            this.result = onError(i);
        }
    }

    protected R onError(int i) {
        try {
            R r = (R) GenericTypeReflector.getType(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            this.result = r;
            r.setStatus(new Status(i));
            return this.result;
        } catch (Exception unused) {
            return null;
        }
    }

    protected boolean checkApiClient(ApiClient apiClient) {
        return apiClient != null && apiClient.isConnected();
    }

    protected static class CallbackHandler<R extends Result> extends Handler {
        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public void post(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            Pair pair = (Pair) message.obj;
            send((ResultCallback) pair.first, (Result) pair.second);
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected void send(ResultCallback<? super R> resultCallback, R r) {
            resultCallback.onResult(r);
        }
    }
}
