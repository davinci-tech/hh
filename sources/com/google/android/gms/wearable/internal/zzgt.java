package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.Channel;

/* loaded from: classes8.dex */
final class zzgt extends zzgm<Channel.GetOutputStreamResult> {
    private final zzbr zzeu;

    public zzgt(BaseImplementation.ResultHolder<Channel.GetOutputStreamResult> resultHolder, zzbr zzbrVar) {
        super(resultHolder);
        this.zzeu = (zzbr) Preconditions.checkNotNull(zzbrVar);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzdo zzdoVar) {
        zzbl zzblVar;
        if (zzdoVar.zzdr != null) {
            zzblVar = new zzbl(new ParcelFileDescriptor.AutoCloseOutputStream(zzdoVar.zzdr));
            this.zzeu.zza(new zzbm(zzblVar));
        } else {
            zzblVar = null;
        }
        zza((zzgt) new zzbh(new Status(zzdoVar.statusCode), zzblVar));
    }
}
