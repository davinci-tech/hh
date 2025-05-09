package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.MessageApi;

/* loaded from: classes2.dex */
final class zzhe extends zzgm<MessageApi.SendMessageResult> {
    public zzhe(BaseImplementation.ResultHolder<MessageApi.SendMessageResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzga zzgaVar) {
        zza((zzhe) new zzey(zzgd.zzb(zzgaVar.statusCode), zzgaVar.zzeh));
    }
}
