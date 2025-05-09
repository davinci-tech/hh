package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzt;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
final class zzc implements SensorEventDispatcher {
    private final zzt zzhr;

    zzc(zzt zztVar) {
        this.zzhr = (zzt) Preconditions.checkNotNull(zztVar);
    }

    @Override // com.google.android.gms.fitness.service.SensorEventDispatcher
    public final void publish(DataPoint dataPoint) throws RemoteException {
        dataPoint.zzg();
        this.zzhr.zzc(dataPoint);
    }

    @Override // com.google.android.gms.fitness.service.SensorEventDispatcher
    public final void publish(List<DataPoint> list) throws RemoteException {
        Iterator<DataPoint> it = list.iterator();
        while (it.hasNext()) {
            publish(it.next());
        }
    }
}
