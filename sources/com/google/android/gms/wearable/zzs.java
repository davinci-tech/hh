package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;

/* loaded from: classes2.dex */
final class zzs implements Runnable {
    private final /* synthetic */ WearableListenerService.zzd zzao;
    private final /* synthetic */ com.google.android.gms.wearable.internal.zzi zzau;

    zzs(WearableListenerService.zzd zzdVar, com.google.android.gms.wearable.internal.zzi zziVar) {
        this.zzao = zzdVar;
        this.zzau = zziVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.wearable.internal.zzi, com.google.android.gms.wearable.zzb] */
    @Override // java.lang.Runnable
    public final void run() {
        WearableListenerService.this.onEntityUpdate(this.zzau);
    }
}
