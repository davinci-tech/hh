package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* loaded from: classes8.dex */
public final class zzcj extends zza implements zzch {
    zzcj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzch
    public final void zza(ListSubscriptionsResult listSubscriptionsResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, listSubscriptionsResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
