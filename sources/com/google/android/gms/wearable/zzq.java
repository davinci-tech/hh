package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzah;

/* loaded from: classes2.dex */
final class zzq implements Runnable {
    private final /* synthetic */ WearableListenerService.zzd zzao;
    private final /* synthetic */ zzah zzas;

    zzq(WearableListenerService.zzd zzdVar, zzah zzahVar) {
        this.zzao = zzdVar;
        this.zzas = zzahVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WearableListenerService.this.onCapabilityChanged(this.zzas);
    }
}
