package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;

/* loaded from: classes2.dex */
final class zzr implements Runnable {
    private final /* synthetic */ WearableListenerService.zzd zzao;
    private final /* synthetic */ com.google.android.gms.wearable.internal.zzl zzat;

    zzr(WearableListenerService.zzd zzdVar, com.google.android.gms.wearable.internal.zzl zzlVar) {
        this.zzao = zzdVar;
        this.zzat = zzlVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.wearable.internal.zzl, com.google.android.gms.wearable.zzd] */
    @Override // java.lang.Runnable
    public final void run() {
        WearableListenerService.this.onNotificationReceived(this.zzat);
    }
}
