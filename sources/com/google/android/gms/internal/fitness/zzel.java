package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionReadResult;

/* loaded from: classes8.dex */
final class zzel extends zzcl {
    private final BaseImplementation.ResultHolder<SessionReadResult> zzev;

    private zzel(BaseImplementation.ResultHolder<SessionReadResult> resultHolder) {
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzck
    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        this.zzev.setResult(sessionReadResult);
    }

    /* synthetic */ zzel(BaseImplementation.ResultHolder resultHolder, zzef zzefVar) {
        this(resultHolder);
    }
}
