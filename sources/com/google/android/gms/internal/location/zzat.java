package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

/* loaded from: classes8.dex */
final class zzat extends com.google.android.gms.location.zzv {
    private final ListenerHolder<LocationCallback> zzda;

    public final void release() {
        synchronized (this) {
            this.zzda.clear();
        }
    }

    @Override // com.google.android.gms.location.zzu
    public final void onLocationResult(LocationResult locationResult) {
        this.zzda.notifyListener(new zzau(this, locationResult));
    }

    @Override // com.google.android.gms.location.zzu
    public final void onLocationAvailability(LocationAvailability locationAvailability) {
        this.zzda.notifyListener(new zzav(this, locationAvailability));
    }

    zzat(ListenerHolder<LocationCallback> listenerHolder) {
        this.zzda = listenerHolder;
    }
}
