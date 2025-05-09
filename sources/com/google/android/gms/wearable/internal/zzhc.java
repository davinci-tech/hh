package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes8.dex */
final class zzhc extends zzgm<Status> {
    public zzhc(BaseImplementation.ResultHolder<Status> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzbp zzbpVar) {
        zza((zzhc) new Status(zzbpVar.statusCode));
    }
}
