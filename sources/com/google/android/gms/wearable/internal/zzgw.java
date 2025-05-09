package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

/* loaded from: classes8.dex */
final class zzgw extends zzgm<DataItemBuffer> {
    public zzgw(BaseImplementation.ResultHolder<DataItemBuffer> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zzb(DataHolder dataHolder) {
        zza((zzgw) new DataItemBuffer(dataHolder));
    }
}
