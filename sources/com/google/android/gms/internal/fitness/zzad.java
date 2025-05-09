package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes2.dex */
final class zzad extends Api.AbstractClientBuilder<zzab, Api.ApiOptions.NoOptions> {
    private zzad() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzab buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzab(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
