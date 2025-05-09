package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* loaded from: classes8.dex */
final class zzc implements ListenerHolder.Notifier<BleScanCallback> {
    zzc(zza zzaVar) {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(BleScanCallback bleScanCallback) {
        bleScanCallback.onScanStopped();
    }
}
