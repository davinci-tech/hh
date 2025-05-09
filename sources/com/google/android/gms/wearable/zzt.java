package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzas;
import com.google.android.gms.wearable.internal.zzaw;

/* loaded from: classes2.dex */
final class zzt implements Runnable {
    private final /* synthetic */ WearableListenerService.zzd zzao;
    private final /* synthetic */ zzaw zzav;

    zzt(WearableListenerService.zzd zzdVar, zzaw zzawVar) {
        this.zzao = zzdVar;
        this.zzav = zzawVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzas zzasVar;
        this.zzav.zza(WearableListenerService.this);
        zzaw zzawVar = this.zzav;
        zzasVar = WearableListenerService.this.zzaj;
        zzawVar.zza(zzasVar);
    }
}
