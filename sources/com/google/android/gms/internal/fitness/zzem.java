package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionStopResult;

/* loaded from: classes8.dex */
final class zzem extends zzco {
    private final BaseImplementation.ResultHolder<SessionStopResult> zzev;

    private zzem(BaseImplementation.ResultHolder<SessionStopResult> resultHolder) {
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzcn
    public final void zza(SessionStopResult sessionStopResult) {
        this.zzev.setResult(sessionStopResult);
    }

    /* synthetic */ zzem(BaseImplementation.ResultHolder resultHolder, zzef zzefVar) {
        this(resultHolder);
    }
}
