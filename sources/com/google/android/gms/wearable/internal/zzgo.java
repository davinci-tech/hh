package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes8.dex */
final class zzgo extends zzgm<Status> {
    public zzgo(BaseImplementation.ResultHolder<Status> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zzb(zzbt zzbtVar) {
        zza((zzgo) new Status(zzbtVar.statusCode));
    }
}
