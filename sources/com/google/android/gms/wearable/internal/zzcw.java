package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.DataClient;

/* loaded from: classes8.dex */
final class zzcw extends UnregisterListenerMethod<zzhg, DataClient.OnDataChangedListener> {
    private final DataClient.OnDataChangedListener zzdk;

    private zzcw(DataClient.OnDataChangedListener onDataChangedListener, ListenerHolder.ListenerKey<DataClient.OnDataChangedListener> listenerKey) {
        super(listenerKey);
        this.zzdk = onDataChangedListener;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgg(taskCompletionSource), this.zzdk);
    }
}
