package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.DataApi;

/* loaded from: classes8.dex */
final class zzgv extends zzgm<DataApi.DataItemResult> {
    public zzgv(BaseImplementation.ResultHolder<DataApi.DataItemResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzec zzecVar) {
        zza((zzgv) new zzcg(zzgd.zzb(zzecVar.statusCode), zzecVar.zzdy));
    }
}
