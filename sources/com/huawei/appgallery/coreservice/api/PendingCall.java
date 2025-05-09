package com.huawei.appgallery.coreservice.api;

import android.os.RemoteException;
import com.huawei.appgallery.coreservice.f;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCResponse;
import com.huawei.appmarket.framework.coreservice.Status;
import com.huawei.appmarket.framework.coreservice.a;
import defpackage.aeu;
import defpackage.afu;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class PendingCall<C extends BaseIPCRequest, R extends BaseIPCResponse> {

    /* renamed from: a, reason: collision with root package name */
    private final f f1867a;
    public WeakReference<ApiClient> api;
    protected final Object lock = new Object();
    private final CountDownLatch e = new CountDownLatch(1);
    public Status<R> mResult = new Status<>(10);
    private boolean d = false;

    public interface Callback<R extends BaseIPCResponse> {
        void onReceiveStatus(Status<R> status);
    }

    public void setResult(Status<R> status) {
        synchronized (this.lock) {
            this.mResult = status;
            this.d = true;
        }
    }

    public void setCallback(final Callback<R> callback) {
        afu.e("PendingCall", "setResultCallback");
        ApiClient apiClient = this.api.get();
        if (apiClient != null) {
            this.f1867a.a(apiClient, new a.AbstractBinderC0043a(this) { // from class: com.huawei.appgallery.coreservice.api.PendingCall.1
                @Override // com.huawei.appmarket.framework.coreservice.a
                public void call(Status status) throws RemoteException {
                    callback.onReceiveStatus(status);
                }
            });
            return;
        }
        afu.a("PendingCall", "api is null");
        synchronized (this.lock) {
            this.mResult.setStatusCode(12);
        }
        callback.onReceiveStatus(getResult());
    }

    public Status<R> getResult() {
        Status<R> status;
        synchronized (this.lock) {
            status = this.mResult;
        }
        return status;
    }

    public void awaitOnAnyThread() {
        afu.e("PendingCall", "awaitOnAnyThread");
        ApiClient apiClient = this.api.get();
        if (apiClient != null) {
            this.f1867a.a(apiClient, new a.AbstractBinderC0043a() { // from class: com.huawei.appgallery.coreservice.api.PendingCall.2
                @Override // com.huawei.appmarket.framework.coreservice.a
                public void call(Status status) throws RemoteException {
                    synchronized (PendingCall.this.lock) {
                        PendingCall.this.setResult(status);
                    }
                }
            });
            return;
        }
        afu.a("PendingCall", "api is null");
        synchronized (this.lock) {
            this.mResult.setStatusCode(12);
        }
    }

    public Status<R> await(long j, TimeUnit timeUnit) {
        awaitOnAnyThread();
        synchronized (this.lock) {
            if (!this.d) {
                try {
                    afu.b("PendingCall", "await:" + this.e.await(j, timeUnit));
                } catch (InterruptedException unused) {
                    afu.d("PendingCall", "InterruptedException");
                }
            }
        }
        return getResult();
    }

    public Status<R> await() {
        awaitOnAnyThread();
        synchronized (this.lock) {
            if (!this.d) {
                try {
                    this.e.await();
                } catch (InterruptedException unused) {
                    afu.d("PendingCall", "InterruptedException");
                }
            }
        }
        return getResult();
    }

    public PendingCall(ApiClient apiClient, C c) {
        this.f1867a = new aeu(apiClient.getContext(), c);
        this.api = new WeakReference<>(apiClient);
    }
}
