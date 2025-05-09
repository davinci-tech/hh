package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes8.dex */
final class zzhf extends zzgm<Status> {
    public zzhf(BaseImplementation.ResultHolder<Status> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzbn zzbnVar) {
        zza((zzhf) new Status(zzbnVar.statusCode));
    }
}
