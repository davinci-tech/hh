package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes2.dex */
final class zzho implements ListenerHolder.Notifier<CapabilityApi.CapabilityListener> {
    private final /* synthetic */ zzah zzfr;

    zzho(zzah zzahVar) {
        this.zzfr = zzahVar;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(CapabilityApi.CapabilityListener capabilityListener) {
        capabilityListener.onCapabilityChanged(this.zzfr);
    }
}
