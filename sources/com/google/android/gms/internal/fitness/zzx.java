package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes2.dex */
final class zzx extends Api.AbstractClientBuilder<zzv, Api.ApiOptions.NoOptions> {
    private zzx() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzv buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzv(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
