package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
public abstract class UnregisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder.ListenerKey<L> zajl;

    public UnregisterListenerMethod(ListenerHolder.ListenerKey<L> listenerKey) {
        this.zajl = listenerKey;
    }

    protected abstract void unregisterListener(A a2, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException;

    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zajl;
    }
}
