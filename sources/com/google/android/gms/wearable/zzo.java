package com.google.android.gms.wearable;

import com.google.android.gms.wearable.WearableListenerService;
import com.google.android.gms.wearable.internal.zzfo;

/* loaded from: classes2.dex */
final class zzo implements Runnable {
    private final /* synthetic */ WearableListenerService.zzd zzao;
    private final /* synthetic */ zzfo zzaq;

    zzo(WearableListenerService.zzd zzdVar, zzfo zzfoVar) {
        this.zzao = zzdVar;
        this.zzaq = zzfoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WearableListenerService.this.onPeerDisconnected(this.zzaq);
    }
}
