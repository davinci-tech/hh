package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.CapabilityApi;
import java.util.Map;

/* loaded from: classes8.dex */
final class zzgq extends zzgm<CapabilityApi.GetAllCapabilitiesResult> {
    public zzgq(BaseImplementation.ResultHolder<CapabilityApi.GetAllCapabilitiesResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzdi zzdiVar) {
        Map zza;
        Status zzb = zzgd.zzb(zzdiVar.statusCode);
        zza = zzgk.zza(zzdiVar.zzdp);
        zza((zzgq) new zzx(zzb, zza));
    }
}
