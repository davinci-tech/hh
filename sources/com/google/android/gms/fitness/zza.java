package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zza implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zza();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((BleDevicesResult) result).getClaimedBleDevices();
    }

    private zza() {
    }
}
