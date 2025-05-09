package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataClient;

/* loaded from: classes8.dex */
final class zzcv extends RegisterListenerMethod<zzhg, DataClient.OnDataChangedListener> {
    private final IntentFilter[] zzba;
    private final ListenerHolder<DataApi.DataListener> zzbz;
    private final DataClient.OnDataChangedListener zzdk;

    private zzcv(DataClient.OnDataChangedListener onDataChangedListener, IntentFilter[] intentFilterArr, ListenerHolder<DataClient.OnDataChangedListener> listenerHolder) {
        super(listenerHolder);
        this.zzdk = onDataChangedListener;
        this.zzba = intentFilterArr;
        this.zzbz = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final /* synthetic */ void registerListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgh(taskCompletionSource), this.zzdk, this.zzbz, this.zzba);
    }
}
