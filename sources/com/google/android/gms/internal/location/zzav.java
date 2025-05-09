package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

/* loaded from: classes8.dex */
final class zzav implements ListenerHolder.Notifier<LocationCallback> {
    private final /* synthetic */ LocationAvailability zzdc;

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(LocationCallback locationCallback) {
        locationCallback.onLocationAvailability(this.zzdc);
    }

    zzav(zzat zzatVar, LocationAvailability locationAvailability) {
        this.zzdc = locationAvailability;
    }
}
