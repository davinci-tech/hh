package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.DataApi;

/* loaded from: classes2.dex */
final class zzgx extends zzgm<DataApi.GetFdForAssetResult> {
    public zzgx(BaseImplementation.ResultHolder<DataApi.GetFdForAssetResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzee zzeeVar) {
        zza((zzgx) new zzci(zzgd.zzb(zzeeVar.statusCode), zzeeVar.zzdz));
    }
}
