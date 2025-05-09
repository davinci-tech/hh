package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;

/* loaded from: classes8.dex */
public final class zzal extends com.google.android.gms.fitness.data.zzu {
    private final ListenerHolder<OnDataPointListener> zzhn;

    private zzal(ListenerHolder<OnDataPointListener> listenerHolder) {
        this.zzhn = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    @Override // com.google.android.gms.fitness.data.zzt
    public final void zzc(DataPoint dataPoint) throws RemoteException {
        this.zzhn.notifyListener(new zzam(this, dataPoint));
    }

    public final void release() {
        this.zzhn.clear();
    }

    /* synthetic */ zzal(ListenerHolder listenerHolder, zzam zzamVar) {
        this(listenerHolder);
    }
}
