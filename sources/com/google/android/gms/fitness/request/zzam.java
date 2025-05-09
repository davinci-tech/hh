package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.DataPoint;

/* loaded from: classes8.dex */
final class zzam implements ListenerHolder.Notifier<OnDataPointListener> {
    private final /* synthetic */ DataPoint zzho;

    zzam(zzal zzalVar, DataPoint dataPoint) {
        this.zzho = dataPoint;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(OnDataPointListener onDataPointListener) {
        onDataPointListener.onDataPoint(this.zzho);
    }
}
