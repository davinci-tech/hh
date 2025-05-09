package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* loaded from: classes2.dex */
final class zaal implements Runnable {
    private final /* synthetic */ zaak zagj;

    zaal(zaak zaakVar) {
        this.zagj = zaakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        googleApiAvailabilityLight = this.zagj.zaey;
        context = this.zagj.mContext;
        googleApiAvailabilityLight.cancelAvailabilityErrorNotifications(context);
    }
}
