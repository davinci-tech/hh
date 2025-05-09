package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.fitness.FitnessOptions;

/* loaded from: classes2.dex */
final class zzaw extends Api.AbstractClientBuilder<zzas, FitnessOptions> {
    private zzaw() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzas buildClient(Context context, Looper looper, ClientSettings clientSettings, FitnessOptions fitnessOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzas(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
