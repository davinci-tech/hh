package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* loaded from: classes8.dex */
final class zzda extends zzes {
    private final BaseImplementation.ResultHolder<BleDevicesResult> zzev;

    private zzda(BaseImplementation.ResultHolder<BleDevicesResult> resultHolder) {
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzer
    public final void zza(BleDevicesResult bleDevicesResult) {
        this.zzev.setResult(bleDevicesResult);
    }

    /* synthetic */ zzda(BaseImplementation.ResultHolder resultHolder, zzcu zzcuVar) {
        this(resultHolder);
    }
}
