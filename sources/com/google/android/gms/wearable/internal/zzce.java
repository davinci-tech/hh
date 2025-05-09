package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.DataApi;

/* loaded from: classes8.dex */
final class zzce implements zzc<DataApi.DataListener> {
    private final /* synthetic */ IntentFilter[] zzbr;

    zzce(IntentFilter[] intentFilterArr) {
        this.zzbr = intentFilterArr;
    }

    @Override // com.google.android.gms.wearable.internal.zzc
    public final /* synthetic */ void zza(zzhg zzhgVar, BaseImplementation.ResultHolder resultHolder, DataApi.DataListener dataListener, ListenerHolder<DataApi.DataListener> listenerHolder) throws RemoteException {
        zzhgVar.zza((BaseImplementation.ResultHolder<Status>) resultHolder, dataListener, listenerHolder, this.zzbr);
    }
}
