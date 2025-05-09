package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/* loaded from: classes8.dex */
final class zzay implements ListenerHolder.Notifier<LocationListener> {
    private final /* synthetic */ Location zzdd;

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(LocationListener locationListener) {
        locationListener.onLocationChanged(this.zzdd);
    }

    zzay(zzax zzaxVar, Location location) {
        this.zzdd = location;
    }
}
